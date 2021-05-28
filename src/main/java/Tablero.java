
import java.util.ArrayList;

public class Tablero {

    private Circle[][] espacios;
    private Circle[][] colores;
    private Circle[][] aciertos;
    private Rectangulo t1;
    private Rectangulo t2;
    private int tamaño;
    private static final String[] COLORES = {"red", "green", "blue", "yellow", "brown", "orange", "black", "white"};

    public Tablero(int tamaño) {
        espacios = new Circle[11][tamaño];
        colores = new Circle[4][2];
        aciertos = new Circle[10][tamaño];
        this.tamaño = tamaño;
        crearTablero(tamaño);
    }

    /* Crea el tablero parte por parte 
     */
    public void crearTablero(int tamaño) {
        int x = 315, y = 430;

        if (tamaño == 4) { //Si el tamaño deseado es de 4 canicas

            t1 = new Rectangulo(220, 525, 300, 50);
            t1.changeColor("beige");

            t2 = new Rectangulo(145, 355, 310, 110);
            t2.changeColor("cafe claro");

            for (int i = 0; i < 11; i++) { //ciclos para crear los espacios de las canicas donde se escogeran los colores
                for (int j = 0; j < tamaño; j++) {
                    if (i == 10) { //si se llega a la fila 10 va a colocar espacios blancos donde van los colores aleatorios
                        Circle c1 = new Circle(x, 65);
                        c1.changeColor("white");
                        espacios[i][j] = c1;
                        x += 35;
                    } else { //mientas no sea 10 crea los demas espacios
                        Circle c1 = new Circle(x, y);
                        espacios[i][j] = c1;
                        x += 35;
                    }
                }
                x = 315;
                y -= 35;
            }

            x = 460;
            y = 432;
            for (int i = 0; i < 10; i++) { //estos ciclos crean los espacios de los aciertos

                for (int j = 0; j < tamaño; j++) {
                    if (j == tamaño / 2) {
                        x = 460;
                        y += 15;
                    }
                    Circle a = new Circle(x, y);
                    a.changeSize(12);
                    aciertos[i][j] = a;
                    x += 15;
                }
                x = 460;
                y -= 50;
            }

        } else if (tamaño == 6) { //Cuando el tamaño deseado sea 6

            t1 = new Rectangulo(275, 525, 280, 50);
            t1.changeColor("beige");

            t2 = new Rectangulo(215, 370, 290, 100);
            t2.changeColor("cafe claro");

            for (int i = 0; i < 11; i++) { //Espacios para jugar
                x = 295;
                for (int j = 0; j < tamaño; j++) {
                    if (i == 10) {
                        Circle c1 = new Circle(x, 65);
                        c1.changeColor("white");
                        espacios[i][j] = c1;
                        x += 35;
                    } else {
                        Circle c1 = new Circle(x, y);
                        espacios[i][j] = c1;
                        x += 35;
                    }
                }
                y -= 35;
            }

            x = 510;
            y = 433;
            for (int i = 0; i < 10; i++) { //Espacios de los aciertos

                for (int j = 0; j < tamaño; j++) {
                    if (j == tamaño / 2) {
                        x = 510;
                        y += 15;
                    }
                    Circle a = new Circle(x, y);
                    a.changeSize(12);
                    aciertos[i][j] = a;
                    x += 15;
                }
                x = 510;
                y -= 50;
            }
        }

        int m = 315, n = 520, c = 0;
        for (int i = 0; i < 2; i++) { //Aquí se crean los colores al final del tablero para que se sepa cuales son los colores disponibles
            for (int j = 0; j < 4; j++) {
                Circle color = new Circle(m, n);
                color.changeSize(40);
                color.changeColor(COLORES[c]);
                c++;
                colores[j][i] = color;
                m += 45;
            }
            m = 315;
            n -= 45;
        }
    }

    /*
     * Nos ayuda a borrar el tablero cuando queremos volver a jugar
     */
    public void borrarTablero() {
        t1.makeInvisible();
        t2.makeInvisible();

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < tamaño; j++) {
                espacios[i][j].makeInvisible();
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                colores[i][j].makeInvisible();
            }
        }

        for (int i = 0; i < 10 * 2; i++) {
            for (int j = 0; j < tamaño / 2; j++) {
                aciertos[i][j].makeInvisible();
            }
        }
    }

    /*
     * Este método recorre los espacios del tablero y pinta de los colores deseados
     * por el jugador los espacios necesarios
    */
    public void pintarCanicas(ArrayList<Canica> canicas) {
        int contadorCanicas = 0;

        for (int i = 0; i < canicas.size() / tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                Canica can = canicas.get(contadorCanicas);
                contadorCanicas++;
                espacios[i][j].changeColor(can.getColor());
            }
        }
    }

    /*
     * Colorea la respuesta en los ultimos espacios del ArrayList de espacios 
     * Para mostrarlo en pantalla
    */
    public void pintarRespuesta(Canica[] resp) {
        for (int i = 0; i < tamaño; i++) {
            espacios[10][i].changeColor(resp[i].getColor());
        }
    }

    /*
     * Segun los numeros guardados en el método de contar aciertos en Control 
     * se pintan del color necesario.
    */
    public void pintarAciertos(int[][] aciertos) {
        for (int i = 0; i < aciertos.length; i++) {
            for (int j = 0; j < aciertos[i].length; j++) {
                if (aciertos[i][j] == 2) {
                    this.aciertos[i][j].changeColor("red");
                } else if (aciertos[i][j] == 1) {
                    this.aciertos[i][j].changeColor("white");
                }
            }
        }
    }

}
