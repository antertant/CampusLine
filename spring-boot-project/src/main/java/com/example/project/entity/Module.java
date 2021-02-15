package com.example.project.entity;

import java.util.List;

public class Module {
    private String module_name;
    private String module_intro;
    private int max_adminNumber;
    private List<String> admins;

    public String getModule_name() {
        return module_name;
    }

    public void setModule_name(String module_name) {
        this.module_name = module_name;
    }

    public String getModule_intro() {
        return module_intro;
    }

    public void setModule_intro(String module_intro) {
        this.module_intro = module_intro;
    }

    public int getMax_adminNumber() {
        return max_adminNumber;
    }

    public void setMax_adminNumber(int max_adminNumber) {
        this.max_adminNumber = max_adminNumber;
    }

    public List<String> getAdmins() {
        return admins;
    }

    public void setAdmins(List<String> admins) {
        this.admins = admins;
    }
}
