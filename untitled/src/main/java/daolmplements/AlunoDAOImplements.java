package daolmplements;

import database.slqConn;
import dao.IAlunoDAO;
import model.Aluno;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlunoDAOImplements implements IAlunoDAO {

    @Override
    public void salvarAluno(Aluno aluno) {
        String sql = "INSERT INTO aluno(nome, cpf, email, data_nascimento, telefone) VALUES (?,?,?,?,?)";

        try (Connection conn = slqConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCpf());
            stmt.setString(3, aluno.getEmail());
            stmt.setDate(4, java.sql.Date.valueOf(aluno.getDataNascimento()));
            stmt.setString(5, aluno.getTelefone());

            int resultado = stmt.executeUpdate();

            if (resultado > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        String idGerado = rs.getString(1);
                        aluno.setId(idGerado);
                        System.out.println("Aluno salvo com ID: " + idGerado);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Aluno> buscarPorId(int id) {
        String sql = "SELECT * FROM aluno WHERE id = ?";

        try (Connection conn = slqConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Aluno aluno = new Aluno(
                            rs.getString("id"),
                            rs.getString("nome"),
                            rs.getString("cpf"),
                            rs.getString("email"),
                            rs.getDate("data_nascimento").toLocalDate(),
                            rs.getString("telefone")
                    );
                    return Optional.of(aluno);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public List<Aluno> listarTodosAlunos() {
        String sql = "SELECT * FROM aluno ORDER BY nome ASC";
        List<Aluno> alunos = new ArrayList<>();

        try (Connection conn = slqConn.getConnection();
             PreparedStatement stnt = conn.prepareStatement(sql);
             ResultSet rs = stnt.executeQuery()) {

            while (rs.next()) {
                alunos.add(new Aluno(
                        rs.getString("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("email"),
                        rs.getDate("data_nascimento").toLocalDate(),
                        rs.getString("telefone")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar os alunos:" + e.getMessage());
        }

        return alunos;
    }

    @Override
    public void atualizarAluno(Aluno aluno) {
        String sql = "UPDATE aluno SET nome = ?, cpf = ?, email = ?, data_nascimento = ?, telefone = ? WHERE id = ?";

        try (Connection conn = slqConn.getConnection();
             PreparedStatement stnt = conn.prepareStatement(sql)) {

            stnt.setString(1, aluno.getNome());
            stnt.setString(2, aluno.getCpf());
            stnt.setString(3, aluno.getEmail());
            stnt.setDate(4, java.sql.Date.valueOf(aluno.getDataNascimento()));
            stnt.setString(5, aluno.getTelefone());
            stnt.setInt(6, Integer.parseInt(aluno.getId()));

            int linhasAtualizada = stnt.executeUpdate();
            if (linhasAtualizada > 0) {
                System.out.println("Aluno atualizado com sucesso");
            } else {
                System.out.println("Nenhum aluno foi atualizado");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar o aluno: " + e.getMessage());
        }
    }

    @Override
    public void excluirAluno(int id) {
        String sql = "DELETE FROM aluno WHERE id = ?";

        try (Connection conn = slqConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Aluno deletado com sucesso!");
            } else {
                System.out.println("Nenhum aluno encontrado com esse ID.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}