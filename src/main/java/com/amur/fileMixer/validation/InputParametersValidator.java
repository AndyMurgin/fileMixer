package com.amur.fileMixer.validation;

import java.io.File;
import java.io.IOException;

public class InputParametersValidator {

    public static ValidationResult validateFilesDirectory(File directory) {
        String fileCanonicalPath;
        try {
            fileCanonicalPath = directory.getCanonicalPath();
        } catch (IOException e) {
            return new ValidationResult(false, e.getMessage());
        }
        if (!directory.exists()) {
            return new ValidationResult(false, String.format("Directory %s doesn't exist", fileCanonicalPath));
        }
        if (!directory.canWrite()) {
            return new ValidationResult(false, String.format("Unable to update files in directory %s " +
                            "- Permission Denied", fileCanonicalPath));
        }
        if (!directory.isDirectory()) {
            return new ValidationResult(false, String.format("%s is not a directory", fileCanonicalPath));
        }
        return new ValidationResult(true);
    }
}
