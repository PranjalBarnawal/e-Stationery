package com.example.codered;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolderClass extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView txtName,txtPrice;
    public ImageView imageView;
    public ItemClickListener listener;

    public ViewHolderClass(@NonNull View itemView) {
        super(itemView);
        imageView=(ImageView) itemView.findViewById(R.id.ProductImage);
        txtName=(TextView) itemView.findViewById(R.id.ProductName);
        txtPrice=(TextView) itemView.findViewById(R.id.Price);
    }

    public  void setItemClickListener(ItemClickListener listener) {
        this.listener=listener;

    }
    @Override
    public void onClick(View view) {
        listener.onClick(view,getAdapterPosition(),false);

    }
}
