package java7;

import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import com.google.gson.reflect.TypeToken;


import com.google.gson.Gson;


import java.io.FileReader;
import java.util.List;
import org.ietf.jgss.GSSContext;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nb;
        int nb1=0;
        String var;
        ArrayList <Personne> personne = new ArrayList<>();
        Gson gson = new Gson();
        
        do {
            System.out.println("1. Ajouter une personne");
            System.out.println("2. Afficher toutes les personnes");
            System.out.println("3. Sauvegarder en JSON");
            System.out.println("4. Charger depuis JSON");
            System.err.println("5. Personne à supprimer");
            System.out.println("0. Quitter");
        
             nb = scanner.nextInt(); 
             scanner.nextLine();

             if(nb==0){
                System.exit(0);
             }

             if(nb==1){
                System.out.println("Nom :");
                String nom = scanner.nextLine();

                System.out.println("Âge :");
                int age = scanner.nextInt();
                scanner.nextLine(); // pour vider le buffer

                personne.add(new Personne(nom, age));

            }

            if(nb==2){
                for (Personne elem : personne) {
                    System.out.println("Nom " + elem.getNom() + " Age " + elem.getAge());                    
                }
            }

            if(nb==3){

                try(FileWriter writer = new FileWriter("personne2.json")) {
                    gson.toJson(personne,writer);
                    System.out.println("Json Enregistré ");
                } 
                catch (IOException e) {
                    System.out.println("L'erreur est " + e.getMessage());
                }
            }

            if(nb==4){
                try(FileReader reader = new FileReader("personne2.json")) {
                    List <Personne> per = gson.fromJson(reader, new TypeToken<List<Personne>>(){}.getType());
                    personne = new ArrayList<>(per);
                    System.out.println("Json peut maintenant etre lue ");
                    for (Personne elem : per) {
                        System.out.println("Nom " + elem.getNom() + " Age " + elem.getAge());
                    }

                } catch (IOException e) {
                    System.err.println("AHHHHHHHHHHH");
                }
                
            }



            if(nb==5){
               System.out.println("Nom à supprimer :");
                String nom = scanner.nextLine();

                boolean result = personne.removeIf(p -> p.getNom().equalsIgnoreCase(nom));

                if (result) {
                    System.out.println("✅ Personne(s) supprimée(s).");
                } else {
                System.out.println("❌ Aucun nom correspondant.");
                    }



            }
            
            
         } while ( nb!= 0);
        
                    }
    
}


            /*if(nb==5){
                var=scanner.nextLine();
                for (int i = 0; i < personne.size(); i++) {
                    if(personne.get(i).getNom().equals(var)){
                        personne.remove(i);
                        System.out.println("Personne " + var + "enlevé");
                        break;
                    }
                    
                }
        }   
                
        

            System.out.println("Entrez le nom de la personne à supprimer :");
            String nomASupprimer = scanner.nextLine();

            boolean supprimée = false;

            for (int i = 0; i < personne.size(); i++) {
                if (personne.get(i).getNom().equalsIgnoreCase(nomASupprimer)) {
                    personne.remove(i);
                    supprimée = true;
                    System.out.println("✅ Personne supprimée !");
                    break;
                         }
                }

            if (!supprimée) {
                System.out.println("❌ Personne introuvable.");
                    }
              */

