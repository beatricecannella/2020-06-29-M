/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.imdb;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.imdb.model.Director;
import it.polito.tdp.imdb.model.DirectorAdiacente;
import it.polito.tdp.imdb.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnAdiacenti"
    private Button btnAdiacenti; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaAffini"
    private Button btnCercaAffini; // Value injected by FXMLLoader

    @FXML // fx:id="boxAnno"
    private ComboBox<Integer> boxAnno; // Value injected by FXMLLoader

    @FXML // fx:id="boxRegista"
    private ComboBox<Director> boxRegista; // Value injected by FXMLLoader

    @FXML // fx:id="txtAttoriCondivisi"
    private TextField txtAttoriCondivisi; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();

    	this.boxRegista.getItems().clear();
    	int anno = this.boxAnno.getValue();
    	model.creaGrafo(anno);
    	this.boxRegista.getItems().addAll(model.vertici());
    	this.txtResult.appendText("Grafo creato con "+  model.nVertici() + " e " + model.nArchi() + " archi." );
    	
    }

    @FXML
    void doRegistiAdiacenti(ActionEvent event) {
    	txtResult.clear();
    	Director d = this.boxRegista.getValue();
    	this.txtResult.appendText("Registi adiacenti a " + d.toString()+ "\n");
    	for(DirectorAdiacente dd: model.getAdiacenti(d)) {
    	this.txtResult.appendText(dd.toString()+"\n");
    	}
    	
    }

    @FXML
    void doRicorsione(ActionEvent event) {
    	txtResult.clear();
    	
    	Director d = this.boxRegista.getValue();
    	int c=0;
    	try {
    		c  = Integer.parseInt(this.txtAttoriCondivisi.getText());
    		
    		
    	}catch(NumberFormatException nfe) {
    		nfe.printStackTrace();
    		txtResult.appendText("Inserire un numero intero!");
    		return;
    	}
    	List<Director> lista = model.doRicorsione(c,d);
    	if(lista == null) {
    		txtResult.appendText("Non esiste un percorso migliore");
    		
    	}else {
    		for(Director dd: lista) {
    			txtResult.appendText(dd.toString() + "\n");
    		}
    	}
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnAdiacenti != null : "fx:id=\"btnAdiacenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaAffini != null : "fx:id=\"btnCercaAffini\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxAnno != null : "fx:id=\"boxAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxRegista != null : "fx:id=\"boxRegista\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtAttoriCondivisi != null : "fx:id=\"txtAttoriCondivisi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
   public void setModel(Model model) {
    	
    	this.model = model;
    	this.boxAnno.getItems().addAll(2004, 2005, 2006);
    }
    
}
