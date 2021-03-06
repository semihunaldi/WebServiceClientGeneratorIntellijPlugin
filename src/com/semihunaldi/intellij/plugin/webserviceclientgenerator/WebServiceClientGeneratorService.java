package com.semihunaldi.intellij.plugin.webserviceclientgenerator;


import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
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
            File mvnExecutable = getMavenHomeDir();
            File jarFile = new File(wsGeneratorDTO.getPathToGenerateClient());
            File wsdl = getWsdlFile(wsGeneratorDTO);
            File newWsdl = new File(wsdl.getParent().concat("/NEW_"+wsdl.getName()));
            convertWsdlToAscii(wsdl, newWsdl);
            runWsImport(wsGeneratorDTO, mvnExecutable, jarFile, newWsdl);
        }
        catch (Exception e)
        {
            throw new RuntimeException("Unexpected Error Occured, " + e.getMessage(),e);
        }
    }

    private void runWsImport(WSGeneratorDTO wsGeneratorDTO, File mvnExecutable, File jarFile, File newWsdl) throws IOException, InterruptedException
    {
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
            runMavenInstallOnGeneratedClient(wsGeneratorDTO, mvnExecutable, jarFile, process);
            System.out.println("Finished");
        }
    }

    private void runMavenInstallOnGeneratedClient(WSGeneratorDTO wsGeneratorDTO, File mvnExecutable, File jarFile, Process process) throws IOException, InterruptedException
    {
        String mvnCommand = mvnExecutable.getAbsolutePath() + " install:install-file -DgroupId=" + wsGeneratorDTO.getGroupId() + " -DartifactId=" + wsGeneratorDTO.getArtifactId() + " -Dversion=" + wsGeneratorDTO.getVersion() + " -Dpackaging=jar -Dfile=" + jarFile.getAbsolutePath() + " -DgeneratePom=true";
        Process mvn = Runtime.getRuntime().exec(mvnCommand);
        mvn.waitFor();
        System.out.println("mvn-install exit value : " + mvn.exitValue());
        if(process.exitValue() != 0)
        {
            throw new RuntimeException(getErrorStreamString(mvn.getErrorStream()));
        }
    }

    private void convertWsdlToAscii(File wsdl, File newWsdl) throws IOException, InterruptedException
    {
        Process native2ascii = Runtime.getRuntime().exec("native2ascii " + wsdl.getAbsolutePath() + " " + newWsdl.getAbsolutePath());
        native2ascii.waitFor(1500, TimeUnit.MILLISECONDS);
        native2ascii.destroy();
        if(native2ascii.exitValue() != 0)
        {
            String s = getErrorStreamString(native2ascii.getErrorStream());
            s = s.concat(getErrorStreamString(native2ascii.getInputStream()));
            throw new RuntimeException(s);
        }
    }

    @NotNull
    private File getWsdlFile(WSGeneratorDTO wsGeneratorDTO)
    {
        File wsdl = new File(wsGeneratorDTO.getWsdlPath());
        if(!wsdl.isFile())
        {
            throw new RuntimeException("Selected wsdl is not a file !");
        }
        return wsdl;
    }

    private File getMavenHomeDir()
    {
        File mvnExecutable = null;
        String mavenHome = System.getenv("M2_HOME");
        if (mavenHome == null || mavenHome.isEmpty())
        {
            throw new RuntimeException("Error : set M2_HOME as maven environment variable");
        }
        else
        {
            File executable = new File(mavenHome.concat("/bin/mvn"));
            File batExecutable = new File(mavenHome.concat("/bin/mvn.bat"));
            File cmdExecutable = new File(mavenHome.concat("/bin/mvn.cmd"));
            switch (Util.getOS())
            {
                case MAC:
                    mvnExecutable = executable;
                    break;
                case LINUX:
                    mvnExecutable = executable;
                    break;
                case WINDOWS:
                    if (executable.exists())
                    {
                        mvnExecutable = executable;
                    }
                    else if(batExecutable.exists())
                    {
                        mvnExecutable = batExecutable;
                    }
                    else
                    {
                        mvnExecutable = cmdExecutable;
                    }
                    break;
                default:
                    if (executable.exists())
                    {
                        mvnExecutable = executable;
                    }
                    else if(batExecutable.exists())
                    {
                        mvnExecutable = batExecutable;
                    }
                    else
                    {
                        mvnExecutable = cmdExecutable;
                    }
                    break;
            }
        }
        return mvnExecutable;
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
