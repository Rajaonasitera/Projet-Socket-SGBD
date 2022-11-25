package connect;
import java.util.Vector;
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

                    // System.out.println(table.getLength());
                    // Vector<String[]> you=new Vector<>();
                    // String[][] yu=new String[table.getDataLength()][];
                    // for (int i = 0; i < table.getDataLength(); i++) {
                    //     yu[i]=new String[table.getLength()];
                    // }
                    // for (int i = 0; i < table.getDataLength(); i++) {
                    //     for (int j = 0; j < table.getLength(); j++) {
                    //         yu[i][j]=table.getData()[j][i];
                    //         // System.out.println(yu[i][j]);
                    //     }
                    // }
                    // for (int i = 0; i < yu.length; i++) {
                    //     you.add(yu[i]);
                    // }
                    // System.out.println(you.size());
                    table.setDataByLine(table.turnToLine());
                    // for (int i = 0; i < table.getDataLength(); i++) {
                    //     System.out.println(yu[i][0]);
                    // }
                    return table;
                } catch (Exception e) {
                    System.out.println(e);
                    // throw new Exception("CETTE TABLE N'EXISTE PAS");
                    // TODO: handle exception
                }
            } else {
                System.out.println("CETTE TABLE N'EXISTE PAS");
                return null;
            }
            return null;
        
        
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

    // public void create(String quoi,String nom){
    //         if (quoi.equals("Database")) {
    //             File f=new File(nom);
    //             f.mkdir();
    //         }
    //     } else {
            
    //     }
    // }
//------------------------------------------------------------------------------------------------------------------

    public void requete(String sql)throws Exception{
        if (getCo()==true) {
            if (sql.length()<24) {
                // throw new Exception("Misy diso tompoko ny requete nao");
                System.out.println("MISY DISO TOMPOKO NY REQUETE NAO");
            }
            String sql1=sql.substring(0,12);
            String sql2=sql.substring(0,22);
            // String sql0=sql.substring(0,6);
            // System.out.println(sql0);

            if(sql1.equals("Soraty anaty")){
                String[] sql3=sql.substring(13).split(" ");
                String sql5=sql3[1]+" "+sql3[2]+" "+sql3[3].charAt(0);
                // System.out.println(sql5);
                String nomT=this.nom+"/"+sql3[0];

                if (sql5.equals("ny hoe :")) {
                        String[] table=sql.substring(27).split(",");
                        if (table.length!=3) {
                            // throw new Exception("Tsy ampy ilay data");
                            System.out.println("TSY AMPY ILAY DATA");
                        }
                    for (int i = 0; i < table.length; i++) {
                        ecrire(table[i],nomT);
                    }
                }else if(sql5.equals("ny hoe :")==false){
                    // throw new Exception("Misy diso tompoko ny requete nao");
                    System.out.println("MISY DISO TOMPOKO NY REQUETE NAO");
                }
            }
            // String[] sql6=sql.substring(8).split(" ");
            // if(sql0.equals("Alaivo")&&sql6[2].equals(txt)){
            //     String nomT=this.nom+"/"+sql.substring(23);
            //     String[] txt=prendre(nomT);

            //     if (sql6[0].equals("daholo")) {
            //         System.out.println(txt[1]+"           "+txt[2]+"        "+txt[3]+"\n");
            //         for (int i = 4; i < txt.length; i+=3) {
            //             System.out.println(txt[i]+" "+txt[i+1]+" "+txt[i+2]+"\n");
            //         }
            //     } else {
            //         int ind=0;
            //         for (int i = 1; i < Integer.parseInt(txt[0]); i++) {
            //             if (txt[i].equals(sql6[0])) {
            //                 ind=i;
            //             }
            //         }
            //         System.out.println(txt[ind]);
            //         for (int i = Integer.parseInt(txt[0])+1; i < txt.length; i+=3) {
            //             System.out.println(txt[i]"\n");
            //         }
            //     }
                
                
            // }


            if(sql2.equals("Alaivo daholo ny anaty")){
                String nomT=this.nom+"/"+sql.substring(23);
                Table txt=prendre(nomT);
                txt.affichage();
                // String[] tableau=new String[txt.getDataLength()+1];
                // System.out.println(txt[1]+"           "+txt[2]+"        "+txt[3]+"\n");
                // for (int i = 4; i < txt.length; i+=3) {
                //     System.out.println(txt[i]+" "+txt[i+1]+" "+txt[i+2]+"\n");
                // }
            }


            // if(sql.equals("Fafao any ny")){
            //     delete(text);
            // }
            else if(sql2.equals("Alaivo daholo ny anaty")==false&&sql1.equals("Soraty anaty")==false){
                // throw new Exception("Misy diso tompoko ny requete nao");
                System.out.println("MISY DISO TOMPOKO NY REQUETE NAO");
            }
        }else{
            throw new Exception("ERREUR DE CONNECTION");
        }
    }
    
//------------------------------------------------------------------------------------------------------------------

// public void requete(String sql)throws Exception{
//     String sqls=sql.substring(0,22);
//     System.out.println(sqls);
//     if (getCo()==true) {
//         if(sqls.equals("Alaivo daholo ny anaty")){
//             String[] txt=prendre();
//             // for (int i = 0; i < txt.length; i++) {
//             //     if () {
                    
//             //     }
//             // }
//             System.out.println(txt[0]+"           "+txt[1]+"        "+txt[2]+"\n");
//             for (int i = 3; i < txt.length; i+=3) {
//                 System.out.println(txt[i]+" "+txt[i+1]+" "+txt[i+2]+"\n");
//             }
//         }
//         else if(sql.equals("Alaivo daholo ny anaty table")==false&&sql.equals("Soraty anaty table ny hoe")==false){
//             throw new Exception("Misy diso tompoko ny requete nao");
//         }
//     }else{
//         throw new Exception("Erreur de connection");
//     }
// }
}