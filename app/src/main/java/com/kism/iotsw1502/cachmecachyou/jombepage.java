package com.kism.iotsw1502.cachmecachyou;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class jombepage extends AppCompatActivity {
    String id;
    GameAdaptor adaptor;
    ListView listView;
    Button startGame;
    Intent intent;
    ArrayList<memberVO>list = new ArrayList<>();
    private Socket mSocket;
    String room_id="";
    int IsCheck = 0;
    String html;
    LinearLayout bg;

    @Override
    public void onBackPressed() {
        String url = "https://"+"catch-jackiehoon.c9users.io/zombie/exit/"+id+"/"+room_id;
        MainActivity.DownloadHtml(url);
        mSocket.emit("ready_list", "{\"room_id\":"+room_id+"}");
        super.onBackPressed();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_choice_game);
        setContentView(R.layout.activity_jombepage);



        MyApplication app = (MyApplication) getApplication();
        mSocket = app.getSocket();

        mSocket.on("readyList", onReadyList);


        intent = getIntent();
        id = app.getId();
        listView = (ListView)findViewById(R.id.nicknameList);
        startGame = (Button)findViewById(R.id.start_game);
        bg = (LinearLayout)findViewById(R.id.bg);

        String url1 = "https://"+"catch-jackiehoon.c9users.io/zombie/enter/"+id;
        html = MainActivity.DownloadHtml(url1);

        JSONObject json = null;

        try {
            json = new JSONObject(html);
            room_id=json.getString("room_id");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mSocket.emit("ready_list", "{\"room_id\":"+room_id+"}");



        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IsCheck==0){
                    IsCheck=1;
                    bg.setBackgroundColor(Color.rgb(0,255,21));
                    startGame.setTextColor(Color.rgb(213,213,213));

                }else if(IsCheck==1){
                    IsCheck=0;
                    bg.setBackgroundColor(Color.rgb(247,247,247));
                }
                String url = "https://"+"catch-jackiehoon.c9users.io/zombie/ready/"+IsCheck+"/"+room_id+"/"+id;


                html = MainActivity.DownloadHtml(url);
                mSocket.emit("ready_list", "{\"room_id\":"+room_id+"}");
                Log.v("url",url);

            }
        });
        adaptor = new GameAdaptor(getApplicationContext(), R.layout.game_member_list, list);

    }

    private Emitter.Listener onReadyList = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {

            JSONObject data = (JSONObject) args[0];
            String isStart, userList;

            try {
                isStart = data.getString("is_start");
                Log.v("is",isStart);
                if(isStart.equals("1")){
                    Intent intent = new Intent(jombepage.this, gamingpage.class);
                    startActivity(intent);
                }
                userList = data.getString("userList");
                Log.v("iss",userList);
                JSONArray ja = new JSONArray(userList);
                list.clear();
                for(int i = 0; i<ja.length(); i++){
                    JSONObject jo = (JSONObject)ja.get(i);
                    Log.v("nic",jo.getString("nickname"));
                    Log.v("red",String.valueOf(jo.getInt("is_ready")));
                    list.add(new memberVO(jo.getString("nickname"), jo.getInt("is_ready")));
                }




                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable(){
                            @Override
                            public void run() {
                                listView.setAdapter(adaptor);
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
