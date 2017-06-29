package es.ulpgc.eite.clean.mvp.sample.database;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Carlos Espacios on 29/06/2017.
 */

public class ManejadorDataBase implements I_ManejadorDataBase {
   private static ManejadorDataBase ourInstance;
    private Realm realm;

    public static synchronized ManejadorDataBase getInstance() {
        if(ourInstance == null){
            ourInstance = new ManejadorDataBase();
        }
        return ourInstance;
    }

    private ManejadorDataBase() {
        realm = Realm.getDefaultInstance();
        initBaseDeDatos();
    }




    //Getters de platos, plato y tipoDeComida
    ///////////////////////////////POSIBLE FALLO AQUIIIIII!!!!!!!!!!!
    @Override
    public String getNombreTipoDeComida(int tipoDeComida) {
        RealmResults<TipoDeComida> result = realm.where(TipoDeComida.class).equalTo("id",tipoDeComida).findAll();
        return result.get(0).getTipoDeComida();

    }

    @Override
    public int[] getListaIdPlatos(int tipoDeComida) {
        return new int[0];
    }

    @Override
    public String[] getNombresByArrayIdPlatos(int[] ids) {
        return new String[0];
    }

    @Override
    public String getRecetaPlato(int idPlato) {
        return null;
    }

    @Override
    public String getNombrePlato(int idPlato) {
        return null;
    }

    @Override
    public String getImagenPlato(int idPlato) {
        return null;
    }

    @Override
    public Boolean isInAssetsPlato(int idPlato) {
        return null;
    }

    //Datos que se inicializaran con la appp

    public void initBaseDeDatos(){

        if(realm.isEmpty()) {


        }
        }
}
