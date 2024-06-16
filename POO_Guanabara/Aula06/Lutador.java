package POO_Guanabara.Aula06;

import java.util.Random;

public class Lutador {
    // Atributos gerais
    private String nome;
    private String nacionalidade;
    private int idade;
    private float peso;
    private float altura;
    private float aptidaoFisica; // Vai de 1 a 10 (Gerado automaticamente)
    private int lutas;
    private int vidaBase = 300;

    // Atributos derivados (dos atributos gerais)
    private int vitorias;
    private int derrotas;
    private int empates;

    private String categoria; // Categoria do Lutador
    private float forca; // Vai de 1 a 10 (O atributo resistencia foi mesclado à força), se baseia na idade, peso, altura e aptidão física
    private float agilidade; // Vai de 1 a 10, se baseia na idade, peso, altura e aptidão física
    private int energia; // Vai de 0 a 100%
    private int experiencia; // Atributo baseado no número de lutas e idade do lutador (60% idade, 40% número de lutas)
    private int desempenho; // Atributo baseado na porcentagem de vitórias, abaixo de 10% será 1 (Valor mínimo), acima de 60% será 10 (Valor máximo).
    private int estadoFisico; // Baseado na idade e aptidão física (70% idade e 30% aptidão física)

    // Atributos finais
    private float tecnica; // Vai de 1 a 10 (Baseada 80% na experiência, e 20% no desempenho)
    private int confianca; // Vai de 0 a 100% (Baseada inicialmente no desempenho geral do lutador)
    private int danoDoGolpe; // Baseada na força, podendo variar de 3 a 5 vezes o valor da força do lutador
    private int vida; // Vida base + Vida Força (25% do total da vida depende da força)
    int vidaForca = (int)this.getForca() * 10;// 25% da vida é baseada na força

    // Variáveis para o sistema como um todo
    Random valorAleatorio = new Random();

    // Métodos dos atributos gerais
    
    // Construtor
    public Lutador(String nome, String nacionalidade, int idade, float peso, float altura) {
        // Gerando os atributos principais
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.idade = idade;
        this.peso = peso;
        this.altura = altura;

        // Gerando os dados das lutas, a experiência e o desempenho.
        gerarDadosDaLuta();
        this.vitorias = getVitorias();
        this.derrotas = getDerrotas();
        this.empates = getEmpates();
        this.experiencia = getExperiencia();
        this.desempenho = getDesempenho();

        this.calcularVidaInicial();
        this.tecnica = calcularTecnica(); // Corrigido: Calcula a técnica ao criar o objeto
        this.energia = 100;
        this.setAgilidade();
        this.setForca();
        this.setCategoria();
        this.setAptidaoFisica();
        this.calcularConfiancaInicial(desempenho);
    
    }


    // GERADOR
    // Nesses metodos será possivel gerar automaticamente valores aleatorios para nome, idade, altura, nacionalidade e aptidão fisica.

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
        this.idade = valorAleatorio.nextInt(40) +18 + 1;
    }



    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = valorAleatorio.nextInt(120) + 50 + 1;
        this.setCategoria(); 
    }



    
    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = valorAleatorio.nextFloat() *0.5f + 1.5f;
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

    //********************************************************************************************************************************//


    // Métodos para gerir a quantidade de lutas (Vitórias, Empates e Derrotas)
    public void gerarDadosDaLuta(){
        setLutas();
        setVitorias();
        setDerrotas();
        setEmpates();
        calcularExperiencia();
        calcularDesempenho();
    }

    public int getLutas(){
        return lutas;
    }

    public void setLutas(){
        this.lutas = valorAleatorio.nextInt(100) + 1;
    }

    public int getVitorias() {
        return vitorias; 
    }

    public void setVitorias() {
        this.vitorias = valorAleatorio.nextInt(this.lutas + 1);
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void setDerrotas() {
        int restante = this.lutas - this.vitorias;
        this.derrotas = valorAleatorio.nextInt(restante + 1);
    }

    public int getEmpates() {
        return empates;
    }

    public void setEmpates() {
        this.empates = this.lutas - this.vitorias - this.derrotas;
    }

    public int getExperiencia(){
        return experiencia;
    }

    public void calcularExperiencia() {
        // Intervalos ideais
        double idadeIdealMin = 32;
        double idadeIdealMax = 38;
        double numeroDeLutasIdealMin = 80;
        double numeroDeLutasIdealMax = 100;

        // Limites dos requisitos
        double idadeMin = 18;
        double idadeMax = 40;
        double numeroDeLutasMin = 1;
        double numeroDeLutasMax = 100;

        double idadeNormalizada = normalizar(this.idade, idadeIdealMin, idadeIdealMax, idadeMin, idadeMax);
        double lutasNormalizadas = normalizar(this.lutas, numeroDeLutasIdealMin, numeroDeLutasIdealMax, numeroDeLutasMin, numeroDeLutasMax);
        double mediaNormalizada = (idadeNormalizada * 6) + (lutasNormalizadas * 4) / 10;

        this.experiencia = (int) Math.round(mediaNormalizada * 10);

        if (this.experiencia < 1) {
            this.experiencia = 1;
        }

        if (this.experiencia > 10) {
            this.experiencia = 10;
        }
    }

    public int getDesempenho(){
        return desempenho;
    }

    public void calcularDesempenho() {
        // Intervalos ideais
        double porcentagemIdealDeVitoriasMin = 60;
        double porcentagemIdealDeVitoriasMax = 100;
    
        // Limites dos requisitos
        double porcentagemDeVitoriasMin = 10;
        double porcentagemDeVitoriasMax = 100;
    
        // Certificando que a divisão por zero não ocorre
        double aproveitamento = this.lutas > 0 ? ((double) this.vitorias / this.lutas) * 100 : 0; 
    
        double vitoriasNormalizadas = normalizar(aproveitamento, porcentagemIdealDeVitoriasMin, porcentagemIdealDeVitoriasMax, porcentagemDeVitoriasMin, porcentagemDeVitoriasMax
        );
    
        this.desempenho = (int) Math.round(vitoriasNormalizadas * 10);
    
        if (this.desempenho < 1) {
            this.desempenho = 1;
        }
    
        if (this.desempenho > 10) {
            this.desempenho = 10;
        }
    }
    

    
    // Método para calcular a técnica
    public int calcularTecnica() {
        // Técnica é influenciada pela experiência (80%) e desempenho (20%)
        double tecnica = (this.experiencia * 0.8) + (this.desempenho * 0.2);
        if (this.tecnica < 1) this.tecnica = 1;
        if (this.tecnica > 10) this.tecnica = 10;
        return (int) Math.round(tecnica);
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

    public float getForca() {
        return forca;
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


    
    

    // Metodos dos atributos finais

    //************************************************************************/

    // Métodos para calcular e manipular a vida
    
    public void calcularVidaInicial(){
        this.vida = this.vidaBase + this.vidaForca;
    }

     public int getVida() {
        return vida;
    }

    public void setVida(int novaVida) {
        this.vida = novaVida;
    }

    //*************************************************************************/

  
    

    // Método para calcular o dano do golpe
    public int calcularDanoDoGolpe() {
    // O dano do golpe varia de 3 a 5 vezes o valor da força do lutador.
        float multiplicador = valorAleatorio.nextFloat() * 2 + 3;
        return Math.round(this.forca * multiplicador);
    }

    // Método para calcular a resistência ao golpe
    public int calcularResistenciaAoGolpe(Lutador lutador) {
    // Passo 1: Calcular o dano do golpe do atacante
        int danoDoGolpe = lutador.calcularDanoDoGolpe();

    // Passo 2: Calcular a porcentagem de resistência
        float multiplicador = valorAleatorio.nextFloat() + 5; // Valor aleatório entre 5 e 6
        int porcentagemResistencia = Math.round(this.forca * multiplicador);

    // Passo 3: Aplicar a resistência ao dano do golpe
        float danoIgnorado = danoDoGolpe * (porcentagemResistencia / 100.0f);
        int danoFinal = Math.round(danoDoGolpe - danoIgnorado);

        return danoFinal;
    }


    //*********************************************************************************************************************/


    // GESTÃO DE CONFIANÇA
    // CONSTRUÇÃO DA MANIPULAÇÃO DO ATRIBUTO CONFIANÇA.

    public int confiancaInicial;

    public void calcularConfiancaInicial(int desempenho) {
        this.confianca = desempenho * 10;
        this.confiancaInicial = desempenho * 10;
    }

    public int getConfianca() {
        return confianca;
    }

    public int getConfiancaInicial() {
        return confiancaInicial;
    }

    public void somarConfianca(int valor) {
        double ganhoDeConfianca = obterGanhoDeConfianca();
        this.confianca = this.getConfianca() + (int)(valor * ganhoDeConfianca);
    }

    public void subtrairConfianca(int valor) {
        double percaDeConfianca = obterPercaDeConfianca();
        this.confianca = this.getConfianca() - (int)(valor * percaDeConfianca);
    }

    // Alterar confiança baseado na esquiva

    public void ganharConfiancaEsquiva(Lutador lutador) {
        lutador.somarConfianca(4);
    }

    public void perderConfiancaEsquiva(Lutador lutador) {
        lutador.subtrairConfianca(4);
    }

    // Modificadores de confiança
    // Nesse trecho do codigo vou construir um parametro diferente para cada lutador. Cada lutador pode ganhar ou perder um certo nivel de confiaça.
    // Isso irá depender da confiança original.

    private double obterGanhoDeConfianca() {
        if (this.getConfiancaInicial() > 80) {
            return 1.5;
        } else if (this.getConfiancaInicial() > 50) {
            return 1.2;
        } else if (this.getConfiancaInicial() > 30) {
            return 1.0;
        } else {
            return 0.8;
        }
    }

    private double obterPercaDeConfianca() {
        if (this.getConfiancaInicial() > 80) {
            return 0.8;
        } else if (this.getConfiancaInicial() > 50) {
            return 1.0;
        } else if (this.getConfiancaInicial() > 30) {
            return 1.2;
        } else {
            return 1.5;
        }
    }


    //********************************************************************************************************************/

   
    //*************************************************/

    //METODOS PARA CALCULAR A ESQUIVA

    // Calcular a velocidade de ataque
    public int calcularVelocidadeDeAtaque() {
        return valorAleatorio.nextInt((int)(this.agilidade * 10)) + 1;
    }

    // Calcular a velocidade de esquiva
    public int calcularVelocidadeDeEsquiva() {
        return valorAleatorio.nextInt((int)(this.agilidade * 10)) + 1;
    }

    public void CalcularChanceDeEsquiva(Lutador atacante, Lutador defensor) {
        if (defensor.calcularVelocidadeDeEsquiva() > atacante.calcularVelocidadeDeAtaque()) {
            // o que acontece caso o defensor consiga esquivar
            System.out.println("O defensor " + defensor.getNome() + " conseguiu esquivar do ataque!");
            ganharConfiancaEsquiva(defensor);
            perderConfiancaEsquiva(atacante);
        } else {
            // o que acontece caso o atacante acerte o golpe
            System.out.println("O atacante " + atacante.getNome() + " acertou o golpe no defensor " + defensor.getNome() + "!");
            defensor.setVida(defensor.getVida() - defensor.calcularResistenciaAoGolpe(atacante));
        }
    }
    

    //************************************/
    // Método para aplicar a diminuição da agilidade com base na energia e confiança
    public void aplicarDiminuiçãoDeAgilidade() {
        float decrescimoEnergia = ((100 - this.energia) / 10) * 0.1f; // 0.1 de agilidade a cada 10% de energia perdida
        float decrescimoConfianca = ((100 - this.confianca) / 25) * 0.1f; // 0.1 de agilidade a cada 25% de confiança perdida
        this.agilidade -= (decrescimoEnergia + decrescimoConfianca);
        if (this.agilidade < 0.5) this.agilidade = 0.5f;
    }

     // Método para aplicar a diminuição da força com base na energia
     public void aplicarDiminuiçãoDeForca() {
        float decrescimo = ((100 - this.energia) / 10) * 0.1f; // 0.1 de força a cada 10% de energia perdida
        this.forca -= decrescimo;
        if (this.forca < 0.5) this.forca = 0.5f;
    }



    // GESTÃO DE ENERGIA
    // CONSTRUÇÃO DA MANIPULAÇÃO DO ATRIBUTO ENERGIA.

    // Definindo o estado fisico do lutador

    public int getEstadoFisico() {
        return estadoFisico;
    }

    public void setEstadoFisico(int idade, int aptidaoFisica) {
        double idadeNormalizada = normalizar(idade, 18, 26, 18, 40);
        double aptidaoFisicaNormalizada = normalizar(aptidaoFisica, 7, 10, 1, 10);
        this.estadoFisico = (int) ((idadeNormalizada * 7) + (aptidaoFisicaNormalizada * 3));
    }

     // Perda de energia baseada em turnos
    public void perdaDeEnergiaTurno() {
        int energiaPerdida;
        if (this.estadoFisico >= 8) {
            energiaPerdida = 1;
        } else if (this.estadoFisico >= 5) {
            energiaPerdida = 2;
        } else if (this.estadoFisico >= 2) {
            energiaPerdida = 3;
        } else {
            energiaPerdida = 4;
        }

        this.energia -= energiaPerdida; // Subtraindo a energia perdida da energia atual

        // Adicionando validação para garantir que a energia não se torne negativa
        if (this.energia < 0) {
            this.energia = 0;
        }
    }


    // Perda de energia baseada em dano
    public void perdaDeEnergiaDano(Lutador atacante, Lutador defensor) {
    int energiaPerdida;
    int danoSofrido = defensor.calcularResistenciaAoGolpe(atacante); // Calcula o dano sofrido pelo desafiado

    // Valores ideais
    int danoSofridoIdealMin = 1;
    int danoSofridoIdealMax = 15;
    int estadoFisicoIdealMin = 8;
    int estadoFisicoIdealMax = 10;

    // Valores limites
    int danoSofridoMin = 1;
    int danoSofridoMax = 50;
    int estadoFisicoMin = 1;
    int estadoFisicoMax = 10;

    // Normalizando valores
    double danoSofridoNormalizado = normalizar(danoSofrido, danoSofridoIdealMin, danoSofridoIdealMax, danoSofridoMin, danoSofridoMax);
    double estadoFisicoNormalizado = normalizar(defensor.estadoFisico, estadoFisicoIdealMin, estadoFisicoIdealMax, estadoFisicoMin, estadoFisicoMax);
    double mediaNormalizada = (danoSofridoNormalizado + estadoFisicoNormalizado) * 5;

    long valoresNormalizados = Math.round(mediaNormalizada);

    // Determina se a força do desafiado é maior que a do desafiante
    boolean forcaMaior = defensor.forca > atacante.forca;

    if (forcaMaior && defensor.estadoFisico >= 8 && danoSofrido <= 15) {
        energiaPerdida = 0;
    } else {
        if (valoresNormalizados >= 8) {
            energiaPerdida = 1;
        } else if (valoresNormalizados >= 5) {
            energiaPerdida = 2;
        } else if (valoresNormalizados >= 2) {
            energiaPerdida = 3;
        } else {
            energiaPerdida = 4;
        }

        this.energia -= energiaPerdida; // Subtrai a energia perdida da energia atual
    }
    }}
