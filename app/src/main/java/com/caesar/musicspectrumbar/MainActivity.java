package com.caesar.musicspectrumbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        MusicSpectrumBar mb1 = findViewById(R.id.mb_type1);
        TextView tv_show = findViewById(R.id.tv_show);
        Button btnOne = findViewById(R.id.btn_one);
        Button btnTwo = findViewById(R.id.btn_two);
        mb1.setOnSeekBarChangeListener(new MusicSpectrumBar.OnSeekChangeListener() {
            @Override
            public void onProgressChanged(int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch() {

            }

            @Override
            public void onStopTrackingTouch(int i) {

            }
        });

    }
}
