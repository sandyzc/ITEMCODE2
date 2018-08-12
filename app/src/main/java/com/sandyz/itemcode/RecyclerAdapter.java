package com.sandyz.itemcode;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.google.android.gms.ads.AdView;
import com.ramotion.foldingcell.FoldingCell;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class RecyclerAdapter extends RecyclerSwipeAdapter<RecyclerAdapter.Viewholder> {

    private ArrayList<Beans> myList;
    private Context context;
    private HashSet<Integer> unfoldedIndexes = new HashSet<>();


    public RecyclerAdapter(ArrayList<Beans> myList, Context context) {

        this.myList = myList;
        this.context = context;
    }

    public Beans getItem(int pos) {

        return this.myList.get(pos);
    }

    //inflate contentents into row
    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = null;
        Viewholder mviewholder = null;

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        v = inflater.inflate(R.layout.recycler_row, parent, false);
        mviewholder = new Viewholder(v);


        return mviewholder;
    }



    @Override
    public void onBindViewHolder(Viewholder holder, final int position) {

        if (unfoldedIndexes.contains(position)) {
            holder.fc.unfold(true);
        } else {
            holder.fc.fold(true);
        }



        final Beans codes = myList.get(position);
        //using swipelayout to use share option
        holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
        holder.swipeLayout.addSwipeListener(new SimpleSwipeListener() {
            @Override
            public void onOpen(SwipeLayout layout) {

                layout.findViewById(R.id.trash);

                super.onOpen(layout);
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
                String desc = codes.getDescp();

                Intent share = new Intent();
                share.setAction(Intent.ACTION_SEND);
                share.putExtra(Intent.EXTRA_TEXT, code + "\n" + desc);
                share.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                share.setType("text/plain");

                context.startActivity(share);
            }
        });


        holder.code.setText(codes.getCode());
        holder.desp.setText(codes.getDescp().trim());
//        holder.equip.setText(codes.getEquip());
        holder.descp_unfold.setText(codes.getDescp());
        holder.code_unfold.setText(codes.getCode());
        holder.pip_stock.setText(codes.getPip_stock());
        holder.fdy_stock.setText(codes.getFdy_stock());
        holder.pip_store_location.setText(codes.getPip_location());
        holder.fdy_sore_location.setText(codes.getFdy_location());

    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }


    public class Viewholder extends RecyclerView.ViewHolder {

        SwipeLayout swipeLayout;
        Button share_Button;
        private TextView code, desp, equip,code_unfold,descp_unfold,fdy_stock,pip_stock,pip_store_location,fdy_sore_location;
        public AdView adView;
        FoldingCell fc;

        Viewholder(final View itemView) {
            super(itemView);


            swipeLayout = itemView.findViewById(R.id.swipe);
            share_Button = itemView.findViewById(R.id.share_Button);
            code = itemView.findViewById(R.id.itemcode_rec);
            desp = itemView.findViewById(R.id.descp_Rec);
            code_unfold=itemView.findViewById(R.id.code_unfold);
            descp_unfold=itemView.findViewById(R.id.descp_unfold);
            pip_stock=itemView.findViewById(R.id.pip_stock);
            fdy_stock=itemView.findViewById(R.id.fdy_stock);
            pip_store_location=itemView.findViewById(R.id.pip_store_location);
            fdy_sore_location=itemView.findViewById(R.id.fdy_sore_location);

            fc = itemView.findViewById(R.id.folding_cell);
            fc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fc.toggle(false);


                }
            });

        }
    }

    void filter(ArrayList<Beans> filterbeans) {
        this.myList = filterbeans;

        notifyDataSetChanged();
    }

    public void registerToggle(int position) {
        if (unfoldedIndexes.contains(position))
            registerFold(position);
        else
            registerUnfold(position);
    }

    public void registerFold(int position) {
        unfoldedIndexes.remove(position);
    }

    public void registerUnfold(int position) {
        unfoldedIndexes.add(position);
    }


}
