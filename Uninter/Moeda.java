package Uninter;

// Declaração da classe Moeda
public class Moeda {
    // Declaração dos atributos privados tipo e valor
    private String tipo; 
    private float valor; 

    // Construtor da classe Moeda
    // Recebe como parâmetros o tipo e o valor da moeda e os atribui aos atributos correspondentes
    public Moeda(String tipo, float valor) {
        this.tipo = tipo; 
        this.valor = valor; 
    }

    // Método setter para o atributo tipo
    public void setTipo(String tipo) {
        this.tipo = tipo; // Define o tipo da moeda com o valor recebido como parâmetro
    }

    // Método getter para o atributo tipo
    // Retorna o tipo da moeda
    public String getTipo() {
        return tipo;
    }

    // Método setter para o atributo valor
    public void setValor(float valor) {
        this.valor = valor; // Define o valor da moeda com o valor recebido como parâmetro
    }

    // Método getter para o atributo valor
    // Retorna o valor da moeda
    public float getValor() {
        return valor;
    }

    // Sobrescrita do método toString
    // Retorna uma representação em string da moeda, contendo o tipo e o valor
    @Override
    public String toString() {
        return tipo + ", no valor de " + valor; // Retorna o tipo e o valor da moeda formatados como string
    }
}
