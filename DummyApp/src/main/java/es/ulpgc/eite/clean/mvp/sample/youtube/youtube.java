package es.ulpgc.eite.clean.mvp.sample.youtube;

import android.content.Context;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.Model;
import es.ulpgc.eite.clean.mvp.Presenter;

/**
 * Created by Carlos Espacios on 04/10/2017.
 */

public interface youtube {



    interface ToYoutube {
        void onScreenStarted();

    }

    interface YoutubeTo {
        Context getManagedContext();
        void destroyView();
    }


    interface ViewToPresenter extends Presenter<PresenterToView> {
        void onButtonClicked();
        void inicializarVista();
    }


    interface PresenterToView extends ContextView {
        void finishScreen();
        void setNombrePlato(String txt);

    }



    interface PresenterToModel extends Model<ModelToPresenter> {
        String getNombrePlato(int id);
        String getEnlaceYoutube(int id);


    }



    interface ModelToPresenter {

    }
}
