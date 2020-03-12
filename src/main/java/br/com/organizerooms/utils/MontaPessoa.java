/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.organizerooms.utils;

import br.com.organizerooms.dto.PessoaDTO;
import br.com.organizerooms.enums.PerfilEnum;
import br.com.organizerooms.models.Pessoa;
import br.com.organizerooms.models.Response;
import br.com.organizerooms.models.Unidade;
import br.com.organizerooms.services.PessoaService;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author FelipeGostos
 */
@Service
public class MontaPessoa {
    
    @Autowired
    PessoaService pessoaService;
    
    public void run(String arquivo, Unidade unidade) throws IOException {
   
    String arquivoCSV = "C:\\Users\\Aluno\\Documents\\Netbeans\\BackEnd-OrganizeRooms\\OrganizeRooms\\src\\main\\java\\ArquivoPessoas\\";
    arquivoCSV += arquivo;
    BufferedReader br = null;
    String linha = "";
    String csvDivisor = ",";
    try {

        br = new BufferedReader(new FileReader(arquivoCSV));
        
        br.readLine();
        
        while ((linha = br.readLine()) != null) {

            String[] pessoa = linha.split(csvDivisor);
            
            PessoaDTO pessoaMonta = new PessoaDTO(null, // id
                    pessoa[pessoa.length-6],  // nome
                    pessoa[pessoa.length-5],  // email
                   // pessoa[pessoa.length-4],  // senha
                    "admin".equals(pessoa[pessoa.length-3]) ? PerfilEnum.ROLE_ADMIN : PerfilEnum.ROLE_USUARIO, // pesPermissao
                    "admin".equals(pessoa[pessoa.length-3]) ? "Administrador" : "Usuario", //PesDescricaoPermisao
                    unidade, //Unidade
                    pessoa[pessoa.length-2], //pesDdd
                    pessoa[pessoa.length-1], // pesTelefone
                    "IMP", // PesTipoInclusao
                    null, // pesCadastro
                    Calendar.getInstance().getTime(), // dataCadastro
                    null, // pessoaAtualzacao
                    null); // Data Atualizaco;
            
            Pessoa newPessoa = new Pessoa(pessoaMonta);
            PessoaDTO pesDTO = new PessoaDTO(pessoaService.addPessoa(newPessoa));

        }

    } catch (FileNotFoundException e) {
        System.err.println("Não foi encontrando nenhum arquivo!! Caminho executado: " + arquivo);
    } catch (IOException e) {
    } finally {
        if (br != null) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
  }
}
