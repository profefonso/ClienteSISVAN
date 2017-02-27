package co.gov.cundinamarca.sisvan.view;

import org.hibernate.Session;

import co.gov.cundinamarca.sisvan.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.print.PrinterJob;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class GraphicsOverviewController {
	private Main mainApp;
	private Session session;
	private Stage dialogStage;
	String graficaDraw;
	Task copyWorker;
	
	@FXML
	private WebView webView;
	private WebEngine engine;
	
	@FXML
    private void initialize() {
		copyWorker = createWorker();
		engine=webView.getEngine();
		engine.setJavaScriptEnabled(true);
		engine.load(this.getClass().getResource("htmlView/graphics.html").toExternalForm());
		copyWorker.messageProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.equals("0")){
                	System.out.println("Grafica");
                	engine.executeScript(graficaDraw);
                }
            }
        });
        
        new Thread(copyWorker).start();
	}
	
	@FXML
	private void handleCloseWindow() {
		copyWorker.cancel(true);
	    this.dialogStage.close();
	}
	
	@FXML
	private void handlePrint() {
		try{
			PrinterJob job = PrinterJob.createPrinterJob();
		    if (job != null) {
		    	engine.print(job);
		        job.endJob();
		    }
		}
		catch(Exception ep){
			ep.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Error");
        	alert.setHeaderText("Error al Imprimir");
        	alert.setContentText(ep.toString());
        	alert.showAndWait();
		}
		
	}
	
	private void cargaComponentes(){
	}
	
	public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
	
	public void setMainApp(Main mainApp, Session session, String graficaDraw) {
		this.mainApp = mainApp;
		this.session=session;
		this.graficaDraw=graficaDraw;
		cargaComponentes();
    }
	
	public Task createWorker() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                for (int i = 0; i < 1; i++) {
                    Thread.sleep(500);
                    updateMessage(String.valueOf(i));
                    updateProgress(i + 1, 10);
                }
                return true;
            }
        };
    }
}
