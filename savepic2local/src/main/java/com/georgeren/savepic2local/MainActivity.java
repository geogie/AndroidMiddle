package com.georgeren.savepic2local;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.georgeren.savepic2local.utils.ImageUtils;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private ImageView imgPic;
    private String picUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1504511970772&di=cf0c346e642074318c98ecc2c2d0c276&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F13%2F74%2F51%2F99d58PIC6vm_1024.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageUtils.getInstance().saveImage2(MainActivity.this, picUrl,"myTest.jpg");
            }
        });
        imgPic = (ImageView) findViewById(R.id.imgPic);
        ImageUtils.getInstance().displayImage(picUrl, imgPic);
    }

}
