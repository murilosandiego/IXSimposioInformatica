package br.edu.ifnmg.ixsimposioinformatica.adapter;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;


import java.util.ArrayList;

import br.edu.ifnmg.ixsimposioinformatica.R;
import br.edu.ifnmg.ixsimposioinformatica.domain.Noticia;

/**
 * Created by murilo on 15/04/16.
 */
public class NoticiasAdapter extends BaseAdapter {

    private static ArrayList<Noticia> listaDeNoticias;

    private LayoutInflater mInflater;
    Context context;

    public NoticiasAdapter(Context photosFragment, ArrayList<Noticia> results){
        this.listaDeNoticias = results;
        mInflater = LayoutInflater.from(photosFragment);
        context = photosFragment;
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return listaDeNoticias.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return listaDeNoticias.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.item_noticia, null);
            holder = new ViewHolder();
//            holder.txtname = (TextView) convertView.findViewById(R.id.lv_contact_item_name);
//            holder.txtphone = (TextView) convertView.findViewById(R.id.lv_contact_item_phone);

            holder.imgNoticia = (SimpleDraweeView) convertView.findViewById(R.id.img_noticia);
            holder.txtTitulo = (TextView) convertView.findViewById(R.id.txtTitulo);
            holder.txtDescricao = (TextView) convertView.findViewById(R.id.txtDescricao);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

//        holder.txtname.setText(listUrlFotos.get(position).getName());
//        holder.txtphone.setText(listUrlFotos.get(position).getPhone());

//        Uri uri = Uri.parse(listUrlFotos.get(position));
//        SimpleDraweeView draweeView = (SimpleDraweeView) holder.imgNoticia;
//        draweeView.setImageURI(uri);

        ControllerListener listener = new BaseControllerListener(){
            @Override
            public void onFinalImageSet(String id, Object imageInfo, Animatable animatable) {
                super.onFinalImageSet(id, imageInfo, animatable);
                Log.i("LOG", "onFinalImageSet");
            }

            @Override
            public void onFailure(String id, Throwable throwable) {
                super.onFailure(id, throwable);
                Log.i("LOG", "onFailure");
            }

            @Override
            public void onIntermediateImageFailed(String id, Throwable throwable) {
                super.onIntermediateImageFailed(id, throwable);
                Log.i("LOG", "onIntermediateImageFailed");
            }

            @Override
            public void onIntermediateImageSet(String id, Object imageInfo) {
                super.onIntermediateImageSet(id, imageInfo);
                Log.i("LOG", "onIntermediateImageSet");
            }

            @Override
            public void onRelease(String id) {
                super.onRelease(id);
                Log.i("LOG", "onRelease");
            }

            @Override
            public void onSubmit(String id, Object callerContext) {
                super.onSubmit(id, callerContext);
                Log.i("LOG", "onSubmit");
            }
        };

        Uri uri = Uri.parse(listaDeNoticias.get(position).getUrlNoticia());
        DraweeController dc = Fresco.newDraweeControllerBuilder()
                .setUri( uri )
                .setTapToRetryEnabled(true)
                .setControllerListener( listener )
                .setOldController( holder.imgNoticia.getController() )
                .build();

        RoundingParams rp = RoundingParams.fromCornersRadii(3, 3, 0, 0);
        holder.imgNoticia.setImageURI(uri);
        holder.imgNoticia.getHierarchy().setRoundingParams( rp );

        holder.txtTitulo.setText(listaDeNoticias.get(position).getTitulo());
        holder.txtDescricao.setText(listaDeNoticias.get(position).getDescricao());

        return convertView;
    }

    static class ViewHolder{
        SimpleDraweeView imgNoticia;
        TextView txtTitulo;
        TextView txtDescricao;
    }
}
