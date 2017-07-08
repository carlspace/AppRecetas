package es.ulpgc.eite.clean.mvp.sample.plato;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.sample.R;


public class PlatoView
    extends GenericActivity<Plato.PresenterToView, Plato.ViewToPresenter, PlatoPresenter> implements Plato.PresenterToView {

  private Toolbar toolbar;
  private ImageView imagen;
  private TextView receta;
  private Button btnYoutube;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_plato);

    toolbar = (Toolbar) findViewById(R.id.toolbar);


    setSupportActionBar(toolbar);
    receta = (TextView) findViewById(R.id.recetaPlato);
    receta.setMovementMethod(new ScrollingMovementMethod());
    imagen = (ImageView) findViewById(R.id.imagenPlato);
    {



    }
  }
    /**
     * Method that initialized MVP objects
     * {@link super#onResume(Class, Object)} should always be called
     */
    @SuppressLint("MissingSuperCall")
    @Override
    protected void onResume () {
      super.onResume(PlatoPresenter.class, this);
      getPresenter().inicializarVista();
    }

  /*
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_dummy, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
  */


    ///////////////////////////////////////////////////////////////////////////////////
    // Presenter To View /////////////////////////////////////////////////////////////






  @Override
  public void finishScreen() {
    finish();
  }

  @Override
  public void setDescripcionPlato(String txt) {
    receta.setText(txt);
  }

  @Override
  public void setNombrePlato(String txt) {
    toolbar.setTitle(txt);
  }
}


