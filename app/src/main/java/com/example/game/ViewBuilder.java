package com.example.game;

import android.view.SurfaceView;
import android.view.View;

public interface ViewBuilder {

    public ViewBuilder setManager();

    //param = text size
    public ViewBuilder setPaint(int strSize);

    public ViewBuilder setTimer(int maxTime);
    public ViewBuilder setBackground();

    public SurfaceView build();




}
