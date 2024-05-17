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

        // Separador visual entre canetas
        System.out.println("\n==================================\n");

        // Inicializando a segunda caneta
        Caneta caneta2 = new Caneta();
        caneta2.modelo = "BIC Lenuvo";
        caneta2.cor = "Preta";
        caneta2.ponta = 0.7f;
        caneta2.carga = 15;
        caneta2.tampada = true;

        // Exibindo o status da segunda caneta
        System.out.println("==== INÍCIO DA SEGUNDA CANETA ====");
        caneta2.status();

        // Simulando o uso da segunda caneta
        caneta2.destampar();
        caneta2.rabiscar();
        System.out.println("==== FIM DA SEGUNDA CANETA ====");
    }
}
