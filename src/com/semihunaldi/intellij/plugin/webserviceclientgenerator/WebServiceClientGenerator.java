package com.semihunaldi.intellij.plugin.webserviceclientgenerator;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

/**
 * Created by semih on 26.07.2017.
 */
public class WebServiceClientGenerator extends AnAction
{

    @Override
    public void actionPerformed(AnActionEvent e)
    {
        WebServiceClientGeneratorDialog webServiceClientGeneratorDialog = new WebServiceClientGeneratorDialog(e.getProject());
        webServiceClientGeneratorDialog.createCenterPanel();
        webServiceClientGeneratorDialog.show();
    }
}
