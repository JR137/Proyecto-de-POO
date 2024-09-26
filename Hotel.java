import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

public class Hotel {
    private String nombreHotel;
    private String direccion;
    private String ciudad;
    private ArrayList<Clientes> cliente;
    private ArrayList<Reservas> reserva;
    private ArrayList<Habitaciones> habitacion;

    public Hotel(String nombreHotel, String direccion, String ciudad) {
        this.nombreHotel = nombreHotel;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.cliente = new ArrayList<>();
        this.reserva = new ArrayList<>();
        this.habitacion = new ArrayList<>();
    }

    public String getNombreHotel() {
        return nombreHotel;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public ArrayList<Clientes> getClientes() {
        return cliente;
    }

    public ArrayList<Reservas> getReservas() {
        return reserva;
    }

    public ArrayList<Habitaciones> getHabitaciones() {
        return habitacion;
    }

    public void setNombreHotel(String nombreHotel) {
        this.nombreHotel = nombreHotel;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setClientes(ArrayList<Clientes> clientes) {
        this.cliente = clientes;
    }

    public void setReservas(ArrayList<Reservas> reservas) {
        this.reserva = reserva;
    }

    public void setHabitaciones(ArrayList<Habitaciones> habitaciones) {
        this.habitacion = habitacion;
    }

    // Metodos de clases
    // Obtener el clinete de la arrayList con el ID
    public Clientes getClientes(String ID) {
        Clientes clientes = null;
        boolean encontrado = false;
        Iterator<Clientes> it = cliente.iterator();
        while (!encontrado && it.hasNext()){
            Clientes c = it.next();
            if (c.getID().equals(ID)) {
                clientes = c;
                encontrado = true;
            }
        }
        return clientes;
    }

    public Habitaciones getHabitaciones(int numero) {
        Habitaciones habitaciones = null;
        boolean encontrado = false;
        Iterator<Habitaciones> it = habitacion.iterator();
        while (!encontrado && it.hasNext()) {
            Habitaciones r = it.next();
            if (r.getNumeroHabitacion() == numero) {
                habitaciones = r;
                encontrado = true;
            }
        }
        return habitaciones;
    }

    // Metodos para añadir al arrayList
    public void añadirReservas(Reservas reservas) {reserva.add(reservas);}

    public void añadirClientes(Clientes clientes) {cliente.add(clientes);}

    public void añadirHabitacion(Habitaciones habitaciones) { habitacion.add(habitaciones);}

    // Metodos para borrar del arrayList
    public void borrarReserva(Reservas reservas) {reserva.remove(reservas);}

    public void borrarCliente(Clientes clientes) {cliente.remove(clientes);}

    public void borrarHabitacion(Habitaciones habitaciones) {habitacion.remove(habitaciones);}

    // Limpiar informacion en el arrayList
    public void borrarDatos() {
        reserva.clear();
        habitacion.clear();
        cliente.clear();
    }

    // Precarga de informacion en la aplicacion
    public static void cargarDatos(Hotel hotel) {
        HabitacionIndividual Individual;
        HabitacionDoble Doble;
        HabitacionSuite Suite;
        Habitaciones habitaciones;
        Clientes clientes;
        Reservas reservas;

        hotel.borrarDatos();

        // Habitaciones
        Individual = new HabitacionIndividual(1, 24.5, 80.4);
        hotel.añadirHabitacion(Individual);
        Individual = new HabitacionIndividual(4, 12.5, 78.3);
        hotel.añadirHabitacion(Individual);
        Individual = new HabitacionIndividual(5, 20.4, 79.4);
        hotel.añadirHabitacion(Individual);
        Doble = new  HabitacionDoble(2, 30.1, 100.76, "cama mediana");
        hotel.añadirHabitacion(Doble);
        Doble = new  HabitacionDoble(6, 30.1, 100.76, "cama mediana");
        hotel.añadirHabitacion(Doble);
        Doble = new  HabitacionDoble(7, 30.1, 100.76, "cama mediana");
        hotel.añadirHabitacion(Doble);
        Suite = new HabitacionSuite(3, 30.8, 24.5, 145.89);
        hotel.añadirHabitacion(Suite);
        Suite = new HabitacionSuite(8, 30.8, 24.5, 145.89);
        hotel.añadirHabitacion(Suite);
        Suite = new HabitacionSuite(9, 30.8, 24.5, 145.89);
        hotel.añadirHabitacion(Suite);

        // Clientes
        clientes = new Clientes("Diego", "Garcia Lopez", "2345673A");
        hotel.añadirClientes(clientes);
        clientes = new Clientes("Nicolas", "Prado Sanchez", "33529651R");
        hotel.añadirClientes(clientes);
        clientes = new Clientes("Paula", "Garcia Perez", "1234567Q");
        hotel.añadirClientes(clientes);
        //Reservas
        clientes = hotel.getClientes("2345673A");
        LocalDate entrada = LocalDate.of(2021, 12, 22);
        LocalDate salida = LocalDate.of(2021, 12, 30);
        reservas = new Reservas(1, entrada, salida);
        habitaciones = hotel.getHabitaciones(1);
        reservas.añadirHabitacion(habitaciones);
        reservas.añadirCliente(clientes);
        hotel.añadirReservas(reservas);

        clientes = hotel.getClientes("2345673A");
        entrada = LocalDate.of(2021, 12, 02);
        salida = LocalDate.of(2021, 12, 14);
        reservas = new Reservas(5,  entrada, salida);
        habitaciones = hotel.getHabitaciones(8);
        reservas.añadirHabitacion(habitaciones);
        reservas.añadirCliente(clientes);
        hotel.añadirReservas(reservas);

        clientes = hotel.getClientes("33529651R");
        entrada = LocalDate.of(2021, 12, 24);
        salida = LocalDate.of(2021, 12, 28);
        reservas = new Reservas(2,  entrada, salida);
        habitaciones = hotel.getHabitaciones(4);
        reservas.añadirHabitacion(habitaciones);
        reservas.añadirCliente(clientes);
        hotel.añadirReservas(reservas);

        clientes = hotel.getClientes("1234567Q");
        entrada = LocalDate.of(2021, 12, 17);
        salida= LocalDate.of(2021, 12, 22);
        reservas = new Reservas(3,  entrada, salida);
        habitaciones = hotel.getHabitaciones(9);
        reservas.añadirHabitacion(habitaciones);
        reservas.añadirCliente(clientes);
        hotel.añadirReservas(reservas);

        clientes = hotel.getClientes("2345673A");
        entrada = LocalDate.of(2021, 12, 16);
        salida = LocalDate.of(2021, 12, 20);
        reservas = new Reservas(4,  entrada, salida);
        habitaciones = hotel.getHabitaciones(5);
        reservas.añadirHabitacion(habitaciones);
        reservas.añadirCliente(clientes);
        hotel.añadirReservas(reservas);


        System.out.println("Datos cargados correctamente.");
    }

    @Override
    public String toString() {
        return "Informacion sobre el hotel: Nombre " + this.nombreHotel + " la calle es " + this.direccion + " en la ciudad " + this.ciudad;
    }
}
