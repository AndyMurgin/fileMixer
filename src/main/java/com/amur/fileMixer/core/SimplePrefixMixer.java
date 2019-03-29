package com.amur.fileMixer.core;

import com.amur.fileMixer.api.Mixer;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.FileSystems;
import java.util.Arrays;
import java.util.Objects;

public class SimplePrefixMixer implements Mixer {

    private static final String osPathSeparator = FileSystems.getDefault().getSeparator();

    // need redesign in case if more parameters occur
    @Override
    public void mix(File filesDirectory, boolean cleanMixPrefix) {
        try {
            System.out.println("Getting files from directory " + filesDirectory.getCanonicalPath());

            File[] files = filesDirectory.listFiles(File::isFile);
            Objects.requireNonNull(files);
            Arrays.asList(files).forEach(file -> addPrefix(file, cleanMixPrefix));
        } catch (IOException e) {
            System.err.println("Error occured during mixing: " + e.getMessage());
        }
    }

    private void addPrefix(File file, boolean cleanMixPrefix) {
        System.out.println("Processing " + file.getName());
        if (cleanMixPrefix) {
            file = tryCleanMixPrefix(file);
        }
        renameFile(file, getNewMixedName(file));
    }

    private File tryCleanMixPrefix(File file) {
        System.out.println("Trying to clean previously added mix prefix");
        String initName = getInitName(file.getName());
        if (!initName.equals(file.getName())) {
            return renameFile(file, file.getParent() + osPathSeparator + initName);
        } else {
            return file;
        }
    }

    private String getInitName(String nameWithOptionalPrefix) {
        String[] nameParts = nameWithOptionalPrefix.split("-", 2);
        if (nameParts.length > 1 && isMixPrefix(nameParts[0])) {
            System.out.println("Found mix prefix " + nameParts[0]);
            return nameParts[1];
        } else {
            System.out.println("Previous mix prefix is not found");
            return nameWithOptionalPrefix;
        }
    }

    private boolean isMixPrefix(String namePart) {
        try {
            return namePart.endsWith("m") && Integer.valueOf(namePart.substring(0, namePart.length() - 1)) >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private File renameFile(File oldNameFile, String newName) {
        File renamedFile = new File(newName);
        System.out.println(String.format("Renamed %s to %s", oldNameFile.getName(), renamedFile.getName()));
        return renamedFile;
    }

    private String getNewMixedName(File oldFile) {
        return oldFile.getParent() + osPathSeparator + getRandomNumber() + "m-" + oldFile.getName();
    }

    private int getRandomNumber() {
        return new BigDecimal(Math.random())
                .setScale(3, RoundingMode.HALF_EVEN)
                .multiply(new BigDecimal(1000))
                .intValue();
    }
}
