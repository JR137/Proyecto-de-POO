import java.time.LocalDate;
import java.util.Scanner;

public class Menu {
        // Creo el objeto hotel
        private static final Hotel ho = new Hotel ("Jhosua Resort", "Cuesta de las perdices, numero 9", "Aranjuez");
        private static boolean exit;
        static void menu (String[] args) throws Exception {
            // Cargo la información
            Hotel.cargarDatos(ho);
            // Menú
            runMenu();
        }

        // Ejecuto el menú
        public static void runMenu() throws Exception {
            while (!exit) {
                printMenu();
                int choice = getInput();
                opciones(choice);
            }
        }

        // Imprimo el menu
        private static void printMenu() {
            System.out.println("\nOpciones disponibles: ");
            System.out.println("-------------------");
            System.out.println("1. Gestionar reservas");
            System.out.println("2. Gestionar habitaciones");
            System.out.println("3. Gestionar clientes");
            System.out.println("Para salir presione 0");
        }
        // Leer la entrada del usuario
        private static int getInput() throws NumberFormatException{
            Scanner sb = new Scanner(System.in);
            int choice = -1;
            while(choice < 0 || choice > 3){
                try {
                    System.out.print("\nElija una opción: \n");
                    choice = Integer.parseInt(sb.nextLine());
                }
                catch(NumberFormatException e){
                    System.out.println("Ingrese una opción válida\n");
                }
            }
            return choice;
        }

        // Switch para las opciones del menú
        private static void opciones(int choice) throws Exception {
            switch (choice) {
                case 0 -> exit = true;
                case 1 -> gestionarReservas(ho);
                case 2 -> gestionarHabitaciones(ho);
                case 3 -> gestionarClientes(ho);
                default -> System.out.println("Error desconocido");
            }
        }

        // Submenú para gestionar reservas
        private static void gestionarReservas(Hotel hotel) throws Exception {
            while(!exit){
                menuReservas();
                int choice = getInput();
                accionReservas(hotel, choice);
            }
        }
        // Imprimir submenú
        private static void menuReservas() {
            System.out.println("\nElija una opción: ");
            System.out.println("---------------------");
            System.out.println("0. Volver al menú principal");
            System.out.println("1. Crear una nueva reserva");
            System.out.println("2. Eliminar una reserva");
            System.out.println("3. Lista de las reservas");
        }

        // Acción del submenú
        private static void accionReservas(Hotel hotel, int choice) throws Exception {
            switch(choice){
                case 0 -> runMenu();
                case 1 -> crearReserva(hotel);
                case 2 -> borrarReserva(hotel);
                case 3 -> listaReserva(hotel);
                default -> System.out.println("Error desconocido");
            }
        }

        // Crear una reserva
        private static void crearReserva(Hotel hotel) {
            Reservas reservas;
            Clientes clientes = null;
            Habitaciones habitaciones;
            int numeroHabitacion = 0;
            boolean reservaFinalizada = false;

            Scanner sc = new Scanner(System.in);

            // Obtener la entrada del usuario
            System.out.print("Ingrese el ID del cliente: ");
            String c = sc.nextLine();
            // Verificar si el cliente ya existe
            try {
                if (Clientes.clientesExiste(hotel, c)) {
                    clientes = hotel.getClientes(c);
                    System.out.println(clientes);
                } else {
                    // Informar que el cliente no existe
                    System.out.println("Este cliente no está registrado en la base de datos.");
                    runMenu();
                }
            } catch (Exception e) {
                System.err.println("Ingrese un ID válido." + e);
            }

            // Obtener el número de reserva
            int numeroReserva = Reservas.ultimaReserva(ho);
            System.out.println("Número de la reserva: " + numeroReserva);

            // Obtener la fecha de inicio
            LocalDate fechaInicio;
            do {
                System.out.print("Ingrese la fecha de inicio (formato AAA-mm-dd): ");
                fechaInicio = Reservas.analizarFecha(sc.nextLine());
                // Verificar que la fecha sea válida
            } while ((fechaInicio == null) || (fechaInicio.isBefore(LocalDate.now())));

            // Obtener la fecha de salida
            LocalDate fechaSalida;
            do {
                System.out.print("Ingrese la fecha de salida (formato AAA-mm-dd): ");
                fechaSalida = Reservas.analizarFecha(sc.nextLine());
                // Verificar que la fecha de salida sea válida y que sea después de la fecha de inicio
            } while (fechaSalida == null || fechaSalida.isBefore(fechaInicio));

            // Mostrar las habitaciones disponibles
            System.out.println("Las habitaciones disponibles para este período son: ");
            System.out.println("--------------------------------------------");

            // Obtener la habitación
            do {
                try {
                    System.out.print("Elija la habitación: ");
                    numeroHabitacion = Integer.parseInt(sc.nextLine());
                } catch (Exception e) {
                    System.err.println("Ingrese una habitación válida" + e);
                }

                habitaciones = hotel.getHabitaciones(numeroHabitacion);

                // Comprobar si la habitación está en otras reservas
                for (Reservas res : hotel.getReservas()) {
                    if (numeroHabitacion == habitaciones.getNumeroHabitacion()) {
                        // fecha a verificar
                        LocalDate fechaSalidaComprobar = res.getSalida();
                        // Comprobar si está disponible
                        if (Reservas.habitacionDisponible(fechaInicio, fechaSalidaComprobar)) {
                            // si está disponible, agregarlo a la reserva
                            System.out.println("Se agregó a la reserva la habitación número " + numeroHabitacion);
                            // Crear reserva
                            reservas = new Reservas(numeroReserva, fechaInicio, fechaSalida);
                            reservas.añadirCliente(clientes);
                            reservas.añadirHabitacion(habitaciones);
                            System.out.println(habitaciones);
                            hotel.añadirReservas(reservas);
                            reservaFinalizada = true;
                        } else {
                            System.out.println("Esa habitación no está disponible para estas fechas");
                        }
                    }
                }
            } while (!reservaFinalizada);
        }
        // Eliminar una reserva
        private static void borrarReserva(Hotel hotel) {
            boolean reservaBorrada = false;
            int numeroReserva = 0;
            Scanner sc = new Scanner(System.in);
            System.out.println("--------------------------");
            System.out.println("     Eliminar reserva     ");
            System.out.println("--------------------------");

            do try {
                System.out.print("Ingrese el número de reserva a eliminar: ");
                numeroReserva = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Ingrese un número válido");
            } while (numeroReserva == 0);

            for (Reservas res : hotel.getReservas()) {
                if (res.getNumeroReserva() == numeroReserva) {
                    Reservas.borrarReserva(hotel, numeroReserva);
                    System.out.print("La reserva ha sido eliminada");
                    reservaBorrada = true;
                    break;
                }
            }
            if (!reservaBorrada) {
                System.out.println("No se encontró la reserva");
            }

        }

        // listar reservas
        private static void listaReserva(Hotel hotel) {

            System.out.println("--------------------------");
            System.out.println("    Lista de reservas     ");
            System.out.println("--------------------------");

            for (Reservas reservas : hotel.getReservas()) {
                System.out.println(reservas);
            }
        }
        // Submenú
        private static void gestionarHabitaciones(Hotel hotel) throws Exception {
            while(!exit){
                menuHabitaciones();
                int choice = getInput();
                accionHabitaciones(hotel, choice);
            }
        }

        private static void menuHabitaciones() {
            System.out.println("\nElija una opción: ");
            System.out.println("-------------------");
            System.out.println("0. Volver al menú principal");
            System.out.println("1. Crear una nueva habitación");
            System.out.println("2. Eliminar una habitación");
            System.out.println("3. Listar habitaciones");
        }

        private static void accionHabitaciones (Hotel hotel, int choice) throws Exception {
            switch(choice){
                case 0 -> runMenu();
                case 1 -> crearHabitacion(hotel);
                case 2 -> borrarHabitacion(hotel);
                case 3 -> listaHabitaciones(hotel);
                default -> System.out.println("Error desconocido");
            }
        }
        // Fin del submenú

        // Crear habitación
        private static void crearHabitacion(Hotel hotel) throws Exception {
            Scanner sc = new Scanner(System.in);
            int numeroHabitacion = 0;
            try {
                System.out.print("Ingrese el número de la habitación: ");
                numeroHabitacion = Integer.parseInt(sc.nextLine());
            } catch (Exception e){
                System.err.println("Ingrese un número válido\n" + e);
            }

            // Comprobar si la habitación existe
            if (Habitaciones.existeHabitacion(numeroHabitacion, hotel)) {
                System.out.println("Esta habitación ya existe.");
                menuHabitaciones();
            } else {

                System.out.println("Ingrese el tipo de habitación");
                System.out.println("--------------------------");
                System.out.println("0. Volver al menú principal");
                System.out.println("1. Crear habitación simple");
                System.out.println("2. Crear habitación doble");
                System.out.println("3. Crear suite");

                int eleccion = -1;
                while (eleccion < 0 || eleccion > 3) {
                    try {
                        System.out.print("\nElija la opción: \n");
                        eleccion = Integer.parseInt(sc.nextLine());
                    } catch (Exception e) {
                        System.out.println("Ingrese un número válido\n");
                    }
                    switch (eleccion) {
                        case 0 -> runMenu();
                        case 1 -> crearIndividual(hotel, numeroHabitacion);
                        case 2 -> crearDoble(hotel, numeroHabitacion);
                        case 3 -> crearSuite(hotel, numeroHabitacion);
                        default -> System.out.println("Error desconocido");
                    }
                }
            }
        }

        // Crear habitación simple
        private static void crearIndividual(Hotel hotel, int numero) {
            HabitacionIndividual habitacionIndividual;
            Scanner sc = new Scanner(System.in);

            System.out.print("Ingrese los metros cuadrados: ");
            double metrosCuadrados = sc.nextDouble();
            System.out.print("Ingrese el precio: ");
            double precio = sc.nextDouble();
            habitacionIndividual = new HabitacionIndividual(numero, metrosCuadrados, precio);
            hotel.añadirHabitacion(habitacionIndividual);
            System.out.println("Habitación creada: " + habitacionIndividual);
        }

        // Crear habitación doble
        private static void crearDoble(Hotel hotel, int numero) {
            HabitacionDoble habitacionDoble;
            Scanner sc = new Scanner(System.in);
            double metrosCuadrados = 0, precio = 0;


            do try {
                System.out.print("Ingrese los metros cuadrados: ");
                metrosCuadrados = Double.parseDouble(sc.nextLine());
            } catch (Exception e) {
                System.err.println("Ingrese un número válido.\n" + e);
            }  while (metrosCuadrados == 0);
            do try {
                System.out.print("Ingrese el precio: ");
                precio = Double.parseDouble(sc.nextLine());
            } catch (Exception e) {
                System.err.println("Ingrese un número válido.\n" + e);
            } while (precio == 0);
            System.out.print("Ingrese el tipo de cama: ");
            String tipoCama = sc.nextLine();
            habitacionDoble = new HabitacionDoble(numero, metrosCuadrados, precio, tipoCama);
            hotel.añadirHabitacion(habitacionDoble);
            System.out.println("Habitación creada: " + habitacionDoble);
        }

        // Crear habitación suite
        private static void crearSuite(Hotel hotel, int numero) {
            HabitacionSuite habitacionSuite;
            Scanner sc = new Scanner(System.in);
            double metrosDormitorio = 0.0, metrosSala = 0.0, precio = 0.0;

            do try {
                System.out.print("Ingrese los metros cuadrados del dormitorio: ");
                metrosDormitorio = Double.parseDouble(sc.nextLine());
            } catch (Exception e) {
                System.err.println("Ingrese un número válido.\n" + e);
            } while (metrosDormitorio == 0);
            do try {
                System.out.print("Ingrese los metros cuadrados de la habitación adicional: ");
                metrosSala = Double.parseDouble(sc.nextLine());
            } catch (Exception e) {
                System.err.println("Ingrese un número válido.\n" + e);
            } while (metrosSala == 0);
            do try {
                System.out.print("Ingrese el precio: ");
                precio = Double.parseDouble(sc.nextLine());
            } catch (Exception e) {
                System.err.println("Ingrese un número válido.\n" + e);
            } while (precio == 0);
            habitacionSuite = new HabitacionSuite(numero, metrosDormitorio, metrosSala, precio);
            hotel.añadirHabitacion(habitacionSuite);
            System.out.println("Habitación creada: " + habitacionSuite);
        }

        // Eliminar habitación
        private static void borrarHabitacion(Hotel hotel) {
            boolean habitacionEliminada = false;
            int numeroHabitacionEliminar = 0;
            Scanner sc = new Scanner(System.in);
            System.out.println("-----------------------");
            System.out.println("  Eliminar habitación  ");
            System.out.println("-----------------------");
            System.out.print("Ingrese el número de la habitación a eliminar: ");
            do try {
                numeroHabitacionEliminar = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Ingrese un número válido");
            } while (numeroHabitacionEliminar == 0);

            for (Habitaciones habitaciones : hotel.getHabitaciones()) {
                if (numeroHabitacionEliminar == habitaciones.getNumeroHabitacion()) {
                    Habitaciones.borrarHabitacion(hotel, numeroHabitacionEliminar);
                    System.out.print("La habitación fue eliminada.");
                    habitacionEliminada = true;
                    break;
                }
            }
            if (!habitacionEliminada) {
                System.out.println("La habitación no fue encontrada");
            }

        }

        // Listar habitaciones filtradas por tipo
        private static void listaHabitaciones(Hotel hotel) {
            HabitacionIndividual Individual;
            HabitacionDoble Doble;
            HabitacionSuite Suite;
            System.out.println("--------------------------");
            System.out.println("   Lista de habitaciones  ");
            System.out.println("--------------------------");
            for (Habitaciones habitaciones : hotel.getHabitaciones()) {
                if (habitaciones instanceof HabitacionIndividual) {
                    Individual = (HabitacionIndividual) habitaciones;
                    System.out.println(Individual);
                } else if (habitaciones instanceof HabitacionDoble) {
                    Doble = (HabitacionDoble) habitaciones;
                    System.out.println(Doble);
                } else if (habitaciones instanceof HabitacionSuite) {
                    Suite = (HabitacionSuite) habitaciones;
                    System.out.println(Suite);
                }
            }
        }

        // Submenú para clientes
        private static void gestionarClientes(Hotel hotel) throws Exception {
            while(!exit){
                menuClientes();
                int choice = getInput();
                accionClientes(hotel, choice);
            }
        }
        private static void menuClientes() {
            System.out.println("\nElige una opción: ");
            System.out.println("---------------------");
            System.out.println("0. Volver al menú principal");
            System.out.println("1. Crear un nuevo cliente");
            System.out.println("2. Eliminar un cliente");
            System.out.println("3. Listar clientes");
        }

        private static void accionClientes(Hotel hotel, int choice) throws Exception {
            switch(choice){
                case 0 -> runMenu();
                case 1 -> crearCliente(hotel);
                case 2 -> borrarCliente(hotel);
                case 3 -> listaCliente(hotel);
                default -> System.out.println("Error desconocido");
            }
        }
        // Fin del submenú

        // Crear cliente
        private static void crearCliente(Hotel hotel) {
            Clientes clientes;
            Scanner sc = new Scanner(System.in);

            System.out.print("Ingrese el ID del cliente: ");
            String id = sc.nextLine();
            // Verificar si el cliente ya existe
            if(!Clientes.clientesExiste(hotel, id)) {
                System.out.print("Ingrese el nombre: ");
                String nombre = sc.nextLine();
                System.out.print("Ingrese el apellido: ");
                String apellidos = sc.nextLine();
                clientes = new Clientes(nombre, apellidos, id);
                hotel.añadirClientes(clientes);
                System.out.println("Cliente creado: " + clientes);
            } else  {
                System.out.println("Este cliente ya existe");
            }
        }

        // Eliminar cliente
        private static void borrarCliente(Hotel hotel) {
            boolean clenteBorrado = false;
            Scanner sc = new Scanner(System.in);
            System.out.println("---------------------");
            System.out.println("   Eliminar cliente  ");
            System.out.println("---------------------");
            System.out.print("Ingrese el ID del cliente: ");
            String idClienteBorrado = sc.nextLine();

            for (Clientes clientes : hotel.getClientes()) {
                if (clientes.getID().equals(idClienteBorrado)) {
                    Clientes.borrarCliente(hotel, idClienteBorrado);
                    clenteBorrado = true;
                    System.out.println("El cliente ha sido eliminado.");
                    break;
                }
            }
            if (!clenteBorrado) {
                System.out.println("Cliente no encontrado");
            }
        }

        // Listar clientes
        private static void listaCliente(Hotel hotel) {
            System.out.println("-------------------------");
            System.out.println("    Lista de clientes    ");
            System.out.println("-------------------------");
            for (Clientes clientes : hotel.getClientes()) {
                System.out.println(clientes);
            }
        }
    }

