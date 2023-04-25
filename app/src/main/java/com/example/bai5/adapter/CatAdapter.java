package com.example.bai5.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bai5.MainActivity;
import com.example.bai5.R;
import com.example.bai5.model.Cat;

import java.util.ArrayList;
import java.util.List;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.CatViewHolder> {

    private MainActivity mainActivity;

    private List<Cat> mList;
    private CatItemListener catItemListener;

    public CatAdapter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.mList = new ArrayList<>();
    }

    public void setCatItemListener(CatItemListener catItemListener) {
        this.catItemListener = catItemListener;
    }
    public Cat getItem(int i) {
        return mList.get(i);
    }

    public List<Cat> getList() {
        return mList;
    }
    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new CatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {
        Cat cat = mList.get(position);
        holder.img.setImageResource(cat.getImg());
        holder.name.setText(cat.getName());
        holder.price.setText(cat.getPrice() + "");
        holder.infor.setText(cat.getInfor());
        holder.btXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Thong bao xoa");
                builder.setMessage("Ban co chac chan xoa");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mList.remove(position);
                        notifyDataSetChanged();
                        mainActivity.list = mList;
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class CatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView img;
        TextView name, price, infor;
        Button btXoa;
        public CatViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.item_img);
            name = itemView.findViewById(R.id.item_name);
            price = itemView.findViewById(R.id.item_price);
            infor = itemView.findViewById(R.id.item_desc);
            btXoa = itemView.findViewById(R.id.btXoa);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (catItemListener != null) {
                catItemListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface CatItemListener {
        void onItemClick(View view, int position);
    }

    public void add(Cat cat) {
        mList.add(cat);
        notifyDataSetChanged();
        mainActivity.list = mList;
    }

    public void update(Cat cat, int i) {
        mList.set(i, cat);
        notifyDataSetChanged();
        mainActivity.list = mList;
    }
}
