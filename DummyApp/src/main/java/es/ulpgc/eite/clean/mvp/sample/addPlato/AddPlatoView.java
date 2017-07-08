package es.ulpgc.eite.clean.mvp.sample.addPlato;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Observable;

import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.sample.R;

/**
 * Created by Erik on 04/07/2017.
 */

public class AddPlatoView
            extends GenericActivity<AddPlato.PresenterToView, AddPlato.ViewToPresenter, AddPlatoPresenter>
            implements AddPlato.PresenterToView {

        private Toolbar toolbar;
        private Button addImagen;
        private EditText nombreIntroducido;
        private EditText recetaIntroducida;
        private ImageView imagenSeleccionada;
        private Button btnDone;
        private static MyObservable observable;
        private static final int SELECT_PICTURE = 1;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add_plato);


            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            nombreIntroducido = (EditText) findViewById(R.id.nombrePlatoIntroducido);
            nombreIntroducido.setMovementMethod(new ScrollingMovementMethod());
            recetaIntroducida = (EditText) findViewById(R.id.recetaPlatoIntroducida);
            recetaIntroducida.setMovementMethod(new ScrollingMovementMethod());
            imagenSeleccionada=(ImageView)  findViewById(R.id.imagenSeleccionadaPlato);

            btnDone=(Button) findViewById(R.id.btnDonePlato);
            btnDone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getPresenter().onButtonDoneClicked();
                }
            });

            addImagen = (Button) findViewById(R.id.addImagenPlato);
            addImagen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getPresenter().onButtonAddImagenClicked();
                    observable = new MyObservable();
                    observable.addObserver(getPresenter().getObserver());

                }
            });
        }

        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            Log.d(TAG,"on activity result");
            if (resultCode == RESULT_OK) {
                Uri selectedImageUri = data.getData();
                observable.imagenSeleccionada(selectedImageUri);
            }
        }



        class MyObservable extends Observable {
            public void imagenSeleccionada(Uri uri){
                Log.d(TAG,"on observable");
                setChanged();
                notifyObservers(uri);
            }
        }

        /**
         * Method that initialized MVP objects
         * {@link super#onResume(Class, Object)} should always be called
         */
        @SuppressLint("MissingSuperCall")
        @Override
        protected void onResume() {
            super.onResume(AddPlatoPresenter.class, this);

        }


        ///////////////////////////////////////////////////////////////////////////////////
        // Presenter To View /////////////////////////////////////////////////////////////

        @Override
        public void finishScreen() {
            finish();
        }


        @Override
        public void startMenu(Intent intent){
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);

        }
        @Override
        public String getNombre(){
            return nombreIntroducido.getText().toString();
        }

         @Override
         public String getReceta() {
        return recetaIntroducida.getText().toString();
    }

    @Override
        public String getEnlaceYoutube(){
            return null;
        }

        @Override
        public void setTitle(String txt){
            toolbar.setTitle(txt);
        }

        @Override
        public void showToast(String txt){
            Toast toast = Toast.makeText(getActivityContext(), txt,Toast.LENGTH_SHORT);
            toast.show();

        }
        @Override
        public void setImagen(Bitmap imagen){
            imagenSeleccionada.setImageBitmap(imagen);
        }
        @Override
        public void showImagen(){
            imagenSeleccionada.setVisibility(View.VISIBLE);
        }
        @Override
        public void hideImagen(){
            imagenSeleccionada.setVisibility(View.GONE);
        }


    }


