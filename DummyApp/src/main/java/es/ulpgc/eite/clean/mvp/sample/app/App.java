package es.ulpgc.eite.clean.mvp.sample.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import es.ulpgc.eite.clean.mvp.sample.inicial.Inicial;
import es.ulpgc.eite.clean.mvp.sample.inicial.InicialView;
import es.ulpgc.eite.clean.mvp.sample.platos.PlatosView;


public class App extends Application implements Mediator, Navigator {


  private InicialState toInicialState, inicialToState;
  private PlatosState toPlatosState, platosToState;

  @Override
  public void onCreate() {
    super.onCreate();
    toInicialState = new InicialState();
    toInicialState.toolbarVisibility = false;

  }

  ///////////////////////////////////////////////////////////////////////////////////
  // Mediator //////////////////////////////////////////////////////////////////////

  @Override
  public void startingInicialScreen(Inicial.ToInicial presenter){

    presenter.onScreenStarted();
  }


  ///////////////////////////////////////////////////////////////////////////////////
  // Navigator /////////////////////////////////////////////////////////////////////

  @Override
  public void goToPlatosScreen(Inicial.InicialTo presenter) {
    Log.d("APP", "goToPlatosScreen() has pulsado el boton: "+ presenter. getNumeroBotonPresionado());
    inicialToState = new InicialState();
    inicialToState.numeroBotonPresionado= presenter.getNumeroBotonPresionado();

    platosToState = new PlatosState();
    platosToState.posicionDeListaPlatosClicked = 0;
    Context view = presenter.getManagedContext();

    if (view != null) {
      view.startActivity(new Intent(view, PlatosView.class));

    }

  }
  @Override
  public void goToNextScreen(Inicial.InicialTo presenter) {
    Log.d("APP", "goToPlatosScreen() has pulsado el boton: "+ presenter.getNumeroBotonPresionado());
    inicialToState = new InicialState();
    inicialToState.toolbarVisibility = presenter.isToolbarVisible();


    Context view = presenter.getManagedContext();
    if (view != null) {
      view.startActivity(new Intent(view, InicialView.class));
      presenter.destroyView();
    }

  }
  private class InicialState{
    boolean toolbarVisibility;
    int numeroBotonPresionado;
  }
  @Override
  public int getNumeroBotonPresionado(){
    return inicialToState.numeroBotonPresionado;
  }


  ///////////////////////////////////////////////////////////////////////////////////
  // State /////////////////////////////////////////////////////////////////////////

  private class PlatosState{
    int posicionDeListaPlatosClicked;
    int idPlatoSeleccionado;

  }

}
