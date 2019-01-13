package com.example.umeng_demo2;

import android.app.Application;
import android.widget.Toast;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class app extends Application implements Thread.UncaughtExceptionHandler {
    @Override
    public void onCreate() {
        super.onCreate();
        UMConfigure.init(this,"5a12384aa40fa3551f0001d1","umeng",UMConfigure.DEVICE_TYPE_PHONE,"");
        //下面这一行代码根据需要设置自己的，当前表示qq
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        //  全局异常补货
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        Writer stringWriter = new StringWriter();

        PrintWriter printWriter = new PrintWriter(stringWriter);

        throwable.printStackTrace(printWriter);
        //吐司
        Toast.makeText(this,"asdfsfs",Toast.LENGTH_SHORT).show();
    }
}
