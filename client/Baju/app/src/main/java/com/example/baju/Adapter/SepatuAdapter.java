package com.example.baju.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.baju.EditActivity;
import com.example.baju.Model.Sepatu;
import com.example.baju.R;

import java.util.List;

public class SepatuAdapter extends RecyclerView.Adapter<SepatuAdapter.MyViewHolder> {
    List<Sepatu> mSepatuList;

    public SepatuAdapter(List <Sepatu> SepatuList) {
        mSepatuList = SepatuList;
    }

    @Override
    public MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sepatu_list, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder (MyViewHolder holder, final int position){
        holder.mTextViewId.setText("Id_spt = " + mSepatuList.get(position).getId_spt());
        holder.mTextViewNama.setText("Size = " + mSepatuList.get(position).getSize());
        holder.mTextViewNomor.setText("Color = " + mSepatuList.get(position).getColor());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), EditActivity.class);
                mIntent.putExtra("Id_spt", mSepatuList.get(position).getId_spt());
                mIntent.putExtra("Size", mSepatuList.get(position).getSize());
                mIntent.putExtra("Color", mSepatuList.get(position).getColor());
                view.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount () {
        return mSepatuList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewId, mTextViewNama, mTextViewNomor;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewId = (TextView) itemView.findViewById(R.id.tvId_spt);
            mTextViewNama = (TextView) itemView.findViewById(R.id.tvSize);
            mTextViewNomor = (TextView) itemView.findViewById(R.id.tvColor);
        }
    }
}
