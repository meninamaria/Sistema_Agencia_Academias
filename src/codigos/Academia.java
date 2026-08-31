package codigos;

public class Academia {

    private int idAcd;
    private String nome;
    private String endereco;
    private String n_contato;
    private double mensalidade;
    private String atividadesOfertadas;
    private int numPersonais;
    private double avaliacao;

    // Construtor usado quando os dados vêm do banco (id já existe)
    public Academia(int idAcd, String nome, String endereco, String n_contato, double mensalidade,
                     int numPersonais, String atividadesOfertadas, double avaliacao) {
        this.idAcd = idAcd;
        this.nome = nome;
        this.endereco = endereco;
        this.n_contato = n_contato;
        this.mensalidade = mensalidade;
        this.numPersonais = numPersonais;
        this.atividadesOfertadas = atividadesOfertadas;
        this.avaliacao = avaliacao;
    }

    // Construtor usado para cadastrar uma nova academia (id ainda não existe, será gerado pelo banco)
    public Academia(String nome, String endereco, String n_contato, double mensalidade,
                     int numPersonais, String atividadesOfertadas) {
        this.idAcd = 0; // definido depois do INSERT, pelo AcademiaDAO
        this.nome = nome;
        this.endereco = endereco;
        this.n_contato = n_contato;
        this.mensalidade = mensalidade;
        this.numPersonais = numPersonais;
        this.atividadesOfertadas = atividadesOfertadas;
        this.avaliacao = 0.0;
    }

    public int getIdAcd() {
        return this.idAcd;
    }

    // Usado apenas pelo AcademiaDAO logo após o INSERT, para setar o id gerado pelo AUTO_INCREMENT
    public void setIdAcd(int idAcd) {
        this.idAcd = idAcd;
    }

    public String getNome() {
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

    // Agora representa a MÉDIA das avaliações (carregada do banco), não mais um valor "setado" na mão
    public double getAvaliacao() {
        return this.avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }

    @Override
    public String toString() {
        return "Id: " + this.idAcd + " | Nome: " + this.nome + " | Endereco: " + this.endereco +
                " | Numero para contato: " + this.n_contato + " | Mensalidade: " + this.mensalidade +
                " | Atividades ofertadas: " + this.atividadesOfertadas + " | Numero de Personais: " +
                this.numPersonais + " | Avaliacao: " + this.avaliacao;
    }
}
