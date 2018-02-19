package com.sandyz.itemcode;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.TextView;

import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by santosh on 30-08-2017.
 */

public class    RecyclerAdapter extends RecyclerSwipeAdapter<RecyclerAdapter.Viewholder> {

    ArrayList<Beans> myList;
    Context context;
    private List<Beans> list;

    int AD_TYPE=1;

    int Content_Type=2;

    public RecyclerAdapter(ArrayList<Beans> myList, Context context) {

        this.myList = myList;
        this.context = context;
    }

    public Beans getItem(int pos){

        return this.myList.get(pos);
    }

   //inflate contentents into row
    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType)  {
        View v=null;
        Viewholder  mviewholder=null;

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

                 v = inflater.inflate(R.layout.recycler_row, parent, false);
            mviewholder= new Viewholder(v);


            return mviewholder;
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {



        final Beans codes =myList.get(position);
        //using swipelayout to use share option
        holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
        holder.swipeLayout.addSwipeListener(new SimpleSwipeListener(){
            @Override
            public void onOpen(SwipeLayout layout) {

               layout.findViewById(R.id.trash);

                super.onOpen(layout);
            }

            @Override
            public void onStartClose(SwipeLayout layout) {
                super.onStartClose(layout);
            }

            @Override
            public void onClose(SwipeLayout layout) {
                super.onClose(layout);
            }
        });

        holder.share_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = codes.getCode();
                String desc= codes.getDescp();

                Intent share = new Intent();
                share.setAction(Intent.ACTION_SEND );
                share.putExtra(Intent.EXTRA_TEXT,code+"\n"+desc);
                share.setType("text/plain");
                context.startActivity(share);
            }
        });


        holder.code.setText(codes.getCode());
        holder.desp.setText(codes.getDescp());
        holder.equip.setText(codes.getEquip());
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }


    public class Viewholder extends RecyclerView.ViewHolder{

        SwipeLayout swipeLayout;
        Button share_Button;
        private TextView code,desp,equip;
        public AdView adView;

        public Viewholder(View itemView) {
            super(itemView);



            swipeLayout= (SwipeLayout)itemView.findViewById(R.id.swipe);
            share_Button= (Button)itemView.findViewById(R.id.share_Button);
            code=(TextView)itemView.findViewById(R.id.itemcode_rec);
            desp=(TextView)itemView.findViewById(R.id.descp_Rec);
            equip=(TextView)itemView.findViewById(R.id.equip_rec);

                    }
    }

    public void filter(ArrayList<Beans> filterbeans) {
            this.myList=filterbeans;

        notifyDataSetChanged();
    }


}
