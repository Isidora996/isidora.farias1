package com.isidora.farias.isidorafarias1.model;

import com.isidora.farias.isidorafarias1.dto.UsuarioDto;

import java.util.ArrayList;

public class UsuariosModel {

    private final ArrayList<UsuarioDto> usuarios = new ArrayList<>();

    private Integer ultimoId = 0;

    public ArrayList<UsuarioDto> getUsuarios() {
        return usuarios;
    }

    public UsuarioDto buscarPorId(Integer id) {
        for (UsuarioDto usuario : usuarios) {
            if (usuario.getId().equals(id)) {
                return usuario;
            }
        }
        return null;
    }

    public void agregarUsuario(UsuarioDto usuario) {
        this.ultimoId = this.ultimoId + 1;
        usuario.setId(this.ultimoId);
        usuarios.add(usuario);
    }

    public boolean modificarUsuario(UsuarioDto usuarioModificado) {
        UsuarioDto original = buscarPorId(usuarioModificado.getId());
        if (original == null) {
            return false;
        }
        original.setNombre(usuarioModificado.getNombre());
        original.setContrasena(usuarioModificado.getContrasena());
        original.setEmail(usuarioModificado.getEmail());
        return true;
    }

    public void eliminarUsuario(UsuarioDto usuario) {
        usuarios.remove(usuario);
    }

    public boolean existeNombre(String nombre, Integer idIgnorar) {
        for (UsuarioDto usuario : usuarios) {
            boolean mismoNombre = usuario.getNombre().equalsIgnoreCase(nombre.trim());
            boolean esElMismo = idIgnorar != null && usuario.getId().equals(idIgnorar);
            if (mismoNombre && !esElMismo) {
                return true;
            }
        }
        return false;
    }
}
