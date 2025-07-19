package javafx;

import javafx.geometry.Insets;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java7.Personne;

public class JavaFXApp extends Application {
    private ListView<String> listView;
    private List<Personne> personnes = new ArrayList<>();
    private final String FILE_NAME = "personne2.json";
    private Gson gson = new Gson();

    @Override
    public void start(Stage primaryStage) {
        // Champs d'entrée
        TextField nomField = new TextField();
        nomField.setPromptText("Nom");

        TextField ageField = new TextField();
        ageField.setPromptText("Âge");

        // Boutons
        Button ajouterBtn = new Button("Ajouter");
        Button supprimerBtn = new Button("Supprimer");
        Button sauvegarderBtn = new Button("Sauvegarder JSON");
        Button chargerBtn = new Button("Charger JSON");

        listView = new ListView<>();

        // Layout
        HBox inputBox = new HBox(10, nomField, ageField, ajouterBtn);
        HBox buttonBox = new HBox(10, sauvegarderBtn, chargerBtn, supprimerBtn);
        VBox root = new VBox(10, inputBox, buttonBox, listView);
        root.setPadding(new Insets(10));

        // Ajout d'une personne
        ajouterBtn.setOnAction(e -> {
            String nom = nomField.getText().trim();
            String ageText = ageField.getText().trim();
            if (!nom.isEmpty() && ageText.matches("\\d+")) {
                int age = Integer.parseInt(ageText);
                Personne p = new Personne(nom, age);
                personnes.add(p);
                listView.getItems().add(nom + " (" + age + " ans)");
                nomField.clear();
                ageField.clear();
            }
        });

        // Supprimer
        supprimerBtn.setOnAction(e -> {
            int selected = listView.getSelectionModel().getSelectedIndex();
            if (selected >= 0) {
                personnes.remove(selected);
                listView.getItems().remove(selected);
            }
        });

        // Sauvegarder
        sauvegarderBtn.setOnAction(e -> {
            try (FileWriter writer = new FileWriter(FILE_NAME)) {
                gson.toJson(personnes, writer);
                showAlert("Sauvegarde", "Données sauvegardées !");
            } catch (IOException ex) {
                showAlert("Erreur", "Erreur de sauvegarde : " + ex.getMessage());
            }
        });

        // Charger
        chargerBtn.setOnAction(e -> {
            try (FileReader reader = new FileReader(FILE_NAME)) {
                personnes = gson.fromJson(reader, new TypeToken<List<Personne>>() {}.getType());
                listView.getItems().clear();
                for (Personne p : personnes) {
                    listView.getItems().add(p.getNom() + " (" + p.getAge() + " ans)");
                }
                showAlert("Chargement", "Données chargées !");
            } catch (IOException ex) {
                showAlert("Erreur", "Erreur de chargement : " + ex.getMessage());
            }
        });

        primaryStage.setScene(new Scene(root, 500, 300));
        primaryStage.setTitle("Gestion de personnes");
        primaryStage.show();
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
