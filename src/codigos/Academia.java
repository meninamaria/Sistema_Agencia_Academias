package codigos;


public class Academia {
    //Atributos da academia
   

    private int idAcd;
    private String nome;
    private String endereco;
    private String n_contato;
    private double mensalidade;
    private String atividadesOfertadas;
    private int numPersonais;
    private double avaliacao;
    private String login;
    private String senha;

    // aqui é o construtor para carregar o academiasCadastradas.txt
    public Academia(int idAcd, String nome, String endereco, String n_contato, double mensalidade, int numPersonais, String atividadesOfertadas){
        
        this.idAcd = idAcd;
        this.nome = nome;
        this.n_contato = n_contato;
        this.endereco = endereco;
        this.mensalidade = mensalidade;
        this.numPersonais = numPersonais;
        this.atividadesOfertadas = atividadesOfertadas;
        this.avaliacao = avaliacao;
    }
    
    // aqui é o construtor para add uma nova acadamia
    public Academia(String nome, String endereco, String n_contato, double mensalidade, int numPersonais, String atividadesOfertadas){
        
        this.idAcd = GerenciamentoSistema.getPROX_ID_acad();
        this.nome = nome;
        this.n_contato = n_contato;
        this.endereco = endereco;
        this.mensalidade = mensalidade;
        this.numPersonais = numPersonais;
        this.atividadesOfertadas = atividadesOfertadas;
        this.avaliacao = 0.0;
    }

    public int getIdAcd() {
        return this.idAcd;
    }

    public String getNome(){
        return this.nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return this.endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getN_contato() {
        return this.n_contato;
    }
    public void setN_contato(String n_contato) {
        this.n_contato = n_contato;
    }

    public double getMensalidade() {
        return this.mensalidade;
    }
    public void setMensalidade(double mensalidade) {
        this.mensalidade = mensalidade;
    }

    public String getAtividadesOfertadas() {
        return this.atividadesOfertadas;
    }
    public void setAtividadesOfertadas(String atividadesOfertadas) {
        this.atividadesOfertadas = atividadesOfertadas;
    }

    public int getNumPersonais() {
        return this.numPersonais;
    }
    public void setNumPersonais(int numPersonais) {
        this.numPersonais = numPersonais;
    }

    public double getAvaliacao() {
        return this.avaliacao;
    }
    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }
    

    public String toString(){
        return "Id: " + this.idAcd + " | Nome: " + this.nome + " | Endereco: " + this.endereco + " | Numero para contato: " + this.n_contato + " | Mensalidade: " + this.mensalidade + " | Atividades ofertadas: " + this.atividadesOfertadas + " | Numero de Personais: " + this.numPersonais + " | Avaliacao: " + this.avaliacao;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

        public String toFileString() {
        return this.idAcd + ";" + this.nome + ";" + this.endereco + ";" +
                this.n_contato + ";" + this.mensalidade + ";" + this.atividadesOfertadas + ";" + this.numPersonais + ";" + this.avaliacao;
    }

    public static Academia fromFileString(String linha) {
        String[] dados = linha.split(";");
        if (dados.length != 8) {
            System.out.println("Linha com formato inválido: " + linha);
            return null;
        }

        try {
            return new Academia(Integer.parseInt(dados[0].trim()), dados[1].trim(), dados[2].trim(), dados[3].trim(), Double.parseDouble(dados[4].trim()), Integer.parseInt(dados[6].trim()), dados[5].trim());

        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter dados: " + linha);
            return null;
        }
    }
}
