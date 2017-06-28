package es.ulpgc.eite.clean.mvp.sample.app;

import es.ulpgc.eite.clean.mvp.sample.inicial.Inicial;

public interface Navigator {
  void goToNextScreen(Inicial.InicialTo presenter);
  void goToPlatosScreen(Inicial.InicialTo presenter);
}
