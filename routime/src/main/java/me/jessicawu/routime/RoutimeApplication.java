package me.jessicawu.routime;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class RoutimeApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault("fonts/Ruda-Regular.ttf");
    }
}
