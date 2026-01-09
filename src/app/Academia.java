package app;

public class Academia {
    private String nomeAcad;
    private String ruaAcad;
    private String bairroAcad;
    private int numAcad;
    private String telefoneAcad;
    private double avaliacaoAcad;
    private int num_personais;
    //private String atividades_ofertadas;

    public Academia(String nomeAcad, String ruaAcad, String bairroAcad, int numAcad, String telefoneAcad, double avaliacaoAcad, int num_personais) {
        this.nomeAcad = nomeAcad;
        this.ruaAcad = ruaAcad;
        this.bairroAcad = bairroAcad;
        this.numAcad = numAcad;
        this.telefoneAcad = telefoneAcad;
        this.avaliacaoAcad = avaliacaoAcad;
        this.num_personais = num_personais;
    }

    public String getNomeAcad() {
        return nomeAcad;
    }

    public void setNomeAcad(String nomeAcad) {
        this.nomeAcad = nomeAcad;
    }

    public String getRuaAcad() {
        return ruaAcad;
    }

    public void setRuaAcad(String ruaAcad) {
        this.ruaAcad = ruaAcad;
    }

    public String getBairroAcad() {
        return bairroAcad;
    }

    public void setBairroAcad(String bairroAcad) {
        this.bairroAcad = bairroAcad;
    }

    public int getNumAcad() {
        return numAcad;
    }

    public void setNumAcad(int numAcad) {
        this.numAcad = numAcad;
    }

    public String getTelefoneAcad() {
        return telefoneAcad;
    }

    public void setTelefoneAcad(String telefoneAcad) {
        this.telefoneAcad = telefoneAcad;
    }

    public double getAvaliacaoAcad() {
        return avaliacaoAcad;
    }

    public void setAvaliacaoAcad(double avaliacaoAcad) {
        this.avaliacaoAcad = avaliacaoAcad;
    }

    public int getNum_personais() {
        return num_personais;
    }

    public void setNum_personais(int num_personais) {
        this.num_personais = num_personais;
    }
}
