package com.sandyz.itemcode;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by santosh on 18-08-2017.
 */

public class SearchAdapter extends BaseAdapter {
    private ArrayList<Beans> querylist;
    private Context context;

    private LayoutInflater layoutInflater;

    public SearchAdapter(ArrayList<Beans> querylist, Context context) {
        this.querylist = querylist;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return querylist.size();
    }

    @Override
    public Object getItem(int i) {
        return querylist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view=layoutInflater.inflate(R.layout.v,viewGroup,false);
        TextView code =(TextView)view.findViewById(R.id.txtcode);
        TextView descp =(TextView)view.findViewById(R.id.txtdescprip);
        TextView equip =(TextView)view.findViewById(R.id.txtequip);

        Beans data = new Beans();

        code.setText(data.getCode());
        descp.setText(data.getDescp());
        equip.setText(data.getEquip());
        return view;
    }
}
