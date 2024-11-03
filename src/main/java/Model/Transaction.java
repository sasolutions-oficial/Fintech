package Model;

//import com.google.gson.Gson;
import java.sql.Timestamp;

public class Transaction {
    private String id;
    private String descricao;
    private double valor;
    private int tipo;
    private String data;
    private String idUsuario;

    public Transaction(){}
    public Transaction(String id, String descricao, double valor, int tipo, String data, String idUsuario){
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
        this.data = data;
        this.idUsuario = idUsuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Transaction getTransactionData(){
//    	Gson gson = new Gson();
    	Transaction transaction = new Transaction(id, descricao, valor, tipo, data, idUsuario);
//    	System.out.println(gson.toJson(transaction));
        return transaction;
    }
}
