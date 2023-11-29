public class Aluno extends Pessoa{
    private int matricula;
    private double nota;
    
    
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

    public Aluno(int matricula, double nota, String nome) {
        super();
        this.matricula = matricula;
        this.nota = nota;
    }

    
}
