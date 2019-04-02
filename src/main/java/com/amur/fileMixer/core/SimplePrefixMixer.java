package com.amur.fileMixer.core;

import com.amur.fileMixer.api.Mixer;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class SimplePrefixMixer implements Mixer {

    // need redesign in case if more parameters occur
    @Override
    public void mix(File filesDirectory, boolean cleanMixPrefix) {
        try {
            System.out.println("Getting files from directory " + filesDirectory.getCanonicalPath());

            File[] files = filesDirectory.listFiles(File::isFile);
            Objects.requireNonNull(files);
            Arrays.asList(files).forEach(file -> MixUtils.addPrefix(file, cleanMixPrefix));
        } catch (IOException e) {
            System.err.println("Error occured during mixing: " + e.getMessage());
        }
    }
}
