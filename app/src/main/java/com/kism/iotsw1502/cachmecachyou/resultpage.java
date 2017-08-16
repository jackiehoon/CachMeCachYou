package com.kism.iotsw1502.cachmecachyou;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class resultpage extends AppCompatActivity {
    ImageView result;
    Button move_main;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_choice_game);
        setContentView(R.layout.activity_resultpage);

        result = (ImageView)findViewById(R.id.result_img);
        move_main = (Button)findViewById(R.id.move_main);


        move_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(resultpage.this, MainActivity.class);
                finish();
            }
        });

    }
}
