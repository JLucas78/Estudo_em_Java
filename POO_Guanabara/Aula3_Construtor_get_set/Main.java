package POO_Guanabara.Aula3_Construtor_get_set;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private Scanner escanear = new Scanner(System.in);
    private ArrayList<Caneta> colecaoCanetas = new ArrayList<>(); 

    public static void main(String[] args) {
        Main main = new Main();
        main.menu();
    }

    public void menu() {
        while (true) {
            System.out.println("1. Adicionar nova caneta.");
            System.out.println("2. Listar todas as canetas disponiveis.");
            System.out.println("3. Sair.");

            String resposta = escanear.nextLine();

            switch (resposta) {
                case "1":
                    adicionarNovaCaneta();
                    break;
                case "2":
                    listarCanetas();
                    break;
                case "3":
                    System.out.println("Encerrando o programa...");
                    return;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
                    break;
            }
        }
    }

    public void adicionarNovaCaneta() {
        System.out.println("Qual o modelo da caneta?");
        String modelo = escanear.nextLine();
        System.out.println("A cor dela?");
        String cor = escanear.nextLine();
        System.out.println("E a espessura da sua ponta?");
        float ponta = escanear.nextFloat();
        escanear.nextLine(); // Consumir a nova linha remanescente

        Caneta novaCaneta = new Caneta(modelo, cor, ponta);
        colecaoCanetas.add(novaCaneta);
        System.out.println("Caneta adicionada com sucesso!");
    }

    public void listarCanetas() {
        // Método para listar canetas (deixado vazio conforme solicitado)
    }
}
