package codigos;

public class Cliente {

    private int idCli;
    private String nome;
    private int idade;
    private String cpf;
    private String login;
    private String senha;
    private String restMedica;
    private Academia academia; // academia atual (matrícula ATIVA), carregada sob demanda

    // Construtor usado quando os dados vêm do banco (id já existe)
    public Cliente(int idCli, String nome, String cpf, int idade, String login, String senha, String restMedica) {
        this.idCli = idCli;
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.login = login;
        this.senha = senha;
        this.restMedica = restMedica;
    }

    // Construtor usado para cadastrar um novo cliente (id ainda não existe, será gerado pelo banco)
    public Cliente(String nome, String cpf, int idade, String login, String senha, String restMedica) {
        this.idCli = 0; // definido depois do INSERT, pelo ClienteDAO
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.login = login;
        this.senha = senha;
        this.restMedica = restMedica;
    }

    public int getIdCli() {
        return this.idCli;
    }

    // Usado apenas pelo ClienteDAO logo após o INSERT, para setar o id gerado pelo AUTO_INCREMENT
    public void setIdCli(int idCli) {
        this.idCli = idCli;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return this.idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRestMedica() {
        return this.restMedica;
    }

    public void setRestMedica(String restMedica) {
        this.restMedica = restMedica;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Academia getAcademia() {
        return academia;
    }

    public void setAcademia(Academia academia) {
        this.academia = academia;
    }

    @Override
    public String toString() {
        return "Nome: " + this.nome + " | CPF: " + this.cpf + " | Idade: " + this.idade +
                " | Restricao Medica: " + this.restMedica;
    }
}
