import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/cadastro_alunos";
    private static final String USUARIO = "root";
    private static final String SENHA = "admin123";
    static Pessoa p = new Pessoa();
    public static void main(String[] args) throws Exception {
                           
        System.out.println("---------------------------------");
        System.out.println("---------------------------------");
        System.out.println("Sistema de Cadastro de alunos");
        System.out.println("---------------------------------");
        System.out.println("---------------------------------");

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexao = DriverManager.getConnection(URL,USUARIO,SENHA);
            Scanner sc = new Scanner(System.in);
            
            

            while (true){
            System.out.println("1. Adicionar aluno");
            System.out.println("2. Visualizar alunos");
            System.out.println("3. Modificar aluno");
            System.out.println("4. Excluir aluno");
            System.out.println("5. Sair");
            System.out.println("Escolha uma opção: ");
            int escolha = sc.nextInt();

            switch (escolha) {
                case 1:
                    adicionarAluno(conexao, sc);
                    break;
                case 2:
                    visualizarAlunos(conexao);
                    break;
                case 3:
                    modificarAluno(conexao, sc);
                    break;
                case 4:
                    excluirAluno(conexao, sc);
                    break;
                case 5:
                    System.out.println("Saindo...");
                    conexao.close();
                    System.exit(0);
                    break;        
                default:
                    System.out.println("Opcao invalida!");
            }
        }
    } catch (Exception e){
        e.printStackTrace();
    }
}

private static void adicionarAluno(Connection conexao, Scanner sc) throws SQLException {
    
    System.out.println("Nome do aluno: ");
    p.setNome(sc.nextLine());
    sc.nextLine();
    

    System.out.println("Matrícula do aluno: ");
    p.setMatricula(sc.nextInt());
    sc.nextLine();
    
    

    System.out.println("Nota do aluno: ");
    p.setNota(sc.nextDouble());

    

    String sql = "INSERT INTO alunos (nome, matricula, nota) VALUES (?, ?, ?)";
    try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
        preparedStatement.setString(1, p.getNome());
        preparedStatement.setInt(2, p.getMatricula());
        preparedStatement.setDouble(3, p.getNota());
        preparedStatement.executeUpdate();
        System.out.println("Aluno adicionado com sucesso!");
    }
}
private static void visualizarAlunos(Connection conexao) throws SQLException {
    String sql = "SELECT * FROM alunos";
    try (Statement statement = conexao.createStatement();
         ResultSet resultSet = statement.executeQuery(sql)) {

        System.out.println("\nLista de Alunos:");
        System.out.printf("%-5s %-20s %-20s %-10s\n", "ID", "Nome", "Matrícula", "Nota");
        

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String nome = resultSet.getString("nome");
            int matricula = resultSet.getInt("matricula");
            double nota = resultSet.getDouble("nota");

            System.out.printf("%-5d %-20s %-20s %-10.2f\n", id, nome, matricula, nota);
            System.out.println("-------------------------------------------------------------------");
        }
    }
}
private static void modificarAluno(Connection conexao, Scanner scanner) throws SQLException {
    visualizarAlunos(conexao);

    System.out.print("Digite o ID do aluno que deseja modificar: ");
    int id = scanner.nextInt();
    scanner.nextLine();

    System.out.print("Novo nome do aluno: ");
    String novoNome = scanner.nextLine();

    System.out.print("Nova matrícula do aluno: ");
    int novaMatricula = scanner.nextInt();

    System.out.print("Nova nota do aluno: ");
    double novaNota = scanner.nextDouble();

    String sql = "UPDATE alunos SET nome=?, matricula=?, nota=? WHERE id=?";
    try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
        preparedStatement.setString(1, novoNome);
        preparedStatement.setInt(2, novaMatricula);
        preparedStatement.setDouble(3, novaNota);
        preparedStatement.setInt(4, id);
        int linhasAfetadas = preparedStatement.executeUpdate();

        if (linhasAfetadas > 0) {
            System.out.println("Aluno modificado com sucesso!");
        } else {
            System.out.println("Aluno não encontrado.");
        }
    }
}
private static void excluirAluno(Connection conexao, Scanner scanner) throws SQLException {
    visualizarAlunos(conexao);

    System.out.print("Digite o ID do aluno que deseja excluir: ");
    int id = scanner.nextInt();

    String sql = "DELETE FROM alunos WHERE id=?";
    try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
        preparedStatement.setInt(1, id);
        int linhasAfetadas = preparedStatement.executeUpdate();

        if (linhasAfetadas > 0) {
            System.out.println("Aluno excluído com sucesso!");
        } else {
            System.out.println("Aluno não encontrado.");
        }
    }
}
}
