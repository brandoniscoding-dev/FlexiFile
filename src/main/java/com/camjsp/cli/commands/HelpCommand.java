package com.camjsp.cli.commands;

import picocli.CommandLine.Command;
import com.camjsp.utils.LoggerUtils;

@Command(name = "help", description = "Lists all available commands.")
public class HelpCommand implements Runnable {

    @Override
    public void run() {
        LoggerUtils.log("Help command executed");
        System.out.println("Available commands:");
        System.out.println("  help    - Lists all available commands");
        System.out.println("  version - Displays the application version");
    }
}
