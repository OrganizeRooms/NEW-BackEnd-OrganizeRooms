/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.organizerooms.repositorios;

import br.com.organizerooms.models.Unidade;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Lucas Jansen
 */
@Repository
@Transactional(readOnly = true )
public interface UnidadeRepository extends JpaRepository <Unidade, Long >{
    
    public List<Unidade> findAllByOrderByUniNome(); 
    
    public  List<Unidade> findByUniAtiva(boolean ativo);
   
    
}
