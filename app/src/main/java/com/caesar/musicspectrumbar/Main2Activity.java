package com.caesar.musicspectrumbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.caesar.musicspectrumbarlibrary.MusicSpectrumBar;

public class Main2Activity extends AppCompatActivity {
    int[] highD = {1, 2, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 6, 7};
    String[] ColorStr = {"#0050dc", "#0650dc", "#0b51dd", "#1151dd", "#1951de", "#2052de", "#2852df", "#3153df", "#3a53e0", "#4454e0", "#4e54e1", "#5855e2", "#6255e2", "#6d56e3", "#7756e3", "#8257e4", "#8c58e5", "#9758e5", "#a159e6", "#ab59e7", "#b45ae7", "#be5ae8", "#c65be8", "#ce5be9", "#d65ce9", "#dd5cea", "#e45cea", "#e95deb", "#ee5deb", "#ce5be9", "#d65ce9", "#dd5cea", "#e45cea", "#e95deb", "#ee5deb"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
    }

    private void initView() {


        findViewById(R.id.btn_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        final MusicSpectrumBar mb_type = findViewById(R.id.mb_type);
        mb_type.setDatas(highD, ColorStr);
    }
}
