package codigos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ClienteDAO {

    private static final String SELECT_BASE =
        "SELECT id_cli, nome, cpf, idade, login, senha, rest_medica FROM cliente ";

    public void inserir(Cliente c) throws SQLException {
        String sql = "INSERT INTO cliente (nome, cpf, idade, login, senha, rest_medica) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getCpf());
            stmt.setInt(3, c.getIdade());
            stmt.setString(4, c.getLogin());
            stmt.setString(5, c.getSenha());
            stmt.setString(6, c.getRestMedica());
            stmt.executeUpdate();

            try (ResultSet chaves = stmt.getGeneratedKeys()) {
                if (chaves.next()) {
                    c.setIdCli(chaves.getInt(1));
                }
            }
        }
    }

    public Cliente buscarPorCpf(String cpf) throws SQLException {
        String sql = SELECT_BASE + "WHERE cpf = ?";

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        }
        return null;
    }

    public Cliente buscarPorLogin(String login) throws SQLException {
        String sql = SELECT_BASE + "WHERE login = ?";

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, login);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        }
        return null;
    }

    public ArrayList<Cliente> listarTodos() throws SQLException {
        ArrayList<Cliente> clientes = new ArrayList<>();
        String sql = SELECT_BASE + "ORDER BY nome";

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                clientes.add(mapear(rs));
            }
        }
        return clientes;
    }

    public int contar() throws SQLException {
        String sql = "SELECT COUNT(*) FROM cliente";

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    private Cliente mapear(ResultSet rs) throws SQLException {
        return new Cliente(
            rs.getInt("id_cli"),
            rs.getString("nome"),
            rs.getString("cpf"),
            rs.getInt("idade"),
            rs.getString("login"),
            rs.getString("senha"),
            rs.getString("rest_medica")
        );
    }
}
