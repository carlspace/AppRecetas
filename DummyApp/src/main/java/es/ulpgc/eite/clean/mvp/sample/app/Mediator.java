package es.ulpgc.eite.clean.mvp.sample.app;

import es.ulpgc.eite.clean.mvp.sample.inicial.Inicial;
import es.ulpgc.eite.clean.mvp.sample.platos.Platos;

public interface Mediator {
  void startingInicialScreen(Inicial.ToInicial presenter);
  void startingPlatosScreen(Platos.ToPlatos presenter);



  int getTipoComidaPresionado();



    int getPosicionPlatos();
}
