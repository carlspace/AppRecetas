package es.ulpgc.eite.clean.mvp.sample.inicial;

import android.content.Context;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.Model;
import es.ulpgc.eite.clean.mvp.Presenter;

/**
 * Created by Carlos Espacios on 26/06/2017.
 */

public interface Inicial {


    ///////////////////////////////////////////////////////////////////////////////////
    // State /////////////////////////////////////////////////////////////////////////

    interface ToInicial {
        void onScreenStarted();
        void setToolbarVisibility(boolean visible);

    }

    interface InicialTo {
        Context getManagedContext();
        void destroyView();
        boolean isToolbarVisible();
        int getNumeroBotonPresionado();
    }

    ///////////////////////////////////////////////////////////////////////////////////
    // Screen ////////////////////////////////////////////////////////////////////////

    /**
     * Methods offered to VIEW to communicate with PRESENTER
     */
    interface ViewToPresenter extends Presenter<Inicial.PresenterToView> {
        void onButtonClicked1();
        void onButtonClicked2();
        void onButtonClicked3();
        void onButtonClicked4();
    }

    /**
     * Required VIEW methods available to PRESENTER
     */
    interface PresenterToView extends ContextView {
        void finishScreen();
        void hideToolbar();
        void setLabel1(String txt);
        void setLabel2(String txt);
        void setLabel3(String txt);
        void setLabel4(String txt);
    }

    /**
     * Methods offered to MODEL to communicate with PRESENTER
     */
    interface PresenterToModel extends Model<Inicial.ModelToPresenter> {



        String getTipoComidaBtn4();

        String getTipoComidaBtn3();

        String getTipoComidaBtn2();

        String getTipoComidaBtn1();
    }

    /**
     * Required PRESENTER methods available to MODEL
     */
    interface ModelToPresenter {

    }
}


