package com.example.zuoye;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.zuoye.adapter.GoodsAdapter;
import com.example.zuoye.bean.GoodsBean;
import com.example.zuoye.bean.MessageBean;
import com.example.zuoye.presenter.PresenterImpl;
import com.example.zuoye.view.Iview;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Iview {

    private PresenterImpl presenter;
    private XRecyclerView xRecyclerView;
    private GoodsAdapter adapter;
    private int page;
    private boolean bool = true;
    private ImageView image_change;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        page=1;
        presenter = new PresenterImpl( this);
        initView();
    }
    private void initView(){

        xRecyclerView = findViewById(R.id.recycle);
        image_change = findViewById(R.id.image_change);
        xRecyclerView.setPullRefreshEnabled(true);
        xRecyclerView.setLoadingMoreEnabled(true);
        adapter = new GoodsAdapter(MainActivity.this,bool);
        xRecyclerView.setAdapter(adapter);
        xRecyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this,DividerItemDecoration.VERTICAL));
        xRecyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this,DividerItemDecoration.HORIZONTAL));
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                initData();
            }

            @Override
            public void onLoadMore() {
                initData();
            }
        });
        initData();

        adapter.setOnClickListener(new GoodsAdapter.onClickCallBack() {
            @Override
            public void setOnClick(String title, String price) {
                MessageBean messageBean = new MessageBean(title,price);
                EventBus.getDefault().postSticky(messageBean);
                Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                startActivity(intent);
            }
        });
        image_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bool){
                    bool=false;
                    xRecyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this,DividerItemDecoration.VERTICAL));
                    xRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
                    initData();
                }else {
                    bool = true;
                    xRecyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this,DividerItemDecoration.VERTICAL));
                    xRecyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this,DividerItemDecoration.HORIZONTAL));
                    xRecyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
                    initData();
                }

                adapter = new GoodsAdapter(MainActivity.this,bool);
                xRecyclerView.setAdapter(adapter);
            }
        });



    }

    @Override
    public void getRequest(Object o) {

        if(o instanceof GoodsBean){
            GoodsBean bean = (GoodsBean) o;
            if (bean == null){
                Toast.makeText(MainActivity.this,bean.getMsg(), Toast.LENGTH_SHORT).show();
            }else{
                if(page == 1){
                    adapter.setData(bean.getData());
                }else{
                    adapter.addData(bean.getData());
                }
                xRecyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
                page++;
                xRecyclerView.refreshComplete();
                xRecyclerView.loadMoreComplete();
            }

        }
    }
    private void initData(){
       presenter.startRequest(Apis.URL+page,GoodsBean.class);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }
}
