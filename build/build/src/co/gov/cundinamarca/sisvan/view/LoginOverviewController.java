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

public class LoginOverviewController {
	
	private Main mainApp;
	private Session session;
	
	@FXML
    private TextField userNameField;
    @FXML
    private TextField passwordField;
    
    private Stage dialogStage;
    private boolean okLogin = false;
	
    
    @FXML
    private void initialize() {
    }
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public boolean isOkLogin() {
        return okLogin;
    }
    
    @FXML
    private void handleIngresar() {
        if (isInputValid()) {
        	String resulValidaUser=validaUsuario();
        	if(resulValidaUser.equals("OK")){
        		okLogin = true;
                dialogStage.close();
        	}else if(resulValidaUser.equals("FAILUPGD")){
        		Alert alert = new Alert(AlertType.ERROR);
	        	alert.setTitle("Error");
	        	alert.setHeaderText("Error en Login");
	        	alert.setContentText("No existe IPS asociada al Usuario");
	        	alert.showAndWait();
        	}else{
	        	Alert alert = new Alert(AlertType.ERROR);
	        	alert.setTitle("Error");
	        	alert.setHeaderText("Error en Login");
	        	alert.setContentText("Acceso Incorrecto");
	        	alert.showAndWait();
        	}
        }
    }
    
    @FXML
	private void handleIraSincronizar() {
		mainApp.showSincronizarAppOverview(true);
	}
    
    private boolean isInputValid() {
        String errorMessage = "";

        if (userNameField.getText() == null || userNameField.getText().length() == 0) {
            errorMessage += "Ingrese Usuario!\n"; 
        }
        if (passwordField.getText() == null || passwordField.getText().length() == 0) {
            errorMessage += "Ingrese Password!\n"; 
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
        	Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Error");
        	alert.setHeaderText("Error en Login");
        	alert.setContentText(errorMessage);

        	alert.showAndWait();
        	return false;
        }
    }
    
    @SuppressWarnings("finally")
	private String validaUsuario(){
    	String idUsuario=userNameField.getText();
    	String password=passwordField.getText();
    	String OkLogin="FAIL";
    	try{
    		Usuarios usuario=new Usuarios();
    		Instituciones upgd=new Instituciones();
    		Institucion_Usuarios institucion_usuario=new Institucion_Usuarios();
    		UsuariosRepository repoUsu=new UsuariosRepository(session);
    		InstitucionesRepository repoInstituciones=new InstitucionesRepository(session);
    		Institucion_UsuariosRepository repoInstitucion_usuario=new Institucion_UsuariosRepository(session);
    		
    		String passwordMD5=CodificacionDigest.codificarBase64(password,"MD5");
    		usuario=repoUsu.validaLogin(idUsuario, passwordMD5);
    		if(usuario!=null){
    			if(usuario.getPer_Usu_Id().equalsIgnoreCase("Alcaldia")){
    				try{
    					upgd=repoInstituciones.findByMunicipio(usuario.getMun_Usu_Id());
    				}catch(NullPointerException e){
    					upgd=null;
    					OkLogin="FAILUPGD";
    				}
    			}else{
    				institucion_usuario=repoInstitucion_usuario.findByUsuarioId(usuario.getUsu_Id());
    				try{
    					upgd=repoInstituciones.findById(institucion_usuario.getIps_Ipsu_Id());
    				}catch(NullPointerException e){
    					upgd=null;
    					OkLogin="FAILUPGD";
    				}
    			}
    			if(upgd!=null){
    				mainApp.setUsuarioApp(usuario);
    				mainApp.setInstitucionApp(upgd);
    				OkLogin="OK";
    			}else{
    				OkLogin="FAILUPGD";
    			}
    		}else{
    			OkLogin="FAILUSER";
    		}
    	}catch(Exception es){
    		es.printStackTrace();
    		OkLogin="FAIL";
    	}
    	finally{
    		return OkLogin;
    	}	
    }
    
    public void setMainApp(Main mainApp, Session session) {
		this.mainApp = mainApp;
		this.session=session;
		userNameField.requestFocus();
    }

}
