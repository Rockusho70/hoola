package mx.hoola.hoola.Models.List;

/**
 * Created by Isaac on 14/02/2017.
 */

public class ListInmuebles {
    private int id;
    private String titulo;
    private String habitacion;
    private String piso;
    private String banio;
    private String estacionamiento;
    private String precio;
    private String imagen;
    private String terreno;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(String habitacion) {
        this.habitacion = habitacion;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getBanio() {
        return banio;
    }

    public void setBanio(String banio) {
        this.banio = banio;
    }

    public String getEstacionamiento() {
        return estacionamiento;
    }

    public void setEstacionamiento(String estacionamiento) {
        this.estacionamiento = estacionamiento;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getTerreno() {
        return terreno;
    }

    public void setTerreno(String terreno) {
        this.terreno = terreno;
    }
}
