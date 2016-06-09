package br.edu.ifnmg.ixsimposioinformatica.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import br.edu.ifnmg.ixsimposioinformatica.R;
import br.edu.ifnmg.ixsimposioinformatica.activity.InformacoesActivity;
import br.edu.ifnmg.ixsimposioinformatica.activity.PalestrantesActivity;
import br.edu.ifnmg.ixsimposioinformatica.activity.ProgramacaoActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.content_main,container,false);


        ImageButton imbInformacoes = (ImageButton) rootView.findViewById(R.id.imbInformacoes);
        imbInformacoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                InformacoesFragment fragment = new InformacoesFragment();
//                FragmentManager fm = getFragmentManager();
//                android.support.v4.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();
//                fragmentTransaction.replace(R.id.fragment_container, fragment);
//                fragmentTransaction.commit();

                startActivity(new Intent(getActivity(),InformacoesActivity.class));
            }
        });

        ImageButton imbProgramacao = (ImageButton) rootView.findViewById(R.id.imgProgramacao);
        imbProgramacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ProgramacaoFragment fragment = new ProgramacaoFragment();
//                FragmentManager fm = getFragmentManager();
//                android.support.v4.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();
//                fragmentTransaction.replace(R.id.fragment_container, fragment);
//                fragmentTransaction.commit();
                startActivity(new Intent(getActivity(), ProgramacaoActivity.class));
            }
        });

        ImageButton imbPalestrantes = (ImageButton) rootView.findViewById(R.id.imgPalestrante);
        imbPalestrantes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                PalestranteFragment fragment = new PalestranteFragment();
//                FragmentManager fm = getFragmentManager();
//                android.support.v4.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();
//                fragmentTransaction.replace(R.id.fragment_container, fragment);
//                fragmentTransaction.commit();

                startActivity(new Intent(getActivity(), PalestrantesActivity.class));
            }
        });

        return rootView;

    }



}
