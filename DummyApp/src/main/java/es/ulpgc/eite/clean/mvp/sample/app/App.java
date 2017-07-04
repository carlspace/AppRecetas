package es.ulpgc.eite.clean.mvp.sample.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import es.ulpgc.eite.clean.mvp.sample.inicial.Inicial;
import es.ulpgc.eite.clean.mvp.sample.plato.Plato;
import es.ulpgc.eite.clean.mvp.sample.plato.PlatoView;
import es.ulpgc.eite.clean.mvp.sample.platos.Platos;
import es.ulpgc.eite.clean.mvp.sample.platos.PlatosView;


public class App extends Application implements Mediator, Navigator {

    private InicialState toInicialState, inicialToState;
    private PlatosState toPlatosState, platosToState;
    private PlatoState toPlatoState, platoState;
    private AddPlatoState toAddPlatoState, addPlatoToState;

    @Override
    public void onCreate() {
        super.onCreate();
        toInicialState = new InicialState();
        toInicialState.toolbarVisibility = false;

    }

    ///////////////////////////////////////////////////////////////////////////////////
    // Mediator //////////////////////////////////////////////////////////////////////

    @Override
    public void startingInicialScreen(Inicial.ToInicial presenter) {

        presenter.onScreenStarted();
    }

    @Override
    public void startingAddPlatoScreen(AddPlatoPresenter presenter) {
        presenter.onScreenStarted();
    }
    ///////////////////////////////////////////////////////////////////////////////////
    // Navigator /////////////////////////////////////////////////////////////////////

    @Override
    public void goToPlatosScreen(Inicial.InicialTo presenter) {
        Log.d("APP", "goToPlatosScreen() has pulsado el boton: " + presenter.getNumeroBotonPresionado());
        inicialToState = new InicialState();
        inicialToState.numeroBotonPresionado = presenter.getNumeroBotonPresionado();

        platosToState = new PlatosState();
        platosToState.posicionListaPlatosPulsada = 0;
        Context view = presenter.getManagedContext();

        if (view != null) {
            view.startActivity(new Intent(view, PlatosView.class));

        }

    }
    @Override
    public void goToAddPlatoScreen(Platos.PlatosTo presenter){

        Log.d("APP", "goToAddPlatoScreen() ");
        Context view = presenter.getManagedContext();
        addPlatoToState = new AddPlatoState();
        addPlatoToState.imagenPlato="ic_escultura.png";
        if (view != null) {
            view.startActivity(new Intent(view, AddPlatoView.class));
        }

    }

    @Override
    public void goToPlatoScreen(Platos.PlatosTo presenter) {

            Log.d("APP", "goToAutorScreen() has pulsado: "+ presenter.getPosicionListaPlatoSeleccionado());

            platosToState.posicionListaPlatoSelecionado = presenter.getPosicionListaPlatoSeleccionado();
            platosToState.idPlatoSeleccionado = presenter.getIdPlatoSeleccionado();

            Log.d("APP", "goToObraScreen() con id obra: "+ platosToState.idPlatoSeleccionado);
            Context view = presenter.getManagedContext();

            if (view != null) {
                view.startActivity(new Intent(view, PlatoView.class));

            }

        }



    @Override
    public int getTipoComidaPresionado() {
        return inicialToState.numeroBotonPresionado;
    }

    @Override
    public int getPosicionPlatos() {
        return platosToState.posicionListaPlatosPulsada;
    }

    @Override
    public int getIdPlatoSeleccionado() {

        return platosToState.idPlatoSeleccionado;
    }

    @Override
    public void startingPlatosScreen(Platos.ToPlatos presenter) {

        presenter.onScreenStarted();
    }
    @Override
    public void startingPlatoScreen(Plato.ToPlato presenter){
        presenter.onScreenStarted();
    }


    @Override
    public String getImagenPlato(){
        return addPlatoToState.imagenPlato;
    }


    @Override
    public void setImagenPlato(String imagen){
        addPlatoToState.imagenPlato =imagen;
    }



    ///////////////////////////////////////////////////////////////////////////////////
    // State /////////////////////////////////////////////////////////////////////////
    private class InicialState {
        boolean toolbarVisibility;
        int numeroBotonPresionado;
    }

    private class PlatosState {
        int posicionListaPlatosPulsada;
        int idPlatoSeleccionado;


        int posicionListaPlatoSelecionado;
    }

    private class PlatoState {
        int posicionListaPlatosPulsada;
        int idPlatoSeleccionado;

    }
    private class AddPlatoState {
        String imagenPlato;
    }
        }

