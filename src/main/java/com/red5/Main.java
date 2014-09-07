package com.red5;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import javax.naming.Context;
import org.apache.catalina.Globals;
import org.apache.catalina.core.AprLifecycleListener;
import org.apache.catalina.core.StandardServer;
import org.apache.catalina.startup.Tomcat;

import org.apache.log4j.Logger;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.slf4j.bridge.SLF4JBridgeHandler;

/**
 * An awesome red5embedded
 * @author Olivier Thiébaut
 */
public class Main {

    private static Logger logger;
    private static CommandLine commandLine;
    private static CmdLineParser parser;
    private static File homeLoggingDir;
    private static Tomcat tomcat;

    public static void main(final String[] args) throws Exception {
        createlogdir();
        logger = Logger.getLogger(Main.class);
        commandLine = new CommandLine();
        SLF4JBridgeHandler.install();
        logger.info("Application v" + Main.class.getPackage().getImplementationVersion());
        logger.info("Build: " + ResourceBundle.getBundle("buildInfo").getString("buildNumber"));
        parseCommandLine(args);
        tomcat = new Tomcat();
        tomcat.setPort(9090);
        tomcat.setBaseDir(".");
        // set up context,
        //  "" indicates the path of the ROOT context
        //  tmpdir is used as docbase because we are not serving any files in this example
        File base = new File(System.getProperty("java.io.tmpdir"));
        org.apache.catalina.Context rootCtx = tomcat.addContext("/media", base.getAbsolutePath());
        rootCtx.getServletContext().setAttribute(Globals.ALT_DD_ATTR, Main.class.getResource("web.xml"));
        // Add the 'killer switch' servlet (used to shut down the server) to the context
        Tomcat.addServlet((org.apache.catalina.Context) rootCtx, "Servlet1", new Servlet1());
        rootCtx.addServletMapping("/", "Servlet1");
        Tomcat.addServlet((org.apache.catalina.Context) rootCtx, "mediaServer", new MediaServer());
        rootCtx.addServletMapping("/", "mediaServer");

        tomcat.getHost().setAppBase(Main.class.getResource("").getPath() + "/../../../webapps/");

        String contextPath = "/red5";

        // Add AprLifecycleListener
        StandardServer server = (StandardServer) tomcat.getServer();
        AprLifecycleListener listener = new AprLifecycleListener();
        server.addLifecycleListener(listener);
        tomcat.addWebapp(null,"", Main.class.getResource("").getPath() + "/../../../webapps/");
        tomcat.start();
        tomcat.getServer().await();
    }

    private static void parseCommandLine(final String[] args) {
        parser = new CmdLineParser(commandLine);
        try {
            parser.parseArgument(args);
            if (commandLine.isHelp()) {
                printUsageAndExit();
            }

        } catch (final CmdLineException e) {
            logger.error(e);
            printUsageAndExit();
        }
    }

    private static void printUsageAndExit() {
        System.out.println("usage: java -jar <name-of-jar> [options...]");
        parser.printUsage(System.out);
        System.exit(-1);
    }

    private static void createlogdir() {
        homeLoggingDir = new File(Main.class.getResource("").getPath() + "/logs/");
        if (!homeLoggingDir.exists()) {
            homeLoggingDir.mkdirs();
            try {
                File filelog = new File(homeLoggingDir + "/genghis.log");
                File filehtml = new File(homeLoggingDir + "/bensApps.html");
                if (filelog.createNewFile() && filehtml.createNewFile()) {
                    System.out.println("File is created!");
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
