package es.ulpgc.eite.clean.mvp.sample.database;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Carlos Espacios on 29/06/2017.
 */

public class ManejadorDataBase implements I_ManejadorDataBase {
   private static ManejadorDataBase ourInstance;
    private Realm realm;

    public static synchronized ManejadorDataBase getInstance() {
        if(ourInstance == null){
            ourInstance = new ManejadorDataBase();
        }
        return ourInstance;
    }

    private ManejadorDataBase() {
        realm = Realm.getDefaultInstance();
        initBaseDeDatos();
    }




    //Getters de platos, plato y tipoDeComida
    ///////////////////////////////POSIBLE FALLO AQUIIIIII!!!!!!!!!!! en el "id"
    @Override
    public String getTipoComida(int idTipoComida) {
        RealmResults<TipoComida> result = realm.where(TipoComida.class).equalTo("id",idTipoComida).findAll();
        return result.get(0).getNombreTipoComida();

    }

    @Override
    public int[] getListaIdPlatos(int idTipoComida) {
        RealmResults<Plato> result = realm.where(Plato.class).equalTo("idTipoComida",idTipoComida).findAll(); //encuentra los platos de este tipo
        int[] array=new int[result.size()];
        int i;
        for(i=0;i< result.size();i++){
            array[i] =result.get(i).getId();
        }
        return array;
    }

    @Override
    public String[] getNombresByArrayIdPlatos(int[] ids) {
        String[] array= new String[ids.length];
        for (int i=0; i< ids.length;i++){
            array[i]= getNombrePlato(ids[i]);
        }
        return array;
    }

    @Override
    public String getRecetaPlato(int idPlato) {
        RealmResults<Plato> result = realm.where(Plato.class).equalTo("id",idPlato).findAll();
        return result.get(0).getDescripcion();
    }

    @Override
    public String getNombrePlato(int idPlato) {
        RealmResults<Plato> result= realm.where(Plato.class).equalTo("id",idPlato).findAll();
        return result.get(0).getNombre();
    }

    @Override
    public String getImagenPlato(int idPlato) {
        RealmResults<Plato> result= realm.where(Plato.class).equalTo("id",idPlato).findAll();
        return result.get(0).getImagen();
    }

    @Override
    public Boolean isInAssetsPlato(int idPlato) {
        RealmResults<Plato> result= realm.where(Plato.class).equalTo("id",idPlato).findAll();
        return result.get(0).getIsInAssetsPlato();
    }




    //Metodos para añadir

    @Override
    public void addTipoComida(String nombre) {
        realm.beginTransaction();
        // Se incrementa el id
        Number currentIdNum = realm.where(TipoComida.class).max("id");
        int nextId;
        if(currentIdNum == null) {
            nextId = 1;
        } else {
            nextId = currentIdNum.intValue() + 1;
        }
        TipoComida tipoDeComida= realm.createObject(TipoComida.class,nextId);
        tipoDeComida.setNombreTipoComida(nombre);
               realm.commitTransaction();
    }

    @Override
    public void addPlato(String nombre, String descripcion, int idTipoComida, String imagen, String enlaceYoutube, Boolean isInAssets) {
        realm.beginTransaction();
        Number currentIdNum = realm.where(Plato.class).max("id");
        int nextId;
        if(currentIdNum == null) {
            nextId = 1;
        } else {
            nextId = currentIdNum.intValue() + 1;
        }
        Plato plato= realm.createObject(Plato.class,nextId);
        plato.setNombre(nombre);
        plato.setDescripcion(descripcion);
        plato.setImagen(imagen);
        plato.setIdTipoComida(idTipoComida);
        plato.setEnlaceYoutbe(enlaceYoutube);
        plato.setIsInAssetsPlato(isInAssets);
        realm.commitTransaction();
    }


    //Datos que se inicializaran con la appp

    public void initBaseDeDatos(){

        if(realm.isEmpty()) {




            //Platos ensalada
            addTipoComida("Ensaladas");
                int idTipoComida=1;
                        //plato Cogollos de Tudela con anchoas y queso (id=1)
                        String nombrePlato = "Cogollos de Tudela con anchoas y queso";

                        String receta = "El primer paso para empezar a preparar estos cogollos de Tudela con anchoas y queso es poner los cogollos de lechuga en remojo en agua bien fría. Los dejaremos 5 minutos.\n" +
                                "Por otro lado, vamos picando los tomates y las cebollas.\n" +
                                "A continuación, ponemos las verduras picadas en un bol y las aliñamos con un poco de aceite y vinagre al gusto.\n" +
                                "\n" +
                                "Entonces lo removemos todo y les echamos un poco de sal y pimienta a nuestro gusto también.\n" +
                                "A continuación, sacamos los cogollos del agua y escurrimos bien. Los cortamos en rodajas.\n" +
                                "Seguidamente los ponemos en una fuente, cortamos el queso de cabra en rodajas y los vamos poniendo encima de cada cogollo.\n" +
                                "\n" +
                                "Encima de los cogollos de lechuga con queso ponemos las anchoas.\n" +
                                "Posteriormente picamos las aceitunas y hacemos tiras de los pimientos del piquillo, y lo vamos colocando encima de los cogollos de Tudela con anchoas y queso.\n" +
                                "\n" +
                                "En el bol donde tenemos todo picadito y aliñado ponemos un poco de perejil o cilantro. Lo removemos bien. Esta será la vinagreta para los cogollos con anchoas\n" +
                                "Después aliñaremos nuestros cogollos de lechuga con queso y anchoas con esta vinagreta. ¡Y ya estarán listos!\n";
                        addPlato(nombrePlato, receta, idTipoComida, "ensalada_cogollos_con_tudela_y_anchoas", "https://www.youtube.com/watch?v=gr3LiqwIVx4",true);


                        //Pimientos asados con bonito(id=2)
                        nombrePlato = "Pimientos asados con bonito";
                        receta = "Comenzaremos a hacer esta receta de pimientos asados con ventresca de bonito asando los pimientos.\n" +
                                "Pues bien, cuando los pimientos estén asados y atemperados, y después de pelarlos, los cortamos en tiras" +
                                " y los colocamos sobre una fuente. Añadimos un poco de sal al gusto y removemos.\n" +
                                "A continuación, colocamos los lomos de bonito del norte por encima y regamos con un poco del aceite del mismo" +
                                " o bote o si preferimos con un poco de aceite de oliva virgen extra\n";

                        addPlato(nombrePlato, receta, idTipoComida, "ensalada_pimientos_asados_con_bonito.jpg", "https://www.youtube.com/watch?v=gh5sdgbvpJU",true);

                        //plato Ensalada de buey de mar (id=3)
                        nombrePlato = "Ensalada de buey de mar";
                        receta = "Empezaremos por hervir los bueyes de mar en una olla grande con abundante agua salada durante 15 minutos. Escurrirlos, " +
                                "abrirlos y separar el interior, presionando en la parte inferior.\n" +
                                "Sacar la carne con la ayuda de una cuchara y picarla finamente con un cuchillo afilado. Reservar los caparazones y " +
                                "el jugo que haya quedado al picar la carne. Esta forma es la más práctica de cocinar buey de mar.\n" +
                                "Por otro lado, nos ponemos a preparar la verdura para la ensalada de cangrejo. Entonces, pelar y picar la cebolla, " +
                                "lavar los cogollos de lechuga y dejarlos escurrir bien.\n" +
                                "Poner en un bol, la cebolla, la lechuga, el apio y los huevos duros todo picado muy pequeño.\n" +
                                "\n" +
                                "A continuación, añadir la carne de buey y mezclar bien. Poner la mayonesa en un cuenco y añadir el concentrado de tomate, el jugo reservado y el brandy.\n" +
                                "Mezclar bien todo y sazonar al gusto si lo consideras necesario.\n" +
                                "Cuando ya tengamos la mezcla lista, solo nos queda rellenar los caparazones con nuestra ensalada de buey de mar y servir.\n";

                        addPlato(nombrePlato, receta, idTipoComida, "ensalada_buey_mar.jpg", "https://www.youtube.com/watch?v=81Jo4YGuRCY",true);


            addTipoComida("Sopas");
            idTipoComida=2;
            //Plato sopa castellana(id=4)
            nombrePlato = "Sopa castellana";
            receta = "Pela los ajos, lamínalos y rehógalos en una cazuela grande. Retira la piel del chorizo, córtalo en cuartos y añádelos. Pica " +
                    "el jamón en dados y agrégalos.\n" +
                    "Con ayuda de un cuchillo de sierra, corta el pan e incorpóralo. Rehoga todo bien. Añade el pimentón y remueve todo un poco.\n" +
                    "Cubre con 2 litros de agua y cuece todo conjuntamente durante 20 minutos. Pon a punto de sal.\n" +
                    "Reparte la sopa en 4 cuencos soperos de barro. Casca 1 huevo en cada uno y colócalos en la placa de horno. Hornea a 180ºC durante unos" +
                    " 10 minutos aproximadamente. Sirve y adorna con unas hojas de perejil.\n";

            addPlato(nombrePlato, receta, idTipoComida, "sopa_sopa_castellana", "https://www.youtube.com/watch?v=JHS2yw5_aFE",true);

            //Plato gazpacho (id=5)
            nombrePlato = "Gazpacho";
            receta = "Reserva 1 tomate, 1/2 cebolleta, 1/2 pimiento y 1/2 pepino (pelado) para la guarnición.\n" +
                    "Pica el resto de las verduras (tomates, cebolleta, pimiento, pepino y ajos) y ponlos en una jarra. Pica la miga de pan e incorpórala. Sazona, vierte un " +
                    "chorrito de vinagre y un buen chorro de aceite de oliva. Tritura con la batidora eléctrica y cuela la mezcla. Resérvalo en el frigorífico.\n" +
                    "Corta el pan en daditos y fríelos en una sartén con aceite. Cuando se doren, sácalos a un plato forrado con papel absorbente de cocina. " +
                    "Sirve el gazpacho, riégalo con un chorrito de aceite y acompáñalo con la guarnición de verduras y el pan frito. Decora con perejil.\n";

            addPlato(nombrePlato, receta, idTipoComida, "sopa_gazpacho", "https://www.youtube.com/watch?v=7TrCI6mLT3E",true);

            //Plato salmorejo (id=6)
            nombrePlato = "Salmorejo tradicional";
            receta = "Lava los tomates, córtalos y colócalos en una jarra. Tritúralos bien con la batidora eléctrica. Cuélalos para eliminar la piel y las pepitas.\n" +
                    "Pasa el puré de tomate a la jarra. Trocea la miga de pan e incorpórala. Añade el diente de ajo picado, una pizca de sal y 100 ml de aceite. " +
                    "Tritura todo bien hasta que quede una crema homogénea. Enfríalo en el frigorífico.\n" +
                    "Cuece los huevos en una cazuela con agua durante 5 minutos desde el momento en que empiece a hervir el agua. Refresca, pela y córtalos en 4 gajos.\n";

            addPlato(nombrePlato, receta, idTipoComida, "sopa_salmorejo", "https://www.youtube.com/watch?v=_mb2N9apN2A",true);







            addTipoComida("Carnes");
            idTipoComida=3;




            addTipoComida("Pescado");
            idTipoComida=4;

        }
        }
}
