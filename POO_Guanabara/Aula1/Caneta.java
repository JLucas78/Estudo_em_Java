package POO_Guanabara.Aula1;

public class Caneta {
    public String modelo;
    public String cor;
    public float ponta;
    public int carga;
    public boolean tampada;

    public void rabiscar() {
        if (this.tampada) {
            System.out.println("Erro: Não posso rabiscar, a caneta está tampada!");
        } else if (this.carga == 0) {
            System.out.println("Erro: Não posso rabiscar, a caneta está sem tinta!");
        } else {
            System.out.println("ESTOU RABISCANDO...");
            this.carga--;
        }
    }

    public void tampar() {
        this.tampada = true;
    }

    public void destampar() {
        this.tampada = false;
    }

    public void status() {
        System.out.println("*******************************");
        System.out.println("Uma caneta: " + this.cor);
        System.out.println("Modelo: " + this.modelo);
        System.out.println("Ponta: " + this.ponta);
        System.out.println("Carga: " + this.carga + "%");
        if (this.tampada) {
            System.out.println("Ela está tampada.");
        } else {
            System.out.println("Ela não está tampada.");
        }
        System.out.println("*******************************");
    }
}
