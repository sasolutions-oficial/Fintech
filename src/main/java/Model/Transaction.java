package Model;

import java.util.UUID;

public class Transaction {
    private UUID id;
    private int tipo;
    private String descricao;
    private double valor;
    private UUID idUsuario;
    private String cpfUsuario;

    public Transaction(){}
    public Transaction(int tipo, String descricao, double valor, UUID idUsuario, String cpfUsuario){
        this.tipo = tipo;
        this.descricao = descricao;
        this.valor = valor;
        this.idUsuario = idUsuario;
        this.cpfUsuario = cpfUsuario;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public UUID getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UUID idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCpfUsuario() {
        return cpfUsuario;
    }

    public void setCpfUsuario(String cpfUsuario) {
        this.cpfUsuario = cpfUsuario;
    }

    public Transaction getTransactionData(){
        Transaction transaction = new Transaction(tipo, descricao, valor, idUsuario, cpfUsuario);
        return  transaction;
    }
}
