package com.xn.materialdesignpro1.itemtouchhelper;

import java.io.Serializable;

public class QQDataBean implements Serializable {
    private String time;
    private String userName;
    private int logo;
    private String lastMessage;
    private int pop;

    @Override
    public String toString() {
        return "QQDataBean{" +
                "time='" + time + '\'' +
                ", userName='" + userName + '\'' +
                ", logo=" + logo +
                ", lastMessage='" + lastMessage + '\'' +
                ", pop=" + pop +
                '}';
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public int getpop() {
        return pop;
    }

    public void setpop(int pop) {
        this.pop = pop;
    }

    public QQDataBean() {
    }

    public QQDataBean( int logo,String userName, String lastMessage, String time,int pop) {
        this.time = time;
        this.userName = userName;
        this.logo = logo;
        this.lastMessage = lastMessage;
        this.pop = pop;
    }
    public QQDataBean( int logo,String userName, String lastMessage, String time) {
        this.time = time;
        this.userName = userName;
        this.logo = logo;
        this.lastMessage = lastMessage;

    }
}
