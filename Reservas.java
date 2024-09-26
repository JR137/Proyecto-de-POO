import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
public class Reservas {
    private int numeroReserva;
    private LocalDate entrada;
    private LocalDate salida;
    private ArrayList<Clientes>clientes;
    private ArrayList<Habitaciones> habitaciones;

    public Reservas(int numeroReserva, LocalDate entrada, LocalDate salida) {
        this.numeroReserva=numeroReserva;
        this.entrada=entrada;
        this.salida=salida;
        this.clientes= new ArrayList<>();
        this.habitaciones= new ArrayList<>();
    }
    public int getNumeroReserva() {
        return numeroReserva;
    }
    public LocalDate getEntrada() {
        return entrada;
    }
    public LocalDate getSalida() {
        return salida;
    }
    public ArrayList<Clientes> getClientes() {
        return clientes;
    }

    public ArrayList<Habitaciones> getHabitaciones() {
        return habitaciones;
    }
    public void setNumeroReserva(int numeroReserva) {
        this.numeroReserva = numeroReserva;
    }

    public void setEntrada(LocalDate entrada) {
        this.entrada = entrada;
    }

    public void setSalida(LocalDate salida) {
        this.salida = salida;
    }

    public void setClientes(ArrayList<Clientes> clientes) {
        this.clientes = clientes;
    }

    public void setHabitaciones(ArrayList<Habitaciones> habitaciones) {
        this.habitaciones = habitaciones;
    }
    public void añadirCliente(Clientes c) {
        clientes.add(c);
    }
    public void añadirHabitacion(Habitaciones h) {
        habitaciones.add(h);
    }
    //Obtener la ultima reserva
    public static int ultimaReserva(Hotel hotel) {
        int numeroUltimaReserva = 0;
        ArrayList<Reservas>reservas = hotel.getReservas();
        numeroUltimaReserva = (reservas.get(reservas.size() - 1).getNumeroReserva()) + 1;
        return numeroUltimaReserva;
    }
    //Analizar LocalDate
    public static LocalDate analizarFecha(String string) {
            LocalDate analizarDate = null;
            do try {
                analizarDate = LocalDate.parse(string);
            } catch (DateTimeParseException e) {
                System.out.println("Introducir un valor de fecha valida (YYY-mm-dd)" + e);
            }   while (analizarDate == null);

            return analizarDate;
        }
    //Comprobar si la habitacion esta disponible para la reserva
    public static boolean habitacionDisponible (LocalDate entradaReservaNueva, LocalDate salidaNuevaReserva) {
        return entradaReservaNueva.isAfter(salidaNuevaReserva) &&
                entradaReservaNueva.isEqual(salidaNuevaReserva);
    }
    public static void borrarReserva(Hotel hotel, int numeroReserva) {
        for (Reservas reservas : hotel.getReservas()) {
            if (reservas.getNumeroReserva() == numeroReserva) {
                hotel.borrarReserva(reservas);
                break;
            }
        }
    }

    @Override
    public String toString() {
        return "Numero de la reserva: " + this.numeroReserva + ", el cliente es " + this.clientes +
                " en la habitacion " + this.habitaciones + ", fecha de entrada: " + this.entrada + ", fecha de salida: " + this.salida;
    }
}


