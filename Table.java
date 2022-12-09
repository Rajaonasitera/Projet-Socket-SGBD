package tab;

import connect.Connect;
import java.util.Vector;

public class Table {
    String nom;
    int length;
    String[] colonne;
    String[][] data;
    int dataLength;
    Vector<String[]> dataByLine;
    

    

    public Vector<String[]> getDataByLine() {
        return dataByLine;
    }

    public void setDataByLine(Vector<String[]> dataByLine) {
        this.dataByLine = dataByLine;
    }

    public Table(String nom, int length, String[] colonne, String[][] data, int dataLength) {
        this.nom = nom;
        this.length = length;
        this.colonne = colonne;
        this.data = data;
        this.dataLength = dataLength;
    }

    public Table() {
    } 

    public int getDataLength() {
        return dataLength;
    }

    public void setDataLength(int dataLength) {
        this.dataLength = dataLength;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String[] getColonne() {
        return colonne;
    }

    public void setColonne(String[] colonne) {
        this.colonne = colonne;
    }

    public String[][] getData() {
        return data;
    }

    public void setData(String[][] data) {
        this.data = data;
    }

    public String[] affichage(){
        String[] affichage=new String[this.getDataLength()+1];

        affichage[0]=this.getColonne()[0];
        for (int i = 1; i < this.getColonne().length; i++) {
            affichage[0]=affichage[0]+"\t\t"+this.getColonne()[i];
            // System.out.println(affichage[0]);
            
        }
        // System.out.println(affichage[0]);
        for (int i = 1; i < affichage.length; i++) {
            affichage[i]=this.getData()[0][i-1];
            // System.out.println(affichage[i]);
            for (int j = 1; j < this.getLength(); j++) {
                affichage[i]=affichage[i]+"\t\t"+this.getData()[j][i-1];
                // System.out.println(this.getData()[j][i]);
                // System.out.println(affichage.length);
            }
            // System.out.println(affichage[i]);
        }
        return affichage;
        
    }

    public String[] affichageByLine(){
        String[] affichage=new String[this.getDataLength()+1];

        affichage[0]=this.getColonne()[0];
        for (int i = 1; i < this.getColonne().length; i++) {
            affichage[0]=affichage[0]+"\t\t"+this.getColonne()[i];
            // System.out.println(affichage[0]);
            
        }
        // System.out.println(affichage[0]);
        for (int i = 1; i < affichage.length; i++) {
            affichage[i]=this.getDataByLine().get(i-1)[0];
            // System.out.println(affichage[i]);
            for (int j = 1; j < this.getLength(); j++) {
                affichage[i]=affichage[i]+"\t\t"+this.getDataByLine().get(i-1)[j];
                // System.out.println(this.getDataByLine().get(i)[j-1]);
                // System.out.println(affichage.length);
                // System.out.println(i-1);
            }
            // System.out.println(affichage[i]);
        }
        return affichage;
    }

    public Table selection(String colonne,String mots){
        int c=0;
        for (int i = 0; i < this.colonne.length; i++) {
            if (this.getColonne()[i].equals(colonne)) {
                c=i;
            }
        }

        Table tab=new Table();
        String name=this.getNom()+"."+nom;
        tab.setNom(name);

        tab.setColonne(this.getColonne());
        tab.setLength(this.getLength());

        int isa=0;
        for (int i = 0; i < this.getDataLength(); i++) {
            if (this.data[c][i].equals(mots)) {
                isa++;
            }
        }

        String[][] ta=new String[this.getLength()][];
        for (int i = 0; i < ta.length; i++) {
            ta[i]=new String[isa];
        }

        tab.setDataLength(isa);
        int p=0;
        for (int i = 0; i < this.getDataLength(); i++) {
            if (this.data[c][i].equals(mots)) {
                for (int j = 0; j < ta.length; j++) {
                    ta[j][p]=this.data[j][i];
                }
                p++;
            }
        }
        
        tab.setData(ta);
        tab.setDataByLine(tab.turnToLine());
        return tab;
    }

    public Table projection(String[] colonne){
        Vector<Integer> c=new Vector<>();
        for (int i = 0; i < this.colonne.length; i++) {
            for (int j = 0; j < colonne.length; j++) {
                if (this.getColonne()[i].equals(colonne[j])) {
                    c.add(i);
                }
            }
        }
        Table tab=new Table();
        String name="Projection "+this.getNom()+" de "+colonne[0];
        for (int j = 1; j < colonne.length; j++) {
            name=name+" , "+colonne[j];
        }
        tab.setNom(name);
        String[] col=new String[c.size()];
        for (int i = 0; i < col.length; i++) {
            col[i]=this.getColonne()[c.get(i)];
        }
        tab.setColonne(col);
        tab.setLength(col.length);
        tab.setDataLength(this.getDataLength());

        String[][] dta=new String[tab.getLength()][];
        for (int i = 0; i < tab.getLength(); i++) {
            dta[i]=new String[tab.getDataLength()];
        }
        for (int i = 0; i < dta.length; i++) {
            dta[i]=this.getData()[c.get(i)];
        }
        tab.setData(dta);
        tab.setDataByLine(tab.turnToLine());
        return tab;
    }

    public Table union(Table t)throws Exception{
        if (this.getLength()==t.getLength()) {
            Table reponse=new Table();
            reponse.setNom(this.getNom()+" union "+t.getNom());
            reponse.setColonne(this.getColonne());
            reponse.setLength(this.getLength());
            Vector<String[]> you=new Vector<>();
            for (int i = 0; i < this.getDataByLine().size(); i++) {
                you.add(this.getDataByLine().get(i));
            }
            for (int i = 0; i < t.getDataByLine().size(); i++) {
                you.add(t.getDataByLine().get(i));
            }
            you=deleteDouble(you);
            reponse.setDataLength(you.size());
            reponse.setDataByLine(you);
            reponse.setData(reponse.turnToCol());
            // for (int i = 0; i < you.size(); i++) {
            //     System.out.println(reponse.getDataByLine().get(i)[0]);
            // }
            return reponse;
        }else{
            throw new Exception("COLONNE NUMBER INCOMPATIBLE");
        }
        
    }

    public Table difference(Table t)throws Exception{
        // if (this.getLength()!=t.getLength()) {
            Table tab=new Table();
            tab.setLength(this.getLength());
            tab.setColonne(this.getColonne());
            // Vector<Integer> del=this.getDataByLine();
            int test=0;
            Vector<String[]> dt=this.getDataByLine();
            for (int i = 0; i < this.getDataByLine().size() ; i++) {
                for (int j = 0; j < t.getDataByLine().size(); j++) {
                    test=0;
                    for (int j2 = 0; j2 < this.colonne.length; j2++) {
                        if (this.getDataByLine().get(i)[j2].equals(t.getDataByLine().get(j)[j2])) {
                            test++;
                        }
                    }
                    if (test==this.colonne.length) {
                        dt.removeElementAt(j);
                    }
                }
            }
            tab.setDataLength(dt.size());
            tab.setDataByLine(dt);
            tab.setData(tab.turnToCol());
            return tab;    
        // }else{
        //     throw new Exception("COLONNE NUMBER INCOMPATIBLE");
        // }
        
        
    }

    public Table intersection(Table t){
        Table reponse=new Table();
        reponse.setNom(this.getNom()+" union "+t.getNom());
        reponse.setColonne(this.getColonne());
        reponse.setLength(this.getLength());
        Vector<String[]> you=new Vector<>();
        for (int i = 0; i < this.getDataByLine().size(); i++) {
            you.add(this.getDataByLine().get(i));
        }
        you=deleteNotDouble(you);
        you=deleteDouble(you);
        // reponse.setDataByLine(you);
        reponse.setDataLength(you.size());
        reponse.setDataByLine(you);
        reponse.setData(reponse.turnToCol());
        // for (int i = 0; i < you.size(); i++) {
        //     System.out.println(reponse.getDataByLine().get(i)[0]);
        // }
        return reponse;
    }

    public Table produit(Table t)throws Exception{
        Table rep=new Table();
        rep.setNom(this.getNom()+" x "+t.getNom());
        rep.setLength(this.getLength()+t.getLength());
        String[] s=new String[rep.getLength()];
        for (int i = 0; i < this.getColonne().length; i++) {
            s[i]=this.getColonne()[i];
        }
        for (int i = this.getColonne().length; i < t.getColonne().length+this.getColonne().length; i++) {
            s[i]=t.getColonne()[i-this.getColonne().length];
        }
        rep.setColonne(s);
        String[][] newt=new String[this.getLength()+t.getLength()][];
        // Vector<String[]> datal=new Vector<>();
        for (int i = 0; i < newt.length; i++) {
            newt[i]=new String[this.getDataLength()*t.getDataLength()];
        }
        rep.setDataLength(this.getDataLength()*t.getDataLength());
            for (int i = 0; i < this.getLength(); i++) {
                for (int j = 0; j < rep.getDataLength(); j++) {
                        newt[i][j]=this.getData()[i][j%this.getLength()];
                        // System.out.println(j%this.getLength());
                        // System.out.println(newt[i][j]);
                }
            }
            for (int i = this.getLength(); i < rep.getLength(); i++) {
            int o=0;
            // System.out.println(rep.getLength());
                for (int j = 0; j < rep.getDataLength(); j++) {
                        newt[i][j]=t.getData()[i-this.getLength()][o];
                        // System.out.println(newt[i][j]);
                        // System.out.println(o);
                        if ((j+1)%this.getLength()==0) {
                            o=o+1;
                            // System.out.println(o);
                        }
                }
                // System.out.println(i);
            }
            rep.setData(newt);
            rep.setDataByLine(rep.turnToLine());
            return rep;
    }

    public Table division(Table t)throws Exception{
        Table reponse=new Table();
        Vector<String> col=new Vector<>();
        for (int i = 0; i < this.getColonne().length; i++) {
            for (int j = 0; j < t.getColonne().length; j++) {
                if (this.getColonne()[i].equals(t.getColonne()[j])==false) {
                    col.add(this.getColonne()[i]);
                }    
            }
        }
        String[] c=new String[col.size()];
        for (int i = 0; i < col.size(); i++) {
            c[i]=col.get(i);
        }
        Table T1=this.projection(c);
        // T1.affichageByLine();
        // System.out.println("b");
 
        Table p=t.produit(T1);
        // p.affichageByLine();
        // System.out.println("b");
 
        Table d=p.difference(this);
        // d.affichageByLine();
        // System.out.println("b");
 
        Table T2=d.projection(c);
        // T2.affichageByLine();
        // T2.affichageByLine();
        // System.out.println("b");

        reponse=T2.difference(T1);
        // reponse.affichageByLine();

        return reponse;
    }

    public Table join(Table t,String pk,String fk)throws Exception{
        String[] c=new String[t.getLength()-1];
        Vector<String> col=new Vector<>();
        int ifk=0;
        for (int i = 0; i < t.getColonne().length; i++) {
            if (t.getColonne()[i].equals(fk)==false) {
                col.add(t.getColonne()[i]);
            }else{
                ifk=i;
            }
        }
        int ipk=0;
        for (int i = 0; i < this.getColonne().length; i++) {
            if (this.getColonne()[i].equals(fk)) {
                ipk=i;
            }
        }
        for (int i = 0; i < c.length; i++) {
            c[i]=col.get(i);
        }
        t=t.arranger(this.getData()[ipk], ifk);
        Table t1=t.projection(c);
        Table reponse=this.addition(t1);
        return reponse;
    }

    public Table addition(Table t){
        Table reponse=new Table();
        reponse.setNom(this.getNom()+" add "+t.getNom());
        reponse.setLength(this.getLength()+t.getLength());
        reponse.setDataLength(this.getDataLength());
        String[] col=new String[reponse.getLength()];
        Vector<String> co=new Vector<>();
        for (int i = 0; i < this.getLength(); i++) {
            co.add(this.getColonne()[i]);
        }
        for (int i = 0; i < t.getLength(); i++) {
            co.add(t.getColonne()[i]);
        }
        for (int i = 0; i < col.length; i++) {
            col[i]=co.get(i);
        }
        reponse.setColonne(col);
        String[][] vao=new String[reponse.getLength()][];
        for (int i = 0; i < vao.length; i++) {
            vao[i]=new String[reponse.getDataLength()];
        }
        Vector<String[]> nc=new Vector<>();
        for (int i = 0; i < this.getData().length; i++) {
            nc.add(this.getData()[i]);
        }
        for (int i = 0; i < t.getData().length; i++) {
            nc.add(t.getData()[i]);
        }
        for (int i = 0; i < vao.length; i++) {
            vao[i]=nc.get(i);
        }
        reponse.setData(vao);
        reponse.setDataByLine(reponse.turnToLine());
        return reponse;
    }

    public Table arranger(String[] data,int fk){
        Vector<String[]> line=new Vector<>();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if (data[i].equals(this.dataByLine.get(j)[fk])) {
                    line.add(this.dataByLine.get(j));
                }
            }
        }
        Table reponse=this;
        reponse.setDataByLine(line);
        reponse.setData(reponse.turnToCol());
        return reponse;
    }

    public Vector<String[]> deleteNotDouble(Vector<String[]> you){
        int test=0;
        Vector<Integer> ind=new Vector<>();
        for (int i = 0; i < you.size() ; i++) {
            for (int j = i+1; j < this.getDataByLine().size(); j++) {
                test=0;
                for (int j2 = 0; j2 < this.colonne.length; j2++) {
                    if (you.get(i)[j2].equals(this.getDataByLine().get(j)[j2])) {
                        test++;
                    }
                }
                // System.out.println(this.colonne.length);
                if (test==this.colonne.length) {
                    // System.out.println(you.get(i)[0]);
                    you.removeElementAt(i);
                    // System.out.println(i);
                    // ind.add(j);
                }
            }
        }

        // for (int j2 = 0; j2 < ind.size(); j2++) {
        //     you.removeElementAt(ind.get(j2));
        // }
        return you;
    }
    
    public Vector<String[]> deleteDouble(Vector<String[]> you){
        int test=0;
        for (int i = 0; i < you.size() ; i++) {
            for (int j = i+1; j < you.size(); j++) {
                test=0;
                for (int j2 = 0; j2 < this.colonne.length; j2++) {
                    if (you.get(i)[j2].equals(you.get(j)[j2])) {
                        test++;
                    }
                }
                if (test==this.colonne.length) {
                    you.removeElementAt(j);
                }
            }
        }
        return you;
    }

    public Vector<String[]> turnToLine(){
        Vector<String[]> you=new Vector<>();
        String[][] yu=new String[this.getDataLength()][];
        for (int i = 0; i < this.getDataLength(); i++) {
            yu[i]=new String[this.getLength()];
        }
        for (int i = 0; i < this.getDataLength(); i++) {
            for (int j = 0; j < this.getLength(); j++) {
                yu[i][j]=this.getData()[j][i];
                // System.out.println(yu[i][j]);
            }
        }
        for (int i = 0; i < yu.length; i++) {
            you.add(yu[i]);
        }
        return you;
    }

    public String[][] turnToCol(){
        String[][] rep=new String[this.getLength()][];
        for (int i = 0; i < rep.length; i++) {
            rep[i]=new String[this.getDataByLine().size()];
        }
        for (int i = 0; i < rep.length; i++) {
            for (int j = 0; j < rep[0].length; j++) {
                rep[i][j]=this.getDataByLine().get(j)[i];
            }
        }
        return rep;
    }

    // public Boolean testColonne(Table t){
    //     int ok=0;
    //     for (int i = 0; i < this.getColonne().length; i++) {
    //         for (int j = 0; j < t.getColonne().length; j++) {
    //             if (this.getColonne()[i].equals(t.getColonne()[j])) {
    //                 ok=1;
    //             }
    //         }
    //     }
    //     if (ok==0) {
    //         return false;
    //     }else{
    //         return true;   
    //     }
    //     return false;
    // }



}
