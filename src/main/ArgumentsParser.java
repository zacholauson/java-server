package main;

import java.io.File;
import java.lang.Integer;
import java.lang.String;
import java.lang.System;
import java.util.Arrays;

public class ArgumentsParser {
    public static final int    DEFAULT_PORT = 5000;
    public static final String DEFAULT_DIR  = System.getProperty("user.dir");
    private String[] args;


    public ArgumentsParser(String[] args){
        this.args = args;
    }

    public int getPort(){
        int port = DEFAULT_PORT;
        if (flagSet("-p")) { port = Integer.parseInt(getFlagValue("-p")); }
        return port;
    }

    public String getDirectory(){
        String directory = DEFAULT_DIR;
        if (flagSet("-d")) { directory = getFlagValue("-d"); }
        return new File(directory).getAbsolutePath();
    }

    public boolean flagSet(String flag){
        int index = Arrays.asList(args).indexOf(flag);
        return (index != -1);
    }

    public int getFlagIndex(String flag){
        return Arrays.asList(args).indexOf(flag);
    }

    public String getFlagValue(String flag) {
        return args[(getFlagIndex(flag)) + 1];
    }
}
