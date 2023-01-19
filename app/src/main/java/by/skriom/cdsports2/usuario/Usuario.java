package by.skriom.cdsports2.usuario;

public class Usuario { //MODELO DEL OBJETO USUARIO A GESTIONAR EN BASE DE DATOS
    int Id;
    String Nombre, Apellidos, Usuario, Password;
    String fecha, telefono, direccion;

    //METODO CONSTRUCTOR VACIO
    public Usuario() {
    }
//METODO CONSTRUCTOR CON PARAMETROS
    public Usuario(String nombre, String apellidos, String usuario, String password, String fecha, String telefono, String direccion) {
        Nombre = nombre;
        Apellidos = apellidos;
        Usuario = usuario;
        Password = password;
        fecha = fecha;
        telefono = telefono;
        direccion = direccion;
    }

    public boolean isNull() {
        if (Nombre.equals("") && Apellidos.equals("") && Usuario.equals("") && Password.equals("") && fecha.equals("") && telefono.equals("") && direccion.equals("")) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "Id=" + Id +
                ", Nombre='" + Nombre + '\'' +
                ", Apellidos='" + Apellidos + '\'' +
                ", Usuario='" + Usuario + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }
// METODOS GET Y SET DE CADA VARIABLE DE CLASE
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
