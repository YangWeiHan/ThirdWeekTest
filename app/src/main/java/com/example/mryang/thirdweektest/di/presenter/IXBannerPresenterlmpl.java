package com.example.mryang.thirdweektest.di.presenter;

import com.example.mryang.thirdweektest.data.bean.XBannerbean;
import com.example.mryang.thirdweektest.di.contract.IXBannerContract;
import com.example.mryang.thirdweektest.di.model.IXBannerModellmpl;

import java.lang.ref.SoftReference;

public class IXBannerPresenterlmpl implements IXBannerContract.IXBannerPresenter<IXBannerContract.IXBannerView> {
    IXBannerContract.IXBannerView ixBannerView;
    private SoftReference<IXBannerContract.IXBannerView> reference;
    private IXBannerModellmpl modellmpl;

    @Override
    public void attchView(IXBannerContract.IXBannerView ixBannerView) {
        this.ixBannerView = ixBannerView ;
        reference = new SoftReference<>(ixBannerView);
        modellmpl = new IXBannerModellmpl();
    }

    @Override
    public void detachView(IXBannerContract.IXBannerView ixBannerView) {
        reference.clear();
    }

    @Override
    public void roToRequestImageData() {
        modellmpl.ContainImageData(new IXBannerContract.IXBannerModel.OnCallBack() {
            @Override
            public void setData(XBannerbean bannerbean) {
                ixBannerView.setBannerData(bannerbean);
            }
        });

    }
}
