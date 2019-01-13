package com.example.mryang.thirdweektest.di.model;

import com.example.mryang.thirdweektest.data.apils.Apils;
import com.example.mryang.thirdweektest.data.bean.XBannerbean;
import com.example.mryang.thirdweektest.di.contract.IXBannerContract;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.List;

public class IXBannerModellmpl implements IXBannerContract.IXBannerModel {
    @Override
    public void ContainImageData(final OnCallBack onCallBack) {
        OkGo.<String>get(Apils.XBANNDER_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String xBannerData = response.body().toString();
                Gson gson = new Gson();
                XBannerbean bannerbean = gson.fromJson(xBannerData, XBannerbean.class);
                onCallBack.setData(bannerbean);


            }
        });
    }
}
