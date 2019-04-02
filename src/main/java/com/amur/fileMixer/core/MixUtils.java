package com.amur.fileMixer.core;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.FileSystems;

public class MixUtils {

    public static final String OS_PATH_SEPARATOR = FileSystems.getDefault().getSeparator();

    public static void addPrefix(File file, boolean cleanMixPrefix) {
        System.out.println("Processing " + file.getName());
        if (cleanMixPrefix) {
            file = tryCleanMixPrefix(file);
        }
        renameFile(file, getNewMixedName(file));
    }

    private static File tryCleanMixPrefix(File file) {
        System.out.println("Trying to clean previously added mix prefix");
        String initName = getInitName(file.getName());
        if (!initName.equals(file.getName())) {
            return renameFile(file, file.getParent() + OS_PATH_SEPARATOR + initName);
        } else {
            return file;
        }
    }

    private static String getInitName(String nameWithOptionalPrefix) {
        String[] nameParts = nameWithOptionalPrefix.split("-", 2);
        if (nameParts.length > 1 && isMixPrefix(nameParts[0])) {
            System.out.println("Found mix prefix " + nameParts[0]);
            return nameParts[1];
        } else {
            System.out.println("Previous mix prefix is not found");
            return nameWithOptionalPrefix;
        }
    }

    private static boolean isMixPrefix(String namePart) {
        try {
            return namePart.endsWith("m") && Integer.valueOf(namePart.substring(0, namePart.length() - 1)) >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static File renameFile(File oldNameFile, String newName) {
        File renamedFile = new File(newName);
        System.out.println(String.format("Renamed %s to %s", oldNameFile.getName(), renamedFile.getName()));
        return renamedFile;
    }

    private static String getNewMixedName(File oldFile) {
        return oldFile.getParent() + OS_PATH_SEPARATOR + getRandomNumber() + "m-" + oldFile.getName();
    }

    private static int getRandomNumber() {
        return new BigDecimal(Math.random())
                .setScale(3, RoundingMode.HALF_EVEN)
                .multiply(new BigDecimal(1000))
                .intValue();
    }
}
