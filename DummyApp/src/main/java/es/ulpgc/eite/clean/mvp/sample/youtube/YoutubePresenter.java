package es.ulpgc.eite.clean.mvp.sample.youtube;

import android.content.Context;
import android.util.Log;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.GenericPresenter;
import es.ulpgc.eite.clean.mvp.sample.app.Mediator;

/**
 * Created by Carlos Espacios on 04/10/2017.
 */

public class YoutubePresenter extends GenericPresenter<youtube.PresenterToView, youtube.PresenterToModel, youtube.ModelToPresenter, YoutubeModel>
        implements youtube.ViewToPresenter, youtube.ModelToPresenter, youtube.YoutubeTo, youtube.ToYoutube{
    /**
     * Operation called during VIEW creation in {@link GenericActivity#onResume(Class, Object)}
     * Responsible to initialize MODEL.
     * Always call {@link GenericPresenter#onCreate(Class, Object)} to initialize the object
     * Always call  {@link GenericPresenter#setView(ContextView)} to save a PresenterToView reference
     *
     * @param view The current VIEW instance
     */
    @Override
    public void onCreate(youtube.PresenterToView view) {
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
    public void onResume(youtube.PresenterToView view) {
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
