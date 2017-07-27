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

    public void setGroupId(String groupId)
    {
        this.groupId = groupId;
    }

    public String getArtifactId()
    {
        return artifactId;
    }

    public void setArtifactId(String artifactId)
    {
        this.artifactId = artifactId;
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }

    public String getWsdlPath()
    {
        return wsdlPath;
    }

    public void setWsdlPath(String wsdlPath)
    {
        this.wsdlPath = wsdlPath;
    }

    public String getPathToGenerateClient()
    {
        return pathToGenerateClient;
    }

    public void setPathToGenerateClient(String pathToGenerateClient)
    {
        this.pathToGenerateClient = pathToGenerateClient;
    }

    public String getOptions()
    {
        return options;
    }

    public void setOptions(String options)
    {
        this.options = options;
    }
}
