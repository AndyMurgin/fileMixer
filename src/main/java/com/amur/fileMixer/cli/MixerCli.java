package com.amur.fileMixer.cli;

import com.amur.fileMixer.api.Mixer;
import com.amur.fileMixer.api.ParametersValidator;
import com.amur.fileMixer.validation.InputParametersValidator;
import com.amur.fileMixer.validation.ValidationResult;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.io.PrintStream;
import java.util.concurrent.Callable;

@Command(description = "Mixes files in the specified directory by adding mixing prefix to a file name",
        name = "fmix", mixinStandardHelpOptions = true, version = "fmix 1.0")
public class MixerCli implements Callable<Void> {

    @Option(names = {"-c", "--clean"}, description = "Delete previously added mixing prefix if present")
    private boolean cleanOldPrefix;

    @Parameters(index = "0", paramLabel = "DIRECTORY", description = "Full path to directory with files required to be mixed")
    private File filesDirectory;

    private final Mixer mixer;
    private final ParametersValidator validator;

    public MixerCli(Mixer mixer, ParametersValidator validator) {
        this.mixer = mixer;
        this.validator = validator;
    }

    @Override
    public Void call() {
        boolean validationPassed = validate();
        if (validationPassed) {
            System.out.println("@Amur Files Mixer - let's mess your files!");
            mixer.mix(filesDirectory, cleanOldPrefix);
            System.out.println("Done! Enjoy your new files order :)");
        }
        return null;
    }

    private boolean validate() {
        ValidationResult result = validator.validateFilesDirectory(filesDirectory);
        if (result.getMessages().size() != 0) {
            PrintStream printStream = result.isSucceed() ? System.out : System.err;
            printStream.println(String.join("\n", result.getMessages()));
        }
        return result.isSucceed();
    }

    boolean isCleanOldPrefix() {
        return cleanOldPrefix;
    }

    File getFilesDirectory() {
        return filesDirectory;
    }
}
