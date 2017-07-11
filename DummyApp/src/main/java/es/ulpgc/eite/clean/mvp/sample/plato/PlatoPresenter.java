package es.ulpgc.eite.clean.mvp.sample.plato;


import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.GenericPresenter;
import es.ulpgc.eite.clean.mvp.sample.app.Mediator;

public class PlatoPresenter extends GenericPresenter
    <Plato.PresenterToView, Plato.PresenterToModel, Plato.ModelToPresenter, PlatoModel>
    implements Plato.ViewToPresenter, Plato.ModelToPresenter, Plato.PlatoTo, Plato.ToPlato {



  /**
   * Operation called during VIEW creation in {@link GenericActivity#onResume(Class, Object)}
   * Responsible to initialize MODEL.
   * Always call {@link GenericPresenter#onCreate(Class, Object)} to initialize the object
   * Always call  {@link GenericPresenter#setView(ContextView)} to save a PresenterToView reference
   *
   * @param view The current VIEW instance
   */
  @Override
  public void onCreate(Plato.PresenterToView view) {
    super.onCreate(PlatoModel.class, this);
    setView(view);
    Log.d(TAG, "calling onCreate()");



  }

  /**
   * Operation called by VIEW after its reconstruction.
   * Always call {@link GenericPresenter#setView(ContextView)}
   * to save the new instance of PresenterToView
   *
   * @param view The current VIEW instance
   */
  @Override
  public void onResume(Plato.PresenterToView view) {
    setView(view);
    Log.d(TAG, "calling onResume()");

    inicializarVista();
  }

  /**
   * Helper method to inform Presenter that a onBackPressed event occurred
   * Called by {@link GenericActivity}
   */
  @Override
  public void onBackPressed() {
    Log.d(TAG, "calling onBackPressed()");
  }

  /**
   * Hook method called when the VIEW is being destroyed or
   * having its configuration changed.
   * Responsible to maintain MVP synchronized with Activity lifecycle.
   * Called by onDestroy methods of the VIEW layer, like: {@link GenericActivity#onDestroy()}
   *
   * @param isChangingConfiguration true: configuration changing & false: being destroyed
   */
  @Override
  public void onDestroy(boolean isChangingConfiguration) {
    super.onDestroy(isChangingConfiguration);
    Log.d(TAG, "calling onDestroy()");
  }


  ///////////////////////////////////////////////////////////////////////////////////
  // View To Presenter /////////////////////////////////////////////////////////////

  @Override
  public void onButtonClicked() {
    Log.d(TAG, "calling onButtonClicked()");
    Mediator app=(Mediator) getView().getApplication();
    int id=app.getIdPlatoSeleccionado();

    String nombrePlato = getModel().getNombrePlato(id);
}
  private void inicializarImagen(Boolean inicial, int id){
    String imagen =getModel().getImagen(id);
    // la imagen se obtiene desde assets
    if (inicial){

      AssetManager am = getView().getActivityContext().getAssets();
      InputStream is = null;
      try{

        is = am.open(imagen);
      }catch(IOException e){
        e.printStackTrace();
      }

      Bitmap bitmapAssets = BitmapFactory.decodeStream(is);
      getView().setImagenPlato(bitmapAssets);

      // no se obtiene de assets
    }else{

      File imgFile = new  File(imagen);
      Bitmap bitmapUsuario = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
      getView().setImagenPlato(bitmapUsuario);
    }
  }
  @Override
  public void inicializarVista() {
    Mediator app=(Mediator) getView().getApplication();

    int id=app.getIdPlatoSeleccionado();
    getView().setNombrePlato(getModel().getNombrePlato(id));
    getView().setRecetaPlato(getModel().getReceta(id));
    getView().setIngredientesPlato(getModel().getIngredientes(id));
    inicializarImagen(getModel().getInitial(id), id);

  }




  ///////////////////////////////////////////////////////////////////////////////////
  // To Platos //////////////////////////////////////////////////////////////////////

  @Override
  public void onScreenStarted() {
    Log.d(TAG, "calling onScreenStarted()");
    if(isViewRunning()) {
      inicializarVista();
    }

  }



  ///////////////////////////////////////////////////////////////////////////////////
  // Platos To //////////////////////////////////////////////////////////////////////




  @Override
  public Context getManagedContext(){
    return getActivityContext();
  }

  @Override
  public void destroyView(){
    if(isViewRunning()) {
      getView().finishScreen();
    }
  }

  ///////////////////////////////////////////////////////////////////////////////////



}
