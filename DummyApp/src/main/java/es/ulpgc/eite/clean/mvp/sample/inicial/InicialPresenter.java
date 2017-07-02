package es.ulpgc.eite.clean.mvp.sample.inicial;
import android.content.Context;
import android.util.Log;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.GenericPresenter;
import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.sample.app.Mediator;
import es.ulpgc.eite.clean.mvp.sample.app.Navigator;


public class InicialPresenter extends GenericPresenter
<Inicial.PresenterToView, Inicial.PresenterToModel, Inicial.ModelToPresenter, InicialModel>
        implements Inicial.ViewToPresenter, Inicial.ModelToPresenter, Inicial.InicialTo, Inicial.ToInicial{

    private boolean toolbarVisible;
    private int numeroBotonPresionado;


    /**
     * Operation called during VIEW creation in {@link GenericActivity#onResume(Class, Object)}
     * Responsible to initialize MODEL.
     * Always call {@link GenericPresenter#onCreate(Class, Object)} to initialize the object
     * Always call  {@link GenericPresenter#setView(ContextView)} to save a PresenterToView reference
     *
     * @param view The current VIEW instance
     */
    @Override
    public void onCreate(Inicial.PresenterToView view) {
        super.onCreate(InicialModel.class, this);
        setView(view);
        Log.d(TAG, "calling onCreate()");

        Log.d(TAG, "calling startingInicialScreen()");
        Mediator app = (Mediator) getView().getApplication();
        app.startingInicialScreen(this);
    }

    /**
     * Operation called by VIEW after its reconstruction.
     * Always call {@link GenericPresenter#setView(ContextView)}
     * to save the new instance of PresenterToView
     *
     * @param view The current VIEW instance
     */
    @Override
    public void onResume(Inicial.PresenterToView view) {
        setView(view);
        Log.d(TAG, "calling onResume()");

        if(configurationChangeOccurred()) {
            inicializarVista();

            checkToolbarVisibility();

        }
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
    public void onButtonClicked1() {
        Log.d(TAG, "calling onButtonClicked1()");
        numeroBotonPresionado=1;
        Navigator app = (Navigator) getView().getApplication();
        app.goToPlatosScreen(this);

    }
    @Override
    public void onButtonClicked2() {
        Log.d(TAG, "calling onButtonClicked2()");
        numeroBotonPresionado=2;
        Navigator app = (Navigator) getView().getApplication();
        app.goToPlatosScreen(this);
    }

    @Override
    public void onButtonClicked3() {
        Log.d(TAG, "calling onButtonClicked3()");
        numeroBotonPresionado=3;
        Navigator app = (Navigator) getView().getApplication();
        app.goToPlatosScreen(this);
    }

    @Override
    public void onButtonClicked4() {
        Log.d(TAG, "calling onButtonClicked4()");
        numeroBotonPresionado=4;
        Navigator app = (Navigator) getView().getApplication();
        app.goToPlatosScreen(this);
    }


    ///////////////////////////////////////////////////////////////////////////////////
    // To Inicial //////////////////////////////////////////////////////////////////////

    @Override
    public void onScreenStarted() {
        Log.d(TAG, "calling onScreenStarted()");
        if(isViewRunning()) {
           inicializarVista();
        }
        checkToolbarVisibility();

    }

    @Override
    public void setToolbarVisibility(boolean visible) {
        toolbarVisible = visible;
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
    @Override
    public boolean isToolbarVisible() {
        return toolbarVisible;
    }

    @Override
    public int getNumeroBotonPresionado(){
        return numeroBotonPresionado;
    }
    ///////////////////////////////////////////////////////////////////////////////////

    private void checkToolbarVisibility(){
        Log.d(TAG, "calling checkToolbarVisibility()");
        if(isViewRunning()) {
            if (!toolbarVisible) {
                getView().hideToolbar();
            }
        }
    }


    private void inicializarVista() {
        getView().setLabel1(getModel().getTipoComidaBtn1());
        getView().setLabel2(getModel().getTipoComidaBtn2());
        getView().setLabel3(getModel().getTipoComidaBtn3());
        getView().setLabel4(getModel().getTipoComidaBtn4());

    }
}
