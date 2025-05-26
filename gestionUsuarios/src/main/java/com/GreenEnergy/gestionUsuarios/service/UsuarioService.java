package com.GreenEnergy.gestionUsuarios.service;

import com.GreenEnergy.gestionUsuarios.model.Usuario;
import com.GreenEnergy.gestionUsuarios.model.Rol;
import com.GreenEnergy.gestionUsuarios.repository.UsuarioRepository;
import com.GreenEnergy.gestionUsuarios.repository.RolRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, RolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
    }

    // Métodos para Usuarios Generales
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> obtenerUsuarioPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario actualizarUsuario(Long id, Usuario usuario) {
        Usuario usuarioExistente = usuarioRepository.findById(id).orElse(null);
        if (usuarioExistente != null) {
            usuarioExistente.setUsername(usuario.getUsername());
            usuarioExistente.setCorreo(usuario.getCorreo()); // Asumiendo que usas 'correo' ahora
            usuarioExistente.setRoles(usuario.getRoles());
            usuarioExistente.setEspecialidad(usuario.getEspecialidad());
            if (usuario.getDisponible() != null) {
                usuarioExistente.setDisponible(usuario.getDisponible());
            }
            return usuarioRepository.save(usuarioExistente);
        }
        return null;
    }

    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public void asignarRoles(Long usuarioId, Set<Rol> roles) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
        if (usuario != null) {
            usuario.setRoles(roles);
            usuarioRepository.save(usuario);
        }
    }

    // Métodos específicos para Técnicos
    public List<Usuario> listarTecnicos() {
        return usuarioRepository.findAll().stream()
                .filter(u -> u.getEspecialidad() != null)
                .collect(Collectors.toList());
    }

    public List<Usuario> listarTecnicosPorEspecialidad(String especialidad) {
        return usuarioRepository.findByEspecialidad(especialidad);
    }

    public List<Usuario> listarTecnicosDisponibles() {
        return usuarioRepository.findByDisponibleTrue();
    }

    public List<Usuario> listarTecnicosNoDisponibles() {
        return usuarioRepository.findByDisponibleFalse();
    }

    public void actualizarDisponibilidadTecnico(Long id, boolean disponible) {
        Usuario tecnico = usuarioRepository.findById(id).orElse(null);
        if (tecnico != null && tecnico.getEspecialidad() != null) {
            tecnico.setDisponible(disponible);
            usuarioRepository.save(tecnico);
        }
    }
}