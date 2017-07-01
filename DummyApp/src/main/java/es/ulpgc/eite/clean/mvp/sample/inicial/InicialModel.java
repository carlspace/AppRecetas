package es.ulpgc.eite.clean.mvp.sample.inicial;

import es.ulpgc.eite.clean.mvp.GenericModel;
import es.ulpgc.eite.clean.mvp.sample.database.I_ManejadorDataBase;
import es.ulpgc.eite.clean.mvp.sample.database.ManejadorDataBase;

public class InicialModel extends GenericModel<Inicial.ModelToPresenter>
        implements Inicial.PresenterToModel{


    private String dummyText;
    private String dummyLabel;
    private int numOfTimes;
    private String msgText;

    I_ManejadorDataBase miManejador;


    /**
     * Method that recovers a reference to the PRESENTER
     * You must ALWAYS call {@link super#onCreate(Object)} here
     *
     * @param presenter Presenter interface
     */
    @Override
    public void onCreate(Inicial.ModelToPresenter presenter) {
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
    public String getText() {
        return msgText;
    }

    @Override
    public String getLabel() {
        return dummyLabel;
    }

    @Override
    public String getTipoComidaBtn4() {
        return miManejador.getNombreTipoDeComida(4);
    }

    @Override
    public String getTipoComidaBtn3() {
        return miManejador.getNombreTipoDeComida(3);
    }

    @Override
    public String getTipoComidaBtn2() {
        return miManejador.getNombreTipoDeComida(2);
    }

    @Override
    public String getTipoComidaBtn1() {
        return miManejador.getNombreTipoDeComida(1);
    }
}


