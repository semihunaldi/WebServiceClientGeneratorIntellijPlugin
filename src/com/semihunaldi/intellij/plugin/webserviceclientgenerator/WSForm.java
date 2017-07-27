package com.semihunaldi.intellij.plugin.webserviceclientgenerator;

import javax.swing.*;

/**
 * Created by TTSUNALDI on 7/27/2017.
 */
public class WSForm
{
    private JTextField groupIdField;
    private JTextField artifactIdField;
    private JTextField wsdlPathField;
    private JTextField optionsField;
    private JPanel panelj;
    private JTextField versionField;
    private JTextField pathToGenerateClientField;

    public JTextField getGroupIdField()
    {
        return groupIdField;
    }

    public void setGroupIdField(JTextField groupIdField)
    {
        this.groupIdField = groupIdField;
    }

    public JTextField getArtifactIdField()
    {
        return artifactIdField;
    }

    public void setArtifactIdField(JTextField artifactIdField)
    {
        this.artifactIdField = artifactIdField;
    }

    public JTextField getWsdlPathField()
    {
        return wsdlPathField;
    }

    public void setWsdlPathField(JTextField wsdlPathField)
    {
        this.wsdlPathField = wsdlPathField;
    }

    public JTextField getOptionsField()
    {
        return optionsField;
    }

    public void setOptionsField(JTextField optionsField)
    {
        this.optionsField = optionsField;
    }

    public JPanel getPanelj()
    {
        return panelj;
    }

    public void setPanelj(JPanel panelj)
    {
        this.panelj = panelj;
    }

    public JTextField getVersionField()
    {
        return versionField;
    }

    public void setVersionField(JTextField versionField)
    {
        this.versionField = versionField;
    }

    public JTextField getPathToGenerateClientField()
    {
        return pathToGenerateClientField;
    }

    public void setPathToGenerateClientField(JTextField pathToGenerateClientField)
    {
        this.pathToGenerateClientField = pathToGenerateClientField;
    }
}
