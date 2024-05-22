package pratica.exe01;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Scanner escanear = new Scanner(System.in);
    private static ArrayList<Pessoa> pessoasCadastradas = new ArrayList<>();

    public static void main(String[] args) {
        // Iniciar o menu
        Menu();
    }

    public static void Menu() {
        while (true) {
            System.out.println("Selecione alguma opção abaixo: ");
            System.out.println("1. Cadastrar uma nova pessoa.");
            System.out.println("2. Visualizar todos os cadastros.");
            System.out.println("3. Sair.");

            String resposta = escanear.next();

            switch (resposta) {
                case "1":
                    CadastrarPessoa();
                    break;
                case "2":
                    VisualizarCadastros();
                    break;
                case "3":
                    System.out.println("Saindo...");
                    return; // Termina o loop e o programa
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

    private static void CadastrarPessoa() {
        System.out.println("Digite o nome:");
        escanear.nextLine(); // Consumir a quebra de linha pendente
        String nome = escanear.nextLine();

        System.out.println("Agora digite a idade:");
        int idade = escanear.nextInt();

        Pessoa novaPessoa = new Pessoa(nome, idade);
        pessoasCadastradas.add(novaPessoa);

        System.out.println("Pessoa cadastrada com sucesso!");
    }

    private static void VisualizarCadastros() {
        if (pessoasCadastradas.isEmpty()) {
            System.out.println("Nenhuma pessoa cadastrada.");
        } else {
            for (Pessoa pessoaAtual : pessoasCadastradas) {
                System.out.println("Nome: " + pessoaAtual.getNome() + ", Idade: " + pessoaAtual.getIdade());
            }
        }
    }
}
