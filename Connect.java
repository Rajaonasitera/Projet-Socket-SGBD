package connect;
import java.util.Vector;

import javafx.scene.control.TableCell;

import java.lang.Exception;
import java.util.*;
import java.io.*;
import tab.*;
/**
 * Connect
 */
public class Connect {
    boolean co;
    String nom;

//------------------------------------------------------------------------------------------------------------------

    public boolean getCo() {
        return co;
    }
    public void setCo(boolean co) {
        this.co = co;
    }

//------------------------------------------------------------------------------------------------------------------

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }


//------------------------------------------------------------------------------------------------------------------

    public Connect(String connect,String nomBdd)throws Exception{
        if(connect.equals("Connect to Tsql")!=true){
            setCo(false);
            throw new Exception("ERREUR DE CONNECTION");
        }else{
            File bdd= new File(nomBdd);
            if (bdd.exists()) {
            setCo(true);
            setNom(nomBdd);
            System.out.println("CONNECTED");
            } else {
            throw new Exception("CETTE BASE N'EXISTE PAS");
            }
        }
    }

//------------------------------------------------------------------------------------------------------------------

    public void ecrire(String text,String nom)throws Exception{
        String nmFich=nom+".bdd";
        File fil= new File(nmFich);
        if (fil.exists()) {
            PrintWriter ecrire = new PrintWriter(new FileOutputStream(nmFich,true));
            ecrire.write(text+"\n");
            ecrire.close();
        } else {
            throw new Exception("CETTE TABLE N'EXISTE PAS");
        }
        
    }

//------------------------------------------------------------------------------------------------------------------

    public Table prendre(String nom)throws Exception{
            Table table=new Table();
            table.setNom(nom);

            String nmFich=nom+".bdd";
            File file=new File(nmFich);
            if (file.exists()) {
                try {

                    Scanner scan=new Scanner(file);
                    Vector<String>line=new Vector<>();
                    while(scan.hasNextLine()){
                        line.addElement(scan.nextLine());
                    }
                    Object[] rep=line.toArray();


                    table.setLength(Integer.parseInt((String)rep[0]));


                    String[] colonne=new String[table.getLength()];


                    int nb=(rep.length-(table.getLength()+1))/table.getLength();
                    table.setDataLength(nb);
                    // System.out.println(nb);


                    for (int i = 1; i < table.getLength()+1; i++) {
                        colonne[i-1]=(String)rep[i];
                    }
                    table.setColonne(colonne);
                    
                    String[][] valiny=new String[table.getLength()][];
                        int u=0;
                    for (int i = 0; i < table.getLength(); i++) {
                        // System.out.println(table.getLength());
                        valiny[i]=new String[table.getDataLength()];
                        u=0;
                        for (int j = i+table.getLength()+1; j < rep.length; j+=table.getLength()) {
                            
                            // System.out.println(va[i]);
                            String g=(String)rep[j]; 
                            valiny[i][u]=g;
                            // System.out.println(valiny[i][u]);

                            u++;
                        }
                       
                    }
                    table.setData(valiny);

                    table.setDataByLine(table.turnToLine());

                    return table;
                } catch (Exception e) {
                    // System.out.println(e);
                    throw new Exception("CETTE TABLE N'EXISTE PAS");
                    // TODO: handle exception
                }
            } else {
                throw new Exception("CETTE TABLE N'EXISTE PAS");
                // System.out.println("CETTE TABLE N'EXISTE PAS");
                // return null;
            }
            // return null;
        
        
    }
//------------------------------------------------------------------------------------------------------------------


    public void createdataB(String nom)throws Exception{
        if (co==true) {
            Boolean n=false;
            File file=new File(nom);
            if (file.exists()==false) {
                n=file.mkdir();
            } else {
                throw new Exception("DATABASE EXISTANT");
            }    
        }else{


            throw new Exception("ERREUR DE CONNECTION");
        }
        
    }

//------------------------------------------------------------------------------------------------------------------


    public void createT(String nom)throws Exception{
        if (co==true) {
            File file=new File(this.nom +"/"+ nom+".bdd");
            if (file.exists()==false) {
                file.createNewFile();
            } else {
                throw new Exception("TABLE EXISTANT");
            }    
        } else {
            
            throw new Exception("ERREUR DE CONNECTION");
        }
    }
//------------------------------------------------------------------------------------------------------------------

    public void delete(String condition)throws Exception{
        File file=new File("Data.bb");
        Scanner scan=new Scanner(file);
        Vector<String>line=new Vector<>();
        while(scan.hasNextLine()){
            if(scan.nextLine().equals(condition)==false){
                line.addElement(scan.nextLine());
            }
        }
        file.delete();
        Object[] nb=line.toArray();
        PrintWriter ecrire = new PrintWriter(new FileOutputStream("Data.bb",true));
        for (int i = 0; i < nb.length; i++) {
            ecrire.write(line.get(i)+"\n");
        }
        ecrire.close();

    }

//------------------------------------------------------------------------------------------------------------------

    public void deleteFile(String nom)throws Exception{
        if (co==true) {
            File file=new File(this.nom +"/"+ nom+".bdd");
            if (file.exists()) {
                file.delete();
            } else {
                throw new Exception("CETTE TABLE N'EXISTE PAS");
            }    
        } else {
            throw new Exception("ERREUR DE CONNECTION");
        }
    }
//------------------------------------------------------------------------------------------------------------------

//------------------------------------------------------------------------------------------------------------------

    public Table requete(String sql)throws Exception{
        if (getCo()==true) {
            if (sql.split(" ")[0].equals("delete")) {
                deleteFile(sql.split(" ")[1]);
                throw new Exception("OK");
            }
            String sql1=sql.substring(0, 9);
            String sql4=sql.substring(0,4);

            // get all in table
            // get all in table where Nom = Raj
            // get all in table join tab on Nom = Nom
            // get Nom in table
            // get Nom in table where Nom = Raj
            // get table union tab
            // get table divise tab
            // get table fois tab
            // get table diff tab
            // get table inter tab
            // write in table : bla,bla,bla
            // new database Base
            // new table table : Nom,Prenom,Age
            // deconnect
            // write in table : bla,bla,bla
            // delete g

            
            
            if(sql.equals("deconnect")){
                deconnect();
                throw new Exception("DISCONNECTED");
            }

            if (sql.split(" ")[0].equals("new")&&sql.split(" ").length==3) {
                if (sql.split(" ")[1].equals("database")) {
                    createdataB(sql.split(" ")[2]);
                    throw new Exception("OK");
                }else{
                    throw new Exception("SQL ERREUR");
                }
            }

            if (sql.split(" ")[0].equals("new")&&sql.split(" ").length==5) {
                if (sql.split(" ")[1].equals("table")) {
                    String[] sql9=sql.split(" ")[4].split(",");
                        createT(sql.split(" ")[2]);
                        ecrire(String.valueOf(sql9.length),this.nom+"/"+sql.split(" ")[2]);
                    for (int i = 0; i < sql9.length; i++) {
                        ecrire(sql9[i],this.nom+"/"+sql.split(" ")[2]);
                    }
                    throw new Exception("OK");
                    
                }else{
                    throw new Exception("SQL ERREUR");
                }
            }

            if (sql1.equals("write in ")) {
                String[] sql2=sql.split(" ");
                String nomT1=this.nom+"/"+sql2[2];
                File fil1=new File(nomT1+".bdd");
                if (fil1.exists()==false) {
                    throw new Exception("CETTE TABLE N'EXISTE PAS") ;
                }

                if (sql2[3].equals(":")) {
                    String[] sql3=sql2[4].split(",");
                    Table t1=prendre(nomT1);
                    if (sql3.length!=t1.getLength()) {
                        throw new Exception("TSY AMPY ILAY DATA");
                        // System.out.println("TSY AMPY ILAY DATA");
                    }else{
                        for (int i = 0; i < sql3.length; i++) {
                            ecrire(sql3[i],nomT1);
                        }
                        throw new Exception("OK");
                    }
                }
            }

            if (sql4.equals("get ")) {
                String[] sql5=sql.split(" ");
                // System.out.println(sql5[1]);
                // System.out.println(sql5[2]);
                // System.out.println(sql5[3]);

                if (sql5.length==4) {

                    if (sql5[2].equals("in")) {
                        String nomT2=this.nom+"/"+sql5[3];
                        File fil2=new File(nomT2+".bdd");
                        if (fil2.exists()==false) {
                            throw new Exception("CETTE TABLE N'EXISTE PAS") ;
                        }
                        Table t2=prendre(nomT2);
                        // System.out.println("jdj");

                        // get all in table
                        if (sql5[1].equals("all")) {
                            return t2;
                            // t2.affichageByLine();
                        }
                        
                        // get Nom in table
                        if (sql5[1].equals("all")==false) {
                            String[] u=new String[1];
                            u[0]=sql5[1];
                            Table t3=t2.projection(u);
                            return t3;
                            // t3.affichageByLine();
                        }    
                    }
                    
                    if (sql5[1].equals("all")==false&&sql5[2].equals("in")==false) {
                        String nomT5=this.nom+"/"+sql5[1];
                        String nomT6=this.nom+"/"+sql5[3];
                        File fil3=new File(nomT5+".bdd");
                        File fil4=new File(nomT6+".bdd");
                        if (fil3.exists()==false||fil4.exists()==false) {
                            throw new Exception("CETTE TABLE N'EXISTE PAS") ;
                        }
                        Table t6=prendre(nomT5);
                        Table t7=prendre(nomT6);
                        // t7.affichage();
                            // get table union tab
                            if (sql5[2].equals("union")) {
                                t6=t6.union(t7);
                                return t6;
                                // t6.affichageByLine();
                            }
                            // get table divise tab
                            if (sql5[2].equals("divise")) {
                                t6=t6.division(t7);
                                return t6;
                                // t6.affichageByLine();
                            }
                            // get table fois tab
                            if (sql5[2].equals("fois")) {
                                t6=t6.produit(t7);
                                return t6;
                                // t6.affichageByLine();
                            }
                            // get table inter tab
                            if (sql5[2].equals("inter")) {
                                t6=t6.intersection(t7);
                                return t6;
                                // t6.affichageByLine();
                            }
                            // get table diff tab
                            if (sql5[2].equals("diff")) {
                                t6=t6.difference(t7);
                                return t6;
                                // t6.affichageByLine();
                            }
                    }
                }

                if (sql5[2].equals("in")&&sql5.length==8) {
                    String nomT3=this.nom+"/"+sql5[3];
                    File fil6=new File(nomT3+".bdd");
                        if (fil6.exists()==false) {
                            throw new Exception("CETTE TABLE N'EXISTE PAS") ;
                        }
                    Table t4=prendre(nomT3);


                    // get all in table where Nom = Raj
                    if (sql5[1].equals("all")) {
                        t4=t4.selection(sql5[5], sql5[7]);
                        return t4;
                        // t4.affichage();
                    }
                    
                    // get Nom in table where Nom = Raj
                    else if (sql5[1].equals("all")==false) {
                        t4=t4.selection(sql5[5], sql5[7]);
                        String[] u=new String[1];
                        u[0]=sql5[1];
                        t4=t4.projection(u);
                        return t4;
                        // t4.affichageByLine();
                    }
                }

                if (sql5[2].equals("in")&&sql5.length==10) {
                    String nomT3=this.nom+"/"+sql5[3];
                    String nomT4=this.nom+"/"+sql5[5];
                    File fil6=new File(nomT3+".bdd");
                    File fil7=new File(nomT4+".bdd");
                        if (fil6.exists()==false||fil7.exists()==false) {
                            throw new Exception("CETTE TABLE N'EXISTE PAS") ;
                        }
                    Table t4=prendre(nomT3);
                    Table t5=prendre(nomT4);
                    // System.out.println("shdhkjs");
                    // get all in table join tab on Nom = Nom
                    if (sql5[1].equals("all")) {
                        t4=t4.join(t5, sql5[7], sql5[9]);
                        return t4;
                        // t4.affichageByLine();
                    }
                
            }
        } 
        }else{
            // return "ERREUR DE CONNECTION";
            throw new Exception("ERREUR DE CONNECTION");
        }
    return null;
    
}  
//------------------------------------------------------------------------------------------------------------------

    public void deconnect(){
        this.co=false;
        this.nom=null;
    }
}