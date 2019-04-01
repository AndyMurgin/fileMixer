package com.amur.fileMixer.api;

import com.amur.fileMixer.validation.ValidationResult;

import java.io.File;

public interface ParametersValidator {
    ValidationResult validateFilesDirectory(File filesDirectory);
}
