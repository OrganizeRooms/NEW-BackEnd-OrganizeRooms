package br.com.organizerooms.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class JwtAuthenticationDto {

    private String pesEmail;
    private String pesSenha;
    private String pesNovaSenha;

    public JwtAuthenticationDto() {
    }

    @NotEmpty(message = "Email não pode ser vazio.")
    //@Email(message = "Email inválido.")
    public String getPesEmail() {
        return pesEmail;
    }

    public void setPesEmail(String pesEmail) {
        this.pesEmail = pesEmail;
    }

    @NotEmpty(message = "Senha não pode ser vazia.")
    public String getPesSenha() {
        return pesSenha;
    }

    public void setPesSenha(String pesSenha) {
        this.pesSenha = pesSenha;
    }

    public String getPesNovaSenha() {
        return pesNovaSenha;
    }

    public void setPesNovaSenha(String pesNovaSenha) {
        this.pesNovaSenha = pesNovaSenha;
    }

    @Override
    public String toString() {
        return "JwtAuthenticationDto{" + "pesEmail=" + pesEmail + ", pesSenha=" + pesSenha + ", pesNovaSenha=" + pesNovaSenha + '}';
    }

}
