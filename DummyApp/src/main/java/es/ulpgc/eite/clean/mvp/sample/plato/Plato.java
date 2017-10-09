package es.ulpgc.eite.clean.mvp.sample.plato;

import android.content.Context;
import android.graphics.Bitmap;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.Model;
import es.ulpgc.eite.clean.mvp.Presenter;

/**
 * Created by Luis on 12/11/16.
 */

public interface Plato {


  ///////////////////////////////////////////////////////////////////////////////////
  // State /////////////////////////////////////////////////////////////////////////

  interface ToPlato {
    void onScreenStarted();

  }

  interface PlatoTo {
    Context getManagedContext();
    void destroyView();
    int getIdPlatoSeleccionado();

  }

  ///////////////////////////////////////////////////////////////////////////////////
  // Screen ////////////////////////////////////////////////////////////////////////

  /**
   * Methods offered to VIEW to communicate with PRESENTER
   */
  interface ViewToPresenter extends Presenter<PresenterToView> {
    void onButtonClicked();
    void inicializarVista();
  }

  /**
   * Required VIEW methods available to PRESENTER
   */
  interface PresenterToView extends ContextView {
    void finishScreen();
    void setRecetaPlato(String txt);
    void setNombrePlato(String txt);
    void setIngredientesPlato(String txt);
    void setImagenPlato(Bitmap imagen);
  }

  /**
   * Methods offered to MODEL to communicate with PRESENTER
   */
  interface PresenterToModel extends Model<ModelToPresenter> {



    String getNombrePlato(int id);
    String getReceta(int id);
    String getIngredientes(int id);
    String getImagen(int id);
    Boolean getInitial(int id);


  }

  /**
   * Required PRESENTER methods available to MODEL
   */
  interface ModelToPresenter {

  }
}
