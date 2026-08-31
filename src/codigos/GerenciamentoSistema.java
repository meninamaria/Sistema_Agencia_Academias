package codigos;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class GerenciamentoSistema {

    private Cliente clienteLogado;

    private final AcademiaDAO academiaDAO = new AcademiaDAO();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final MatriculaDAO matriculaDAO = new MatriculaDAO();
    private final AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();

    // Singleton - única instância
    private static GerenciamentoSistema instancia;

    public GerenciamentoSistema() {
        clienteLogado = null;
    }

    // Único ponto de acesso ao Singleton
    public static GerenciamentoSistema getInstance() {
        if (instancia == null) {
            instancia = new GerenciamentoSistema();
        }
        return instancia;
    }

    public void cadastrarCliente(Cliente cliente) {
        try {
            clienteDAO.inserir(cliente);
            this.clienteLogado = cliente;
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
            JOptionPane.showMessageDialog(null,
                "Não foi possível cadastrar o cliente. Verifique se o CPF ou login já existem.",
                "Erro ao Cadastrar", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Cliente buscarCliente(String cpf) {
        try {
            return clienteDAO.buscarPorCpf(cpf);
        } catch (SQLException e) {
            System.out.println("Erro ao buscar cliente: " + e.getMessage());
            return null;
        }
    }

    public Cliente loginCliente(String login, String senha) {
        try {
            Cliente c = clienteDAO.buscarPorLogin(login);
            if (c != null && c.getSenha().equals(senha)) {
                clienteLogado = c;
                return c;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao efetuar login: " + e.getMessage());
        }
        clienteLogado = null;
        return null;
    }

    // Método para logout
    public void logoutCliente() {
        clienteLogado = null;
    }

    public Cliente buscarCpfCliente(String cpf) {
        return buscarCliente(cpf);
    }

    public String imprimirDadosCliente(String cpf) {
        Cliente c = buscarCpfCliente(cpf);
        return (c != null) ? c.toString() : null;
    }

    public void cadastrarAcademia(String nome, String endereco, String n_contato, double mensalidade,
                                   int num_personais, String atividadesOfertadas) {
        try {
            Academia a = new Academia(nome, endereco, n_contato, mensalidade,
                                       num_personais, atividadesOfertadas);
            academiaDAO.inserir(a);
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar academia: " + e.getMessage());
            JOptionPane.showMessageDialog(null,
                "Não foi possível cadastrar a academia.",
                "Erro ao Cadastrar", JOptionPane.ERROR_MESSAGE);
        }
    }

    public String listarAcademias() {
        StringBuilder sb = new StringBuilder();
        try {
            for (Academia a : academiaDAO.listarTodas()) {
                sb.append(a).append("\n");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar academias: " + e.getMessage());
        }
        return sb.toString();
    }

    public Academia buscarAcademia(String buscarNome) {
        try {
            return academiaDAO.buscarPorNome(buscarNome);
        } catch (SQLException e) {
            System.out.println("Erro ao buscar academia: " + e.getMessage());
            return null;
        }
    }

    public void atualizarAcademia(String busca, String nome, String endereco,
                                   String n_contato, double mensalidade,
                                   String atividadesOfertadas, int nPersonais) {
        try {
            Academia a = academiaDAO.buscarPorNome(busca);
            if (a != null) {
                a.setNome(nome);
                a.setEndereco(endereco);
                a.setN_contato(n_contato);
                a.setMensalidade(mensalidade);
                a.setAtividadesOfertadas(atividadesOfertadas);
                a.setNumPersonais(nPersonais);
                academiaDAO.atualizar(a);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar academia: " + e.getMessage());
            JOptionPane.showMessageDialog(null,
                "Não foi possível atualizar a academia.",
                "Erro ao Atualizar", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void excluirAcademia(String busca) {
        try {
            Academia a = academiaDAO.buscarPorNome(busca);
            if (a != null) {
                academiaDAO.excluir(a.getIdAcd());
            }
        } catch (SQLException e) {
            System.out.println("Erro ao excluir academia: " + e.getMessage());
            JOptionPane.showMessageDialog(null,
                "Não foi possível excluir a academia. Ela ainda possui clientes matriculados.",
                "Erro ao Excluir", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean avaliarAcademia(Academia academia, double nota) {
        if (academia == null) {
            return false;
        }
        if (nota < 0.0 || nota > 5.0) {
            return false;
        }
        if (clienteLogado == null) {
            JOptionPane.showMessageDialog(null,
                "É necessário estar logado para avaliar uma academia.",
                "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            avaliacaoDAO.inserir(clienteLogado.getIdCli(), academia.getIdAcd(), nota);
            // Atualiza a média em memória, já recalculada pela view vw_academia_avaliacao
            Academia atualizada = academiaDAO.buscarPorId(academia.getIdAcd());
            if (atualizada != null) {
                academia.setAvaliacao(atualizada.getAvaliacao());
            }
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao avaliar academia: " + e.getMessage());
            return false;
        }
    }

    public boolean matricularUsuario(Academia academia, Cliente cliente, String senhaConfirmacao) {

        // 1. Verificar se a senha fornecida corresponde ao cliente
        if (!verificarIdentidade(cliente.getCpf(), senhaConfirmacao)) {
            JOptionPane.showMessageDialog(null,
                "Senha incorreta! Matrícula não autorizada.",
                "Erro de Autenticação",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            // 2. Verifica se o cliente já está matriculado em outra academia
            Academia academiaAtual = matriculaDAO.buscarAcademiaAtual(cliente.getIdCli());

            if (academiaAtual != null) {
                int resposta = JOptionPane.showConfirmDialog(null,
                    "Você já está matriculado na academia:\n" +
                    academiaAtual.getNome() + "\n\n" +
                    "Deseja trocar para:\n" +
                    academia.getNome() + "?",
                    "Trocar de Academia",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

                if (resposta != JOptionPane.YES_OPTION) {
                    return false;
                }
            }

            matriculaDAO.matricular(cliente.getIdCli(), academia.getIdAcd());
            cliente.setAcademia(academia);

            System.out.println("SUCESSO: " + cliente.getNome() + " matriculado em " + academia.getNome());

            JOptionPane.showMessageDialog(null,
                "Matrícula realizada com sucesso!\n" +
                "Bem-vindo à " + academia.getNome(),
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao matricular usuário: " + e.getMessage());
            JOptionPane.showMessageDialog(null,
                "Não foi possível concluir a matrícula.",
                "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean verificarIdentidade(String cpf, String senha) {
        Cliente cliente = buscarCliente(cpf);
        if (cliente == null) {
            return false;
        }
        return cliente.getSenha().equals(senha);
    }

    public boolean autenticarAdmin(String login, String senha) {
        String loginAdmin = "adminCoelho";
        String senhaAdmin = "@meury123";
        return loginAdmin.equals(login) && senhaAdmin.equals(senha);
    }

    public Cliente getClienteLogado() {
        return clienteLogado;
    }

    public void setClienteLogado(Cliente clienteLogado) {
        this.clienteLogado = clienteLogado;
    }

    public ArrayList<Academia> getAcademias() {
        try {
            return academiaDAO.listarTodas();
        } catch (SQLException e) {
            System.out.println("Erro ao listar academias: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public ArrayList<Cliente> getClientes() {
        try {
            return clienteDAO.listarTodos();
        } catch (SQLException e) {
            System.out.println("Erro ao listar clientes: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public int getQuantidadeAcademias() {
        try {
            return academiaDAO.contar();
        } catch (SQLException e) {
            System.out.println("Erro ao contar academias: " + e.getMessage());
            return 0;
        }
    }

    public int getQuantidadeClientes() {
        try {
            return clienteDAO.contar();
        } catch (SQLException e) {
            System.out.println("Erro ao contar clientes: " + e.getMessage());
            return 0;
        }
    }
}
