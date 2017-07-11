package es.ulpgc.eite.clean.mvp.sample.database;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Carlos Espacios on 28/06/2017.
 */

public class Plato extends RealmObject {

    @PrimaryKey
    private int id;
    private String nombre;
    private String descripcion;
    private String ingredientes;
    private int idTipoComida;
    private String imagen;
    private String enlaceYoutbe;
    private Boolean isInAssetsPlato;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public int getIdTipoComida() {
        return idTipoComida;
    }

    public void setIdTipoComida(int idTipoComida) {
        this.idTipoComida = idTipoComida;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Boolean getIsInAssetsPlato() {
        return isInAssetsPlato;
    }

    public String getEnlaceYoutbe() {
        return enlaceYoutbe;
    }

    public void setEnlaceYoutbe(String enlaceYoutbe) {
        this.enlaceYoutbe = enlaceYoutbe;
    }

    public void setIsInAssetsPlato(Boolean inAssetsPlato) {
        isInAssetsPlato = inAssetsPlato;
    }


    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }
}
