package com.amur.fileMixer.cli;

import com.amur.fileMixer.api.Mixer;
import com.amur.fileMixer.core.SimplePrefixMixer;
import picocli.CommandLine;

public class Main {

    private static final Mixer mixer = new SimplePrefixMixer();

    public static void main(String[] args) {
        CommandLine.call(new MixerCli(mixer), args);
    }
}
