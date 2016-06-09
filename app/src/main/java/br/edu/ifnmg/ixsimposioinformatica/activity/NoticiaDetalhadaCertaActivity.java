package br.edu.ifnmg.ixsimposioinformatica.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import br.edu.ifnmg.ixsimposioinformatica.R;
import br.edu.ifnmg.ixsimposioinformatica.domain.Noticia;

public class NoticiaDetalhadaCertaActivity extends AppCompatActivity {

    private ViewGroup mRoot;
    private TextView txtConteudo;
    private Noticia noticia;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

             // TRANSITIONS
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ){
                /*Explode trans1 = new Explode();
                trans1.setDuration(3000);
                Fade trans2 = new Fade();
                trans2.setDuration(3000);

                getWindow().setEnterTransition( trans1 );
                getWindow().setReturnTransition( trans2 );*/

            TransitionInflater inflater = TransitionInflater.from( this );
            Transition transition = inflater.inflateTransition( R.transition.transitions );

            getWindow().setSharedElementEnterTransition(transition);

            Transition transition1 = getWindow().getSharedElementEnterTransition();
            transition1.addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionStart(Transition transition) {

                }

                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onTransitionEnd(Transition transition) {
                    TransitionManager.beginDelayedTransition(mRoot, new Slide());
                    txtConteudo.setVisibility( View.VISIBLE );
                }

                @Override
                public void onTransitionCancel(Transition transition) {

                }

                @Override
                public void onTransitionPause(Transition transition) {

                }

                @Override
                public void onTransitionResume(Transition transition) {

                }
            });
        }
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_noticia_detalhada_certa);

        if(savedInstanceState != null){
            noticia = savedInstanceState.getParcelable("noticia");
        }
        else {
            if (getIntent() != null && getIntent().getExtras() != null && getIntent().getExtras().getParcelable("noticia") != null) {
                noticia = getIntent().getExtras().getParcelable("noticia");
            } else {
                Toast.makeText(this, "Fail!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
//
//        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
//        mCollapsingToolbarLayout.setTitle(noticia.getTitulo() );


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(noticia.getTitulo());
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent minhaIntent = new Intent();
                minhaIntent.setAction(Intent.ACTION_SEND);
           //     minhaIntent.putExtra(Intent.EXTRA_SUBJECT, "Compartilhar");
                minhaIntent.putExtra(Intent.EXTRA_TEXT, noticia.getDescricao());
                minhaIntent.setType("text/plain");
                startActivity(minhaIntent);
            }
        });


        SimpleDraweeView imgNoticia = (SimpleDraweeView) findViewById(R.id.img_noticia_detalhada);

        Uri uri = Uri.parse(noticia.getUrlNoticia() );
        DraweeController dc = Fresco.newDraweeControllerBuilder()
                .setUri( uri )
                .setAutoPlayAnimations(true)
                .setOldController( imgNoticia.getController() )
                .build();

        imgNoticia.setController( dc );

        txtConteudo = (TextView) findViewById(R.id.txtConteudo);
        txtConteudo.setText(noticia.getConteudo());

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("noticia", noticia);
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
