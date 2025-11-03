package com.org.insurance.ui.command;

public class SaveToFileCommand implements Command {
    private String path;

    public SaveToFileCommand() { }

    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }

    @Override public void execute() { }
    @Override public String getDescription() { return "Зберегти дані у файл"; }
}
