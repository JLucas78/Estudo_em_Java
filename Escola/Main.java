package Escola;

import java.util.ArrayList;

public class Main {
    
    public static void main(String[] args) {
        Turma nova = new Turma();

        nova.prof = new Professor();
        nova.prof.nomeProfessor = "Lucas";

        nova.alunos = new ArrayList<>();
        nova.alunos.add(new Aluno());
        nova.alunos.get(0).nomeAluno = "Renan";
    }
}
