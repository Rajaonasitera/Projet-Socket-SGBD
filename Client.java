package main;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.io.Serializable;

import tab.Table;

public class Client {
    public static void main(String[] args) throws IOException {
            System.out.println("Sending a Request..");
            Socket s = new Socket("localhost",6666);
            System.out.println("Ok");

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            ObjectOutputStream di = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream d = new ObjectInputStream(s.getInputStream());
            String str = br.readLine();
            String[] co=str.split("/");
            File bdd=new File("hh");
            if (co.length!=1) {    
                bdd=new File(co[1]);
            }
            
            while (co[0].equals("Connect to Tsql")==false||bdd.exists()==false) {
                System.out.println("ERREUR DE CONNEXION");
                str = br.readLine();
                co=str.split("/");
                if (co.length!=1) {    
                    bdd=new File(co[1]);
                }
            }
            di.writeObject(co);
            di.flush();
            System.out.println("CONNECTED");
            while (true) {
                try {
                    System.out.print("  >> ");
                    String st = br.readLine();
                        di.writeUTF(st);
                        di.flush();
    
                    // System.out.println(st);
                    
                    Object ob = d.readObject();
                    if (ob==null) {
                        System.out.println("ERREUR");
                    }
                    if (ob!=null&&ob.getClass().getSimpleName().equals("String[]")) {
                        String[] tab=(String[])ob;
                        for (int i = 0; i < tab.length; i++) {
                            System.out.println(tab[i]);
                        }
                    }
                    if (ob!=null&&ob.getClass().getSimpleName().equals("String")) {
                        if (ob.equals("DISCONNECTED")) {
                            str = br.readLine();
                            co=str.split("/");
                            bdd=new File("hh");
                            if (co.length!=1) {    
                                bdd=new File(co[1]);
                            }
                            
                            while (co[0].equals("Connect to Tsql")==false||bdd.exists()==false) {
                                System.out.println("ERREUR DE CONNEXION");
                                str = br.readLine();
                                co=str.split("/");
                                if (co.length!=1) {    
                                    bdd=new File(co[1]);
                                }
                            }

                            di.writeObject(co);
                            di.flush();
                            System.out.println("CONNECTED");
                        } else if (ob.equals("String index out of range: 9")) {
                            // System.out.println(" ");
                        }else{                            
                            System.out.println(ob);
                        }
                    }
                    // Thread.sleep(100);    
                } catch (Exception e) {
                    // TODO: handle exception
                }
                // System.out.println("Input the data..");
                
            }
    }
}
