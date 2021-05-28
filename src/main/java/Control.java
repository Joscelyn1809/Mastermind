
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class Control {

    private Canica combinacion[]; //Guarda la combinacion final que se debe adivinar
    private final String colores[] = {"RO", "VE", "AZ", "AM", "CA", "NA", "NE", "BL"};
    Random rnd = new Random();
    private int tamaño;
    private ArrayList<Canica> respuestas; //Se guardan todas las respuestas del jugador
    private int turno = 0;
    private HashSet<ArrayList<Canica>> tablero; //Un HashSet para que no se repitan las respuestas del jugador.
    private int[][] aciertos; //Nos ayudará a poner mas facilmente los colores correspondientes en los espacios  

    /*
     * En el constructor recibimos el tamaño del juego para crearlo correctamente
     * ademas de crear los vectores, ArrayList, matrices y HashSet correspondientes.
     * Después mandamos llamar el método para crear una combinación la cual es la 
     * que adivinará el jugador.
     */
    public Control(int tamaño) {
        this.tamaño = tamaño;
        combinacion = new Canica[tamaño];
        respuestas = new ArrayList();
        tablero = new HashSet(10);
        aciertos = new int[10][tamaño];
        crearCombinacion();
    }

    /*Crea una combinacion de canicas,la cual es la que el usuario deberá adivinar
    * y la crea según el tamaño del juego y retorna ese vector.
     */
    public Canica[] crearCombinacion() {
        for (int i = 0; i < tamaño; i++) {
            combinacion[i] = (new Canica(colores[rnd.nextInt(8)]));
        }
        return combinacion;
    }

    public Canica[] getCombinacion() {
        return combinacion;
    }

    /*
     * Este metodo nos ayuda a dejar un poco mas limpio el main haciendo aquí todo
     * el proceso de llamada e ingresado de variables para las respuestas.
     * Retorna un ArrayList 
     */
    public ArrayList<Canica> ingresarCombinacionDelJugador() {
        ArrayList<Canica> resp = new ArrayList(tamaño);
        String codigo = "";
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < tamaño; i++) { //Para que introduzca el numero de colores segun el tamaño de juego

            System.out.println("Introduzca un color: ");
            codigo = scan.nextLine().toUpperCase();
            while (!codigo.equals("RO") && !codigo.equals("VE") && !codigo.equals("AZ") //comprueba colores correctos
                    && !codigo.equals("AM") && !codigo.equals("CA") && !codigo.equals("NA")
                    && !codigo.equals("NE") && !codigo.equals("BL")) {

                System.out.println("No válido. Intente de nuevo");
                codigo = scan.nextLine().toUpperCase();
            }
            resp.add(new Canica(codigo)); //Crea la canica con el color indicado y la añade a un nuevo ArrayList 
        }
        if (tablero.add(resp)) {
            for (Canica ca : resp) {
                respuestas.add(ca);
            }
        }
        turno++; //sumamos 1 turno al jugador
        return respuestas;                          //por qué hay resp y respuestas
    }

    public ArrayList<Canica> getRespuestas() {
        return respuestas;
    }

    /*
     * Como el ArrayListt de respuestas guarda todas las combinaciones del usuario, este metodo
     * nos permite ver las ultimas 4 combinaciones introducidas por el usuario y las retorna.
     */
    public ArrayList<Canica> obtenerRespuesta() {
        ArrayList<Canica> resp = new ArrayList();
        for (int i = respuestas.size() - tamaño; i < respuestas.size(); i++) {
            resp.add(respuestas.get(i));
        }
        return resp;
    }

//    /*
//     * Este metodo compara 
//     */
//    private int compararColores(int comb, int respuesta) {
//        int colCorrecto = 0;
//        if (comb > 0 && respuesta > 0) {
//            if (comb == respuesta) {
//                colCorrecto++;
//            }
//        }
//        return colCorrecto;
//    }
    private int comparar(int comb, int resp) {
        int colores = 0;
        if (comb > 0 && resp > 0) {
            if (comb == resp) {
                colores += resp;
            } else if (resp > comb) {
                colores += comb;
            } else {
                colores += resp;
            }
        }
        return colores;
    }

    /**
     * Metodo para contar aciertos de la ultima combinacion ingresada cuenta los
     * colores de la combinacion y los colores de la respuesta y los compara
     * para sacar cuantos colores son iguales. Luego, cuenta cuantos colores y
     * posiciones son iguales. Con esos datos añade ya sea 2, 1 o 0 al array de
     * aciertos.
     */
    public void contarAciertos() {
        //Se crea un vector con contadores para cada color de canica de combinacion
        int[] coloresCombinacion = new int[8];
        //Se crea un vector con contadores para los colores de la respuesta
        int[] coloresRespuesta = new int[8];

        //con este ciclo ponemos un 1 en el vector cada que identifique un color
        for (int i = 0; i < tamaño; i++) {
            //Se cuentan los colores de la combinacion ingresada
            switch (obtenerRespuesta().get(i).getColor()) {
                case "RO" ->
                    coloresCombinacion[0]++;
                case "VE" ->
                    coloresCombinacion[1]++;
                case "AZ" ->
                    coloresCombinacion[2]++;
                case "AM" ->
                    coloresCombinacion[3]++;
                case "CA" ->
                    coloresCombinacion[4]++;
                case "NA" ->
                    coloresCombinacion[5]++;
                case "NE" ->
                    coloresCombinacion[6]++;
                case "BL" ->
                    coloresCombinacion[7]++;
            }

            //Se cuentan los colores de la respuesta
            switch (combinacion[i].getColor()) {
                case "RO" ->
                    coloresCombinacion[0]++;
                case "VE" ->
                    coloresCombinacion[1]++;
                case "AZ" ->
                    coloresCombinacion[2]++;
                case "AM" ->
                    coloresCombinacion[3]++;
                case "CA" ->
                    coloresCombinacion[4]++;
                case "NA" ->
                    coloresCombinacion[5]++;
                case "NE" ->
                    coloresCombinacion[6]++;
                case "BL" ->
                    coloresCombinacion[7]++;
            }
        }

        //contadores de colores y posiciones correctas
        int colCorrectos = 0, posCorrectas = 0;

        //Ciclo que compara los colores de combinacion con respuesta,
        //para saber que colores son acordes con la respuesta
        for (int i = 0; i < 8; i++) {
            colCorrectos += comparar(coloresCombinacion[i], coloresRespuesta[i]);
        }

        //Ciclo para saber cuantas canicas tienes el color y posicion correcto
        for (int i = 0; i < tamaño; i++) {
            Canica respuesta = combinacion[i]; //guardamos las canicas en posicion i
            Canica combinacion = obtenerRespuesta().get(i);// en canicas auxiliares; respuesta y combinacion
            if (respuesta.getColor().equals(combinacion.getColor())) //comparamas si son son iguales
            {
                posCorrectas++; //si estan en la misma posicion y son iguales, incrementa contador
            }
        }

        //Ciclos para añadir valores en el array de aciertos
        //agrega un 2 en las posiciones i menores al contador
        //de posiciones correctas
        for (int i = 0; i < posCorrectas; i++) {
            aciertos[turno - 1][i] = 2;
        }
        //retoma la posicion en la que se quedó el ciclo pasado
        //e introduce un 1 en las posiciones i menores a colores correctos
        for (int i = posCorrectas; i < colCorrectos; i++) {
            aciertos[turno - 1][i] = 1;
        }
        //retoma la posiciones en la que se quedó el ciclo pasado
        //e introduce 0 en las posiciones restantes
        for (int i = colCorrectos; i < tamaño; i++) {
            aciertos[turno - 1][i] = 0;
        }
    }

    public int[][] getAciertos() {
        return aciertos;
    }

    public boolean hayVictoria() {
        int contador = 0;
        for (int i = 0; i < tamaño; i++) {
            if (obtenerRespuesta().get(i).getColor().equals(combinacion[i].getColor())) {
                contador++;
            }
        }
        if (contador == tamaño) {
            return true;
        } else {
            return false;
        }
    }
}
