package com.red5;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.slf4j.bridge.SLF4JBridgeHandler;

/**
 * An awesome red5embedded
 *
 */
public class Main {

    private static Logger logger;
    private static CommandLine commandLine;
    private static CmdLineParser parser;
    private static File homeLoggingDir;

    public static void main(final String[] args) throws Exception {
        createlogdir();
        logger = Logger.getLogger(Main.class);
        commandLine = new CommandLine();
        SLF4JBridgeHandler.install();
        logger.info("Application v" + Main.class.getPackage().getImplementationVersion());
        logger.info("Build: " + ResourceBundle.getBundle("buildInfo").getString("buildNumber"));
        parseCommandLine(args);
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
                File filelog = new File(homeLoggingDir+"/genghis.log");
                File filehtml = new File(homeLoggingDir+"/bensApps.html");
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
