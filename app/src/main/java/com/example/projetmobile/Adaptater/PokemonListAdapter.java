package com.example.projetmobile.Adaptater;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.projetmobile.Common.Common;
import com.example.projetmobile.Interface.ItemClickListener;
import com.example.projetmobile.Model.Pokemon;
import com.example.projetmobile.R;

import java.util.List;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.MyViewHolder> {

    private Context context;
    private List<Pokemon> pokemonList;

    public PokemonListAdapter(Context context, List<Pokemon> pokemonList) {
        this.context = context;
        this.pokemonList = pokemonList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.pokemon_list_item,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Load Image
        Glide.with(context).load(pokemonList.get(position).getImg()).into(holder.pokemon_image);
        //Set name
        holder.pokemon_name.setText(pokemonList.get(position).getName());

        //Event
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onCLick(View view, int position) {
               // Toast.makeText(context,"click on pokemon " + pokemonList.get(position).getName(), Toast.LENGTH_LONG).show();

                LocalBroadcastManager.getInstance(context)
                        .sendBroadcast(new Intent(Common.KEY_ENABLE_HOME).putExtra("num", pokemonList.get(position).getNum()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView pokemon_image;
        TextView pokemon_name;

        ItemClickListener itemClickListener;

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        public MyViewHolder(View itemView) {
            super(itemView);

            pokemon_image = (ImageView) itemView.findViewById(R.id.pokemon_image);
            pokemon_name = (TextView) itemView.findViewById(R.id.text_pokemon_name);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){
            itemClickListener.onCLick(view,getAdapterPosition());
        }
    }
}
