package com.caesar.musicspectrumbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

        int[] highD = {1, 2, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 6, 7};
        String[] ColorStr = {"#0050dc", "#0650dc", "#0b51dd", "#1151dd", "#1951de", "#2052de", "#2852df", "#3153df", "#3a53e0", "#4454e0", "#4e54e1", "#5855e2", "#6255e2", "#6d56e3", "#7756e3", "#8257e4", "#8c58e5", "#9758e5", "#a159e6", "#ab59e7", "#b45ae7", "#be5ae8", "#c65be8", "#ce5be9", "#d65ce9", "#dd5cea", "#e45cea", "#e95deb", "#ee5deb", "#ce5be9", "#d65ce9", "#dd5cea", "#e45cea", "#e95deb", "#ee5deb", "#0050dc", "#0650dc", "#0b51dd", "#1151dd", "#1951de", "#2052de", "#2852df"};
        MusicSpectrumBar mb5 = findViewById(R.id.mb_type5);
        mb5.setDatas(highD, ColorStr);


        int[] highD2 = {1, 2, 3, 4, 5, 6, 7, 6, 5, 4, 3, 2, 1, 2, 3, 4, 5, 6, 7, 6, 5, 4, 3, 2, 1, 2, 3, 4, 5, 6, 7, 6, 5, 4, 3, 2, 1, 2, 3, 4, 5, 6};
        String[] ColorStr2 = {"#0050dc", "#0650dc", "#0b51dd", "#1151dd", "#1951de", "#2052de", "#2852df", "#3153df", "#3a53e0", "#4454e0", "#4e54e1", "#5855e2", "#6255e2", "#6d56e3", "#7756e3", "#8257e4", "#8c58e5", "#9758e5", "#a159e6", "#ab59e7", "#b45ae7", "#be5ae8", "#c65be8", "#ce5be9", "#d65ce9", "#dd5cea", "#e45cea", "#e95deb", "#ee5deb", "#ce5be9", "#d65ce9", "#dd5cea", "#e45cea", "#e95deb", "#ee5deb", "#0050dc", "#0650dc", "#0b51dd", "#1151dd", "#1951de", "#2052de", "#2852df"};
        MusicSpectrumBar mb6 = findViewById(R.id.mb_type6);
        mb6.setDatas(highD2, ColorStr2);
        MusicSpectrumBar mb7 = findViewById(R.id.mb_type7);
        mb7.setDatas(highD2, ColorStr2);


        int[] highD3 = {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
        String[] ColorStr3 = {"#0050dc", "#0650dc", "#0b51dd", "#1151dd", "#1951de", "#2052de", "#2852df", "#3153df", "#3a53e0", "#4454e0", "#4e54e1", "#5855e2", "#6255e2", "#6d56e3", "#7756e3", "#8257e4", "#8c58e5", "#9758e5", "#a159e6", "#ab59e7", "#b45ae7", "#be5ae8", "#c65be8", "#ce5be9", "#d65ce9", "#dd5cea", "#e45cea", "#e95deb", "#ee5deb", "#ce5be9", "#d65ce9", "#dd5cea", "#e45cea", "#e95deb", "#ee5deb", "#0050dc", "#0650dc", "#0b51dd", "#1151dd", "#1951de", "#2052de", "#2852df"};
        MusicSpectrumBar mb8 = findViewById(R.id.mb_type8);
        mb8.setDatas(highD3, ColorStr3);
        MusicSpectrumBar mb9 = findViewById(R.id.mb_type9);
        mb9.setDatas(highD3, ColorStr3);

    }
}
