
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
        ArrayList<Canica> resp = new ArrayList(tamaño); //Es un ArrayList que contiene los utlimos 4 colores usados
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
        if (tablero.add(resp)) { //Si se pudo agregar, entonces lo añadimos a las respuestas
            for (Canica ca : resp) {
                respuestas.add(ca);
            }
            turno++; //sumamos 1 turno al jugador
        }

        return respuestas; //retorna todas las combinaciones.
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
    
    /*
    * Compara y regresa cuantos colores se repitieron
    */
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

    /*
    * Este método cuenta los aciertos que hizo el jugador en sus combinaciones 
    * comparando sus ingresos con la combinacion aleatoria creada al inicio del juego
    * Se guardan numeros en un ArrayList, si es un 2 la posicion es correcta, si es 
    * 1 el color es correcto pero no la posicion, y si es 0 no está.
    */
    public void contarAciertos() {
        int[] coloresCombinacion = {0,0,0,0,0,0,0,0}; //Un vector para contar los colores encontrados en la combinacion
        int[] coloresRespuesta = {0,0,0,0,0,0,0,0}; //Un vector para contar los colores encontrados en la respuesta 

        for (int i = 0; i < tamaño; i++) { //Suma 1 al color que encuentre
            switch (obtenerRespuesta().get(i).getCodigo()) {
                case "RO" ->
                    coloresRespuesta[0]++;
                case "VE" ->
                    coloresRespuesta[1]++;
                case "AZ" ->
                    coloresRespuesta[2]++;
                case "AM" ->
                    coloresRespuesta[3]++;
                case "CA" ->
                    coloresRespuesta[4]++;
                case "NA" ->
                    coloresRespuesta[5]++;
                case "NE" ->
                    coloresRespuesta[6]++;
                case "BL" ->
                    coloresRespuesta[7]++;
            }

            switch (combinacion[i].getCodigo()) { 
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

        
        int colCorrectos = 0, posCorrectas = 0; //Cuenta los colores y posiciones correctas

        //Compara la combinacion final con la respuesta ingresada
        for (int i = 0; i < 8; i++) {
            colCorrectos += comparar(coloresCombinacion[i], coloresRespuesta[i]);
        }

        //Cuenta las posiciones correctas
        for (int i = 0; i < tamaño; i++) {
            Canica respuesta = combinacion[i];
            Canica combinacion = obtenerRespuesta().get(i);
            if (respuesta.getColor().equals(combinacion.getColor())) 
            {
                posCorrectas++; //Si al comparar se encuentrra que son iguales se agrega
            }
        }

        //Para las posiciones correctas
        for (int i = 0; i < posCorrectas; i++) {
            aciertos[turno - 1][i] = 2;
        }
        //Para los colores correctos
        for (int i = posCorrectas; i < colCorrectos; i++) {
            aciertos[turno - 1][i] = 1;
        }
        //Cuando no hay nada
        for (int i = colCorrectos; i < tamaño; i++) {
            aciertos[turno - 1][i] = 0;
        }
    }

    public int[][] getAciertos() {
        return aciertos;
    }

    //Con esto verificamos si se adivinó el color de la combinacion
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
