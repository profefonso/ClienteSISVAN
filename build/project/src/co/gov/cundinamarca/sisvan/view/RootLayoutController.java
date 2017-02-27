package co.gov.cundinamarca.sisvan.view;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import co.gov.cundinamarca.sisvan.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class RootLayoutController {
	// Reference to the main application
    private Main mainApp;

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
    
    
    @FXML
	private void handleIraMenores() {
		mainApp.pacientesOverview("Menores");
	}

	@FXML
	private void handleIraAdultos() {
		mainApp.pacientesOverview("Adultos");
	}

	@FXML
	private void handleIraGestantes() {
		mainApp.pacientesOverview("Gestantes");
	}

	@FXML
	private void handleIraExporttar() {
		mainApp.exportarTransaccionesOverview();
	}

	@FXML
	private void handleIraSincronizar() {
		mainApp.showSincronizarAppOverview(false);
	}
	
	@FXML
	private void handleOpenHelp() {
		try {
		     File path = new File ("resources/help/Mango.pdf");
		     Desktop.getDesktop().open(path);
		}catch (IOException ex) {
			ex.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
	    	alert.setTitle("MANGO");
	    	alert.setHeaderText("No se puede abrir archivo de ayuda");
	    	alert.setContentText("error");
	    	alert.showAndWait();
		}
		catch(Exception e){
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
	    	alert.setTitle("MANGO");
	    	alert.setHeaderText("No se puede abrir archivo de ayuda");
	    	alert.setContentText("error");
	    	alert.showAndWait();
		}
	}

    
    @FXML
    private void handleAbout() {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("MANGO");
    	alert.setHeaderText("Monitoreo Alimentario y Nutricional de la Gobernacion de Cundinamarca\n MANGO");
    	alert.setContentText("1.0 - 2017");
    	alert.showAndWait();
    }
    
    @FXML
	private void handleIraCambioClave() {
		mainApp.showCambiarClaveOverview();
	}

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }
}
