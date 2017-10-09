package es.ulpgc.eite.clean.mvp.sample.app;

import es.ulpgc.eite.clean.mvp.sample.addPlato.AddPlatoPresenter;
import es.ulpgc.eite.clean.mvp.sample.plato.Plato;
import es.ulpgc.eite.clean.mvp.sample.inicial.Inicial;
import es.ulpgc.eite.clean.mvp.sample.platos.Platos;
import es.ulpgc.eite.clean.mvp.sample.youtube.Youtube;

public interface Mediator {
  void startingInicialScreen(Inicial.ToInicial presenter);
  void startingPlatosScreen(Platos.ToPlatos presenter);
  void startingPlatoScreen(Plato.ToPlato presenter);
  void startingAddPlatoScreen(AddPlatoPresenter addPlatoPresenter);
  void startingYoutubeScreen(Youtube.ToYoutube presenter);

  int getTipoComidaPresionado();

  void setImagenPlato(String imagen);
  String getImagenPlato();


    int getPosicionPlatos();

  int getIdPlatoSeleccionado();
}
