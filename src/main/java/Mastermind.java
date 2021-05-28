
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
                tab.pintarCanicas(control.ingresarCombinacionDelJugador()); //Pinta las canicas con la combinacion que se dio
                control.contarAciertos(); //Cuenta los aciertos
                tab.pintarAciertos(control.getAciertos()); //Se pintan los aciertos
                if(control.hayVictoria()){ //Verifica que no se haya ingresado ya la combinacion correcta
                    System.out.println("DING DING DING TENEMOS UN GANADOR");
                    break;
                }
            }
         
            tab.pintarRespuesta(control.getCombinacion()); //Cuando termine pintamos la combinacion al final de tablero
            System.out.println("¿Desea jugar de nuevo? ");
            continuar = scan.nextLine().toLowerCase();

            if (continuar.equals("si") != true) {
                System.out.println("Adiós");
                break;
            } else {
                tab.borrarTablero();
            }
        } while (continuar.equals("si"));
    }
}
