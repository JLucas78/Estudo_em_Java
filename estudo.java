import java.util.Scanner;

public class estudo {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        
        // Parte 1: Solicitação e leitura da idade
        System.out.println("Digite a idade: ");
        int idade = teclado.nextInt();

        // Parte 2: Solicitação e leitura do peso
        System.out.println("Digite o peso: ");
        double peso = teclado.nextDouble();
        teclado.nextLine(); // Limpa o buffer do teclado antes de ler a próxima linha

        // Parte 3: Solicitação e leitura do nome
        System.out.println("Digite o nome: ");
        String nome = teclado.nextLine();

        // Impressão das informações lidas
        System.out.println("Idade: " + idade);
        System.out.println("Nome: " + nome);
        System.out.printf("Peso: %.1f\n", peso);

        // Fechamento do Scanner para liberar recursos
        teclado.close();
    }
}
