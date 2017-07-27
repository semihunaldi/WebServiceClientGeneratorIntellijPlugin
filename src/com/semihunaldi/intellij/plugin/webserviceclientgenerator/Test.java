package com.semihunaldi.intellij.plugin.webserviceclientgenerator;

import java.io.IOException;

/**
 * Created by TTSUNALDI on 7/27/2017.
 */
public class Test
{
    public static void main(String[] args)
    {
        String command = "C:\\apache-maven-3.2.1\\bin\\mvn.bat install:install-file -DgroupId=com.semih.unaldi -DartifactId=hello -Dversion=1.0.0 -Dpackaging=jar -Dfile=C:\\Users\\TTSUNALDI\\Desktop\\testing\\CrmIPCentrexWSClient.jar -DgeneratePom=true";
        try
        {
            Runtime.getRuntime().exec(command).waitFor();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
