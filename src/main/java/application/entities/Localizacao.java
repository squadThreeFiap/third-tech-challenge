package application.entities;

public class Localizacao {
    private String uf;
    private String cidade;
    private String bairro;
    private String logradouro;
    private Integer numero;

    public Localizacao(String uf, String cidade, String bairro, String logradouro, Integer numero) {
        this.setUf(uf);
        this.setCidade(cidade);
        this.setBairro(bairro);
        this.setLogradouro(logradouro);
        this.numero = numero;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        if (uf == null || uf.isBlank() || uf.length() > 2) {
            throw new IllegalArgumentException("UF deve ser informado com 2 caracteres.");
        }

        this.uf = uf;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        if (cidade == null || cidade.isBlank()) {
            throw new IllegalArgumentException("Cidade deve ser informada.");
        }

        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        if (bairro == null || bairro.isBlank()) {
            throw new IllegalArgumentException("Bairro deve ser informado");
        }

        this.bairro = bairro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        if (logradouro == null || logradouro.isBlank()) {
            throw new IllegalArgumentException("Logradouro deve ser informado");
        }

        this.logradouro = logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }
}
