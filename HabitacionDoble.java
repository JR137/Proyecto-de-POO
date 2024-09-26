public class HabitacionDoble extends Habitaciones{
    private String estiloCama;

    public HabitacionDoble(int numeroHabitacion, double metrosCuadrados, double precio, String estiloCama) {
        super(numeroHabitacion, metrosCuadrados, precio);
        this.estiloCama = estiloCama;
    }

    public String getEstiloCama() {
        return estiloCama;
    }
    public void setEstiloCama(String estiloCama) {
        this.estiloCama = estiloCama;
    }

    @Override
    public String toString() {
        return "Indormacion sobre la habitacion doble: numero de habitacion " + getNumeroHabitacion() + ", el tamaño es " + getMetrosCuadrados() +
                " metros cuadrados, la cama es " + this.estiloCama + ", y el precio de la habitacion es " + getPrecio() + " euros ";
    }
}
