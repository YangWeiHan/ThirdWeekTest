package com.example.mryang.thirdweektest.di.model;

import com.example.mryang.thirdweektest.data.apils.Apils;
import com.example.mryang.thirdweektest.data.bean.NineGongGeBean;
import com.example.mryang.thirdweektest.data.bean.ShoppingBean;
import com.example.mryang.thirdweektest.di.contract.IGoodsContract;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class IGoodsModellmpl implements IGoodsContract.IGoodsModel {
    @Override
    public void ContainGoodsData(final OnCallBack onCallBack) {
        OkGo.<String>get(Apils.JIUGONGGE_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String jiuGongGeData = response.body().toString();
                Gson gson = new Gson();
                NineGongGeBean responesData = gson.fromJson(jiuGongGeData, NineGongGeBean.class);
                onCallBack.setData(responesData);
            }
        });
    }

    @Override
    public void ContainFooterGoodsData(final ShopCallBack ShopCallBack) {
        OkGo.<String>get(Apils.SHOPPING_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String shoppingData = response.body().toString();
                Gson gson = new Gson();
                ShoppingBean responesData = gson.fromJson(shoppingData, ShoppingBean.class);
                ShopCallBack.setShopData(responesData);
            }
        });
    }

}
