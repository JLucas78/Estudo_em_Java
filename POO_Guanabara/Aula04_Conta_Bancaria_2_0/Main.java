package POO_Guanabara.Aula04_Conta_Bancaria_2_0;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private Scanner escanear = new Scanner(System.in);
    private ArrayList<ContaBancaria> listaDeContasBancarias = new ArrayList<>();
    private DecimalFormat df = new DecimalFormat("0.00");

    public static void main(String[] args) {
        Main main = new Main();
        main.menuPrincipal();
    }

    public void menuPrincipal() {
        while (true) {
            System.out.println("1. Abrir uma nova conta.");
            System.out.println("2. Listar contas existentes.");
            System.out.println("3. Excluir alguma conta.");
            System.out.println("4. Sair.");

            String resposta = escanear.nextLine();

            switch (resposta) {
                case "1":
                    abrirNovaConta();
                    break;
                case "2":
                    listar();
                    break;
                case "3":
                    excluirConta();
                    break;
                case "4":
                    System.out.println("Encerrando o programa...");
                    return;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
                    break;
            }
        }
    }

    public void abrirNovaConta() {
        Random random = new Random();
        String numeroDaConta = String.format("%06d", random.nextInt(1000000));
        Float saldoInicial = random.nextFloat() * 10000;
        
        System.out.println("Preencha todas as informações abaixo.");
        System.out.println("Qual o seu nome?");
        String titular = escanear.nextLine();

        System.out.println("Que tipo de conta você deseja abrir?");
        System.out.println("1. Conta Poupança.");
        System.out.println("2. Conta Corrente.");
        String tipo = escanear.nextLine();
        Float bonusInicial = 0f;

        switch (tipo) {
            case "1":
                tipo = "Poupança";
                bonusInicial = 150f;
                break;
            case "2":
                tipo = "Corrente";
                bonusInicial = 50f;
                break;
            default:
                System.out.println("Tipo inválido. Conta Corrente será usada como padrão.");
                tipo = "Corrente";
                bonusInicial = 50f;
                break;
        }

        Boolean status = true;
        Float saldoDisponivel = saldoInicial + bonusInicial;

        ContaBancaria novaContaBancaria = new ContaBancaria(numeroDaConta, tipo, titular, saldoDisponivel, status);
        listaDeContasBancarias.add(novaContaBancaria);

        menudoUsuario(novaContaBancaria);
    }

    public void menudoUsuario(ContaBancaria conta) {
        while (true) {
            System.out.println("Bem-vindo " + conta.getTitular() + ", selecione a opção que você deseja: ");
            System.out.println("1. Verificar informações da conta.");
            System.out.println("2. Depositar.");
            System.out.println("3. Sacar.");
            if (conta.getStatus()) {
                System.out.println("4. Bloquear conta.");
            } else {
                System.out.println("4. Desbloquear conta.");
            }
            System.out.println("5. Excluir conta atual.");
            System.out.println("6. Voltar ao menu principal.");

            String resposta = escanear.nextLine();

            switch (resposta) {
                case "1":
                    infoConta(conta);
                    break;
                case "2":
                    depositar(conta);
                    break;
                case "3":
                    sacar(conta);
                    break;
                case "4":
                    if (conta.getStatus()) {
                        bloquearConta(conta);
                    } else {
                        desbloquearConta(conta);
                    }
                    break;
                case "5":
                    excluirConta(conta);
                    return;
                case "6":
                    return;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
                    break;
            }
        }
    }

    public void infoConta(ContaBancaria conta) {
        System.out.println("Número da conta: " + conta.getNumeroDaConta());
        System.out.println("Tipo de conta: " + conta.getTipo());
        System.out.println("Titular: " + conta.getTitular());
        System.out.println("Saldo disponível: " + df.format(conta.getSaldoDisponivel()));
        System.out.println("Status da conta: " + (conta.getStatus() ? "Ativa" : "Bloqueada"));
    }

    public void depositar(ContaBancaria conta) {
        if (conta.getStatus()) {
            System.out.println("Digite o valor do seu depósito:");
            Float deposito = escanear.nextFloat();
            escanear.nextLine(); // Consumir a nova linha remanescente
            if (deposito > 0) {
                conta.setSaldoDisponivel(conta.getSaldoDisponivel() + deposito);
                System.out.println("Depósito realizado com sucesso.");
            } else {
                System.out.println("Insira um valor válido.");
            }
        } else {
            System.out.println("Conta bloqueada. Não é possível realizar depósito.");
        }
    }

    public void sacar(ContaBancaria conta) {
        if (conta.getStatus()) {
            System.out.println("Digite o valor que você deseja sacar:");
            Float saque = escanear.nextFloat();
            escanear.nextLine(); // Consumir a nova linha remanescente
            if (saque > 0 && saque <= conta.getSaldoDisponivel()) {
                conta.setSaldoDisponivel(conta.getSaldoDisponivel() - saque);
                System.out.println("Saque realizado com sucesso.");
                System.out.println("Valor do saque: " + df.format(saque) + " Reais, o seu saldo atual é de " + df.format(conta.getSaldoDisponivel()) + " Reais.");
            } else {
                System.out.println("Valor inválido. Você possui " + df.format(conta.getSaldoDisponivel()) + " Reais.");
            }
        } else {
            System.out.println("Conta bloqueada. Não é possível realizar saque.");
        }
    }

    public void bloquearConta(ContaBancaria conta) {
        if (conta.getStatus()) {
            conta.setStatus(false);
            System.out.println("Conta bloqueada com sucesso.");
        } else {
            System.out.println("Sua conta já está bloqueada.");
        }
    }

    public void desbloquearConta(ContaBancaria conta) {
        if (!conta.getStatus()) {
            conta.setStatus(true);
            System.out.println("Conta desbloqueada com sucesso.");
        } else {
            System.out.println("Sua conta já está aberta.");
        }
    }

    public void excluirConta(ContaBancaria conta) {
        if (!conta.getStatus()) {
            if (conta.getSaldoDisponivel() < 0) {
                System.out.println("Você está em débito no valor de " + df.format(conta.getSaldoDisponivel()) + " Reais. Por favor, pague sua dívida antes de excluir a conta.");
            } else if (conta.getSaldoDisponivel() > 0) {
                System.out.println("Você tem um saldo positivo de " + df.format(conta.getSaldoDisponivel()) + " Reais. Por favor, saque seu saldo antes de excluir a conta.");
            } else {
                listaDeContasBancarias.remove(conta);
                System.out.println("Conta excluída com sucesso.");
            }
        } else {
            System.out.println("Para excluir uma conta, ela precisa estar bloqueada.");
        }
    }

    public void excluirConta() {
        System.out.println("Digite o número da conta a ser excluída:");
        String numeroConta = escanear.nextLine();
        for (ContaBancaria conta : listaDeContasBancarias) {
            if (conta.getNumeroDaConta().equals(numeroConta)) {
                excluirConta(conta);
                return;
            }
        }
        System.out.println("Conta não encontrada.");
    }

    public void listar() {
        System.out.println("Lista de contas bancárias:");
        for (ContaBancaria conta : listaDeContasBancarias) {
            System.out.println("Número: " + conta.getNumeroDaConta() + " | Titular: " + conta.getTitular() + " | Saldo: " + df.format(conta.getSaldoDisponivel()));
        }
    }
}
