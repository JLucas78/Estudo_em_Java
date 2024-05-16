import java.util.Scanner;

public class imc {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int peso;
        double altura;

        System.out.println("Digite o seu peso (em kg): ");
        peso = teclado.nextInt();

        System.out.println("Agora a sua altura (em metros): ");
        altura = teclado.nextDouble();

        // Convertendo o peso para double para garantir uma divisão de ponto flutuante
        double imc = peso / (altura * altura);

        System.out.printf("IMC: %.2f", imc);

        teclado.close();
    }
}
