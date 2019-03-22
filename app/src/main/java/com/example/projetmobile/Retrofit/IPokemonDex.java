package com.example.projetmobile.Retrofit;

import com.example.projetmobile.Model.Pokedex;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IPokemonDex {
    @GET("pokedex.json")

    Observable<Pokedex> getListPokemon();
}
