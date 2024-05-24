package POO_Guanabara.Aula04_Conta_Bancaria_2_0;

import java.util.ArrayList;

public class ContaBancaria {
    private String numeroDaConta;
    private String tipo;
    private String titular;
    private Float saldoDisponivel;
    private Boolean status;
    private ArrayList<Transacao> historicoTransacoes;

    public ContaBancaria(String numeroDaConta, String tipo, String titular, Float saldoDisponivel, Boolean status) {
        this.numeroDaConta = numeroDaConta;
        this.tipo = tipo;
        this.titular = titular;
        this.saldoDisponivel = saldoDisponivel;
        this.status = status;
        this.historicoTransacoes = new ArrayList<>();
    }

    // Getters e Setters...

    public void setNumeroDaConta(String numeroDaConta) {
        this.numeroDaConta = numeroDaConta;
    }

    public String getNumeroDaConta() {
        return numeroDaConta;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getTitular() {
        return titular;
    }

    public void setSaldoDisponivel(Float saldoDisponivel) {
        this.saldoDisponivel = saldoDisponivel;
    }

    public Float getSaldoDisponivel() {
        return saldoDisponivel;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }

    public void adicionarTransacao(Transacao transacao) {
        historicoTransacoes.add(transacao);
    }

    public ArrayList<Transacao> getHistoricoTransacoes() {
        return historicoTransacoes;
    }
}
