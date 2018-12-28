package com.example.zuoye.model;

import com.example.zuoye.bean.GoodsBean;
import com.example.zuoye.callback.MyCallBack;
import com.example.zuoye.callback.ICallBack;
import com.example.zuoye.network.RetrofitManager;
import com.example.zuoye.utils.OkHttpUtil;
import com.google.gson.Gson;

import java.util.Map;

import okhttp3.RequestBody;

public class ModelImpl implements Imodel{


    @Override
    public void requestData(String url,  final Class clazz, final MyCallBack myCallBack) {

        RetrofitManager.getInstance().get(url).result(new RetrofitManager.HttpListener() {
            @Override
            public void onSuccess(String data) {
                Object obj = new Gson().fromJson(data,GoodsBean.class);
                myCallBack.getData(obj);

            }

            @Override
            public void onFail(String error) {

            }
        });
    }
}
