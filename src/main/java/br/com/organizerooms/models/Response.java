package br.com.organizerooms.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class Response<T> {

    private T data;

    private List<String> erros;

    private HashMap<?, ?> dadosMap;

    private Optional<Pessoa> pessoa;

    public Response(List<String> erros, HashMap<?, ?> dadosMap) {
        this.erros = erros;
        this.dadosMap = dadosMap;
    }

    public Response(T data) {
        this.data = data;
    }

    public Response() {

    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<String> getErros() {
        if (this.erros == null) {
            this.erros = new ArrayList<String>();
        }
        return erros;
    }

    public void setErros(List<String> erros) {
        this.erros = erros;
    }

    public HashMap<?, ?> getDadosMap() {
        return dadosMap;
    }

    public void setDadosMap(HashMap<?, ?> dadosMap) {
        this.dadosMap = dadosMap;
    }

    public Optional<Pessoa> getPessoa() {
        return pessoa;
    }

    public void setPessoa(Optional<Pessoa> pessoa) {
        this.pessoa = pessoa;
    }

}
