package es.ulpgc.eite.clean.mvp.sample.youtube;

import android.content.Context;
import android.util.Log;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.GenericPresenter;
import es.ulpgc.eite.clean.mvp.sample.app.Mediator;
import es.ulpgc.eite.clean.mvp.sample.database.I_ManejadorDataBase;
import es.ulpgc.eite.clean.mvp.sample.database.ManejadorDataBase;

/**
 * Created by Carlos Espacios on 04/10/2017.
 */

public class ConfigYoutube extends GenericPresenter<Youtube.PresenterToView, Youtube.PresenterToModel, Youtube.ModelToPresenter, YoutubeModel>
        implements Youtube.ViewToPresenter, Youtube.ModelToPresenter, Youtube.YoutubeTo, Youtube.ToYoutube{

    I_ManejadorDataBase miManejador;
    // Google Console APIs developer clave
    public static final String DEVELOPER_KEY = "AIzaSyCJ4yOn8M9R-nAM5Lb9GSL5KrbVqo_AL00";

    // YouTube video id
    public  static String YOUTUBE_VIDEO_CODE = ConfigYoutube.cogerEnlace();

    @Override
    public void onCreate(Youtube.PresenterToView view) {
        super.onCreate(YoutubeModel.class, this);
        setView(view);
        Log.d(TAG, "calling onCreate()");



    }

    /**
     * Operation called by VIEW after its reconstruction.
     * Always call {@link GenericPresenter#setView(ContextView)}
     * to save the new instance of PresenterToView
     *
     * @param view The current VIEW instance
     */
    @Override
    public void onResume(Youtube.PresenterToView view) {
        setView(view);
        Log.d(TAG, "calling onResume()");

        inicializarVista();
    }

    /**
     * Helper method to inform Presenter that a onBackPressed event occurred
     * Called by {@link GenericActivity}
     */
    @Override
    public void onBackPressed() {
        Log.d(TAG, "calling onBackPressed()");
    }

    /**
     * Hook method called when the VIEW is being destroyed or
     * having its configuration changed.
     * Responsible to maintain MVP synchronized with Activity lifecycle.
     * Called by onDestroy methods of the VIEW layer, like: {@link GenericActivity#onDestroy()}
     *
     * @param isChangingConfiguration true: configuration changing & false: being destroyed
     */
    @Override
    public void onDestroy(boolean isChangingConfiguration) {
        super.onDestroy(isChangingConfiguration);
        Log.d(TAG, "calling onDestroy()");
    }


    ///////////////////////////////////////////////////////////////////////////////////
    // View To Presenter /////////////////////////////////////////////////////////////

    @Override
    public void onButtonClicked() {
        Log.d(TAG, "calling onButtonClicked()");
        Mediator app=(Mediator) getView().getApplication();
        int id=app.getIdPlatoSeleccionado();

        String nombrePlato = getModel().getNombrePlato(id);
    }

    @Override
    public void inicializarVista() {
        Mediator app = (Mediator) getView().getApplication();

        int id = app.getIdPlatoSeleccionado();
        getView().setNombrePlato(getModel().getNombrePlato(id));


    }

    public static String cogerEnlace(){
        Mediator app = (Mediator) getView().getApplication();
        int id = app.getIdPlatoSeleccionado();
        miManejador = ManejadorDataBase.getInstance();
        String enlace=miManejador.getEnlaceYoutube(id);
        return enlace;

    }




    ///////////////////////////////////////////////////////////////////////////////////
    // To Platos //////////////////////////////////////////////////////////////////////

    @Override
    public void onScreenStarted() {
        Log.d(TAG, "calling onScreenStarted()");
        if(isViewRunning()) {
            inicializarVista();
        }

    }



    ///////////////////////////////////////////////////////////////////////////////////
    // Platos To //////////////////////////////////////////////////////////////////////




    @Override
    public Context getManagedContext(){
        return getActivityContext();
    }

    @Override
    public void destroyView(){
        if(isViewRunning()) {
            getView().finishScreen();
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////



}
