package Uninter;

import java.util.Scanner;
import java.util.ArrayList;

public class Cofre {
    private static Scanner escanear = new Scanner(System.in);
    private static ArrayList<Moeda> moedas = new ArrayList<>();

    public static void main(String[] args) {
        menuPrincipal();
    }

    public static void menuPrincipal() {
        System.out.println("1. Adicionar moeda.");
        System.out.println("2. Remover moeda.");
        System.out.println("3. Listar moedas adicionadas.");
        System.out.println("4. Calcular montante total convertido para o real.");
        System.out.println("5. Fechar programa.");

        String resposta = escanear.nextLine();

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

    public static void selecionarTipo() {
        System.out.println("Selecione qual moeda você deseja adicionar:");
        System.out.println("1. Real");
        System.out.println("2. Dolar");
        System.out.println("3. Euro");
        System.out.println("4. Voltar ao menu.");

        String resposta = escanear.nextLine();

        if (resposta.equals("1")) {
            adicionarMoeda("Real");
        } else if(resposta.equals("2")) {
            adicionarMoeda("Dolar");
        } else if(resposta.equals("3")) {
            adicionarMoeda("Euro");
        } else if(resposta.equals("4")) {
            menuPrincipal();
        } else {
            System.out.println("Opção inválida.");
            selecionarTipo();
        }
    }

    public static void adicionarMoeda(String tipo) {
        System.out.println("Qual valor você deseja adicionar?");
        float valor = escanear.nextFloat();
        escanear.nextLine(); // Limpar o buffer do scanner
        Moeda moeda = new Moeda(tipo, valor);
        moedas.add(moeda);
        System.out.println("Moeda adicionada com sucesso.");
        menuPrincipal();
    }

    public static void removerMoeda() {
        if (moedas.isEmpty()) {
            System.out.println("Não há moedas para remover.");
            menuPrincipal();
            return;
        }
        
        System.out.println("Selecione o número da moeda que deseja remover:");
        listarMoedas();
        int opcao = escanear.nextInt();
        escanear.nextLine(); // Limpar o buffer do scanner
    
        if (opcao >= 0 && opcao < moedas.size()) {
            Moeda moedaRemovida = moedas.remove(opcao);
            System.out.println("Moeda " + moedaRemovida + " removida com sucesso.");
        } else {
            System.out.println("Opção inválida.");
        }
    }

    public static void listarMoedas() {
        System.out.println("Moedas adicionadas:");
        for (int i = 0; i < moedas.size(); i++) {
            System.out.println(i + ". " + moedas.get(i));
        }
        menuPrincipal();
    }

    public static void calcularMontante() {
        float totalReal = 0;
        for (Moeda moeda : moedas) {
            // Aqui você precisaria de uma lógica para converter o valor da moeda para Real
            // Vou assumir que a conversão é 1 para 1 para simplificar
            totalReal += moeda.getValor();
        }
        System.out.println("Montante total em Reais: " + totalReal);
        menuPrincipal();
    }
}
