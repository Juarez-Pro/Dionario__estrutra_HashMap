package br.edu.aesa.model;

public class Palavra {
    private String termoIngles, traducaoPortugues;

    Palavra(String termoIngles, String traducaoPortugues) {
        this.termoIngles = termoIngles;
        this.traducaoPortugues = traducaoPortugues;
    }

    String getTermoIngles() {
        return termoIngles;
    }
    void setTermoIngles(String termoIngles) {
        this.termoIngles = termoIngles;
    }

    String getTraducaoPortugues() {
        return traducaoPortugues;
    }
    void setTraducaoPortugues(String traducaoPortugues) {
        this.traducaoPortugues = traducaoPortugues;
    }


}
