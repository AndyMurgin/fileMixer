package com.amur.fileMixer.api;

import java.io.File;

public interface Mixer {
    void mix(File filesDirectory, boolean cleanOldPrefix);
}
