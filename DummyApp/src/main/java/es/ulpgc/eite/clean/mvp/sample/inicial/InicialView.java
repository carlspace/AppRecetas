package es.ulpgc.eite.clean.mvp.sample.inicial;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.sample.R;
import io.realm.Realm;
import io.realm.RealmConfiguration;


/**
 * Created by Carlos Espacios on 26/06/2017.
 */

public class InicialView extends GenericActivity<Inicial.PresenterToView, Inicial.ViewToPresenter, InicialPresenter>
    implements Inicial.PresenterToView {
//ASasASS
        private Toolbar toolbar;
        private Button button;

        private ConstraintLayout layout1;
        private ConstraintLayout layout2;
        private ConstraintLayout layout3;
        private ConstraintLayout layout4;
        private TextView text1;
        private TextView text2;
        private TextView text3;
        private TextView text4;
        private ImageView icono1;
        private ImageView icono2;
        private ImageView icono3;
        private ImageView icono4;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_inicial);

            text1 = (TextView) findViewById(R.id.textoBtnEnsalada);
            text2 = (TextView) findViewById(R.id.textoBtnSopa);
            text3 = (TextView) findViewById(R.id.textoBtnCarne);
            text4 = (TextView) findViewById(R.id.textoBtnPescado);

            icono1 = (ImageView)  findViewById(R.id.iconoBtnEnsalada);
            icono2 = (ImageView)  findViewById(R.id.iconoBtnSopa);
            icono3 = (ImageView)  findViewById(R.id.iconoBtnCarne);
            icono4 =( ImageView)  findViewById(R.id.iconoBtnPescado);

            layout1 = (ConstraintLayout) findViewById(R.id.btnEnsalada);
            layout1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getPresenter().onButtonClicked1();
                }
            });

            layout2 = (ConstraintLayout) findViewById(R.id.btnSopa);
            layout2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getPresenter().onButtonClicked2();
                }
            });

            layout3 = (ConstraintLayout) findViewById(R.id.btnCarne);
            layout3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getPresenter().onButtonClicked3();
                }
            });

            layout4 = (ConstraintLayout) findViewById(R.id.btnPescado);
            layout4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getPresenter().onButtonClicked4();
                }
            });

            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            Realm.init(this);
            RealmConfiguration config = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();

        }

        /**
         * Method that initialized MVP objects
         * {@link super#onResume(Class, Object)} should always be called
         */
        @SuppressLint("MissingSuperCall")
        @Override
        protected void onResume() {
            super.onResume(InicialPresenter.class, this);
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
        public void hideToolbar() {
            toolbar.setVisibility(View.GONE);
        }

    @Override
    public void setLabel1(String txt) {
        text1.setText(txt);
    }
    @Override
    public void setLabel2(String txt) {
        text2.setText(txt);
    }
    @Override
    public void setLabel3(String txt) { text3.setText(txt);
    }
    @Override
    public void setLabel4(String txt) {
        text4.setText(txt);
    }




}



