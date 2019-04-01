package com.amur.fileMixer.cli;

import com.amur.fileMixer.api.Mixer;
import com.amur.fileMixer.api.ParametersValidator;
import com.amur.fileMixer.core.SimplePrefixMixer;
import com.amur.fileMixer.validation.InputParametersValidator;
import picocli.CommandLine;

public class Starter {

    private static final Mixer mixer = new SimplePrefixMixer();
    private static final ParametersValidator validator = new InputParametersValidator();

    public static void main(String[] args) {
        CommandLine.call(new MixerCli(mixer, validator), args);
    }
}
