package com.example.mihw.segmentationarc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SegmentationArc segmentationArc;
    private int[] colors={R.color.main_top_color1,R.color.main_top_color2,
            R.color.main_top_color3,R.color.main_top_color4};
    private float[] weightStandard={40.2f,52.4f,71.2f,95.65f,110.44f};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {

        segmentationArc=findViewById(R.id.sta);
        segmentationArc.setRoundwidth(Utils.dip2px(12));
        segmentationArc.setStandColors(colors);
        segmentationArc.setStandWeights(weightStandard);
        segmentationArc.curWeight(54.2f);

    }
}
