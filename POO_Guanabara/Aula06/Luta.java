package POO_Guanabara.Aula06;

import java.util.Random;

public class Luta {
    private Lutador desafiado;
    private Lutador desafiante;
    private int rounds;
    private boolean aprovada;


    // Metodos

    public void setDesafiado(Lutador desafiado) {
        this.desafiado = desafiado;
    }

    public Lutador getDesafiado() {
        return desafiado;
    }

    public void setDesafiante(Lutador desafiante) {
        this.desafiante = desafiante;
    }

    public Lutador getDesafiante() {
        return desafiante;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public int getRounds() {
        return rounds;
    }

    public void setAprovada(boolean aprovada) {
        this.aprovada = aprovada;
    }

    public boolean getAprovada(){
        return aprovada;
    }


    // Metodos publicos

    public void marcarLuta(Lutador desafiado, Lutador desafiante){
        if(desafiado.getCategoria().equals(desafiante.getCategoria()) && desafiado != desafiante){
            this.setAprovada(true);
            this.setDesafiado(desafiado);
            this.setDesafiante(desafiante);
        } else{
            this.setAprovada(false);
            this.setDesafiado(null);
            this.setDesafiante(null);
        }
    }


    public void lutar(){
        if(getAprovada()){
            System.out.println("### DESAFIADO ###");
            desafiado.apresentar();
            System.out.println("### DESAFIANTE ###");
            desafiante.apresentar();

            Random aleatorio = new Random();

            int vencedor = aleatorio.nextInt(3);

            switch (vencedor) {
                case 0:
                System.out.println("######################");
                System.out.println("Empatou!");
                this.desafiado.empatarLuta();
                this.desafiante.empatarLuta();      
                    break;
                case 1:
                System.out.println("######################");
                System.out.println(desafiado.getNome() + " Venceu a luta!");
                this.desafiado.ganharLuta();
                this.desafiante.perderLuta();      
                    break;
                case 2:
                System.out.println("######################");
                System.out.println(desafiante.getNome() + " Venceu a luta!");
                this.desafiante.ganharLuta();
                this.desafiado.perderLuta();            
                    break;       
                default:
                    break;
            }
        } else{
            System.out.println("A luta não pode acontecer");
        }
    }
}
