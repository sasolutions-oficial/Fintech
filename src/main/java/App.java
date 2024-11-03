//import DAO.AddressDAO;
//import DAO.PhoneDAO;
//import DAO.UserDAO;

//import Model.Address;
//import Model.Phone;
//import Model.User;
//import com.google.gson.Gson;
import config.ConnectionFactory;

import java.sql.SQLException;
//import java.sql.Date;

public class App {
    public static void main(String [] args) throws SQLException {
        ConnectionFactory.getConnection();
//        UserDAO user = new UserDAO();
//        AddressDAO address = new AddressDAO();
//        PhoneDAO phone = new PhoneDAO();
//        Gson gson = new Gson();

//        User saveUser = new User("", "teste@teste.com", "Teste@123", "Nome de Teste do Teste", "12345678900");
//        User saveUser = new User("", "teste@teste.com", "Teste@123", "Nome de Teste do Teste", "12345678900", new Date(2000, 5, 1), "São Paulo", "SP", "Nome da mãe", "Nome do pai", 1, 2, 2);
//        user.save(saveUser);
//        user.get("25DDFD3C9F82AD20E063103CA8C0AFAB");
//        user.delete("25DDF5F16584AC84E063103CA8C0579A");


//        Address addressUser = new Address("", "12345678", "Rua A", "300", "teste de complemento", "Campo Limpo", "São Paulo", "SP", "25DDFD3C9F82AD20E063103CA8C0AFAB" );
//        Address addressUser = new Address("", "12345678", "Rua A", "300", "Campo Limpo", "São Paulo", "SP", "25DDFD3C9F82AD20E063103CA8C0AFAB" );
//        address.save(addressUser);
//        address.get("25DDFD3C9F82AD20E063103CA8C0AFAB");
//        address.delete("25DDFD3C9F82AD20E063103CA8C0AFAB");

//        Phone phoneUser = new Phone("", "11", "987654321", 1, "25DDFD3C9F82AD20E063103CA8C0AFAB");
//        Phone phoneUser = new Phone("", "11", "36008000", "8010", 2, "25DDFD3C9F82AD20E063103CA8C0AFAB");
//        phone.save(phoneUser);
//        phone.getAll("25DDFD3C9F82AD20E063103CA8C0AFAB");
//        phone.get("9");
    }
}
