package codigos;

public class Cliente {
    //atributos cliente
    private int idCli;
    private String nome;
    private int idade;
    private String cpf;
    private String login;
    private String senha;
    private String restMedica;
    private Academia academia;

    // aqui é o construtor para carregar o clientesCadastrados.txt
    public Cliente (int idCli, String nome, String cpf, int idade, String login, String senha, String restMedica){
        this.idCli = idCli;
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.login = login;
        this.senha = senha;
        this.restMedica = restMedica;
       // this.clienteLogado = clienteLogado;
        
    }
    
    // aqui é o construtor para add uma nova acadamia
    public Cliente (String nome, String cpf, int idade, String login, String senha, String restMedica){
        this.idCli = GerenciamentoSistema.getPROX_ID_cli();
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.login = login;
        this.senha = senha;
        this.restMedica = restMedica;
       // this.clienteLogado = clienteLogado;
        
    }

    public int getIdCli() {
        return this.idCli;
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
    public String toString(){
        return "Nome: " + this.nome + " | CPF: " + this.cpf + " | Idade: " + this.idade + " | Restricao Medica: " + this.restMedica;
    }

            public String toFileString() {
        return this.idCli+ ";" + this.nome + ";" + this.cpf + ";" +
                this.idade + ";" + this.login + ";" + this.senha + ";" + this.restMedica;
    }

    public static Cliente fromFileString(String linha) {
        String[] dados = linha.split(";");
        if (dados.length != 7) {
            System.out.println("Linha com formato inválido: " + linha);
            return null;
        }

        try {
            return new Cliente(Integer.parseInt(dados[0].trim()), dados[1].trim(), dados[2].trim(), Integer.parseInt(dados[3].trim()), dados[4].trim(), dados[5].trim(), dados[6].trim());

        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter dados: " + linha);
            return null;
        }
    }
   
}