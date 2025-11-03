package com.org.insurance.ui.command;

public class LoadFromFileCommand implements Command {
    private String path;

    public LoadFromFileCommand() { }

    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }

    @Override public void execute() { }
    @Override public String getDescription() { return "Завантажити дані з файлу"; }
}
