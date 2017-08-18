package com.kism.iotsw1502.cachmecachyou;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class jombepage extends AppCompatActivity {
    String id;
    GameAdaptor adaptor;
    String nickName;
    ListView listView;
    Button startGame;
    Intent intent;
    boolean IsCheck = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_choice_game);
        setContentView(R.layout.activity_jombepage);

        intent = getIntent();
        id = intent.getStringExtra("id");
        listView = (ListView)findViewById(R.id.nicknameList);
        startGame = (Button)findViewById(R.id.start_game);

        adaptor = new GameAdaptor(getApplicationContext(), R.layout.game_member_list, nickName);
        listView.setAdapter(adaptor);

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





            }
        });

    }
}
