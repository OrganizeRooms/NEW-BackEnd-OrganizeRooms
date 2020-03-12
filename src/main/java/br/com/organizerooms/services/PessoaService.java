package br.com.organizerooms.services;

import br.com.organizerooms.models.Pessoa;
import br.com.organizerooms.models.Sala;
import br.com.organizerooms.models.Unidade;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.organizerooms.repositorios.PessoaRepository;
import java.util.List;

@Service
public class PessoaService implements PessoaServiceInterface {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public Optional<Pessoa> buscarPessoaPorEmail(String pesEmail) {
        return Optional.ofNullable(this.pessoaRepository.findByPesEmail(pesEmail));
    }

    public List<Pessoa> buscarTodasPessoas() {
        return this.pessoaRepository.findAllByOrderByPesNome();
    }

    public Pessoa addPessoa(Pessoa pes) {
        return this.pessoaRepository.save(pes);
    }

    public Optional<Pessoa> buscarPessoaPorId(Long pesId) {
        return this.pessoaRepository.findById(pesId);
    }
    
    public void remover(Long id) {
        this.pessoaRepository.deleteById(id);
    }
    
    public List<Pessoa> buscarPorUnidade(Unidade unidade) {
        return this.pessoaRepository.findAllByPesUnidade(unidade);
    }
}
