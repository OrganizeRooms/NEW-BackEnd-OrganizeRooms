package br.com.organizerooms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import br.com.organizerooms.enums.PerfilEnum;
import br.com.organizerooms.models.Agendamento;
import br.com.organizerooms.models.Equipamento;
import br.com.organizerooms.models.Mensagem;
import br.com.organizerooms.models.Participante;
import br.com.organizerooms.models.Pessoa;
import br.com.organizerooms.models.ReservaEquipamento;
import br.com.organizerooms.models.Sala;
import br.com.organizerooms.models.Unidade;
import br.com.organizerooms.repositorios.AgendamentoRepository;
import br.com.organizerooms.repositorios.EquipamentoRepository;
import br.com.organizerooms.repositorios.MensagemRepository;
import br.com.organizerooms.repositorios.ParticipanteRepository;
import br.com.organizerooms.repositorios.PessoaRepository;
import br.com.organizerooms.repositorios.ReservaEquipamentoRepository;
import br.com.organizerooms.repositorios.SalaRepository;
import br.com.organizerooms.repositorios.UnidadeRepository;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class OrganizeRooms implements CommandLineRunner {

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    UnidadeRepository unidadeRepository;

    @Autowired
    EquipamentoRepository equipamentoRepository;

    @Autowired
    SalaRepository salaRepository;

    @Autowired
    AgendamentoRepository agendamentoRepository;

    @Autowired
    ParticipanteRepository participanteRepository;

    @Autowired
    ReservaEquipamentoRepository reservaEquipamentoRepository;

    @Autowired
    MensagemRepository mensagemRepository;

    public static void main(String[] args) {
        SpringApplication.run(OrganizeRooms.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));

        Unidade unidade1 = new Unidade(null,
                "Blumenau",
                true,
                calendar.getTime(),
                null,
                calendar.getTime(),
                null);
        unidadeRepository.save(unidade1);

        Pessoa pes = new Pessoa(null,
                "ADMIN",
                "admin@admin.com",
                "123",
                PerfilEnum.ROLE_ADMIN,
                unidade1,
                "",
                "",
                "SIS",
                null,
                calendar.getTime(),
                null,
                calendar.getTime());
        pessoaRepository.save(pes);

        Pessoa pes2 = new Pessoa(null,
                "Tablet",
                "tablet@tablet.com",
                "123",
                PerfilEnum.ROLE_TABLET,
                unidade1,
                "",
                "",
                "SIS",
                null,
                calendar.getTime(),
                null,
                calendar.getTime());
        pessoaRepository.save(pes2);

        Pessoa pes3 = new Pessoa(null,
                "Usuario",
                "usuario@usuario@.com",
                "123",
                PerfilEnum.ROLE_USUARIO,
                unidade1,
                "",
                "",
                "SIS",
                null,
                calendar.getTime(),
                null,
                calendar.getTime());
        pessoaRepository.save(pes3);

        Pessoa pes4 = new Pessoa(null,
                "Boris Moser",
                "boris.moser@edu.sc.senai.br",
                "123",
                PerfilEnum.ROLE_ADMIN,
                unidade1,
                "",
                "",
                "SIS",
                null,
                calendar.getTime(),
                null,
                calendar.getTime());
        pessoaRepository.save(pes4);

        Pessoa pes5 = new Pessoa(null,
                "Matheus L. Krueger",
                "matheus.krueger@sc.senai.br",
                "123",
                PerfilEnum.ROLE_ADMIN,
                unidade1,
                "",
                "",
                "SIS",
                null,
                calendar.getTime(),
                null,
                calendar.getTime());
        pessoaRepository.save(pes5);

        Pessoa pes6 = new Pessoa(null,
                "Airan Arine Possamai",
                "airan.possamai@edu.sc.senai.br",
                "123",
                PerfilEnum.ROLE_ADMIN,
                unidade1,
                "",
                "",
                "SIS",
                null,
                calendar.getTime(),
                null,
                calendar.getTime());
        pessoaRepository.save(pes6);

        Pessoa pes7 = new Pessoa(null,
                "Lucas R Jansen",
                "lucas@gmail.com",
                "123",
                PerfilEnum.ROLE_USUARIO,
                unidade1,
                "47",
                "992821333",
                "SIS",
                null,
                calendar.getTime(),
                null,
                calendar.getTime());
        pessoaRepository.save(pes7);

        Pessoa pes8 = new Pessoa(null,
                "Éder Jean Dias",
                "eder@gmail.com",
                "123",
                PerfilEnum.ROLE_USUARIO,
                unidade1,
                "",
                "",
                "SIS",
                null,
                calendar.getTime(),
                null,
                calendar.getTime());
        pessoaRepository.save(pes8);

        Pessoa pes9 = new Pessoa(null,
                "Felipe Haag de Lima",
                "felipe@gmail.com",
                "123",
                PerfilEnum.ROLE_USUARIO,
                unidade1,
                "",
                "",
                "SIS",
                null,
                calendar.getTime(),
                null,
                calendar.getTime());
        pessoaRepository.save(pes9);

        Pessoa pes10 = new Pessoa(null,
                "Patrício Henrique Lehn",
                "patricio@gmail.com",
                "123",
                PerfilEnum.ROLE_USUARIO,
                unidade1,
                "",
                "",
                "SIS",
                null,
                calendar.getTime(),
                null,
                calendar.getTime());
        pessoaRepository.save(pes10);

        Optional<Unidade> optUni = unidadeRepository.findById(1L);
        Unidade uni = optUni.get();
        unidade1 = new Unidade(uni.getUniId(),
                uni.getUniNome(),
                uni.getUniAtiva(),
                uni.getUniDtCadastro(),
                pes.getPesId(),
                uni.getUniDtAtualizacao(),
                pes.getPesId());
        unidadeRepository.save(unidade1);

        Equipamento equipamento1 = new Equipamento(
                null,
                "Notebook Acer",
                "Notebook Acer Core i3",
                unidade1,
                true,
                pes.getPesId(),
                calendar.getTime(),
                calendar.getTime(),
                pes.getPesId());

        Equipamento equipamento2 = new Equipamento(
                null,
                "Notebook Dell",
                "Notebook Dell Core i5",
                unidade1,
                true,
                pes.getPesId(),
                calendar.getTime(),
                calendar.getTime(),
                pes.getPesId());

        Equipamento equipamento3 = new Equipamento(
                null,
                "Projetor",
                "Projetor",
                unidade1,
                false,
                pes.getPesId(),
                calendar.getTime(),
                calendar.getTime(),
                pes.getPesId());

        Equipamento equipamento4 = new Equipamento(
                null,
                "Notebook Phillips",
                "Notebook Phillips Core i9",
                unidade1,
                true,
                pes.getPesId(),
                calendar.getTime(),
                calendar.getTime(),
                pes.getPesId());

        equipamentoRepository.save(equipamento1);
        equipamentoRepository.save(equipamento2);
        equipamentoRepository.save(equipamento3);
        equipamentoRepository.save(equipamento4);

        Sala sala1 = new Sala(
                null,
                "Setor Comercial Sala 2",
                10,
                true,
                pes.getPesId(),
                calendar.getTime(),
                calendar.getTime(),
                pes.getPesId(),
                unidade1);

        Sala sala2 = new Sala(
                null,
                "Setor Comercial Sala 1",
                8,
                false,
                pes.getPesId(),
                calendar.getTime(),
                calendar.getTime(),
                pes.getPesId(),
                unidade1);
        Sala sala3 = new Sala(
                null,
                "Sala para Reuniões 1",
                22,
                true,
                pes.getPesId(),
                calendar.getTime(),
                calendar.getTime(),
                pes.getPesId(),
                unidade1);
        Sala sala4 = new Sala(
                null,
                "Sala para Reuniões 2",
                10,
                true,
                pes.getPesId(),
                calendar.getTime(),
                calendar.getTime(),
                pes.getPesId(),
                unidade1);

        salaRepository.save(sala1);
        salaRepository.save(sala2);
        salaRepository.save(sala3);
        salaRepository.save(sala4);

        Date ageData = new Date("2019/12/17");

        // COLOCAR UMA HORA A MAIS PARA NAO DAR DIFERENca NO FRONT
        Date ageHoraInicio1 = new Date("2019/12/17 08:00:00");
        Date ageHoraFim1 = new Date("2019/12/17 09:00:00");

        Agendamento age = new Agendamento(
                null,
                "Retirar Dúvidas",
                "Retirar Dúvidas com Cliente referente as novas Solicitações dos Clientes",
                "AGENDADO",
                ageData,
                ageHoraInicio1,
                ageHoraFim1,
                calendar.getTime(),
                calendar.getTime(),
                sala1,
                pes7,
                1l,
                1l,
                null,
                null);

        Date ageHoraInicio2 = new Date("2019/12/17 08:00:00");
        Date ageHoraFim2 = new Date("2019/12/17 10:30:00");
        Agendamento age2 = new Agendamento(
                null,
                "Planejamento do Novo projeto",
                "Encontro com responsáveis para Planejamento do Novo projeto",
                "AGENDADO",
                ageData,
                ageHoraInicio2,
                ageHoraFim2,
                calendar.getTime(),
                calendar.getTime(),
                sala1,
                pes9,
                1l,
                1l,
                null,
                null);

        Date ageHoraInicio3 = new Date("2019/12/17 11:00:00");
        Date ageHoraFim3 = new Date("2019/12/17 11:30:00");
        Agendamento age3 = new Agendamento(
                null,
                "Repassar Demandas para Equipe",
                "Repassar novos Testes e tempo de entrega de cada um para a Equipe",
                "AGENDADO",
                ageData,
                ageHoraInicio3,
                ageHoraFim3,
                calendar.getTime(),
                calendar.getTime(),
                sala3,
                pes10,
                1l,
                1l,
                null,
                null);

        Date ageHoraInicio4 = new Date("2019/12/17 16:00:00");
        Date ageHoraFim4 = new Date("2019/12/17 17:30:00");

        Agendamento age4 = new Agendamento(
                null,
                "Planejar Kanban",
                "Planejamento do Kanban da Sprint com Equipe de Desenvolvimento",
                "AGENDADO",
                ageData,
                ageHoraInicio4,
                ageHoraFim4,
                calendar.getTime(),
                calendar.getTime(),
                sala4,
                pes8,
                1l,
                1l,
                null,
                null);

        Agendamento ageGravado = agendamentoRepository.save(age);
        Agendamento ageGravado2 = agendamentoRepository.save(age2);
        Agendamento ageGravado3 = agendamentoRepository.save(age3);
        Agendamento ageGravado4 = agendamentoRepository.save(age4);

        /// PRIMEIRO
        Participante part1Age1 = new Participante(null, 1, null, pes7, ageGravado);
        Participante part2Age1 = new Participante(null, 1, null, pes8, ageGravado);
        Participante part3Age1 = new Participante(null, 1, null, pes9, ageGravado);
        Participante part4Age1 = new Participante(null, 1, null, pes10, ageGravado);

        /// SEGUNDO
        Participante part1Age2 = new Participante(null, 1, null, pes7, ageGravado2);
        Participante part2Age2 = new Participante(null, 1, null, pes8, ageGravado2);
        Participante part3Age2 = new Participante(null, 1, null, pes9, ageGravado2);
        Participante part4Age2 = new Participante(null, 1, null, pes10, ageGravado2);

        /// 3
        Participante part1Age3 = new Participante(null, 1, null, pes7, ageGravado3);
        Participante part2Age3 = new Participante(null, 1, null, pes8, ageGravado3);
        Participante part3Age3 = new Participante(null, 1, null, pes9, ageGravado3);
        Participante part4Age3 = new Participante(null, 1, null, pes10, ageGravado3);

        /// 4
        Participante part1Age4 = new Participante(null, 1, null, pes7, ageGravado4);
        Participante part2Age4 = new Participante(null, 1, null, pes8, ageGravado4);
        Participante part3Age4 = new Participante(null, 1, null, pes9, ageGravado4);
        Participante part4Age4 = new Participante(null, 1, null, pes10, ageGravado4);

        participanteRepository.save(part1Age1);
        participanteRepository.save(part2Age1);
        participanteRepository.save(part3Age1);
        participanteRepository.save(part4Age1);

        participanteRepository.save(part1Age2);
        participanteRepository.save(part2Age2);
        participanteRepository.save(part3Age2);
        participanteRepository.save(part4Age2);

        participanteRepository.save(part1Age3);
        participanteRepository.save(part2Age3);
        participanteRepository.save(part3Age3);
        participanteRepository.save(part4Age3);

        participanteRepository.save(part1Age4);
        participanteRepository.save(part2Age4);
        participanteRepository.save(part3Age4);
        participanteRepository.save(part4Age4);

        // RESERVA EQUIPAMENTO
        ReservaEquipamento reserv1Age1 = new ReservaEquipamento(null, equipamento1, ageGravado);

        ReservaEquipamento reserv2Age2 = new ReservaEquipamento(null, equipamento2, ageGravado2);

        ReservaEquipamento reserv2Age4 = new ReservaEquipamento(null, equipamento2, ageGravado4);

        reservaEquipamentoRepository.save(reserv1Age1);
        reservaEquipamentoRepository.save(reserv2Age2);
        reservaEquipamentoRepository.save(reserv2Age4);

    }
}
