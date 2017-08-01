package com.semihunaldi.intellij.plugin.webserviceclientgenerator;

import javax.swing.*;

public class ErrorDialog extends JDialog
{
    private JPanel contentPane;
    private JTextArea textArea;

    public ErrorDialog(String text)
    {
        setContentPane(contentPane);
        setModal(true);
        prepareTextArea(text);
    }

    private void prepareTextArea(String text)
    {
        textArea.setEditable(false);
        textArea.setText(text);
        textArea.setVisible(true);
        textArea.update(textArea.getGraphics());
    }

    @Override
    public JPanel getContentPane()
    {
        return contentPane;
    }
}
