package Model;

public class PhoneType {
    private int tipoTelefone;
    private String descricaoTelefone;

    public PhoneType() {
    }

    public PhoneType(int tipoTelefone, String descricaoTelefone) {
        this.tipoTelefone = tipoTelefone;
        this.descricaoTelefone = descricaoTelefone;
    }

    public int getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(int tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }

    public String getDescricaoTelefone() {
        return descricaoTelefone;
    }

    public void setDescricaoTelefone(String descricaoTelefone) {
        this.descricaoTelefone = descricaoTelefone;
    }

    public PhoneType getPhoneTypeData() {
        PhoneType phoneType = new PhoneType(tipoTelefone, descricaoTelefone);
        return phoneType;
    }
}
