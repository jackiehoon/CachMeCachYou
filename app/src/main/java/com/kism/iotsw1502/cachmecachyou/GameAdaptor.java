package com.kism.iotsw1502.cachmecachyou;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by pc-15 on 2017-08-14.
 */
public class GameAdaptor extends BaseAdapter {
    Context context;
    int customlist;
    LayoutInflater lif;
    String nickName;

    public GameAdaptor(Context applicationContext, int activity_custum_list, String nickName) {
        this.context = applicationContext;
        this.customlist = activity_custum_list;
        this.lif = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.nickName = nickName;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int position) {
        return nickName;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = lif.inflate(customlist, parent, false);
        }


        TextView name = (TextView) convertView.findViewById(R.id.nickname_db);

        name.setText(nickName);
        return convertView;
    }


}

