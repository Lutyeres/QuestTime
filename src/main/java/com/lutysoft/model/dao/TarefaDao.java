package com.lutysoft.model.dao;

import com.lutysoft.model.entiny.Tarefa;
import com.lutysoft.util.connection.ConnectionFactory;
import com.lutysoft.util.connection.MySqlConnectionFactory;
import org.w3c.dom.ls.LSOutput;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TarefaDao {

    private final ConnectionFactory conexao;

    public TarefaDao(){
        conexao = new MySqlConnectionFactory();
    }

    private static final String SQL_INSERT_INICIO = "INSERT INTO tarefa(tarefaNome,tarefaObs,tarefaDataHoraInicio) values (?,?,?)";
    private static final String SQL_UPDATE_FIM = "UPDATE tarefa SET tarefaDataHoraFinal = ?, taferaNome = ?, tarefaObs = ? WHERE idTarefa = ?";
    private static final String SQL_UPDATE_TAREFA = "UPDATE tarefa SET tarefaNome = ?, tarefaObs = ? WHERE idTarefa = ?  ";
    private static final String SQL_DELETE = "DELETE FROM tarefa WHERE idTarefa = ?";
    private static final String SQL_SELECT_ID = "SELECT * FROM tarefa WHERE idTarefa = ?";
    private static final String SQL_SELECT_NAME = "SELECT * FROM tarefa WHERE tarefaNome = ?";
    private static final String SQL_SELECT_NAME_NO_FINALIZED = "SELECT * FROM tarefa WHERE tarefaNome = ? AND tarefaDataHoraFinal IS NULL";

    //Metodo que vai decidir se vai salvar ou atualizar a tarefa
    public String create(Tarefa tarefa) {
        return tarefa.getId() == 0 ? add(tarefa) : update(tarefa);
    }

    //Metodo para adicionar uma tarefa no BD
    private String add(Tarefa tarefa){
        Tarefa tarefaTest = getTarefaNoFinalized(tarefa.getNome());
        if(tarefaTest != null){
            return String.format("ERROR: A tarefa %s não foi finalizada, para adicionar um tarefa semelhante finalize a tarefa primeiro", tarefaTest.getNome());
        }


        try(Connection con = conexao.getConnection(); PreparedStatement stmt = con.prepareStatement(SQL_INSERT_INICIO)){
            stmt.setString(1, tarefa.getNome());
            stmt.setString(2, tarefa.getObs());
            stmt.setObject(3, tarefa.getDataHorarioInicio());


            int resultado = stmt.executeUpdate();

            return resultado == 1 ? "Tarefa adicionada com sucesso!" : "Não foi possível adicionar a tarefa!";

        } catch (SQLException e) {
            return String.format("Error: %s", e.getMessage());
        }

    }

    //Metodo para atualizar uma tarefa do BD
    private String update(Tarefa tarefa){

        if(tarefa.getDataHorarioFinal() != null){

            try(Connection con = conexao.getConnection(); PreparedStatement stmt = con.prepareStatement(SQL_UPDATE_FIM)){

                stmt.setObject(1, tarefa.getDataHorarioFinal());
                stmt.setString(2, tarefa.getNome());
                stmt.setString(3, tarefa.getObs());
                stmt.setInt(4, tarefa.getId());

                int resultado = stmt.executeUpdate();
                return resultado == 1 ? "Tarefa alterada com sucesso!" : "Não foi possível alterar a tarefa";

            }catch (SQLException e){
                return String.format("ERROR: %s", e.getMessage());
            }

        }else {
            try (Connection con = conexao.getConnection(); PreparedStatement stmt = con.prepareStatement(SQL_UPDATE_TAREFA);) {

                stmt.setString(1, tarefa.getNome());
                stmt.setString(2, tarefa.getObs());
                stmt.setInt(3, tarefa.getId());

                int resultado = stmt.executeUpdate();
                return resultado == 1 ? "Tarefa alterada com sucesso!" : "Não foi possível alterar a tarefa";

            } catch (SQLException e) {
                return String.format("ERROR: %s", e.getMessage());

            }
        }
    }

    //Metodo para deletar uma tarefa do DB
    public String delete(Tarefa tarefa){

        try (Connection con = conexao.getConnection(); PreparedStatement stmt = con.prepareStatement(SQL_DELETE);){

            stmt.setInt(1, tarefa.getId());

            int resultado = stmt.executeUpdate();
            return resultado == 1 ? "Tarefa excluída com sucessor" : "Não foi possivel excluir essa tarefa!";

        }catch (SQLException e){
            return String.format("ERROR: %s", e.getMessage());
        }
    }

    //Metodo para pegar o ResultSet do BD e Transformar ele em uma Tarefa()
    private Tarefa getTarefa(ResultSet rs) throws SQLException{
        Tarefa tarefa = new Tarefa();

        tarefa.setId((rs.getInt("idTarefa")));
        tarefa.setNome(rs.getString("tarefaNome"));
        tarefa.setObs(rs.getString("tarefaObs"));
        tarefa.setDataHorarioInicio(rs.getObject("tarefaDataHoraInicio", LocalDateTime.class));
        tarefa.setDataHorarioFinal(rs.getObject("tarefaDataHoraFinal", LocalDateTime.class));

        return tarefa;

    }

    //Metodo para buscas a tarefa pelo ID
    public Tarefa getTarefaId (int idtarefa){

        try (Connection con = conexao.getConnection(); PreparedStatement stmt = con.prepareStatement(SQL_SELECT_ID)){
            stmt.setInt(1,idtarefa);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                return getTarefa(rs);
            }
        } catch (SQLException e) {
            System.out.println(String.format("Error: %s", e.getMessage()));
        }

        return null;

    }

    //Metodo que busca as tarefas pelo nome e retorna um ArrayList
    public List<Tarefa> getTarefaName(Tarefa tarefaNome){
        List<Tarefa> tarefas = new ArrayList<>();

        try (Connection con = conexao.getConnection(); PreparedStatement stmt = con.prepareStatement(SQL_SELECT_NAME);){
            stmt.setString(1,tarefaNome.getNome());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                tarefas.add(getTarefa(rs));
            }

        } catch (SQLException e) {
            System.out.println(String.format("Error: %s", e.getMessage()));
        }

        return tarefas;
    }

    //Metodo que busca as tarefas pelo nome que ainda não foram finalizadas
    public Tarefa getTarefaNoFinalized (String tarefaName){

        try (Connection con = conexao.getConnection(); PreparedStatement stmt = con.prepareStatement(SQL_SELECT_NAME_NO_FINALIZED)){
            stmt.setString(1,tarefaName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                return getTarefa(rs);
            }
        } catch (SQLException e) {
            System.out.println(String.format("Error: %s", e.getMessage()));
        }

        return null;
    }

}
