
import java.util.Scanner;

public class Mastermind {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String continuar = "";
        String codigo = "";
        boolean estadoDeJuego = true;
        Tablero tab;
        Control control;

        do {
            System.out.println("¿Qué tamaño de juego desea? 4/6");
            int tamaño = scan.nextInt();

            /**
             * Ciclo para que se valide si se introducen numeros validos.
             */
            while (tamaño != 4 && tamaño != 6) {
                scan.nextLine();
                System.out.println("No válido. Intente de nuevo");
                tamaño = scan.nextInt();
            }

            tab = new Tablero(tamaño);
            control = new Control(tamaño);
            control.crearCombinacion();
            
            for (int i = 0; i < tamaño; i++) { //Esto es para comprobar que la combinacion se creó bien
                System.out.println(control.getCombinacion()[i].getColor());
            }

            scan.nextLine();
            System.out.println("Colores disponibles: RO, VE, AZ, AM, CA, NA, NE, BL\n");

            for (int i = 0; i < 10; i++) {
                System.out.println("Introduzca los colores para la siguiente fila");
                control.ingresarCombinacionDelJugador();
                //método para poner los colores ingresados en pantalla y mostrar aciertos
                //A su vez se comprobará si la combinacion es o no la misma creada aleatoriamente
            }
            //cuando acabe deberá mostrar la  combinacion final graficamente.
//            scan.nextLine();

            System.out.println("¿Desea jugar de nuevo? ");
            continuar = scan.nextLine().toLowerCase();

            if (continuar.equals("si") != true) {
                System.out.println("Adiós");
                tab.borrarTablero();
                break;
            } else if (continuar.equals("si")) {
                tab.borrarTablero();
            }

        } while (continuar.equals("si"));
    }
}
