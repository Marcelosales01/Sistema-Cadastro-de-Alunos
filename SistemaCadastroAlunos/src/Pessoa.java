public class Pessoa {
    private String nome;
    private int matricula;  
    private double nota;    
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public Pessoa() {
        this.nome = null;
        this.matricula = 0;  
        this.nota = 0.0;     
    }
}
