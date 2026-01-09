package app;

public class Clientes {
    //atributos cliente
    private String nome;
    private int idade;
    private String cpf;
    private String restricaoMed;

    public Clientes (String nome, String cpf, int idade, String restricaoMed){
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.restricaoMed = restricaoMed;
    }

    Clientes() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRestricaoMed() {
        return restricaoMed;
    }

    public void setRestricaoMed(String restricaoMed) {
        this.restricaoMed = restricaoMed;
    }

}