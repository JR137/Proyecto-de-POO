public class HabitacionSuite extends Habitaciones {
    private double metrosCuadradosDormitorio;

    public HabitacionSuite(int numeroHabitacion, double precio, double metrosCuadradosDormitorio, double metrosCuadrados) {
        super(numeroHabitacion, metrosCuadrados, precio);
        this.metrosCuadradosDormitorio = metrosCuadradosDormitorio;
    }

    public double getMetrosCuadradosDormitorio() {
        return metrosCuadradosDormitorio;
    }

    public void setMetrosCuadradosDormitorio(double metrosCuadradosDormitorio) {
        this.metrosCuadradosDormitorio = metrosCuadradosDormitorio;
    }

    @Override
    public String toString() {
        return "Informacion de la suite: numero de habitacion " + getNumeroHabitacion() + ", metros cuadrados del dormitorio: " +
                this.metrosCuadradosDormitorio + ", metros cuadrados de la sala: " + getMetrosCuadrados() +
                ", el precio es " + getPrecio() + " euros ";
    }
}
