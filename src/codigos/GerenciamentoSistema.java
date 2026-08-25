package codigos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class GerenciamentoSistema {

    private ArrayList<Academia> academias;
    private ArrayList<Cliente> clientes;
    private Cliente clienteLogado;
    private static int PROX_ID_acad = 100;
    private static int PROX_ID_cli = 1;
    private final String arquivo_acad = "academiasCadastradas.txt";
    private final String arquivo_cli = "clientesCadastrados.txt";

    
    // Singleton - única instância
    private static GerenciamentoSistema instancia;
    
    // Construtor privado para Singleton
    public GerenciamentoSistema() {
        academias = new ArrayList<>();
        clientes = new ArrayList<>();
        clienteLogado = null;
        carregarDados_acad();
        carregarDados_cli();

    }
    
    // Único ponto de acesso ao Singleton
    public static GerenciamentoSistema getInstance() {
        if (instancia == null) {
            instancia = new GerenciamentoSistema();
        }
        return instancia;
    }
    
    public static int getPROX_ID_acad() {
        return PROX_ID_acad++;
    }
    
    public static void setPROX_ID_acad(int id_acad) {
        if (id_acad > PROX_ID_acad) {
            PROX_ID_acad = id_acad;
        }
    }

    public static int getPROX_ID_cli() {
        return PROX_ID_cli;
    }

    public static void setPROX_ID_cli(int id_cli) {
        if (id_cli > PROX_ID_acad) {
            PROX_ID_acad = id_cli;
        }
    }
    

    public void cadastrarCliente(Cliente cliente) {
        clientes.add(cliente);
        this.clienteLogado = cliente;
        salvarDados_cli();
    }

    public Cliente buscarCliente(String cpf) {
        for (Cliente c : clientes) {
            if (c.getCpf().equals(cpf)) {
                return c;
            }
        }
        return null;
    }

    public Cliente loginCliente(String login, String senha) {
        
        for (Cliente c : clientes) {
            if (c.getLogin().equalsIgnoreCase(login) && c.getSenha().equals(senha)) {
                clienteLogado = c; // Define o cliente logado
                return c;
            }
        }
        
        clienteLogado = null;
        return null;
    }
    
    // Método para logout
    public void logoutCliente() {
        clienteLogado = null;
    }

    public Cliente buscarCpfCliente(String cpf) {
        for (Cliente c: this.clientes) {
            if (cpf.equals(c.getCpf())) {
                return c;
            }
        }
        return null; 
    }

    public String imprimirDadosCliente(String cpf) {
        for (Cliente c: this.clientes){
            if (cpf.equals(c.getCpf())){
                return c.toString();
            }
        }
        return null;
    }


    public void cadastrarAcademia(String nome,String endereco, String n_contato, double mensalidade,
                                  int num_personais, String atividadesOfertadas) {

        Academia a = new Academia(nome, endereco, n_contato, mensalidade, 
                                 num_personais, atividadesOfertadas);
        academias.add(a);
        salvarDados_acad();
    }

    public String listarAcademias() {
        StringBuilder sb = new StringBuilder();
        for (Academia a : academias) {
            sb.append(a).append("\n");
        }
        return sb.toString();
    }
    
    public Academia buscarAcademia(String buscarNome) {
        for (Academia a : academias) {
            if (buscarNome.equalsIgnoreCase(a.getNome())) {
                return a;
            }
        }
        return null;
    }  
    
    public void atualizarAcademia(String busca, String nome, String endereco, 
                                  String n_contato, double mensalidade, 
                                  String atividadesOfertadas, int nPersonais) {
        for (Academia a: academias) {
            if (busca.equalsIgnoreCase(a.getNome())) {
                a.setNome(nome);
                a.setEndereco(endereco);
                a.setN_contato(n_contato);
                a.setMensalidade(mensalidade);
                a.setAtividadesOfertadas(atividadesOfertadas);
                a.setNumPersonais(nPersonais);
                salvarDados_acad();
            }
        }
    }
    
    public void excluirAcademia(String busca) {
        Academia remover = null;
        for (Academia a : academias) {
            if (busca.equalsIgnoreCase(a.getNome())) {
                remover = a;
                break;
            }
        }
        if (remover != null) {
            academias.remove(remover);
             salvarDados_acad();

        }
    }

    public boolean avaliarAcademia(Academia academia, double nota) {
        if (academia == null) {
            return false;
        }
        if (nota < 0.0 || nota > 5.0) {
            return false;
        }
        academia.setAvaliacao(nota);
        //NÃO TA SALVANDO A AVALIAÇÃO QUANDO O SISTEMA INICIA DE NOVO AJEITAR!!!!!!!!!!!!!!!!!!!
        salvarDados_acad();
        return true;
    }
    
    public boolean matricularUsuario(Academia academia, Cliente cliente, String senhaConfirmacao){
    
    // 1. Verificar se a senha fornecida corresponde ao cliente
    if (!verificarIdentidade(cliente.getCpf(), senhaConfirmacao)) {
        JOptionPane.showMessageDialog(null, 
            "Senha incorreta! Matrícula não autorizada.",
            "Erro de Autenticação", 
            JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    // 2. Resto do código atual...
    if (cliente.getAcademia() != null) {
        Academia academiaAtual = cliente.getAcademia();
        
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
    
    cliente.setAcademia(academia);    
    salvarDados_acad();
    
    // Atualiza o cliente na lista
    for (int i = 0; i < clientes.size(); i++) {
        if (clientes.get(i).getCpf().equals(cliente.getCpf())) {
            clientes.set(i, cliente);
            break;
        }
    }
    
    System.out.println("SUCESSO: " + cliente.getNome() + " matriculado em " + academia.getNome());
    
    JOptionPane.showMessageDialog(null, 
        "Matrícula realizada com sucesso!\n" + 
        "Bem-vindo à " + academia.getNome(), 
        "Sucesso", 
        JOptionPane.INFORMATION_MESSAGE); 
    return true;

    }
    
    public boolean verificarIdentidade(String cpf, String senha) {
    Cliente cliente = buscarCliente(cpf);
    if (cliente == null) return false;
    return cliente.getSenha().equals(senha);
}

    
    public boolean autenticarAdmin(String login, String senha) {
        String loginAdmin = "adminCoelho";
        String senhaAdmin = "@meury123";
            if (loginAdmin.equals(login) && senhaAdmin.equals(senha)) {
                return true;
            }
        return false;
    }
    
    
    public Cliente getClienteLogado() {
        return clienteLogado;
    }
    
    public void setClienteLogado(Cliente clienteLogado) {
        this.clienteLogado = clienteLogado;
    }
    
    public ArrayList<Academia> getAcademias() {
        return academias;
    }
    
    public ArrayList<Cliente> getClientes() {
        return clientes;
    }
    
    public int getQuantidadeAcademias() {
        return academias.size();
    }
    
    public int getQuantidadeClientes() {
        return clientes.size();
    }
    
    
    private void carregarDados_acad() {
        File dadosAcad = new File(arquivo_acad);

        if (!dadosAcad.exists()) {
            System.out.println("Arquivo de dados não encontrado. Iniciando sistema sem nenhum cadastro");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo_acad))) {
            String linha;
            int maiorId = 99;

            while ((linha = br.readLine()) != null) {
                Academia academia = Academia.fromFileString(linha);
                if (academia != null) {
                    academias.add(academia);

                    if (academia.getIdAcd()> maiorId) {
                        maiorId = academia.getIdAcd();
                    }
                }
            }

            if (maiorId >= 100) {
                setPROX_ID_acad(maiorId + 1);
            }

            System.out.println("Dados carregados com sucesso! " + academias.size() + " academias encontrados.");

        } catch (IOException e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro ao processar arquivo: " + e.getMessage());
        }

    }
    
    private void carregarDados_cli() {
        File dadosCli= new File(arquivo_cli);
        
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo_cli))) {
            String linha;
            int maiorId = 0;

            while ((linha = br.readLine()) != null) {
                Cliente cliente = Cliente.fromFileString(linha);
                if (cliente != null) {
                    clientes.add(cliente);

                    if (cliente.getIdCli()> maiorId) {
                        maiorId = cliente.getIdCli();
                    }
                }
            }

            if (maiorId >= 1) {
                setPROX_ID_cli(maiorId + 1);
            }

            System.out.println("Dados carregados com sucesso! " + clientes.size() + " clientes encontrados.");

        } catch (IOException e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro ao processar arquivo: " + e.getMessage());
        }
    }
        
    private void salvarDados_acad() {
        try (BufferedWriter escrever_arqAcad = new BufferedWriter(new FileWriter(arquivo_acad))) {
            for (Academia a : academias) {
                escrever_arqAcad.write(a.toFileString());
                escrever_arqAcad.newLine();
            }
            System.out.println("Dados salvos automaticamente!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }
    
        private void salvarDados_cli() {
        try (BufferedWriter escrever_arqCli = new BufferedWriter(new FileWriter(arquivo_cli))) {
            for (Cliente c: clientes) {
                escrever_arqCli.write(c.toFileString());
                escrever_arqCli.newLine();
            }
            System.out.println("Dados salvos automaticamente!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }
    
}  
    
