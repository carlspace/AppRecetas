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
    private int idTipoDeComida;
    private String imagen;
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


    public int getIdCategoria() {
        return idTipoDeComida;
    }

    public void setIdCategoria(int idCategoria) {
        this.idTipoDeComida = idTipoDeComida;
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

    public void setIsInAssetsPlato(Boolean inAssetsPlato) {
        isInAssetsPlato = isInAssetsPlato;
    }
}
