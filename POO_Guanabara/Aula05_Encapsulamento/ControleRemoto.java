package POO_Guanabara.Aula05_Encapsulamento;

public class ControleRemoto implements Controlador {
    // Atributos
    private int volume;
    private boolean ligado;
    private boolean tocando;

    // metodos especiais

    public ControleRemoto(int volume, boolean ligado, boolean tocando){
        this.setVolume(volume);
        this.setLigado(ligado);
        this.setTocando(tocando);
        
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
        System.out.println("Volume: " + this.getVolume());

        for(int i = 0; i <= this.getVolume(); i+=10){
            System.out.println("[]");

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
    
    }

    @Override
    public void sairDoMudo() {
    
    }

    @Override
    public void play() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'play'");
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pause'");
    }


   

    
}
