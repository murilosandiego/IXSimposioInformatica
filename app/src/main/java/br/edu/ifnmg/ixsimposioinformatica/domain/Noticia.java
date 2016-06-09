package br.edu.ifnmg.ixsimposioinformatica.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by murilo on 16/04/16.
 */
public class Noticia implements Parcelable {


    private String urlNoticia;
    private String titulo;
    private String descricao;
    private String conteudo;
    private String id;

    public Noticia(){

    }

    protected Noticia(Parcel in) {
        urlNoticia = in.readString();
        titulo = in.readString();
        descricao = in.readString();
        conteudo = in.readString();
        id = in.readString();
    }

    public static final Creator<Noticia> CREATOR = new Creator<Noticia>() {
        @Override
        public Noticia createFromParcel(Parcel in) {
            return new Noticia(in);
        }

        @Override
        public Noticia[] newArray(int size) {
            return new Noticia[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getUrlNoticia() {
        return urlNoticia;
    }

    public void setUrlNoticia(String urlNoticia) {
        this.urlNoticia = urlNoticia;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(urlNoticia);
        dest.writeString(titulo);
        dest.writeString(descricao);
        dest.writeString(conteudo);
        dest.writeString(id);
    }
}
