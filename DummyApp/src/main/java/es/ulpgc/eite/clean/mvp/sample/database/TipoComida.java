package es.ulpgc.eite.clean.mvp.sample.database;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Carlos Espacios on 29/06/2017.
 */

public class TipoComida extends RealmObject {

    @PrimaryKey
    private int id;
    private String nombreTipoComida;
    private String imagen;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreTipoComida() {
        return nombreTipoComida;
    }

    public void setNombreTipoComida(String nombreTipoComida) {
        this.nombreTipoComida = nombreTipoComida;
    }


    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }


}
