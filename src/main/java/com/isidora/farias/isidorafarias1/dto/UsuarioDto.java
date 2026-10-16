package com.isidora.farias.isidorafarias1.dto;

/**
 * DTO = Data Transfer Object.
 * Es simplemente una "caja" que guarda los datos de UN usuario.
 * No tiene logica, solo atributos con sus getters y setters.
 */
public class UsuarioDto {
    private Integer id;
    private String nombre = "";
    private String contrasena = "";
    private String email = "";

    public UsuarioDto() {
    }

    public UsuarioDto(Integer id, String nombre, String contrasena, String email) {
        this.id = id;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UsuarioDto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
