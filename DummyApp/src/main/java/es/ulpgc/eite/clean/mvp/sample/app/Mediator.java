package es.ulpgc.eite.clean.mvp.sample.app;

import es.ulpgc.eite.clean.mvp.sample.inicial.Inicial;
import es.ulpgc.eite.clean.mvp.sample.platos.PlatosPresenter;

public interface Mediator {
  void startingInicialScreen(Inicial.ToInicial presenter);
  int getNumeroBotonPresionado();

    void startingPlatosScreen(PlatosPresenter platosPresenter);
}
