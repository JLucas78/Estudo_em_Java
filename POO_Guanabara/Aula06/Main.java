package POO_Guanabara.Aula06;

public class Main {
    public static void main(String[] args) {
        Lutador lutador[] = new Lutador[6];

        lutador[0] = new Lutador("Lucas", "Brasil", 23, 63f, 1.72f, 12, 0, 0);
        lutador[1] = new Lutador("Douglas", "Brasil", 18, 61f, 1.73f, 11, 0, 2);
        lutador[2] = new Lutador("Fabian", "Uruguai", 27, 67f, 1.78f, 17, 2, 1);
        lutador[3] = new Lutador("Roger", "Espanha", 24, 89f, 1.74f, 14, 2, 6);
        lutador[4] = new Lutador("Rico", "Mexico", 34, 76f, 1.85f, 13, 3, 1);
        lutador[5] = new Lutador("Jon", "EUA", 25, 103f, 1.87f, 13, 0, 5);

        Combate UEC01 = new Combate();
        UEC01.marcarLuta(lutador[4], lutador[1]);
        UEC01.lutar();
    }
}
