package com.semihunaldi.intellij.plugin.webserviceclientgenerator;

import javax.swing.*;
import java.io.File;

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
    private JButton WSDLPathButton;

    WSForm()
    {
        prepareFileChooser();
    }

    private void prepareFileChooser()
    {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setDialogTitle("Choose WSDL");
        this.getWSDLPathButton().addActionListener(e ->
        {

            int returnVal = jFileChooser.showOpenDialog(this.getPanelj());
            if (returnVal == JFileChooser.APPROVE_OPTION)
            {
                File file = jFileChooser.getSelectedFile();
                if(file != null)
                {
                    this.getWsdlPathField().setText(file.getAbsolutePath());
                    File generatePath = new File(file.getParent().concat("/").concat(getFileName(file)).concat("Client.jar"));
                    this.getPathToGenerateClientField().setText(generatePath.getAbsolutePath());
                }
            }
            else
            {
                System.out.println("file chooser closed by user");
            }
        });
    }

    private String getFileName(File file)
    {
        return file.getName().substring(0,file.getName().lastIndexOf("."));
    }

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

    public JButton getWSDLPathButton()
    {
        return WSDLPathButton;
    }

    public void setWSDLPathButton(JButton WSDLPathButton)
    {
        this.WSDLPathButton = WSDLPathButton;
    }
}
