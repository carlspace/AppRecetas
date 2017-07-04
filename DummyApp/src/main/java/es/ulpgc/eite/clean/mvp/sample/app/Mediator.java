package es.ulpgc.eite.clean.mvp.sample.app;

import es.ulpgc.eite.clean.mvp.sample.plato.Plato;
import es.ulpgc.eite.clean.mvp.sample.inicial.Inicial;
import es.ulpgc.eite.clean.mvp.sample.platos.Platos;

public interface Mediator {
  void startingInicialScreen(Inicial.ToInicial presenter);
  void startingPlatosScreen(Platos.ToPlatos presenter);
  void startingPlatoScreen(Plato.ToPlato presenter);
  void startingAddPlatoScreen(AddPlatoPresenter addPlatoPresenter);


  int getTipoComidaPresionado();



    int getPosicionPlatos();

  int getIdPlatoSeleccionado();
}
