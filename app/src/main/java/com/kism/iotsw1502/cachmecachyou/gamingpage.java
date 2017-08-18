package com.kism.iotsw1502.cachmecachyou;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class gamingpage extends AppCompatActivity {
    String id; //db에서 받아온 디바이스 아이디
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_choice_game);
        setContentView(R.layout.activity_gamingpage);

        intent = getIntent();
        id = intent.getStringExtra("id");

    }
}
