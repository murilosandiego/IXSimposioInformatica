package br.edu.ifnmg.ixsimposioinformatica.adapter;

import android.content.Context;

import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;


import java.util.ArrayList;

import br.edu.ifnmg.ixsimposioinformatica.R;

import br.edu.ifnmg.ixsimposioinformatica.domain.Programacao;

/**
 * Created by murilo on 14/05/16.
 */
public class ProgramacaoAdapter extends BaseAdapter {

    private static ArrayList<Programacao> listaProgramacao;

    private LayoutInflater mInflater;
    Context context;

    public ProgramacaoAdapter(Context photosFragment, ArrayList<Programacao> results) {
        this.listaProgramacao = results;
        mInflater = LayoutInflater.from(photosFragment);
        context = photosFragment;
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return listaProgramacao.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return listaProgramacao.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_programacao, null);
            holder = new ViewHolder();

            holder.txtHorario = (TextView) convertView.findViewById(R.id.txtHorario);
            holder.txtConteudo = (TextView) convertView.findViewById(R.id.txtProgramacao);
            holder.txtPalestrante = (TextView) convertView.findViewById(R.id.txtPalestrante);
         //   holder.imgFavorito = (ToggleButton) convertView.findViewById(R.id.imgFavorito);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtPalestrante.setText(listaProgramacao.get(position).getPalestrante());
        holder.txtConteudo.setText(listaProgramacao.get(position).getConteudo());
        holder.txtHorario.setText(listaProgramacao.get(position).getHorario());

//        holder.imgFavorito.setChecked(false);
//        holder.imgFavorito.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.staroff));
//        holder.imgFavorito.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked)
//                    holder.imgFavorito.setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.staron));
//                else
//                    holder.imgFavorito.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.staroff));
//            }
//        });


        return convertView;
    }



    static class ViewHolder {
        TextView txtHorario;
        TextView txtConteudo;
        TextView txtPalestrante;
        ToggleButton imgFavorito;
    }
}
