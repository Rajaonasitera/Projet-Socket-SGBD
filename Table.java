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

    public void affichage(){
        String[] affichage=new String[this.getDataLength()+1];

        affichage[0]=this.getColonne()[0];
        for (int i = 1; i < this.getColonne().length; i++) {
            affichage[0]=affichage[0]+"\t\t\t\t"+this.getColonne()[i];
            // System.out.println(affichage[0]);
            
        }
        System.out.println(affichage[0]);
        for (int i = 1; i < affichage.length; i++) {
            affichage[i]=this.getData()[0][i-1];
            // System.out.println(affichage[i]);
            for (int j = 1; j < this.getLength(); j++) {
                affichage[i]=affichage[i]+"\t\t\t\t"+this.getData()[j][i-1];
                // System.out.println(this.getData()[j][i]);
                // System.out.println(affichage.length);
            }
            System.out.println(affichage[i]);
        }
        
    }

    public void affichageByLine(){
        String[] affichage=new String[this.getDataLength()+1];

        affichage[0]=this.getColonne()[0];
        for (int i = 1; i < this.getColonne().length; i++) {
            affichage[0]=affichage[0]+"\t\t\t\t"+this.getColonne()[i];
            // System.out.println(affichage[0]);
            
        }
        System.out.println(affichage[0]);
        for (int i = 1; i < affichage.length; i++) {
            affichage[i]=this.getDataByLine().get(i-1)[0];
            // System.out.println(affichage[i]);
            for (int j = 1; j < this.getLength(); j++) {
                affichage[i]=affichage[i]+"\t\t\t\t"+this.getDataByLine().get(i-1)[j];
                // System.out.println(this.getDataByLine().get(i)[j-1]);
                // System.out.println(affichage.length);
                // System.out.println(i-1);
            }
            System.out.println(affichage[i]);
        }
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

        return tab;
    }

    public Table union(Table t){
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
        for (int i = 0; i < you.size(); i++) {
            System.out.println(reponse.getDataByLine().get(i)[0]);
        }
        return reponse;
    }

    public Table difference(Table t){
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
        return tab;
        
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
        // for (int i = 0; i < you.size(); i++) {
        //     System.out.println(reponse.getDataByLine().get(i)[0]);
        // }
        return reponse;
    }

    public Table produit(Table t){
        Table rep=new Table();
        rep.setLength(this.getLength()+t.getLength());
        String[] s=new String[rep.getLength()];
        Vector<String[]> newc=new Vector<>();
        for (int i = 0; i < this.getLength(); i++) {
            newc.add(this.getColonne()[i]);
        }
        for (int i = 0; i < t.getLength(); i++) {
            newc.add(t.getColonne()[i]);
        }
        rep.setColonne(s);
        String[][] newt=new String[this.getLength()+t.getLength()][];
        for (int i = 0; i < newt.length; i++) {
            newt[i]=new String[this.getDataLength()*t.getDataLength()];
        }
        // for (int i = 0; i < this.getLength(); i++) {
        //     for (int j = 0; j < this.getDataLength(); j++) {
        //         newt[i][j]=this.getData()[i][j];
        //     }
        // }
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

    



}
