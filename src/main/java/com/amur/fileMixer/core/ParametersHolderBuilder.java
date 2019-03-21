package com.amur.fileMixer.core;

public class ParametersHolderBuilder {
    private boolean cleanMixPrefix;
    private String filesDirectory;

    public ParametersHolderBuilder cleanMixPrefix(boolean cleanMixPrefix) {
        this.cleanMixPrefix = cleanMixPrefix;
        return this;
    }

    public ParametersHolderBuilder filesDirectory(String filesDirectory) {
        this.filesDirectory = filesDirectory;
        return this;
    }

    public ParametersHolder done() {
        return new ParametersHolder(cleanMixPrefix, filesDirectory);
    }
}
