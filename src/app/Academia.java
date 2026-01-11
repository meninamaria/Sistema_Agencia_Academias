package app;

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


    public Academia(String nome, String n_contato, String endereco, double mensalidade, int numPersonais, String atividadesOfertadas){
        this.idAcd = GerenciamentoSistema.IDACD;
        ++GerenciamentoSistema.IDACD;

        this.nome = nome;
        this.n_contato = n_contato;
        this.endereco = endereco;
        this.mensalidade = mensalidade;
        this.numPersonais = numPersonais;
        this.atividadesOfertadas = atividadesOfertadas;
        this.avaliacao = avaliacao;

    }

    public int getIdAcd() {
        return idAcd;
    }

    public String getNome(){
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getN_contato() {
        return n_contato;
    }
    public void setN_contato(String n_contato) {
        this.n_contato = n_contato;
    }

    public double getMensalidade() {
        return mensalidade;
    }
    public void setMensalidade(double mensalidade) {
        this.mensalidade = mensalidade;
    }

    public String getAtividadesOfertadas() {
        return atividadesOfertadas;
    }
    public void setAtividadesOfertadas(String atividadesOfertadas) {
        this.atividadesOfertadas = atividadesOfertadas;
    }

    public int getNumPersonais() {
        return numPersonais;
    }
    public void setNumPersonais(int numPersonais) {
        this.numPersonais = numPersonais;
    }

    public double getAvaliacao() {
        return avaliacao;
    }
    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String toString(){
        return "Id: " + this.idAcd + " | Nome: " + this.nome + " | Endereco: " + this.endereco + " | Numero para contato: " + this.n_contato + " | Mensalidade: " + this.mensalidade + " | Atividades ofertadas: " + this.atividadesOfertadas + " | Numero de Personais: " + this.numPersonais + " | Avaliacao: " + this.avaliacao;
    }
}
