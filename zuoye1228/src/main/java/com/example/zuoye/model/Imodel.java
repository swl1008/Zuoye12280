package com.example.zuoye.model;

import com.example.zuoye.callback.MyCallBack;

import java.util.Map;

public interface Imodel {
    void requestData(String url,Class clazz,MyCallBack myCallBack);
}
