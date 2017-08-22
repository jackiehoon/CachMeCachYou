package com.kism.iotsw1502.cachmecachyou;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class choiceGame extends AppCompatActivity {
    TextView tv;
    Animation translate;
    Button jombe;
    Intent intent;
    String nickname;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_choice_game);
        intent = getIntent();
        nickname = intent.getStringExtra("nickname");
        tv = (TextView)findViewById(R.id.choice_game_palse);
        jombe = (Button)findViewById(R.id.jombeepage_move);

        tv.setText(nickname+"님 게임을 선택해주세요");

        translate = AnimationUtils.loadAnimation(this, R.anim.translate);
        tv.startAnimation(translate);

        jombe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(choiceGame.this, jombepage.class);
                startActivity(intent);
            }
        });



    }

}
