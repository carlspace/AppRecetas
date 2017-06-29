package es.ulpgc.eite.clean.mvp.sample.inicial;

import es.ulpgc.eite.clean.mvp.GenericModel;

public class InicialModel extends GenericModel<Inicial.ModelToPresenter>
        implements Inicial.PresenterToModel{


    private String dummyText;
    private String dummyLabel;
    private int numOfTimes;
    private String msgText;

    /**
     * Method that recovers a reference to the PRESENTER
     * You must ALWAYS call {@link super#onCreate(Object)} here
     *
     * @param presenter Presenter interface
     */
    @Override
    public void onCreate(Inicial.ModelToPresenter presenter) {
        super.onCreate(presenter);


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
        return "Pescado";
    }

    @Override
    public String getTipoComidaBtn3() {
        return "Carne";
    }

    @Override
    public String getTipoComidaBtn2() {
        return "Sopa";
    }

    @Override
    public String getTipoComidaBtn1() {
        return "Ensalada";
    }
}


