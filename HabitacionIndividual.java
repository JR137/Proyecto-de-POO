public class HabitacionIndividual extends Habitaciones{
    public HabitacionIndividual(int numeroHabitacion, double metrosCuadrados, double precio) {
        super(numeroHabitacion, metrosCuadrados, precio);

    }
    @Override
    public String toString() {
        return "Informacion sobre la habitacion individual: numero de habitacion " + getNumeroHabitacion() + ", el tamaño es " + getMetrosCuadrados() +
                " metros cuadrados, precio: " + getPrecio() + " euros ";
    }
}
