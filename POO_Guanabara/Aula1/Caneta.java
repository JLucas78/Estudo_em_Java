package POO_Guanabara.Aula1;

public class Caneta {
    String modelo;
    String cor;
    float ponta;
    int carga;
    boolean tampada;

    void rabiscar() {
        if (this.tampada) {
            System.out.println("Erro: Não posso rabiscar, a caneta está tampada!");
        } else if (this.carga == 0) {
            System.out.println("Erro: Não posso rabiscar, a caneta está sem tinta!");
        } else {
            System.out.println("ESTOU RABISCANDO...");
            this.carga--;
        }
    }

    void tampar() {
        this.tampada = true;
    }

    void destampar() {
        this.tampada = false;
    }

    void status() {
        System.out.println("Uma caneta: " + this.cor);
        System.out.println("Modelo: " + this.modelo);
        System.out.println("Ponta: " + this.ponta);
        System.out.println("Carga: " + this.carga + "%");
        System.out.println("Ela está tampada? " + this.tampada);
    }
}
