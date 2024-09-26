public abstract class Habitaciones {
    private int numeroHabitacion;
    private double metrosCuadrados;
    private double precio;
    public Habitaciones(int numeroHabitacion, double precio, double metrosCuadrados) {
        this.numeroHabitacion = numeroHabitacion;
        this.precio = precio;
        this.metrosCuadrados = metrosCuadrados;
    }
    public int getNumeroHabitacion() {

        return numeroHabitacion;
    }
    public double getMetrosCuadrados() {
        return metrosCuadrados;
    }

    public double getPrecio() {
        return precio;
    }

    public void setNumeroHabitacion(int numeroHabitacion) {

        this. numeroHabitacion = numeroHabitacion;
    }

    public void setMetrosCuadrados(double metrosCuadrados) {
        this.metrosCuadrados = metrosCuadrados;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    //Comprobamos si la habitacion existe
    static boolean existeHabitacion(int numeroHabitacion, Hotel hotel) {
    boolean existe = false;
        for (Habitaciones habitaciones : hotel.getHabitaciones()) {
            if (habitaciones.getNumeroHabitacion() == numeroHabitacion) {
                existe = true;
                break;
            }
        }
        return existe;
    }
    //Borrar una habitacion existente
    static void borrarHabitacion (Hotel hotel, int numeroHabitacion) {
        for (Habitaciones habitaciones : hotel.getHabitaciones()) {
            if (habitaciones.getNumeroHabitacion() == numeroHabitacion) {
                hotel.borrarHabitacion(habitaciones);
                break;
            }
        }
    }
}
