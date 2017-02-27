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
import co.gov.cundinamarca.sisvan.model.parameters.Entidades;
import co.gov.cundinamarca.sisvan.model.parameters.Especialidades;
import co.gov.cundinamarca.sisvan.model.parameters.Micronutrientes;
import co.gov.cundinamarca.sisvan.model.parameters.Municipios;
import co.gov.cundinamarca.sisvan.model.parameters.Paciente_Detalles;
import co.gov.cundinamarca.sisvan.model.parameters.Pacientes;
import co.gov.cundinamarca.sisvan.model.parameters.Programas;
import co.gov.cundinamarca.sisvan.model.parameters.Provincias;
import co.gov.cundinamarca.sisvan.model.parameters.Regimenes;
import co.gov.cundinamarca.sisvan.model.parameters.SelectSiNo;
import co.gov.cundinamarca.sisvan.model.parameters.Tipo_Poblaciones;
import co.gov.cundinamarca.sisvan.model.transactions.Adultos;
import co.gov.cundinamarca.sisvan.repository.ActividadesRepository;
import co.gov.cundinamarca.sisvan.repository.AdultosRepository;
import co.gov.cundinamarca.sisvan.repository.Dato_DinamicosRepository;
import co.gov.cundinamarca.sisvan.repository.EntidadesRepository;
import co.gov.cundinamarca.sisvan.repository.EspecialidadesRepository;
import co.gov.cundinamarca.sisvan.repository.MicronutrientesRepository;
import co.gov.cundinamarca.sisvan.repository.MunicipiosRepository;
import co.gov.cundinamarca.sisvan.repository.Paciente_DetallesRepository;
import co.gov.cundinamarca.sisvan.repository.PacientesRepository;
import co.gov.cundinamarca.sisvan.repository.ProgramasRepository;
import co.gov.cundinamarca.sisvan.repository.ProvinciasRepository;
import co.gov.cundinamarca.sisvan.repository.RegimenesRepository;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.StringConverter;

public class AdultosOverviewController {
	private Main mainApp;
	private Adultos adulto;
	private Pacientes paciente;
	private Paciente_Detalles pacientes_detalle;
	private Dato_Dinamicos dinamicos;
	private Date fechaConsulta;
	private Session session;
	
	DecimalFormat format = new DecimalFormat( "#.0" );
	String pattern = "dd/MM/yyyy";
	
	AdultosRepository repoAdultos;
	PacientesRepository repoPacientes;
	Paciente_DetallesRepository repoPaciente_Detalles;
	Tipo_PoblacionesRepository repoTipo_Poblaciones;
	RegimenesRepository repoRegimenes;
	EntidadesRepository repoEntidades;
	ProgramasRepository repoProgramas;
	EspecialidadesRepository repoEspecialidades;
	MicronutrientesRepository repoMicronutrientes;
	ActividadesRepository repoActividades;
	MunicipiosRepository repoMunicipios;
	ProvinciasRepository repoProvincias;
	Dato_DinamicosRepository repoDinamicos;
	DatosCalculados datosCaculados;
	
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
	private TextField adultoPesoField;
	@FXML
	private TextField adultoTallaField;
	@FXML
	private TextField adultoCircunferenicaCinturaField;
	@FXML
	private TextField adultoCircunferenicaMunecaField;
	@FXML
	private TextField calculaEdadField;
	@FXML
	private TextField iMCField;
	@FXML
	private TextField obesidadAbdominalField;
	@FXML
	private TextField estadoNutricionalField;
	@FXML
	private TextField clasificacionObesidadField;
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
	private ComboBox<SelectSiNo> lactanteBox;
	@FXML
	private ComboBox<Tipo_Poblaciones> tipo_PoblacionesBox;
	@FXML
	private ComboBox<Regimenes> regimenesBox;
	@FXML
	private ComboBox<Entidades> entidadesBox;
	@FXML
	private ComboBox<SelectSiNo> paqueteNutricionalBox;
	@FXML
	private ComboBox<Programas> programasBox;
	@FXML
	private ComboBox<Especialidades> especialidadesBox;
	@FXML
	private ComboBox<Micronutrientes> micronutrientesBox;
	@FXML
	private ComboBox<SelectSiNo> educacionNutricionalBox;
	@FXML
	private ComboBox<Actividades> actividadesBox;
	
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
				if(adulto==null){
					adulto=new Adultos();
				}
				loadModel();
				if((repoAdultos.saveAdulto(adulto))&&(repoPaciente_Detalles.saveDetallePaciente(pacientes_detalle))){
					Alert alert = new Alert(AlertType.CONFIRMATION);
		        	alert.setTitle("Adultos");
		        	alert.setHeaderText("Guardar Adulto");
		        	alert.setContentText("¡Se guardo el adulto correctamente!");
		        	alert.showAndWait();
				}else{
					Alert alert = new Alert(AlertType.ERROR);
		        	alert.setTitle("Adultos");
		        	alert.setHeaderText("Error al guardar el adulto");
		        	alert.setContentText("No se puede guardar adulto");
		        	alert.showAndWait();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Adultos");
        	alert.setHeaderText("Error al guardar el adulto");
        	alert.setContentText("No se puede guardar adulto "+e.toString());
        	alert.showAndWait();
		}
	}

	@FXML
	private void handleCancelar() {
		mainApp.dashBoardOverview();
	}
	private void cargaComponentes(){
		adulto=new Adultos();
		repoAdultos=new AdultosRepository(session);
		repoPaciente_Detalles=new Paciente_DetallesRepository(session);
		repoTipo_Poblaciones=new Tipo_PoblacionesRepository(session);
		repoRegimenes=new RegimenesRepository(session);
		repoEntidades=new EntidadesRepository(session);
		repoProgramas=new ProgramasRepository(session);
		repoEspecialidades=new EspecialidadesRepository(session);
		repoMicronutrientes=new MicronutrientesRepository(session);
		repoActividades=new ActividadesRepository(session);
		repoMunicipios=new MunicipiosRepository(session);
		repoProvincias=new ProvinciasRepository(session);
		repoDinamicos=new Dato_DinamicosRepository(session);
		datosCaculados=new DatosCalculados();
		
		addTextLimiter(adultoPesoField,5);
		addTextLimiter(adultoTallaField,5);
		addTextLimiter(adultoCircunferenicaCinturaField,5);
		addTextLimiter(adultoCircunferenicaMunecaField,5);
		
		//Campos Disponibles
		disponibleUnoField.setVisible(false);
		disponibleDosField.setVisible(false);
		disponibleTresField.setVisible(false);
		disponibleCuatroField.setVisible(false);
		
		disponibleUnoLabel.setVisible(false);
		disponibleDosLabel.setVisible(false);
		disponibleTresLabel.setVisible(false);
		disponibleCuatroLabel.setVisible(false);
		
		lactanteBox.setItems(selectSiNoList);
		tipo_PoblacionesBox.setItems(repoTipo_Poblaciones.getListCombo());
		regimenesBox.setItems(repoRegimenes.getListCombo());
		entidadesBox.setItems(repoEntidades.getListCombo());
		programasBox.setItems(repoProgramas.getListCombo("Adultos"));
		especialidadesBox.setItems(repoEspecialidades.getListCombo("Adultos"));
		micronutrientesBox.setItems(repoMicronutrientes.getListCombo("Adultos"));
		actividadesBox.setItems(repoActividades.getListCombo());
		paqueteNutricionalBox.setItems(selectSiNoList);
		educacionNutricionalBox.setItems(selectSiNoList);
		
		adultoPesoField.setTextFormatter( new TextFormatter<>(c ->{
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
		
		adultoTallaField.setTextFormatter( new TextFormatter<>(c ->{
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
		
		adultoCircunferenicaCinturaField.setTextFormatter( new TextFormatter<>(c ->{
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
		
		adultoCircunferenicaMunecaField.setTextFormatter( new TextFormatter<>(c ->{
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
		
		new ComboBoxAutoComplete<SelectSiNo> (lactanteBox);
		new ComboBoxAutoComplete<Tipo_Poblaciones> (tipo_PoblacionesBox);
		new ComboBoxAutoComplete<Regimenes> (regimenesBox);
		new ComboBoxAutoComplete<Entidades> (entidadesBox);
		new ComboBoxAutoComplete<SelectSiNo> (paqueteNutricionalBox);
		new ComboBoxAutoComplete<Programas> (programasBox);
		new ComboBoxAutoComplete<Especialidades> (especialidadesBox);
		new ComboBoxAutoComplete<Micronutrientes> (micronutrientesBox);
		new ComboBoxAutoComplete<SelectSiNo> (educacionNutricionalBox);
		new ComboBoxAutoComplete<Actividades> (actividadesBox);
	}
	
	private void loadModel(){
		pacientes_detalle.setTpo_Pacd_Id(tipo_PoblacionesBox.getValue().getTpo_Id());
		pacientes_detalle.setReg_Pacd_Id(regimenesBox.getValue().getReg_Id());
		pacientes_detalle.setEps_Pacd_Id(entidadesBox.getValue().getEps_Id());
		
		adulto.setIps_Adu_Id(mainApp.getInstitucionApp().getIps_Id());
		adulto.setTid_Pac_Adu_Id(paciente.getTid_Pac_Id());
		adulto.setPac_Adu_Id(paciente.getPac_Id());
		adulto.setPacd_Adu_Id(pacientes_detalle.getPacd_Id());
		adulto.setPrg_Adu_Id(programasBox.getValue().getPrg_Id());
		adulto.setMnu_Adu_Id(micronutrientesBox.getValue().getMnu_Id());
		adulto.setAct_Adu_Id(actividadesBox.getValue().getAct_Id());
		adulto.setUsu_Adu_Id(mainApp.getUsuarioApp().getUsu_Id());
		adulto.setAdu_Mujer_Lactante(lactanteBox.getValue().getId());
		adulto.setAdu_Peso(formatVal(adultoPesoField.getText()));
		adulto.setAdu_Talla(formatVal(adultoTallaField.getText()));
		adulto.setAdu_Cintura(formatVal(adultoCircunferenicaCinturaField.getText()));
		adulto.setAdu_Muneca(formatVal(adultoCircunferenicaMunecaField.getText()));
		adulto.setAdu_Remision(especialidadesBox.getValue().getEsp_Id());
		adulto.setAdu_Paquete_Nutricional(paqueteNutricionalBox.getValue().getId());
		adulto.setAdu_Educacion_Nutricional(educacionNutricionalBox.getValue().getId());
		adulto.setAdu_Estado("R");
		adulto.setAdu_Fecha(new Date());
		adulto.setAdu_Fecha_Reporte(fechaConsulta);
		
		if(disponibleUnoField.isVisible()){
			adulto.setAdu_Campo1(disponibleUnoField.getText());
		}
		
		if(disponibleDosField.isVisible()){
			adulto.setAdu_Campo2(disponibleDosField.getText());
		}
		
		if(disponibleTresField.isVisible()){
			adulto.setAdu_Campo3(disponibleTresField.getText());
		}
		
		if(disponibleCuatroField.isVisible()){
			adulto.setAdu_Campo4(disponibleCuatroField.getText());
		}
		
		adulto.setAdu_sync("NS");
	}
	
	private Double formatVal(String iniVal){
		try{
			return Double.valueOf(iniVal.replace(",", "."));
		}catch(Exception t){
			t.printStackTrace();
			return (double) 0;
		}
	}
	
	private void loadFormAdultos() {
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
		
		habilitaCamposTipoAdulto();
	}
	
	private void habilitaCamposTipoAdulto() {
		if(paciente.getPac_Sexo().equals("0")){
			//"Masculino";
			lactanteBox.setValue(new SelectSiNo("2"));
			lactanteBox.setDisable(true);
		}
	}

	private void cargaAdultosForm() {
		lactanteBox.setValue(new SelectSiNo(adulto.getAdu_Mujer_Lactante()));
		programasBox.setValue(repoProgramas.findById(adulto.getPrg_Adu_Id()));
		especialidadesBox.setValue(repoEspecialidades.findById(adulto.getAdu_Remision()));
		micronutrientesBox.setValue(repoMicronutrientes.findById(adulto.getMnu_Adu_Id()));
		actividadesBox.setValue(repoActividades.findById(adulto.getAct_Adu_Id()));
		paqueteNutricionalBox.setValue(new SelectSiNo(adulto.getAdu_Paquete_Nutricional()));
		educacionNutricionalBox.setValue(new SelectSiNo(adulto.getAdu_Educacion_Nutricional()));
		
		String adultoPeso=InversaformatVal(adulto.getAdu_Peso());
		adultoPesoField.setText(adultoPeso);
		String adultoTalla=InversaformatVal(adulto.getAdu_Talla());
		adultoTallaField.setText(adultoTalla);
		String adultoCircunferenciaCintura=InversaformatVal(adulto.getAdu_Cintura());
		adultoCircunferenicaCinturaField.setText(adultoCircunferenciaCintura);
		String adultoCircunferenciaMuneca=InversaformatVal(adulto.getAdu_Muneca());
		adultoCircunferenicaMunecaField.setText(adultoCircunferenciaMuneca);
		
		if(disponibleUnoField.isVisible()){
			disponibleUnoField.setText(adulto.getAdu_Campo1());
		}
		
		if(disponibleDosField.isVisible()){
			disponibleDosField.setText(adulto.getAdu_Campo2());
		}
		
		if(disponibleTresField.isVisible()){
			disponibleTresField.setText(adulto.getAdu_Campo3());
		}
		
		if(disponibleCuatroField.isVisible()){
			disponibleCuatroField.setText(adulto.getAdu_Campo4());
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
	
	private void calculaDatos(){
		try{
			String limiteVal=limitesAdutos();
			if(!limiteVal.equals("")){
				Alert alert = new Alert(AlertType.ERROR);
		        alert.setTitle("Adultos");
		        alert.setHeaderText("Limites de datos");
		        alert.setContentText(limiteVal);
		        alert.showAndWait();
			}
			
			String tallaAdulto=adultoTallaField.getText();
			tallaAdulto=tallaAdulto.replace(",", ".");
			String pesoAdulto=adultoPesoField.getText();
			pesoAdulto=pesoAdulto.replace(",", ".");
			String circunferenciaCintura=adultoCircunferenicaCinturaField.getText();
			circunferenciaCintura=circunferenciaCintura.replace(",", ".");
			
			String edadExactaCalculada=datosCaculados.calculaEdadExacta(paciente.getPac_Fechanacimiento());
			String scriptIMC="calculateAgeImc('"+pesoAdulto+"','"+tallaAdulto+"')";
			Object iMCCalculate=engine.executeScript(scriptIMC);
			
			String scriptObesidadAbdominal="abdominalObsesity('"+circunferenciaCintura+"','"+paciente.getPac_Sexo()+"')";
			Object obesidadAbdominal=engine.executeScript(scriptObesidadAbdominal);
			
			String scriptEstadoNutricional="nutritionalState('"+iMCCalculate.toString()+"')";
			Object estadoNutrional=engine.executeScript(scriptEstadoNutricional);
			
			String scriptObesidadClasificacion="obesityClasification('"+iMCCalculate.toString()+"')";
			Object obesidadClasificacion=engine.executeScript(scriptObesidadClasificacion);
			
			String[] provinciasCalculadas = new String[2];
			provinciasCalculadas=getProvincias();
			
			calculaEdadField.setText(edadExactaCalculada);
			iMCField.setText(iMCCalculate.toString());
			obesidadAbdominalField.setText(obesidadAbdominal.toString());
			estadoNutricionalField.setText(estadoNutrional.toString());
			clasificacionObesidadField.setText(obesidadClasificacion.toString());
			proviciaNotificacionField.setText(provinciasCalculadas[0]);
			proviciaResidenciaField.setText(provinciasCalculadas[1]);
			
		}catch(Exception er){
			/*er.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Adultos");
        	alert.setHeaderText("Error Generar Calculados");
        	alert.setContentText("No se pueden calcular datos del Adulto");
        	alert.showAndWait();*/
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
						
			if(adultoPesoField.getText().equals("")){ okValidate=false; messange=messange+"Ingrese el peso\n";}
			
			if(adultoTallaField.getText().equals("")){okValidate=false; messange=messange+"Ingrese la talla\n";}
			
			if(adultoCircunferenicaCinturaField.getText().equals("")){ okValidate=false; messange=messange+"Ingrese la circunferencia de cintura\n"; }
			
			if(adultoCircunferenicaMunecaField.getText().equals("")){	okValidate=false; messange=messange+"Ingrese la circunferencia de la muñeca\n"; }
			
			if(!lactanteBox.isDisable()){
				if(lactanteBox.getValue()==null){ okValidate=false; messange=messange+"Seleccione lactancia\n"; }
			}
			
			if(tipo_PoblacionesBox.getValue()==null){ okValidate=false; messange=messange+"Seleccione tipo de población\n"; }
			
			if(regimenesBox.getValue()==null){ okValidate=false; messange=messange+"Seleccione régimen\n"; }
			
			if(entidadesBox.getValue()==null){ okValidate=false; messange=messange+"Seleccione entidad EPS\n"; }
			
			if(paqueteNutricionalBox.getValue()==null){ okValidate=false; messange=messange+"Seleccione programa nutricional\n"; }
			
			if(programasBox.getValue()==null){ okValidate=false; messange=messange+"Seleccione programa SAN\n"; }
			
			if(especialidadesBox.getValue()==null){ okValidate=false; messange=messange+"Seleccione especialidad\n"; }
			
			if(micronutrientesBox.getValue()==null){ okValidate=false; messange=messange+"Seleccione micronutrientes\n"; }
			
			if(educacionNutricionalBox.getValue()==null){ okValidate=false; messange=messange+"Seleccione educación nutricional\n"; }
			
			if(actividadesBox.getValue()==null){ okValidate=false; messange=messange+"Seleccione actividad física\n"; }
			
			String limiteVal=limitesAdutos();
			if(!limiteVal.equals("")){
				okValidate=false;
				messange=messange+limiteVal+"\n";
			}
			
			if(!okValidate){
				Alert alert = new Alert(AlertType.ERROR);
	        	alert.setTitle("Adultos");
	        	alert.setHeaderText("Validación de formulario");
	        	alert.setContentText(messange);
	        	alert.showAndWait();
			}
		}catch(Exception e){
			Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Adultos");
        	alert.setHeaderText("Validación de formulario");
        	alert.setContentText("Error de validación");
        	alert.showAndWait();
		}
		
		return okValidate;
	}
	
	@SuppressWarnings("finally")
	private String limitesAdutos(){
		String mensajeError="";
		try{
			String tallaAdulto=adultoTallaField.getText();
			tallaAdulto=tallaAdulto.replace(",", ".");
			String pesoAdulto=adultoPesoField.getText();
			pesoAdulto=pesoAdulto.replace(",", ".");
			String circunferenciaCintura=adultoCircunferenicaCinturaField.getText();
			circunferenciaCintura=circunferenciaCintura.replace(",", ".");
			String circunferenciaMuneca=this.adultoCircunferenicaMunecaField.getText();
			circunferenciaMuneca=circunferenciaMuneca.replace(",", ".");
			
			if(tallaAdulto.equals(""))
				tallaAdulto="0";
			if(pesoAdulto.equals(""))
				pesoAdulto="0";
			if(circunferenciaCintura.equals(""))
				circunferenciaCintura="0";
			if(circunferenciaMuneca.equals(""))
				circunferenciaMuneca="0";
			
			if((Double.parseDouble(pesoAdulto)<15)||(Double.parseDouble(pesoAdulto)>170)){
				mensajeError="El peso del adulto esta fuera de los límites establecidos";
			}
			
			if((Double.parseDouble(tallaAdulto)<100)||(Double.parseDouble(tallaAdulto)>195)){
				mensajeError="La talla del adulto esta fuera de los límites establecidos";
			}
			
			if((Double.parseDouble(circunferenciaCintura)<30)||(Double.parseDouble(circunferenciaCintura)>130)){
				mensajeError="La circunferencia de cintura del adulto esta fuera de los límites establecidos";
			}
			
			if((Double.parseDouble(circunferenciaMuneca)<10)||(Double.parseDouble(circunferenciaMuneca)>30)){
				mensajeError="La circunferencia de muñeca del adulto esta fuera de los límites establecidos";
			}
			
		}catch(Exception e){
			mensajeError=e.toString();
		}finally{
			return mensajeError;
		}
	}
	
	private void iniciaCamposDinamicos() {
		try{
			dinamicos=repoDinamicos.findDinamicoById("ADUCAMPO1");
			if(dinamicos.getDad_Estado().equals("A")){
				disponibleUnoField.setVisible(true);
				disponibleUnoLabel.setVisible(true);
				disponibleUnoLabel.setText(dinamicos.getDad_Nombre());
			}
			
			dinamicos=repoDinamicos.findDinamicoById("ADUCAMPO2");
			if(dinamicos.getDad_Estado().equals("A")){
				disponibleDosField.setVisible(true);
				disponibleDosLabel.setVisible(true);
				disponibleDosLabel.setText(dinamicos.getDad_Nombre());
			}
			
			dinamicos=repoDinamicos.findDinamicoById("ADUCAMPO3");
			if(dinamicos.getDad_Estado().equals("A")){
				disponibleTresField.setVisible(true);
				disponibleTresLabel.setVisible(true);
				disponibleTresLabel.setText(dinamicos.getDad_Nombre());
			}
			
			dinamicos=repoDinamicos.findDinamicoById("ADUCAMPO4");
			if(dinamicos.getDad_Estado().equals("A")){
				disponibleCuatroField.setVisible(true);
				disponibleCuatroLabel.setVisible(true);
				disponibleCuatroLabel.setText(dinamicos.getDad_Nombre());
			}
		}
		catch(Exception e){
			
		}	
	}
	
	public void setMainApp(Main mainApp, Session session, Pacientes paciente, Paciente_Detalles paciente_detalle, Adultos adulto2, Date fechaConsulta) {
		this.mainApp = mainApp;
		this.session=session;
		this.paciente=paciente;
		this.pacientes_detalle=paciente_detalle;
		this.fechaConsulta=fechaConsulta;
		cargaComponentes();
		iniciaCamposDinamicos();
		loadFormAdultos();
		if(adulto2!=null){
			this.adulto=adulto2;
			cargaAdultosForm();
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
