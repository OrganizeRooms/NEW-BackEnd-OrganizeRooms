package br.com.organizerooms.repositorios;

import br.com.organizerooms.models.Pessoa;
import br.com.organizerooms.models.Unidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    public List<Pessoa> findAllByOrderByPesNome();

    public Pessoa findByPesEmail(String pesEmail);
    
    public List<Pessoa> findAllByPesUnidade(Unidade unidade);
}
