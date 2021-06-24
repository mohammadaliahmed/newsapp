package com.appsinventiv.newsapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

import com.appsinventiv.newsapp.NetworkResponses.ApiResponse;
import com.appsinventiv.newsapp.NetworkResponses.Value;
import com.appsinventiv.newsapp.R;
import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by AliAh on 21/02/2018.
 */

public class MainSliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    List<Value> itemList;

    public MainSliderAdapter(Context context, List<Value> itemList) {

        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.main_news_slider, container, false);
        ImageView back = view.findViewById(R.id.back);
        ImageView next = view.findViewById(R.id.next);
        TextView title = view.findViewById(R.id.title);


        title.setText(itemList.get(position).getName());
        container.addView(view);




        return view;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);

    }


}
