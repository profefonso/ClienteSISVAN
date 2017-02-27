package co.gov.cundinamarca.sisvan.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;

import co.gov.cundinamarca.sisvan.Main;
import co.gov.cundinamarca.sisvan.model.parameters.Paciente_Detalles;
import co.gov.cundinamarca.sisvan.model.parameters.Pacientes;
import co.gov.cundinamarca.sisvan.model.transactions.Gestantes;
import co.gov.cundinamarca.sisvan.model.transactions.Menores;
import co.gov.cundinamarca.sisvan.repository.GestantesRepository;
import co.gov.cundinamarca.sisvan.repository.MenoresRepository;
import co.gov.cundinamarca.sisvan.repository.Paciente_DetallesRepository;
import co.gov.cundinamarca.sisvan.repository.PacientesRepository;
import co.gov.cundinamarca.sisvan.utilSiiweb.DatosCalculados;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HistoricoGraficasGestantesController {
	private Main mainApp;
	private Session session;
	private Stage dialogStage;
	
	Pacientes paciente;
	Paciente_Detalles paciente_detalles;
	Gestantes gestante;
	DatosCalculados datosCaculados;
		
	PacientesRepository repoPacientes;
	Paciente_DetallesRepository repoPaciente_Detalles;
	GestantesRepository repoGestantes;
	
	List<String> listTallas=new ArrayList<String>();
	List<String> listPesos=new ArrayList<String>();
	List<String> listIMC=new ArrayList<String>();
	List<String> listSemanasGestacion=new ArrayList<String>();
	
	String scriptGraphicImcPregnant;
		
	@FXML
	private TextField pacienteIdentificacionField;
	@FXML
	private TextField pacienteNombreField;
	@FXML
	private TextField pacienteFechaNacimientoField;
	@FXML
	private TextField pacienteSexoField;
	
	@FXML
    private void initialize() {
		paciente=new Pacientes();
		paciente_detalles=new Paciente_Detalles();
	}
	
	@FXML
	private void handleCloseWindow() {
	    this.dialogStage.close();
	}
	
	@FXML
	private void handleIrGraphicImcPregnant() {
		String imcG="[";
		Iterator<String> iter = listIMC.iterator();
		int i=0;
		listIMC.size();
		while (iter.hasNext()){
			imcG+="'"+iter.next()+"'";
			i++;
			if(i<listIMC.size())
				imcG+=",";
		}
		imcG+="]";
		
		String semanasG="[";
		Iterator<String> itere = listSemanasGestacion.iterator();
		int j=0;
		listSemanasGestacion.size();
		while (itere.hasNext()){
			semanasG+="'"+itere.next()+"'";
			j++;
			if(j<listSemanasGestacion.size())
				semanasG+=",";
		}
		semanasG+="]";
		
		scriptGraphicImcPregnant="draw(getGraphicImcPregnant("+imcG+","+semanasG+"))";
		System.out.println(scriptGraphicImcPregnant);
		mainApp.showGraphicsOverview(scriptGraphicImcPregnant);
	}
	
	private void cargaComponentes(){
		repoPacientes=new PacientesRepository(session);
		repoPaciente_Detalles=new Paciente_DetallesRepository(session);
		repoGestantes=new GestantesRepository(session);
		datosCaculados=new DatosCalculados();
		loadFormGetantes();
	}
	
	private void loadFormGetantes(){
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
	}
	
	private void historicoGestanteVisitas(){	
		List<Gestantes> listGestantes=repoGestantes.buscaGestanteByIdentificacion(paciente.getPac_Id());
    	Gestantes gestanteFind=new Gestantes();
		Iterator<Gestantes> iter = listGestantes.iterator();
		while (iter.hasNext()){
			gestanteFind=(Gestantes) iter.next();
			String imcCalculado=datosCaculados.calculaIMC(gestanteFind.getGes_Peso_Pregestacion(), gestanteFind.getGes_Talla());
			listTallas.add(gestanteFind.getGes_Talla().toString());
			listPesos.add(gestanteFind.getGes_Peso_Pregestacion().toString());
			listIMC.add(imcCalculado);
			listSemanasGestacion.add(gestanteFind.getGes_Edadgestacional().toString());
		}
	}
	
	public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
	
	public void setMainApp(Main mainApp, Session session, Pacientes paciente) {
		this.mainApp = mainApp;
		this.session=session;
		this.paciente=paciente;
		cargaComponentes();
		historicoGestanteVisitas();
    }
}
