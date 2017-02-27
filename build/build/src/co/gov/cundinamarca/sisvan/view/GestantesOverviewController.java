package co.gov.cundinamarca.sisvan.view;

import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
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
import co.gov.cundinamarca.sisvan.model.parameters.Riesgos;
import co.gov.cundinamarca.sisvan.model.parameters.SelectSiNo;
import co.gov.cundinamarca.sisvan.model.parameters.Tipo_Consultas;
import co.gov.cundinamarca.sisvan.model.parameters.Tipo_Poblaciones;
import co.gov.cundinamarca.sisvan.model.transactions.Gestantes;
import co.gov.cundinamarca.sisvan.repository.ActividadesRepository;
import co.gov.cundinamarca.sisvan.repository.Dato_DinamicosRepository;
import co.gov.cundinamarca.sisvan.repository.EntidadesRepository;
import co.gov.cundinamarca.sisvan.repository.EspecialidadesRepository;
import co.gov.cundinamarca.sisvan.repository.GestantesRepository;
import co.gov.cundinamarca.sisvan.repository.MicronutrientesRepository;
import co.gov.cundinamarca.sisvan.repository.MunicipiosRepository;
import co.gov.cundinamarca.sisvan.repository.Paciente_DetallesRepository;
import co.gov.cundinamarca.sisvan.repository.PacientesRepository;
import co.gov.cundinamarca.sisvan.repository.ProgramasRepository;
import co.gov.cundinamarca.sisvan.repository.ProvinciasRepository;
import co.gov.cundinamarca.sisvan.repository.RegimenesRepository;
import co.gov.cundinamarca.sisvan.repository.RiesgosRepository;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class GestantesOverviewController {
	private Main mainApp;
	private Gestantes gestante;
	private Pacientes paciente;
	private Paciente_Detalles pacientes_detalle;
	private Dato_Dinamicos dinamicos;
	private Date fechaConsulta;
	private Session session;
	
	String scriptGraphicImcPregnant;
	
	DecimalFormat format = new DecimalFormat( "#.0" );
	
	GestantesRepository repoGestantes;
	PacientesRepository repoPacientes;
	Paciente_DetallesRepository repoPaciente_Detalles;
	Tipo_ConsultasRepository repoTipo_Consultas;
	Tipo_PoblacionesRepository repoTipo_Poblaciones;
	RegimenesRepository repoRegimenes;
	EntidadesRepository repoEntidades;
	ProgramasRepository repoProgramas;
	EspecialidadesRepository repoEspecialidades;
	MicronutrientesRepository repoMicronutrientes;
	ActividadesRepository repoActividades;
	RiesgosRepository repoRiesgos;
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
	private TextField gestantePesoActualField;
	@FXML
	private TextField gestantePesoPreGestacionalField;
	@FXML
	private TextField gestanteTallaField;
	@FXML
	private TextField gestanteSemanasGestacionField;
	@FXML
	private TextField calculaEdadField;
	@FXML
	private TextField iMCField;
	@FXML
	private TextField estadoNutricionalField;
	@FXML
	private TextField proviciaNotificacionField;
	@FXML
	private TextField proviciaResidenciaField;
	@FXML
	private Label mensajeAlertSemOneField;
	@FXML
	private Label mensajeAlertSemTwoField;
	
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
	private ComboBox<Tipo_Consultas> tipo_ConsultasBox;
	@FXML
	private ComboBox<Tipo_Poblaciones> tipo_PoblacionesBox;
	@FXML
	private ComboBox<SelectSiNo> paqueteNutricionalBox;
	@FXML
	private ComboBox<Programas> programasBox;
	@FXML
	private ComboBox<Regimenes> regimenesBox;
	@FXML
	private ComboBox<Entidades> entidadesBox;
	@FXML
	private ComboBox<Especialidades> remisionBox;
	@FXML
	private ComboBox<Micronutrientes> micronutrientesBox;
	@FXML
	private ComboBox<SelectSiNo> educacionNutricionalBox;
	@FXML
	private ComboBox<Actividades> actividadesBox;
	@FXML
	private ComboBox<Riesgos> riesgosBox;
		
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
				if(gestante==null){
					gestante=new Gestantes();
				}
				loadModel();
				if((repoGestantes.saveGestante(gestante))&&(repoPaciente_Detalles.saveDetallePaciente(pacientes_detalle))){
					Alert alert = new Alert(AlertType.CONFIRMATION);
		        	alert.setTitle("Gestantes");
		        	alert.setHeaderText("Guardar Gestante");
		        	alert.setContentText("Se Guardo Gestante Correctamente");
		        	alert.showAndWait();
				}else{
					Alert alert = new Alert(AlertType.ERROR);
		        	alert.setTitle("Gestantes");
		        	alert.setHeaderText("Error al Guardar Gestante");
		        	alert.setContentText("No se puede guardar Gestante");
		        	alert.showAndWait();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Gestantes");
        	alert.setHeaderText("Error al Guardar Gestante");
        	alert.setContentText("No se puede guardar Gestante "+e.toString());
        	alert.showAndWait();
		}
	}

	@FXML
	private void handleCancelar() {
		mainApp.dashBoardOverview();
	}
	
	@FXML
	private void handleIrGraphicsImcPregnant() {
		mainApp.showGraphicsOverview(scriptGraphicImcPregnant);
	}
	
	private void cargaComponentes(){
		gestante=new Gestantes();
		repoGestantes=new GestantesRepository(session);
		repoPaciente_Detalles=new Paciente_DetallesRepository(session);
		repoTipo_Consultas=new Tipo_ConsultasRepository(session);
		repoTipo_Poblaciones=new Tipo_PoblacionesRepository(session);
		repoRegimenes=new RegimenesRepository(session);
		repoEntidades=new EntidadesRepository(session);
		repoProgramas=new ProgramasRepository(session);
		repoMicronutrientes=new MicronutrientesRepository(session);
		repoActividades=new ActividadesRepository(session);
		repoRiesgos=new RiesgosRepository(session);
		repoMunicipios=new MunicipiosRepository(session);
		repoProvincias=new ProvinciasRepository(session);
		repoEspecialidades=new EspecialidadesRepository(session);
		repoDinamicos=new Dato_DinamicosRepository(session);
		datosCaculados=new DatosCalculados();
	
		addTextLimiter(gestantePesoActualField,5);
		addTextLimiter(gestantePesoPreGestacionalField,5);
		addTextLimiter(gestanteTallaField,5);
		addTextLimiter(gestanteSemanasGestacionField,3);
		
		//Campos Disponibles
		disponibleUnoField.setVisible(false);
		disponibleDosField.setVisible(false);
		disponibleTresField.setVisible(false);
		disponibleCuatroField.setVisible(false);
		
		disponibleUnoLabel.setVisible(false);
		disponibleDosLabel.setVisible(false);
		disponibleTresLabel.setVisible(false);
		disponibleCuatroLabel.setVisible(false);
		
		mensajeAlertSemOneField.setVisible(false);
		mensajeAlertSemTwoField.setVisible(false);
		
		tipo_ConsultasBox.setItems(repoTipo_Consultas.getListCombo("Gestantes"));
		tipo_PoblacionesBox.setItems(repoTipo_Poblaciones.getListCombo());
		paqueteNutricionalBox.setItems(selectSiNoList);
		regimenesBox.setItems(repoRegimenes.getListCombo());
		entidadesBox.setItems(repoEntidades.getListCombo());
		programasBox.setItems(repoProgramas.getListCombo("Gestantes"));
		remisionBox.setItems(repoEspecialidades.getListCombo("Gestantes"));
		micronutrientesBox.setItems(repoMicronutrientes.getListCombo("Gestantes"));
		actividadesBox.setItems(repoActividades.getListCombo());
		educacionNutricionalBox.setItems(selectSiNoList);
		riesgosBox.setItems(repoRiesgos.getListCombo());
		
		gestantePesoActualField.setTextFormatter( new TextFormatter<>(c ->{
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
		
		gestantePesoPreGestacionalField.setTextFormatter( new TextFormatter<>(c ->{
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
		
		gestanteTallaField.setTextFormatter( new TextFormatter<>(c ->{
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
		
		gestanteSemanasGestacionField.textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
	            if (!newValue.matches("\\d*")) {
	            	gestanteSemanasGestacionField.setText(newValue.replaceAll("[^\\d]", ""));
	            }
	        }
	    });
		
		tabPane.getSelectionModel().selectedItemProperty().addListener(
			    new ChangeListener<Tab>() {
			        @Override
			        public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab t1) {
			        	calculaDatos();
			        }
			    }
			);
		
		new ComboBoxAutoComplete<Tipo_Consultas> (tipo_ConsultasBox);
		new ComboBoxAutoComplete<Tipo_Poblaciones> (tipo_PoblacionesBox);
		new ComboBoxAutoComplete<SelectSiNo> (paqueteNutricionalBox);
		new ComboBoxAutoComplete<Programas> (programasBox);
		new ComboBoxAutoComplete<Regimenes> (regimenesBox);
		new ComboBoxAutoComplete<Entidades> (entidadesBox);
		new ComboBoxAutoComplete<Especialidades>(remisionBox);
		new ComboBoxAutoComplete<Micronutrientes> (micronutrientesBox);
		new ComboBoxAutoComplete<SelectSiNo> (educacionNutricionalBox);
		new ComboBoxAutoComplete<Actividades> (actividadesBox);
		new ComboBoxAutoComplete<Riesgos> (riesgosBox);
	}
	
	
	private void loadModel(){
		pacientes_detalle.setTpo_Pacd_Id(tipo_PoblacionesBox.getValue().getTpo_Id());
		pacientes_detalle.setReg_Pacd_Id(regimenesBox.getValue().getReg_Id());
		pacientes_detalle.setEps_Pacd_Id(entidadesBox.getValue().getEps_Id());
		
		gestante.setIps_Ges_Id(mainApp.getInstitucionApp().getIps_Id());
		gestante.setTid_Pac_Ges_Id(paciente.getTid_Pac_Id());
		gestante.setPac_Ges_Id(paciente.getPac_Id());
		gestante.setPacd_Ges_Id(pacientes_detalle.getPacd_Id());
		gestante.setCtr_Ges_Id(tipo_ConsultasBox.getValue().getTco_Id());
		gestante.setPrg_Ges_Id(programasBox.getValue().getPrg_Id());
		gestante.setMnu_Ges_Id(micronutrientesBox.getValue().getMnu_Id());
		gestante.setAct_Ges_Id(actividadesBox.getValue().getAct_Id());
		gestante.setRie_Ges_Id(riesgosBox.getValue().getRie_Id());
		gestante.setUsu_Ges_Id(mainApp.getUsuarioApp().getUsu_Id());
		gestante.setGes_Peso_Pregestacion(formatVal(gestantePesoPreGestacionalField.getText()));
		gestante.setGes_Peso_Actual(formatVal(gestantePesoActualField.getText()));
		gestante.setGes_Talla(formatVal(gestanteTallaField.getText()));
		gestante.setGes_Paquete_Nutricional(paqueteNutricionalBox.getValue().getId());
		gestante.setGes_Remision(remisionBox.getValue().getEsp_Id());
		gestante.setGes_Edadgestacional(formatVall(gestanteSemanasGestacionField.getText()));
		gestante.setGes_Educacion_Nutricional(educacionNutricionalBox.getValue().getId());
		gestante.setGes_Estado("R");
		gestante.setGes_Fecha(new Date());
		gestante.setGes_Fecha_Reporte(fechaConsulta);
		gestante.setGes_Estado_Nutricional(estadoNutricionalField.getText());
		
		if(disponibleUnoField.isVisible()){
			gestante.setGes_Campo1(disponibleUnoField.getText());
		}
		
		if(disponibleDosField.isVisible()){
			gestante.setGes_Campo2(disponibleDosField.getText());
		}
		
		if(disponibleTresField.isVisible()){
			gestante.setGes_Campo3(disponibleTresField.getText());
		}
		
		if(disponibleCuatroField.isVisible()){
			gestante.setGes_Campo4(disponibleCuatroField.getText());
		}
		
		gestante.setGes_sync("NS");
	}
	
	
	private Double formatVal(String iniVal){
		try{
			return Double.valueOf(iniVal.replace(",", "."));
		}catch(Exception t){
			t.printStackTrace();
			return (double) 0;
		}
	}
	
	private Long formatVall(String iniVal){
		try{
			return Long.valueOf(iniVal.replace(",", "."));
		}catch(Exception t){
			t.printStackTrace();
			return (long) 0;
		}
	}
	
	private void loadFormGestantes() {
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
	}
	
	private void loadGestanteForm() {
		tipo_ConsultasBox.setValue(repoTipo_Consultas.findById(gestante.getCtr_Ges_Id()));
		paqueteNutricionalBox.setValue(new SelectSiNo(gestante.getGes_Paquete_Nutricional()));
		programasBox.setValue(repoProgramas.findById(gestante.getPrg_Ges_Id()));
		remisionBox.setValue(repoEspecialidades.findById(gestante.getGes_Remision()));
		micronutrientesBox.setValue(repoMicronutrientes.findById(gestante.getMnu_Ges_Id()));
		actividadesBox.setValue(repoActividades.findById(gestante.getAct_Ges_Id()));
		educacionNutricionalBox.setValue(new SelectSiNo(gestante.getGes_Educacion_Nutricional()));
		riesgosBox.setValue(repoRiesgos.findById(gestante.getRie_Ges_Id()));
		
		String gestantePesoActual=InversaformatVal(gestante.getGes_Peso_Actual());
		gestantePesoActualField.setText(gestantePesoActual);
		String gestantePesoPreGestacional=InversaformatVal(gestante.getGes_Peso_Pregestacion());
		gestantePesoPreGestacionalField.setText(gestantePesoPreGestacional);
		String gestanteTalla=InversaformatVal(gestante.getGes_Talla());
		gestanteTallaField.setText(gestanteTalla);
		String gestanteSemanasGestacion=gestante.getGes_Edadgestacional().toString();
		gestanteSemanasGestacionField.setText(gestanteSemanasGestacion);
		
		if(disponibleUnoField.isVisible()){
			disponibleUnoField.setText(gestante.getGes_Campo1());
		}
		
		if(disponibleDosField.isVisible()){
			disponibleDosField.setText(gestante.getGes_Campo2());
		}
		
		if(disponibleTresField.isVisible()){
			disponibleTresField.setText(gestante.getGes_Campo3());
		}
		
		if(disponibleCuatroField.isVisible()){
			disponibleCuatroField.setText(gestante.getGes_Campo4());
		}
		//LocalDate fechaConsulta = gestante.getGes_Fecha_Reporte().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		//gestanteFechaConsultafield.setValue(fechaConsulta);
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
			String limiteVal=limitesGestantes();
			if(!limiteVal.equals("")){
				Alert alert = new Alert(AlertType.ERROR);
		        alert.setTitle("Gestantes");
		        alert.setHeaderText("Limites de datos");
		        alert.setContentText(limiteVal);
		        alert.showAndWait();
			}
			
			String tallaGestante=gestanteTallaField.getText();
			tallaGestante=tallaGestante.replace(",", ".");
			String pesoGestante=gestantePesoActualField.getText();
			pesoGestante=pesoGestante.replace(",", ".");
			String semanasGestacion=gestanteSemanasGestacionField.getText();
			semanasGestacion=semanasGestacion.replace(",", ".");
			
			String edadExactaCalculada=datosCaculados.calculaEdadExacta(paciente.getPac_Fechanacimiento());
			String scriptIMC="calculateAgeImc('"+pesoGestante+"','"+tallaGestante+"')";
			Object iMCCalculate=engine.executeScript(scriptIMC);
			
			String scriptEstadoNutricional="pregnantNutritionalState('"+iMCCalculate.toString()+"','"+semanasGestacion+"')";
			Object estadoNutrional=engine.executeScript(scriptEstadoNutricional);
			
			scriptGraphicImcPregnant="draw(getGraphicImcPregnant(['"+iMCCalculate.toString()+"'],['"+semanasGestacion+"']))";
			
			String[] provinciasCalculadas = new String[2];
			provinciasCalculadas=getProvincias();
			
			calculaEdadField.setText(edadExactaCalculada);
			iMCField.setText(iMCCalculate.toString());
			estadoNutricionalField.setText(estadoNutrional.toString());
			proviciaNotificacionField.setText(provinciasCalculadas[0]);
			proviciaResidenciaField.setText(provinciasCalculadas[1]);
			
		}catch(Exception er){
			/*er.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Gestantes");
        	alert.setHeaderText("Error Generar Calculados");
        	alert.setContentText("No se pueden calcular datos del Gestante");
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
						
			if(gestantePesoActualField.getText().equals("")){ okValidate=false; messange=messange+"Ingrese el Peso Actual\n";}
			
			if(gestantePesoPreGestacionalField.getText().equals("")){ okValidate=false; messange=messange+"Ingrese el Peso Pre-Gestacional\n";}
			
			if(gestanteTallaField.getText().equals("")){okValidate=false; messange=messange+"Ingrese la Talla\n";}
			
			if(gestanteSemanasGestacionField.getText().equals("")){ okValidate=false; messange=messange+"Ingrese Semanas de Gestacion\n"; }
			
			if(tipo_ConsultasBox.getValue()==null){ okValidate=false; messange=messange+"Seleccione Tipo de Consulta\n"; }
			
			if(tipo_PoblacionesBox.getValue()==null){ okValidate=false; messange=messange+"Seleccione Tipo Poblacion\n"; }
			
			if(paqueteNutricionalBox.getValue()==null){ okValidate=false; messange=messange+"Seleccione Paquete Nutricional\n"; }
			
			if(programasBox.getValue()==null){ okValidate=false; messange=messange+"Seleccione Programa\n"; }
			
			if(regimenesBox.getValue()==null){ okValidate=false; messange=messange+"Seleccione Seleccione Regimen\n"; }
			
			if(entidadesBox.getValue()==null){ okValidate=false; messange=messange+"Seleccione Entidad EPS\n"; }
			
			if(remisionBox.getValue()==null){ okValidate=false; messange=messange+"Seleccione Remision\n"; }
			
			if(micronutrientesBox.getValue()==null){ okValidate=false; messange=messange+"Seleccione Micronutrientes\n"; }
			
			if(educacionNutricionalBox.getValue()==null){ okValidate=false; messange=messange+"Seleccione Educacion Nutricional\n"; }
			
			if(actividadesBox.getValue()==null){ okValidate=false; messange=messange+"Seleccione Actividad Fisica\n"; }
			
			if(riesgosBox.getValue()==null){ okValidate=false; messange=messange+"Seleccione Riesgo\n"; }
			
			String limiteVal=limitesGestantes();
			if(!limiteVal.equals("")){
				okValidate=false; 
				messange=messange+limiteVal+"\n";
			}
			
			if(!okValidate){
				Alert alert = new Alert(AlertType.ERROR);
	        	alert.setTitle("Gestantes");
	        	alert.setHeaderText("Validacion de formulario");
	        	alert.setContentText(messange);
	        	alert.showAndWait();
			}
		}catch(Exception e){
			Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Gestantes");
        	alert.setHeaderText("Validacion de formulario");
        	alert.setContentText("Error de Validacion");
        	alert.showAndWait();
		}
		
		return okValidate;
	}
	
	@SuppressWarnings("finally")
	private String limitesGestantes(){
		String mensajeError="";
		try{
			String tallaGestante=gestanteTallaField.getText();
			tallaGestante=tallaGestante.replace(",", ".");
			String pesoGestante=gestantePesoActualField.getText();
			pesoGestante=pesoGestante.replace(",", ".");
			String semanasGestacion=gestanteSemanasGestacionField.getText();
			semanasGestacion=semanasGestacion.replace(",", ".");
			
			if(pesoGestante.equals(""))
				pesoGestante="0";
			if(tallaGestante.equals(""))
				tallaGestante="0";
			
			if((Double.valueOf(pesoGestante)<15)||(Double.valueOf(pesoGestante)>170)){
				mensajeError="El Peso de la Gestante esta fuera de los limites establecidos";
			}
			
			if((Double.valueOf(tallaGestante)<100)||(Double.valueOf(tallaGestante)>190)){
				mensajeError="La Talla de la Gestante esta fuera de los limites establecidos";
			}
			
			if((Double.valueOf(semanasGestacion)<0)||(Double.valueOf(semanasGestacion)>43)){
				mensajeError="las Semanas de gestacion estan fuera de los limites establecidos";
			}
			
			try{
				int semanVal=Integer.valueOf(semanasGestacion);
				System.out.println(semanVal);
				if(semanVal<=5){
					mensajeAlertSemOneField.setVisible(true);
					mensajeAlertSemTwoField.setVisible(true);
				}else{
					mensajeAlertSemOneField.setVisible(false);
					mensajeAlertSemTwoField.setVisible(false);
				}
			}
			catch(Exception er){
				mensajeAlertSemOneField.setVisible(false);
				mensajeAlertSemTwoField.setVisible(false);
			}
			
		}catch(Exception e){
		}finally{
			return mensajeError;
		}
	}
	
	private void iniciaCamposDinamicos() {
		try{
			dinamicos=repoDinamicos.findDinamicoById("GESCAMPO1");
			if(dinamicos.getDad_Estado().equals("A")){
				disponibleUnoField.setVisible(true);
				disponibleUnoLabel.setVisible(true);
				disponibleUnoLabel.setText(dinamicos.getDad_Nombre());
			}
			
			dinamicos=repoDinamicos.findDinamicoById("GESCAMPO2");
			if(dinamicos.getDad_Estado().equals("A")){
				disponibleDosField.setVisible(true);
				disponibleDosLabel.setVisible(true);
				disponibleDosLabel.setText(dinamicos.getDad_Nombre());
			}
			
			dinamicos=repoDinamicos.findDinamicoById("GESCAMPO3");
			if(dinamicos.getDad_Estado().equals("A")){
				disponibleTresField.setVisible(true);
				disponibleTresLabel.setVisible(true);
				disponibleTresLabel.setText(dinamicos.getDad_Nombre());
			}
			
			dinamicos=repoDinamicos.findDinamicoById("GESCAMPO4");
			if(dinamicos.getDad_Estado().equals("A")){
				disponibleCuatroField.setVisible(true);
				disponibleCuatroLabel.setVisible(true);
				disponibleCuatroLabel.setText(dinamicos.getDad_Nombre());
			}
		}
		catch(Exception e){
			
		}	
	}
	
	public void setMainApp(Main mainApp, Session session, Pacientes paciente, Paciente_Detalles paciente_detalle, Gestantes gestante2, Date fechaConsulta) {
		this.mainApp = mainApp;
		this.session=session;
		this.paciente=paciente;
		this.pacientes_detalle=paciente_detalle;
		this.fechaConsulta=fechaConsulta;
		cargaComponentes();
		iniciaCamposDinamicos();
		loadFormGestantes();
		if(gestante2!=null){
			this.gestante=gestante2;
			loadGestanteForm();
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
