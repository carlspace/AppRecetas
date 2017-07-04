package es.ulpgc.eite.clean.mvp.sample.app;

import es.ulpgc.eite.clean.mvp.sample.inicial.Inicial;
import es.ulpgc.eite.clean.mvp.sample.platos.Platos;

public interface Navigator {
  void goToPlatosScreen(Inicial.InicialTo presenter);
  void goToPlatoScreen(Platos.PlatosTo presenter);
  void goToAddPlatoScreen(Platos.PlatosTo presenter);
}
