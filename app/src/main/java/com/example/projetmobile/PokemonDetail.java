package com.example.projetmobile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.projetmobile.Adaptater.PokemonEvolutionAdapter;
import com.example.projetmobile.Adaptater.PokemonTypeAdapter;
import com.example.projetmobile.Common.Common;
import com.example.projetmobile.Model.Pokemon;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrConfig;
import com.r0adkll.slidr.model.SlidrInterface;
import com.r0adkll.slidr.model.SlidrPosition;


/**
 * A simple {@link Fragment} subclass.
 */
public class PokemonDetail extends Fragment {

    ImageView pokemon_img;
    TextView pokemon_name, pokemon_wheight, pokemon_height;
    RecyclerView recycler_type, recycler_weakness, recycler_prev_evolution, recycler_next_evolution;
    SlidrInterface slidrInterface;


    static PokemonDetail instance;

    public static PokemonDetail getInstance() {
        if(instance == null)
            instance = new PokemonDetail();
        return instance;
    }


    public PokemonDetail() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View itemView = inflater.inflate(R.layout.fragment_pokemon_detail, container, false);

        Pokemon pokemon = Common.findPokemonByNum(getArguments().getString("num"));

        pokemon_img = (ImageView) itemView.findViewById(R.id.pokemon_image);
        pokemon_name = (TextView) itemView.findViewById(R.id.name);
        pokemon_wheight = (TextView) itemView.findViewById(R.id.weigh);
        pokemon_height = (TextView) itemView.findViewById(R.id.heigh);

        recycler_type = (RecyclerView) itemView.findViewById(R.id.recycler_type);
        recycler_type.setHasFixedSize(true);
        recycler_type.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false));

        recycler_weakness = (RecyclerView) itemView.findViewById(R.id.recycler_weakness);
        recycler_weakness.setHasFixedSize(true);
        recycler_weakness.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false));

        recycler_prev_evolution = (RecyclerView) itemView.findViewById(R.id.recycler_prev_evolution);
        recycler_prev_evolution.setHasFixedSize(true);
        recycler_prev_evolution.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false));

        recycler_next_evolution = (RecyclerView) itemView.findViewById(R.id.recycler_next_evolution);
        recycler_next_evolution.setHasFixedSize(true);
        recycler_next_evolution.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false));
        //slidrInterface = Slidr.attach(getActivity().onAttachFragment(this));
        setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move));

        setDetailPokemon(pokemon);

        return itemView;
    }

    @Override
    public void onResume() {
        super.onResume();
           // slidrInterface = Slidr.replace();
    }


    private void setDetailPokemon(Pokemon pokemon) {
        //load Img
        Glide.with(getActivity()).load(pokemon.getImg()).into(pokemon_img);
        pokemon_name.setText(pokemon.getName());
        pokemon_wheight.setText("Weight :"+pokemon.getWeight());
        pokemon_height.setText("Height :"+pokemon.getHeight());

        //set type
        PokemonTypeAdapter typeAdapter = new PokemonTypeAdapter(getActivity(),pokemon.getType());
        recycler_type.setAdapter(typeAdapter);

        //set weakness
        PokemonTypeAdapter weaknessAdapter = new PokemonTypeAdapter(getActivity(),pokemon.getWeaknesses());
        recycler_weakness.setAdapter(weaknessAdapter);

        //set Evolution
        PokemonEvolutionAdapter prevEvolutionAdapter = new PokemonEvolutionAdapter(getActivity(),pokemon.getPrevEvolution());
        recycler_prev_evolution.setAdapter(prevEvolutionAdapter);
        PokemonEvolutionAdapter nextEvolutionAdapter = new PokemonEvolutionAdapter(getActivity(),pokemon.getNextEvolution());
        recycler_next_evolution.setAdapter(nextEvolutionAdapter);


    }

}
