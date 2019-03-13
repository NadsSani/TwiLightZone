package com.example.twilightzone.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.twilightzone.R;

public class PagerAdapterForViewPager extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private Integer[] image = {R.drawable.trtg,R.mipmap.oldagehomelogocopy,R.drawable.oldagehomewall,R.mipmap.oldagehomelogocopy};

    public PagerAdapterForViewPager(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return image.length;
    }


    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.image_for_slliding,null);
        ImageView imageView = (ImageView)view.findViewById(R.id.imageView);
        imageView.setImageResource(image[position]);
        ViewPager vp = (ViewPager)container;
        vp.addView(view,0);
        return view;
    }
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager vp = (ViewPager)container;
        View view = (View)object;
        vp.removeView(view);
    }





}
