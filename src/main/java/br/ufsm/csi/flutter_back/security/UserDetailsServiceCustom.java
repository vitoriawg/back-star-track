package br.ufsm.csi.flutter_back.security;

import br.ufsm.csi.flutter_back.db.UsuarioDAO;
import br.ufsm.csi.flutter_back.model.Usuario;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceCustom implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = new UsuarioDAO().getUser(username);
        if(user != null){
            return User.withUsername(user.getEmail()).password(user.getSenha()).build();
        }
        throw new UsernameNotFoundException("Usuário não encontrado");
    }
}
