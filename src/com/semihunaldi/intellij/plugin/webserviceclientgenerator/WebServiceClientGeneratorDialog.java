package com.semihunaldi.intellij.plugin.webserviceclientgenerator;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Created by semih on 26.07.2017.
 */
public class WebServiceClientGeneratorDialog extends DialogWrapper
{
    protected WebServiceClientGeneratorDialog(Project project)
    {
        super(project);
        this.project = project;
        init();
        setTitle("Web Service Client Generator");
    }

    private Project project;

    private WSForm wsForm = new WSForm();

    @Nullable
    @Override
    protected JComponent createCenterPanel()
    {
        adjustOkActionButton();
        return wsForm.getPanelj();
    }

    @Override
    protected void doOKAction()
    {
        String groupId = wsForm.getGroupIdField().getText();
        String artifactId = wsForm.getArtifactIdField().getText();
        String version = wsForm.getVersionField().getText();
        String wsdlPath = wsForm.getWsdlPathField().getText();
        String pathToGenerateClient = wsForm.getPathToGenerateClientField().getText();
        String options = "";
        if (wsForm.getOptionsField() != null && !wsForm.getOptionsField().getText().isEmpty())
        {
            options = wsForm.getOptionsField().getText();
        }
        generateClient(groupId, artifactId, version, wsdlPath, pathToGenerateClient, options);
    }

    private void generateClient(String groupId, String artifactId, String version, String wsdlPath, String pathToGenerateClient, String options)
    {
        WSGeneratorDTO wsGeneratorDTO = new WSGeneratorDTO(groupId, artifactId, version, wsdlPath, pathToGenerateClient, options);
        try
        {
            new WebServiceClientGeneratorService().generateClient(wsGeneratorDTO);
            Notification notification = new Notification("info","WS Client Generator","Success", NotificationType.INFORMATION);
            notification.notify(project);
        }
        catch (Exception e)
        {
            Notification notification = new Notification("error","WS Client Generator",e.getMessage(), NotificationType.ERROR);
            notification.notify(project);
        }
    }

    private void adjustOkActionButton()
    {
        myOKAction.setEnabled(false);
        addFocusListeners(wsForm.getGroupIdField());
        addFocusListeners(wsForm.getArtifactIdField());
        addFocusListeners(wsForm.getVersionField());
        addFocusListeners(wsForm.getWsdlPathField());
        addFocusListeners(wsForm.getPathToGenerateClientField());
    }

    private void addFocusListeners(JTextField jTextField)
    {
        jTextField.addFocusListener(new FocusListener()
        {
            @Override
            public void focusGained(FocusEvent e)
            {
            }

            @Override
            public void focusLost(FocusEvent e)
            {
                if (isValid())
                {
                    myOKAction.setEnabled(true);
                }
                else
                {
                    myOKAction.setEnabled(false);
                }
            }
        });
    }

    private boolean isValid()
    {
        if (wsForm.getGroupIdField().getText().isEmpty())
        {
            return false;
        }
        if (wsForm.getArtifactIdField().getText().isEmpty())
        {
            return false;
        }
        if (wsForm.getVersionField().getText().isEmpty())
        {
            return false;
        }
        if (wsForm.getWsdlPathField().getText().isEmpty())
        {
            return false;
        }
        if (wsForm.getPathToGenerateClientField().getText().isEmpty())
        {
            return false;
        }
        return true;
    }
}
