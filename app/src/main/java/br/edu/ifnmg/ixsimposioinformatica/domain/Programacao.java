package br.edu.ifnmg.ixsimposioinformatica.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by murilo on 14/05/16.
 */
public class Programacao implements Parcelable{
    private String horario;
    private String conteudo;
    private String palestrante;
    private String detalhe;
    private String urlLattes;
    private String urlLattes2;
    private boolean favorito;

    public static Creator<Programacao> getCREATOR() {
        return CREATOR;
    }

    public Programacao(){
    }

    protected Programacao(Parcel in) {
        horario = in.readString();
        conteudo = in.readString();
        palestrante = in.readString();
        detalhe = in.readString();
        urlLattes = in.readString();
        urlLattes2 = in.readString();
        favorito = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(horario);
        dest.writeString(conteudo);
        dest.writeString(palestrante);
        dest.writeString(detalhe);
        dest.writeString(urlLattes);
        dest.writeString(urlLattes2);
        dest.writeByte((byte) (favorito ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Programacao> CREATOR = new Creator<Programacao>() {
        @Override
        public Programacao createFromParcel(Parcel in) {
            return new Programacao(in);
        }

        @Override
        public Programacao[] newArray(int size) {
            return new Programacao[size];
        }
    };

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getPalestrante() {
        return palestrante;
    }

    public void setPalestrante(String palestrante) {
        this.palestrante = palestrante;
    }

    public String getDetalhe() {
        return detalhe;
    }

    public void setDetalhe(String detalhe) {
        this.detalhe = detalhe;
    }

    public String getUrlLattes() {
        return urlLattes;
    }

    public void setUrlLattes(String urlLattes) {
        this.urlLattes = urlLattes;
    }

    public String getUrlLattes2() {
        return urlLattes2;
    }

    public void setUrlLattes2(String urlLattes2) {
        this.urlLattes2 = urlLattes2;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }
}
