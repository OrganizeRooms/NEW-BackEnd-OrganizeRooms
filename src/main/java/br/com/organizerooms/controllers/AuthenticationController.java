package br.com.organizerooms.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.organizerooms.dto.JwtAuthenticationDto;
import br.com.organizerooms.dto.TokenDto;
import br.com.organizerooms.models.Pessoa;
import br.com.organizerooms.models.Response;
import br.com.organizerooms.services.PessoaService;
import br.com.organizerooms.utils.JwtTokenUtil;
import br.com.organizerooms.utils.SenhaUtils;
import java.util.HashMap;
import org.springframework.http.HttpHeaders;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class AuthenticationController {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);
    private static final String TOKEN_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PessoaService pessoaService;

    /**
     * Gera e retorna um novo token JWT.
     *
     * @param authenticationDto
     * @param result
     * @return ResponseEntity<Response<TokenDto>> @thr ows
     * AuthenticationException
     */
    @PostMapping
    public ResponseEntity<Response<TokenDto>> gerarTokenJwt(@Valid @RequestBody JwtAuthenticationDto authenticationDto,
            BindingResult result) throws AuthenticationException {
        Response<TokenDto> response = new Response<TokenDto>();
        HttpHeaders responseHeaders = new HttpHeaders();
        if (result.hasErrors()) {
            log.error("Erro validando lançamento: {}", result.getAllErrors());
            result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
            HashMap resp = new HashMap();
            resp.put("ERRO", result);
            responseHeaders.setAll(resp);
            return ResponseEntity.badRequest().body(response);
        }

        log.info("Gerando token para o email {}.", authenticationDto.getPesEmail());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationDto.getPesEmail(), authenticationDto.getPesSenha()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationDto.getPesEmail());
        String token = jwtTokenUtil.obterToken(userDetails);
        response.setData(new TokenDto(token));

        response.setPessoa(pessoaService.buscarPessoaPorEmail(userDetails.getUsername()));

        return ResponseEntity.ok(response);
    }

    /**
     * Gera um novo token com uma nova data de expiração.
     *
     * @param request
     * @return ResponseEntity<Response<TokenDto>>
     */
    @PostMapping(value = "/refresh")
    public ResponseEntity<Response<TokenDto>> gerarRefreshTokenJwt(HttpServletRequest request) {
        log.info("Gerando refresh token JWT.");
        Response<TokenDto> response = new Response<TokenDto>();
        Optional<String> token = Optional.ofNullable(request.getHeader(TOKEN_HEADER));
        if (token.isPresent() && token.get().startsWith(BEARER_PREFIX)) {
            token = Optional.of(token.get().substring(7));
        }
        if (!token.isPresent()) {
            response.getErros().add("Token não informado.");
        } else if (!jwtTokenUtil.tokenValido(token.get())) {
            response.getErros().add("Token inválido ou expirado.");
        }
        if (!response.getErros().isEmpty()) {
            return ResponseEntity.badRequest().body(response);
        }
        String refreshedToken = jwtTokenUtil.refreshToken(token.get());
        response.setData(new TokenDto(refreshedToken));

        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/verificarEmail")
    public ResponseEntity<Response> verificarEmail(@RequestBody JwtAuthenticationDto email) {
        Optional<Pessoa> pessoa = pessoaService.buscarPessoaPorEmail(email.getPesEmail());

        Boolean resposta = false;
        if (pessoa.isPresent()) {
            resposta = true;
        }

        Response response = new Response(resposta);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping(value = "/novaSenha")
    public ResponseEntity<Response> novaSenha(@RequestBody JwtAuthenticationDto novaSenha) {
        Optional<Pessoa> pessoa = pessoaService.buscarPessoaPorEmail(novaSenha.getPesEmail());

        Pessoa novaPessoa = new Pessoa();
        Boolean resposta = false;
        if (pessoa.get().getPesSenha().equals(novaSenha.getPesSenha())) {
            
            pessoa.get().setPesSenha(SenhaUtils.gerarBCrypt(novaSenha.getPesNovaSenha()));
            novaPessoa = this.pessoaService.addPessoa(pessoa.get()); 
            if(novaPessoa != null && !novaPessoa.getPesSenha().equals(novaSenha.getPesSenha())){
                resposta = true;
            }
        }
        
        Response response = new Response(resposta);
        return ResponseEntity.ok().body(response);
    }

}
