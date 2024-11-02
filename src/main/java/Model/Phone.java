package Model;

public class Phone {
    private String id;
    private String ddd;
    private String telefone;
    private String ramal;
    private int tipoTelefone;
    private String idUsuario;

    public Phone(){}
    public Phone(String id, String ddd, String telefone, int tipoTelefone, String idUsuario){
        this.id = id;
        this.ddd = ddd;
        this.telefone = telefone;
        this.tipoTelefone = tipoTelefone;
        this.idUsuario = idUsuario;
    }
    public Phone(String id, String ddd, String telefone, String ramal, int tipoTelefone, String idUsuario){
        this.id = id;
        this.ddd = ddd;
        this.telefone = telefone;
        this.ramal = ramal;
        this.tipoTelefone = tipoTelefone;
        this.idUsuario = idUsuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getRamal() {
        return ramal;
    }

    public void setRamal(String ramal) {
        this.ramal = ramal;
    }

    public int getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(int tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Phone getPhoneData(){
        Phone phone = new Phone(id, ddd, telefone, ramal, tipoTelefone, idUsuario);
        return phone;
    }
}
