package com.semihunaldi.intellij.plugin.webserviceclientgenerator;

/**
 * Created by TTSUNALDI on 7/27/2017.
 */
public class WSGeneratorDTO
{
    private String groupId;
    private String artifactId;
    private String version;
    private String wsdlPath;
    private String pathToGenerateClient;
    private String options;

    public WSGeneratorDTO(String groupId, String artifactId, String version, String wsdlPath, String pathToGenerateClient, String options)
    {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
        this.wsdlPath = wsdlPath;
        this.pathToGenerateClient = pathToGenerateClient;
        this.options = options;
    }

    public String getGroupId()
    {
        return groupId;
    }

    public String getArtifactId()
    {
        return artifactId;
    }

    public String getVersion()
    {
        return version;
    }

    public String getWsdlPath()
    {
        return wsdlPath;
    }

    public String getPathToGenerateClient()
    {
        return pathToGenerateClient;
    }

    public String getOptions()
    {
        return options;
    }

}
