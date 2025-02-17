package eu.telecomnancy.labfx;

import javafx.fxml.FXML;

import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import eu.telecomnancy.labfx.model.Ad;
import eu.telecomnancy.labfx.model.User;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

public class ViewOfferProfilController {
    @FXML private Label nom;
    @FXML private Label type;
    @FXML private Label description;
    @FXML private Label cout;
    @FXML private Label localisation;
    @FXML private Label distanceMax;
    @FXML private Label dateDebut;
    @FXML private Label dateFin;
    @FXML private Label duree;
    @FXML private Label disponibilites;
    @FXML private Label reservationLabel;
   
    @FXML private Button reserveButton;
    @FXML private ImageView image;
        
    @FXML private Button editButton;
    
    private Main main;
    private Ad offer;
    private User user;
    private String imagePath;

    public void setMain(Main main){
        this.main=main;
    }

    public void setAd(Ad offer){
        this.offer=offer;
    }

    public void setUser(User user){
        this.user=user;
    }

   
    public String dateToString(Date date) {
        if (date != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            return dateFormat.format(date);
        } else {
            
            return "N/A";
        }
    }
    
    public Image loadImage(){
        String imagePath = offer.imagePath;
        if(imagePath!=null && !imagePath.isEmpty()){
            System.out.println("Loading image");
            File file = new File(imagePath);
            String imageFileUrl = file.toURI().toString();
            Image imageLoaded = new Image(imageFileUrl);
            return imageLoaded;
        }
        else{
            System.out.println("Failed to load image");
            return null;
        }
    }

    public void initializeItems(){
        nom.setText(offer.name);
        if(offer.offer){
            type.setText("Offre");
        }
        else{
            type.setText("Demande");
        }
        description.setText(offer.description);
        cout.setText(Integer.toString(offer.cost));
        localisation.setText(offer.address);
        distanceMax.setText(offer.maxDistance + "");
        dateDebut.setText(dateToString(offer.start));
        if(dateFin != null){
            dateFin.setText(dateToString(offer.end));
        }
        else{
            dateFin.setText("");
        }
        if(duree!=null){
            duree.setText(Integer.toString(offer.duration));
        }
        else{
            duree.setText("");
        }
        disponibilites.setText(offer.disponibilities);
        image.setImage(loadImage());
        checkStatus();
    }

    public void checkStatus(){
        // for(AdHistory adHistory: user.history){
        //     if(adHistory.ad.userID == user.UID){
        //         reserveButton.setText("Waiting...");
        //         reserveButton.setDisable(true);
        //     }
        // }
    }
    

    @FXML
    public void retourMainScreen() throws IOException{
        main.ViewCompteController(user);
    }

    @FXML 
    public void sendMessage() throws IOException{
        main.inboxScreen(user, offer,user);
    }

    @FXML
    public void editOffer() throws IOException {
        
        main.editOfferScreen(user, offer);
    }
}
