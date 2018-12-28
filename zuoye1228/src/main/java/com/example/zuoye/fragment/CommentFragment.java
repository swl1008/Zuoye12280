package com.example.zuoye.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zuoye.R;
import com.example.zuoye.bean.CommentBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentFragment extends Fragment {
    @BindView(R.id.comment_price)
    TextView comment_price;
    private String cPrice;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comment,container,false);
        ButterKnife.bind(this,view);
        comment_price.setText(cPrice);
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getComment(CommentBean commentBean){
        cPrice = commentBean.getcPrice();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
