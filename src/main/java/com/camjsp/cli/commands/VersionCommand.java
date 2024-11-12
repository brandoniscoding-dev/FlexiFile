package com.camjsp.cli.commands;

import picocli.CommandLine.Command;
import com.camjsp.utils.LoggerUtils;
import com.indvd00m.ascii.render.Render;
import com.indvd00m.ascii.render.api.ICanvas;
import com.indvd00m.ascii.render.api.IContextBuilder;
import com.indvd00m.ascii.render.elements.PseudoText;


@Command(name = "version", description = "Displays the application version.")
public class VersionCommand implements Runnable {

    @Override
    public void run() {
        LoggerUtils.log("Version command executed");

        Render render = new Render();
        render.setPseudoCanvas(true); 

        IContextBuilder builder = render.newBuilder();
        builder.width(200).height(40); 
        builder.element(new PseudoText("FlexiFile", false)); 
        ICanvas canvas = render.render(builder.build());

        String asciiArt = canvas.getText();
        System.out.println(asciiArt); 
        System.out.println("FlexiFile Version 1.0");

    }
}
