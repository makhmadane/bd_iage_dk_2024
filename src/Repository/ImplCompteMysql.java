package Repository;

import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImplCompteMysql implements IOperation<Compte> {
    private  Connection connection;

    public ImplCompteMysql() {
        this.connection = new BD().getConnection();
    }

    @Override
    public void add(Compte compte) throws SQLException {
        Boolean result= compte instanceof CompteCourant;
        String choix = (result) ? "decouvert" :"taux";
        String sql = "INSERT INTO compte (solde,devise,numero,"+choix+") values (?,?,?,?)";
        PreparedStatement statement= connection.prepareStatement(sql);
        statement.setDouble(1,compte.getSolde());
        statement.setString(2,compte.getDevise());
        statement.setString(3,compte.getNumero());
        if ((result)) {
            statement.setInt(4, ((CompteCourant) compte).getDecouvert());
        } else {
            statement.setDouble(4, ((CompteEpargne) compte).getTaux());
        }

        statement.executeUpdate();

    }

    @Override
    public void update(int id,Compte compte) throws SQLException {
        Boolean result= compte instanceof CompteCourant;
        String choix = (result) ? "decouvert" :"taux";

        String sql = "Update compte SET numero = ? , solde = ? " +
                ", devise = ?, "+choix+" = ? where id= ?";

        System.out.println(sql);
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setDouble(2,compte.getSolde());
        statement.setString(3,compte.getDevise());
        statement.setString(1,compte.getNumero());
        if ((result)) {
            statement.setInt(4, ((CompteCourant) compte).getDecouvert());
        } else {
            statement.setDouble(4, ((CompteEpargne) compte).getTaux());
        }
        statement.setInt(5,id);

       int rs= statement.executeUpdate();

       if(rs == 0){
           throw new SQLException();
       }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "Delete from compte where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,id);
        statement.executeUpdate();

    }

    @Override
    public Compte findById(int id) throws SQLException {
        String sql = "SELECT * FROM compte where id= ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,id);
        ResultSet result=  statement.executeQuery();
        if(result.next()){
            return  generatedCompte(result);
        }

        throw new SQLException("Id n'existe pas ");
    }

    @Override
    public List<Compte> findByNumero(String numero) throws SQLException {
        List<Compte> comptes = new ArrayList<>();
        String sql = "SELECT * FROM compte where numero LIKE '%"+numero+"%'";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet result=  statement.executeQuery();
        while(result.next()){
            comptes.add(generatedCompte(result));
        }
        return  comptes;
    }

    @Override
    public List<Compte> findAll() throws SQLException {
        List<Compte> comptes = new ArrayList<>();
        String sql = "SELECT * FROM compte";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet result= statement.executeQuery();

        while(result.next()){
                comptes.add(generatedCompte(result));
        }
        return comptes;
    }

    private Compte generatedCompte(ResultSet result) throws SQLException {
        Compte compte ;
        if(result.getDouble("taux") != 0){
            compte  = new CompteEpargne(
                    result.getDouble("solde"),
                    result.getString("devise"),
                    result.getString("numero"),
                    result.getDouble("taux")
            );
        }else{
            compte  = new CompteCourant(
                    result.getDouble("solde"),
                    result.getString("devise"),
                    result.getString("numero"),
                    result.getInt("decouvert")
            );
        }
        return  compte;
    }
}
