
public class Canica {

    private String color;
    private String codigo;

    public Canica(String codigo) {
        this.codigo = codigo;
        this.color = escogerColor();
    }

    public String escogerColor() {
        color = "";

        switch (codigo) {
            case "RO" -> color = "red";
            case "VE" -> color = "green";
            case "AZ" -> color = "blue";
            case "AM" -> color = "yellow";
            case "CA" -> color = "brown";
            case "NA" -> color = "orange";
            case "NE" -> color = "black";
            case "BL" -> color = "white";
        }

        return color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
