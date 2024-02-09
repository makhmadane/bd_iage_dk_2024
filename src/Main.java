import Metier.Fonctionnalite;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        int choix;

        Fonctionnalite fonctionnalite = new Fonctionnalite();

        do{
            choix = fonctionnalite.menu();

            switch (choix){

                case  1:
                    fonctionnalite.ajoutCompte();
                    break;
                case  2:
                    fonctionnalite.printAllCompte();
                    break;
                case  3:
                    fonctionnalite.searchByNumero();
                    break;
                case  4:
                    fonctionnalite.deleteCompte();
                    break;
                case  5:
                    break;
                default:
                    System.out.println("Ciao Ciao !!!");

            }
        }while(choix != 6);




    }
}