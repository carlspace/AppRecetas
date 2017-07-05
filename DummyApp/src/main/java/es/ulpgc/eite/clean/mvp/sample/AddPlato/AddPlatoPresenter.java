package es.ulpgc.eite.clean.mvp.sample.AddPlato;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.util.Observable;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.GenericPresenter;
import es.ulpgc.eite.clean.mvp.sample.app.Mediator;

/**
 * Created by Erik on 04/07/2017.
 */

public class AddPlatoPresenter extends GenericPresenter<AddPlato.PresenterToView, AddPlato.PresenterToModel, AddPlato.ModelToPresenter, AddPlatoModel>
        implements AddPlato.ViewToPresenter, AddPlato.ModelToPresenter, AddPlato.AddPlatoTo, AddPlato.ToAddPlato {

private String imagenPath;
private static MyObserver observer;
private Uri uri;

/**
 * Operation called during VIEW creation in {@link es.ulpgc.eite.clean.mvp.GenericActivity#onResume(Class, Object)}
 * Responsible to initialize MODEL.
 * Always call {@link es.ulpgc.eite.clean.mvp.GenericPresenter#onCreate(Class, Object)} to initialize the object
 * Always call  {@link es.ulpgc.eite.clean.mvp.GenericPresenter#setView(ContextView)} to save a PresenterToView reference
 *
 * @param view The current VIEW instance
 */
@Override
public void onCreate(AddPlato.PresenterToView view) {
        super.onCreate(AddPlatoModel.class, this);
        setView(view);
        Log.d(TAG, "calling onCreate()");

        Log.d(TAG, "calling startingAddAutorScreen()");
        Mediator app = (Mediator) getView().getApplication();
        app.startingAddPlatoScreen(this);
        }

/**
 * Operation called by VIEW after its reconstruction.
 * Always call {@link es.ulpgc.eite.clean.mvp.GenericPresenter#setView(ContextView)}
 * to save the new instance of PresenterToView
 *
 * @param view The current VIEW instance
 */
@Override
public void onResume(AddPlato.PresenterToView view) {
        setView(view);
        Log.d(TAG, "calling onResume()");
        inicializarVista();
        if(configurationChangeOccurred()) {
        inicializarVista();

        }
        }

/**
 * Helper method to inform Presenter that a onBackPressed event occurred
 * Called by {@link es.ulpgc.eite.clean.mvp.GenericActivity}
 */
@Override
public void onBackPressed() {
        Log.d(TAG, "calling onBackPressed()");
        }

/**
 * Hook method called when the VIEW is being destroyed or
 * having its configuration changed.
 * Responsible to maintain MVP synchronized with Activity lifecycle.
 * Called by onDestroy methods of the VIEW layer, like: {@link es.ulpgc.eite.clean.mvp.GenericActivity#onDestroy()}
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
public void onButtonAddImagenClicked(){
        observer = new MyObserver();
        Intent intent = new Intent(
        Intent.ACTION_PICK,
        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        );
        getView().startMenu(intent);

        }

class MyObserver implements java.util.Observer{

    @Override
    public void update(Observable o, Object arg) {
        Log.d(TAG,"update observer");
        uri =(Uri)arg;
        if(uri != null){
            imagenPath = getRealPathFromURI(uri);
            if(imagenPath != null) {
                setImagenSelecionada();
                setImagenView(imagenPath);
                getView().showImagen();
            }
        }
    }
}

    @Override
    public MyObserver getObserver(){
        return observer;

    }
    @Override
    public void onButtonDoneClicked() {
        Mediator app = (Mediator) getView().getApplication();
        String nombre= getView().getNombre();
        String receta= getView().getReceta();
        String path = getImagenSelecionada();

        if((!nombre.equals(""))&&(!receta.equals(""))
                ){
            if (path.equals("ic_escultura.png")){
                getModel().addPlatoSinImagen(nombre, receta, app.getTipoComidaPresionado());
            }
            else {
                getModel().addPlatoConImagen(nombre, receta, app.getTipoComidaPresionado(),
                        path);
            }
            getView().finishScreen();
        }else {
            getView().showToast("Introducir Datos Validos");
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////
    // To AddAutor //////////////////////////////////////////////////////////////////////

    @Override
    public void onScreenStarted() {
        Log.d(TAG, "calling onScreenStarted()");
        if(isViewRunning()) {
            inicializarVista();
        }

    }


    ///////////////////////////////////////////////////////////////////////////////////
    // AddAutor To //////////////////////////////////////////////////////////////////////


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

    @Override
    public void setImagenSelecionada(){
        Mediator app = (Mediator) getView().getApplication();
        String imagen = imagenPath;
        app.setImagenPlato(imagen);

    }

    ///////////////////////////////////////////////////////////////////////////////////

    public void inicializarVista(){
        getView().setTitle("Nuevo Autor");
        if(getImagenSelecionada().equals("ic_escultura.png")){
            getView().hideImagen();
        }else{
            getView().showImagen();
            setImagenView(getImagenSelecionada());
        }
    }

    private String getImagenSelecionada(){
        Mediator app = (Mediator) getView().getApplication();
        return app.getImagenPlato();

    }
    private void setImagenView(String imagen){
        if( imagen != null) {
            File imgFile = new File(imagen);
            if (imgFile.exists()) {
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                getView().setImagen(myBitmap);
            }
        }
    }



}

