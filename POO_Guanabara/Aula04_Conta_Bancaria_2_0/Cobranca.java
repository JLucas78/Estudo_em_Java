package POO_Guanabara.Aula04_Conta_Bancaria_2_0;

public class Cobranca {
    String contaEmissora;
    Float valorCobranca;
    String data;

    public Cobranca(String contaEmissora, Float valorCobranca, String data) {
        this.contaEmissora = contaEmissora;
        this.valorCobranca = valorCobranca;
        this.data = data;
    }


    public void setContaEmissora(String contaEmissora) {
        this.contaEmissora = contaEmissora;
    }


    public String getContaEmissora() {
        return contaEmissora;
    }


    public void setValorCobranca(Float valorCobranca) {
        this.valorCobranca = valorCobranca;
    }


    public Float getValorCobranca() {
        return valorCobranca;
    }


    public void setData(String data) {
        this.data = data;
    }


    public String getData() {
        return data;
    }

    

    
}
