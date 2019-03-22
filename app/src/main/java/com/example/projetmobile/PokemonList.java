package com.example.projetmobile;


import android.opengl.Matrix;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.projetmobile.Adaptater.PokemonListAdapter;
import com.example.projetmobile.Common.Common;
import com.example.projetmobile.Common.ItemOffsetDecoration;
import com.example.projetmobile.Model.Pokedex;
import com.example.projetmobile.Model.Pokemon;
import com.example.projetmobile.Retrofit.IPokemonDex;
import com.example.projetmobile.Retrofit.RetrofitClient;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.RequiresApi;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import static android.app.PendingIntent.getActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class PokemonList extends Fragment{

    IPokemonDex iPokemonDex;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    RecyclerView pokemon_list_recyclerview;
    PokemonListAdapter adapter;
    List<String> last_suggest = new ArrayList<>();
    PokemonListAdapter search_adapter;
    MaterialSearchBar searchBar;


    static PokemonList instance;


    public static PokemonList getInstance() {
        if(instance == null)
            instance = new PokemonList();
        return instance;
    }





    public PokemonList() {
        Retrofit retrofit = RetrofitClient.getInstace();
        iPokemonDex = retrofit.create(IPokemonDex.class);

    }


    //@RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_pokemon_list, container, false);

        pokemon_list_recyclerview = view.findViewById(R.id.pokemon_list_recyclerview);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
        pokemon_list_recyclerview.setLayoutManager(manager);
        pokemon_list_recyclerview.setHasFixedSize(true);
        adapter = new PokemonListAdapter(getActivity(),Common.commonPokemonList);
        pokemon_list_recyclerview.setAdapter(adapter);
        ItemOffsetDecoration itemOffsetDecoration = new ItemOffsetDecoration(4);
        pokemon_list_recyclerview.addItemDecoration(itemOffsetDecoration);

        //Setup SearchBar
        searchBar = (MaterialSearchBar)view.findViewById(R.id.search_bar);
        searchBar.setHint("Enter Pokemon name");
        searchBar.setCardViewElevation(10);
        searchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<String> suggest = new ArrayList<>();
                for(String search:last_suggest){
                    if(search.toLowerCase().contains(searchBar.getText().toLowerCase()))
                        suggest.add(search);
                }
                searchBar.setLastSuggestions(suggest);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        searchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if(!enabled)
                    pokemon_list_recyclerview.setAdapter(adapter);
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                startSearch(text);
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });

        fetchData();

        return view;
    }

    private void startSearch(CharSequence text) {
        if(Common.commonPokemonList.size() > 0){
            List<Pokemon> result = new ArrayList<>();
            for(Pokemon pokemon:Common.commonPokemonList)
                if (pokemon.getName().toLowerCase().contains(text.toString().toLowerCase()))
                    result.add(pokemon);
                search_adapter = new PokemonListAdapter(getActivity(),result);
                pokemon_list_recyclerview.setAdapter(search_adapter);

        }
    }

    private void fetchData() {
        compositeDisposable.add(iPokemonDex.getListPokemon()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Pokedex>() {
                    @Override
                    public void accept(Pokedex pokedex) throws Exception {
                        Common.commonPokemonList = pokedex.getPokemon();
                        adapter = new PokemonListAdapter(getActivity(),Common.commonPokemonList);
                        //pokemon_list_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
                        pokemon_list_recyclerview.setAdapter(adapter);
                        last_suggest.clear();
                        adapter.notifyDataSetChanged();
                        for(Pokemon pokemon:Common.commonPokemonList)
                            last_suggest.add(pokemon.getName());
                        searchBar.setVisibility(View.VISIBLE);
                        searchBar.setLastSuggestions(last_suggest);
                    }
                }));

    }


}
