package Pratica.exe02;

class Usuario {
    private String nomeUsuario;
    private String senha;

    public Usuario(String nomeUsuario, String senha) {
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public boolean verificarSenha(String senha) {
        return this.senha.equals(senha);
    }
}