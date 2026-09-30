package br.edu.aesa.service;

import br.edu.aesa.model.Palavra;

import java.util.*;

public class DicionarioService {
    private final Map<String, String> dicionario = new HashMap<>();

    public boolean cadastrar(String chaveIngles, String valorPortugues) {
        if (dicionario.containsKey(chaveIngles)) {
            return false;
        }

        dicionario.put(chaveIngles, valorPortugues);
        return true;
    }

    public String BuscarPorChave (String chaveIngles) {
        return dicionario.get(chaveIngles);
    }

    public List<Palavra> listarTodos() {
        List<Palavra> palavras = new ArrayList<>();
        for (Map.Entry<String, String> entrada: dicionario.entrySet()) {
            palavras.add(new Palavra(entrada.getKey(), entrada.getValue()));
        }
        palavras.sort(Comparator.comparing(Palavra::getTermoIngles));
        return palavras;
    }

    public boolean atualizar(String chaveInglesAtual, String novaChaveIngles, String novaTraducaoPortugues) {
        if (!dicionario.containsKey(chaveInglesAtual) || dicionario.containsKey(novaChaveIngles)){
            return false;
        }

        dicionario.remove(chaveInglesAtual);
        dicionario.put(novaChaveIngles, novaTraducaoPortugues);
        return true;
    }

    public boolean remover(String chaveIngles) {
        if (!dicionario.containsKey(chaveIngles)) {
            return false;
        }

        dicionario.remove(chaveIngles);
        return true;
    }

    public boolean estaVazio() {
        return dicionario.isEmpty();
    }

}