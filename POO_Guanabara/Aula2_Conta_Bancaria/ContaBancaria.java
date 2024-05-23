package POO_Guanabara.Aula2_Conta_Bancaria;

import java.util.Scanner;
import java.util.Random;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class ContaBancaria {
    String titular;
    float saldo;

    Scanner escanear = new Scanner(System.in);
    Random valorAleatorio = new Random();
    DecimalFormat formatarValor = new DecimalFormat("#,##0.00");
    ArrayList<Transacao> historico = new ArrayList<>();

    void infos() {
        System.out.println("Por favor, digite o seu nome abaixo: ");
        titular = escanear.nextLine();
        System.out.println("Prazer senhor(a) " + this.titular + ", Bom dia!");
        saldo = valorAleatorio.nextFloat() * 10000; // Gerando um saldo aleatório entre 0 e 10000
        System.out.println("O saldo inicial da sua conta é: R$" + formatarValor.format(this.saldo));
    }

    void consultar() {
        System.out.println("O seu saldo atual é: R$" + formatarValor.format(saldo));
    }

    void depositar() {
        System.out.print("Digite o valor que você deseja depositar: ");
        float deposito = escanear.nextFloat();
        if (deposito > 0) {
            saldo += deposito;
            System.out.println("Depósito de R$" + formatarValor.format(deposito) + " realizado com sucesso. Seu saldo atual é de R$" + formatarValor.format(saldo) + ".");

            Transacao transacao = new Transacao("Depósito", deposito);
            historico.add(transacao);
        } else {
            System.out.println("Valor de depósito inválido. Informe um valor acima de R$0,00.");
        }
        escanear.nextLine(); // Consumir a nova linha remanescente
    }

    void sacar() {
        System.out.print("Quanto você deseja sacar? ");
        float saque = escanear.nextFloat();
        if (saque <= saldo) {
            saldo -= saque;
            System.out.println("O seu saldo era de R$" + formatarValor.format(saldo + saque) + ", e o valor do saque foi de R$" + formatarValor.format(saque) + ". Agora o seu saldo é de R$" + formatarValor.format(saldo) + ".");

            Transacao transacao = new Transacao("Saque", saque);
            historico.add(transacao);
        } else {
            System.out.println("Você não possui saldo suficiente para fazer esse saque.");
        }
        escanear.nextLine(); // Consumir a nova linha remanescente
    }

    void exibirTitular() {
        System.out.println("Titular da conta: " + titular);
    }

    void exibirHistorico() {
        System.out.println("Você fez " + historico.size() + " transações até o momento. Sendo elas: ");
        for (Transacao posicaoNoHistorico : historico) {
            System.out.println(posicaoNoHistorico.detalhes());
        }
        
    }
}
