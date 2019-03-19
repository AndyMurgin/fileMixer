package com.amur.fileMixer.core;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Mixer {

    public static void run(String filesDirectory) {
        try {
            System.out.println("Getting files from directory " + filesDirectory);
            Files.newDirectoryStream(Paths.get(filesDirectory),
                    path -> path.toFile().isFile())
                    .forEach(Mixer::addPrefix);
        } catch (IOException e) {
            System.err.println("Unable to process files in directory " + filesDirectory + " : " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private static void addPrefix(Path path) {
        File file = path.toFile();
        File renamedFile = new File(getMixedName(file));
        file.renameTo(renamedFile);
        System.out.println(String.format("Renamed %s to %s", file.getName(), renamedFile.getName()));
    }

    //TODO add delete of the previous prefix
    private static String getMixedName(File oldFile) {
        return oldFile.getParent() + "/" + getRandomNumber() + "m-" + oldFile.getName();
    }

    private static int getRandomNumber() {
        return new BigDecimal(Math.random())
                .setScale(3, RoundingMode.HALF_EVEN)
                .multiply(new BigDecimal(1000))
                .intValue();
    }
}
