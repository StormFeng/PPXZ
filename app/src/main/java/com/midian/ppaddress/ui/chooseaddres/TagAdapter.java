package com.midian.ppaddress.ui.chooseaddres;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.midian.baselib.R;
import com.midian.ppaddress.bean.MemberOrderMemberShowBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import midian.baselib.widget.CircleImageView;

/**
 * 两情若是久长时
 * 又岂在朝朝暮暮
 * Created by chu on 2016/5/29.
 */
public class TagAdapter<T> extends BaseAdapter {

    private  Context mContext;
    private  List<MemberOrderMemberShowBean.Counselors> mDataList;

    public TagAdapter(Context context) {
        this.mContext = context;
        mDataList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.tag_item, null);//
//        View view = LayoutInflater.from(mContext).inflate(R.layout.item_make_appointment, null);
        CircleImageView head_cv = (CircleImageView) view.findViewById(R.id.head_cv);
        TextView name_tv = (TextView) view.findViewById(R.id.name_tv);
//        TextView check_tv = (TextView) view.findViewById(R.id.check_tv);
//        Map t = mDataList.get(position);
        for (int i = 0; i < mDataList.size(); i++) {
            name_tv.setText(mDataList.get(i).getFullname());

//            head_cv.setImageDrawable(mDataList.get(i).getPortrait());

        }

     /*   if (t instanceof String) {
            name_tv.setText((String) t);
            head_cv.setBackgroundResource(t.);
        }*/
        return view;
    }

    public void onlyAddAll(List datas) {
//        mDataList.addAll(datas);
        this.mDataList = datas;
        notifyDataSetChanged();
    }

    public void clearAndAddAll(List datas) {
        mDataList.clear();
        onlyAddAll(datas);
    }
}