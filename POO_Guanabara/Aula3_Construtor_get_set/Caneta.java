package POO_Guanabara.Aula3_Construtor_get_set;

public class Caneta {
    private String modelo;
    private String cor;
    private Float ponta;

    public Caneta(String modelo, String cor, Float ponta) {
        this.setModelo(modelo);
        this.setPonta(ponta);
        this.setCor(cor);
        
    }

    public void setCor(String cor) {
        this.cor = getCor();
    }

    public String getCor() {
        return cor;
    }

    public void setModelo(String modelo) {
        this.modelo = getModelo();
    }

    public String getModelo() {
        return modelo;
    }

    public void setPonta(Float ponta) {
        this.ponta = getPonta();
    }

    public Float getPonta() {
        return ponta;
    }
}
