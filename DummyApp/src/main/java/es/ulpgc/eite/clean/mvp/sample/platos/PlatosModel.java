package es.ulpgc.eite.clean.mvp.sample.platos;

import es.ulpgc.eite.clean.mvp.GenericModel;
import es.ulpgc.eite.clean.mvp.sample.database.I_ManejadorDataBase;
import es.ulpgc.eite.clean.mvp.sample.database.ManejadorDataBase;



public class PlatosModel extends GenericModel<Platos.ModelToPresenter>
    implements Platos.PresenterToModel {

    I_ManejadorDataBase miManejador;
  private static final String NOTIFY_DELETED = "Deleted";


  /**
   * Method that recovers a reference to the PRESENTER
   * You must ALWAYS call {@link super#onCreate(Object)} here
   *
   * @param presenter Presenter interface
   */
  @Override
  public void onCreate(Platos.ModelToPresenter presenter) {
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
  public int getIdPlatoSeleccionado(int idTipoDeComida, int posicionDeListaPlatosClicked) {
    int[] ids = miManejador.getListaIdPlatos(idTipoDeComida);
    return ids[posicionDeListaPlatosClicked];
  }


  @Override
  public String[] getPlatos(int idTipoDeComida) {
      int [] vectorPlatos = miManejador.getListaIdPlatos(idTipoDeComida);
      return miManejador.getNombresByArrayIdPlatos(vectorPlatos);
  }

  @Override
  public String getTipoComida(int idTipoDeComida) {
    return miManejador.getTipoComida(idTipoDeComida);
  }

  @Override
  public void deleteListItemAt(int id) {

  }

  @Override
  public void showUndoSnackbar() {

  }



  @Override
  public String getNotifyDeleted() {
    return NOTIFY_DELETED;

  }


}

