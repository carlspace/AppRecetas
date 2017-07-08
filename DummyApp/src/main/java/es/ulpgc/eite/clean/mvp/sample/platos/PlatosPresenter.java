package es.ulpgc.eite.clean.mvp.sample.platos;


import android.content.Context;
import android.util.Log;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.GenericPresenter;
import es.ulpgc.eite.clean.mvp.sample.app.Mediator;
import es.ulpgc.eite.clean.mvp.sample.app.Navigator;

public class PlatosPresenter extends GenericPresenter
    <Platos.PresenterToView, Platos.PresenterToModel, Platos.ModelToPresenter, PlatosModel>
    implements Platos.ViewToPresenter, Platos.ModelToPresenter, Platos.PlatosTo, Platos.ToPlatos {


    private boolean toolbarVisible;
    private boolean buttonClicked;
    private boolean textVisible;
    private int idPlatoSeleccionado;
    private int posicionDeListaPlatosClicked;
    int posicionListaPlatoSeleccionado;

      /**
   * Operation called during VIEW creation in {@link GenericActivity#onResume(Class, Object)}
   * Responsible to initialize MODEL.
   * Always call {@link GenericPresenter#onCreate(Class, Object)} to initialize the object
   * Always call  {@link GenericPresenter#setView(ContextView)} to save a PresenterToView reference
   *
   * @param view The current VIEW instance
   */
  @Override
  public void onCreate(Platos.PresenterToView view) {
    super.onCreate(PlatosModel.class, this);
    setView(view);
    Log.d(TAG, "calling onCreate()");

    Log.d(TAG, "calling startingPlatosScreen()");
    Mediator app = (Mediator) getView().getApplication();
    app.startingPlatosScreen(this);
  }

  /**
   * Operation called by VIEW after its reconstruction.
   * Always call {@link GenericPresenter#setView(ContextView)}
   * to save the new instance of PresenterToView
   *
   * @param view The current VIEW instance
   */
  @Override
  public void onResume(Platos.PresenterToView view) {
    setView(view);
    Log.d(TAG, "calling onResume()");
    inicializarVista();
    if (configurationChangeOccurred()) {
      inicializarVista();
        Log.d(TAG, "ha pasado el segundo corte");

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
///

  ///////////////////////////////////////////////////////////////////////////////////
  // View To Presenter /////////////////////////////////////////////////////////////

  @Override
  public void inicializarVista() {
      Mediator app = (Mediator) getApplication();
      getView().actualizarListaPlatos(getModel().getPlatos(app.getTipoComidaPresionado()));
      getView().setTituloToolbar(getModel().getTipoComida(app.getTipoComidaPresionado()));
      getView().setPosicionLista(app.getPosicionPlatos());

  }

    @Override
    public void onButtonAddPlato() {
        Navigator app = (Navigator) getView().getApplication();
        app.goToAddPlatoScreen(this);
    }


    @Override
  public void onItemClickSelected(int pos) {
        Log.d(TAG,"posicion pulsada" + pos);
        Mediator mediator = (Mediator) getApplication();
        setPosicionDeListaPlatosClicked(pos);
        setIdPlatoSeleccionado(mediator.getTipoComidaPresionado(),pos);
        Navigator app = (Navigator) getView().getApplication();
        app.goToPlatoScreen(this);
    }



  ///////////////////////////////////////////////////////////////////////////////////
  // To Platos //////////////////////////////////////////////////////////////////////

  @Override
  public void onScreenStarted() {
    Log.d(TAG, "calling onScreenStarted()");
    if (isViewRunning()) {
      inicializarVista();
    }


    ///////////////////////////////////////////////////////////////////////////////////
    // Platos To //////////////////////////////////////////////////////////////////////



  }

    @Override
    public Context getManagedContext() {
        return getActivityContext();
    }

    @Override
    public void destroyView() {
        if(isViewRunning()) {
            getView().finishScreen();
        }
    }

    @Override
    public int getPosicionDeListaPlatosClicked() {
        return posicionDeListaPlatosClicked;
    }

    @Override
    public int getIdPlatoSeleccionado() {
        return idPlatoSeleccionado;
    }

    @Override
    public int getPosicionListaPlatoSeleccionado() {

        return posicionListaPlatoSeleccionado;
    }

    public void setPosicionDeListaPlatosClicked(int posicionDeListaPlatosClicked) {
        this.posicionDeListaPlatosClicked = posicionDeListaPlatosClicked;
    }
    public void setIdPlatoSeleccionado(int idTipoComida, int posicionDeListaPlatosClicked){
        this.idPlatoSeleccionado = getModel().getIdPlatoSeleccionado(idTipoComida,posicionDeListaPlatosClicked);
    }
}
