package com.amur.fileMixer.core;

public class ParametersHolder {
    private final boolean cleanMixPrefix;
    private final String filesDirectory;

    public ParametersHolder(boolean cleanMixPrefix, String filesDirectory) {
        this.cleanMixPrefix = cleanMixPrefix;
        this.filesDirectory = filesDirectory;
    }

    public boolean isCleanMixPrefix() {
        return cleanMixPrefix;
    }

    public String getFilesDirectory() {
        return filesDirectory;
    }

    @Override
    public String toString() {
        return "ParametersHolder{" +
                "cleanMixPrefix=" + cleanMixPrefix +
                ", filesDirectory='" + filesDirectory + '\'' +
                '}';
    }
}
