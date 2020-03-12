/*
 * To change this license header, choose License Headers in Project Properties.
 * To changes this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.organizerooms.controllers;

import br.com.organizerooms.models.Unidade;
import br.com.organizerooms.services.UnidadeService;
import br.com.organizerooms.utils.MontaPessoa;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Pichau
 */
@RestController
@RequestMapping("/montaPessoa")
public class MontaPessoaController {
    
    @Autowired
    MontaPessoa monta;
    
    @Autowired
    UnidadeService unidadeService;
    
    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public void enviaArquivo(String arquivo, Long id) throws IOException{
         Unidade unidade = unidadeService.buscarUnidadePorId(id);
         monta.run(arquivo, unidade);
    }
    
}
