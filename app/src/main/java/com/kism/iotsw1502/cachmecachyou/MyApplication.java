package com.kism.iotsw1502.cachmecachyou;

import android.app.Application;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

/**
 * Created by pc-15 on 2017-08-17.
 */
public class MyApplication extends Application {
    private MyBluetooth myBluetooth;
    private Socket mSocket;
    private String id;


    {
        try {
            mSocket = IO.socket("https://catch-jackiehoon.c9users.io");
            mSocket.connect();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
       this.id = id;
    }

    public Socket getSocket() {
        return mSocket;
    }

    public MyBluetooth getMyBluetooth() {
        return myBluetooth;
    }

    public void setMyBluetooth(MyBluetooth myBluetooth) {
        this.myBluetooth = myBluetooth;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
