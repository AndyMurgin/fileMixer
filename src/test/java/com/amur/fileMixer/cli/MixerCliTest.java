package com.amur.fileMixer.cli;

import com.amur.fileMixer.api.Mixer;
import com.amur.fileMixer.api.ParametersValidator;
import com.amur.fileMixer.core.MixUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mockito;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class MixerCliTest {

    @TempDir
    public File directory;

    private static ParametersValidator validator;
    private static Mixer mixer;

    @BeforeEach
    public void init() throws IOException {
        validator = Mockito.mock(ParametersValidator.class);
        mixer = Mockito.mock(Mixer.class);
        populateDirectory();
    }

    private void populateDirectory() throws IOException {
        File file = new File(directory.getCanonicalPath() + MixUtils.OS_PATH_SEPARATOR + "test.txt");
        if (!file.createNewFile()) {
            throw new RuntimeException("Unable to initialize tests - broken temp directory");
        }
    }

    @Test
    public void commandLineInitWithoutDirectoryTest() {
        assertThrows(CommandLine.MissingParameterException.class,
                () -> CommandLine.populateCommand(new MixerCli(mixer, validator)));
    }

    @Test
    public void commandLineInitCorrectTest() {
        try {
            MixerCli commandLine = CommandLine.populateCommand(new MixerCli(mixer, validator),
                    "--clean", directory.getCanonicalPath());
            assertNotNull(commandLine);
            assertTrue(commandLine.isCleanOldPrefix());
            File commandLineFilesDirectory = commandLine.getFilesDirectory();
            assertNotNull(commandLineFilesDirectory);

            System.out.println("Testing directory is " + commandLineFilesDirectory.getCanonicalPath());
            assertEquals(commandLineFilesDirectory.getName(), directory.getName());
            assertEquals(commandLineFilesDirectory.listFiles().length, directory.listFiles().length);
            assertEquals(commandLineFilesDirectory.listFiles()[0].getName(), directory.listFiles()[0].getName());
        } catch (IOException e) {
            fail("Unable to access test directory");
        }
    }
}
