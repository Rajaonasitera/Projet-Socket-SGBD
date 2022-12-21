package main;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import connect.Connect;
import tab.Table;
import java.io.Serializable;

public class Server {
    public static void main(String[] args) throws Exception{
        ServerSocket ss=new ServerSocket(6666);
        System.out.println("waiting for request...");
        Socket s=ss.accept();
        System.out.println("Client Connecter");

            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            ObjectInputStream di=new ObjectInputStream(s.getInputStream());
            ObjectOutputStream d=new ObjectOutputStream(s.getOutputStream());

                String[] c=(String[])di.readObject();
                Connect bdd=new Connect(c[0],c[1]);

        while (true) {
            try {
                String y=di.readUTF();
                System.out.println(y);
                Table sr=bdd.requete(y);
                    d.writeObject(sr.affichageByLine());
                    d.flush();    
            } catch (Exception e) {
                if (e.getMessage().equals("OK")) {
                    d.writeObject("OK");
                    d.flush();    
                }else if (e.getMessage().equals("ERREUR DE CONNECTION")) {
                    d.writeObject("ERREUR DE CONNECTION");
                    d.flush();    
                }else if (e.getMessage().equals("CETTE BASE N'EXISTE PAS")) {
                    d.writeObject("BASE INEXISTANTE");
                    d.flush();    
                }else if (e.getMessage().equals("CETTE TABLE N'EXISTE PAS")) {
                    d.writeObject("TABLE INEXISTANTE");
                    d.flush();    
                }else if (e.getMessage().equals("DATABASE EXISTANT")) {
                    d.writeObject("DATABASE EXISTANT");
                    d.flush();    
                }else if (e.getMessage().equals("TABLE EXISTANT")) {
                    d.writeObject("TABLE EXISTANT");
                    d.flush();    
                }else if (e.getMessage().equals("TSY AMPY ILAY DATA")) {
                    d.writeObject("DATA INCOMPLET");
                    d.flush();    
                }else{
                    d.writeObject("SQL ERREUR");
                    d.flush();
                }
            }
        }
    }
}
