package es.ulpgc.eite.clean.mvp.sample.database;

/**
 * Created by Carlos Espacios on 29/06/2017.
 */

public interface  I_ManejadorDataBase {

    //TipoDeComida
    String getTipoComida (int idTipoComida);

    //platos
    int [] getListaIdPlatos (int tipoComida);
    String[] getNombresByArrayIdPlatos(int[] ids);

    //plato
    String getRecetaPlato(int idPlato);
    String getNombrePlato(int idPlato);
    String getImagenPlato(int idPlato);
    Boolean isInAssetsPlato(int idPlato);


    void addTipoComida(String nombre);
    void addPlato(String nombre, String descripcion, int idTipoComida, String imagen, String enlaceYoutube, Boolean isInAssets);


}
