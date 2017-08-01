package com.semihunaldi.intellij.plugin.webserviceclientgenerator;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class ErrorDialogUI extends DialogWrapper
{
    protected ErrorDialogUI(@Nullable Project project, String text)
    {
        super(project);
        this.project = project;
        this.text = text;
        init();
        setTitle("Message");
    }

    private String text;

    private Project project;

    @Nullable
    @Override
    protected JComponent createCenterPanel()
    {
        ErrorDialog errorDialog = new ErrorDialog(text);
        return errorDialog.getContentPane();
    }
}
