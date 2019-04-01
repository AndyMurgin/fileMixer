package com.amur.fileMixer.cli;

import com.amur.fileMixer.api.Mixer;
import com.amur.fileMixer.api.ParametersValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class MixerCliTest {

    private static ParametersValidator validator;
    private static Mixer mixer;

    @BeforeEach
    void init() {
        validator = Mockito.mock(ParametersValidator.class);
        mixer = Mockito.mock(Mixer.class);
    }

    @Test
    public void commandLineInitWithoutDirectoryTest() {
        assertThrows(CommandLine.MissingParameterException.class,
                () -> CommandLine.populateCommand(new MixerCli(mixer, validator)));
    }

    @Test
    public void commandLineInitCorrectTest() {
        MixerCli commandLine = CommandLine.populateCommand(new MixerCli(mixer, validator),
                "./../resources/mixer-test");
        assertNotNull(commandLine);
        File directory = commandLine.getFilesDirectory();
        assertNotNull(directory);
        try {
            System.out.println("Testing directory is " + directory.getCanonicalPath());
        } catch (IOException e) {
            fail("Unable to access test directory");
        }
        assertEquals(directory.getName(), "mixer-test");
    }
}
