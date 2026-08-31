package codigos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AcademiaDAO {

    // SELECT base, já trazendo a média de avaliação calculada pela view vw_academia_avaliacao
    private static final String SELECT_BASE =
        "SELECT a.id_acd, a.nome, a.endereco, a.contato, a.mensalidade, a.atividades_ofertadas, " +
        "a.num_personais, COALESCE(v.media_avaliacao, 0) AS media_avaliacao " +
        "FROM academia a LEFT JOIN vw_academia_avaliacao v ON v.id_acd = a.id_acd ";

    public void inserir(Academia a) throws SQLException {
        String sql = "INSERT INTO academia (nome, endereco, contato, mensalidade, atividades_ofertadas, " +
                      "num_personais) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, a.getNome());
            stmt.setString(2, a.getEndereco());
            stmt.setString(3, a.getN_contato());
            stmt.setDouble(4, a.getMensalidade());
            stmt.setString(5, a.getAtividadesOfertadas());
            stmt.setInt(6, a.getNumPersonais());
            stmt.executeUpdate();

            try (ResultSet chaves = stmt.getGeneratedKeys()) {
                if (chaves.next()) {
                    a.setIdAcd(chaves.getInt(1));
                }
            }
        }
    }

    public void atualizar(Academia a) throws SQLException {
        String sql = "UPDATE academia SET nome = ?, endereco = ?, contato = ?, mensalidade = ?, " +
                      "atividades_ofertadas = ?, num_personais = ? WHERE id_acd = ?";

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, a.getNome());
            stmt.setString(2, a.getEndereco());
            stmt.setString(3, a.getN_contato());
            stmt.setDouble(4, a.getMensalidade());
            stmt.setString(5, a.getAtividadesOfertadas());
            stmt.setInt(6, a.getNumPersonais());
            stmt.setInt(7, a.getIdAcd());
            stmt.executeUpdate();
        }
    }

    public void excluir(int idAcd) throws SQLException {
        String sql = "DELETE FROM academia WHERE id_acd = ?";

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idAcd);
            stmt.executeUpdate();
        }
    }

    public Academia buscarPorNome(String nome) throws SQLException {
        String sql = SELECT_BASE + "WHERE a.nome = ?";

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, nome);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        }
        return null;
    }

    public Academia buscarPorId(int idAcd) throws SQLException {
        String sql = SELECT_BASE + "WHERE a.id_acd = ?";

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idAcd);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        }
        return null;
    }

    public ArrayList<Academia> listarTodas() throws SQLException {
        ArrayList<Academia> academias = new ArrayList<>();
        String sql = SELECT_BASE + "ORDER BY a.nome";

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                academias.add(mapear(rs));
            }
        }
        return academias;
    }

    public int contar() throws SQLException {
        String sql = "SELECT COUNT(*) FROM academia";

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    private Academia mapear(ResultSet rs) throws SQLException {
        return new Academia(
            rs.getInt("id_acd"),
            rs.getString("nome"),
            rs.getString("endereco"),
            rs.getString("contato"),
            rs.getDouble("mensalidade"),
            rs.getInt("num_personais"),
            rs.getString("atividades_ofertadas"),
            rs.getDouble("media_avaliacao")
        );
    }
}
