package br.edu.aesa.model;

public class Palavra {
    private String termoIngles, traducaoPortugues;

    public Palavra(String termoIngles, String traducaoPortugues) {
        this.termoIngles = termoIngles;
        this.traducaoPortugues = traducaoPortugues;
    }

    public String getTermoIngles() {
        return termoIngles;
    }
    public void setTermoIngles(String termoIngles) {
        this.termoIngles = termoIngles;
    }

    public String getTraducaoPortugues() {
        return traducaoPortugues;
    }
    public void setTraducaoPortugues(String traducaoPortugues) {
        this.traducaoPortugues = traducaoPortugues;
    }


}
