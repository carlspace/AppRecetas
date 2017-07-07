package es.ulpgc.eite.clean.mvp.sample.addPlato;

import es.ulpgc.eite.clean.mvp.GenericModel;
import es.ulpgc.eite.clean.mvp.sample.database.I_ManejadorDataBase;
import es.ulpgc.eite.clean.mvp.sample.database.ManejadorDataBase;

/**
 * Created by Erik on 04/07/2017.
 */

public class AddPlatoModel extends GenericModel<AddPlato.ModelToPresenter>
        implements AddPlato.PresenterToModel {

    I_ManejadorDataBase miManejador;

    /**
     * Method that recovers a reference to the PRESENTER
     * You must ALWAYS call {@link super#onCreate(Object)} here
     *
     * @param presenter Presenter interface
     */
    @Override
    public void onCreate(AddPlato.ModelToPresenter presenter) {
        super.onCreate(presenter);

        miManejador = ManejadorDataBase.getInstance();
    }

    /**
     * Called by layer PRESENTER when VIEW pass for a reconstruction/destruction.
     * Usefull for kill/stop activities that could be running on the background Threads
     *
     * @param isChangingConfiguration Informs that a change is occurring on the configuration
     */
    @Override
    public void onDestroy(boolean isChangingConfiguration) {


    }


    @Override
    public void  addPlatoSinImagen(String nombre,String receta,int idTipoComida){
        String imgDefault ="";
        miManejador.addPlato(nombre,receta,idTipoComida,imgDefault,true);

    }

    @Override
    public void  addPlatoConImagen(String nombre, String receta, int idTipoComida, String PathImagen){
        miManejador.addPlato(nombre,receta,idTipoComida,PathImagen,false);

    }


}


