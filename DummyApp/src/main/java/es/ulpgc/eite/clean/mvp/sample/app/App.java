package es.ulpgc.eite.clean.mvp.sample.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import es.ulpgc.eite.clean.mvp.sample.inicial.Inicial;
import es.ulpgc.eite.clean.mvp.sample.platos.Platos;
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
  public void startingInicialScreen(Inicial.ToInicial presenter) {

    presenter.onScreenStarted();
  }


  ///////////////////////////////////////////////////////////////////////////////////
  // Navigator /////////////////////////////////////////////////////////////////////

  @Override
  public void goToPlatosScreen(Inicial.InicialTo presenter) {
    Log.d("APP", "goToPlatosScreen() has pulsado el boton: " + presenter.getNumeroBotonPresionado());
    inicialToState = new InicialState();
    inicialToState.numeroBotonPresionado = presenter.getNumeroBotonPresionado();

    platosToState = new PlatosState();
    platosToState.posicionListaPlatosPulsada = 0;
    Context view = presenter.getManagedContext();

    if (view != null) {
      view.startActivity(new Intent(view, PlatosView.class));

    }

  }

  private class InicialState {
    boolean toolbarVisibility;
    int numeroBotonPresionado;
  }

  @Override
  public int getTipoComidaPresionado() {
    return inicialToState.numeroBotonPresionado;
  }

  @Override
  public int getPosicionPlatos() {
    return platosToState.posicionListaAutoresPulsada;
  }

  @Override
  public void startingPlatosScreen(Platos.ToPlatos presenter) {
    presenter.onScreenStarted();
    {

    }




    ///////////////////////////////////////////////////////////////////////////////////
    // State /////////////////////////////////////////////////////////////////////////

    private class PlatosState {
      int posicionListaPlatosPulsada;
      int idPlatoSeleccionado;


    }

  }
}
