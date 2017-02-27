package co.gov.cundinamarca.sisvan.view;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;

import co.gov.cundinamarca.sisvan.Main;
import co.gov.cundinamarca.sisvan.model.parameters.Instituciones;
import co.gov.cundinamarca.sisvan.repository.InstitucionesRepository;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class IpsFindListOverviewController {
	
	private Main mainApp;
	private Session session;
	private Stage dialogStage;
	InstitucionesRepository repoInstituciones;
	Instituciones IpsSelect;
	
	@FXML
	TableView<Instituciones> tableInstituciones = new TableView();
	@FXML
	TableColumn<Instituciones, String> codigoColumn = new TableColumn<>("Codigo");
	@FXML
	TableColumn<Instituciones, String> identificacionColumn = new TableColumn<>("Identificacion");
	@FXML
	TableColumn<Instituciones, String> nivelColumn = new TableColumn<>("Nivel");
	@FXML
	TableColumn<Instituciones, String> nombreColumn = new TableColumn<>("Nombre");
	
	@FXML
    private TextField buscarIpsField;
	
	@FXML
    private void initialize() {
		tableInstituciones.setOnMousePressed(new EventHandler<MouseEvent>() {
		    @Override 
		    public void handle(MouseEvent event) {
		        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
		            Node node = ((Node) event.getTarget()).getParent();
		            TableRow row;
		            if (node instanceof TableRow) {
		                row = (TableRow) node;
		            } else {
		                // clicking on text part
		                row = (TableRow) node.getParent();
		            }
		            IpsSelect=(Instituciones) row.getItem();
			    	dialogStage.close();
		        }
		    }
		});
    }
	
	@SuppressWarnings("unchecked")
	private void cargaComponentes(){
		IpsSelect=new Instituciones();
		IpsSelect=null;
		tableInstituciones.getColumns().addAll(codigoColumn, identificacionColumn, nivelColumn, nombreColumn);
		codigoColumn.setCellValueFactory(new PropertyValueFactory<>("ips_Id"));
		identificacionColumn.setCellValueFactory(new PropertyValueFactory<>("ips_Identificacion"));
		nivelColumn.setCellValueFactory(new PropertyValueFactory<>("ips_Nivel"));
		nombreColumn.setCellValueFactory(new PropertyValueFactory<>("ips_Nombre"));
		
		repoInstituciones=new InstitucionesRepository(session);
    	List<Instituciones> listInstituciones=repoInstituciones.findInstirucionesTop(100);
    	Instituciones institucionFind=new Instituciones();
		Iterator iter = listInstituciones.iterator();
		while (iter.hasNext()){
			institucionFind=(Instituciones) iter.next();
			tableInstituciones.getItems().add(institucionFind);
		}
	}
	
	@FXML
	private void handleBuscarInstitucion() {
		tableInstituciones.getItems().clear();
		List<Instituciones> listInstituciones=repoInstituciones.findInstitucionesByIdentAndName(buscarIpsField.getText(),100);
    	Instituciones institucionesFind=new Instituciones();
		Iterator iter = listInstituciones.iterator();
		while (iter.hasNext()){
			institucionesFind=(Instituciones) iter.next();
			tableInstituciones.getItems().add(institucionesFind);
		}
	}
	
	@FXML
	private void handleSelectInstitucion() {
	    int selectedIndex = tableInstituciones.getSelectionModel().getSelectedIndex();
	    if (selectedIndex >= 0) {
	    	IpsSelect=tableInstituciones.getItems().get(selectedIndex);
	    	this.dialogStage.close();
	    } else {
	    	Alert alert = new Alert(AlertType.WARNING);
        	alert.setTitle("Alerta");
        	alert.setHeaderText("Seleccion de IPS");
        	alert.setContentText("Seleccione una IPS");
        	alert.showAndWait();
	    }
	}
	
	@FXML
	private void handleCloseWindow() {
	    this.dialogStage.close();
	}
	
	@FXML
	private void handleCleanForm() {
		buscarIpsField.setText("");
		tableInstituciones.getItems().clear();
	}
	
	
	
	public Instituciones getIpsSelect() {
		return IpsSelect;
	}
	
	
	
	public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
	
	public void setMainApp(Main mainApp, Session session) {
		this.mainApp = mainApp;
		this.session=session;
		cargaComponentes();
    }

}
