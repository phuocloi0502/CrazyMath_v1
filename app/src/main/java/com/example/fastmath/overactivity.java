package com.example.fastmath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class overactivity extends AppCompatActivity {
    TextView txvmyScrore;
    Button btn_Try, btn_Home;
    String myCore,mybest;
    SoundPool mysounds;
    public int clicksound;
    public MediaPlayer overl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overactivity);
        txvmyScrore = findViewById(R.id.txvmyscore);
        btn_Home = findViewById(R.id.btnhome);
        btn_Try = findViewById(R.id.btntryagain);
        mysounds = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        clicksound = mysounds.load(getApplicationContext(), R.raw.click_2, 1);
        // lay diem
        Intent getScrore = this.getIntent();
        myCore = getScrore.getStringExtra("d");
        mybest = getScrore.getStringExtra("dcao");
        //hien diem
        txvmyScrore.setText(myCore);
        btn_Try.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mysounds.play(clicksound, 1.0f, 1.0f, 1, 0, 1);
                Intent intent=new Intent(overactivity.this,playactivity.class);
                startActivity(intent);
                overactivity.this.finish();
            }
        });
        btn_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mysounds.play(clicksound, 1.0f, 1.0f, 1, 0, 1);
                Intent intent=new Intent(overactivity.this,MainActivity.class);
                intent.putExtra("BestScore",mybest);
                startActivity(intent);
                overactivity.this.finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        soundHome();
    }

    @Override
    protected void onPause() {
        super.onPause();
        overl.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        overl.pause();
    }

    public void soundHome() {
        overl = MediaPlayer.create(overactivity.this, R.raw.wrong2);
        overl.setVolume(1.0f, 1.0f); //set volume takes two paramater
        overl.setLooping(false);
        overl.start();
    }
}
