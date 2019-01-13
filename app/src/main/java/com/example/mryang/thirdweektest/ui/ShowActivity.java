package com.example.mryang.thirdweektest.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.mryang.thirdweektest.R;
import com.example.mryang.thirdweektest.data.adapter.FoorerAdapter;
import com.example.mryang.thirdweektest.data.adapter.NineGongGeAdapter;
import com.example.mryang.thirdweektest.data.bean.NineGongGeBean;
import com.example.mryang.thirdweektest.data.bean.ShoppingBean;
import com.example.mryang.thirdweektest.di.contract.IGoodsContract;
import com.example.mryang.thirdweektest.di.presenter.IGoodsPresenterlmpl;

import java.util.List;

public class ShowActivity extends AppCompatActivity implements IGoodsContract.IGoodsView {

    private IGoodsPresenterlmpl presenterlmpl;
    private RecyclerView hread_recyclerView,footer_recyclView;
    private NineGongGeAdapter n_adapter;
    private FoorerAdapter f_adapter;
    private List<NineGongGeBean.DataBean> goodsBeaen;
    private List<ShoppingBean.DataBean> footerData;
    private List<Object> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        hread_recyclerView = findViewById(R.id.hread_linear);
        footer_recyclView = findViewById(R.id.footer_linear);
        presenterlmpl = new IGoodsPresenterlmpl();
        presenterlmpl.attchView(this);
        presenterlmpl.toToRequestGoodsData();
        presenterlmpl.GotoRequestFooterGoodsData();


    }

    @Override
    public void setGoodsData(NineGongGeBean responesData) {
        goodsBeaen = responesData.getData();


        GridLayoutManager gridLayoutManager = new GridLayoutManager(ShowActivity.this, 5, GridLayoutManager.VERTICAL, false);
        hread_recyclerView.setLayoutManager(gridLayoutManager);

        n_adapter = new NineGongGeAdapter(this);
        n_adapter.setList(goodsBeaen);
        hread_recyclerView.setAdapter(n_adapter);


    }

    @Override
    public void setfooterGooddData(ShoppingBean responesData) {
        //数据源
        footerData = responesData.getData();

        footerData.remove(0);
        //布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(ShowActivity.this,LinearLayoutManager.VERTICAL,false);
        footer_recyclView.setLayoutManager(layoutManager);
        //适配器
        f_adapter = new FoorerAdapter(this);
        footer_recyclView.setAdapter(f_adapter);
        f_adapter.setBeanList(footerData.get(0).getList());
        for (int i = 0; i < footerData.size(); i++) {
            f_adapter.addBeanList(footerData.get(i).getList());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenterlmpl.detachView(this);
    }
}

