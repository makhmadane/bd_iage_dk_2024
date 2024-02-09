package Metier;

import model.Compte;
import model.CompteCourant;
import model.CompteEpargne;
import Repository.ImplCompteMysql;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Fonctionnalite {

    Scanner clavier = new Scanner(System.in);

    private ImplCompteMysql implCompteMysql;
    public Fonctionnalite(){
            implCompteMysql = new ImplCompteMysql();
    }
    public int menu(){
        int choix;

        System.out.println("1-Ajout d'un compte");
        System.out.println("2-Lister les comptes");
        System.out.println("3-Recherche par numero");
        System.out.println("4-Supprimer");
        System.out.println("5-Mettre à jour");
        System.out.println("6-Quitter");
        do{
            choix = clavier.nextInt();
            if(choix<1 || choix >6){
                System.out.println("Le choix est incorrect .");
            }
        }while(choix<1 || choix >6);
        return  choix;
    }

    public int menuTypeCompte(){
        int choix;

        System.out.println("1-Compte Courant");
        System.out.println("2-Compte Epargne");
        do{
            choix = clavier.nextInt();
            if(choix<1 || choix >2){
                System.out.println("Le choix est incorrect .");
            }
        }while(choix<1 || choix >2);
        return  choix;
    }

    public void ajoutCompte() throws SQLException {
        int type = menuTypeCompte();
        Compte compte = (type == 1) ?  new CompteCourant() : new CompteEpargne();

        clavier.nextLine();
        System.out.println("Saisir le numero du compte");
        compte.setNumero(clavier.nextLine());
        System.out.println("Saisir le solde du compte");
        compte.setSolde(clavier.nextDouble());
        clavier.nextLine();
        System.out.println("Saisir le devise du compte");
        compte.setDevise(clavier.nextLine());
        clavier.nextLine();
        if( type == 1 ){
            System.out.println("Saisir la decouvert");
            ((CompteCourant)compte).setDecouvert(clavier.nextInt());
        }else {
            System.out.println("Saisir le taux");
            ((CompteEpargne)compte).setTaux(clavier.nextDouble());
        }
        implCompteMysql.add(compte);
    }

    public void printAllCompte() throws SQLException {
        List<Compte> comptes =  implCompteMysql.findAll();
        for (Compte c: comptes) {
            System.out.println(c.toString());
        }
    }

    public void searchByNumero() throws SQLException {
        String numero;
        clavier.nextLine();
        System.out.println("Saisir le numero a recherche");
        numero = clavier.nextLine();
        List<Compte> comptes =  implCompteMysql.findByNumero(numero);
        for (Compte c: comptes) {
            System.out.println(c.toString());
        }
    }

    public void deleteCompte() throws SQLException {
        int choix;
        printAllCompte();
        choix = clavier.nextInt();
        implCompteMysql.delete(choix);
    }

}
