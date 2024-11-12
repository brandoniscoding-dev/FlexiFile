package com.camjsp.cli;

import picocli.CommandLine;
import com.camjsp.cli.commands.HelpCommand;
import com.camjsp.cli.commands.VersionCommand;

@CommandLine.Command(
    name = "FlexiFile",
    mixinStandardHelpOptions = true,
    version = "FlexiFile 1.0",
    description = "FlexiFile CLI - A versatile file conversion tool",
    subcommands = {HelpCommand.class, VersionCommand.class}
)
public class CommandLineInterface implements Runnable {

    @Override
    public void run() {
        System.out.println("Welcome to FlexiFile! Use -h or --help to see available commands.");
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new CommandLineInterface()).execute(args);
        System.exit(exitCode);
    }
}
