package org.example;

import dao.ITurmaDAO;
import daolmplements.AlunoDAOImplements;
import daolmplements.TurmaDAOImplements;
import database.sqlConn;
import model.Aluno;
import model.Turma;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class App
{
    public static void main( String[] args )
    {
        sqlConn.testeConnction();

        AlunoDAOImplements alunoDaoMethods = new AlunoDAOImplements();
        TurmaDAOImplements turmaDAOImplements = new TurmaDAOImplements();
        Scanner sc = new Scanner(System.in);

        int opcao;

        do{

            System.out.println("=======MENU=======");
            System.out.println("1. Cadastrar Aluno");
            System.out.println("2. Atualizar Aluno");
            System.out.println("3. Excluir Aluno");
            System.out.println("4 Listar Aluno");
            System.out.println("5. Salvar Aluno");
            System.out.println("0. Sair do programa");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao){
                case 1:
                    System.out.println("Cadrastro de aluno");

                    System.out.println("Nome: ");
                    String nome = sc.nextLine();

                    System.out.println("Cpf: ");
                    String cpf = sc.nextLine();

                    System.out.println("Email: ");
                    String email = sc.nextLine();

                    System.out.println("Data de nascimento:");
                    LocalDate dataNascimento;
                    try {
                        dataNascimento = LocalDate.parse(sc.nextLine());
                    }catch (Exception e){
                        throw new RuntimeException("Erro de data. Tente: aaaa-mm-dd");
                    }

                    System.out.println("Telefone: ");
                    String telefone = sc.nextLine();

                    Aluno alunoNovo = new Aluno(nome, cpf, email, dataNascimento, telefone);
                    alunoDaoMethods.salvarAluno(alunoNovo);
                    break;
                case 2:
                    System.out.println("Atualizar aluno");
                    break;
                case 3:
                    System.out.println("Excluir Aluno");
                    System.out.print("Digite o ID do aluno a excluir: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    alunoDaoMethods.excluirAluno(id);
                    break;
                case 4:
                    System.out.println("Listar Alunos");
                    break;
                case 5:
                    System.out.println("Listar Aluno por ID. Informe um ID para pesquisar");
                    int idBusca = sc.nextInt();

                    Optional<Aluno> alunoEncontrado = alunoDaoMethods.buscarPorId(idBusca);

                    if (alunoEncontrado.isPresent()){
                        System.out.println(alunoEncontrado);
                    }else {
                        System.out.println("nenhum aluno encontrado");
                    }
                    break;


                case 6:
                    System.out.println("Listar turmas");


                    List<Turma> todasTurmas = turmaDAOImplements.listarTodasTurmas();

                    if (todasTurmas.isEmpty()) {
                        System.out.println("Nenhuma turma encontrada");
                    } else {
                        for (Turma turma : todasTurmas) {
                            System.out.println(turma);
                        }
                    }

                    System.out.println("Informe o id da turma para visualizar os alunos:");
                    int idInformado = sc.nextInt();

                    List<Aluno> alunosTurmaEncontrada = turmaDAOImplements.listarAlunosPorTurmaID(idInformado);

                    if (alunosTurmaEncontrada.isEmpty()) {
                        System.out.println("Nenhum aluno encontrado nesta turma!");
                    } else {
                        System.out.println("Alunos matriculados: ");
                        for (Aluno aluno : alunosTurmaEncontrada) {
                            System.out.println(aluno);
                        }
                    }




            }

        }while (opcao !=0);


            }
        }



