package Uninter;

import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Cofre {
    // Scanner para entrada de dados do usuário
    private static Scanner scanner = new Scanner(System.in);
    // Lista para armazenar as moedas
    private static ArrayList<Moeda> moedas = new ArrayList<>();

    public static void main(String[] args) {
        menuPrincipal();
    }

    // Função para exibir o menu principal
    public static void menuPrincipal() {
        System.out.println("1. Adicionar moeda.");
        System.out.println("2. Remover moeda.");
        System.out.println("3. Listar moedas adicionadas.");
        System.out.println("4. Calcular montante total convertido para o real.");
        System.out.println("5. Fechar programa.");

        String resposta = scanner.nextLine();

        if (resposta.equals("1")) {
            selecionarTipo();
        } else if(resposta.equals("2")) {
            removerMoeda();
        } else if(resposta.equals("3")) {
            listarMoedas();
        } else if(resposta.equals("4")) {
            calcularMontante();
        } else if(resposta.equals("5")) {
            System.out.println("Programa encerrado.");
            System.exit(0);
        } else {
            System.out.println("Opção inválida.");
        }
    }

    // Função para selecionar o tipo de moeda a ser adicionada
    public static void selecionarTipo() {
        System.out.println("Selecione qual moeda você deseja adicionar:");
        System.out.println("1. Real");
        System.out.println("2. Dolar");
        System.out.println("3. Euro");
        System.out.println("4. Voltar ao menu.");

        String resposta = scanner.nextLine();

        if (resposta.equals("1")) {
            adicionarValor("Real");
        } else if(resposta.equals("2")) {
            adicionarValor("Dolar");
        } else if(resposta.equals("3")) {
            adicionarValor("Euro");
        } else if(resposta.equals("4")) {
            menuPrincipal();
        } else {
            System.out.println("Opção inválida.");
            selecionarTipo();
        }
    }

    // Função para adicionar o valor da moeda especificada
    public static void adicionarValor(String tipo) {
        System.out.println("Qual valor você deseja adicionar?");
        float valor = scanner.nextFloat();
        scanner.nextLine(); // Limpar o buffer do scanner
        Moeda moeda = new Moeda(tipo, valor);
        moedas.add(moeda);
        System.out.println("Moeda adicionada com sucesso.");
        System.out.println("----------------------------------");
        menuPrincipal();
    }

    // Função para remover uma moeda da lista
    public static void removerMoeda() {
        if (moedas.isEmpty()) {
            System.out.println("Não há moedas para remover.");
            menuPrincipal();
            return;
        }
        
        System.out.println("Selecione o número da moeda que deseja remover:");
        listarMoedas();
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner
    
        if (opcao >= 1 && opcao <= moedas.size()) {
            Moeda moedaRemovida = moedas.remove(opcao - 1);
            System.out.println("Moeda " + moedaRemovida + " removida com sucesso.");
        } else {
            System.out.println("Opção inválida.");
        }
        System.out.println("----------------------------------");
        menuPrincipal();
    }

    // Função para listar as moedas adicionadas
    public static void listarMoedas() {
        System.out.println("Moedas adicionadas:");
        for (int i = 0; i < moedas.size(); i++) {
            System.out.println((i+1) + ". " + moedas.get(i));
        }
    }

    // Função para calcular o montante total em Reais
    public static void calcularMontante() {
        // Defina as taxas de conversão fixas
        float taxaEuro = 5.55f;
        float taxaDolar = 5.10f;

        // Inicialize o total em Reais
        float totalReal = 0;

        // Percorra todas as moedas na lista
        for (Moeda moeda : moedas) {
            // Verifique o tipo de moeda e aplique a taxa de conversão apropriada
            if (moeda.getTipo().equals("Euro")) {
                totalReal += moeda.getValor() * taxaEuro;
            } else if (moeda.getTipo().equals("Dolar")) {
                totalReal += moeda.getValor() * taxaDolar;
            } else if (moeda.getTipo().equals("Real")) {
                totalReal += moeda.getValor();
            }
        }

        // Formate o montante total em Reais com duas casas decimais e no formato desejado
        int valorInteiro = (int) totalReal;
        int valorDecimal = (int) ((totalReal - valorInteiro) * 100);
        
        // Exiba o montante total no formato desejado
        System.out.println("Montante total em Reais: " + valorInteiro + " reais e " + valorDecimal + " centavos.");

        // Retorne ao menu principal
        System.out.println("----------------------------------");
        menuPrincipal();
    }
}
