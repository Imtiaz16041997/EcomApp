package com.imtiaz.ecomapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.imtiaz.ecomapplication.Listeners.CategoriesClickListener;
import com.imtiaz.ecomapplication.Models.Category;
import com.imtiaz.ecomapplication.R;

import java.util.List;


public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesViewHolder> {
    Context context;
    List<Category> list;
    CategoriesClickListener categoriesClickListener;

    public CategoriesAdapter(Context context, List<Category> list,CategoriesClickListener categoriesClickListener) {
        this.context = context;
        this.list = list;
        this.categoriesClickListener = categoriesClickListener;
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoriesViewHolder(LayoutInflater.from(context).inflate(R.layout.categories_layout,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {
        holder.textView_categories.setText(list.get(position).getTitle());
        holder.imageview_categories.setImageResource(list.get(position).getIcon());
        holder.textView_categories.setSelected(true);

        holder.specific_categories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoriesClickListener.onClick(list.get(holder.getAdapterPosition()).getTitle());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class CategoriesViewHolder extends RecyclerView.ViewHolder{
    CardView specific_categories;
    ImageView imageview_categories;
    TextView textView_categories;
    public CategoriesViewHolder(@NonNull View itemView) {
        super(itemView);
        imageview_categories = itemView.findViewById(R.id.imageview_categories);
        textView_categories = itemView.findViewById(R.id.textView_categories);
        specific_categories = itemView.findViewById(R.id.specific_categories);
    }
}
