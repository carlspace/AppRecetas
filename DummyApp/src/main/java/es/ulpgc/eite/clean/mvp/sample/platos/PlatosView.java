package es.ulpgc.eite.clean.mvp.sample.platos;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.sample.R;
import es.ulpgc.eite.clean.mvp.sample.database.Plato;
import io.realm.RealmList;

public class PlatosView
    extends GenericActivity<Platos.PresenterToView, Platos.ViewToPresenter, PlatosPresenter>
    implements Platos.PresenterToView {

    private Toolbar toolbar;
    //private ListView listaPlatos;
    private RecyclerView listaPlatosRecycler;
    private FloatingActionButton btnAddPlato;
    //private List<String> platos= new ArrayList();
    private List<Plato> platos= new ArrayList();
   // private ArrayAdapter<String> adaptador;
    private PlatosRecyclerViewAdapter adaptador;
    private RealmList<Plato> listPlato;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_platos);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        initRecyclerListener();
        btnAddPlato = (FloatingActionButton) findViewById(R.id.btnAddPlato);
        btnAddPlato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().onButtonAddPlato();

            }
        });
    }
        //Instanciamos el listView
        /*listaPlatos = (ListView) findViewById(R.id.listaPlatos);
        listaPlatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              onItemClickSelected(i);
            }

        });

        adaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, new String[] { "Item 1",
                "Item 2", "Item 2", "Item 3", "Item 4", "Item 5" });
        listaPlatos.setAdapter(adaptador);

    }




*/

    private void initRecyclerListener() {
        listaPlatosRecycler = (RecyclerView) findViewById(R.id.listaPlatos);
        listaPlatosRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        listaPlatosRecycler.setItemAnimator(new DefaultItemAnimator());


        adaptador = new PlatosRecyclerViewAdapter(listPlato);
        listaPlatosRecycler.setAdapter(adaptador);
       /* listaPlatosRecycler.setOnClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                onItemClickSelected(i);
            }

        });
*/
        ItemTouchHelper swipeToDismissTouchHelper= new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override

            public boolean onMove(
                    RecyclerView recyclerView,
                    RecyclerView.ViewHolder viewHolder,
                    RecyclerView.ViewHolder target) {

                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                getPresenter().deletePlatoByPosition(viewHolder.getAdapterPosition());
                //getPresenter().deletePlatoByPosition(((PlatosRecyclerViewAdapter.ViewHolder) viewHolder).position);
               // adaptador.notifyItemRemoved(viewHolder.getAdapterPosition());
                adaptador.notifyDataSetChanged();
            }
        });

        swipeToDismissTouchHelper.attachToRecyclerView(listaPlatosRecycler);
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
        getPresenter().inicializarVista();
        Log.d(TAG, "ha pasado el corte");
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
       //listaPlatosRecycler.setp

    }

    //Este método se introduce para cuando se introduzcan los botones add y delete
    @Override
    public void actualizarListaPlatos(String[] nombresPlatos){
        //PlatosRecyclerViewAdapter adaptador = new PlatosRecyclerViewAdapter(listPlato
        //);
       adaptador.setItemList(nombresPlatos);
        listaPlatosRecycler.setAdapter(adaptador);
    }
    @Override
    public void setTituloToolbar(String txt) {
        toolbar.setTitle(txt);
    }






    public class PlatosRecyclerViewAdapter
            extends RecyclerView.Adapter<PlatosRecyclerViewAdapter.ViewHolder> {

        private  List<Plato> mValues;

        public PlatosRecyclerViewAdapter(List<Plato> items) {

            mValues = items;
        }

        @Override
        public PlatosRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_content, parent, false);
            return new PlatosView.PlatosRecyclerViewAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final PlatosRecyclerViewAdapter.ViewHolder holder, final int position) {
            holder.position = position;
            holder.mItem = mValues.get(position);
            holder.mContentView.setText(mValues.get(position).getNombre());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                onItemClickSelected(position);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }
        private void setItemList(List<Plato> itemList) {
            mValues = itemList;
            notifyDataSetChanged();
        }
        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mContentView;
            public Plato mItem;
            public int position;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mContentView = (TextView) view.findViewById(R.id.content);
            }

            @Override
            public String toString() {
                return mContentView.getText().toString();
            }
        }
    }

    public void showPlatos(RealmList<Plato> listPlato) {
        this.listPlato = listPlato;
        adaptador = new PlatosRecyclerViewAdapter(listPlato);
        listaPlatosRecycler.setAdapter(adaptador);
    }
}
