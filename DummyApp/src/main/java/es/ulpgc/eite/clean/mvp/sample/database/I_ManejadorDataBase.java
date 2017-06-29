package es.ulpgc.eite.clean.mvp.sample.database;

/**
 * Created by Carlos Espacios on 29/06/2017.
 */

public interface I_ManejadorDataBase {

    //TipoDeComida
    String getNombreTipoDeComida (int tipoDeComida);

    //platos
    int [] getListaIdPlatos (int tipoDeComida);
    String[] getNombresByArrayIdPlatos(int[] ids);

    //plato
    String getRecetaPlato(int idPlato);
    String getNombrePlato(int idPlato);
    String getImagenPlato(int idPlato);
    Boolean isInAssetsPlato(int idPlato);
}
