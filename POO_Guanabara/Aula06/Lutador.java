package POO_Guanabara.Aula06;




public class Lutador {
    // Atributos gerais
    private String nome;
    private String nacionalidade;
    private int idade;
    private float peso;
    private float altura;
    private String categoria;
    private int vitorias;
    private int derrotas;
    private int empates;



    // Atributos derivados
    private float forca; // vai de 1 á 10
    private float agilidade; // vai de 1 á 10
    private float resistencia; // vai de 1 á 10
    private float tecnica; // vai de 1 á 10
    private int moral; // vai de 0 á 100%
    private int energia; // vai de 0 á 100%


    // Atributos finais
    private int danoDoGolpe;
    private int chanceDeDesvio;
    private int vidaBase = 180;
    private int vida;




    // Metodos dos atributos gerais
    
    // Construtor
        public Lutador(String nome, String nacionalidade, int idade, float peso, float altura, int vitorias, int derrotas, int empates, int resistencia, int tecnica) {
            this.nome = nome;
            this.nacionalidade = nacionalidade;
            this.idade = idade;
            this.peso = peso;
            this.altura = altura;
            this.vitorias = vitorias;
            this.derrotas = derrotas;
            this.empates = empates;
            this.resistencia = resistencia;
            this.tecnica = tecnica;
            this.moral = 100;
            this.energia = 100;
            this.setAgilidade();
            this.setForca();
            this.setVida();
            this.setCategoria();
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
            this.categoria = "Invalido, peso abaixo da categoria minima."; // Corrigir para atribuir diretamente a categoria
        } else if (peso <= 70.3) {
            this.categoria = "Leve";
        } else if (peso <= 83.9) {
            this.categoria = "Medio";
        } else if (peso <= 120.2) {
            this.categoria = "Pesado";
        } else {
            this.categoria = "Invalido, peso acima da categoria maxima.";
        }
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }

    public int getVitorias() {
        return vitorias; 
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


    // Metodos dos atributos derivados


     // Método para normalizar os dados
     private static double normalizar(double valor, double idealMin, double idealMax, double min, double max) {
        if (valor >= idealMin && valor <= idealMax) {
            return 1.0; // Valor ideal, normalizado como máximo
        } else if (valor < idealMin) {
            return 1 - Math.abs((valor - idealMin) / (idealMin - min));
        } else {
            return 1 - Math.abs((valor - idealMax) / (max - idealMax));
        }
    }


    // Método para calcular a força
    public void setForca() {
        double idadeIdeal = 28;
        double pesoIdeal = 120;
        double alturaIdeal = 2.0;
        double idadeMin = 18;
        double idadeMax = 40;
        double pesoMin = 50;
        double pesoMax = 120;
        double alturaMin = 1.5;
        double alturaMax = 2.0;

        double idadeNormalizada = normalizar(this.idade, idadeIdeal, idadeIdeal, idadeMin, idadeMax);
        double pesoNormalizado = normalizar(this.peso, pesoIdeal, pesoIdeal, pesoMin, pesoMax);
        double alturaNormalizada = normalizar(this.altura, alturaIdeal, alturaIdeal, alturaMin, alturaMax);

        double mediaNormalizada = (idadeNormalizada + pesoNormalizado + alturaNormalizada) / 3.0;
        this.forca = (float) Math.round(mediaNormalizada * 10);

        if (this.forca < 1) this.forca = 1;
        if (this.forca > 10) this.forca = 10;
    }


     
   // Método para calcular a agilidade
// Método para calcular a agilidade
public void setAgilidade() {
    double idadeIdealMin = 18;
    double idadeIdealMax = 26;
    double pesoIdealMin = 50;
    double pesoIdealMax = 80;
    double alturaIdealMin = 1.6;
    double alturaIdealMax = 1.8;
    double idadeMin = 18;
    double idadeMax = 40;
    double pesoMin = 50;
    double pesoMax = 120;
    double alturaMin = 1.5;
    double alturaMax = 2.0;

    double idadeNormalizada = normalizar(this.idade, idadeIdealMin, idadeIdealMax, idadeMin, idadeMax);
    double pesoNormalizado = normalizar(this.peso, pesoIdealMin, pesoIdealMax, pesoMin, pesoMax);
    double alturaNormalizada = normalizar(this.altura, alturaIdealMin, alturaIdealMax, alturaMin, alturaMax);

    double mediaNormalizada = (idadeNormalizada + pesoNormalizado + alturaNormalizada) / 3.0;
    this.agilidade = (float) Math.round(mediaNormalizada * 10);

    double distanciaAgilidadeMaxima = Math.max(
    Math.abs(this.agilidade - 10), // Distância em relação à agilidade máxima
    Math.abs(1 - this.agilidade) // Distância em relação à agilidade mínima
    );
    double ajuste = 1.0 - distanciaAgilidadeMaxima / 9.0; // 9 é a máxima diferença possível
    this.agilidade *= ajuste;

    // Arredonda a agilidade ajustada para o valor inteiro mais próximo
    this.agilidade = Math.round(this.agilidade);
    }




    // Metodos dos atributos finais

    // Método para calcular a vida
    public void setVida() {
        double forcaVida = this.forca * 4.5; // 45 de vida para força 10
        double resistenciaVida = this.resistencia * 7.5; // 75 de vida para resistência 10
        this.vida = (int) (vidaBase + forcaVida + resistenciaVida);
    }

    // Método para calcular o dano
    public float calcularDano() {
        float danoMinimo = 10 + (int) (this.forca - 1) * 3;
        float danoMaximo = 16 + (int) (this.forca - 1) * 3;
        return (float) (Math.random() * (danoMaximo - danoMinimo + 1)) + danoMinimo;
    }

    // Método para aplicar a diminuição da força com base na energia
    public void aplicarDiminuiçãoDeForca() {
        float decrescimo = ((100 - this.energia) / 10) * 0.1f; // 0.1 de força a cada 10% de energia perdida
        this.forca -= decrescimo;
        if (this.forca < 0.5) this.forca = 0.5f;
    }

    // Método para aplicar a diminuição da agilidade com base na energia e moral
    public void aplicarDiminuiçãoDeAgilidade() {
        float decrescimoEnergia = ((100 - this.energia) / 10) * 0.1f; // 0.1 de agilidade a cada 10% de energia perdida
        float decrescimoMoral = ((100 - this.moral) / 25) * 0.1f; // 0.1 de agilidade a cada 25% de moral perdida
        this.agilidade -= (decrescimoEnergia + decrescimoMoral);
        if (this.agilidade < 0.5) this.agilidade = 0.5f;
    }

    





    // Metodos da luta

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
        System.out.println("Chegou a hora, apresentamnos o lutador " + this.getNome());
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
