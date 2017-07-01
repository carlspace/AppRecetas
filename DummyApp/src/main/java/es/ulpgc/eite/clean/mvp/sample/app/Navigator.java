package es.ulpgc.eite.clean.mvp.sample.app;

import es.ulpgc.eite.clean.mvp.sample.inicial.Inicial;
import es.ulpgc.eite.clean.mvp.sample.plato.Plato;

public interface Navigator {
  void goToPlatosScreen(Inicial.InicialTo presenter);
  void goToPlatoScreen(Plato.PlatoTo presenter);
}
