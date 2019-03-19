package com.amur.fileMixer;

import com.amur.fileMixer.core.Mixer;

public class Main {

    public static void main(String[] args) {
        System.out.println("@Amur Files Mixer - let's mess your files!");
        Mixer.run(args[0]);
        System.out.println("Done! Enjoy your new files order :)");
    }
}
