package com.example.zuoye;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zuoye.bean.BannerBean;
import com.example.zuoye.fragment.CommentFragment;
import com.example.zuoye.fragment.DetailFragment;
import com.example.zuoye.fragment.ProductFragment;
import com.example.zuoye.presenter.PresenterImpl;
import com.example.zuoye.view.Iview;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private RadioGroup group;
    private List<Fragment> flist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        viewPager = findViewById(R.id.viewpager);
        group = findViewById(R.id.group);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                Fragment fragment=null;
                switch (i){
                    case 0:
                        fragment = new ProductFragment();
                        break;
                    case 1:
                        fragment = new DetailFragment();
                        break;
                    case 2:
                        fragment = new CommentFragment();
                        break;
                }

                return fragment;
            }

            @Override
            public int getCount() {
                return 3;
            }
        });



        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        group.check(R.id.btn_product);
                        break;
                    case 1:
                        group.check(R.id.btn_detail);
                        break;
                    case 2:
                        group.check(R.id.btn_comment);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.btn_product:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.btn_detail:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.btn_comment:
                        viewPager.setCurrentItem(2);
                        break;
                }
            }
        });

    }


}
