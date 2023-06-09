package ar.edu.unlam.tallerweb1.domain.comentarios;
import ar.edu.unlam.tallerweb1.domain.cuidado.Cuidado;
import ar.edu.unlam.tallerweb1.domain.mascotas.Mascota;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = true)
    private int clasificacion;
    @Column(length = 255)
    private String mensaje;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IdUsuario")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IdCuidado")
    private Cuidado cuidado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IdMascota")
    private Mascota mascota;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IdComentarioPadre")
    private Comentario comentarioPadre;

    private Date fecha;

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }



    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public int getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(int clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Cuidado getCuidado() {
        return cuidado;
    }

    public void setCuidado(Cuidado cuidado) {
        this.cuidado = cuidado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setComentarioPadre(Comentario comentarioPadre) {
        this.comentarioPadre = comentarioPadre;
    }

    public Comentario getComentarioPadre() {
        return comentarioPadre;
    }
}
