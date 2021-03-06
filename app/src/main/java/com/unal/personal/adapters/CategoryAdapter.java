package com.unal.personal.adapters;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.unal.personal.R;
import com.unal.personal.interfaces.OnCategoryListListener;
import com.unal.personal.structures.Category;

import java.util.List;

/**
 * Created by juanka on 07/07/2016.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<Category> categories;
    private OnCategoryListListener mCallBack;


    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardviewcat, parent, false);
        return new CategoryViewHolder(v);
    }

    @Override
    public void onViewRecycled(CategoryViewHolder holder) {
        super.onViewRecycled(holder);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int i) {
        Category p = categories.get(i);
        holder.icon.setImageResource(p.getImage());
        holder.title.setText(p.getName());
        holder.cv.setBackgroundColor(Color.parseColor(p.getColor()));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public CategoryAdapter(List<Category> places, OnCategoryListListener mCallBack) {
        this.categories = places;
        this.mCallBack = mCallBack;
    }

    private void selectCategory(int position) {
        mCallBack.onCategorySelected(categories.get(position));
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cv;
        ImageView icon;
        TextView title;

        CategoryViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.card_view);
            icon = (ImageView) itemView.findViewById(R.id.image);
            title = (TextView) itemView.findViewById(R.id.title);
            cv.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            selectCategory(getAdapterPosition());
        }

    }
}
