package es.ulpgc.eite.clean.mvp.sample;

import android.content.pm.ActivityInfo;
import android.os.SystemClock;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import es.ulpgc.eite.clean.mvp.sample.database.Plato;
import es.ulpgc.eite.clean.mvp.sample.database.TipoComida;
import es.ulpgc.eite.clean.mvp.sample.inicial.InicialView;
import io.realm.Realm;
import io.realm.RealmResults;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.junit.Assert.assertEquals;

/**
 * Created by Carlos Espacios on 07/07/2017.
 */
@RunWith(AndroidJUnit4.class)
public class BasicInstrumentedTest {

    @Rule
    public ActivityTestRule<InicialView> mActivityRule = new ActivityTestRule<InicialView>(InicialView.class);

    ////////////////////Tests de pantalla

    @Test
    public void testRotarInicial() {
        mActivityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        SystemClock.sleep(1000);
        onView(allOf(withId(R.id.btnEnsalada), isDisplayed()));
        onView(allOf(withId(R.id.btnSopa), isDisplayed()));
        onView(allOf(withId(R.id.btnCarne), isDisplayed()));
        onView(allOf(withId(R.id.btnPescado), isDisplayed()));
    }


    @Test
    public void testBotonesInicial() {
        onView(allOf(withId(R.id.btnEnsalada), isDisplayed()));
        onView(withId(R.id.textoBtnEnsalada)).check(matches(withText("Ensaladas")));

        onView(allOf(withId(R.id.btnSopa), isDisplayed()));
        onView(withId(R.id.textoBtnSopa)).check(matches(withText("Sopas")));

        onView(allOf(withId(R.id.btnCarne), isDisplayed()));
        onView(withId(R.id.textoBtnCarne)).check(matches(withText("Carnes")));

        onView(allOf(withId(R.id.btnPescado), isDisplayed()));
        onView(withId(R.id.textoBtnPescado)).check(matches(withText("Pescados")));
    }

    @Test
    public void testDisplaylistaPlatosEnsalada() {
        onView(withId(R.id.btnEnsalada)).perform(click());
        //comprobar el titulo de Toolbar
        onView(allOf(isAssignableFrom(TextView.class), withParent(isAssignableFrom(Toolbar.class))))
                .check(matches(withText("Ensaladas")));
        onView(allOf(withId(R.id.listaPlatos),isDisplayed()));
        onData(anything())
                .inAdapterView(withId(R.id.listaPlatos))
                .atPosition(0)
                .check(matches(withText("Cogollos de Tudela con anchoas y queso")));
    }
    @Test
    public void testDisplaylistaPlatosSopa() {
        onView(withId(R.id.btnSopa)).perform(click());
        //comprobar el titulo de Toolbar
        onView(allOf(isAssignableFrom(TextView.class), withParent(isAssignableFrom(Toolbar.class))))
                .check(matches(withText("Sopas")));
        onView(allOf(withId(R.id.listaPlatos),isDisplayed()));
        onData(anything())
                .inAdapterView(withId(R.id.listaPlatos))
                .atPosition(0)
                .check(matches(withText("Sopa castellana")));
    }

    @Test
    public void testDisplaylistaPlatosCarne() {
        onView(withId(R.id.btnCarne)).perform(click());
        //comprobar el titulo de Toolbar
        onView(allOf(isAssignableFrom(TextView.class), withParent(isAssignableFrom(Toolbar.class))))
                .check(matches(withText("Carnes")));
        onView(allOf(withId(R.id.listaPlatos),isDisplayed()));
        onData(anything())
                .inAdapterView(withId(R.id.listaPlatos))
                .atPosition(0)
                .check(matches(withText("Carne guisada con papas")));
    }

    @Test
    public void testDisplaylistaPlatosPescado() {
        onView(withId(R.id.btnPescado)).perform(click());
        //comprobar el titulo de Toolbar
        onView(allOf(isAssignableFrom(TextView.class), withParent(isAssignableFrom(Toolbar.class))))
                .check(matches(withText("Pescados")));
        onView(allOf(withId(R.id.listaPlatos),isDisplayed()));
        onData(anything())
                .inAdapterView(withId(R.id.listaPlatos))
                .atPosition(0)
                .check(matches(withText("Salmon encebollado")));
    }


    ////////Test de la base de datos (realm)

    @Test
    public void testRealmGetIdsPlatoPorTipoDeComida(){
        Realm realm = Realm.getDefaultInstance();
        int idTipoComida=2;
        int[] ids={4,5,6};

        RealmResults<Plato> result =realm.where(Plato.class).equalTo("idTipoComida",idTipoComida).findAll();
        int[] array=new int[result.size()];
        int i;
        for(i=0; i<result.size();i++){
            array[i] =result.get(i).getId();
        }
        assertEquals(ids[0],array[0]);
        assertEquals(ids[1],array[1]);
        assertEquals(ids[2],array[2]);
    }
    @Test
    public void testRealmGetNombreTipoComida(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<TipoComida> result= realm.where(TipoComida.class).equalTo("id",1).findAll();
        String nombre = result.get(0).getNombreTipoComida();
        assertEquals(nombre,"Ensaladas");
    }





}
