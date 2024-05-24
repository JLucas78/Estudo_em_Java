package POO_Guanabara.Aula04_Conta_Bancaria_2_0;

public class Cobranca {
    private String nomeContaEmissora;
    private String numeroContaEmissora;
    private String contaReceptora;
    private Float valor;
    private String data;

    public Cobranca(String nomeContaEmissora, String numeroContaEmissora, String contaReceptora, Float valor, String data) {
        this.nomeContaEmissora = nomeContaEmissora;
        this.numeroContaEmissora = numeroContaEmissora;
        this.contaReceptora = contaReceptora;
        this.valor = valor;
        this.data = data;
    }

    public String getNomeContaEmissora() {
        return nomeContaEmissora;
    }

    public String getNumeroContaEmissora() {
        return numeroContaEmissora;
    }

    public String getContaReceptora() {
        return contaReceptora;
    }

    public Float getValor() {
        return valor;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Cobranca: " +
                "Titular da conta emissora = " + nomeContaEmissora + '\'' +
                "Numero da conta emissora = " + numeroContaEmissora + '\'' +
                ", contaReceptora='" + contaReceptora + '\'' +
                ", valor=" + valor +
                ", data='" + data;
    }
}
