package co.gov.cundinamarca.sisvan.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;

import co.gov.cundinamarca.sisvan.Main;
import co.gov.cundinamarca.sisvan.model.parameters.Paciente_Detalles;
import co.gov.cundinamarca.sisvan.model.parameters.Pacientes;
import co.gov.cundinamarca.sisvan.model.transactions.Menores;
import co.gov.cundinamarca.sisvan.repository.MenoresRepository;
import co.gov.cundinamarca.sisvan.repository.Paciente_DetallesRepository;
import co.gov.cundinamarca.sisvan.repository.PacientesRepository;
import co.gov.cundinamarca.sisvan.utilSiiweb.DatosCalculados;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HistoricoGraficasMenoresController {
	private Main mainApp;
	private Session session;
	private Stage dialogStage;
	
	Pacientes paciente;
	Paciente_Detalles paciente_detalles;
	Menores menor;
	DatosCalculados datosCaculados;
		
	PacientesRepository repoPacientes;
	Paciente_DetallesRepository repoPaciente_Detalles;
	MenoresRepository repoMenores;
	
	List<String> listTallas=new ArrayList<String>();
	List<String> listEdadesAproximadas=new ArrayList<String>();
	List<String> listPesos=new ArrayList<String>();
	List<String> listPerimetrosCefalicos=new ArrayList<String>();
	List<String> listIMC=new ArrayList<String>();
	
	String scriptGraphicAgeSize;
	String scriptGraphicWeightAge;
	String scriptGraphicHeadAge;
	String scriptGraphicImcAge;
	String scriptGraphicWeightSize;
	
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
	private void handleIrGraphicsAgeSize() {
		String tallasM="[";
		Iterator<String> iter = listTallas.iterator();
		int i=0;
		listTallas.size();
		while (iter.hasNext()){
			tallasM+="'"+iter.next()+"'";
			i++;
			if(i<listTallas.size())
				tallasM+=",";
		}
		tallasM+="]";
		
		String edadesM="[";
		Iterator<String> itere = listEdadesAproximadas.iterator();
		int j=0;
		listEdadesAproximadas.size();
		while (itere.hasNext()){
			edadesM+="'"+itere.next()+"'";
			j++;
			if(j<listEdadesAproximadas.size())
				edadesM+=",";
		}
		edadesM+="]";
		
		scriptGraphicAgeSize="draw(dataGraphicSizeAge("+tallasM+","+edadesM+",'"+paciente.getPac_Sexo()+"'))";
		System.out.println(scriptGraphicAgeSize);
		mainApp.showGraphicsOverview(scriptGraphicAgeSize);
	}
	
	@FXML
	private void handleIrGraphicsWeightAge() {
		String pesosM="[";
		Iterator<String> iter = listPesos.iterator();
		int i=0;
		listPesos.size();
		while (iter.hasNext()){
			pesosM+="'"+iter.next()+"'";
			i++;
			if(i<listPesos.size())
				pesosM+=",";
		}
		pesosM+="]";
		
		String edadesM="[";
		Iterator<String> itere = listEdadesAproximadas.iterator();
		int j=0;
		listEdadesAproximadas.size();
		while (itere.hasNext()){
			edadesM+="'"+itere.next()+"'";
			j++;
			if(j<listEdadesAproximadas.size())
				edadesM+=",";
		}
		edadesM+="]";
		
		scriptGraphicWeightAge="draw(dataGraphicWeightAge("+pesosM+","+edadesM+",'"+paciente.getPac_Sexo()+"'))";
		System.out.println(scriptGraphicWeightAge);
		mainApp.showGraphicsOverview(scriptGraphicWeightAge);
	}
	
	@FXML
	private void handlerIrGraphicsHeadAge(){
		String perimetrosM="[";
		Iterator<String> iter = listPerimetrosCefalicos.iterator();
		int i=0;
		listPerimetrosCefalicos.size();
		while (iter.hasNext()){
			perimetrosM+="'"+iter.next()+"'";
			i++;
			if(i<listPerimetrosCefalicos.size())
				perimetrosM+=",";
		}
		perimetrosM+="]";
		
		String edadesM="[";
		Iterator<String> itere = listEdadesAproximadas.iterator();
		int j=0;
		listEdadesAproximadas.size();
		while (itere.hasNext()){
			edadesM+="'"+itere.next()+"'";
			j++;
			if(j<listEdadesAproximadas.size())
				edadesM+=",";
		}
		edadesM+="]";
		
		scriptGraphicHeadAge="draw(dataGraphicHeadAge("+perimetrosM+","+edadesM+",'"+paciente.getPac_Sexo()+"'))";
		System.out.println(scriptGraphicHeadAge);
		mainApp.showGraphicsOverview(scriptGraphicHeadAge);
	}
	
	@FXML
	private void handleIrGraphicsImcAge() {
		String imcM="[";
		Iterator<String> iter = listIMC.iterator();
		int i=0;
		listIMC.size();
		while (iter.hasNext()){
			imcM+="'"+iter.next()+"'";
			i++;
			if(i<listIMC.size())
				imcM+=",";
		}
		imcM+="]";
		
		String edadesM="[";
		Iterator<String> itere = listEdadesAproximadas.iterator();
		int j=0;
		listEdadesAproximadas.size();
		while (itere.hasNext()){
			edadesM+="'"+itere.next()+"'";
			j++;
			if(j<listEdadesAproximadas.size())
				edadesM+=",";
		}
		edadesM+="]";
		
		scriptGraphicImcAge="draw(dataGraphicImcAge("+imcM+","+edadesM+",'"+paciente.getPac_Sexo()+"'))";
		System.out.println(scriptGraphicImcAge);
		mainApp.showGraphicsOverview(scriptGraphicImcAge);
	}
	
	@FXML
	private void handleIrGraphicsWeightSize() {
		String pesosM="[";
		Iterator<String> iter = listPesos.iterator();
		int i=0;
		listPesos.size();
		while (iter.hasNext()){
			pesosM+="'"+iter.next()+"'";
			i++;
			if(i<listPesos.size())
				pesosM+=",";
		}
		pesosM+="]";
		
		String tallasM="[";
		Iterator<String> iterT = listTallas.iterator();
		int k=0;
		listTallas.size();
		while (iterT.hasNext()){
			tallasM+="'"+iterT.next()+"'";
			k++;
			if(k<listTallas.size())
				tallasM+=",";
		}
		tallasM+="]";
		
		String edadesM="[";
		Iterator<String> itere = listEdadesAproximadas.iterator();
		int j=0;
		listEdadesAproximadas.size();
		while (itere.hasNext()){
			edadesM+="'"+itere.next()+"'";
			j++;
			if(j<listEdadesAproximadas.size())
				edadesM+=",";
		}
		edadesM+="]";
		
		scriptGraphicWeightSize="draw(dataGraphicWeightSize("+pesosM+","+tallasM+","+edadesM+",'"+paciente.getPac_Sexo()+"'))";
		System.out.println(scriptGraphicWeightSize);
		mainApp.showGraphicsOverview(scriptGraphicWeightSize);
	}
	
	private void cargaComponentes(){
		repoPacientes=new PacientesRepository(session);
		repoPaciente_Detalles=new Paciente_DetallesRepository(session);
		repoMenores=new MenoresRepository(session);
		datosCaculados=new DatosCalculados();
		loadFormMenores();
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
	}
	
	private void historicoMenorVisitas(){	
		List<Menores> listMenores=repoMenores.buscarMenoresByIdentificacion(paciente.getPac_Id());
    	Menores menorFind=new Menores();
		Iterator<Menores> iter = listMenores.iterator();
		while (iter.hasNext()){
			menorFind=(Menores) iter.next();
			menorFind.setPacienteNombreFull(repoPacientes.nombreByIdentificacion(menorFind.getPac_Mnor_Id()));
			String edadAproximadaCalculada=datosCaculados.edadAproximadaGrafica(paciente.getPac_Fechanacimiento(),menorFind.getMnor_Fecha_Reporte());
			String imcCalculado=datosCaculados.calculaIMC(menorFind.getMnor_Peso(), menorFind.getMnor_Talla());
			listTallas.add(menorFind.getMnor_Talla().toString());
			listEdadesAproximadas.add(edadAproximadaCalculada);
			listPesos.add(menorFind.getMnor_Peso().toString());
			listPerimetrosCefalicos.add(menorFind.getMnor_Perimetro_Cefalico().toString());
			listIMC.add(imcCalculado);
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
		historicoMenorVisitas();
    }
}
