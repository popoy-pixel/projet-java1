package java7;
import java.io.Serializable;

public class Personne implements Serializable{
    private static final long serialVersionUID =1L;

    private String nom;
    private int age;

    public Personne(String nom,int age){
        this.nom=nom;
        this.age=age;
    }
    public String getNom(){
        return nom;
    }

    public int getAge(){
        return age;
    }

    
}
