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
        ++this.idCli;

    }

    public void CadastrarAcademia(String nome, String n_contato, String endereco, double mensalidade, int num_personais, String atividadesOfertadas){
        Academia a = new Academia(nome, n_contato, endereco, mensalidade, num_personais, atividadesOfertadas);
        this.academia[this.idAcd] = a;
        ++this.idAcd;

    }

    public Academia buscarAcademia(int busca) {
        int i;

        for (i = 0; i < this.idAcd; ++i) {
            if (busca == this.academia[i].getIdAcd()) {
                return this.academia[i];
            }
        }
        return null;
    }

    public String listarAcademias() {
        int i;
        String mensagem = "";

        for (i = 0; i < this.idAcd; ++i) {
            mensagem = mensagem + this.academia[i].toString() + "\n";
        }

        return mensagem;
    }

    public void excluirAcademia(int busca) {
        int i;

        for (i = 0; i < this.idAcd; ++i) {
            if (busca == this.academia[i].getIdAcd()) {
                if (i == (this.idAcd - 1)) {  // remover o último elemento do vetor
                    this.academia[i] = null;
                } else {
                    int j;
                    for (j = i; j < this.idAcd-1; ++j) {
                        this.academia[j] = this.academia[j+1];
                    }
                    this.academia[this.idAcd-1] = null;
                }
                --this.idAcd;
            }
        }
    }

    public void avaliarAcademia(int busca, double avali) {
        int i;

        for (i = 0; i < this.idAcd; ++i) {
            if (busca == this.academia[i].getIdAcd()) {
                this.academia[i].setAvaliacao(avali);
            }
        }
    }

    public Clientes buscarCliente(String cpf) {
        int i;

        for (i = 0; i < this.idCli; ++i) {
            if (cpf.equalsIgnoreCase(this.clientes[i].getCpf())) { // PERGUNTAR PRA RAQUEL SE É MELHOR O IGNORE CASE OU NEN
                return this.clientes[i];
            }
        }

        return null;
    }


    public String imprimirDadosCliente(String cpf) {
        int i;

        for (i = 0; i < this.idCli; ++i) {
            if (cpf.equalsIgnoreCase(this.clientes[i].getCpf())) {
                return this.clientes[i].toString();
            }
        }
        return null;
    }

    public String listarAcademias_Preco(int opcao) {
        int i;
        String mensagem = "";

        if (opcao == 1) {
            for (i = 0; i < idAcd; ++i) {
                if (this.academia[i].getMensalidade() <= 49.99) {
                    mensagem = mensagem + this.academia[i].toString() + "\n";
                }
            }
        } else if (opcao == 2) {
            for (i = 0; i < idAcd; ++i) {
                if ((this.academia[i].getMensalidade() >= 50.00) && (this.academia[i].getMensalidade() <= 99.99)) {
                    mensagem = mensagem + this.academia[i].toString() + "\n";
                }
            }
        } else {
            for (i = 0; i < idAcd; ++i) {
                if (this.academia[i].getMensalidade() >= 100.00) {
                    mensagem = mensagem + this.academia[i].toString() + "\n";
                }
            }
        }
        return mensagem;
    }

    public String listarAcademias_Avaliacao(int opcao) {
        int i;
        String mensagem = "";

        switch (opcao) {
            case 1:
                for (i = 0; i < this.idAcd; ++i) {
                    if ((this.academia[i].getAvaliacao() >= 1.0) && (this.academia[i].getAvaliacao() < 2.0))
                        mensagem = mensagem + this.academia[i].toString() + "\n";
                }
                break;
            case 2:
                for (i = 0; i < this.idAcd; ++i) {
                    if ((this.academia[i].getAvaliacao() >= 2.0) && (this.academia[i].getAvaliacao() < 3.0))
                        mensagem = mensagem + this.academia[i].toString() + "\n";
                }
                break;
            case 3:
                for (i = 0; i < this.idAcd; ++i) {
                    if ((this.academia[i].getAvaliacao() >= 3.0) && (this.academia[i].getAvaliacao() < 4.0))
                        mensagem = mensagem + this.academia[i].toString() + "\n";
                }
                break;
            case 4:
                for (i = 0; i < this.idAcd; ++i) {
                    if ((this.academia[i].getAvaliacao() >= 4.0) && (this.academia[i].getAvaliacao() < 5.0))
                        mensagem = mensagem + this.academia[i].toString() + "\n";
                }
                break;
            case 5:
                for (i = 0; i < this.idAcd; ++i) {
                    if (this.academia[i].getAvaliacao() >= 5.0) {
                        mensagem = mensagem + this.academia[i].toString() + "\n";
                    }
                }
                break;
        }

        return mensagem;
    }
}

