package POO_Guanabara.Aula04_Conta_Bancaria_2_0;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
            System.out.println("4. Acessar conta existente.");
            System.out.println("5. Sair.");

            String resposta = escanear.nextLine();

            switch (resposta) {
                case "1":
                    abrirNovaConta();
                    break;
                case "2":
                    listar();
                    break;
                case "3":
                    excluirContaEspecifica();
                    break;
                case "4":
                    acessarConta();
                    break;
                case "5":
                    System.out.println("Encerrando o programa...");
                    return;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
                    break;
            }
        }
    }

    public void abrirNovaConta() {
        Random valorAleatorio = new Random();
        String numeroDaConta = String.format("%06d", valorAleatorio.nextInt(1000000));
        Float saldoInicial = valorAleatorio.nextFloat() * 10000;
        
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
            System.out.println("6. Ver histórico de transações.");
            System.out.println("7. Ir para o menu de transferencias.");
            System.out.println("8. Voltar ao menu principal.");

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
                    excluirContaAtual(conta);
                    return;
                case "6":
                    verHistoricoTransacoes(conta);
                    break;
                case "7":
                    menuDeTransferencia(conta);
                    break;
                case "8":
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
                Transacao transacao = new Transacao("Depósito", deposito, obterDataAtual());
                conta.adicionarTransacao(transacao);
                System.out.println("Depósito realizado com sucesso.");
            } else {
                System.out.println("Insira um valor válido.");
            }
        } else {
            System.out.println("Conta bloqueada. Não é possível realizar depósito.");
            menudoUsuario(conta);
        }
    }

    public void sacar(ContaBancaria conta) {
        if (conta.getStatus()) {
            System.out.println("Digite o valor que você deseja sacar:");
            Float saque = escanear.nextFloat();
            escanear.nextLine(); // Consumir a nova linha remanescente
            if (saque > 0 && saque <= conta.getSaldoDisponivel()) {
                conta.setSaldoDisponivel(conta.getSaldoDisponivel() - saque);
                Transacao transacao = new Transacao("Saque", saque, obterDataAtual());
                conta.adicionarTransacao(transacao);
                System.out.println("Saque realizado com sucesso.");
                System.out.println("Valor do saque: " + df.format(saque) + " Reais, o seu saldo atual é de " + df.format(conta.getSaldoDisponivel()) + " Reais.");
            } else {
                System.out.println("Valor inválido. Você possui " + df.format(conta.getSaldoDisponivel()) + " Reais.");
            }
        } else {
            System.out.println("Conta bloqueada. Não é possível realizar saque.");
        }
    }


    public void menuDeTransferencia(ContaBancaria contaEmissora) {
                System.out.println("1. Enviar dinheiro.");
                System.out.println("2. Fazer uma cobrança.");
                System.out.println("3. Visualizar cobranças a pagar.");
                System.out.println("4. Voltar para o menu de usuário.");
        
                String resposta = escanear.nextLine();
        
                switch (resposta) {
                    case "1":
                        enviarDinheiro(contaEmissora);
                        break;
        
                    case "2":
                        fazerCobranca(contaEmissora);
                        break;
        
                    case "3":
                        pagarCobrancas();
                        break;
        
                    case "4":
                        return;
        
                    default:
                        System.out.println("Opção inválida.");
                }
        }
        
        public void enviarDinheiro(ContaBancaria contaEmissora) {
                System.out.println("Selecione para qual conta você deseja enviar dinheiro:");
        
                for (ContaBancaria conta : listaDeContasBancarias) {
                    System.out.println(conta.getTitular());
                }
        
                String titularContaReceptora = escanear.nextLine();
        
                System.out.println("Agora selecione o valor que você deseja enviar.");
                Float valorEnvio = escanear.nextFloat();
        
                escanear.nextLine(); // Consumir a nova linha remanescente
        
                for (ContaBancaria conta : listaDeContasBancarias) {
                    if (conta.getTitular().equals(titularContaReceptora)) {
                        if (conta.getStatus()) {
                            if (valorEnvio > 0 && valorEnvio <= contaEmissora.getSaldoDisponivel()) {
                                conta.setSaldoDisponivel(conta.getSaldoDisponivel() + valorEnvio);
                                contaEmissora.setSaldoDisponivel(contaEmissora.getSaldoDisponivel() - valorEnvio);
        
                                Transacao transacao = new Transacao("Envio", valorEnvio, obterDataAtual());
                                conta.adicionarTransacao(transacao);
                                System.out.println("Envio Realizado com sucesso.");
                                return;
                            } else {
                                System.out.println("Valor inválido ou saldo insuficiente.");
                                return;
                            }
                        } else {
                            System.out.println("A conta do(a) " + conta.getTitular() + " atualmente está bloqueada, tente novamente mais tarde.");
                            menudoUsuario(contaEmissora);
                            return;
                        }
                    }
                }
                System.out.println("Conta não encontrada.");
        }
        
        public void fazerCobranca(ContaBancaria contaEmissora) {
                System.out.println("Selecione para qual conta você deseja fazer uma cobrança:");
        
                for (ContaBancaria conta : listaDeContasBancarias) {
                    System.out.println(conta.getTitular());
                }
        
                String titularContaReceptora = escanear.nextLine();
        
                System.out.println("Agora selecione o valor que você deseja cobrar.");
                Float valorDaCobranca = escanear.nextFloat();
        
                escanear.nextLine(); // Consumir a nova linha remanescente
        
                for (ContaBancaria conta : listaDeContasBancarias) {
                    if (conta.getTitular().equals(titularContaReceptora)) {
                        if (conta.getStatus()) {
                            if (valorDaCobranca > 0) {
                                // Adicionar a lógica para criar e armazenar uma cobrança
                                System.out.println("Cobrança realizada com sucesso.");
                                return;
                            } else {
                                System.out.println("Valor inválido.");
                                return;
                            }
                        } else {
                            System.out.println("A conta do(a) " + conta.getTitular() + " atualmente está bloqueada, tente novamente mais tarde.");
                            menudoUsuario(contaEmissora);
                            return;
                        }
                    }
                }
                System.out.println("Conta não encontrada.");
        }
        
















    public void bloquearConta(ContaBancaria conta) {
        conta.setStatus(false);
        System.out.println("Conta bloqueada com sucesso.");
    }

    public void desbloquearConta(ContaBancaria conta) {
        conta.setStatus(true);
        System.out.println("Conta desbloqueada com sucesso.");
    }

    public void excluirContaAtual(ContaBancaria conta) {
        listaDeContasBancarias.remove(conta);
        System.out.println("Conta excluída com sucesso.");
    }

    public void verHistoricoTransacoes(ContaBancaria conta) {
        System.out.println("Histórico de transações:");
        if (conta.getHistoricoTransacoes().isEmpty()) {
            System.out.println("Nenhuma transação realizada.");
        } else {
            for (Transacao transacao : conta.getHistoricoTransacoes()) {
                System.out.println(transacao);
            }
        }
    }

    public void acessarConta() {
        System.out.println("Digite o número da conta que você deseja acessar:");
        System.out.println(listaDeContasBancarias);
        String numeroConta = escanear.nextLine();
        for (ContaBancaria conta : listaDeContasBancarias) {
            if (conta.getNumeroDaConta().equals(numeroConta)) {
                String contaEmissora = ContaBancaria.novaContaBancaria();
                menudoUsuario(conta);
                return;
            }
        }
        System.out.println("Conta não encontrada.");
    }

    public void listar() {
        for (ContaBancaria conta : listaDeContasBancarias) {
            System.out.println(conta);
        }
    }

    public void excluirContaEspecifica() {
        System.out.println("Digite o número da conta que você deseja excluir:");
        String numeroConta = escanear.nextLine();
        for (ContaBancaria conta : listaDeContasBancarias) {
            if (conta.getNumeroDaConta().equals(numeroConta)) {
                listaDeContasBancarias.remove(conta);
                System.out.println("Conta excluída com sucesso.");
                return;
            }
        }
        System.out.println("Conta não encontrada.");
    }

    private String obterDataAtual() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(new Date());
    }
}
