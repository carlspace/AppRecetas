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
}
