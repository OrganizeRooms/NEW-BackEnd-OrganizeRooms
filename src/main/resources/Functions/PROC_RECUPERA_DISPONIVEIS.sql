CREATE DEFINER=`root`@`localhost` PROCEDURE `organizerooms`.`PROC_RECUPERA_DISPONIVEIS`(idUnidade INT, lotacao INT, dataInicial DATETIME, dataFinal DATETIME, dataAgendamento DATE)
BEGIN
	 select SALA_ID, SALA_NOME, SALA_LOTACAO from sala s where s.sala_id not in (
select s.sala_id from agendamento a
inner join sala s on a.age_sala = s.sala_id
where s.uni_id = idUnidade
and s.sala_ativa = 1
and a.age_hora_inicio >= dataInicial
and a.age_hora_inicio <= dataFinal)
and s.uni_id = idUnidade
and s.sala_ativa = 1
and s.sala_lotacao >= lotacao;

 END