package com.amur.fileMixer.cli;

import picocli.CommandLine;

public class Main {

    public static void main(String[] args) {
        CommandLine.call(new MixerCli(), args);
    }
}
