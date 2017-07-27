package com.semihunaldi.intellij.plugin.webserviceclientgenerator;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Created by semih on 26.07.2017.
 */
public class WebServiceClientGeneratorDialog extends DialogWrapper
{
    protected WebServiceClientGeneratorDialog(Project project)
    {
        super(project);
        init();
        setTitle("Web Service Client Generator");
    }

    private WSForm wsForm = new WSForm();

    @Nullable
    @Override
    protected JComponent createCenterPanel()
    {
        return wsForm.getPanelj();
    }

    @Override
    protected void doOKAction()
    {
        System.out.println(wsForm.getGroupIdField());
    }
}
