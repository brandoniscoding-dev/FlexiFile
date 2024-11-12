package com.camjsp.utils;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerUtils {

    private static final Logger logger = Logger.getLogger(LoggerUtils.class.getName());
    private static FileHandler fileHandler;

    static {
        try {
            // Initialiser le FileHandler pour enregistrer dans un fichier
            fileHandler = new FileHandler("flexifile.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            
            // Supprimer les handlers existants (ConsoleHandler)
            logger.setUseParentHandlers(false);
            
            // Ajouter uniquement le FileHandler
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);
        } catch (IOException e) {
            System.err.println("Failed to initialize logger: " + e.getMessage());
        }
    }

    private LoggerUtils() {
    }

    public static void log(String message) {
        logger.info(message);
    }

    public static void logError(String message) {
        logger.severe(message);
    }

    public static void logWarning(String message) {
        logger.warning(message);
    }
}
