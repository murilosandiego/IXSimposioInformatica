package br.edu.ifnmg.ixsimposioinformatica.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.github.ivbaranov.mfb.MaterialFavoriteButton;

import br.edu.ifnmg.ixsimposioinformatica.R;
import br.edu.ifnmg.ixsimposioinformatica.domain.Programacao;
import br.edu.ifnmg.ixsimposioinformatica.fragment.ProgramacaoFragment;

public class ProgramacaoDetalhadaActivity extends AppCompatActivity {

    private Programacao programacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programacao_detalhada);


        if (savedInstanceState != null) {
            programacao = savedInstanceState.getParcelable("programacao");
        } else {
            if (getIntent() != null && getIntent().getExtras() != null && getIntent().getExtras().getParcelable("programacao") != null) {
                programacao = getIntent().getExtras().getParcelable("programacao");
            } else {
                Toast.makeText(this, "Fail!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }

        if (programacao.getPalestrante() == null) {
            Toast.makeText(this, "Este evento não possui detalhes", Toast.LENGTH_SHORT).show();
            finish();
        } else {

        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Programação");
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);


        TextView txvTitulo = (TextView) findViewById(R.id.txvProgramacaoDetalhadaTitulo);
        txvTitulo.setText(programacao.getConteudo());

        TextView txvDetalhe = (TextView) findViewById(R.id.txvDetalhe);
        txvDetalhe.setText(programacao.getDetalhe());

        TextView txvHorario = (TextView) findViewById(R.id.txvHorario);
        txvHorario.setText(programacao.getHorario());

        TextView txvPalestrante = (TextView) findViewById(R.id.txvPalestrante);
        txvPalestrante.setText(programacao.getPalestrante());

//        MaterialFavoriteButton favorite = new MaterialFavoriteButton.Builder(this)
//                .create();
//
//        favorite.setOnFavoriteChangeListener(
//                new MaterialFavoriteButton.OnFavoriteChangeListener() {
//                    @Override
//                    public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
//                       if (buttonView.isFavorite() && favorite == true){
//                           Toast.makeText(getBaseContext(),"favorito",Toast.LENGTH_SHORT).show();
//                       }
//                    }
//                });
//        final ToggleButton imgFavorito = (ToggleButton) findViewById(R.id.imgFavorito);
//
//        if(programacao.isFavorito()){
//            imgFavorito.setChecked(true);
//            imgFavorito.setBackgroundDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.staron));
//        }else {
//            imgFavorito.setChecked(false);
//            imgFavorito.setBackgroundDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.staroff));
//        }
//
//        imgFavorito.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    imgFavorito.setBackgroundDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.staron));
//                    Toast.makeText(getBaseContext(),"Favorito",Toast.LENGTH_SHORT).show();
//                    programacao.setFavorito(true);
//                } else {
//                    imgFavorito.setBackgroundDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.staroff));
//                    Toast.makeText(getBaseContext(),"Não Favorito",Toast.LENGTH_SHORT).show();
//                    programacao.setFavorito(false);
//                }
//            }
//
//        });

    }
//
//    private void saveState(boolean isFavourite) {
//        SharedPreferences aSharedPreferences = this.getSharedPreferences(
//                "Favourite", Context.MODE_PRIVATE);
//        SharedPreferences.Editor aSharedPreferencesEdit = aSharedPreferences
//                .edit();
//        aSharedPreferencesEdit.putBoolean("State", isFavourite);
//        aSharedPreferencesEdit.commit();
//    }
//
//    private boolean readState() {
//        SharedPreferences aSharedPreferences = this.getSharedPreferences(
//                "Favourite", Context.MODE_PRIVATE);
//        return aSharedPreferences.getBoolean("State", true);
//    }

    public void abrirLattes(View view) {
        String url = programacao.getUrlLattes();
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("programacao", programacao);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Id correspondente ao botão Up/Home da actionbar
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
