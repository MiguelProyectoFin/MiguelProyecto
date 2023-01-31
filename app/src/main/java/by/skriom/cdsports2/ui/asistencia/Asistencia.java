package by.skriom.cdsports2.ui.asistencia;

public class Asistencia {
    int idasistencia;
    String usuario;
    String fecha;

    public Asistencia() {
    }

    public Asistencia(String usuario) {
        this.usuario = usuario;
    }

    public Asistencia(int idasistencia, String usuario, String fecha) {
        this.idasistencia = idasistencia;
        this.usuario = usuario;
        this.fecha = fecha;
    }

    public int getIdasistencia() {
        return idasistencia;
    }

    public void setIdasistencia(int idasistencia) {
        this.idasistencia = idasistencia;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
