package br.com.usystem.stockroll.usuario;

/*
public enum Perfil {
    GESTOR,
    USUARIO;
}
*/


// Modelo abaixo não funcionou, tentar descobrir o porquê.
// 

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
