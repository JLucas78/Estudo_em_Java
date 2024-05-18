package POO_Guanabara.Aula1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Inicializando a primeira caneta
        Caneta caneta = new Caneta();
        caneta.modelo = "BIC Cristal";
        caneta.cor = "Azul";
        caneta.ponta = 0.5f;
        caneta.carga = 10; // Definindo uma carga inicial menor para facilitar o teste
        caneta.tampada = false;

        // Loop para simular o uso da primeira caneta
        boolean continuarRabiscando = true;

        System.out.println("==== INÍCIO DA PRIMEIRA CANETA ====");

        while (continuarRabiscando) {
            caneta.status();
            System.out.print("Deseja rabiscar? (s/n): ");
            String resposta = scanner.nextLine();

            if (resposta.equalsIgnoreCase("s")) {
                caneta.rabiscar();
            } else if (resposta.equalsIgnoreCase("n")) {
                continuarRabiscando = false;
            } else {
                System.out.println("Entrada inválida. Por favor, responda com 's' ou 'n'.");
            }

            if (caneta.carga == 0) {
                System.out.println("A caneta está sem carga e não pode mais rabiscar.");
                continuarRabiscando = false;
            }
        }

        System.out.println("==== FIM DA PRIMEIRA CANETA ====");

        // Fechando o scanner
        scanner.close();
    }
}
