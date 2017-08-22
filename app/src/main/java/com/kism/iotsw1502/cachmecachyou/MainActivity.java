package com.kism.iotsw1502.cachmecachyou;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;

import android.graphics.Color;
import android.net.Uri;
import android.os.NetworkOnMainThreadException;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;


public class MainActivity extends AppCompatActivity {
    TextView textView2;
    Button click_btn;
    EditText input_nickname;
    String nickname = "";
    Button result;
    Button game_btn;
    BluetoothAdapter btAdapter;
    String check = "0";
    Button start_btn;

    String macAddr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start_btn = (Button)findViewById(R.id.start_btn);
        textView2 = (TextView)findViewById(R.id.textView2);
        click_btn = (Button)findViewById(R.id.clickbtn2);
        input_nickname = (EditText)findViewById(R.id.input_nick);


        input_nickname.setText("");
        btAdapter = BluetoothAdapter.getDefaultAdapter();

        StrictMode.ThreadPolicy pol = new StrictMode.ThreadPolicy.Builder().permitNetwork().build();
        StrictMode.setThreadPolicy(pol);



        click_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent = new Intent(MainActivity.this, bluetoothPage.class);
                    startActivityForResult(intent,1001);

                }
        });

        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nickname = input_nickname.getText().toString();

                String html;
                Log.i("mac >> ",macAddr);
                Log.i("nickname >> ",nickname);

                String url = "https://"+"catch-jackiehoon.c9users.io/enter/"+macAddr+"/"+nickname;
                Log.i("rr >> ",url);

                html = DownloadHtml(url);

                Log.i("zxa >> ",html);
                html = html.replace("[","").replace("]","");

                JSONObject json = null;
                String device_id="";
                try {
                    json = new JSONObject(html);
                    device_id=json.getString("id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(MainActivity.this, choiceGame.class);
                MyApplication app = (MyApplication) getApplication();
                app.setId(device_id);
                intent.putExtra("nickname", nickname);
                startActivity(intent);
                Log.v("idd", device_id);
            }
        });

    }

    public static String DownloadHtml(String addr) {
        StringBuilder html = new StringBuilder();
        try {
            URL url = new URL(addr);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            if (con != null) {
                con.setConnectTimeout(10000);
                con.setUseCaches(false);
                if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    for(;;){
                        String line = br.readLine();
                        if(line == null) break;
                        html.append(line + '\n');

                    }
                    br.close();
                }
                con.disconnect();
            }

        }catch(NetworkOnMainThreadException e){
            return "Error : 메인 스레드 네트워크 작업 에러 - "+e.getMessage();
        }catch (Exception e){
            return "Error : "+e.getMessage();
        }
        return html.toString();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1001){
            if(resultCode == RESULT_OK){
                check = data.getStringExtra("check");
                macAddr = data.getStringExtra("macAddr");

                if(check.equals("1")){
                    click_btn.setWidth(10);
                    click_btn.setHeight(10);
                    click_btn.setVisibility(View.INVISIBLE);
                    start_btn.setVisibility(View.VISIBLE);
                    textView2.setVisibility(View.INVISIBLE);
                }
            }
        }
    }
}
