package br.com.usystem.stockroll.model;

/*
public enum Perfil {
    GESTOR,
    USUARIO;
}
*/



public enum Perfil {
    
    GESTOR("Gestor"),
    USUARIO("Usuario");


    private String tipo;


    Perfil(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

}
