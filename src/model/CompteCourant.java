package model;

public class CompteCourant extends Compte{

    private int decouvert;

    public CompteCourant(double solde, String devise, String numero, int decouvert) {
        super(solde, devise, numero);
        this.decouvert = decouvert;
    }

    public CompteCourant() {
    }

    public int getDecouvert() {
        return decouvert;
    }

    public void setDecouvert(int decouvert) {
        this.decouvert = decouvert;
    }

    @Override
    public String toString() {
        return "CompteCourant{" +
                "decouvert=" + decouvert +
                ", solde=" + solde +
                ", devise='" + devise + '\'' +
                ", numero='" + numero + '\'' +
                '}';
    }
}
