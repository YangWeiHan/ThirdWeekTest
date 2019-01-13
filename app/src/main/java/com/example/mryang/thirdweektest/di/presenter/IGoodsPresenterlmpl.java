package com.example.mryang.thirdweektest.di.presenter;

import com.example.mryang.thirdweektest.data.bean.NineGongGeBean;
import com.example.mryang.thirdweektest.data.bean.ShoppingBean;
import com.example.mryang.thirdweektest.di.contract.IGoodsContract;
import com.example.mryang.thirdweektest.di.model.IGoodsModellmpl;

import java.lang.ref.SoftReference;

public class IGoodsPresenterlmpl implements IGoodsContract.IGoodsrPresenter<IGoodsContract.IGoodsView> {
    IGoodsContract.IGoodsView iGoodsView;
    private SoftReference<IGoodsContract.IGoodsView> reference;
    private IGoodsModellmpl modellmpl;

    @Override
    public void attchView(IGoodsContract.IGoodsView iGoodsView) {
        this.iGoodsView = iGoodsView;
        reference = new SoftReference<>(iGoodsView);
        modellmpl = new IGoodsModellmpl();
    }

    @Override
    public void detachView(IGoodsContract.IGoodsView iGoodsView) {
        reference.clear();
    }

    @Override
    public void toToRequestGoodsData() {
        modellmpl.ContainGoodsData(new IGoodsContract.IGoodsModel.OnCallBack() {
            @Override
            public void setData(NineGongGeBean responesData) {
                iGoodsView.setGoodsData(responesData);
            }
        });
    }

    @Override
    public void GotoRequestFooterGoodsData() {
        modellmpl.ContainFooterGoodsData(new IGoodsContract.IGoodsModel.ShopCallBack() {
            @Override
            public void setShopData(ShoppingBean responesData) {
                iGoodsView.setfooterGooddData(responesData);
            }
        });
    }
}
