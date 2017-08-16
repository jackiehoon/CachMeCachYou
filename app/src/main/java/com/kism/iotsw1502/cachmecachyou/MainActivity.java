package com.kism.iotsw1502.cachmecachyou;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {
    ImageButton click_btn;
    EditText input_nickname;
    String nickname = "";
    Button result;
    Button game_btn;
    BluetoothAdapter btAdapter;

    final int REQUEST_ENABLE_BT = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        click_btn = (ImageButton)findViewById(R.id.clickbtn);
        input_nickname = (EditText)findViewById(R.id.input_nick);
        result = (Button)findViewById(R.id.result);
        game_btn = (Button)findViewById(R.id.game_btn);
        input_nickname.setText("");
        btAdapter = BluetoothAdapter.getDefaultAdapter();

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, resultpage.class);
                startActivity(intent);
            }
        });
        game_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, gamingpage.class);
                startActivity(intent);
            }
        });

        click_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nickname = input_nickname.getText().toString();
                Intent intent = new Intent(MainActivity.this, choiceGame.class);
                intent.putExtra("nickname", nickname);
                startActivity(intent);
            }
        });

    }

}
