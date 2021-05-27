
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Control {

    private Canica combinacion[]; //Guarda la combinacion final que se debe adivinar
    private final String colores[] = {"RO", "VE", "AZ", "AM", "CA", "NA", "NE", "BL"};
    Random rnd = new Random();
    private int tamaño;
    private ArrayList<Canica> respuestas; //Se guardan las respuestas del jugador

    public Control(int tamaño) {
        this.tamaño = tamaño;
        combinacion = new Canica[tamaño];
        respuestas = new ArrayList();
        crearCombinacion();
    }

    //Crea una combinacion de canicas,la cual es la que el usuario deberá adivinar
    public Canica[] crearCombinacion() {
        for (int i = 0; i < tamaño; i++) {
            combinacion[i] = (new Canica(colores[rnd.nextInt(8)]));
        }
        return combinacion;
    }

    public Canica[] getCombinacion() {
        return combinacion;
    }

    public ArrayList<Canica> ingresarCombinacionDelJugador() {
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
            respuestas.add(new Canica(codigo)); //Crea la canica con el color indicado
        }
        return respuestas;
    }

    public ArrayList<Canica> getRespuestas() {
        return respuestas;
    }

    public boolean comprobarCombinacion() {
        boolean combEncontrada = false;
        //Se haría el procedimiento para comprobar si ya se hizo esa combinación.
        return combEncontrada;
    }
}
