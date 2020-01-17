package com.example.game;

import android.app.Application;

public class Globals extends Application {
    private int theme = 0;

    private static int artifactsCollected = 0;

    private String userName;

    public int getThemeType(){
        return theme;
    }

    public void setThemeType(int type){
        theme = type;
    }

    public String getUsername() {return userName;
    }

    public void setUserName(String user) {this.userName = user;}

    public static void addArtifact(){artifactsCollected += 1;}

    public static void resetArtifact(){artifactsCollected = 0;}

    public static int getArtifacts(){return artifactsCollected;}

    public static void addArtifacts(int num) {artifactsCollected+= num;}

}
