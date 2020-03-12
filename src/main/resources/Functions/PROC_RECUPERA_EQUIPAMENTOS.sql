CREATE DEFINER=`root`@`localhost` PROCEDURE `organizerooms`.`PROC_RECUPERA_EQUIPAMENTOS`(idUnidade INT, dataInicial DATETIME, dataFinal DATETIME)
BEGIN
    select eq.equ_id, eq.equ_nome, eq.equ_descricao 
      from equipamento eq 
     where eq.equ_id 
        not in (
            select e.equ_id from agendamento a
        inner join reserva_equipamento r on r.age_id = a.age_id
        inner join equipamento e on e.equ_id = r.equ_id
             where e.uni_id = idUnidade
               and e.equ_ativa = 1
               and a.age_hora_inicio >= dataInicial
               and a.age_hora_inicio <= dataFinal
        )
       and eq.uni_id = idUnidade
       and eq.equ_ativa = 1;
END