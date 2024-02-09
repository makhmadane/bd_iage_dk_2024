package model;

public class Compte {

    protected  int id;
    protected  double solde;
    protected  String devise;
    protected  String numero;


    //Constructeur
    //Surcharge de methode
    public Compte() {
    }

    public Compte(double solde, String devise, String numero) {
        this.solde = solde;
        this.devise = devise;
        this.numero = numero;
    }


    //Getters/Setters


    public int getId() {
        return id;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public String getDevise() {
        return devise;
    }

    public void setDevise(String devise) {
        this.devise = devise;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    //Redefinition
    @Override
    public String toString() {
        return
                "solde=" + solde +
                ", devise='" + devise + '\'' +
                ", numero='" + numero + '\'' ;
    }
}
