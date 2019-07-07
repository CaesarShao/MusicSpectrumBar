package com.caesar.musicspectrumbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.caesar.musicspectrumbarlibrary.MusicSpectrumBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
    }

    private void initview() {
        final MusicSpectrumBar mb1 = findViewById(R.id.mb_type1);
        final TextView tv_show = findViewById(R.id.tv_show);
        final TextView tv_touch = findViewById(R.id.tv_touch);
        findViewById(R.id.btn_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mb1.setCurrent(25);
            }
        });
        findViewById(R.id.btn_two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mb1.setCurrent(60);
            }
        });
        ;
        mb1.setOnSeekBarChangeListener(new MusicSpectrumBar.OnSeekChangeListener() {

            @Override
            public void onProgressChanged(int i, boolean b) {
                tv_show.setText("当前进度 : " + i);
            }

            @Override
            public void onStartTrackingTouch() {

            }

            @Override
            public void onStopTrackingTouch(int i) {
                tv_touch.setText("手指放开进度 : " + i);
            }
        });

    }
}
