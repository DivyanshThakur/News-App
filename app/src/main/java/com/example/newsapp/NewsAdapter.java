package com.example.newsapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<News> items = new ArrayList<>();

    public void updateNews(List<News> newsList){
        items.clear();
        items.addAll(newsList);

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent,false);
        NewsViewHolder viewHolder = new NewsViewHolder(view);

        view.setOnClickListener(v -> Toast.makeText(parent.getContext(), "Item Clicked at position " + viewHolder.getAdapterPosition(), Toast.LENGTH_SHORT).show());

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News currentNews = items.get(position);
        holder.getTitleView().setText(currentNews.getTitle());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitleView;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            mTitleView = itemView.findViewById(R.id.title);
        }

        public TextView getTitleView() {
            return mTitleView;
        }
    }
}
