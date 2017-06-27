package es.ulpgc.eite.clean.mvp.sample.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import es.ulpgc.eite.clean.mvp.sample.inicial.Inicial;


public class App extends Application implements Mediator, Navigator {


  private InicialState toInicialState, inicialToState;

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


}
