package com.lan.bean;

import java.util.List;

public class Lan {

    private String serverName;

    private List<Yang> serverInfo;

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public List<Yang> getServerInfo() {
        return serverInfo;
    }

    public void setServerInfo(List<Yang> serverInfo) {
        this.serverInfo = serverInfo;
    }
}
