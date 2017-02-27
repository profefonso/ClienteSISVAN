package co.gov.cundinamarca.sisvan.view;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;

import co.gov.cundinamarca.sisvan.Main;
import co.gov.cundinamarca.sisvan.model.parameters.Barrio_Veredas;
import co.gov.cundinamarca.sisvan.model.parameters.Departamentos;
import co.gov.cundinamarca.sisvan.model.parameters.Lugar_Residencias;
import co.gov.cundinamarca.sisvan.model.parameters.Municipios;
import co.gov.cundinamarca.sisvan.model.parameters.Paciente_Detalles;
import co.gov.cundinamarca.sisvan.model.parameters.Pacientes;
import co.gov.cundinamarca.sisvan.model.parameters.Sexo;
import co.gov.cundinamarca.sisvan.model.parameters.Tipo_Identificaciones;
import co.gov.cundinamarca.sisvan.model.transactions.Adultos;
import co.gov.cundinamarca.sisvan.model.transactions.Gestantes;
import co.gov.cundinamarca.sisvan.model.transactions.Menores;
import co.gov.cundinamarca.sisvan.repository.AdultosRepository;
import co.gov.cundinamarca.sisvan.repository.Barrio_VeredasRepository;
import co.gov.cundinamarca.sisvan.repository.DepartamentosRepository;
import co.gov.cundinamarca.sisvan.repository.GestantesRepository;
import co.gov.cundinamarca.sisvan.repository.Lugar_ResidenciasRepository;
import co.gov.cundinamarca.sisvan.repository.MenoresRepository;
import co.gov.cundinamarca.sisvan.repository.MunicipiosRepository;
import co.gov.cundinamarca.sisvan.repository.Paciente_DetallesRepository;
import co.gov.cundinamarca.sisvan.repository.PacientesRepository;
import co.gov.cundinamarca.sisvan.repository.Tipo_IdentificacionesRepository;
import co.gov.cundinamarca.sisvan.utilSiiweb.ComboBoxAutoComplete;
import co.gov.cundinamarca.sisvan.utilSiiweb.DatosCalculados;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class PacientesOverviewController {
	
	private Main mainApp;
	private Session session;
	Pacientes paciente;
	Paciente_Detalles paciente_detalles;
	private String irForm;
	private Date fechaConsulta;
	
	PacientesRepository repoPacientes;
	Paciente_DetallesRepository repoPaciente_Detalles;
	Tipo_IdentificacionesRepository repoTipo_Identificaciones;
	DepartamentosRepository repoDepartamentos;
	MunicipiosRepository repoMunicipios;
	Barrio_VeredasRepository repoBarrio_Veredas;
	Lugar_ResidenciasRepository repoLugar_Residencias;
	MenoresRepository repoMenores;
	AdultosRepository repoAdultos;
	GestantesRepository repoGestantes;
	
	String pattern = "dd/MM/yyyy";
	
	@FXML
	private Tab tabMenores;
	@FXML
	private Tab tabAdultos;
	@FXML
	private Tab tabGestantes;
	
	@FXML
	private TextField pacienteIdentificacionField;
	@FXML
	private TextField pacientePrimerNombreField;
	@FXML
	private TextField pacienteSegundoNombreField;
	@FXML
	private TextField pacientePrimerApellidoField;
	@FXML
	private TextField pacienteSegundoApellidoField;
	@FXML
	private TextField detalle_pacienteDireccionField;
	@FXML
	private TextField detalle_pacienteTelefonoField;
	@FXML
	private TextField detalle_pacienteCorreoField;
	@FXML
	private TextField detalle_pacienteCelularField;
	@FXML
	private TextField buscarMenorField;
	@FXML
	private TextField buscarAdultoField;
	@FXML
	private TextField buscarGestanteField;
	
	ObservableList<Sexo> sexoList=FXCollections.observableArrayList(new Sexo("0"), new Sexo("1"), new Sexo("2"));
	
	@FXML
	private ComboBox<Tipo_Identificaciones> tipo_IdentificacionesBox;
	@FXML
	private ComboBox<Departamentos> departamentosBox;
	@FXML
	private ComboBox<Municipios> municipiosBox;
	@FXML
	private ComboBox<Barrio_Veredas> barrio_VeredasBox;
	@FXML
	private ComboBox<Lugar_Residencias> lugar_ResidenciasBox;
	@FXML
	private ComboBox<Sexo> sexoBox;
	
	@FXML
	private DatePicker pacienteFechaNacimientoField;
	@FXML
	private DatePicker pacienteFechaConsultafield;
	
	@FXML
	private Button btnGraficasHistoricasMenores;
	@FXML
	private Button btnGraficasHistoricasGestantes;
	
	//----------------------------------------------------------- Tablas Historicas -----------------------------------//
	@FXML
	TableView<Menores> tableMenores = new TableView();
	@FXML
	TableColumn<Menores, String> identColumn = new TableColumn<>("Identificacion");
	@FXML
	TableColumn<Menores, String> nombreColumn = new TableColumn<>("Nombre Paciente");
	@FXML
	TableColumn<Menores, Date> fechaReporteColumn = new TableColumn<>("Fecha Reporte");
	@FXML
	TableColumn<Menores, Long> idMenorColumn = new TableColumn<>("Id");
	
	//------------------------------------
	
	@FXML
	TableView<Adultos> tableAdultos = new TableView();
	@FXML
	TableColumn<Adultos, String> identAdultoColumn = new TableColumn<>("Identificacion");
	@FXML
	TableColumn<Adultos, String> nombreAdultoColumn = new TableColumn<>("Nombre Paciente");
	@FXML
	TableColumn<Adultos, Date> fechaReporteAdultoColumn = new TableColumn<>("Fecha Reporte");
	@FXML
	TableColumn<Adultos, Long> idAdultoColumn = new TableColumn<>("Id");
	
	//--------------------------------
	
	@FXML
	TableView<Gestantes> tableGestantes = new TableView();
	@FXML
	TableColumn<Gestantes, String> identGestanteColumn = new TableColumn<>("Identificacion");
	@FXML
	TableColumn<Gestantes, String> nombreGestanteColumn = new TableColumn<>("Nombre Paciente");
	@FXML
	TableColumn<Gestantes, Date> fechaReporteGestanteColumn = new TableColumn<>("Fecha Reporte");
	@FXML
	TableColumn<Gestantes, Long> idGestanteColumn = new TableColumn<>("Id");
	
	//--------------------------------
	
	
	@FXML
    private void initialize() {
		paciente=new Pacientes();
		paciente_detalles=new Paciente_Detalles();
    }
	
	@FXML
	private void handleFindPacienteList() {
		setPaciente(mainApp.showPacientesFindListOverview());
	}
	
	@FXML
	private void handleCancelar() {
		mainApp.dashBoardOverview();
	}
	
	@FXML
	private void handleIrGraficasHistoricas() {
		mainApp.graficasHistoricasOverview(paciente);
	}
	
	@FXML
	private void handleIrGraficasHistoricasGestantes() {
		mainApp.graficasHistoricasGestantesOverview(paciente);
	}
	
	@FXML
	private void handleIrADireccion() {
		String textoDireccion=mainApp.showAddressOverview();
		if((!textoDireccion.equals("null"))&&(!textoDireccion.equals(""))&&(!textoDireccion.equals(null))){
			this.detalle_pacienteDireccionField.setText(textoDireccion);
		}
		
	}
	
	@FXML
	private void handleGuardarContinuar() {
		try{
			if(validarForm()){
				if(paciente==null){
					paciente=new Pacientes();
					paciente_detalles=new Paciente_Detalles();
				}
				loadModel();
				if(repoPacientes.savePaciente(paciente)&&(repoPaciente_Detalles.saveDetallePaciente(paciente_detalles))){
					contiunarFormulario();
				}else{
					Alert alert = new Alert(AlertType.ERROR);
		        	alert.setTitle("Error al Guardar");
		        	alert.setHeaderText("Guardar Paciente");
		        	alert.setContentText("No se puede guardar Paciente");
		        	alert.showAndWait();
				}
			}
		}catch(Exception es){
			es.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Error al Guardar");
        	alert.setHeaderText("Guardar Paciente");
        	alert.setContentText(es.toString());
        	alert.showAndWait();
		}
	}

	@SuppressWarnings("unchecked")
	private void cargaComponentes(){
		repoPacientes=new PacientesRepository(session);
		repoPaciente_Detalles=new Paciente_DetallesRepository(session);
		repoTipo_Identificaciones=new Tipo_IdentificacionesRepository(session);
		repoDepartamentos=new DepartamentosRepository(session);
		repoMunicipios=new MunicipiosRepository(session);
		repoBarrio_Veredas=new Barrio_VeredasRepository(session);
		repoLugar_Residencias=new Lugar_ResidenciasRepository(session);
		
		addTextLimiter(pacienteIdentificacionField,17);
		addTextLimiter(pacientePrimerNombreField,28);
		addTextLimiter(pacienteSegundoNombreField,28);
		addTextLimiter(pacientePrimerApellidoField,28);
		addTextLimiter(pacienteSegundoApellidoField,28);
		addTextLimiter(detalle_pacienteDireccionField,99);
		addTextLimiter(detalle_pacienteTelefonoField,17);
		
		detalle_pacienteDireccionField.setDisable(true);
		
		tipo_IdentificacionesBox.setItems(repoTipo_Identificaciones.getListCombo(irForm));
		departamentosBox.setItems(repoDepartamentos.getListCombo());
		municipiosBox.setItems(repoMunicipios.getListCombo());
		barrio_VeredasBox.setItems(repoBarrio_Veredas.getListCombo("0","0"));
		lugar_ResidenciasBox.setItems(repoLugar_Residencias.getListCombo());
		sexoBox.setItems(sexoList);
		ComboBoxAutoComplete<Municipios> municipios=new ComboBoxAutoComplete<Municipios> (municipiosBox);
		ComboBoxAutoComplete<Barrio_Veredas> lugarResidencias=new ComboBoxAutoComplete<Barrio_Veredas> (barrio_VeredasBox);
		
		pacienteFechaNacimientoField.setPromptText(pattern.toLowerCase());

		pacienteFechaNacimientoField.setConverter(new StringConverter<LocalDate>() {
		     DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

		     @Override 
		     public String toString(LocalDate date) {
		         if (date != null) {
		             return dateFormatter.format(date);
		         } else {
		             return "";
		         }
		     }

		     @Override 
		     public LocalDate fromString(String string) {
		         if (string != null && !string.isEmpty()) {
		             return LocalDate.parse(string, dateFormatter);
		         } else {
		             return null;
		         }
		     }
		 });
					
		pacienteFechaConsultafield.setPromptText(pattern.toLowerCase());

		pacienteFechaConsultafield.setConverter(new StringConverter<LocalDate>() {
		     DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

		     @Override 
		     public String toString(LocalDate date) {
		         if (date != null) {
		             return dateFormatter.format(date);
		         } else {
		             return "";
		         }
		     }

		     @Override 
		     public LocalDate fromString(String string) {
		         if (string != null && !string.isEmpty()) {
		             return LocalDate.parse(string, dateFormatter);
		         } else {
		             return null;
		         }
		     }
		 });
		
		LocalDate fechaActual = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		pacienteFechaConsultafield.setValue(fechaActual);
		
		departamentosBox.valueProperty().addListener(new ChangeListener(){
			@Override
		    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				municipiosBox.getItems().clear();
				barrio_VeredasBox.getItems().clear();
				if(departamentosBox.getValue()!=null){
					municipiosBox.setItems(repoMunicipios.findByDptoId(departamentosBox.getValue().getDep_Id()));
					municipios.setItemsAll(municipiosBox);
				}
		     }
		});
		
		municipiosBox.valueProperty().addListener(new ChangeListener(){
			@Override
		    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				barrio_VeredasBox.getItems().clear();
				if(municipiosBox.getValue()!=null){
					barrio_VeredasBox.setItems(repoBarrio_Veredas.getListCombo(departamentosBox.getValue().getDep_Id(),municipiosBox.getValue().getMun_Id()));
					lugarResidencias.setItemsAll(barrio_VeredasBox);
				}
		     }
		});
		
		pacienteIdentificacionField.textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
	            if (!newValue.matches("\\d*")) {
	            	pacienteIdentificacionField.setText(newValue.replaceAll("[^\\d]", ""));
	            }
	        }
	    });
		
		pacienteIdentificacionField.textProperty().addListener((observable, oldValue, newValue) -> {
			findPaciente();
		});
		new ComboBoxAutoComplete<Tipo_Identificaciones>(tipo_IdentificacionesBox);
		new ComboBoxAutoComplete<Departamentos> (departamentosBox);
		new ComboBoxAutoComplete<Municipios> (municipiosBox);
		new ComboBoxAutoComplete<Barrio_Veredas> (barrio_VeredasBox);
		new ComboBoxAutoComplete<Lugar_Residencias> (lugar_ResidenciasBox);
		new ComboBoxAutoComplete<Sexo> (sexoBox);
		
		loadTable();
	}

	public void setPaciente(Pacientes paciente){
		this.paciente=paciente;
		if(this.paciente!=null){
			paciente_detalles=repoPaciente_Detalles.findById(paciente.getPac_Id());
			loadPacienteForm();
			tipo_IdentificacionesBox.setDisable(true);
		}else{
			paciente_detalles=null;
			cleanPacienteForm();
			tipo_IdentificacionesBox.setDisable(false);
		}
	}
	
	public Pacientes getPaciente(){
		return this.paciente;
	}
	
	private void loadPacienteForm(){
		pacienteIdentificacionField.setText(paciente.getPac_Id());
		pacientePrimerNombreField.setText(paciente.getPac_Primernombre());
		pacienteSegundoNombreField.setText(paciente.getPac_Segundonombre());
		pacientePrimerApellidoField.setText(paciente.getPac_Primerapellido());
		pacienteSegundoApellidoField.setText(paciente.getPac_Segundoapellido());
		tipo_IdentificacionesBox.setValue(repoTipo_Identificaciones.findById(paciente.getTid_Pac_Id()));
		departamentosBox.setValue(repoDepartamentos.findById(paciente_detalles.getDep_Pacd_Id()));
		municipiosBox.setValue(repoMunicipios.findById(paciente_detalles.getMun_Pacd_Id(),paciente_detalles.getDep_Pacd_Id()));
		detalle_pacienteDireccionField.setText(paciente_detalles.getPacd_Direccion());
		barrio_VeredasBox.getItems().clear();
		barrio_VeredasBox.setItems(repoBarrio_Veredas.getListCombo(paciente_detalles.getDep_Pacd_Id(),paciente_detalles.getMun_Pacd_Id()));
		barrio_VeredasBox.setValue(repoBarrio_Veredas.findById(paciente_detalles.getMun_Pacd_Id(), paciente_detalles.getBve_Pacd_Id()));
		lugar_ResidenciasBox.setValue(repoLugar_Residencias.findById(paciente_detalles.getLre_Pacd_Id()));
		detalle_pacienteTelefonoField.setText(paciente_detalles.getPacd_Telefono());
		LocalDate fechaNacimiento = paciente.getPac_Fechanacimiento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		pacienteFechaNacimientoField.setValue(fechaNacimiento);
		sexoBox.setValue(new Sexo(paciente.getPac_Sexo()));
		detalle_pacienteCorreoField.setText(paciente_detalles.getPacd_Correo());
		detalle_pacienteCelularField.setText(paciente_detalles.getPacd_Celular());
	}
	
	private void cleanPacienteForm(){
		//pacienteIdentificacionField.setText("");
		//tipo_IdentificacionesBox.setValue(null);
		pacientePrimerNombreField.setText("");
		pacienteSegundoNombreField.setText("");
		pacientePrimerApellidoField.setText("");
		pacienteSegundoApellidoField.setText("");
		departamentosBox.setValue(null);
		municipiosBox.setValue(null);
		detalle_pacienteDireccionField.setText("");
		barrio_VeredasBox.getItems().clear();
		barrio_VeredasBox.setValue(null);
		lugar_ResidenciasBox.setValue(null);
		detalle_pacienteTelefonoField.setText("");
		pacienteFechaNacimientoField.setValue(null);
		sexoBox.setValue(null);
		detalle_pacienteCorreoField.setText("");
		detalle_pacienteCelularField.setText("");
	}
	
	private void loadModel(){
		paciente.setTid_Pac_Id(tipo_IdentificacionesBox.getValue().getTid_Id());
		paciente.setPac_Id(pacienteIdentificacionField.getText());
		paciente.setUsu_Pac_Id(mainApp.getUsuarioApp().getUsu_Id());
		paciente.setPac_Primerapellido(pacientePrimerApellidoField.getText());
		paciente.setPac_Segundoapellido(pacienteSegundoApellidoField.getText());
		paciente.setPac_Primernombre(pacientePrimerNombreField.getText());
		paciente.setPac_Segundonombre(pacienteSegundoNombreField.getText());
		paciente.setPac_Sexo(sexoBox.getValue().getId());
		LocalDate fechaLocal=pacienteFechaNacimientoField.getValue();
		Date dateJ = Date.from(fechaLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());
		paciente.setPac_Fechanacimiento(dateJ);
		paciente.setPac_Estado("A");
		paciente.setPac_Fecha(new Date());
		paciente.setPac_sync("NS");
		
		paciente_detalles.setTid_Pac_Pacd_Id(tipo_IdentificacionesBox.getValue().getTid_Id());
		paciente_detalles.setPac_Pacd_Id(pacienteIdentificacionField.getText());
		paciente_detalles.setPacd_Id((long)1);
		paciente_detalles.setUsu_Pacd_Id(mainApp.getUsuarioApp().getUsu_Id());
		paciente_detalles.setDep_Pacd_Id(departamentosBox.getValue().getDep_Id());
		paciente_detalles.setMun_Pacd_Id(municipiosBox.getValue().getMun_Id());
		paciente_detalles.setBve_Pacd_Id(barrio_VeredasBox.getValue().getBve_Id());
		paciente_detalles.setLre_Pacd_Id(lugar_ResidenciasBox.getValue().getLre_Id());
		paciente_detalles.setPacd_Direccion(detalle_pacienteDireccionField.getText());
		paciente_detalles.setPacd_Telefono(detalle_pacienteTelefonoField.getText());
		paciente_detalles.setPacd_Correo(detalle_pacienteCorreoField.getText());
		paciente_detalles.setPacd_Celular(detalle_pacienteCelularField.getText());
		paciente_detalles.setPacd_Fecha(new Date());
		paciente_detalles.setPacd_sync("NS");
	}
	
	private void findPaciente(){
		setPaciente(repoPacientes.findById(pacienteIdentificacionField.getText()));
	}
	
	private boolean validarForm() {
		boolean okValidate=true;
		String messange="";
		try{
						
			if(pacienteIdentificacionField.getText().equals("")){ okValidate=false; messange=messange+"Ingrese la Identificacion\n";}
			
			if(pacientePrimerNombreField.getText().equals("")){okValidate=false; messange=messange+"Ingrese Primer Nombre\n";}
			
			if(pacientePrimerApellidoField.getText().equals("")){	okValidate=false; messange=messange+"Ingrese Primer Apellido\n"; }
			
			if(detalle_pacienteDireccionField.getText().equals("")){	detalle_pacienteDireccionField.setText("NO REGISTRA"); }
			
			if(detalle_pacienteTelefonoField.getText().equals("")){	okValidate=false; messange=messange+"Ingrese Telefono\n"; }
			
			if(tipo_IdentificacionesBox.getValue()==null){ okValidate=false; messange=messange+"Seleccione Tipo de Identificacion\n"; }
			
			if(departamentosBox.getValue()==null){ okValidate=false; messange=messange+"Seleccione Departamento\n"; }
			
			if(municipiosBox.getValue()==null){ okValidate=false; messange=messange+"Seleccione Municipio\n"; }
			
			if(barrio_VeredasBox.getValue()==null){ okValidate=false; messange=messange+"Seleccione Barrio o Vereda\n"; }
			
			if(lugar_ResidenciasBox.getValue()==null){ okValidate=false; messange=messange+"Seleccione Lugar de Residencia\n"; }
			
			if((pacienteFechaConsultafield.getValue()==null)||(pacienteFechaConsultafield.getValue().equals(""))){
				okValidate=false; messange=messange+"Ingrese la Fecha de Consulta\n";
			}else{
				LocalDate fechaLocal=pacienteFechaConsultafield.getValue();
				fechaConsulta = Date.from(fechaLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());
			}
			
			if(sexoBox.getValue()==null){ 
				okValidate=false; 
				messange=messange+"Seleccione Sexo\n"; 
			}else{
				if(irForm.equals("Gestantes")){
					if(!sexoBox.getValue().getId().equals("1")){
						okValidate=false; 
						messange=messange+"Para Gestantes solo se permite Sexo Femenino\n"; 
					}
				}
			}
			
			if((pacienteFechaNacimientoField.getValue()==null)||(pacienteFechaNacimientoField.getValue().equals(""))){ 
				okValidate=false; 
				messange=messange+"Ingrese la Fecha de Nacimiento\n";
			}else{
				if(!validaEdadPaciente()){
					okValidate=false; 
					messange=messange+"La edad del paciente no corresponde a un registro "+irForm+" \n";
				}
			}
			
			if(!okValidate){
				Alert alert = new Alert(AlertType.ERROR);
	        	alert.setTitle("Pacientes");
	        	alert.setHeaderText("Validacion de formulario");
	        	alert.setContentText(messange);
	        	alert.showAndWait();
			}
		}catch(Exception e){
			Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Pacientes");
        	alert.setHeaderText("Validacion de formulario");
        	alert.setContentText("Error de Validacion");
        	alert.showAndWait();
		}
		
		return okValidate;
	}
	
	private void loadTable() {
		switch (irForm){
		case "Menores":
			tabMenores.setDisable(false);
			tabAdultos.setDisable(true);
			tabGestantes.setDisable(true);
			cargaTablaMenores();
			break;
		case "Adultos":
			tabMenores.setDisable(true);
			tabAdultos.setDisable(false);
			tabGestantes.setDisable(true);
			cargaTablaAdultos();
			break;
		case "Gestantes":
			tabMenores.setDisable(true);
			tabAdultos.setDisable(true);
			tabGestantes.setDisable(false);
			cargaTablaGestantes();
			break;
			default:
				Alert alert = new Alert(AlertType.ERROR);
	        	alert.setTitle("Error al Direccionar");
	        	alert.setHeaderText("No hay formulario");
	        	alert.setContentText("-----");
	        	alert.showAndWait();
				break;
		}
		
	}
	
	private void cargaTablaMenores(){
		repoMenores=new MenoresRepository(session);
		tableMenores.getColumns().addAll(idMenorColumn, identColumn, nombreColumn, fechaReporteColumn);
		idMenorColumn.setCellValueFactory(new PropertyValueFactory<>("mnor_Id"));
		identColumn.setCellValueFactory(new PropertyValueFactory<>("pac_Mnor_Id"));
		nombreColumn.setCellValueFactory(new PropertyValueFactory<>("pacienteNombreFull"));
		fechaReporteColumn.setCellValueFactory(new PropertyValueFactory<>("mnor_Fecha_Reporte"));
		
		List<Menores> listMenores=repoMenores.listadoMenores(100);
    	Menores menorFind=new Menores();
		Iterator iter = listMenores.iterator();
		while (iter.hasNext()){
			menorFind=(Menores) iter.next();
			menorFind.setPacienteNombreFull(repoPacientes.nombreByIdentificacion(menorFind.getPac_Mnor_Id()));
			tableMenores.getItems().add(menorFind);
		}
		
		tableMenores.setOnMousePressed(new EventHandler<MouseEvent>() {
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
		            Menores menorSelect=new Menores();
		            menorSelect=(Menores) row.getItem();
		            mainApp.setPaciente(repoPacientes.findById(menorSelect.getPac_Mnor_Id()));
		    		mainApp.setPaciente_detalle(repoPaciente_Detalles.findById(menorSelect.getPac_Mnor_Id()));
		    		mainApp.setFechaConsulta(menorSelect.getMnor_Fecha_Reporte());
		            mainApp.menoresOverview(menorSelect);
		        }
		    }
		});
	}
	
	private void cargaTablaAdultos(){
		repoAdultos=new AdultosRepository(session);
		tableAdultos.getColumns().addAll(idAdultoColumn, identAdultoColumn, nombreAdultoColumn, fechaReporteAdultoColumn);
		idAdultoColumn.setCellValueFactory(new PropertyValueFactory<>("adu_Id"));
		identAdultoColumn.setCellValueFactory(new PropertyValueFactory<>("pac_Adu_Id"));
		nombreAdultoColumn.setCellValueFactory(new PropertyValueFactory<>("pac_Adu_Nombre"));
		fechaReporteAdultoColumn.setCellValueFactory(new PropertyValueFactory<>("adu_Fecha_Reporte"));
		
		List<Adultos> listAdultos=repoAdultos.listadoAdultos(100);
    	Adultos adultoFind=new Adultos();
		Iterator iter = listAdultos.iterator();
		while (iter.hasNext()){
			adultoFind=(Adultos) iter.next();
			adultoFind.setPac_Adu_Nombre(repoPacientes.nombreByIdentificacion(adultoFind.getPac_Adu_Id()));
			tableAdultos.getItems().add(adultoFind);
		}
		
		tableAdultos.setOnMousePressed(new EventHandler<MouseEvent>() {
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
		            Adultos adultoSelect=new Adultos();
		            adultoSelect=(Adultos) row.getItem();
		            mainApp.setPaciente(repoPacientes.findById(adultoSelect.getPac_Adu_Id()));
		    		mainApp.setPaciente_detalle(repoPaciente_Detalles.findById(adultoSelect.getPac_Adu_Id()));
		    		mainApp.setFechaConsulta(adultoSelect.getAdu_Fecha_Reporte());
		            mainApp.adultosOverview(adultoSelect);
		        }
		    }
		});
	}
	
	private void cargaTablaGestantes(){
		repoGestantes=new GestantesRepository(session);
		tableGestantes.getColumns().addAll(idGestanteColumn, identGestanteColumn, nombreGestanteColumn, fechaReporteGestanteColumn);
		idGestanteColumn.setCellValueFactory(new PropertyValueFactory<>("ges_Id"));
		identGestanteColumn.setCellValueFactory(new PropertyValueFactory<>("pac_Ges_Id"));
		nombreGestanteColumn.setCellValueFactory(new PropertyValueFactory<>("ges_Adu_Nombre"));
		fechaReporteGestanteColumn.setCellValueFactory(new PropertyValueFactory<>("ges_Fecha_Reporte"));
		
		List<Gestantes> listGestantes=repoGestantes.listGestantes(100);
    	Gestantes gestanteFind=new Gestantes();
		Iterator iter = listGestantes.iterator();
		while (iter.hasNext()){
			gestanteFind=(Gestantes) iter.next();
			gestanteFind.setGes_Adu_Nombre(repoPacientes.nombreByIdentificacion(gestanteFind.getPac_Ges_Id()));
			tableGestantes.getItems().add(gestanteFind);
		}
		
		tableGestantes.setOnMousePressed(new EventHandler<MouseEvent>() {
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
		            Gestantes gestantesSelect=new Gestantes();
		            gestantesSelect=(Gestantes) row.getItem();
		            mainApp.setPaciente(repoPacientes.findById(gestantesSelect.getPac_Ges_Id()));
		    		mainApp.setPaciente_detalle(repoPaciente_Detalles.findById(gestantesSelect.getPac_Ges_Id()));
		    		mainApp.setFechaConsulta(gestantesSelect.getGes_Fecha_Reporte());
		            mainApp.gestantesOverview(gestantesSelect);
		        }
		    }
		});
	}
	
	@FXML
	private void handleBuscaMenor() {
		String Identificacion=buscarMenorField.getText();
		tableMenores.getItems().clear();
		List<Menores> listMenores=null;
		if(Identificacion.equals("")){
			listMenores=repoMenores.listadoMenores(100);
		}else{
			listMenores=repoMenores.buscarMenoresByIdentificacion(Identificacion);
		}
		
    	Menores menorFind=new Menores();
		Iterator iter = listMenores.iterator();
		int registrosEncontrados=listMenores.size();
		if(!Identificacion.equals("")){
			if(registrosEncontrados>0){
				setPaciente(repoPacientes.findById(Identificacion));
				btnGraficasHistoricasMenores.setDisable(false);
			}else{
				btnGraficasHistoricasMenores.setDisable(true);
			}
		}else{
			btnGraficasHistoricasMenores.setDisable(true);
		}
		
		while (iter.hasNext()){
			menorFind=(Menores) iter.next();
			menorFind.setPacienteNombreFull(repoPacientes.nombreByIdentificacion(menorFind.getPac_Mnor_Id()));
			tableMenores.getItems().add(menorFind);
		}
	}
	
	@FXML
	private void handleBuscaAdulto() {
		String IdentificacionAdulto=buscarAdultoField.getText();
		tableAdultos.getItems().clear();
		List<Adultos> listAdultos=repoAdultos.buscarAdultoByIdentificacion(IdentificacionAdulto);
    	Adultos adultoFind=new Adultos();
		Iterator iter = listAdultos.iterator();
		while (iter.hasNext()){
			adultoFind=(Adultos) iter.next();
			adultoFind.setPac_Adu_Nombre(repoPacientes.nombreByIdentificacion(adultoFind.getPac_Adu_Id()));
			tableAdultos.getItems().add(adultoFind);
		}
	}
	
	@FXML
	private void handleBuscaGestante() {
		String IdentificacionGestante=buscarGestanteField.getText();
		tableGestantes.getItems().clear();
		List<Gestantes> listGestantes=null;
		if(IdentificacionGestante.equals("")){
			listGestantes=repoGestantes.listGestantes(100);
		}else{
			listGestantes=repoGestantes.buscaGestanteByIdentificacion(IdentificacionGestante);
		}
    	Gestantes gestanteFind=new Gestantes();
		Iterator iter = listGestantes.iterator();
		int registrosEncontrados=listGestantes.size();
		if(!IdentificacionGestante.equals("")){
			if(registrosEncontrados>0){
				setPaciente(repoPacientes.findById(IdentificacionGestante));
				btnGraficasHistoricasGestantes.setDisable(false);
			}else{
				btnGraficasHistoricasGestantes.setDisable(true);
			}
		}else{
			btnGraficasHistoricasGestantes.setDisable(true);
		}
		while (iter.hasNext()){
			gestanteFind=(Gestantes) iter.next();
			gestanteFind.setGes_Adu_Nombre(repoPacientes.nombreByIdentificacion(gestanteFind.getPac_Ges_Id()));
			tableGestantes.getItems().add(gestanteFind);
		}
	}
	
	private boolean validaEdadPaciente(){
		int edadPaciente;
		boolean okValidateEdad=false;
		try{
			if((pacienteFechaNacimientoField.getValue()!=null)&&(pacienteFechaNacimientoField.getValue()!=null)){
				LocalDate bornDate=pacienteFechaNacimientoField.getValue();
				Date dateBorn = Date.from(bornDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
				DatosCalculados datosC=new DatosCalculados();
				edadPaciente=datosC.calculaEdad(dateBorn);
				
				if(edadPaciente<0){
					Alert alert = new Alert(AlertType.ERROR);
		        	alert.setTitle("Edad");
		        	alert.setHeaderText("Error al Validar Edad");
		        	alert.setContentText("Ingrese una Fecha de nacimineto Valida");
		        	alert.showAndWait();
				}else{
				
					switch (irForm){
					case "Menores":
						if(edadPaciente<18){
							okValidateEdad=true;
						}
						break;
					case "Adultos":
						if(edadPaciente>=18){
							okValidateEdad=true;
						}
						break;
					case "Gestantes":
						if(edadPaciente>=9){
							okValidateEdad=true;
						}
						break;
						default:
							Alert alert = new Alert(AlertType.ERROR);
				        	alert.setTitle("Edad");
				        	alert.setHeaderText("Error al Validar Edad");
				        	alert.setContentText("Ingrese una Fecha de nacimineto Valida");
				        	alert.showAndWait();
							break;
					}
				}
			}
		}catch(Exception ed){
			ed.printStackTrace();
		}
		return okValidateEdad;
	}
	
	private void contiunarFormulario() {
		mainApp.setPaciente(paciente);
		mainApp.setPaciente_detalle(paciente_detalles);
		mainApp.setFechaConsulta(fechaConsulta);
		switch (irForm){
		case "Menores":
			mainApp.menoresOverview(null);
			break;
		case "Adultos":
			mainApp.adultosOverview(null);
			break;
		case "Gestantes":
			mainApp.gestantesOverview(null);
			break;
			default:
				Alert alert = new Alert(AlertType.ERROR);
	        	alert.setTitle("Error al Direccionar");
	        	alert.setHeaderText("No hay formulario");
	        	alert.setContentText("-----");
	        	alert.showAndWait();
				break;
		}
		
	}
	
	public void setMainApp(Main main, Session session, String formSiguiente) {
		this.mainApp=main;
		this.session=session;
		this.irForm=formSiguiente;
		cargaComponentes();
		pacienteIdentificacionField.requestFocus();
	}
	
	public static void addTextLimiter(final TextField tf, final int maxLength) {
	    tf.textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	            if (tf.getText().length() > maxLength) {
	                String s = tf.getText().substring(0, maxLength);
	                tf.setText(s);
	            }
	        }
	    });
	}
}
