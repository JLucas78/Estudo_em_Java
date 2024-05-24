package POO_Guanabara.Aula04_Conta_Bancaria_2_0;

import java.util.ArrayList;

public class ContaBancaria {
    private String numeroDaConta;
    private String tipo;
    private String titular;
    private Float saldoDisponivel;
    private Boolean status;
    private ArrayList<Transacao> historicoTransacoes = new ArrayList<>();

    // Construtor e outros métodos...

    public ContaBancaria(String numeroDaConta, String tipo, String titular, Float saldoDisponivel, Boolean status) {
        this.numeroDaConta = numeroDaConta;
        this.tipo = tipo;
        this.titular = titular;
        this.saldoDisponivel = saldoDisponivel;
        this.status = status;
        this.historicoTransacoes = new ArrayList<>();
    }

    // Getters e Setters...

    public String getNumeroDaConta() {
        return numeroDaConta;
    }

    public String getTipo() {
        return tipo;
    }

    public String getTitular() {
        return titular;
    }

    public Float getSaldoDisponivel() {
        return saldoDisponivel;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setSaldoDisponivel(Float saldoDisponivel) {
        this.saldoDisponivel = saldoDisponivel;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public ArrayList<Transacao> getHistoricoTransacoes() {
        return historicoTransacoes;
    }

    public void adicionarTransacao(Transacao transacao) {
        historicoTransacoes.add(transacao);
    }

    @Override
    public String toString() {
        String statusString = status ? "Ativa" : "Inativa";
        return "Conta Bancaria:" +
                " Numero da conta='" + numeroDaConta + '\'' +
                ", Tipo='" + tipo + '\'' +
                ", Titular='" + titular + '\'' +
                ", Saldo Disponivel=" + saldoDisponivel +
                ", Status=" + statusString;
    }
}
