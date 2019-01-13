package com.example.mryang.thirdweektest.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mryang.thirdweektest.R;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

public class ScanActivityActivity extends AppCompatActivity implements QRCodeView.Delegate {
    private ZXingView zXingView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_activity);

        zXingView.findViewById(R.id.zxing_view);
        //设置代理
        zXingView.setDelegate(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //打开相机
        zXingView.startCamera();
        //显示扫描框，并且延迟1.5秒后开始识别
        zXingView.startSpotAndShowRect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        zXingView.stopCamera();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        zXingView.onDestroy();
    }


    @Override
    public void onScanQRCodeSuccess(String result) {

    }

    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {

    }

    @Override
    public void onScanQRCodeOpenCameraError() {

    }
}
