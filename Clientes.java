public class Clientes {
    private String nombre;
    private String apellidos;
    private String ID;
    public Clientes(String nombre, String apellidos, String ID) {
        this.nombre=nombre;
        this.apellidos=apellidos;
        this.ID=ID;
    }
    public String getNombre() {
        return nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public String getID() {
        return ID;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    //Compruebo si el cliente ya existe
    static boolean clientesExiste (Hotel hotel, String ID) {
        boolean existe= false;
        for (Clientes clientes : hotel.getClientes()) {
            if(clientes.getID().equals(ID)) {
                existe=true;
                break;
            }
        }
        return existe;
    }
    //Borrar cliente
    static void borrarCliente (Hotel hotel, String ID) {
        for(Clientes clientes : hotel.getClientes()) {
            hotel.borrarCliente(clientes);
            break;
        }
    }
    public String toStrign() {
        return "Detalles de clientes: " + this.apellidos + "," + this.nombre + ", ID:" + this.ID;
    }
}
