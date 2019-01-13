package com.example.mryang.thirdweektest.di.contract;

import com.example.mryang.thirdweektest.data.bean.XBannerbean;

public interface IXBannerContract {

    public interface IXBannerView{
        void setBannerData(XBannerbean bannerbean);
    }

    public interface IXBannerPresenter<IXBannerView>{

        void attchView(IXBannerView ixBannerView);

        void detachView(IXBannerView ixBannerView);

        void roToRequestImageData();
    }

    public interface IXBannerModel{

        void ContainImageData(OnCallBack onCallBack);

        public interface OnCallBack{

            void setData(XBannerbean bannerbean);
        }
    }
}
