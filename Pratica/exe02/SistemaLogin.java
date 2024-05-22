package Pratica.exe02;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SistemaLogin {

    private static Scanner scanner = new Scanner(System.in);
    private static Map<String, Usuario> usuarios = new HashMap<>();
    private static Usuario usuarioLogado;

    public static void main(String[] args) {
        menuPrincipal();
    }

    public static void menuPrincipal() {
        while (true) {
            System.out.println("Selecione uma opção:");
            System.out.println("1. Criar novo usuário");
            System.out.println("2. Fazer login");
            System.out.println("3. Sair");

            String resposta = scanner.nextLine();

            switch (resposta) {
                case "1":
                    criarNovoUsuario();
                    break;
                case "2":
                    fazerLogin();
                    break;
                case "3":
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

    private static void criarNovoUsuario() {
        System.out.println("Digite o nome de usuário:");
        String nomeUsuario = scanner.nextLine();

        // Verificar se o nome de usuário já existe
        if (usuarios.containsKey(nomeUsuario)) {
            System.out.println("Nome de usuário já existe. Por favor, escolha outro.");
            return;
        }

        System.out.println("Digite a senha:");
        String senha = scanner.nextLine();

        Usuario novoUsuario = new Usuario(nomeUsuario, senha);
        usuarios.put(nomeUsuario, novoUsuario);

        System.out.println("Usuário cadastrado com sucesso!");
    }

    private static void fazerLogin() {
        System.out.println("Digite o nome de usuário:");
        String nomeUsuario = scanner.nextLine();

        System.out.println("Digite a senha:");
        String senha = scanner.nextLine();

        // Verificar se o usuário existe e a senha está correta
        if (usuarios.containsKey(nomeUsuario)) {
            Usuario usuario = usuarios.get(nomeUsuario);
            if (usuario.verificarSenha(senha)) {
                usuarioLogado = usuario;
                System.out.println("Login bem-sucedido. Bem-vindo, " + usuario.getNomeUsuario() + "!");
                // Aqui você pode chamar uma função de menu para o usuário logado ou executar outras ações do sistema
            } else {
                System.out.println("Senha incorreta.");
            }
        } else {
            System.out.println("Nome de usuário não encontrado.");
        }
    }
}
