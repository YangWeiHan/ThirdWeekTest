package com.example.mryang.thirdweektest.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mryang.thirdweektest.R;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;

public class QR_codeActivity extends AppCompatActivity {


    @BindView(R.id.saoyisao)
    Button saoyisao;
    @BindView(R.id.edit_text_qr)
    EditText editTextQr;
    @BindView(R.id.ok_qrcode)
    Button okQrcode;
    @BindView(R.id.QR_images)
    ImageView QRImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.saoyisao, R.id.ok_qrcode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.saoyisao:
                startActivity(new Intent(QR_codeActivity.this,ScanActivityActivity.class));
                checkPermission();
                break;
            case R.id.ok_qrcode:
                createQRCode();
                break;
        }
    }

    private void checkPermission() {
        //第一步，判断系统版本是否为6.0以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            //第二步：checkSelfPermission判断有没有此权限
            //第一个参数：上下文
            //第二个参数：我们想要判断的权限，此处为相机权限
            if (ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                //如果没授予，则申请权限
                //第一个：上下文
                //第二个：要申请的权限数组，此处为相机权限
                //第三个：请求码，startActivityForResult一样
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},100);
            }else{
                startActivity(new Intent(QR_codeActivity.this,ScanActivityActivity.class));
            }
        }
    }
    /**
     * 接受权限请求结果
     * @param requestCode 请求码
     * @param permissions 我们请求的权限数组
     * @param grantResults 返回结果数组
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            startActivity(new Intent(QR_codeActivity.this,ScanActivityActivity.class));
        }
    }

    private void createQRCode() {
        QRTast qrTast = new QRTast(this, QRImages, editTextQr);
        qrTast.execute(editTextQr.getText().toString());

    }

    static class QRTast extends AsyncTask<String,Void,Bitmap>{
        private WeakReference<Context> mContext;
        private WeakReference<ImageView> mImageView;

        public QRTast(Context context, ImageView qrImages, EditText editTextQr) {

            mContext = new WeakReference<>(context);
            mImageView = new WeakReference<>(qrImages);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            String str = params[0];
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            return QRCodeEncoder.syncEncodeQRCode(str,300);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap != null){
                mImageView.get().setImageBitmap(bitmap);
            }else {
                Toast.makeText(mContext.get(),"补考艺术",Toast.LENGTH_SHORT).show();

            }
        }
    }
}
