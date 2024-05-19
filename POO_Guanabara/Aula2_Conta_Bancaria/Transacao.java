package POO_Guanabara.Aula2_Conta_Bancaria;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transacao {
    String tipo;
    float valor;
    String data;

    Transacao(String tipo, float valor) {
        this.tipo = tipo;
        this.valor = valor;
        this.data = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
    }

    public String getTipo() {
        return tipo;
    }

    public float getValor() {
        return valor;
    }

    public String getData() {
        return data;
    }

    public String detalhes() {
        DecimalFormat formatoValor = new DecimalFormat("#,##0.00");
        return data + " - " + tipo + ": R$" + formatoValor.format(valor);
    }
}
