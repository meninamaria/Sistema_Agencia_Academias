package app;

public class GerenciamentoSistema {
    private Academia academia[];
    private Clientes clientes[];
    private int idAcd;
    private int idCli;
    static public int IDACD = 100;


    public GerenciamentoSistema(){
        this.academia = new Academia[50];
        this.clientes = new Clientes[50];

        this.idAcd = 0;
        this.idCli = 0;
    }

    public void CadastrarCliente(String nome, String cpf, int idade, String restricaoMed){
        //Verificar se usuario ja tem cpf cadastrado!!

        //Se não
        Clientes c = new Clientes(nome, cpf, idade, restricaoMed);
        this.clientes[this.idCli] = c;

        System.out.println("Usuario cadastrado com sucesso!!");


    }

    public void CadastrarAcademia(String nome, String endereco, String n_contato, float mensalidade, String atividadesOfertadas, int num_personais){
        Academia a = new Academia(nome, endereco, n_contato, mensalidade, atividadesOfertadas, num_personais);
        ++this.idAcd;

        System.out.println("Academia cadastrada com sucesso!!");
    }

}

