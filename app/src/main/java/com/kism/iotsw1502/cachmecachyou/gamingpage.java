package com.kism.iotsw1502.cachmecachyou;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class gamingpage extends AppCompatActivity {

    private Socket mSocket;

    MyApplication myp = new MyApplication();
    String id;
    ArrayList<memberVO> list = new ArrayList<memberVO>();
    ArrayList<memberVO> peolist = new ArrayList<memberVO>();
    ArrayList<memberVO> zomList = new ArrayList<memberVO>();
    ListView peoListView;
    ListView zomListView;
    peoAdaptor peoAdaptor;
    zomAdaptor zomAdaptor;
    TextView timer;
    Thread thread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_choice_game);
        setContentView(R.layout.activity_gamingpage);

        MyApplication app = (MyApplication) getApplication();
        mSocket = app.getSocket();

        mSocket.on("zombieList", onZombieList);

        id = myp.getId();

        timer = (TextView) findViewById(R.id.timer);
        peoListView = (ListView) findViewById(R.id.peoList);
        zomListView = (ListView) findViewById(R.id.zomList);

        peoAdaptor = new peoAdaptor(getApplicationContext(), R.layout.peoplelist, peolist);
        zomAdaptor = new zomAdaptor(getApplicationContext(), R.layout.jombeelist, zomList);

        thread = new Thread(new timeThread());
    }

    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int time = msg.arg1;
            int min = time/60;
            int sec = time%60;

            timer.setText((min<10?"0"+min:min)+":"+(sec<10?"0"+sec:sec));
        }
    };

    class timeThread implements Runnable {
        int num;

        @Override
        public void run() {
            int cnt = 7;
            while (true) {
                cnt--;
                Message msg = new Message();
                msg.arg1 = cnt;
                handler.sendMessage(msg);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private Emitter.Listener onZombieList = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {

            JSONObject data = (JSONObject) args[0];
            String catcher;

            try {

                catcher = data.getString("is_catcher");
                Log.v("iss", catcher);
                JSONArray ja = new JSONArray(catcher);
                list.clear();
                for (int i = 0; i < ja.length(); i++) {
                    JSONObject jo = (JSONObject) ja.get(i);
                    Log.v("nic", jo.getString("nickname"));
                    Log.v("red", jo.getString("is_catcher"));
                    list.add(new memberVO(jo.getString("nickname"), id, jo.getString("is_catcher")));
                }
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getCatcher().equals("0")) {
                        peolist.add(list.get(i));
                    } else if (list.get(i).getCatcher().equals("1")) {
                        zomList.add(list.get(i));
                    }
                }


                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                peoListView.setAdapter(peoAdaptor);
                                zomListView.setAdapter(zomAdaptor);
                            }
                        });
                    }
                }).start();


            } catch (JSONException e) {
                return;
            }


        }
    };
}
