package co.gov.cundinamarca.sisvan.view;

import java.sql.Connection;

import org.hibernate.Session;

import co.gov.cundinamarca.sisvan.Main;
import co.gov.cundinamarca.sisvan.model.parameters.Usuarios;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class UsuariosOverviewController {
	
	private Main mainApp;
	private Session session;
	
	@FXML
    private TableView<Usuarios> usuariosTable;
    @FXML
    private TableColumn<Usuarios, String> usuarioColumn;
    @FXML
    private TableColumn<Usuarios, String> NombreColumn;
    
    private ObservableList<Usuarios> usuariosData = FXCollections.observableArrayList();
    
    
    
    public UsuariosOverviewController() {
    	   	
	}
    
    @FXML
    private void initialize() {
        
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(Main mainApp, Session session) {
        this.mainApp = mainApp;
        this.session=session;

        // Add observable list data to the table
        usuariosTable.setItems(usuariosData);
    }
    
}
