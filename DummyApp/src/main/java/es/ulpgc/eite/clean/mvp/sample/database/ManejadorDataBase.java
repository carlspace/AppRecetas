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
    ///////////////////////////////POSIBLE FALLO AQUIIIIII!!!!!!!!!!! en el "id"
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




    //Metodos para añadir

    @Override
    public void addTipoDeComida(String nombre) {
        realm.beginTransaction();
        // Se incrementa el id
        Number currentIdNum = realm.where(TipoDeComida.class).max("id");
        int nextId;
        if(currentIdNum == null) {
            nextId = 1;
        } else {
            nextId = currentIdNum.intValue() + 1;
        }
        TipoDeComida tipoDeComida= realm.createObject(TipoDeComida.class,nextId);
        tipoDeComida.setTipoDeComida(nombre);
               realm.commitTransaction();
    }

    @Override
    public void addPlato(String nombre, String descripcion, int idTipoDeComida, String imagen, String enlaceYoutube, Boolean isInAssets) {
        realm.beginTransaction();
        Number currentIdNum = realm.where(Plato.class).max("id");
        int nextId;
        if(currentIdNum == null) {
            nextId = 1;
        } else {
            nextId = currentIdNum.intValue() + 1;
        }
        Plato plato= realm.createObject(Plato.class,nextId);
        plato.setNombre(nombre);
        plato.setDescripcion(descripcion);
        plato.setImagen(imagen);
        plato.setIdTipoDeComida(idTipoDeComida);
        plato.setEnlaceYoutbe(enlaceYoutube);
        plato.setIsInAssetsPlato(isInAssets);
        realm.commitTransaction();
    }


    //Datos que se inicializaran con la appp

    public void initBaseDeDatos(){

        if(realm.isEmpty()) {




            //Platos ensalada
            addTipoDeComida("Ensaladas");
                int idTipoDeComida=1;
                        //plato Cogollos de Tudela con anchoas y queso (id=1)
                        String nombrePlato = "";
                        String receta = "";
                        addPlato(nombrePlato, receta, idTipoDeComida, "", "",true);
                    //plato Cogollos de Tudela con anchoas y queso (id=1)
                 nombrePlato = "";
             receta = "";
            addPlato(nombrePlato, receta, idTipoDeComida, "", "",true);
            //plato Cogollos de Tudela con anchoas y queso (id=1)
             nombrePlato = "";
             receta = "";
            addPlato(nombrePlato, receta, idTipoDeComida, "", "",true);
            //plato Cogollos de Tudela con anchoas y queso (id=1)
             nombrePlato = "";
             receta = "";
            addPlato(nombrePlato, receta, idTipoDeComida, "", "",true);
            //plato Cogollos de Tudela con anchoas y queso (id=1)
             nombrePlato = "";
             receta = "";
            addPlato(nombrePlato, receta, idTipoDeComida, "", "",true);
            //plato Cogollos de Tudela con anchoas y queso (id=1)
             nombrePlato = "";
             receta = "";
            addPlato(nombrePlato, receta, idTipoDeComida, "", "",true);

            addTipoDeComida("Sopa");
            idTipoDeComida=2;

        }
        }
}
