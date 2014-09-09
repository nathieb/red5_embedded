package com.red5;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.Level;
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

        logger = Logger.getLogger(Main.class);
        //commandLine = new CommandLine();
        SLF4JBridgeHandler.install();
        logger.info("Application v" + Main.class.getPackage().getImplementationVersion());
        logger.info("Build: " + ResourceBundle.getBundle("buildInfo").getString("buildNumber"));
        //parseCommandLine(args);
        WFProcess.main(args);
        TomcatStart tomcat = new TomcatStart();
        try {
            tomcat.setup();
        } catch (Throwable ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
