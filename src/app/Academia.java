package app;

public class Academia {
    //Atributos da academia

    private String nome;
    private String endereco;
    private String n_contato;
    private float mensalidade;
    private String atividadesOfertadas;
    private int numPersonais;
    private String avaliacao;


    public Academia(String nome, String endereco, String n_contato, float mensalidade, String atividadesOfertadas, int numPersonais){
        this.idAcd = GerenciamentoSistema.IDACD;
        ++GerenciamentoSistema.IDACD;

        this.nome = nome;
        this.endereco = endereco;
        this.n_contato = n_contato;
        this.mensalidade = mensalidade;
        this.atividadesOfertadas = atividadesOfertadas;
        this.numPersonais = numPersonais;
        this.avaliacao = avaliacao;

    }

    Academia() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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

    public float getMensalidade() {
        return mensalidade;
    }
    public void setMensalidade(float mensalidade) {
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

    public String getAvaliacao() {
        return avaliacao;
    }
    public void setAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String toSting(){
        return "Nome: " + this.nome + "Endereco: " + this.endereco + "Numero para contato: " + this.n_contato + "Mensalidade: " + this.mensalidade + "Atividades ofertadas: " + this.atividadesOfertadas + "Numero de Personais: " + this.numPersonais + "avaliacao: " + this.avaliacao;
    }
}
