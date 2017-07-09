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
    public String getIngredientes(int idPlato) {
        RealmResults<Plato> result= realm.where(Plato.class).equalTo("id",idPlato).findAll();
        return result.get(0).getIngredientes();
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
    public void addPlato(String nombre, String descripcion, int idTipoComida, String ingredientes, String imagen, String enlaceYoutube, Boolean isInAssets) {
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
                        String ingredientes ="1 Bolsa de Cogollos de Tudela\n" +
                                " 1 Paquete de Queso rulo de Cabra\n" +
                                " 2 Latas de Anchoas\n" +
                                " 1 Lata de Pimientos del piquillo\n" +
                                " 1 Lata de Aceitunas\n" +
                                " 1 Unidad de Tomate\n" +
                                "\n" +
                                " 1 Unidad de Cebolla\n" +
                                " 1 Puñado de Perejil\n" +
                                " 1 Pizca de Sal\n" +
                                " 1 Pizca de Pimienta\n" +
                                " 1 Chorro de Aceite de oliva\n" +
                                " 1 Chorro de Vinagre\n";
                        addPlato(nombrePlato, receta, idTipoComida,ingredientes, "ensalada_cogollos_con_tudela_y_anchoas", "https://www.youtube.com/watch?v=gr3LiqwIVx4",true);


                        //Pimientos asados con bonito(id=2)
                        nombrePlato = "Pimientos asados con bonito";
                        receta = "Comenzaremos a hacer esta receta de pimientos asados con ventresca de bonito asando los pimientos.\n" +
                                "Pues bien, cuando los pimientos estén asados y atemperados, y después de pelarlos, los cortamos en tiras" +
                                " y los colocamos sobre una fuente. Añadimos un poco de sal al gusto y removemos.\n" +
                                "A continuación, colocamos los lomos de bonito del norte por encima y regamos con un poco del aceite del mismo" +
                                " o bote o si preferimos con un poco de aceite de oliva virgen extra\n";
                        ingredientes =" 3 Pimientos rojos grandes\n" +
                                " 1 Bote de Bonito del norte en aceite de oliva (de calidad)\n" +
                                " 1 Pizca de Sal al gusto\n" +
                                " 1 Chorro de Aceite de oliva virgen extra\n";
                        addPlato(nombrePlato, receta, idTipoComida,ingredientes, "ensalada_pimientos_asados_con_bonito.jpg", "https://www.youtube.com/watch?v=gh5sdgbvpJU",true);

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
                         ingredientes ="2 Piezas de Buey de mar\n" +
                                 " 2 Huevos duros\n" +
                                 " 1 Cebolla\n" +
                                 " 2 Cogollo de Lechuga\n" +
                                 " 1 Rama de Apio\n" +
                                 " 6 Cucharadas soperas de Mayonesa\n" +
                                 " 1 Cucharadita de Concentrado de Tomate (ketchup)\n" +
                                 " 1 Cucharadita de Brandy\n";
                        addPlato(nombrePlato, receta, idTipoComida,ingredientes, "ensalada_buey_mar.jpg", "https://www.youtube.com/watch?v=81Jo4YGuRCY",true);


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
            ingredientes ="4 huevos\n" +
                    "250 gr de pan español (de víspera)\n" +
                    "250 gr de jamón\n" +
                    "250 gr de chorizo\n" +
                    "8 dientes de ajo\n" +
                    "1 cucharada de pimentón\n" +
                    "Aceite de oliva virgen extra\n" +
                    "Sal\n" +
                    "Perejil\n";
            addPlato(nombrePlato, receta, idTipoComida,ingredientes, "sopa_sopa_castellana", "https://www.youtube.com/watch?v=JHS2yw5_aFE",true);

            //Plato gazpacho (id=5)
            nombrePlato = "Gazpacho";
            receta = "Reserva 1 tomate, 1/2 cebolleta, 1/2 pimiento y 1/2 pepino (pelado) para la guarnición.\n" +
                    "Pica el resto de las verduras (tomates, cebolleta, pimiento, pepino y ajos) y ponlos en una jarra. Pica la miga de pan e incorpórala. Sazona, vierte un " +
                    "chorrito de vinagre y un buen chorro de aceite de oliva. Tritura con la batidora eléctrica y cuela la mezcla. Resérvalo en el frigorífico.\n" +
                    "Corta el pan en daditos y fríelos en una sartén con aceite. Cuando se doren, sácalos a un plato forrado con papel absorbente de cocina. " +
                    "Sirve el gazpacho, riégalo con un chorrito de aceite y acompáñalo con la guarnición de verduras y el pan frito. Decora con perejil.\n";
            ingredientes ="1.200 gr de tomate pera\n" +
                    "1 cebolleta\n" +
                    "1 pepino\n" +
                    "1 pimiento verde\n" +
                    "1 diente de ajo\n" +
                    "30 gr de miga de pan\n" +
                    "3 rebanadas de pan\n" +
                    "Aceite de oliva virgen extra\n" +
                    "Vinagre\n" +
                    "Sal\n";
            addPlato(nombrePlato, receta, idTipoComida, ingredientes, "sopa_gazpacho", "https://www.youtube.com/watch?v=7TrCI6mLT3E",true);

            //Plato salmorejo (id=6)
            nombrePlato = "Salmorejo tradicional";
            receta = "Lava los tomates, córtalos y colócalos en una jarra. Tritúralos bien con la batidora eléctrica. Cuélalos para eliminar la piel y las pepitas.\n" +
                    "Pasa el puré de tomate a la jarra. Trocea la miga de pan e incorpórala. Añade el diente de ajo picado, una pizca de sal y 100 ml de aceite. " +
                    "Tritura todo bien hasta que quede una crema homogénea. Enfríalo en el frigorífico.\n" +
                    "Cuece los huevos en una cazuela con agua durante 5 minutos desde el momento en que empiece a hervir el agua. Refresca, pela y córtalos en 4 gajos.\n";
            ingredientes ="1 kg de tomate\n" +
                    "200 gr de miga de pan\n" +
                    "100 gr de jamón serrano\n" +
                    "8 huevos de codorniz\n" +
                    "1 diente de ajo\n" +
                    "Aceite de oliva virgen extra\n" +
                    "Sal\n";
            addPlato(nombrePlato, receta, idTipoComida,ingredientes, "sopa_salmorejo", "https://www.youtube.com/watch?v=_mb2N9apN2A",true);




            addTipoComida("Carnes");
            idTipoComida=3;
            //Plato  (id=7)
            nombrePlato = "Carne guisada con papas";
            receta = "Corta la carne en dados grandes, salpimienta y dóralos en una sartén con un chorro de aceite. Sácalos, escurre y resérvalos en un plato.\n" +
                    "Pela y pica los ajos, las cebollas y 2 zanahorias en daditos. Ponlos a pochar en una cazuela con aceite. Pica el pimiento y agrégalo. Rehoga todo bien. Añade la carne, vierte el vino y dale un hervor. Incorpora un vaso de agua y la pastilla para guisos de carne desmenuzada. Tapa y cocina todo durante 30-40 minutos.\n" +
                    "Pela las otras zanahorias, córtalas en lonchas de 1 centímetro y cuécelas en un cazo con agua. Una vez cocidas, escurre bien y añádelas al guiso.\n" +
                    "Pela las patatas, córtalas en dados y ponlas a freír en una sartén con aceite. Escurre y añádelas al guiso. Espolvorea con perejil picado y sirve.\n";
            ingredientes ="700 gr de carne de ternera para guisar\n" +
                    "2 patatas\n" +
                    "1 cebolla\n" +
                    "4 zanahorias\n" +
                    "1 pimiento verde\n" +
                    "4 dientes de ajo\n" +
                    "100 ml de vino tinto\n" +
                    "1 pastilla para guisos de carne\n" +
                    "Aceite de oliva virgen extra\n" +
                    "Sal\n" +
                    "Pimienta\n" +
                    "Perejil\n";
            addPlato(nombrePlato, receta, idTipoComida,ingredientes, "carne_carne_guisada_con_carne", "https://www.youtube.com/watch?v=qSMjgjSLhpk",true);


            //Plato  (id=8)
            nombrePlato = "Solomillo Wellington";
            receta = "Salpimienta la carne y séllala por todos los lados hasta que quede dorada. Deja que se enfríe y úntala con la mostaza. Reserva.\n" +
                    "Pica las chalotas y los ajos y ponlos a pochar en una sartén con un chorrito de aceite. Retira la parte inferior de los champiñones y límpialos. Pícalos finamente, incorpóralos a la sartén, sazona y cocínalos durante" +
                    " 10 minutos. Deja que se enfríen. Añade el paté de foie y mezcla todo bien hasta que quede una pasta homogénea. Deja templar.\n" +
                    "Calienta el horno a 200ºC durante 5 minutos. Extiende la masa de hojaldre sobre una placa de horno. Coloca encima, en la parte central, una capa de la mezcla de champiñones y foie. Coloca encima la carne y cúbrela" +
                    " con otra capa de la mezcla de champiñones y foie. Cierra el hojaldre con cuidado, dejando la unión en la parte inferior. Bate el huevo y con ayuda de un pincel pinta la masa (Puedes decorar la superficie con algunos recortes de hojaldre). Hornéalo a 200ºC durante 20 minutos. Deja que repose durante 10 minutos.\n" +
                    "Pon los espárragos en el vaso de la batidora, agrega la leche y un poco de aceite. Tritura. Sirve la carne con la salsa y decora con una ramita de perejil.\n";
            ingredientes ="800 gr de solomillo de ternera\n" +
                    "1 lámina de hojaldre\n" +
                    "200 gr de champiñones\n" +
                    "2 chalotas\n" +
                    "2 dientes de ajo\n" +
                    "1 huevo\n" +
                    "100 gr de paté de foie\n" +
                    "25 gr de mostaza de Dijon\n" +
                    "Aceite de oliva virgen extra\n" +
                    "Sal\n" +
                    "Pimienta\n" +
                    "Perejil\n" +
                    "10 yemas de espárragos blancos\n" +
                    "50 ml de leche\n";
            addPlato(nombrePlato, receta, idTipoComida,ingredientes, "carne_solomillo_wellington", "https://www.youtube.com/watch?v=bNDh4QWbPC4",true);

            //Plato  (id=9)
            nombrePlato = "Rabo de toro";
            receta = "Trocea el rabo y dóralo en una sartén con un chorro de aceite de oliva. Pásalo a la olla rápida.\n" +
                    "Dora la cabeza de ajos entera en la misma sartén. Incorpora el puerro, las zanahorias, la cebolla y el apio, todo finamente picado. Condimenta con sal, pimienta, tomillo y laurel. Agrega el tomate rallado.\n" +
                    "Vierte el brandy sobre el rabo y flambéalo. Añade el vino blanco y el tinto y deja que reduzcan. Vierte un poco de agua e incorpora la verdura pochada.\n" +
                    "Sazona, tapa y cocina durante 25-30 minutos a partir de que la olla coja presión.\n" +
                    "Retira el rabo a un plato y pasa las verduras por el pasapurés. Introduce de nuevo el rabo en la salsa y cocina durante 15-20 minutos más.\n" +
                    "Cuece las coles de Bruselas en una cazuela con abundante agua, sal y perejil durante 15 minutos aproximadamente. Escurre y saltéalas en una sartén con un poco de mantequilla.\n" +
                    "Sirve el rabo con bien de salsa y acompaña con las coles de Bruselas. Decora con una ramita de tomillo.\n";
            ingredientes ="1 rabo de vacuno\n" +
                    "12-14 coles de Bruselas\n" +
                    "2 zanahorias\n" +
                    "1 cebolla\n" +
                    "1 cabeza de ajos\n" +
                    "1 puerro\n" +
                    "1 rama de apio\n" +
                    "1-2 tomates\n" +
                    "1 vaso de vino blanco\n" +
                    "1 vaso de vino tinto\n" +
                    "1 copa de brandy\n" +
                    "1 nuez de mantequilla\n" +
                    "Agua\n" +
                    "Aceite de oliva\n" +
                    "Sal\n" +
                    "20 bolas de pimienta\n" +
                    "2 hojas de laurel\n" +
                    "1 rama de tomillo\n";
            addPlato(nombrePlato, receta, idTipoComida,ingredientes, "carne_rabo_de_toro", "https://www.youtube.com/watch?v=rGmXY2fy1Ow",true);




            addTipoComida("Pescados");
            idTipoComida=4;
            //Plato  (id=10)
            nombrePlato = "Salmon encebollado";
            receta = "Corta la parte dura de los tallos de los espárragos y trocéalos. Quítales el rabito a las vainas y pícalas finamente. Cuece los espárragos y las vainas al vapor durante 10-12 minutos.\n" +
                    "Pica el ajo y corta las cebollas en medias lunas y sofríe todo en una sartén con un chorrito de aceite. Sazona y deja que la cebolla se caramelice.\n" +
                    "Salpimienta las rodajas de salmón e incorpóralas a la sartén. Tapa y deja que se cocine unos 5 minutos.\n" +
                    "Emplata el salmón con la cebolla y acompaña con los espárragos y las vainas. Dale un toque de tamari a las verduras y espolvorea el pescado con perejil picado.\n";
            ingredientes ="2 rodajas de salmón\n" +
                    "2 cebollas\n" +
                    "1 diente de ajo\n" +
                    "6 espárragos trigueros\n" +
                    "6 judías verdes\n" +
                    "Aceite de oliva\n" +
                    "Tamari\n" +
                    "Sal marina\n" +
                    "Pimienta\n" +
                    "Perejil\n";
            addPlato(nombrePlato, receta, idTipoComida,ingredientes, "pescado_salmon_encebollado", "https://www.youtube.com/watch?v=ZnCb9p0R0-Y",true);



            //Plato  (id=11)
            nombrePlato = "Boquerones en vinagre";
            receta = "Limpia las anchoas retirando la cabeza y eliminando bien las vísceras y espina central. Congélalas durante 48 horas.\n" +
                    "Descongela las anchoas y colócalas en un recipiente con la piel hacia abajo. Sazona. Mezcla el vinagre y el agua, cubre las anchoas y déjalas marinando durante 2-4 horas.\n" +
                    "Escúrrelas bien. Pela y pica los ajos finamente y repártelos sobre las anchoas. Cúbrelas con aceite de oliva virgen extra, espolvorea con perejil picado y deja macerar.\n" +
                    "Pela las patatas y córtalas en lonchas finas con ayuda de una mandolina. Ponlas a remojo en un bol con agua para que suelten el almidón y no se peguen entre sí durante la fritura. Escúrrelas bien y sécalas con papel de cocina.\n" +
                    "Pon abundante aceite a calentar en una sartén. Agrega las patatas y fríelas a fuego no muy fuerte. Escúrrelas sobre un plato forrado con papel absorbente de cocina. Sazona.\n" +
                    "Sirve las anchoas y acompáñalas con las patatas y unas aceitunas rellenas. Decora con una ramita de perejil.\n";
            ingredientes ="1 kg de anchoas\n" +
                    "2 patatas\n" +
                    "40 aceitunas rellenas\n" +
                    "3 dientes de ajo\n" +
                    "400 ml de vinagre de vino blanco\n" +
                    "100 ml de agua\n" +
                    "Aceite de oliva virgen extra\n" +
                    "Sal\n" +
                    "Perejil\n";
            addPlato(nombrePlato, receta, idTipoComida,ingredientes, "pescado_boquerones_en_vinagre", "https://www.youtube.com/watch?v=cBxe7SsExEc",true);
            //Plato  (id=12)
            nombrePlato = "Cazon en adobo";
            receta = "Limpia el cazón retirándole la piel y las espinas. Córtalo en trozos de bocado y colócalos en un bol.\n" +
                    "Maja en el mortero 2 dientes de ajo con una pizca de sal. Agrega el orégano, el pimentón y el comino. Mezcla bien. Añade 200 ml de vinagre y viértelo sobre el cazón. Agrega las hojas de laurel y deja que macere durante 6 horas.\n" +
                    "Escurre los trozos de pescado y sécalos con papel absorbente de cocina. Pásalos por harina y fríelos en una sartén con abundante aceite caliente y 1 diente de ajo sin pelar. Escúrrelos.\n" +
                    "Lava las hojas de lechuga, trocea y sécalas. Colócalas en una fuente grande y alíñalas con aceite, vinagre y sal. Sirve el cazón y acompáñalo con la ensalada y los picos de pan. Adorna con unas hojas de perejil.\n";
            ingredientes ="1/2 kg de cazón\n" +
                    "150 gr de picos de pan\n" +
                    "1 lechuga\n" +
                    "3 dientes de ajo\n" +
                    "Harina especial para freír pescado\n" +
                    "Aceite de oliva virgen extra\n" +
                    "Vinagre de jerez\n" +
                    "Sal\n" +
                    "1 cucharadita de pimentón\n" +
                    "1 cucharadita de orégano\n" +
                    "1/2 cucharadita de comino\n" +
                    "2 hojas de laurel\n" +
                    "Perejil\n";
            addPlato(nombrePlato, receta, idTipoComida,ingredientes, "pescado_cazon_en_adobo", "https://www.youtube.com/watch?v=rQi-DEEiEss",true);
        }
        }
}
