package co.gov.cundinamarca.sisvan.view;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;

import co.gov.cundinamarca.sisvan.Main;
import co.gov.cundinamarca.sisvan.model.parameters.Pacientes;
import co.gov.cundinamarca.sisvan.repository.PacientesRepository;
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

public class PacientesFindListOverviewController {
	
	private Main mainApp;
	private Session session;
	private Stage dialogStage;
	PacientesRepository repoPacientes;
	Pacientes pacienteSelect;
	
	@FXML
	TableView<Pacientes> tablePacientes = new TableView();
	@FXML
	TableColumn<Pacientes, String> identificacionColumn = new TableColumn<>("Identificacion");
	@FXML
	TableColumn<Pacientes, String> nombreColumn = new TableColumn<>("Nombre");
	@FXML
	TableColumn<Pacientes, Date> FechaNacimientoColumn = new TableColumn<>("Fecha Nacimiento");
	
	@FXML
    private TextField buscarPacientesField;
	
	@FXML
    private void initialize() {
		tablePacientes.setOnMousePressed(new EventHandler<MouseEvent>() {
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
		            pacienteSelect=(Pacientes) row.getItem();
			    	dialogStage.close();
		        }
		    }
		});
    }
	
	@SuppressWarnings("unchecked")
	private void cargaComponentes(){
		pacienteSelect=new Pacientes();
		pacienteSelect=null;
		tablePacientes.getColumns().addAll(identificacionColumn, nombreColumn, FechaNacimientoColumn);
		identificacionColumn.setCellValueFactory(new PropertyValueFactory<>("pac_Id"));
		nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombreFullPaciente"));
		FechaNacimientoColumn.setCellValueFactory(new PropertyValueFactory<>("pac_Fechanacimiento"));
		
		repoPacientes=new PacientesRepository(session);
    	List<Pacientes> listPacientes=repoPacientes.findPacientesTop(100);
    	Pacientes pacienteFind=new Pacientes();
		Iterator iter = listPacientes.iterator();
		while (iter.hasNext()){
			pacienteFind=(Pacientes) iter.next();
			tablePacientes.getItems().add(pacienteFind);
		}
	}
	
	@FXML
	private void handleBuscarPaciente() {
		tablePacientes.getItems().clear();
		List<Pacientes> listPacientes=repoPacientes.findPacientesByIdentAndName(buscarPacientesField.getText(),100);
		Pacientes pacienteFind=new Pacientes();
		Iterator iter = listPacientes.iterator();
		while (iter.hasNext()){
			pacienteFind=(Pacientes) iter.next();
			tablePacientes.getItems().add(pacienteFind);
		}
	}
	
	@FXML
	private void handleSelectPaciente() {
	    int selectedIndex = tablePacientes.getSelectionModel().getSelectedIndex();
	    if (selectedIndex >= 0) {
	    	pacienteSelect=tablePacientes.getItems().get(selectedIndex);
	    	this.dialogStage.close();
	    } else {
	    	Alert alert = new Alert(AlertType.WARNING);
        	alert.setTitle("Alerta");
        	alert.setHeaderText("Seleccion de Paciente");
        	alert.setContentText("Seleccione un Paciente");
        	alert.showAndWait();
	    }
	}
	
	@FXML
	private void handleCloseWindow() {
	    this.dialogStage.close();
	}
	
	@FXML
	private void handleCleanForm() {
		buscarPacientesField.setText("");
		tablePacientes.getItems().clear();
	}
	
	public Pacientes getPacienteSelect() {
		return pacienteSelect;
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
