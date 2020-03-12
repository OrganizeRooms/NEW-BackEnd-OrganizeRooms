package br.com.organizerooms.enums;

public enum PerfilEnum {

    ROLE_ADMIN(0),
    ROLE_USUARIO(1),
    ROLE_TABLET(2), 
// ROLE_CLIENTE(3)
    ;

    private final int valor;

    PerfilEnum(int valorOpcao) {
        valor = valorOpcao;
    }

    public int getValor() {
        return valor;
    }
}
