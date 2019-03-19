package com.example.recyclerviewapplication;


import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class menuadapter extends  RecyclerView.Adapter<menuadapter.CanteenMenuViewHolder> {

    private Context mCtx;
    private List<Object> CanteenMenuList;


    public menuadapter(Context mCtx, List<Object> CanteenMenuList) {
        this.mCtx = (Context) mCtx;
        this.CanteenMenuList = CanteenMenuList;
    }

    @Override
    public CanteenMenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.my_layout, null);
        return new CanteenMenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CanteenMenuViewHolder holder, int position) {
        //getting the CanteenMenu of the specified position
        menuclass CanteenMenu = (menuclass) CanteenMenuList.get(position);

        //binding the data with the viewholder views
        holder.textViewTitle.setText(CanteenMenu.getTitle());
       // holder.textViewShortDesc.setText(CanteenMenu.getShortdesc());
        //holder.textViewRating.setText(String.valueOf(CanteenMenu.getRating()));
        holder.textViewShortDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Toast toast = Toast.makeText(mCtx.getApplicationContext(), "This is a message displayed in a Toast", Toast.LENGTH_SHORT); toast.show();

            }
        });
      //  holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(CanteenMenu.getImage()));

        FirebaseStorage firebaseStorage=FirebaseStorage.getInstance();
        StorageReference storageReference = firebaseStorage.getReference();
        storageReference.child(CanteenMenu.getTitle()).child("Images/Profile Pic").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).fit().centerCrop().into(holder.imageView);
            }
        });

    }


    @Override
    public int getItemCount() {
        return CanteenMenuList.size();
    }


    class CanteenMenuViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewPrice;
        Button textViewShortDesc;
        ImageView imageView;

        public CanteenMenuViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.text1);
            textViewShortDesc = itemView.findViewById(R.id.butt1);

            imageView = itemView.findViewById(R.id.img);

        }
    }


}
