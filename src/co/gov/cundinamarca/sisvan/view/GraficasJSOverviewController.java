package co.gov.cundinamarca.sisvan.view;

import java.io.File;

import org.hibernate.Session;

import co.gov.cundinamarca.sisvan.Main;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableRow;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class GraficasJSOverviewController {
	
	private Main mainApp;
	private Session session;
	private Stage dialogStage;
	
	@FXML
	WebView viewGraphic;
	
	@FXML
    private void initialize() {
		loaPage();
    }
	
	public void loaPage(){
		try {
			WebEngine webEngine = viewGraphic.getEngine();
			
			webEngine.setJavaScriptEnabled(true);
	        File file = new File("C:/Users/alfon/workspace/ClienteSISVAN/resources/GraficasJs/Graphic.html");
	        System.out.println(file.exists() + " file exitence");
	        webEngine.load(file.toURI().toURL().toString());
	    } catch (Exception ex) {
	        System.err.print("error " + ex.getMessage());
	        ex.printStackTrace();
	    }
	}
	
	public void setMainApp(Main mainApp, Session session) {
		this.mainApp = mainApp;
		this.session=session;
    }

}
