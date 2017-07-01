package es.ulpgc.eite.clean.mvp.sample.platos;

import es.ulpgc.eite.clean.mvp.GenericModel;


public class PlatosModel extends GenericModel<Platos.ModelToPresenter>
    implements Platos.PresenterToModel {

  private String platosText;
  private String platosLabel;
  private int numOfTimes;
  private String msgText;

  /**
   * Method that recovers a reference to the PRESENTER
   * You must ALWAYS call {@link super#onCreate(Object)} here
   *
   * @param presenter Presenter interface
   */
  @Override
  public void onCreate(Platos.ModelToPresenter presenter) {
    super.onCreate(presenter);

    platosLabel = "Click Me!";
    platosText = "Hello World!";
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
  public void onChangeMsgByBtnClicked() {
    msgText = platosText;
    if(numOfTimes > 0) {
      msgText += ", " + numOfTimes + " times";
    }
    numOfTimes++;
  }

  @Override
  public String getText() {
    return msgText;
  }

  @Override
  public String getLabel() {
    return platosLabel;
  }

  @Override
  public int getIdPlatoSeleccionado(int idTipoDeComida, int posicionDeListaPlatosClicked) {
    return 0;
  }

  @Override
  public void actualizarListaPlatos(String[] nombresPlatos) {

  }


  @Override
  public void getPlatos(int idTipoDeComida) {

  }

  @Override
  public String getTipoComida(int numeroBotonPresionado) {
    return null;
  }
}
