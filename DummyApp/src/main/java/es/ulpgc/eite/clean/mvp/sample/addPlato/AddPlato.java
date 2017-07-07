package es.ulpgc.eite.clean.mvp.sample.addPlato;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.Model;
import es.ulpgc.eite.clean.mvp.Presenter;

/**
 * Created by Erik on 04/07/2017.
 */

interface AddPlato {

    ///////////////////////////////////////////////////////////////////////////////////
    // State ////////////////////////////////////////////////////////////////////////

    interface ToAddAutor {
        void onScreenStarted();

    }


    interface ToAddPlato{
        void onScreenStarted();

    }
    interface AddPlatoTo {
        Context getManagedContext();
        void destroyView();
        void setImagenSelecionada();
    }

    ///////////////////////////////////////////////////////////////////////////////////
    // Screen ////////////////////////////////////////////////////////////////////////

    /**
     * Methods offered to VIEW to communicate with PRESENTER
     */
    interface ViewToPresenter extends Presenter<PresenterToView> {

        ///////////////////////////////////////////////////////////////////////////////////
        // View To Presenter /////////////////////////////////////////////////////////////
        void onButtonAddImagenClicked();
        void onButtonDoneClicked();
        AddPlatoPresenter.MyObserver getObserver();
    }

    /**
     * Required VIEW methods available to PRESENTER
     */
    interface PresenterToView extends ContextView {

        void finishScreen();
        void startMenu(Intent intent);
        String getNombre();
        String getReceta();
        void setTitle(String txt);
        void showToast(String txt);
        void setImagen(Bitmap imagen);
        void showImagen();
        void hideImagen();
    }

    /**
     * Methods offered to MODEL to communicate with PRESENTER
     */
    interface PresenterToModel extends Model<ModelToPresenter> {
        void  addPlatoSinImagen(String nombre, String receta, int idPlato);
        void  addPlatoConImagen(String nombre, String descripcion, int idPlato, String PathImagen);
    }

    /**
     * Required PRESENTER methods available to MODEL
     */
    interface ModelToPresenter {

    }
}


