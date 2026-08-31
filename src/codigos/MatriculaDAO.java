package codigos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MatriculaDAO {

    // Cancela a matrícula ativa atual (se existir) e cria uma nova, na mesma transação
    public void matricular(int idCli, int idAcd) throws SQLException {
        try (Connection conexao = ConexaoBD.conectar()) {
            conexao.setAutoCommit(false);
            try {
                cancelarAtiva(conexao, idCli);

                String sqlInsert = "INSERT INTO matricula (id_cli, id_acd) VALUES (?, ?)";
                try (PreparedStatement stmt = conexao.prepareStatement(sqlInsert)) {
                    stmt.setInt(1, idCli);
                    stmt.setInt(2, idAcd);
                    stmt.executeUpdate();
                }

                conexao.commit();
            } catch (SQLException e) {
                conexao.rollback();
                throw e;
            } finally {
                conexao.setAutoCommit(true);
            }
        }
    }

    private void cancelarAtiva(Connection conexao, int idCli) throws SQLException {
        String sql = "UPDATE matricula SET status = 'CANCELADA', data_cancelamento = NOW() " +
                      "WHERE id_cli = ? AND status = 'ATIVA'";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idCli);
            stmt.executeUpdate();
        }
    }

    // Retorna a academia em que o cliente está matriculado atualmente (ou null, se nenhuma)
    public Academia buscarAcademiaAtual(int idCli) throws SQLException {
        String sql =
            "SELECT a.id_acd, a.nome, a.endereco, a.contato, a.mensalidade, a.atividades_ofertadas, " +
            "a.num_personais, COALESCE(v.media_avaliacao, 0) AS media_avaliacao " +
            "FROM matricula m " +
            "JOIN academia a ON a.id_acd = m.id_acd " +
            "LEFT JOIN vw_academia_avaliacao v ON v.id_acd = a.id_acd " +
            "WHERE m.id_cli = ? AND m.status = 'ATIVA'";

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idCli);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
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
        }
        return null;
    }
}
