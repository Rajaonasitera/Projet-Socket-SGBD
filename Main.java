package main;
import connect.Connect;
import tab.*;
import java.util.Scanner;
import java.io.File;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        try {
            Scanner s= new Scanner(System.in);
            // String o=s.nextLine();
            String o="Connect to Tsql";
            // "Connect to Tsql"
            String co=o;
            Scanner sc= new Scanner(System.in);
            // String nm=sc.nextLine();
            String nm="Nouveau dossier";
            // "Nouveau dossier"
            Connect bdd=new Connect(co,nm);
            // bdd.requete("Soraty anaty table ny hoe :Rajaonasitera,Tatiana,Antananarivo");
            // bdd.requete("Alaivo daholo ny anaty table",null);
            // bdd.requete("Fafao any ny","hgdjhwgfgew");
            
            
            // bdd.requete(sql);
            String sql="";
            Table t=bdd.prendre("Nouveau dossier/table");
            // t.affichage();
            Table tt= bdd.prendre("Nouveau dossier/tabl");
            // tt.affichageByLine();
            // String[] colonne=new String[2];
            // colonne[0]="Nom";
            // colonne[1]="Ville";
            // Table tab=t.projection(colonne);
            // tab.affichageByLine();
            // Table ta=t.union(tt);
            // ta.affichageByLine();
            // Table m=tt.difference(t);
            // m.affichageByLine();
            Table i=t.intersection(tt);
            i.affichageByLine();
            
            // t.affichage();
            // Table sel=t.selection("Nom", "Rajao");
            // sel.affichage();
            // t.affichage();
            // while (sql.equals("quit")!=true) {  
            //     // sca=new Scanner(System.in);
            //     // sql=sca.nextLine();
            //     Scanner sca= new Scanner(System.in);
            //     System.out.print("> ");
            //     sql=sca.nextLine();
            //     if (sql.equals("quit")==true) {
            //         break;
            //     }else{
            //         bdd.requete(sql);
            //     }
            // }
            // System.out.println(o);
            // bdd.requete("Alaivo dahol ny anaty table",null,null,null);
            // File dossier= new File("Nouveau dossie");
            // if (dossier.exists()) {
            //     System.out.println("existe");
            // }else{
            //     System.out.println(" no existe");
            // }
            // dossier.mkdir();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            // TODO: handle exception`
        }
        

    }
}