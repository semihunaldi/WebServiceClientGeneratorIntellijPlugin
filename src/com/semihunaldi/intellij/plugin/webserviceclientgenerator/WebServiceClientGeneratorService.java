package com.semihunaldi.intellij.plugin.webserviceclientgenerator;


import java.io.File;
import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Created by TTSUNALDI on 7/27/2017.
 */
public class WebServiceClientGeneratorService
{
    public void generateClient(WSGeneratorDTO wsGeneratorDTO)
    {
        try
        {
            String mavenHome = System.getenv("M2_HOME");
            File mvnExecutable = null;
            if (mavenHome == null || mavenHome.isEmpty())
            {
                throw new RuntimeException("Error : set M2_HOME as maven environment variable");
            }
            else
            {
                switch (Util.getOS())
                {
                    case MAC:
                        mvnExecutable = new File(mavenHome.concat("bin/mvn"));
                        break;
                    case LINUX:
                        mvnExecutable = new File(mavenHome.concat("bin/mvn"));
                        break;
                    case WINDOWS:
                        mvnExecutable = new File(mavenHome.concat("bin/mvn.bat"));
                        break;
                    default:
                        mvnExecutable = new File(mavenHome.concat("bin/mvn.bat"));
                        break;
                }
            }
            File jarFile = new File(wsGeneratorDTO.getPathToGenerateClient());
            File wsdl = new File(wsGeneratorDTO.getWsdlPath());
            File newWsdl = new File(wsdl.getParent().concat("/NEW_"+wsdl.getName()));
            Process native2ascii = Runtime.getRuntime().exec("native2ascii " + wsdl.getAbsolutePath() + " " + newWsdl.getAbsolutePath());
            native2ascii.waitFor(1500, TimeUnit.MILLISECONDS);
            native2ascii.destroy();
            if(native2ascii.exitValue() != 0)
            {
                String s = getErrorStreamString(native2ascii.getErrorStream());
                s = s.concat(getErrorStreamString(native2ascii.getInputStream()));
                throw new RuntimeException(s);
            }
            String command = "wsimport " + newWsdl.getAbsolutePath() + " -d " + jarFile.getParent() + " -clientjar " + jarFile.getAbsolutePath() + " -keep -XadditionalHeaders -B-XautoNameResolution";
            if(wsGeneratorDTO.getOptions() != null && !wsGeneratorDTO.getOptions().isEmpty())
            {
                command = command.concat(wsGeneratorDTO.getOptions());
            }
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
            System.out.println("wsimport exit value : " + process.exitValue());
            if(process.exitValue() != 0)
            {
                throw new RuntimeException(getErrorStreamString(process.getErrorStream()));
            }
            else
            {
                String mvnCommand = mvnExecutable.getAbsolutePath() + " install:install-file -DgroupId=" + wsGeneratorDTO.getGroupId() + " -DartifactId=" + wsGeneratorDTO.getArtifactId() + " -Dversion=" + wsGeneratorDTO.getVersion() + " -Dpackaging=jar -Dfile=" + jarFile.getAbsolutePath() + " -DgeneratePom=true";
                Process mvn = Runtime.getRuntime().exec(mvnCommand);
                mvn.waitFor();
                System.out.println("mvn-install exit value : " + mvn.exitValue());
                if(process.exitValue() != 0)
                {
                    throw new RuntimeException(getErrorStreamString(mvn.getErrorStream()));
                }
                System.out.println("Finished");
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException("Unexpected Error Occured",e);
        }
    }

    private String getErrorStreamString(InputStream errorStream)
    {
        if (errorStream != null)
        {
            Scanner s = new Scanner(errorStream).useDelimiter("\\A");
            return s.hasNext() ? s.next() : "";
        }
        return "";
    }
}
