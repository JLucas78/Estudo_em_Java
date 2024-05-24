package POO_Guanabara.Aula04_Conta_Bancaria_2_0;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private Scanner escanear = new Scanner(System.in);
    private ArrayList<ContaBancaria> listaDeContasBancarias = new ArrayList<>();
    public ArrayList<Cobranca> cobrancas = new ArrayList<>();
    private DecimalFormat df = new DecimalFormat("0.00");

    public static void main(String[] args) {
        Main main = new Main();
        main.menuPrincipal();
    }

    // Menu principal do sistema bancário
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

    // Método para abrir uma nova conta bancária
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

    // Menu específico para o usuário
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
            System.out.println("7. Ir para o menu de transferências.");
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
                    menuTransferencias(conta);
                    break;
                case "8":
                    return;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
                    break;
            }
        }
    }
    

    // Exibe informações da conta
    public void infoConta(ContaBancaria novaContaBancaria) {
        System.out.println("Número da conta: " + novaContaBancaria.getNumeroDaConta());
        System.out.println("Tipo de conta: " + novaContaBancaria.getTipo());
        System.out.println("Titular: " + novaContaBancaria.getTitular());
        System.out.println("Saldo disponível: " + df.format(novaContaBancaria.getSaldoDisponivel()));
        System.out.println("Status da conta: " + (novaContaBancaria.getStatus() ? "Ativa" : "Bloqueada"));
    }

    // Método para depositar na conta
    public void depositar(ContaBancaria novaContaBancaria) {
        if (novaContaBancaria.getStatus()) {
            System.out.println("Digite o valor do seu depósito:");
            Float deposito = escanear.nextFloat();
            escanear.nextLine(); // Consumir a nova linha remanescente
            if (deposito > 0) {
                novaContaBancaria.setSaldoDisponivel(novaContaBancaria.getSaldoDisponivel() + deposito);
                Transacao novaTransacao = new Transacao("Depósito", deposito, obterDataAtual());
                novaContaBancaria.adicionarTransacao(novaTransacao);
                System.out.println("Depósito realizado com sucesso.");
            } else {
                System.out.println("Insira um valor válido.");
            }
        } else {
            System.out.println("Conta bloqueada. Não é possível realizar depósito.");
        }
    }

    // Método para sacar da conta
    public void sacar(ContaBancaria novaContaBancaria) {
        if (novaContaBancaria.getStatus()) {
            System.out.println("Digite o valor que você deseja sacar:");
            Float saque = escanear.nextFloat();
            escanear.nextLine(); // Consumir a nova linha remanescente
            if (saque > 0 && saque <= novaContaBancaria.getSaldoDisponivel()) {
                novaContaBancaria.setSaldoDisponivel(novaContaBancaria.getSaldoDisponivel() - saque);
                Transacao novaTransacao = new Transacao("Saque", saque, obterDataAtual());
                novaContaBancaria.adicionarTransacao(novaTransacao);
                System.out.println("Saque realizado com sucesso.");
                System.out.println("Valor do saque: " + df.format(saque) + " Reais, o seu saldo atual é de " + df.format(novaContaBancaria.getSaldoDisponivel()) + " Reais.");
            } else {
                System.out.println("Valor inválido. Você possui " + df.format(novaContaBancaria.getSaldoDisponivel()) + " Reais.");
            }
        } else {
            System.out.println("Conta bloqueada. Não é possível realizar saque.");
        }
    }

    // Método para bloquear a conta
    public void bloquearConta(ContaBancaria novaContaBancaria) {
        novaContaBancaria.setStatus(false);
        System.out.println("Conta bloqueada com sucesso.");
    }

    // Método para desbloquear a conta
    public void desbloquearConta(ContaBancaria novaContaBancaria) {
        novaContaBancaria.setStatus(true);
        System.out.println("Conta desbloqueada com sucesso.");
    }

    // Método para excluir a conta atual
    public void excluirContaAtual(ContaBancaria contaBancaria) {
        // Verificar se a conta está ativa
        if (contaBancaria.getStatus()) {
            System.out.println("Não é possível excluir a sua conta, pois a mesma está ativa.");
            return;
        }
    
        // Verificar se o saldo disponível é zero
        if (contaBancaria.getSaldoDisponivel() != 0) {
            System.out.println("Seu saldo deve estar zerado para a exclusão da conta.");
            return;
        }
    
        // Verificar se há cobranças pendentes
        for (Cobranca cobranca : cobrancas) {
            if (cobranca.getContaReceptora().equals(contaBancaria.getNumeroDaConta())) {
                System.out.println("Não é possível excluir a conta, pois há cobranças pendentes.");
                return;
            }
        }
    
        // Se todas as condições forem atendidas, a conta pode ser excluída
        listaDeContasBancarias.remove(contaBancaria);
        System.out.println("Conta excluída com sucesso.");
    }
    
      


    // Método para visualizar o histórico de transações da conta
    public void verHistoricoTransacoes(ContaBancaria novaContaBancaria) {
        System.out.println("Histórico de transações:");
        if (novaContaBancaria.getHistoricoTransacoes().isEmpty()) {
            System.out.println("Nenhuma transação realizada.");
        } else {
            for (Transacao transacao : novaContaBancaria.getHistoricoTransacoes()) {
                System.out.println(transacao);
            }
        }
    }

    // Método para acessar uma conta existente
    public void acessarConta() {
        System.out.println("Digite o número da conta que você deseja acessar:");
        System.out.println(listaDeContasBancarias);
        String ContaComAcessoAtual = escanear.nextLine();
        for (ContaBancaria conta : listaDeContasBancarias) {
            if (conta.getNumeroDaConta().equals(ContaComAcessoAtual)) {
                menudoUsuario(conta);
                return;
            }
        }
        System.out.println("Conta não encontrada.");
    }




    // Lista todas as contas bancárias
    public void listar() {
        if (listaDeContasBancarias.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada.");
        } else {
            for (ContaBancaria conta : listaDeContasBancarias) {
                System.out.println(conta);
            }
        }
    }

    // Excluir uma conta específica
    public void excluirContaEspecifica() {
        System.out.println("Digite o número da conta que você deseja excluir:");
        String numeroConta = escanear.nextLine();
    
        ContaBancaria contaParaExcluir = null;
        for (ContaBancaria conta : listaDeContasBancarias) {
            if (conta.getNumeroDaConta().equals(numeroConta)) {
                contaParaExcluir = conta;
                break;
            }
        }
    
        if (contaParaExcluir == null) {
            System.out.println("Conta não encontrada.");
            return;
        }
    
        // Verificar se a conta está ativa
        if (contaParaExcluir.getStatus()) {
            System.out.println("Não é possível excluir a conta, pois a mesma está ativa.");
            return;
        }
    
        // Verificar se o saldo disponível é zero
        if (contaParaExcluir.getSaldoDisponivel() != 0) {
            System.out.println("O saldo da conta deve estar zerado para a exclusão.");
            return;
        }
    
        // Verificar se há cobranças pendentes
        for (Cobranca cobranca : cobrancas) {
            if (cobranca.getContaReceptora().equals(contaParaExcluir.getNumeroDaConta())) {
                System.out.println("Não é possível excluir a conta, pois há cobranças pendentes.");
                return;
            }
        }
    
        // Se todas as condições forem atendidas, a conta pode ser excluída
        listaDeContasBancarias.remove(contaParaExcluir);
        System.out.println("Conta excluída com sucesso.");
    }
    


    public void menuTransferencias(ContaBancaria contaEmissora) {
        while (true) {
            System.out.println("Menu de Transferências");
            System.out.println("1. Enviar dinheiro.");
            System.out.println("2. Fazer cobrança.");
            System.out.println("3. Pagar cobrança.");
            System.out.println("4. Voltar ao menu do usuário.");
    
            String resposta = escanear.nextLine();
    
            switch (resposta) {
                case "1":
                    enviarDinheiro(contaEmissora);
                    break;
                case "2":
                    fazerCobranca(contaEmissora);
                    break;
                case "3":
                    pagarCobranca(contaEmissora);
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
                    break;
            }
        }
    }


    public void enviarDinheiro(ContaBancaria contaEmissora) {
        System.out.println("Digite o número da conta para a qual deseja enviar dinheiro:");
        String numeroContaReceptora = escanear.nextLine();
    
        ContaBancaria contaReceptora = null;
        for (ContaBancaria conta : listaDeContasBancarias) {
            if (conta.getNumeroDaConta().equals(numeroContaReceptora)) {
                contaReceptora = conta;
                break;
            }
        }
    
        if (contaReceptora == null) {
            System.out.println("Conta receptora não encontrada.");
            return;
        }
    
        if(contaEmissora.getStatus()){
            if(contaReceptora.getStatus()){
                System.out.println("Digite o valor que deseja transferir:");
                Float valorDoEnvio = escanear.nextFloat();
                escanear.nextLine(); // Consumir a nova linha remanescente
    
                if (valorDoEnvio > 0 && valorDoEnvio <= contaEmissora.getSaldoDisponivel()) {
                    contaEmissora.setSaldoDisponivel(contaEmissora.getSaldoDisponivel() - valorDoEnvio);
                    contaReceptora.setSaldoDisponivel(contaReceptora.getSaldoDisponivel() + valorDoEnvio);
    
                    Transacao transacaoEmissora = new Transacao("Transferência Enviada para " + contaReceptora, -valorDoEnvio, obterDataAtual());
                    Transacao transacaoReceptora = new Transacao("Transferência Recebida. Origem: " + contaEmissora, valorDoEnvio, obterDataAtual());
    
                    contaEmissora.adicionarTransacao(transacaoEmissora);
                    contaReceptora.adicionarTransacao(transacaoReceptora);
    
                    System.out.println("Transferência realizada com sucesso.");
                } else {
                    System.out.println("Valor inválido. Você possui " + df.format(contaEmissora.getSaldoDisponivel()) + " Reais.");
                }
            } else {
                System.out.println("Desculpe, a conta referente ao usuario " + contaReceptora.getTitular() + " está bloqueada. Volte novamente mais tarde.");
                return;
            }
        } else {
            System.out.println("A sua conta está bloqueada, vá ao menu do usuario e desbloqueei ela.");
            return;
        }
    }
    


    public void fazerCobranca(ContaBancaria contaEmissora) {
        System.out.println("Digite o número da conta que deseja cobrar:");
        String numeroContaCobrada = escanear.nextLine();
    
        ContaBancaria contaCobrada = null;
        for (ContaBancaria conta : listaDeContasBancarias) {
            if (conta.getNumeroDaConta().equals(numeroContaCobrada)) {
                contaCobrada = conta;
                break;
            }
        }
    
        if (contaCobrada == null) {
            System.out.println("Conta não encontrada.");
            return;
        }
    
        System.out.println("Digite o valor da cobrança:");
        Float valor = escanear.nextFloat();
        escanear.nextLine(); // Consumir a nova linha remanescente
    
        if (valor > 0) {
            Cobranca novaCobranca = new Cobranca(contaEmissora.getTitular(), contaEmissora.getNumeroDaConta(), contaCobrada.getNumeroDaConta(), valor, obterDataAtual());
            cobrancas.add(novaCobranca);
            System.out.println("Cobrança realizada com sucesso.");
        } else {
            System.out.println("Valor inválido.");
        }
    }

    public void pagarCobranca(ContaBancaria contaEmissora) {
    List<Cobranca> cobrancasPendentes = new ArrayList<>();
    for (Cobranca cobranca : cobrancas) {
        if (cobranca.getContaReceptora().equals(contaEmissora.getNumeroDaConta())) {
            cobrancasPendentes.add(cobranca);
        }
    }

    if (cobrancasPendentes.isEmpty()) {
        System.out.println("Nenhuma cobrança pendente encontrada.");
        return;
    }

    System.out.println("Cobranças pendentes:");
    for (int i = 0; i < cobrancasPendentes.size(); i++) {
        Cobranca cobranca = cobrancasPendentes.get(i);
        System.out.println((i + 1) + ". " + cobranca);
    }

    System.out.println("Selecione o número da cobrança que deseja pagar:");
    int opcao = escanear.nextInt();
    escanear.nextLine(); // Consumir a nova linha remanescente

    if (opcao > 0 && opcao <= cobrancasPendentes.size()) {
        Cobranca cobrancaSelecionada = cobrancasPendentes.get(opcao - 1);

        if (cobrancaSelecionada.getValor() <= contaEmissora.getSaldoDisponivel()) {
            ContaBancaria contaReceptora = null;
            for (ContaBancaria conta : listaDeContasBancarias) {
                if (conta.getNumeroDaConta().equals(cobrancaSelecionada.getNumeroContaEmissora())) {
                    contaReceptora = conta;
                    break;
                }
            }

            if (contaReceptora != null) {
                contaEmissora.setSaldoDisponivel(contaEmissora.getSaldoDisponivel() - cobrancaSelecionada.getValor());
                contaReceptora.setSaldoDisponivel(contaReceptora.getSaldoDisponivel() + cobrancaSelecionada.getValor());

                Transacao transacaoEmissora = new Transacao("Pagamento de Cobrança para " + contaReceptora.getTitular(), -cobrancaSelecionada.getValor(), obterDataAtual());
                Transacao transacaoReceptora = new Transacao("Recebimento de Cobrança de " + contaEmissora.getTitular(), cobrancaSelecionada.getValor(), obterDataAtual());

                contaEmissora.adicionarTransacao(transacaoEmissora);
                contaReceptora.adicionarTransacao(transacaoReceptora);

                cobrancas.remove(cobrancaSelecionada);

                System.out.println("Cobrança paga com sucesso.");
            } else {
                System.out.println("Conta receptora não encontrada.");
            }
        } else {
            System.out.println("Saldo insuficiente para pagar a cobrança.");
        }
    } else {
        System.out.println("Opção inválida.");
    }
}



    // Obtém a data atual no formato especificado
    private String obterDataAtual() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(new Date());
    } }

