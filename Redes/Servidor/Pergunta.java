package servidor;

public class Pergunta {

    private String enunciado;
    private String[] alternativas;
    private char correta;

    public Pergunta(String enunciado, String[] alternativas, char correta) {
        this.enunciado = enunciado;
        this.alternativas = alternativas;
        this.correta = correta;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public String[] getAlternativas() {
        return alternativas;
    }

    public char getCorreta() {
        return correta;
    }
}