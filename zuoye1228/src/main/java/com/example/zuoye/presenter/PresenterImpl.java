package com.example.zuoye.presenter;

import com.example.zuoye.bean.GoodsBean;
import com.example.zuoye.model.ModelImpl;
import com.example.zuoye.callback.MyCallBack;
import com.example.zuoye.view.Iview;

import java.util.Map;

public class PresenterImpl implements Ipresenter {
    private Iview iView;
    private ModelImpl model;

    public PresenterImpl(Iview iview) {
        iView = iview;
        model = new ModelImpl();
    }

    @Override
    public void startRequest(String url, Class clazz) {
        model.requestData(url, GoodsBean.class, new MyCallBack() {
            @Override
            public void getData(Object o) {
                iView.getRequest(o);
            }
        });
    }


    public void onDetach(){
        if(model!=null){
            model = null;
        }
        if(iView!=null){
            iView = null;
        }
    }



}
