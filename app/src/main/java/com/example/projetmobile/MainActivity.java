package com.example.projetmobile;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.view.InflateException;

import com.example.projetmobile.Adaptater.PokemonListAdapter;
import com.example.projetmobile.Common.Common;
import com.example.projetmobile.Model.Pokemon;
import com.example.projetmobile.PokemonList;


public class MainActivity extends AppCompatActivity{

    Toolbar toolbar;

    BroadcastReceiver showDetail = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Common.KEY_ENABLE_HOME)) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Enable Back Button on Toolbar
                getSupportActionBar().setDisplayShowHomeEnabled(true); //too

                Fragment detailFragment = PokemonDetail.getInstance();
                String num = intent.getStringExtra("num");
                Bundle bundle = new Bundle();
                bundle.putString("num",num);
                detailFragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.list_pokemon_fragment,detailFragment);
                fragmentTransaction.addToBackStack("detail");
                fragmentTransaction.commit();

                //Set pokemon name

                Pokemon pokemon = Common.findPokemonByNum(num);
                toolbar.setTitle(pokemon.getName());
            }
        }
    };

    BroadcastReceiver showEvolution = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Common.KEY_NUM_EVOLUTION)) {

                Fragment detailFragment = PokemonDetail.getInstance();
                Bundle bundle = new Bundle();
                String num = intent.getStringExtra("num");
                bundle.putString("num",num);
                detailFragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.remove(detailFragment);
                fragmentTransaction.replace(R.id.list_pokemon_fragment,detailFragment);
                fragmentTransaction.addToBackStack("detail");
                fragmentTransaction.commit();

                //Set pokemon name

                Pokemon pokemon = Common.findPokemonByNum(num);
                toolbar.setTitle(pokemon.getName());
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.list_pokemon_fragment, new PokemonList())
                .commit();
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("POKEMON LIST");
        setSupportActionBar(toolbar);

        //Register Broadcast
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(showDetail,new IntentFilter(Common.KEY_ENABLE_HOME));
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(showEvolution,new IntentFilter(Common.KEY_NUM_EVOLUTION));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                toolbar.setTitle("POKEMON LIST");

                getSupportFragmentManager().popBackStack("detail", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                getSupportActionBar().setDisplayShowHomeEnabled(false);
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                break;
                default:
                    break;
        }
        return true     ;
    }
}
