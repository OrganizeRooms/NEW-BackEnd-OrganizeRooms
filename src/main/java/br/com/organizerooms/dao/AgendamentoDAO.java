/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.organizerooms.dao;

import br.com.organizerooms.dto.EquipamentoDTO;
import br.com.organizerooms.dto.SalaDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Aluno
 */
@Transactional
@Repository
public class AgendamentoDAO {

    @PersistenceContext
    private EntityManager em;

    public List<SalaDTO> recuperaSala(String idUnidade, String lotacao, String dataInicial, String dataFinal, String dataAgendamento) {
        List<SalaDTO> salas = new ArrayList<>();
        try {
            Connection sqlConnection = getConection();
            //Connection sqlConnection  =  DriverManager.getConnection("jdbc:mysql://localhost:3306/organizerooms", "root", "");
            Statement stmt = sqlConnection.createStatement();
            ResultSet rs = stmt.executeQuery("CALL PROC_RECUPERA_DISPONIVEIS("
                    + idUnidade + ","
                    + lotacao + ","
                    + "'" + dataInicial + "',"
                    + "'" + dataFinal + "',"
                    + "'" + dataAgendamento + "')"
            );

            while (rs.next()) {
                SalaDTO sala = new SalaDTO();
                sala.setSalaId(rs.getLong(1));
                sala.setSalaNome(rs.getString(2));
                sala.setSalaLotacao(rs.getInt(3));
                salas.add(sala);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return salas;

    }

    public List<EquipamentoDTO> recuperaEquipamento(String idUnidade, String dataInicial, String dataFinal) {
        List<EquipamentoDTO> equipamentos = new ArrayList<>();
        try {
            Connection sqlConnection = getConection();
            Statement stmt = sqlConnection.createStatement();
            ResultSet rs = stmt.executeQuery("CALL PROC_RECUPERA_EQUIPAMENTOS("
                    + idUnidade + ","
                    + "'" + dataInicial + "',"
                    + "'" + dataFinal + "')"
            );

            while (rs.next()) {
                EquipamentoDTO equipamento = new EquipamentoDTO();
                equipamento.setEquId(rs.getLong(1));
                equipamento.setEquNome(rs.getString(2));
                equipamento.setEquDescricao(rs.getString(3));
                equipamentos.add(equipamento);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return equipamentos;

    }

    private Connection getConection() {
        try {
            // LocalHost
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/organizerooms", "root", "");

            // Servidor
            //return DriverManager.getConnection("jdbc:mysql://us-cdbr-iron-east-05.cleardb.net/heroku_f0529be4b8bcd9f?reconnect=true", "b79d80426e3788", "95369cb2");
        } catch (SQLException ex) {
            Logger.getLogger(AgendamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
