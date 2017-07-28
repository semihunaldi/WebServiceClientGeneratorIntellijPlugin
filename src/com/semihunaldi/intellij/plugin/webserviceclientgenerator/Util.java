package com.semihunaldi.intellij.plugin.webserviceclientgenerator;

import java.io.File;

/**
 * Created by TTSUNALDI on 7/27/2017.
 */
public class Util
{
    public enum OS
    {
        WINDOWS, LINUX, MAC, SOLARIS
    }

    private static OS os = null;

    public static OS getOS()
    {
        if (os == null)
        {
            String operSys = System.getProperty("os.name").toLowerCase();
            if (operSys.contains("win"))
            {
                os = OS.WINDOWS;
            }
            else if (operSys.contains("nix") || operSys.contains("nux")
                    || operSys.contains("aix"))
            {
                os = OS.LINUX;
            }
            else if (operSys.contains("mac"))
            {
                os = OS.MAC;
            }
            else if (operSys.contains("sunos"))
            {
                os = OS.SOLARIS;
            }
        }
        return os;
    }

    public static String getFileName(File file)
    {
        return file.getName().substring(0,file.getName().lastIndexOf("."));
    }
}