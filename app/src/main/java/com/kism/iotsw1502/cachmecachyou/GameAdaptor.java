package com.kism.iotsw1502.cachmecachyou;

import android.content.Context;
import android.util.Log;
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
    ArrayList<memberVO>list;


    public GameAdaptor(Context applicationContext, int activity_custum_list, ArrayList<memberVO>list) {

        this.context = applicationContext;
        this.customlist = activity_custum_list;
        this.lif = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list;
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

        TextView wait = (TextView)convertView.findViewById(R.id.wait);
        TextView ready = (TextView)convertView.findViewById(R.id.ready);
        TextView name = (TextView) convertView.findViewById(R.id.nickname_db);

        Log.v("listt", String.valueOf(list.size()));
        name.setText((list.get(position)).getNickname());
        if(list.get(position).isCheck()==1){
            wait.setVisibility(View.INVISIBLE);
            ready.setVisibility(View.VISIBLE);
        }else if(list.get(position).isCheck()==0){
            wait.setVisibility(View.VISIBLE);
            ready.setVisibility(View.INVISIBLE);
        }
        return convertView;
    }


}

