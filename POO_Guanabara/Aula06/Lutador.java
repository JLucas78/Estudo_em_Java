package POO_Guanabara.Aula06;

import java.util.Random;



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
    public int lutas;



    // Atributos derivados
    private float forca; // vai de 1 á 10 (o atributo resistencia foi mesclado a força)
    private float agilidade; // vai de 1 á 10
    private float tecnica; // vai de 1 á 10
    private int moral; // vai de 0 á 100%
    private int energia; // vai de 0 á 100%
    private float aptidaoFisica; //vai de 1 á 10 (Gerado automaticamente)


    // Atributos finais
    private int danoDoGolpe;
    private int chanceDeDesvio;
    private int vidaBase = 300;
    private int vida;


    // Variaveis para o sistema como um todo
    Random valorAleatorio = new Random();




    // Metodos dos atributos gerais
    
    // Construtor
        public Lutador(String nome, String nacionalidade, int idade, float peso, float altura, int vitorias, int derrotas, int empates, int tecnica, int luta) {
            this.nome = nome;
            this.nacionalidade = nacionalidade;
            this.idade = idade;
            this.peso = peso;
            this.altura = altura;
            this.vitorias = vitorias;
            this.derrotas = derrotas;
            this.empates = empates;
            this.tecnica = tecnica;
            this.moral = 100;
            this.energia = 100;
            this.setAgilidade();
            this.setForca();
            this.setVida();
            this.setCategoria();
            this.setAptidaoFisica();
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

    public float getAptidaoFisica(){
        return aptidaoFisica;
    }

    public void setAptidaoFisica() {
        this.aptidaoFisica = valorAleatorio.nextInt(10) + 1;
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

    // Metodos para definir a quantidade de  lutas

    public int getLutas(){
        return lutas;
    }

    public void setLutas(){
        this.lutas = valorAleatorio.nextInt(100) + 1;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void setDerrotas() {
        this.derrotas = this.getLutas() * 0.2f;
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

        // Intervalos ideais
        double idadeIdealMin = 24;
        double idadeIdealMax = 32;
        double pesoIdealMin = 100;
        double pesoIdealMax = 120;
        double alturaIdealMin = 1.9;
        double alturaIdealMax = 2.0;
        double aptidaoFisicaIdeal = 9;

        
        // Limites dos requisitos
        double idadeMin = 18;
        double idadeMax = 40;
        double pesoMin = 50;
        double pesoMax = 120;
        double alturaMin = 1.5;
        double alturaMax = 2.0;
        double aptidaoFisicaMin = 1;
        double aptidaoFisicaMax = 10;

        double idadeNormalizada = normalizar(this.idade, idadeIdealMin, idadeIdealMax, idadeMin, idadeMax);
        double pesoNormalizado = normalizar(this.peso, pesoIdealMin, pesoIdealMax, pesoMin, pesoMax);
        double alturaNormalizada = normalizar(this.altura, alturaIdealMin, alturaIdealMax, alturaMin, alturaMax);
        double aptidaoFisicaNormalizada = normalizar(this.aptidaoFisica, aptidaoFisicaIdeal, aptidaoFisicaIdeal, aptidaoFisicaMin, aptidaoFisicaMax);
        double mediaNormalizada = ((idadeNormalizada * 1.5) + (aptidaoFisicaNormalizada * 3) + (pesoNormalizado * 3) + (alturaNormalizada * 2.5)) / 10;

       
        this.forca = (float) Math.round(mediaNormalizada * 10);

        if (this.forca < 1) this.forca = 1;
        if (this.forca > 10) this.forca = 10;
    }


     
   
    // Método para calcular a agilidade
    public void setAgilidade() {

        // Intervalos ideais
        double idadeIdealMin = 18;
        double idadeIdealMax = 26;
        double pesoIdealMin = 50;
        double pesoIdealMax = 80;
        double alturaIdealMin = 1.6;
        double alturaIdealMax = 1.8;
        double aptidaoFisicaIdeal = 9;

        // Limites dos requisitos
        double idadeMin = 18;
        double idadeMax = 40;
        double pesoMin = 50;
        double pesoMax = 120;
        double alturaMin = 1.5;
        double alturaMax = 2.0;
        double aptidaoFisicaMin = 1;
        double aptidaoFisicaMax = 10;
        

        double idadeNormalizada = normalizar(this.idade, idadeIdealMin, idadeIdealMax, idadeMin, idadeMax);
        double pesoNormalizado = normalizar(this.peso, pesoIdealMin, pesoIdealMax, pesoMin, pesoMax);
        double alturaNormalizada = normalizar(this.altura, alturaIdealMin, alturaIdealMax, alturaMin, alturaMax);
        double aptidaoFisicaNormalizada = normalizar(this.aptidaoFisica, aptidaoFisicaIdeal, aptidaoFisicaIdeal, aptidaoFisicaMin, aptidaoFisicaMax);
        double mediaNormalizada = ((idadeNormalizada * 4) + (aptidaoFisicaNormalizada * 2.5) + (pesoNormalizado * 2.5) + (alturaNormalizada * 1)) / 10;
        this.agilidade = (float) Math.round(mediaNormalizada * 10);


        if (this.agilidade < 1) this.agilidade = 1;
        if (this.agilidade > 10) this.agilidade = 10;

    }


    // Método para calcular a Tecnica

    public void setTecnica(){
        // Intervalos ideias
        double idadeIdealMin = 32;
        double idadeIdealMax = 38;
        double lutasIdealMin = 80;
        double lutasIdealMax = 100;

        // Limites dos requisitos
        double idadeMin = 18;
        double idadeMax = 40;
        double lutasMin = 0;
        double lutasMax = 100;

        double idadeNormalizada = normalizar(this.idade, idadeIdealMin, idadeIdealMax, idadeMin, idadeMax);
        double lutasNormalizadas = normalizar(this.luta, lutasIdealMin, lutasIdealMax, lutasMin, lutasMax);
    }






    // Metodos dos atributos finais

    // Método para calcular a vida
    public void setVida() {
        double forcaVida = this.forca * 10; // 25% da vida é baseada na força
        this.vida = (int) (vidaBase + forcaVida); // Valor total da vida
    }

    // Método para calcular o dano
    public int calcularDanoDoGolpe() {
    return (int) (this.forca * (valorAleatorio.nextFloat() * 2 + 3));
    }

     // Método para calcular a resistência ao golpe
     public int calcularResistenciaAoGolpe(Lutador atacante) {
        // Passo 1: Calcular o dano do golpe
        int danoDoGolpe = atacante.calcularDanoDoGolpe();
        
        // Passo 2: Calcular a porcentagem de resistência
        float multiplicador = valorAleatorio.nextFloat() + 5; // Valor aleatório entre 5 e 6
        int porcentagemIgnorada = Math.round(this.forca * multiplicador);
        
        // Passo 3: Aplicar a resistência ao dano do golpe
        float danoIgnorado = danoDoGolpe * (porcentagemIgnorada / 100.0f);
        int danoFinal = Math.round(danoDoGolpe - danoIgnorado);
        
        return danoFinal;
    }

    public void calculoDaChanceDeContraAtaque(Lutador defensor){
        int chanceContraAtaque = (defensor.forca + defensor.tecnica) * 2;

    }


    // Método para aplicar a diminuição da força com base na energia
    public void aplicarDiminuiçãoDeForca() {
        float decrescimo = ((100 - this.energia) / 10) * 0.1f; // 0.1 de força a cada 10% de energia perdida
        this.forca -= decrescimo;
        if (this.forca < 0.5) this.forca = 0.5f;
    }

     
    // Métodos para calcular a velocidade de ataque e esquiva
    public int calcularVelocidadeDeAtaque() {
        return valorAleatorio.nextInt((int)(this.agilidade * 10)) + 1;
    }

    public int calcularVelocidadeDeEsquiva() {
        return valorAleatorio.nextInt((int)(this.agilidade * 10)) + 1;
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
