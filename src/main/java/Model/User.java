package Model;

//import com.google.gson.Gson;
import java.sql.Date;

public class User {
    private String id;
    private String email;
    private String senha;
    private String nome;
    private String cpf;
    private Date nascimento;
    private String naturalidade;
    private String ufNascimento;
    private String filiacao1;
    private String filiacao2;
    private int sexo;
    private int estadoCivil;
    private int raca;

    public User(){}
    public User(String id, String email, String senha, String nome, String cpf){
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.cpf = cpf;
    }

    public User(String id, String email, String senha, String nome, String cpf, Date nascimento, String naturalidade, String ufNascimento, String filiacao1, String filiacao2, int sexo, int estadoCivil, int raca){
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.cpf = cpf;
        this.nascimento = nascimento;
        this.naturalidade = naturalidade;
        this.ufNascimento = ufNascimento;
        this.filiacao1 = filiacao1;
        this.filiacao2 = filiacao2;
        this.sexo = sexo;
        this.estadoCivil = estadoCivil;
        this.raca = raca;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public String getUfNascimento() {
        return ufNascimento;
    }

    public void setUfNascimento(String ufNascimento) {
        this.ufNascimento = ufNascimento;
    }

    public String getFiliacao1() {
        return filiacao1;
    }

    public void setFiliacao1(String filiacao1) {
        this.filiacao1 = filiacao1;
    }

    public String getFiliacao2() {
        return filiacao2;
    }

    public void setFiliacao2(String filiacao2) {
        this.filiacao2 = filiacao2;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public int getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(int estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public int getRaca() {
        return raca;
    }

    public void setRaca(int raca) {
        this.raca = raca;
    }

    public User getUserData(){
//        Gson gson = new Gson();
        User user = new User(id, email, senha, nome, cpf, nascimento, naturalidade, ufNascimento, filiacao1, filiacao2, sexo, estadoCivil, raca);
//        System.out.println(gson.toJson(user));
        return user;
    }
}
