package com.generation.delivery.service;

import com.generation.delivery.model.Usuario;
import com.generation.delivery.model.UsuarioLogin;
import com.generation.delivery.repository.UsuarioRepository;
import com.generation.delivery.security.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    // cadastrar usuário com verificação e criptografia de senha
    public Optional<Usuario> cadastrarUsuario(Usuario usuario) {
        if (usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent()) {
            return Optional.empty();
        }
        usuario.setSenha(criptografarSenha(usuario.getSenha()));
        return Optional.ofNullable(usuarioRepository.save(usuario));
    }

    // atualizar usuário com verificação de duplicidade e criptografia de senha
    public Optional<Usuario> atualizarUsuario(Usuario usuario) {
        if (usuarioRepository.findById(usuario.getId()).isPresent()) {
            Optional<Usuario> usuarioExistente = usuarioRepository.findByUsuario(usuario.getUsuario());
            if (usuarioExistente.isPresent() && !Objects.equals(usuarioExistente.get().getId(), usuario.getId())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O Usuário já existe!");
            }
            usuario.setSenha(criptografarSenha(usuario.getSenha()));
            return Optional.ofNullable(usuarioRepository.save(usuario));
        }
        return Optional.empty();
    }

    public Optional<UsuarioLogin> autenticarUsuario(Optional<UsuarioLogin> usuarioLogin) {
        var credenciais = new UsernamePasswordAuthenticationToken(
                usuarioLogin.get().getUsuario(), usuarioLogin.get().getSenha());

        Authentication authentication = authenticationManager.authenticate(credenciais);

        if (authentication.isAuthenticated()) {
            Optional<Usuario> usuario = usuarioRepository.findByUsuario(usuarioLogin.get().getUsuario());
            if (usuario.isPresent()) {
                usuarioLogin.get().setId(usuario.get().getId());
                usuarioLogin.get().setNome(usuario.get().getNome());
                usuarioLogin.get().setFoto(usuario.get().getFoto());
                usuarioLogin.get().setSenha(""); // limpar senha para segurança
                usuarioLogin.get().setToken(gerarToken(usuarioLogin.get().getUsuario()));
                return usuarioLogin;
            }
        }

        return Optional.empty();
    }

    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getById(Long id) {
        return usuarioRepository.findById(id);
    }

    private String gerarToken(String string) {
        return "Bearer " + jwtService.generateToken(string);
    }

    private String criptografarSenha(String senha) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(senha);
    }
}
