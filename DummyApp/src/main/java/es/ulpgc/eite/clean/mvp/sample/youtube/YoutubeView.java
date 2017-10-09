package es.ulpgc.eite.clean.mvp.sample.youtube;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.sample.R;

/**
 * Created by Carlos Espacios on 04/10/2017.
 */

public class YoutubeView extends GenericActivity<Youtube.PresenterToView, Youtube.ViewToPresenter, ConfigYoutube>
        implements Youtube.PresenterToView{



    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);

        toolbar = (Toolbar) findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);

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
        super.onResume(ConfigYoutube.class, this);
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
    public void setNombrePlato(String txt) {
        toolbar.setTitle(txt);
    }


}
