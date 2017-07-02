package es.ulpgc.eite.clean.mvp.sample.plato;

import es.ulpgc.eite.clean.mvp.GenericModel;
import es.ulpgc.eite.clean.mvp.sample.database.I_ManejadorDataBase;
import es.ulpgc.eite.clean.mvp.sample.database.ManejadorDataBase;


public class PlatoModel extends GenericModel<Plato.ModelToPresenter>
    implements Plato.PresenterToModel {

  I_ManejadorDataBase miManejador;

  /**
   * Method that recovers a reference to the PRESENTER
   * You must ALWAYS call {@link super#onCreate(Object)} here
   *
   * @param presenter Presenter interface
   */
  @Override
  public void onCreate(Plato.ModelToPresenter presenter) {
    super.onCreate(presenter);
    miManejador = ManejadorDataBase.getInstance();

  }

  /**
   * Called by layer PRESENTER when VIEW pass for a reconstruction/destruction.
   * Usefull for kill/stop activities that could be running on the background Threads
   *
   * @param isChangingConfiguration Informs that a change is occurring on the configuration
   */
  @Override
  public void onDestroy(boolean isChangingConfiguration) {

  }

  ///////////////////////////////////////////////////////////////////////////////////
  // Presenter To Model ////////////////////////////////////////////////////////////


  @Override
  public String getNombrePlato(int id) {
    return miManejador.getNombrePlato(id);
  }

  @Override
  public String getReceta(int id) {
    return miManejador.getRecetaPlato(id);
  }

  @Override
  public String getImagen(int id) {
    return miManejador.getImagenPlato(id);
  }

  @Override
  public Boolean getInitial(int id) {
    return miManejador.isInAssetsPlato(id);
  }


  }

