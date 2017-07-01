package es.ulpgc.eite.clean.mvp.sample.platos;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.sample.R;

public class PlatosView
    extends GenericActivity<Platos.PresenterToView, Platos.ViewToPresenter, PlatosPresenter>
    implements Platos.PresenterToView {

    private Toolbar toolbar;
    private Button button;
    private TextView text;
    private ListView listaPlatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_platos);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        listaPlatos = (ListView) findViewById(R.id.listaPlatos);
        listaPlatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                onItemClickSelected(i);
            }
        });
    }

    private void onItemClickSelected(int pos) {
        getPresenter().onItemClickSelected(pos);
    }


    /**
     * Method that initialized MVP objects
     * {@link super#onResume(Class, Object)} should always be called
     */

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onResume() {
        super.onResume(PlatosPresenter.class, this);
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
    /// // Presenter To View ////////////////////////////////////////////////////////////


    @Override
    public void finishScreen() {
        finish();

    }

    @Override
    public void setPosicionLista(int pos) {
        listaPlatos.setSelection(pos);

    }

    //Este método se introduce para cuando se introduzcan los botones add y delete
    @Override
    public void actualizarListaPlatos(String[] nombresPlatos){
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,nombresPlatos
        );
        listaPlatos.setAdapter(arrayAdapter);
    }
    @Override
    public void setTituloToolbar(String txt) {
        toolbar.setTitle(txt);
    }
}
