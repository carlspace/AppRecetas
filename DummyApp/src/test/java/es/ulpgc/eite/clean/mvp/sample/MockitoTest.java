package es.ulpgc.eite.clean.mvp.sample;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import es.ulpgc.eite.clean.mvp.sample.inicial.Inicial;
import es.ulpgc.eite.clean.mvp.sample.inicial.InicialView;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Carlos Espacios on 09/07/2017.
 */

public class MockitoTest {



    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();


    @Test
    public void testMockModeloInicial()  {

        Inicial.PresenterToModel modeloInicial = mock(Inicial.PresenterToModel.class);
        InicialView vistaInicial = mock(InicialView.class) ;


// desde la base de datos llegarían estos Strings por medio de inicialModel, pero los estamos simulando
        when(modeloInicial.getTipoComidaBtn1()).thenReturn("Ensaladas");
        when(modeloInicial.getTipoComidaBtn2()).thenReturn("Sopas");
        when(modeloInicial.getTipoComidaBtn3()).thenReturn("Carnes");
        when(modeloInicial.getTipoComidaBtn4()).thenReturn("Pescados");


// la vista debe de esperar los siguiente resultados
        doNothing().when(vistaInicial).setLabel1("Ensaladas");
        doNothing().when(vistaInicial).setLabel2("Sopas");
        doNothing().when(vistaInicial).setLabel3("Carnes");
        doNothing().when(vistaInicial).setLabel4("Pescados");

        vistaInicial.setLabel1(modeloInicial.getTipoComidaBtn1());



        assertEquals(modeloInicial.getTipoComidaBtn1(), "Ensaladas");
        assertEquals(modeloInicial.getTipoComidaBtn2(), "Sopas");
        assertEquals(modeloInicial.getTipoComidaBtn3(), "Carnes");
        assertEquals(modeloInicial.getTipoComidaBtn4(), "Pescados");

    }
}
