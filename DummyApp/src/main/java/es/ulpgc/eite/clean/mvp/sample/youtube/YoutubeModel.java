package es.ulpgc.eite.clean.mvp.sample.youtube;

import es.ulpgc.eite.clean.mvp.GenericModel;
import es.ulpgc.eite.clean.mvp.sample.database.I_ManejadorDataBase;
import es.ulpgc.eite.clean.mvp.sample.database.ManejadorDataBase;

/**
 * Created by Carlos Espacios on 04/10/2017.
 */

public class YoutubeModel extends GenericModel<youtube.ModelToPresenter>
        implements youtube.PresenterToModel{

    I_ManejadorDataBase miManejador;

    @Override
    public void onCreate(youtube.ModelToPresenter presenter) {
        super.onCreate(presenter);
        miManejador = ManejadorDataBase.getInstance();

    }

    @Override
    public String getNombrePlato(int id) {
        return miManejador.getNombrePlato(id);
    }

    @Override
    public String getEnlaceYoutube(int id) {
        return miManejador.getEnlaceYoutube(id);
    }
}
