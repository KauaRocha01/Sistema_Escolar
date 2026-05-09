package org.example;

import daolmplements.AlunoDAOImplements;
import database.slqConn;
import model.Aluno;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class App
{
    public static void main( String[] args )
    {
        slqConn.testeConnction();

        AlunoDAOImplements alunoDaoMethods = new AlunoDAOImplements();
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




            }

        }while (opcao !=0);


            }
        }



