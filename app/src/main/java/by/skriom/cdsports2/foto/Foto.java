package by.skriom.cdsports2.foto;

public class Foto {
    int id_foto;
    String referencia;
    int id_usuario;

    public Foto(int id_foto) {
        this.id_foto = id_foto;
    }

    public Foto(int id_foto, String referencia, int id_usuario) {
        this.id_foto = id_foto;
        this.referencia = referencia;
        this.id_usuario = id_usuario;
    }

    public Foto( String referencia, int id_usuario) {
        this.referencia = referencia;
        this.id_usuario = id_usuario;
    }

    @Override
    public String toString() {
        return "Foto{" +
                "id_foto=" + id_foto +
                ", referencia='" + referencia + '\'' +
                ", id_usuario=" + id_usuario +
                '}';
    }

    public Foto() {
    }

    public int getId_foto() {
        return id_foto;
    }

    public void setId_foto(int id_foto) {
        this.id_foto = id_foto;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
}
