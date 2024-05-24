package POO_Guanabara.Aula04_Conta_Bancaria_2_0;

public class Transacao {
    private String tipo;
    private Float valor;
    private String data;

    public Transacao(String tipo, Float valor, String data) {
        this.tipo = tipo;
        this.valor = valor;
        this.data = data;
    }

    public String getTipo() {
        return tipo;
    }

    public Float getValor() {
        return valor;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Tipo: " + tipo + ", Valor: " + valor + ", Data: " + data;
    }
}
