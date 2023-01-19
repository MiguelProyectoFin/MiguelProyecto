package by.skriom.cdsports2.foto;

public class Toma {
    int id_toma;
    String angulo;
    String refencia;
    String escena;
    int id_sesion;

    public Toma() {
    }

    public Toma(int id_toma, String angulo, String refencia, String escena, int id_sesion) {
        this.id_toma = id_toma;
        this.angulo = angulo;
        this.refencia = refencia;
        this.escena = escena;
        this.id_sesion = id_sesion;
    }

    public Toma(String angulo, String refencia, String escena, int id_sesion) {
        this.angulo = angulo;
        this.refencia = refencia;
        this.escena = escena;
        this.id_sesion = id_sesion;
    }


    public int getId_toma() {
        return id_toma;
    }

    public void setId_toma(int id_toma) {
        this.id_toma = id_toma;
    }

    public String getAngulo() {
        return angulo;
    }

    public void setAngulo(String angulo) {
        this.angulo = angulo;
    }


    public String getRefencia() {
        return refencia;
    }

    public void setRefencia(String refencia) {
        this.refencia = refencia;
    }

    public String getEscena() {
        return escena;
    }

    public void setEscena(String escena) {
        this.escena = escena;
    }

    public int getId_sesion() {
        return id_sesion;
    }

    public void setId_sesion(int id_sesion) {
        this.id_sesion = id_sesion;
    }
}
