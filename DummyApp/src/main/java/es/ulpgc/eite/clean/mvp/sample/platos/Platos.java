package es.ulpgc.eite.clean.mvp.sample.platos;

import android.content.Context;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.Model;
import es.ulpgc.eite.clean.mvp.Presenter;

/**
 * Created by Luis on 12/11/16.
 */

public interface Platos {


  ///////////////////////////////////////////////////////////////////////////////////
  // State /////////////////////////////////////////////////////////////////////////

  interface ToPlatos {
    void onScreenStarted();

  }

  interface PlatosTo {
      Context getManagedContext();
      void destroyView();
      int getPosicionDeListaPlatosClicked();
      int getIdPlatoSeleccionado();

  }

  ///////////////////////////////////////////////////////////////////////////////////
  // Screen ////////////////////////////////////////////////////////////////////////

  /**
   * Methods offered to VIEW to communicate with PRESENTER
   */
  interface ViewToPresenter extends Presenter<PresenterToView> {
    void onItemClickSelected(int pos);
    void inicializarVista();

    void onButtonClicked();
  }

  /**
   * Required VIEW methods available to PRESENTER
   */
  interface PresenterToView extends ContextView {
    void finishScreen();

      void setPosicionLista(int pos);

    //Este método se introduce para cuando se introduzcan los botones add y delete
    void actualizarListaPlatos(String[] nombresPlatos);

    void setTituloToolbar(String txt);
  }

  /**
   * Methods offered to MODEL to communicate with PRESENTER
   */
  interface PresenterToModel extends Model<ModelToPresenter> {
    void onChangeMsgByBtnClicked();
    String getText();
    String getLabel();

    int getIdPlatoSeleccionado(int idTipoDeComida, int posicionDeListaPlatosClicked);
    void actualizarListaPlatos(String[] nombresPlatos);

    void getPlatos(int idTipoDeComida);

    String getTipoComida(int numeroBotonPresionado);
  }
//HOLA
  /**
   * Required PRESENTER methods available to MODEL
   */
  interface ModelToPresenter {

  }
}
