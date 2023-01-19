package by.skriom.cdsports2.ui.reservar;

public class Reserva {
    int idreserva;
    String clase;
    String horario;
    String usuario;

    public Reserva(String clase, String horario, String usuario) {
        this.clase = clase;
        this.horario = horario;
        this.usuario = usuario;
    }

    public Reserva(int idreserva, String clase, String horario, String usuario) {
        this.idreserva = idreserva;
        this.clase = clase;
        this.horario = horario;
        this.usuario = usuario;
    }

    public int getIdreserva() {
        return idreserva;
    }

    public void setIdreserva(int idreserva) {
        this.idreserva = idreserva;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
