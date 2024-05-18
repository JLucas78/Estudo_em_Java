package POO_Guanabara.Aula2_Conta_Bancaria;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ContaBancaria minhaConta = new ContaBancaria();
        boolean menu = true;
        Scanner scanner = new Scanner(System.in);
        

        minhaConta.infos();

        while (menu) {
            System.out.println("------------------------------------------------");
            System.out.println("O que você deseja fazer, " + minhaConta.titular + "?");
            System.out.println("Para fazer um depósito, digite 'D'.");
            System.out.println("Para fazer um saque, digite 'S'.");
            System.out.println("Para consultar o seu saldo atual, digite 'C'.");
            System.out.println("Para fechar o menu, digite 'F'.");
            System.out.println("------------------------------------------------");
            String resposta = scanner.nextLine();

            if (resposta.equalsIgnoreCase("D")) {
                minhaConta.depositar();
            } else if (resposta.equalsIgnoreCase("S")) {
                minhaConta.sacar();
            } else if (resposta.equalsIgnoreCase("C")) {
                minhaConta.consultar();
            } else if (resposta.equalsIgnoreCase("F")) {
                menu = false;
            } else {
                System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        }
        scanner.close();
        System.out.println("Até a próxima!");
    }
}
