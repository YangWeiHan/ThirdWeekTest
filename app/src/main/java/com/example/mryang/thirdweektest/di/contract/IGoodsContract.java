package com.example.mryang.thirdweektest.di.contract;

import com.example.mryang.thirdweektest.data.bean.NineGongGeBean;
import com.example.mryang.thirdweektest.data.bean.ShoppingBean;

public interface IGoodsContract {

    public interface IGoodsView{
        void setGoodsData(NineGongGeBean responesData);

        void setfooterGooddData(ShoppingBean responesData);
    }

    public interface IGoodsrPresenter<IGoodsView>{

        void attchView(IGoodsView iGoodsView);

        void detachView(IGoodsView iGoodsView);

        void toToRequestGoodsData();

        void GotoRequestFooterGoodsData();
    }

    public interface IGoodsModel{

        void ContainGoodsData(OnCallBack onCallBack);

        void ContainFooterGoodsData(ShopCallBack ShopCallBack);

        public interface OnCallBack{

            void setData(NineGongGeBean responesData);
        }

        public interface ShopCallBack{
            void setShopData(ShoppingBean responesData);
        }
    }
}
