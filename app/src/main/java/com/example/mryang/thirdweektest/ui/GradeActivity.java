package com.example.mryang.thirdweektest.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.mryang.thirdweektest.R;
import com.example.mryang.thirdweektest.data.bean.XBannerbean;
import com.example.mryang.thirdweektest.di.contract.IXBannerContract;
import com.example.mryang.thirdweektest.di.presenter.IXBannerPresenterlmpl;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

public class GradeActivity extends AppCompatActivity implements IXBannerContract.IXBannerView {

        private XBanner banner;
        private List<String> banner_list ;
        private IXBannerPresenterlmpl presenterlmpl;
        private Button  button;
        private Button  button_QR,button_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.htn_tiaozhuan);
        banner = findViewById(R.id.xbanner_list);
        button_QR = findViewById(R.id.QR_code);
        presenterlmpl = new IXBannerPresenterlmpl();
        presenterlmpl.attchView(this);
        presenterlmpl.roToRequestImageData();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GradeActivity.this,ShowActivity.class));
            }
        });
        button_QR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GradeActivity.this,QR_codeActivity.class));
            }
        });
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GradeActivity.this,LoginActivity.class));
            }
        });
    }

    @Override
    public void setBannerData(XBannerbean bannerbean) {
        banner_list = new ArrayList<>();
        for (int i = 0; i < bannerbean.getData().size(); i++) {
            banner_list.add(bannerbean.getData().get(i).getIcon());
        }
        banner.setData(banner_list,null);
        banner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(GradeActivity.this).load(banner_list.get(position)).into((ImageView) view);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenterlmpl.roToRequestImageData();
        banner.stopAutoPlay();
    }
}
