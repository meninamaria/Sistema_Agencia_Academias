package codigos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AvaliacaoDAO {

    public void inserir(int idCli, int idAcd, double nota) throws SQLException {
        String sql = "INSERT INTO avaliacao (id_cli, id_acd, nota) VALUES (?, ?, ?)";

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idCli);
            stmt.setInt(2, idAcd);
            stmt.setDouble(3, nota);
            stmt.executeUpdate();
        }
    }
}
