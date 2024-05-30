package POO_Guanabara.Aula05_Encapsulamento;

public class ControleRemoto implements Controlador {
    // Atributos
    private int volume;
    private boolean ligado;
    private boolean tocando;

    // metodos especiais

    public ControleRemoto(){
        this.setVolume(30);
        this.setLigado(false);
        this.setTocando(false);
        
    }

    public void setLigado(boolean ligado) {
        this.ligado = ligado;
    }

    public boolean getLigado(){
        return ligado;
    }


    public void setTocando(boolean tocando) {
        this.tocando = tocando;
    }

    public boolean getTocando(){
        return tocando;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getVolume() {
        return volume;
    }

    @Override
    public void ligar() {
        this.setLigado(true);

        
    }

    @Override
    public void desligar() {
        this.setLigado(false);
    }

    @Override
    public void abrirMenu() {
        System.out.println("Está ligado? " + this.getLigado());
        System.out.println("Está tocando? " + this.getTocando());
        System.out.print("Volume: " + this.getVolume());

        for(int i = 0; i <= this.getVolume(); i+=10){
            System.out.print("|");

        }
       
    }

    @Override
    public void fecharMenu() {
        System.out.println("Fechando menu.");
        
    }

    @Override
    public void maisVolume() {
        if (this.getLigado()){
            this.setVolume(this.getVolume() + 5);
        }
       
    }

    @Override
    public void menosVolume() {
        if (this.getLigado()){
            this.setVolume(getVolume() - 5);
        }
       
    }

    @Override
    public void deixarMudo() {
        if(this.getLigado() && this.getVolume() > 0){
            this.setVolume(0);
        }
    
    }

    @Override
    public void sairDoMudo() {
        if(this.getLigado() && this.getVolume() == 0){
            setVolume(50);

        }
    
    }

    @Override
    public void play() {
        if(this.getLigado() && !(this.getTocando())){
            this.setTocando(true)

        }
       
    }

    @Override
    public void pause() {
        if(this.getLigado() && this.getTocando()){
            this.setTocando(false);

        }
        
    }


   

    
}
