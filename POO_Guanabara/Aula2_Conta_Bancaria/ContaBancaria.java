package POO_Guanabara.Aula2_Conta_Bancaria;

import java.util.Scanner;
import java.util.Random;
import java.text.DecimalFormat;

public class ContaBancaria {
    String titular;
    float saldo;
    Scanner scanner = new Scanner(System.in);
    Random valorAleatorio = new Random();
    DecimalFormat formatoSaldo = new DecimalFormat("#,##0.00");

    void infos() {
        System.out.println("Por favor, digite o seu nome abaixo: ");
        titular = scanner.nextLine();
        System.out.println("Prazer senhor(a) " + this.titular + ", Bom dia!");
        saldo = valorAleatorio.nextFloat() * 10000; // Gerando um saldo aleatório entre 0 e 10000
        System.out.println("O saldo inicial da sua conta é: R$" + formatoSaldo.format(this.saldo));
    }

    // Método para consultar o saldo
    void consultar() {
        System.out.println("O seu saldo atual é: R$" + saldo);
    }

    // Método para depositar um valor
    void depositar() {
        System.out.print("Digite o valor que você deseja depositar: ");
        float valor = scanner.nextFloat();
        if (valor > 0) {
            saldo += valor;
            System.out.println("Depósito de R$" + valor + " realizado com sucesso. Seu saldo atual é de " + formatoSaldo.format(saldo) + "R$.");
        } else {
            System.out.println("Valor de depósito inválido. Informe um valor acima de R$0,00.");
        }
        scanner.nextLine(); // Consumir a nova linha remanescente
    }

    // Método para sacar um valor
    void sacar() {
        System.out.println("Quanto você deseja sacar? ");
        float saque = scanner.nextFloat();
        if (saque <= saldo) {
            saldo -= saque;
            System.out.println("O seu saldo era de R$" + formatoSaldo.format(saldo + saque) + ", e o valor do saque foi de R$" + formatoSaldo.format(saque) + ". Agora o seu saldo é de R$" + formatoSaldo.format(saldo) + ".");
        } else {
            System.out.println("Você não possui saldo suficiente para fazer esse saque.");
        }
        scanner.nextLine(); // Consumir a nova linha remanescente
    }

    // Método para exibir o titular da conta
    public void exibirTitular() {
        System.out.println("Titular da conta: " + titular);
    }
}
