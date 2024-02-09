package model;

public class CompteEpargne extends  Compte{

    private  double taux;


    public CompteEpargne(double solde, String devise, String numero, double taux) {
        super(solde, devise, numero);
        this.taux = taux;
    }

    public CompteEpargne() {
    }

    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }

    @Override
    public String toString() {
        return "CompteEpargne{" +
                "taux=" + taux +
                ", solde=" + solde +
                ", devise='" + devise + '\'' +
                ", numero='" + numero + '\'' +
                '}';
    }
}
