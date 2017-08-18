package com.kism.iotsw1502.cachmecachyou;

import android.app.Application;

/**
 * Created by pc-15 on 2017-08-17.
 */
public class MyApplication extends Application {
    private MyBluetooth myBluetooth;


    public MyBluetooth getMyBluetooth() {
        return myBluetooth;
    }

    public void setMyBluetooth(MyBluetooth myBluetooth) {
        this.myBluetooth = myBluetooth;
    }
}
