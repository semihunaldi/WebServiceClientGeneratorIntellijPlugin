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
                    File generatePath = new File(file.getParent().concat("/").concat(Util.getFileName(file)).concat("Client.jar"));
                    this.getPathToGenerateClientField().setText(generatePath.getAbsolutePath());
                }
            }
            else
            {
                System.out.println("file chooser closed by user");
            }
        });
    }

    public JTextField getGroupIdField()
    {
        return groupIdField;
    }

    public JTextField getArtifactIdField()
    {
        return artifactIdField;
    }

    public JTextField getWsdlPathField()
    {
        return wsdlPathField;
    }

    public JTextField getOptionsField()
    {
        return optionsField;
    }

    public JPanel getPanelj()
    {
        return panelj;
    }

    public JTextField getVersionField()
    {
        return versionField;
    }

    public JTextField getPathToGenerateClientField()
    {
        return pathToGenerateClientField;
    }

    public JButton getWSDLPathButton()
    {
        return WSDLPathButton;
    }
}
