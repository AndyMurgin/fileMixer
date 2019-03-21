package com.amur.fileMixer.core;

import java.util.Arrays;
import java.util.stream.Stream;

public abstract class InputParser {

    private enum InputParameters {

        CLEAN_MIX_PREFIX(new String[]{"-c", "--clean"}); //potentially where will be more parameters here

        private String[] literals;

        InputParameters(String[] literals) {
            this.literals = literals;
        }

        public boolean matches(String input) {
            return Arrays.asList(literals).contains(input);
        }
    }

    //simple implementation - redesign when other more complex input parameters occur
    public static ParametersHolder getParameters(String[] args) {
        ParametersHolderBuilder parametersBuilder = new ParametersHolderBuilder();
        Stream.of(args).forEach(arg -> process(arg, parametersBuilder));
        return parametersBuilder.done();
    }

    private static void process(String arg, ParametersHolderBuilder currentBuilder) {
        if (InputParameters.CLEAN_MIX_PREFIX.matches(arg)) {
            currentBuilder.cleanMixPrefix(true);
        } else {
            currentBuilder.filesDirectory(arg);
        }
    }
}
