package br.com.organizerooms.services;

import br.com.organizerooms.models.Mensagem;
import br.com.organizerooms.repositorios.MensagemRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Lucas Jansen
 */
@Service
public class MensagemService {

    @Autowired
    MensagemRepository repository;

    public Mensagem persiste(Mensagem mensagem) {
        return this.repository.save(mensagem);
    }

    public List<Mensagem> buscaTodos() {
        return this.repository.findAll();
    }

    public Mensagem buscaPorTipo(Integer tipo) {
        return this.repository.findByMenTipo(tipo);
    }

}
