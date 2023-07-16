package br.ufsm.csi.flutter_back.controller;

import br.ufsm.csi.flutter_back.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufsm.csi.flutter_back.db.UsuarioDAO;
import br.ufsm.csi.flutter_back.model.Usuario;

@RestController
@RequestMapping
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody Usuario usuario){
        final Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getSenha()));
        if(authentication.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            usuario.setPermissao(authentication.getAuthorities().toString().replace("[", "").replace("]", ""));
            usuario.setToken(new JWTUtil().geraToken(usuario));
            return new ResponseEntity<>(usuario, HttpStatusCode.valueOf(200));
        }else {
            return new ResponseEntity<>(HttpStatusCode.valueOf(401));
        }
    }

    @PostMapping("/criarConta")
    public void criarConta(@RequestBody Usuario user){
        new UsuarioDAO().criarConta(user);
    }
}
