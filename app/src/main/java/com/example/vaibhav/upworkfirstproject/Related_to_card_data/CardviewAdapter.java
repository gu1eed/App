package com.example.vaibhav.upworkfirstproject.Related_to_card_data;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vaibhav.upworkfirstproject.Fragment.BottomNavigation.MessageWindow;
import com.example.vaibhav.upworkfirstproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VAIBHAV on 30-Jan-17.
 */

public class CardviewAdapter extends RecyclerView.Adapter<CardviewAdapter.Myviewholder>{


    List<String> name=new ArrayList<String>();
    List<String> intro=new ArrayList<String>();
    List<String> sub=new ArrayList<String>();
    String purpose;
    Context context;


    public CardviewAdapter(List<String> name, List<String> into, List<String> sub,String purpose){
        this.name=name;
        this.intro=into;
        this.sub=sub;
        this.purpose=purpose;
    }

    @Override
    public Myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);
        return new Myviewholder(view);
    }

    @Override
    public void onBindViewHolder(Myviewholder holder, int position) {
        holder.name.setText(name.get(position));
        holder.detail.setText(intro.get(position));
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class Myviewholder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView image;
        TextView detail,name;

        public Myviewholder(View itemView) {
            super(itemView);
            image=(ImageView) itemView.findViewById(R.id.imageView_profile);
            name=(TextView) itemView.findViewById(R.id.card_name);
            detail=(TextView) itemView.findViewById(R.id.textView_cardetail);
            itemView.setOnClickListener(this);
            context=itemView.getContext();
        }

        @Override
        public void onClick(View view) {
            if (purpose.equals("nm")){
                context.startActivity(new Intent(context,TutorDetail.class).putExtra("name",name.getText())
                        .putExtra("intro",detail.getText()).putExtra("subj",sub.get(getLayoutPosition())));

            } else context.startActivity(new Intent(context,MessageWindow.class).putExtra("name",name.getText()));

        }
    }
}
