package br.com.organizerooms.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import br.com.organizerooms.enums.PerfilEnum;
import br.com.organizerooms.models.Pessoa;

public class JwtUserFactory {

    private JwtUserFactory() {
    }

    /**
     * Converte e gera um JwtUser com base nos dados de um pessoa.
     *
     * @param pessoa
     * @return JwtUser
     */
    public static JwtUser create(Pessoa pessoa) {
        return new JwtUser(pessoa.getPesId(), pessoa.getPesEmail(), pessoa.getPesSenha(),
                mapToGrantedAuthorities(pessoa.getPesPermissao()));
    }

    /**
     * Converte o perfil do usuário para o formato utilizado pelo Spring
     * Security.
     *
     * @param perfilEnum
     * @return List<GrantedAuthority>
     */
    private static List<GrantedAuthority> mapToGrantedAuthorities(PerfilEnum perfilEnum) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(perfilEnum.toString()));
        return authorities;
    }
}
