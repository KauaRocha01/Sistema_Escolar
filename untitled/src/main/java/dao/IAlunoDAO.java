package dao;

import model.Aluno;

import java.util.List;
import java.util.Optional;

public interface IAlunoDAO {
    //Crud
    //C - creat
   void salvarAluno(Aluno aluno);
   //R - Read
   List<Aluno> listarTodosAlunos();
   //U - Update
    void atualizarAluno(Aluno aluno);
    //D - Delete
    void excluirAluno(int id);

    Optional<Aluno> buscarPorId (int id);
}

