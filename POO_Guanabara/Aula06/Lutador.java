package POO_Guanabara.Aula06;

public class Lutador {
    private String nome;
    private String nacionalidade;
    private int idade;
    private float peso;
    private float altura;
    private String categoria;
    private int vitorias;
    private int derrotas;
    private int empates;

    // Metodos

    public Lutador(String nome, String nacionalidade, int idade, float peso, float altura, int vitorias, int derrotas, int empates){
        this.setNome(nome);
        this.setNacionalidade(nacionalidade);
        this.setIdade(idade);
        this.setPeso(peso);
        this.setAltura(altura);
        this.setVitorias(vitorias);
        this.setDerrotas(derrotas);
        this.setEmpates(empates);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
        this.setCategoria(); // Corrigir chamada para setCategoria
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria() {
        if (peso < 52.2) {
            this.categoria = "Invalido"; // Corrigir para atribuir diretamente a categoria
        } else if (peso <= 70.3) {
            this.categoria = "Leve";
        } else if (peso <= 83.9) {
            this.categoria = "Medio";
        } else if (peso <= 120.2) {
            this.categoria = "Pesado";
        } else {
            this.categoria = "Invalido";
        }
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }

    public int getVitorias() {
        return vitorias; // Corrigir retorno para vitorias
    }

    public void setVitorias(int vitorias) {
        this.vitorias = vitorias;
    }

    public int getEmpates() {
        return empates;
    }

    public void setEmpates(int empates) {
        this.empates = empates;
    }

    // Metodos gerais

    public void ganharLuta(){
        setVitorias(this.getVitorias()+1);
    }

    public void perderLuta(){
        setDerrotas(this.getDerrotas() + 1);
    }

    public void empatarLuta(){
        setEmpates(getEmpates() + 1);
    }

    public void apresentar(){
        System.out.println("Chegou a hora, aprensetamnos o lutador " + this.getNome());
        System.out.println("diretamente do " +this.getNacionalidade());
        System.out.println(this.getNome() + " tem " + getIdade() + " anos de idade.");
        System.out.println("Com " + this.getAltura() + "m de altura.");
        System.out.println("Pesando " + this.getPeso() + "Kg");
        System.out.println("Já venceu " + this.getVitorias() + " vezes");
        System.out.println("Pórem perdeu " + this.getDerrotas() + " lutas");
        System.out.println("E empatou " + this.getEmpates());
    }

    public void status() {
        System.out.println("Peso: " + this.getPeso() + "Kg");
        System.out.println("Altura: " + this.getAltura() + "m");
        System.out.println(this.getNome() + "é um peso " + this.getCategoria());
        System.out.println("Vitorias: " + this.getVitorias());
        System.out.println("Derrotas: " + this.getDerrotas());
        System.out.println("Empates: " + this.getEmpates());
        
    }
}
