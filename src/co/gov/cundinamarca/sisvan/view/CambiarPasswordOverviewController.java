package co.gov.cundinamarca.sisvan.view;

import java.sql.Connection;

import org.hibernate.Session;

import co.gov.cundinamarca.sisvan.Main;
import co.gov.cundinamarca.sisvan.model.parameters.Institucion_Usuarios;
import co.gov.cundinamarca.sisvan.model.parameters.Instituciones;
import co.gov.cundinamarca.sisvan.model.parameters.Usuarios;
import co.gov.cundinamarca.sisvan.repository.Institucion_UsuariosRepository;
import co.gov.cundinamarca.sisvan.repository.InstitucionesRepository;
import co.gov.cundinamarca.sisvan.repository.UsuariosRepository;
import co.gov.cundinamarca.sisvan.utilSiiweb.CodificacionDigest;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CambiarPasswordOverviewController {
	
	private Main mainApp;
	private Session session;
	
	@FXML
    private TextField passwordActualField;
    @FXML
    private TextField newpasswordField;
    @FXML
    private TextField confirmaNewpasswordField;
    
    private Stage dialogStage;
       
    @FXML
    private void initialize() {
    }
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
        
    @FXML
    private void handleCambiarPassword() {
        if (isInputValid()) {
        	if(cambiaClave()){
        		Alert alert = new Alert(AlertType.CONFIRMATION);
	        	alert.setTitle("Cambio de clave");
	        	alert.setHeaderText("Clave");
	        	alert.setContentText("¡Clave modificada con éxito!");
	        	alert.showAndWait();
                dialogStage.close();
        	}
        }
    }
    
    @FXML
	private void handleCancelar() {
		dialogStage.close();
	}
    
    private boolean isInputValid() {
        String errorMessage = "";

        if (passwordActualField.getText() == null || passwordActualField.getText().length() == 0) {
            errorMessage += "Ingrese clave actual!\n"; 
        }else if (newpasswordField.getText() == null || newpasswordField.getText().length() == 0) {
            errorMessage += "Ingrese nueva clave!\n"; 
        }else if(newpasswordField.getText().length()<5){
        	errorMessage += "El nuevo password debe contener al menos 6 caracteres!\n";
        }else if (confirmaNewpasswordField.getText() == null || confirmaNewpasswordField.getText().length() == 0) {
            errorMessage += "Ingrese confirmación nueva clave!\n"; 
        }else if (!newpasswordField.getText().equals(confirmaNewpasswordField.getText())) {
            errorMessage += "Nueva clave no coincide en la confirmación!\n"; 
        }else if(newpasswordField.getText().equals(passwordActualField.getText())){
        	errorMessage += "El nuevo password debe ser diferente a actual!\n";
        }
        
        if (errorMessage.length() == 0) {
            return true;
        } else {
        	Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Error");
        	alert.setHeaderText("Error cambio de clave");
        	alert.setContentText(errorMessage);

        	alert.showAndWait();
        	return false;
        }
    }
    
    @SuppressWarnings("finally")
	private boolean cambiaClave(){
    	String claveActual=passwordActualField.getText();
    	String claveNueva=newpasswordField.getText();
    	boolean okCambioClave=false;
    	try{
    		Usuarios usuario=new Usuarios();
    		UsuariosRepository repoUsu=new UsuariosRepository(session);
    		String idUsuario=mainApp.getUsuarioApp().getUsu_Id();
    		String passwordMD5=CodificacionDigest.codificarBase64(claveActual,"MD5");
    		usuario=repoUsu.validaLogin(idUsuario, passwordMD5);
    		if(usuario!=null){
    			String passwordMD5Nueva=CodificacionDigest.codificarBase64(claveNueva,"MD5");
    			usuario.setUsu_Clave(passwordMD5Nueva);
    			repoUsu.saveUsuario(usuario);
    			okCambioClave= true;
    		}else{
	        	Alert alert = new Alert(AlertType.ERROR);
	        	alert.setTitle("Error cambio de clave");
	        	alert.setHeaderText("Error en cambio de clave");
	        	alert.setContentText("Calve actual incorrecta");
	        	alert.showAndWait();
        	}
    	}catch(Exception es){
    		es.printStackTrace();
    		Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Error cambio de clave");
        	alert.setHeaderText("Error en cambio de clave");
        	alert.setContentText(es.toString());
        	alert.showAndWait();
    	}finally{
    		return okCambioClave;
    	}	
    }
    
    public void setMainApp(Main mainApp, Session session) {
		this.mainApp = mainApp;
		this.session=session;
		passwordActualField.requestFocus();
    }

}
