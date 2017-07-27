package com.semihunaldi.intellij.plugin.webserviceclientgenerator;

import java.io.File;

/**
 * Created by TTSUNALDI on 7/27/2017.
 */
public class WebServiceClientGeneratorService
{
    public void generateClient(WSGeneratorDTO wsGeneratorDTO)
    {
        try
        {
            File jarFile = new File(wsGeneratorDTO.getPathToGenerateClient());
            File file = new File(wsGeneratorDTO.getWsdlPath());
            Runtime.getRuntime().exec("native2ascii " + file.getAbsolutePath() + " " + file.getAbsolutePath().concat("_NEW"));
            File newWSDL = new File(file.getAbsolutePath().concat("_NEW"));
//            Runtime.getRuntime().exec("wsimport -clientjar" + jarFile.getAbsolutePath() + " " + newWSDL.getAbsolutePath() + " -keep -XadditionalHeaders -extension -B-XautoNameResolution");
            Runtime.getRuntime().exec("wsimport " + newWSDL.getAbsolutePath() + "-d " + jarFile.getParent() + " -clientjar " +jarFile.getAbsolutePath() + " -keep -XadditionalHeaders -B-XautoNameResolution");
            Runtime.getRuntime().exec("mvn install:install-file -DgroupId=" + wsGeneratorDTO.getGroupId() + " -DartifactId=" +wsGeneratorDTO.getArtifactId() + " -Dversion=" + wsGeneratorDTO.getVersion() + " -Dpackaging=jar -Dfile=" + jarFile.getAbsolutePath() + " -DgeneratePom=true");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
