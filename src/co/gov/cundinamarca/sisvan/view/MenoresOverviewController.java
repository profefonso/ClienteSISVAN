package co.gov.cundinamarca.sisvan.view;

import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.hibernate.Session;

import co.gov.cundinamarca.sisvan.Main;
import co.gov.cundinamarca.sisvan.model.parameters.Actividades;
import co.gov.cundinamarca.sisvan.model.parameters.Dato_Dinamicos;
import co.gov.cundinamarca.sisvan.model.parameters.Desarrollos;
import co.gov.cundinamarca.sisvan.model.parameters.Educacion_Menores;
import co.gov.cundinamarca.sisvan.model.parameters.Entidades;
import co.gov.cundinamarca.sisvan.model.parameters.Especialidades;
import co.gov.cundinamarca.sisvan.model.parameters.Lactancias;
import co.gov.cundinamarca.sisvan.model.parameters.Micronutrientes;
import co.gov.cundinamarca.sisvan.model.parameters.Municipios;
import co.gov.cundinamarca.sisvan.model.parameters.Paciente_Detalles;
import co.gov.cundinamarca.sisvan.model.parameters.Pacientes;
import co.gov.cundinamarca.sisvan.model.parameters.Programas;
import co.gov.cundinamarca.sisvan.model.parameters.Provincias;
import co.gov.cundinamarca.sisvan.model.parameters.Regimenes;
import co.gov.cundinamarca.sisvan.model.parameters.SelectSiNo;
import co.gov.cundinamarca.sisvan.model.parameters.Tipo_Consultas;
import co.gov.cundinamarca.sisvan.model.parameters.Tipo_Poblaciones;
import co.gov.cundinamarca.sisvan.model.transactions.Menores;
import co.gov.cundinamarca.sisvan.repository.ActividadesRepository;
import co.gov.cundinamarca.sisvan.repository.Dato_DinamicosRepository;
import co.gov.cundinamarca.sisvan.repository.DesarrollosRepository;
import co.gov.cundinamarca.sisvan.repository.Educacion_MenoresRepository;
import co.gov.cundinamarca.sisvan.repository.EntidadesRepository;
import co.gov.cundinamarca.sisvan.repository.EspecialidadesRepository;
import co.gov.cundinamarca.sisvan.repository.LactanciasRepository;
import co.gov.cundinamarca.sisvan.repository.MenoresRepository;
import co.gov.cundinamarca.sisvan.repository.MicronutrientesRepository;
import co.gov.cundinamarca.sisvan.repository.MunicipiosRepository;
import co.gov.cundinamarca.sisvan.repository.Paciente_DetallesRepository;
import co.gov.cundinamarca.sisvan.repository.PacientesRepository;
import co.gov.cundinamarca.sisvan.repository.ProgramasRepository;
import co.gov.cundinamarca.sisvan.repository.ProvinciasRepository;
import co.gov.cundinamarca.sisvan.repository.RegimenesRepository;
import co.gov.cundinamarca.sisvan.repository.Tipo_ConsultasRepository;
import co.gov.cundinamarca.sisvan.repository.Tipo_PoblacionesRepository;
import co.gov.cundinamarca.sisvan.utilSiiweb.ComboBoxAutoComplete;
import co.gov.cundinamarca.sisvan.utilSiiweb.DatosCalculados;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.StringConverter;
import javafx.scene.control.Alert.AlertType;

public class MenoresOverviewController {
	
	private Main mainApp;
	private Menores menor;
	private Pacientes paciente;
	private Paciente_Detalles pacientes_detalle;
	private Dato_Dinamicos dinamicos;
	private Date fechaConsulta;
	private Session session;
	
	String scriptGraphicAgeSize;
	String scriptGraphicWeightAge;
	String scriptGraphicHeadAge;
	String scriptGraphicImcAge;
	String scriptGraphicWeightSize;
	
	MenoresRepository repoMenores;
	PacientesRepository repoPacientes;
	Paciente_DetallesRepository repoPaciente_Detalles;
	Tipo_ConsultasRepository repoTipo_Consultas;
	LactanciasRepository repoLactancias;
	Tipo_PoblacionesRepository repoTipo_Poblaciones;
	ProgramasRepository repoProgramas;
	MicronutrientesRepository repoMicronutrientes;
	RegimenesRepository repoRegimenes;
	EntidadesRepository repoEntidades;
	DesarrollosRepository repoDesarrollos;
	ActividadesRepository repoActividades;
	EspecialidadesRepository repoEspecialidades;
	Educacion_MenoresRepository repoEducacion_Menores;
	MunicipiosRepository repoMunicipios;
	ProvinciasRepository repoProvincias;
	Dato_DinamicosRepository repoDinamicos;
	DatosCalculados datosCaculados;
	
	
	DecimalFormat format = new DecimalFormat( "#.0" );
	String pattern = "dd/MM/yyyy";
	
	@FXML
	private TabPane tabPane;
	
	@FXML
	private TextField pacienteIdentificacionField;
	@FXML
	private TextField pacienteNombreField;
	@FXML
	private TextField pacienteFechaNacimientoField;
	@FXML
	private TextField pacienteSexoField;
	@FXML
	private TextField menorPesoField;
	@FXML
	private TextField menorTallaField;
	@FXML
	private TextField menorPerimetroCefalicoField;
	@FXML
	private TextField menorCircunferenciaBrazoField;
	@FXML
	private TextField menorTiempoLactanciaField;
	@FXML
	private TextField calculaEdadField;
	@FXML
	private TextField calculaEdadAproximadaField;
	@FXML
	private TextField zScoreAgeSizeField;
	@FXML
	private TextField iMCField;
	@FXML
	private TextField zScoreAgeIMCField;
	@FXML
	private TextField momentoVitalField;
	@FXML
	private TextField zScoreWeightAgeField;
	@FXML
	private TextField headCircunferenceAgeField;
	@FXML
	private TextField zScoreWeightSizeField;
	@FXML
	private TextField armCircunferenceAgeField;
	@FXML
	private TextField proviciaNotificacionField;
	@FXML
	private TextField proviciaResidenciaField;
	
	@FXML
	private TextField disponibleUnoField;
	@FXML
	private TextField disponibleDosField;
	@FXML
	private TextField disponibleTresField;
	@FXML
	private TextField disponibleCuatroField;
	
	@FXML
	private Label disponibleUnoLabel;
	@FXML
	private Label disponibleDosLabel;
	@FXML
	private Label disponibleTresLabel;
	@FXML
	private Label disponibleCuatroLabel;
	
	ObservableList<SelectSiNo> selectSiNoList=FXCollections.observableArrayList(new SelectSiNo("1","Si"), new SelectSiNo("2","No"));
	
	@FXML
	private ComboBox<SelectSiNo> controlBox;
	@FXML
	private ComboBox<Tipo_Consultas> tipo_ConsultasBox;
	@FXML
	private ComboBox<Lactancias> lactanciasBox;
	@FXML
	private ComboBox<Tipo_Poblaciones> tipo_PoblacionesBox;
	@FXML
	private ComboBox<SelectSiNo> paqueteNutricionalBox;
	@FXML
	private ComboBox<Programas> programasBox;
	@FXML
	private ComboBox<Micronutrientes> micronutrientesBox;
	@FXML
	private ComboBox<Regimenes> regimenesBox;
	@FXML
	private ComboBox<Entidades> entidadesBox;
	@FXML
	private ComboBox<Desarrollos> desarrollosBox;
	@FXML
	private ComboBox<SelectSiNo> eraBox;
	@FXML
	private ComboBox<SelectSiNo> edaBox;
	@FXML
	private ComboBox<SelectSiNo> desparacitacionBox;
	@FXML
	private ComboBox<Actividades> actividadesBox;
	@FXML
	private ComboBox<Especialidades> especialidadesBox;
	@FXML
	private ComboBox<Educacion_Menores> educacion_MenoresBox;
	
	@FXML
	private WebView webView;
	private WebEngine engine;
	
	@FXML
    private void initialize() {
		engine=webView.getEngine();
		engine.setJavaScriptEnabled(true);
		engine.load(this.getClass().getResource("htmlView/index.html").toExternalForm());
    }
	
	@FXML
	private void handleGuardar() {
		try{
			if(validarForm()){
				if(menor==null){
					menor=new Menores();
				}
				loadModel();
				if((repoMenores.saveMenor(menor))&&(repoPaciente_Detalles.saveDetallePaciente(pacientes_detalle))){
					Alert alert = new Alert(AlertType.CONFIRMATION);
		        	alert.setTitle("Menores");
		        	alert.setHeaderText("Guardar Menor");
		        	alert.setContentText("Se Guardo el Menor Correctamente");
		        	alert.showAndWait();
				}else{
					Alert alert = new Alert(AlertType.ERROR);
		        	alert.setTitle("Menores");
		        	alert.setHeaderText("Error al Guardar Menor");
		        	alert.setContentText("No se puede guardar Menor");
		        	alert.showAndWait();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Menores");
        	alert.setHeaderText("Error al Guardar Menor");
        	alert.setContentText("No se puede guardar Menor "+e.toString());
        	alert.showAndWait();
		}
	}
	
	@FXML
	private void handleCancelar() {
		mainApp.dashBoardOverview();
	}
	
	@FXML
	private void handleIrGraphicsAgeSize() {
		mainApp.showGraphicsOverview(scriptGraphicAgeSize);
	}
	
	@FXML
	private void handleIrGraphicsWeightAge() {
		mainApp.showGraphicsOverview(scriptGraphicWeightAge);
	}
	
	@FXML
	private void handlerIrGraphicsHeadAge(){
		mainApp.showGraphicsOverview(scriptGraphicHeadAge);
	}
	
	@FXML
	private void handleIrGraphicsImcAge() {
		mainApp.showGraphicsOverview(scriptGraphicImcAge);
	}
	
	@FXML
	private void handleIrGraphicsWeightSize() {
		mainApp.showGraphicsOverview(scriptGraphicWeightSize);
	}
	
	private void cargaComponentes(){
		menor=new Menores();
		repoMenores=new MenoresRepository(session);
		repoPacientes=new PacientesRepository(session);
		repoPaciente_Detalles=new Paciente_DetallesRepository(session);
		repoTipo_Consultas=new Tipo_ConsultasRepository(session);
		repoLactancias=new LactanciasRepository(session);			
		repoTipo_Poblaciones=new Tipo_PoblacionesRepository(session);
		repoProgramas=new ProgramasRepository(session);	
		repoMicronutrientes=new MicronutrientesRepository(session);
		repoRegimenes=new RegimenesRepository(session);
		repoEntidades=new EntidadesRepository(session);
		repoDesarrollos=new DesarrollosRepository(session);
		repoActividades=new ActividadesRepository(session);
		repoEspecialidades=new EspecialidadesRepository(session);			
		repoEducacion_Menores=new Educacion_MenoresRepository(session);
		repoMunicipios=new MunicipiosRepository(session);
		repoProvincias=new ProvinciasRepository(session);
		repoDinamicos=new Dato_DinamicosRepository(session);
		datosCaculados=new DatosCalculados();
		
		addTextLimiter(menorPesoField,5);
		addTextLimiter(menorTallaField,5);
		addTextLimiter(menorPerimetroCefalicoField,5);
		addTextLimiter(menorCircunferenciaBrazoField,5);
		addTextLimiter(menorTiempoLactanciaField,3);
		
		//Campos Disponibles
		disponibleUnoField.setVisible(false);
		disponibleDosField.setVisible(false);
		disponibleTresField.setVisible(false);
		disponibleCuatroField.setVisible(false);
		
		disponibleUnoLabel.setVisible(false);
		disponibleDosLabel.setVisible(false);
		disponibleTresLabel.setVisible(false);
		disponibleCuatroLabel.setVisible(false);
		
		controlBox.setItems(selectSiNoList);
		tipo_ConsultasBox.setItems(repoTipo_Consultas.getListCombo("Menores"));
		tipo_PoblacionesBox.setItems(repoTipo_Poblaciones.getListCombo());
		lactanciasBox.setItems(repoLactancias.getListCombo());
		paqueteNutricionalBox.setItems(selectSiNoList);
		programasBox.setItems(repoProgramas.getListCombo("Menores"));
		micronutrientesBox.setItems(repoMicronutrientes.getListCombo("Menores"));
		regimenesBox.setItems(repoRegimenes.getListCombo());
		entidadesBox.setItems(repoEntidades.getListCombo());
		desarrollosBox.setItems(repoDesarrollos.getListCombo());
		actividadesBox.setItems(repoActividades.getListCombo());
		especialidadesBox.setItems(repoEspecialidades.getListCombo("Menores"));
		educacion_MenoresBox.setItems(repoEducacion_Menores.getListCombo());
		eraBox.setItems(selectSiNoList);
		edaBox.setItems(selectSiNoList);
		desparacitacionBox.setItems(selectSiNoList);
		
		menorPesoField.setTextFormatter( new TextFormatter<>(c ->{
		    if ( c.getControlNewText().isEmpty() ){
		        return c;
		    }
		    ParsePosition parsePosition = new ParsePosition( 0 );
		    Object object = format.parse( c.getControlNewText(), parsePosition );

		    if ( object == null || parsePosition.getIndex() < c.getControlNewText().length() ){
		        return null;
		    }else{
		        return c;
		    }
		}));
		
		menorTallaField.setTextFormatter( new TextFormatter<>(c ->{
		    if ( c.getControlNewText().isEmpty() ){
		        return c;
		    }
		    ParsePosition parsePosition = new ParsePosition( 0 );
		    Object object = format.parse( c.getControlNewText(), parsePosition );

		    if ( object == null || parsePosition.getIndex() < c.getControlNewText().length() ){
		        return null;
		    }else{
		        return c;
		    }
		}));
		
		menorPerimetroCefalicoField.setTextFormatter( new TextFormatter<>(c ->{
		    if ( c.getControlNewText().isEmpty() ){
		        return c;
		    }
		    ParsePosition parsePosition = new ParsePosition( 0 );
		    Object object = format.parse( c.getControlNewText(), parsePosition );

		    if ( object == null || parsePosition.getIndex() < c.getControlNewText().length() ){
		        return null;
		    }else{
		        return c;
		    }
		}));
		
		menorCircunferenciaBrazoField.setTextFormatter( new TextFormatter<>(c ->{
		    if ( c.getControlNewText().isEmpty() ){
		        return c;
		    }
		    ParsePosition parsePosition = new ParsePosition( 0 );
		    Object object = format.parse( c.getControlNewText(), parsePosition );

		    if ( object == null || parsePosition.getIndex() < c.getControlNewText().length() ){
		        return null;
		    }else{
		        return c;
		    }
		}));
		
		menorTiempoLactanciaField.setTextFormatter( new TextFormatter<>(c ->{
		    if ( c.getControlNewText().isEmpty() ){
		        return c;
		    }
		    ParsePosition parsePosition = new ParsePosition( 0 );
		    Object object = format.parse( c.getControlNewText(), parsePosition );

		    if ( object == null || parsePosition.getIndex() < c.getControlNewText().length() ){
		        return null;
		    }else{
		        return c;
		    }
		}));
		
		tabPane.getSelectionModel().selectedItemProperty().addListener(
			    new ChangeListener<Tab>() {
			        @Override
			        public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab t1) {
			        	calculaDatos();
			        }
			    }
			);
		
		new ComboBoxAutoComplete<SelectSiNo> (controlBox);
		new ComboBoxAutoComplete<Tipo_Consultas> (tipo_ConsultasBox);
		new ComboBoxAutoComplete<Lactancias> (lactanciasBox);
		new ComboBoxAutoComplete<Tipo_Poblaciones> (tipo_PoblacionesBox);
		new ComboBoxAutoComplete<SelectSiNo> (paqueteNutricionalBox);
		new ComboBoxAutoComplete<Programas> (programasBox);	
		new ComboBoxAutoComplete<Micronutrientes> (micronutrientesBox);
		new ComboBoxAutoComplete<Regimenes> (regimenesBox);	
		new ComboBoxAutoComplete<Entidades> (entidadesBox);	
		new ComboBoxAutoComplete<Desarrollos> (desarrollosBox);	
		new ComboBoxAutoComplete<SelectSiNo> (eraBox);	
		new ComboBoxAutoComplete<SelectSiNo> (edaBox);	
		new ComboBoxAutoComplete<SelectSiNo> (desparacitacionBox);	
		new ComboBoxAutoComplete<Actividades> (actividadesBox);	
		new ComboBoxAutoComplete<Especialidades> (especialidadesBox);	
		new ComboBoxAutoComplete<Educacion_Menores> (educacion_MenoresBox);
	}

	private void loadFormMenores(){
		pacienteIdentificacionField.setText(paciente.getPac_Id());
		pacienteNombreField.setText(paciente.getNombreFullPaciente());
		String newstring = new SimpleDateFormat("yyyy-MM-dd").format(paciente.getPac_Fechanacimiento());
		pacienteFechaNacimientoField.setText(newstring);
		String sexoPaciente="Indeterminado";
		if(paciente.getPac_Sexo().equals("0")){
			sexoPaciente="Masculino";
		}else if(paciente.getPac_Sexo().equals("1")){
			sexoPaciente="Femenino";
		}
		pacienteSexoField.setText(sexoPaciente);
		tipo_PoblacionesBox.setValue(repoTipo_Poblaciones.findById(pacientes_detalle.getTpo_Pacd_Id()));
		regimenesBox.setValue(repoRegimenes.findById(pacientes_detalle.getReg_Pacd_Id()));
		entidadesBox.setValue(repoEntidades.findById(pacientes_detalle.getEps_Pacd_Id()));
		
		habilitaCamposEdad();
	}
	
	private void habilitaCamposEdad() {
		int edadPaciente;
		Date dateBorn = paciente.getPac_Fechanacimiento();
		DatosCalculados datosC=new DatosCalculados();
		edadPaciente=datosC.calculaEdad(dateBorn);
		Double edadMesesPaciente=0.0;
		try{
			edadMesesPaciente=Double.valueOf(datosC.calulaEdadAproximadaOnlyNumber(dateBorn));
		}catch(Exception e){
			
		}
		
		if(edadMesesPaciente>2){
			controlBox.setValue(null);
			controlBox.setDisable(true);
		}
		if(edadMesesPaciente<6){
			menorCircunferenciaBrazoField.setText("0");
			menorCircunferenciaBrazoField.setDisable(true);
		}
		if(edadPaciente>=3){
			controlBox.setValue(null);
			controlBox.setDisable(true);
			lactanciasBox.setValue(null);
			lactanciasBox.setDisable(true);
			menorTiempoLactanciaField.setText("0");
			menorTiempoLactanciaField.setDisable(true);
		}
		if(edadPaciente>=5){
			menorPerimetroCefalicoField.setText("0");
			menorPerimetroCefalicoField.setDisable(true);
			menorCircunferenciaBrazoField.setText("0");
			menorCircunferenciaBrazoField.setDisable(true);
		}
		if(edadPaciente>=10){
			desarrollosBox.setValue(null);
			desarrollosBox.setDisable(true);
			edaBox.setValue(null);
			edaBox.setDisable(true);
			eraBox.setValue(null);
			eraBox.setDisable(true);
			desparacitacionBox.setValue(null);
			desparacitacionBox.setDisable(true);
		}
	}
	
	private void cargaMenorForm() {
		try{
			if((menor.getMnor_Control()!=null)&&(!menor.getMnor_Control().equals(""))){
				controlBox.setValue(new SelectSiNo(menor.getMnor_Control()));
			}
			
			tipo_ConsultasBox.setValue(repoTipo_Consultas.findById(menor.getTco_Mnor_Id()));
			
			if((menor.getLac_Mnor_Id()!=null)&&(!menor.getLac_Mnor_Id().equals(""))){
				lactanciasBox.setValue(repoLactancias.findById(menor.getLac_Mnor_Id()));
			}
			
			paqueteNutricionalBox.setValue(new SelectSiNo(menor.getMnor_Paquete_Nutricional()));
			programasBox.setValue(repoProgramas.findById(menor.getPrg_Mnor_Id()));
			micronutrientesBox.setValue(repoMicronutrientes.findById(menor.getMnu_Mnor_Id()));
			
			if((menor.getDes_Mnor_Id()!=null)&&(!menor.getDes_Mnor_Id().equals(""))){
				desarrollosBox.setValue(repoDesarrollos.findById(menor.getDes_Mnor_Id()));
			}
			
			actividadesBox.setValue(repoActividades.findById(menor.getAct_Mnor_Id()));
			especialidadesBox.setValue(repoEspecialidades.findById(menor.getEsp_Mnor_Id()));
			educacion_MenoresBox.setValue(this.repoEducacion_Menores.findById(menor.getEme_Mnor_Id()));
			
			if((menor.getMnor_Era()!=null)&&(!menor.getMnor_Era().equals(""))){
				eraBox.setValue(new SelectSiNo(menor.getMnor_Era()));
			}
			
			if((menor.getMnor_Eda()!=null)&&(!menor.getMnor_Eda().equals(""))){
				edaBox.setValue(new SelectSiNo(menor.getMnor_Eda()));
			}
			
			if((menor.getMnor_Desparasitacion()!=null)&&(!menor.getMnor_Desparasitacion().equals(""))){
				desparacitacionBox.setValue(new SelectSiNo(menor.getMnor_Desparasitacion()));
			}
			
			String pesoMenor=InversaformatVal(menor.getMnor_Peso());
			menorPesoField.setText(pesoMenor);
			String tallaMenor=InversaformatVal(menor.getMnor_Talla());
			menorTallaField.setText(tallaMenor);
			String perimetroCefalMenor=InversaformatVal(menor.getMnor_Perimetro_Cefalico());
			menorPerimetroCefalicoField.setText(perimetroCefalMenor);
			String circunferenciaBrazoMenor=InversaformatVal(menor.getMnor_Brazo());
			menorCircunferenciaBrazoField.setText(circunferenciaBrazoMenor);
			String tiempoLactanciaMenor=menor.getMnor_Tiempo_Lactancia().toString();
			menorTiempoLactanciaField.setText(tiempoLactanciaMenor);
			
			if(disponibleUnoField.isVisible()){
				disponibleUnoField.setText(menor.getMnor_Campo1());
			}
			
			if(disponibleDosField.isVisible()){
				disponibleDosField.setText(menor.getMnor_Campo2());
			}
			
			if(disponibleTresField.isVisible()){
				disponibleTresField.setText(menor.getMnor_Campo3());
			}
			
			if(disponibleCuatroField.isVisible()){
				disponibleCuatroField.setText(menor.getMnor_Campo4());
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private String InversaformatVal(Double iniVal){
		try{
			return String.valueOf(iniVal).replace(".", ",");
		}catch(Exception t){
			t.printStackTrace();
			return "";
		}
	}
	
	private void loadModel(){
		pacientes_detalle.setTpo_Pacd_Id(tipo_PoblacionesBox.getValue().getTpo_Id());
		pacientes_detalle.setReg_Pacd_Id(regimenesBox.getValue().getReg_Id());
		pacientes_detalle.setEps_Pacd_Id(entidadesBox.getValue().getEps_Id());
		
		menor.setIps_Mnor_Id(mainApp.getInstitucionApp().getIps_Id());
		menor.setTid_Pac_Mnor_Id(paciente.getTid_Pac_Id());
		menor.setPac_Mnor_Id(paciente.getPac_Id());
		menor.setPacd_Mnor_Id(pacientes_detalle.getPacd_Id());
		
		if(!lactanciasBox.isDisable()){
			menor.setLac_Mnor_Id(lactanciasBox.getValue().getLac_Id());
		}
		
		menor.setPrg_Mnor_Id(programasBox.getValue().getPrg_Id());
		menor.setMnu_Mnor_Id(micronutrientesBox.getValue().getMnu_Id());
		menor.setAct_Mnor_Id(actividadesBox.getValue().getAct_Id());
		menor.setTco_Mnor_Id(tipo_ConsultasBox.getValue().getTco_Id());
		
		if(!desarrollosBox.isDisable()){
			menor.setDes_Mnor_Id(desarrollosBox.getValue().getDes_Id());
		}
		
		menor.setEsp_Mnor_Id(especialidadesBox.getValue().getEsp_Id());
		menor.setEme_Mnor_Id(educacion_MenoresBox.getValue().getEme_Id());
		menor.setUsu_Mnor_Id(mainApp.getUsuarioApp().getUsu_Id());
		menor.setMnor_Fecha(new Date());
		
		if(!controlBox.isDisable()){
			menor.setMnor_Control(controlBox.getValue().getId());
		}
		
		menor.setMnor_Peso(formatVal(menorPesoField.getText()));
		menor.setMnor_Talla(formatVal(menorTallaField.getText()));
		menor.setMnor_Perimetro_Cefalico(formatVal(menorPerimetroCefalicoField.getText()));
		menor.setMnor_Brazo(formatVal(menorCircunferenciaBrazoField.getText()));
		menor.setMnor_Tiempo_Lactancia(formatVal(menorTiempoLactanciaField.getText()).longValue());
		menor.setMnor_Paquete_Nutricional(paqueteNutricionalBox.getValue().getId());
		
		if(!eraBox.isDisable()){
			menor.setMnor_Era(eraBox.getValue().getId());
		}
		
		if(!edaBox.isDisable()){
			menor.setMnor_Eda(edaBox.getValue().getId());
		}
		
		if(!desparacitacionBox.isDisable()){
			menor.setMnor_Desparasitacion(desparacitacionBox.getValue().getId());
		}
		
		menor.setMnor_Estado("R");
		menor.setMnor_Fecha_Reporte(fechaConsulta);
		if((zScoreAgeSizeField.getText()!=null)&&(zScoreAgeSizeField.getText()!="")&&(!zScoreAgeSizeField.getText().equals("No calculada"))){
			menor.setMnor_Zstalla_Edad(getDouble(zScoreAgeSizeField.getText()));
		}else{
			menor.setMnor_Zstalla_Edad((double) 0);
		}
		
		if((zScoreWeightAgeField.getText()!=null)&&(zScoreWeightAgeField.getText()!="")&&(!zScoreWeightAgeField.getText().equals("No calculada"))){
			menor.setMnor_Zspeso_Edad(getDouble(zScoreWeightAgeField.getText()));
		}else{
			menor.setMnor_Zspeso_Edad((double) 0);
		}
		
		if((zScoreAgeIMCField.getText()!=null)&&(zScoreAgeIMCField.getText()!="")&&(!zScoreAgeIMCField.getText().equals("No calculada"))){
			menor.setMnor_Zsimc_Edad(getDouble(zScoreAgeIMCField.getText()));
		}else{
			menor.setMnor_Zsimc_Edad((double) 0);
		}
		
		if((zScoreWeightSizeField.getText()!=null)&&(zScoreWeightSizeField.getText()!="")&&(!zScoreWeightSizeField.getText().equals("No calculada"))){
			menor.setMnor_Zspeso_Talla(getDouble(zScoreWeightSizeField.getText()));
		}else{
			menor.setMnor_Zspeso_Talla((double) 0);
		}
		
		if((headCircunferenceAgeField.getText()!=null)&&(headCircunferenceAgeField.getText()!="")&&(!headCircunferenceAgeField.getText().equals("No calculada"))){
			menor.setMnor_Zspcefalico_Edad(getDouble(headCircunferenceAgeField.getText()));
		}else{
			menor.setMnor_Zspcefalico_Edad((double) 0);
		}
		
		if(disponibleUnoField.isVisible()){
			menor.setMnor_Campo1(disponibleUnoField.getText());
		}
		
		if(disponibleDosField.isVisible()){
			menor.setMnor_Campo2(disponibleDosField.getText());
		}
		
		if(disponibleTresField.isVisible()){
			menor.setMnor_Campo3(disponibleTresField.getText());
		}
		
		if(disponibleCuatroField.isVisible()){
			menor.setMnor_Campo4(disponibleCuatroField.getText());
		}
		
		menor.setMnor_sync("NS");
	}
	
	private Double getDouble(String Text){
		double valReturn=0.0;
		try{
			if(!Text.isEmpty()){
				String[] valText=Text.split("-");
				String numberT=valText[0].trim();
				if(!numberT.equals("NaN")){
					valReturn=Double.parseDouble(numberT);
					if((valReturn<-1000.00)||(valReturn>1000.00)){
						valReturn=0.0;
					}
				}
			}
		}
		catch (NumberFormatException e) {
			valReturn=0.0;
        }
		catch(Exception e){
			valReturn=0.0;
		}
		return valReturn;
	}
	
	private Double formatVal(String iniVal){
		try{
			return Double.valueOf(iniVal.replace(",", "."));
		}catch(Exception t){
			t.printStackTrace();
			return (double) 0;
		}
	}
	
	private void calculaDatos(){
		try{
			String limiteVal=limitesMenores();
			if(!limiteVal.equals("")){
				Alert alert = new Alert(AlertType.ERROR);
		        alert.setTitle("Menores");
		        alert.setHeaderText("Limites de datos");
		        alert.setContentText(limiteVal);
		        alert.showAndWait();
			}
			
			String tallaMenor=menorTallaField.getText();
			tallaMenor=tallaMenor.replace(",", ".");
			String pesoMenor=menorPesoField.getText();
			pesoMenor=pesoMenor.replace(",", ".");
			String perimetroCefalico=menorPerimetroCefalicoField.getText();
			perimetroCefalico=perimetroCefalico.replace(",", ".");
			String circunferenciaBrazo=menorCircunferenciaBrazoField.getText();
			circunferenciaBrazo=circunferenciaBrazo.replace(",", ".");
			
			String edadExactaCalculada=datosCaculados.calculaEdadExacta(paciente.getPac_Fechanacimiento());
			String edadAproximadaCalculada=datosCaculados.calulaEdadAproximada(paciente.getPac_Fechanacimiento());
			String edadAproximadaCalculadaOnly=datosCaculados.calulaEdadAproximadaOnlyNumber(paciente.getPac_Fechanacimiento());
			String diasDiferencia=String.valueOf(datosCaculados.calculaDias(paciente.getPac_Fechanacimiento(), new Date()));
			
			String scriptZscoreAgeSize="calculateSizeAge('"+tallaMenor+"','"+diasDiferencia+"','"+paciente.getPac_Sexo()+"')";
			Object zScoreSizeAgeCalculate=engine.executeScript(scriptZscoreAgeSize);
			
			String scriptIMC="calculateAgeImc('"+pesoMenor+"','"+tallaMenor+"')";
			Object iMCCalculate=engine.executeScript(scriptIMC);
			
			String scriptZscoreAgeIMC="calculateZScoreAgeImc('"+pesoMenor+"','"+tallaMenor+"','"+diasDiferencia+"','"+paciente.getPac_Sexo()+"','"+edadAproximadaCalculadaOnly+"')";
			Object zScoreAgeIMCCalculate=engine.executeScript(scriptZscoreAgeIMC);
			
			String scriptMomentoVitalKids="kidVitalMoment('"+edadAproximadaCalculadaOnly+"')";
			Object momentoVital=engine.executeScript(scriptMomentoVitalKids);
			
			String scriptZScoreWeightAge="calculateWeightAge('"+pesoMenor+"','"+diasDiferencia+"','"+paciente.getPac_Sexo()+"')";
			Object zScoreWeightAge=engine.executeScript(scriptZScoreWeightAge);
			
			String scriptZScoreHeadAge="calculateHeadAge('"+perimetroCefalico+"','"+diasDiferencia+"','"+paciente.getPac_Sexo()+"')";
			Object zScoreHeadAge=engine.executeScript(scriptZScoreHeadAge);

			//String scriptHeadCircunferenceAge="headCircunferenceAge('"+zScoreHeadAge.toString()+"','"+edadAproximadaCalculadaOnly+"')";
			//Object headCircunferenceAge=engine.executeScript(scriptHeadCircunferenceAge);
			
			String scriptZScoreWeightSize="calculateWeightSize('"+pesoMenor+"','"+tallaMenor+"','"+edadAproximadaCalculadaOnly+"','"+paciente.getPac_Sexo()+"')";
			Object zScoreWeightSize=engine.executeScript(scriptZScoreWeightSize);
			
			String scriptCircunferenciaBrazo="armCircunferenceAge('"+circunferenciaBrazo+"','"+edadAproximadaCalculadaOnly+"')";
			Object armCircunferenceAge=engine.executeScript(scriptCircunferenciaBrazo);
			
			scriptGraphicAgeSize="draw(dataGraphicSizeAge(['"+tallaMenor+"'],['"+edadAproximadaCalculadaOnly+"'],'"+paciente.getPac_Sexo()+"'))";
			scriptGraphicWeightAge="draw(dataGraphicWeightAge(['"+pesoMenor+"'],['"+edadAproximadaCalculadaOnly+"'],'"+paciente.getPac_Sexo()+"'))";
			scriptGraphicHeadAge="draw(dataGraphicHeadAge(['"+perimetroCefalico+"'],['"+edadAproximadaCalculadaOnly+"'],'"+paciente.getPac_Sexo()+"'))";
			scriptGraphicImcAge="draw(dataGraphicImcAge(['"+iMCCalculate.toString()+"'],['"+edadAproximadaCalculadaOnly+"'],'"+paciente.getPac_Sexo()+"'))";
			scriptGraphicWeightSize="draw(dataGraphicWeightSize(['"+pesoMenor+"'],['"+tallaMenor+"'],['"+edadAproximadaCalculadaOnly+"'],'"+paciente.getPac_Sexo()+"'))";
			
			String[] provinciasCalculadas = new String[2];
			provinciasCalculadas=getProvincias();
							
			calculaEdadField.setText(edadExactaCalculada);
			calculaEdadAproximadaField.setText(edadAproximadaCalculada);
			zScoreAgeSizeField.setText(zScoreSizeAgeCalculate.toString());
			iMCField.setText(iMCCalculate.toString());
			zScoreAgeIMCField.setText(zScoreAgeIMCCalculate.toString());
			momentoVitalField.setText(momentoVital.toString());
			zScoreWeightAgeField.setText(zScoreWeightAge.toString());
			headCircunferenceAgeField.setText(zScoreHeadAge.toString());
			zScoreWeightSizeField.setText(zScoreWeightSize.toString());
			armCircunferenceAgeField.setText(armCircunferenceAge.toString());
			proviciaNotificacionField.setText(provinciasCalculadas[0]);
			proviciaResidenciaField.setText(provinciasCalculadas[1]);
			
		}catch(Exception er){
			er.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Menores");
        	alert.setHeaderText("Error Generar Calculados");
        	alert.setContentText("No se pueden calcular datos del Menor");
        	alert.showAndWait();
		}
		
	}
	
	@SuppressWarnings("finally")
	private String limitesMenores(){
		String mensajeError="";
		try{
			String tallaMenor=menorTallaField.getText();
			tallaMenor=tallaMenor.replace(",", ".");
			String pesoMenor=menorPesoField.getText();
			pesoMenor=pesoMenor.replace(",", ".");
			String perimetroCefalico=menorPerimetroCefalicoField.getText();
			perimetroCefalico=perimetroCefalico.replace(",", ".");
			String circunferenciaBrazo=menorCircunferenciaBrazoField.getText();
			circunferenciaBrazo=circunferenciaBrazo.replace(",", ".");
			
			if(tallaMenor.equals(""))
				tallaMenor="0";
			if(pesoMenor.equals(""))
				pesoMenor="0";
			if(perimetroCefalico.equals(""))
				perimetroCefalico="0";
			if(circunferenciaBrazo.equals(""))
				circunferenciaBrazo="0";
			
			if((Double.parseDouble(pesoMenor)<0.9)||(Double.parseDouble(pesoMenor)>120)){
				mensajeError="El Peso del Menor esta fuera de los limites establecidos";
			}
			
			if((Double.parseDouble(tallaMenor)<38)||(Double.parseDouble(tallaMenor)>195)){
				mensajeError="La Talla del Menor esta fuera de los limites establecidos";
			}
			
			if(!menorCircunferenciaBrazoField.isDisable()){
				if((Double.parseDouble(circunferenciaBrazo)<6)||(Double.parseDouble(circunferenciaBrazo)>35)){
					mensajeError="La Circunferencia de brazo del Menor esta fuera de los limites establecidos";
				}
			}
			
			if(!menorPerimetroCefalicoField.isDisable()){
				if((Double.parseDouble(perimetroCefalico)<25)||(Double.parseDouble(perimetroCefalico)>64)){
					mensajeError="El Perimetro Cefalico del Menor esta fuera de los limites establecidos";
				}
			}
			
		}catch(Exception e){
			mensajeError=e.toString();
		}finally{
			return mensajeError;
		}
	}
	
	private String[] getProvincias(){
		String[] provincias=new String[2];
		String provinciaNotifica="Provicia No encontrada";
		String provinciaReside="Provicia No encontrada";
		
		Municipios municipioNotifica=new Municipios();
		Municipios municipioReside=new Municipios();
		Provincias provinciaNotificacion=new Provincias();
		Provincias provinciaResidencia=new Provincias();
		
		try{
			municipioNotifica=repoMunicipios.findById(mainApp.getInstitucionApp().getMun_Ips_Id(),mainApp.getInstitucionApp().getDep_Ips_Id());
			provinciaNotificacion=repoProvincias.findById(municipioNotifica.getPro_Mun_Id());
			provinciaNotifica=provinciaNotificacion.getPro_Nombre();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			provincias[0]=provinciaNotifica;
		}
		
		try{
			municipioReside=repoMunicipios.findById(pacientes_detalle.getMun_Pacd_Id(),pacientes_detalle.getDep_Pacd_Id());
			provinciaResidencia=repoProvincias.findById(municipioReside.getPro_Mun_Id());
			provinciaReside=provinciaResidencia.getPro_Nombre();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			provincias[1]=provinciaReside;
		}
		
		return provincias;
	}
	
	
	private boolean validarForm() {
		boolean okValidate=true;
		String messange="";
		try{
						
			if(menorPesoField.getText().equals("")){ okValidate=false; messange=messange+"Ingrese el Peso\n";}
			
			if(menorTallaField.getText().equals("")){okValidate=false; messange=messange+"Ingrese la Talla\n";}
			
			if(!menorPerimetroCefalicoField.isDisable()){
				if(menorPerimetroCefalicoField.getText().equals("")){ okValidate=false; messange=messange+"Ingrese el Perimetro Cefalico\n"; }
			}
			
			if(!menorCircunferenciaBrazoField.isDisable()){
				if(menorCircunferenciaBrazoField.getText().equals("")){	okValidate=false; messange=messange+"Ingrese el Circunferencia del Brazo\n"; }
			}
			
			if(!menorTiempoLactanciaField.isDisable()){
				if(menorTiempoLactanciaField.getText().equals("")){	okValidate=false; messange=messange+"Ingrese el Tiempo de Lactancia\n"; }
			}
			
			if(!controlBox.isDisable()){
				if(controlBox.getValue()==null){ okValidate=false; messange=messange+"Seleccione Control\n"; }
			}
			
			if(tipo_ConsultasBox.getValue()==null){ okValidate=false; messange=messange+"Seleccione Tipo Consulta\n"; }
			
			if(!lactanciasBox.isDisable()){
				if(lactanciasBox.getValue()==null){ okValidate=false; messange=messange+"Seleccione Lactancia\n"; }
			}
			
			if(tipo_PoblacionesBox.getValue()==null){ okValidate=false; messange=messange+"Seleccione Tipo Poblacion\n"; }
			
			if(paqueteNutricionalBox.getValue()==null){ okValidate=false; messange=messange+"Seleccione Programa Nutricional\n"; }
			
			if(programasBox.getValue()==null){ okValidate=false; messange=messange+"Seleccione Programa\n"; }
			
			if(micronutrientesBox.getValue()==null){ okValidate=false; messange=messange+"Seleccione Micronutrientes\n"; }
			
			if(regimenesBox.getValue()==null){ okValidate=false; messange=messange+"Seleccione Seleccione Regimen\n"; }
			
			if(entidadesBox.getValue()==null){ okValidate=false; messange=messange+"Seleccione Entidad EPS\n"; }
			
			if(!desarrollosBox.isDisable()){
				if(desarrollosBox.getValue()==null){ okValidate=false; messange=messange+"Seleccione Desarrollo\n"; }
			}
			
			if(!eraBox.isDisable()){
				if(eraBox.getValue()==null){ okValidate=false; messange=messange+"Seleccione E.R.A\n"; }
			}
			
			if(!edaBox.isDisable()){
				if(edaBox.getValue()==null){ okValidate=false; messange=messange+"Seleccione E.D.A\n"; }
			}
			
			if(!desparacitacionBox.isDisable()){
				if(desparacitacionBox.getValue()==null){ okValidate=false; messange=messange+"Seleccione Desparacitacion\n"; }
			}
			
			if(actividadesBox.getValue()==null){ okValidate=false; messange=messange+"Seleccione Actividad Fisica\n"; }
			
			if(especialidadesBox.getValue()==null){ okValidate=false; messange=messange+"Seleccione Especialidad\n"; }
			
			if(educacion_MenoresBox.getValue()==null){ okValidate=false; messange=messange+"Seleccione Educacion\n"; }
			
			String limiteVal=limitesMenores();
			if(!limiteVal.equals("")){
				okValidate=false;
				messange=messange+limiteVal+"\n";
			}
			
			if(!okValidate){
				Alert alert = new Alert(AlertType.ERROR);
	        	alert.setTitle("Menores");
	        	alert.setHeaderText("Validacion de formulario");
	        	alert.setContentText(messange);
	        	alert.showAndWait();
			}
		}catch(Exception e){
			Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Menores");
        	alert.setHeaderText("Validacion de formulario");
        	alert.setContentText("Error de Validacion");
        	alert.showAndWait();
		}
		
		return okValidate;
	}
	
	private void iniciaCamposDinamicos() {
		try{
			dinamicos=repoDinamicos.findDinamicoById("MNORCAMPO1");
			if(dinamicos.getDad_Estado().equals("A")){
				disponibleUnoField.setVisible(true);
				disponibleUnoLabel.setVisible(true);
				disponibleUnoLabel.setText(dinamicos.getDad_Nombre());
			}
			
			dinamicos=repoDinamicos.findDinamicoById("MNORCAMPO2");
			if(dinamicos.getDad_Estado().equals("A")){
				disponibleDosField.setVisible(true);
				disponibleDosLabel.setVisible(true);
				disponibleDosLabel.setText(dinamicos.getDad_Nombre());
			}
			
			dinamicos=repoDinamicos.findDinamicoById("MNORCAMPO3");
			if(dinamicos.getDad_Estado().equals("A")){
				disponibleTresField.setVisible(true);
				disponibleTresLabel.setVisible(true);
				disponibleTresLabel.setText(dinamicos.getDad_Nombre());
			}
			
			dinamicos=repoDinamicos.findDinamicoById("MNORCAMPO4");
			if(dinamicos.getDad_Estado().equals("A")){
				disponibleCuatroField.setVisible(true);
				disponibleCuatroLabel.setVisible(true);
				disponibleCuatroLabel.setText(dinamicos.getDad_Nombre());
			}
		}
		catch(Exception e){
			
		}	
	}
		
	public Menores getMenor() {
		return menor;
	}

	public void setMenor(Menores menor) {
		this.menor = menor;
	}

	public Pacientes getPaciente() {
		return paciente;
	}

	public void setPaciente(Pacientes paciente) {
		this.paciente = paciente;
		loadFormMenores();
	}

	public Paciente_Detalles getPacientes_detalle() {
		return pacientes_detalle;
	}

	public void setPacientes_detalle(Paciente_Detalles pacientes_detalle) {
		this.pacientes_detalle = pacientes_detalle;
	}
	
	public void setMainApp(Main mainApp, Session session, Pacientes paciente, Paciente_Detalles paciente_detalle, Menores menor2, Date fechaConsulta) {
		this.mainApp = mainApp;
		this.session=session;
		this.paciente=paciente;
		this.pacientes_detalle=paciente_detalle;
		this.fechaConsulta=fechaConsulta;
		cargaComponentes();
		iniciaCamposDinamicos();
		loadFormMenores();
		if(menor2!=null){
			this.menor=menor2;
			cargaMenorForm();
		}
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
