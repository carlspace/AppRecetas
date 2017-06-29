package es.ulpgc.eite.clean.mvp.sample.database;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Carlos Espacios on 29/06/2017.
 */

public class TipoDeComida extends RealmObject {

    @PrimaryKey
    private int id;
    private String nombreTipoDeComida;
    private String imagen;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoDeComida() {
        return nombreTipoDeComida;
    }

    public void setTipoDeComida(String nombreTipoDeComida) {
        this.nombreTipoDeComida = nombreTipoDeComida;
    }


    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
