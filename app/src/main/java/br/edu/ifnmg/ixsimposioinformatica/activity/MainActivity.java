package br.edu.ifnmg.ixsimposioinformatica.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import br.edu.ifnmg.ixsimposioinformatica.fragment.NoticiasFragment;
import br.edu.ifnmg.ixsimposioinformatica.fragment.ProgramacaoFragment;
import br.edu.ifnmg.ixsimposioinformatica.R;
import br.edu.ifnmg.ixsimposioinformatica.fragment.InformacoesFragment;
import br.edu.ifnmg.ixsimposioinformatica.fragment.MainFragment;
import br.edu.ifnmg.ixsimposioinformatica.fragment.PalestranteFragment;
import br.edu.ifnmg.ixsimposioinformatica.fragment.SobreFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static final String STATE_SCORE = "playerScore";
    static final String STATE_LEVEL = "playerLevel";
    private int mCurrentScore = 1;
    private int mCurrentLevel = 1;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            Log.e("LOG", "onCreateActivity");
            MainFragment fragment = new MainFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_share) {

            Intent minhaIntent = new Intent();
            minhaIntent.setAction(Intent.ACTION_SEND);
            minhaIntent.putExtra(Intent.EXTRA_SUBJECT, "IX Simposio de Informatica - IFNMG Campus Januaria");
            minhaIntent.putExtra(Intent.EXTRA_TEXT, "http://simposioinformatica.ifnmg.edu.br/");
            minhaIntent.setType("text/plain");
            startActivity(minhaIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_inicio) {
            MainFragment fragment = new MainFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
            toolbar.setTitle(R.string.app_name);
        } else if (id == R.id.nav_informacoes) {
            InformacoesFragment fragment = new InformacoesFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
            toolbar.setTitle("Informações");
        } else if (id == R.id.nav_palestrantes) {
            PalestranteFragment fragment = new PalestranteFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
            toolbar.setTitle("Palestrantes");
        } else if (id == R.id.nav_programacao) {
            ProgramacaoFragment fragment = new ProgramacaoFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
            toolbar.setTitle("Programação");
        } else if (id == R.id.nav_noticias) {
            NoticiasFragment fragment = new NoticiasFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
            toolbar.setTitle("Notícias");
        } else if (id == R.id.nav_share) {
            Intent minhaIntent = new Intent();
            minhaIntent.setAction(Intent.ACTION_SEND);
            minhaIntent.putExtra(Intent.EXTRA_TEXT, "http://simposioinformatica.ifnmg.edu.br/");
            minhaIntent.setType("text/plain");
            startActivity(minhaIntent);

        } else if (id == R.id.nav_site) {
            String url = "http://simposioinformatica.ifnmg.edu.br/";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        } else if (id == R.id.nav_inscricao) {
            String url = "http://sge.ifnmg.edu.br/GerenciamentoEventos/";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        } else if (id == R.id.nav_sobre) {
            SobreFragment fragment = new SobreFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
            toolbar.setTitle("Sobre este App");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(STATE_SCORE, mCurrentScore);
        savedInstanceState.putInt(STATE_LEVEL, mCurrentLevel);

    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Sempre chame a superclasse para que possa
        // restaurar a hierarquia da view
        super.onRestoreInstanceState(savedInstanceState);

        // Restaura estados membros da instância salva
        mCurrentScore = savedInstanceState.getInt(STATE_SCORE);
        mCurrentLevel = savedInstanceState.getInt(STATE_LEVEL);
    }

//    @Override
//    public void onBackPressed() {
//
//        int count = getFragmentManager().getBackStackEntryCount();
//
//        if (count == 0) {
//            super.onBackPressed();
//            startActivity(new Intent(this,MainActivity.class));
//        } else {
//            getFragmentManager().popBackStack();
//        }
//
//    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
