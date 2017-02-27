package co.gov.cundinamarca.sisvan.view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.hibernate.Session;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.Xpp3Driver;

import co.com.siiweb.clientes.ConsumoCargaSisvan;
import co.com.siiweb.sisvancrypto.Crypto;
import co.gov.cundinamarca.sisvan.Main;
import co.gov.cundinamarca.sisvan.model.parameters.Grupos_Exportacion;
import co.gov.cundinamarca.sisvan.model.parameters.Paciente_Detalles;
import co.gov.cundinamarca.sisvan.model.parameters.Pacientes;
import co.gov.cundinamarca.sisvan.model.transactions.Adultos;
import co.gov.cundinamarca.sisvan.model.transactions.Gestantes;
import co.gov.cundinamarca.sisvan.model.transactions.Menores;
import co.gov.cundinamarca.sisvan.repository.AdultosRepository;
import co.gov.cundinamarca.sisvan.repository.GestantesRepository;
import co.gov.cundinamarca.sisvan.repository.Grupos_ExportacionRepository;
import co.gov.cundinamarca.sisvan.repository.MenoresRepository;
import co.gov.cundinamarca.sisvan.repository.Paciente_DetallesRepository;
import co.gov.cundinamarca.sisvan.repository.PacientesRepository;
import co.gov.cundinamarca.sisvan.utilSiiweb.Utilities;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

public class ExportarTransaccionesOverviewController {
	
	private Main mainApp;
	private StringBuilder buf;
	File fileWriter;
	private long elementosEnviados;
	private long elementosCargados;
	boolean okEnvioInformacion;
	BufferedWriter bw;
	Session session;
	String idGrupoT;
	XStream xstream;
	
	Grupos_ExportacionRepository repoGrpo_Exportacion;
	PacientesRepository repoPacientes;
	Paciente_DetallesRepository repoPaciente_Detalle;
	MenoresRepository repoMenores;
	AdultosRepository repoAdultos;
	GestantesRepository repoGestantes;
		
	@FXML
	TableView<Grupos_Exportacion> tableGrupos = new TableView();
	@FXML
	TableColumn<Grupos_Exportacion, String> tablaColumn = new TableColumn<>("Tabla");
	@FXML
	TableColumn<Grupos_Exportacion, String> registroColumn = new TableColumn<>("Registro");
	@FXML
	TableColumn<Grupos_Exportacion, String> fechaColumn = new TableColumn<>("Fecha");
	@FXML
	TableColumn<Grupos_Exportacion, String> tipoColumn = new TableColumn<>("Tipo");
	@FXML
	TableColumn<Grupos_Exportacion, String> idColumn = new TableColumn<>("ID");
	@FXML
	TableColumn<Grupos_Exportacion, String> idGrupoColumn = new TableColumn<>("ID GRUPO");
	
	@FXML
	TextField totalNSField;
	
	@FXML
	TextArea areaBuf;
	
	@FXML
	ProgressBar progressBar;
	@FXML
	Label labelProgress;
	
	@FXML
	Button butonEnviarInfoWeb;
	@FXML
	Button butonEnviarInfoFile;
	@FXML
	Button butonSeeAll;
	@FXML
	Button butonLoadFile;
	
	@SuppressWarnings("rawtypes")
	Task copyWorker;
	
	@FXML
    private void initialize() {
		labelProgress.setText("Listo para Enviar Información");
    }
	
	@FXML
	private void handleSeeAll() {
		butonSeeAll.setDisable(true);
		iniciarVerTodos();
	}
	
	@FXML
	private void handleCancelar() {
		if(copyWorker!=null){
			copyWorker.cancel(true);
	        progressBar.progressProperty().unbind();
	        progressBar.setProgress(0);
	        System.out.println("cancelled.");
		}
		mainApp.dashBoardOverview();
	}
	
	private void cancelOper(){
		try {
			copyWorker.cancel();
			synchronized (copyWorker) {
			 //copyWorker.wait();
			}
		} catch (Exception e) {
			 // TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("rawtypes")
	public Task createWorker() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(1000);
                    updateMessage(String.valueOf(i));
                    updateProgress(i + 1, 10);
                }
                return true;
            }
        };
    }
	
	private void iniciarVerTodos() {
		tableGrupos.getItems().clear();
		butonEnviarInfoWeb.setDisable(true);
		butonEnviarInfoFile.setDisable(true);
		butonSeeAll.setDisable(true);
		butonLoadFile.setDisable(true);
		copyWorker = createWorker();
        progressBar.progressProperty().unbind();
        progressBar.setProgress(ProgressBar.INDETERMINATE_PROGRESS);
        progressBar.progressProperty().bind(copyWorker.progressProperty());
        
        copyWorker.messageProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println(newValue);
                switch (newValue) {
				case "0":
					labelProgress.setText("Preparando Información.");
					break;
				case "1":
					labelProgress.setText("Iniciando..");
					break;
				case "2":
					labelProgress.setText("Generando Información...");
					break;
				case "3":
					labelProgress.setText("Creando Grupo...");
					break;
				case "4":
					break;
				case "5":
					//Inicia envio de datos cargados al servidor
					iniciaGrupoExportacion();
					break;
				case "6":
					labelProgress.setText("Mostrando Resultados...");
					break;
				case "7":
					
					break;
				case "8":
					labelProgress.setText("Operación Finalizada");
					break;
				case "9":
					labelProgress.setText("Operación Finalizada");
					butonEnviarInfoWeb.setDisable(false);
					butonEnviarInfoFile.setDisable(false);
					butonSeeAll.setDisable(false);
					butonLoadFile.setDisable(false);
					break;
				default:
					break;
				}
            }
        });
        
        new Thread(copyWorker).start();
	}
	
	private void iniciarExportarFile() {
		tableGrupos.getItems().clear();
		butonEnviarInfoWeb.setDisable(true);
		butonEnviarInfoFile.setDisable(true);
		butonSeeAll.setDisable(true);
		butonLoadFile.setDisable(true);
		copyWorker = createWorker();
        progressBar.progressProperty().unbind();
        progressBar.setProgress(ProgressBar.INDETERMINATE_PROGRESS);
        progressBar.progressProperty().bind(copyWorker.progressProperty());
        
        copyWorker.messageProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println(newValue);
                switch (newValue) {
				case "0":
					labelProgress.setText("Preparando Información.");
					break;
				case "1":
					labelProgress.setText("Iniciando..");
					break;
				case "2":
					labelProgress.setText("Generando Información...");
					break;
				case "3":
					labelProgress.setText("Creando Archivo...");
					break;
				case "4":
					break;
				case "5":
					//Inicia envio de datos cargados al servidor
					iniciaGrupoExportacion();
					obtenerInfoExport();
					break;
				case "6":
					labelProgress.setText("Exportando Archivo...");
					break;
				case "7":
					break;
				case "8":
					labelProgress.setText("Operación Finalizada");
					break;
				case "9":
					labelProgress.setText("Operación Finalizada");
					butonEnviarInfoWeb.setDisable(false);
					butonEnviarInfoFile.setDisable(false);
					butonSeeAll.setDisable(false);
					butonLoadFile.setDisable(false);
					finalizaCreacionFile();
					break;
				default:
					break;
				}
            }
        });
        
        new Thread(copyWorker).start();
	}
	
	@FXML
	private void handleIniciarEnvioWEB() {
		butonEnviarInfoWeb.setDisable(true);
		butonEnviarInfoFile.setDisable(true);
		butonSeeAll.setDisable(true);
		butonLoadFile.setDisable(true);
		copyWorker = createWorker();
		okEnvioInformacion=false;
        progressBar.progressProperty().unbind();
        progressBar.setProgress(ProgressBar.INDETERMINATE_PROGRESS);
        progressBar.progressProperty().bind(copyWorker.progressProperty());
        
        copyWorker.messageProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println(newValue);
                switch (newValue) {
				case "0":
					labelProgress.setText("Preparando Información.");
					break;
				case "1":
					labelProgress.setText("Iniciando..");
					break;
				case "2":
					labelProgress.setText("Estableciendo comunicación...");
					break;
				case "3":
					labelProgress.setText("Enviando información transaccional...");
					break;
				case "4":
					break;
				case "5":
					//Inicia envio de datos cargados al servidor
					okEnvioInformacion=iniciaExportacion();
					break;
				case "6":
					labelProgress.setText("Finalizando envio...");
					break;
				case "7":
					
					break;
				case "8":
					labelProgress.setText("Operación Finalizada");
					break;
				case "9":
					labelProgress.setText("Operación Finalizada");
					finalizaSincronizacion();
					butonEnviarInfoWeb.setDisable(false);
					butonEnviarInfoFile.setDisable(false);
					butonSeeAll.setDisable(false);
					butonLoadFile.setDisable(false);
					break;
				default:
					break;
				}
            }
        });
        
        new Thread(copyWorker).start();
	}
	
	private void finalizaSincronizacion() {
		try
        {
			Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setTitle("Envio de Informacion");
        	alert.setHeaderText("Finalizada");
        	alert.setContentText("Envio Finalizado Exitosamente");
        	alert.showAndWait();
        }catch(Exception e){
        	e.printStackTrace();
        }
	}
	
	private void finalizaCreacionFile() {
		try
        {
			Alert alert = new Alert(AlertType.CONFIRMATION);
        	alert.setTitle("Exportar");
        	alert.setHeaderText("Exportar Informacion");
        	alert.setContentText("Archivo Generado con Exito");
        	alert.showAndWait();
        }catch(Exception e){
        	e.printStackTrace();
        }
	}
	
	@SuppressWarnings("finally")
	public boolean iniciaExportacion(){
		elementosEnviados=0;
		elementosCargados=0;
		buf = new StringBuilder();
		boolean okExportacion=true;
		boolean errorConexion=false;
		String IdGrupoCargue="";
		try{
									
			//Recopila informacion no sincorizada de Menores
			List listadoMenoresNoSync=repoMenores.selectNoSyncronizate();
			Iterator iterMenores = listadoMenoresNoSync.iterator();
			Menores menor=new Menores();
			while (iterMenores.hasNext() && !errorConexion){
				Grupos_Exportacion grupoExpor=new Grupos_Exportacion();
				menor=(Menores) iterMenores.next();
				IdGrupoCargue=generaIDGrupo();
				grupoExpor.setGre_Grupo_Id(IdGrupoCargue);
				grupoExpor.setGre_Tabla("MENORES");
				grupoExpor.setGre_Registro(menor.getMnor_Id().toString());
				grupoExpor.setGre_Fecha_Registro(menor.getMnor_Fecha());
				grupoExpor.setGre_Estado("T");
				grupoExpor.setGre_Fecha(new Date());
				grupoExpor.setGre_Descripcion("Registro");
				repoGrpo_Exportacion.saveGrupo(grupoExpor);
				if(enviarRegistros(IdGrupoCargue)){
					elementosEnviados++;
				}else{
					errorConexion=true;
					break;
				}
			}
			
			//Recopila informacion no sincorizada de Gestantes
			List listadoGestantesNoSync=repoGestantes.selectNoSyncronizate();
			Iterator iterGestantes = listadoGestantesNoSync.iterator();
			Gestantes gestante=new Gestantes();
			while (iterGestantes.hasNext() && !errorConexion){
				Grupos_Exportacion grupoExpor=new Grupos_Exportacion();
				gestante=(Gestantes) iterGestantes.next();
				IdGrupoCargue=generaIDGrupo();
				grupoExpor.setGre_Grupo_Id(IdGrupoCargue);
				grupoExpor.setGre_Tabla("GESTANTES");
				grupoExpor.setGre_Registro(gestante.getGes_Id().toString());
				grupoExpor.setGre_Fecha_Registro(gestante.getGes_Fecha());
				grupoExpor.setGre_Estado("T");
				grupoExpor.setGre_Fecha(new Date());
				grupoExpor.setGre_Descripcion("Registro");
				repoGrpo_Exportacion.saveGrupo(grupoExpor);
				if(enviarRegistros(IdGrupoCargue)){
					elementosEnviados++;
				}else{
					errorConexion=true;
					break;
				}
			}
			
			//Recopila informacion no sincorizada de Adultos
			List listadoAdultosNoSync=repoAdultos.selectNoSyncronizate();
			Iterator iterAdultos = listadoAdultosNoSync.iterator();
			Adultos adulto=new Adultos();
			while (iterAdultos.hasNext() && !errorConexion){
				Grupos_Exportacion grupoExpor=new Grupos_Exportacion();
				adulto=(Adultos) iterAdultos.next();
				IdGrupoCargue=generaIDGrupo();
				grupoExpor.setGre_Grupo_Id(IdGrupoCargue);
				grupoExpor.setGre_Tabla("ADULTOS");
				grupoExpor.setGre_Registro(adulto.getAdu_Id().toString());
				grupoExpor.setGre_Fecha_Registro(adulto.getAdu_Fecha());
				grupoExpor.setGre_Estado("T");
				grupoExpor.setGre_Fecha(new Date());
				grupoExpor.setGre_Descripcion("Registro");
				repoGrpo_Exportacion.saveGrupo(grupoExpor);
				if(enviarRegistros(IdGrupoCargue)){
					elementosEnviados++;
				}else{
					errorConexion=true;
					break;
				}
			}	
			
			session.flush();
            session.clear();
			cuentaNoSincronizados();
			tableGrupos.getItems().clear();
			
		}catch(Exception e){
			this.cancelOper();
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Exportar");
        	alert.setHeaderText("Exportar Informacion");
        	alert.setContentText("Error al exportar informacion\n"+e.toString());
        	alert.showAndWait();
		}finally{
			System.out.println("Errores: "+buf);
			System.out.println("Elementos Enviados: "+elementosEnviados);
			System.out.println("Elementos Exitosos: "+elementosCargados);
			areaBuf.setText("Elementos Enviados: "+elementosEnviados+"\n"+"Elementos Exitosos: "+elementosCargados+"\n\n"+buf);
			return okExportacion;
		}
	}
	
	@FXML
    private void handleSaveExport() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "SVN files (*.svn)", "*.svn");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

        if (file != null) {
            // Make sure it has the correct extension
            if (!file.getPath().endsWith(".svn")) {
                file = new File(file.getPath() + ".svn");
            }
            fileWriter=file;
            saveExportToFile();
        }
    }
	
	@FXML
	private void handleOpenCertificado() {
		 FileChooser fileChooser = new FileChooser();
		 // Set extension filter
	     FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("SVN files (*.svn)", "*.svn");
	     fileChooser.getExtensionFilters().add(extFilter);
	     // Show save file dialog
	     File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
	     if (file != null) {
	    	 if(leeCertificado(file)){
	    		 Alert alert = new Alert(AlertType.CONFIRMATION);
		    	 alert.setTitle("Certificado");
		         alert.setHeaderText("Respuesta de Server");
		         alert.setContentText("Certificado Cargado Exitosamente!");
		         alert.showAndWait();
		         mainApp.dashBoardOverview();
	    	 }else{
	    		 Alert alert = new Alert(AlertType.ERROR);
		    	 alert.setTitle("Certificado");
		         alert.setHeaderText("Respuesta de Server");
		         alert.setContentText("Error Cargando Certificado!");
		         alert.showAndWait();
	    	 }
	     }
	}
	
	private boolean leeCertificado(File file) {
		String cadena;
		boolean okCertificado=false;
	    try {
	    	FileReader f = new FileReader(file);
		    BufferedReader b = new BufferedReader(f);
			while((cadena = b.readLine())!=null) {
				cadena=Crypto.decrypt(cadena);
				System.out.println("Archivo:"+cadena);
				String[] respuestaServer=cadena.split(";");
				if(respuestaServer!=null){
					if((respuestaServer[0]!=null)&&(respuestaServer[1]!=null)){
						if(respuestaServer[0].equalsIgnoreCase("OK")){
							if(!respuestaServer[1].equals("")){
								String IdGrupoCargue=respuestaServer[1];
								if(actualizarCargue(IdGrupoCargue)){
									okCertificado=true;
								}
							}
						}
					}
				}
			}
			b.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    catch(Exception ef){
	    	ef.printStackTrace();
	    }
		return okCertificado;
	}

	private boolean actualizarCargue(String IdGrupoCargue) {
		try{
			// 1. obtenemos la informacion del grupo a actualizar
			List grupoActualizarList=repoGrpo_Exportacion.findExportGrupo(IdGrupoCargue);
			Iterator<Grupos_Exportacion> iter = grupoActualizarList.iterator();
			Grupos_Exportacion registroGrupo=new Grupos_Exportacion();
			
			Menores menorF=new Menores();
			Adultos adultoF=new Adultos();
			Gestantes gestantef=new Gestantes();
			Pacientes pacientef=new Pacientes();
			Paciente_Detalles paciente_detallesf=new Paciente_Detalles();
			
			while (iter.hasNext()){
				registroGrupo=iter.next();
				
				if(registroGrupo.getGre_Tabla().equals("MENORES")){
					menorF=repoMenores.getMenor(registroGrupo.getGre_Registro());
					pacientef=repoPacientes.findById(menorF.getPac_Mnor_Id());
					paciente_detallesf=repoPaciente_Detalle.findById(menorF.getPac_Mnor_Id());
					if((menorF!=null)&&(pacientef!=null)&&(paciente_detallesf!=null)){
						menorF.setMnor_sync("S");
						pacientef.setPac_sync("S");
						paciente_detallesf.setPacd_sync("S");
						repoMenores.saveMenor(menorF);
						repoPacientes.savePaciente(pacientef);
						repoPaciente_Detalle.saveDetallePaciente(paciente_detallesf);
					}
				}
				
				if(registroGrupo.getGre_Tabla().equals("ADULTOS")){
					adultoF=repoAdultos.getAdulto(registroGrupo.getGre_Registro());
					pacientef=repoPacientes.findById(adultoF.getPac_Adu_Id());
					paciente_detallesf=repoPaciente_Detalle.findById(adultoF.getPac_Adu_Id());
					if((adultoF!=null)&&(pacientef!=null)&&(paciente_detallesf!=null)){
						adultoF.setAdu_sync("S");
						pacientef.setPac_sync("S");
						paciente_detallesf.setPacd_sync("S");
						repoAdultos.saveAdulto(adultoF);
						repoPacientes.savePaciente(pacientef);
						repoPaciente_Detalle.saveDetallePaciente(paciente_detallesf);
					}
				}
				
				if(registroGrupo.getGre_Tabla().equals("GESTANTES")){
					gestantef=repoGestantes.getGestante(registroGrupo.getGre_Registro());
					pacientef=repoPacientes.findById(gestantef.getPac_Ges_Id());
					paciente_detallesf=repoPaciente_Detalle.findById(gestantef.getPac_Ges_Id());
					if((gestantef!=null)&&(pacientef!=null)&&(paciente_detallesf!=null)){
						gestantef.setGes_sync("S");
						pacientef.setPac_sync("S");
						paciente_detallesf.setPacd_sync("S");
						repoGestantes.saveGestante(gestantef);
						repoPacientes.savePaciente(pacientef);
						repoPaciente_Detalle.saveDetallePaciente(paciente_detallesf);
					}
				}
			}
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	private void saveExportToFile() {
		try {
			iniciarExportarFile();           
		} catch (Exception e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Exportar");
        	alert.setHeaderText("Exportar Informacion");
        	alert.setContentText("Error al Generar Archivo\n"+e.toString());
        	alert.showAndWait();
		}
	}

	private void obtenerInfoExport() {
		String xmlReturn="";
		String upgd=mainApp.getInstitucionApp().getIps_Id();
		String usuario=mainApp.getUsuarioApp().getUsu_Id();
		String xmlInicial="<sisvan>";
		xmlInicial += "<usuario_id>"+usuario+"</usuario_id>";
		xmlInicial += "<upgd>"+upgd+"</upgd>";
		xmlInicial += "<grupo>"+idGrupoT+"</grupo>";
		try{
			bw = new BufferedWriter(new FileWriter(fileWriter));
			// 1. obtenemos la informacion del grupo a exportar
			List grupoAExportarListMenores=repoGrpo_Exportacion.findExportGrupo(idGrupoT);
			Iterator<Grupos_Exportacion> iter = grupoAExportarListMenores.iterator();
			Grupos_Exportacion registroGrupo=new Grupos_Exportacion();
			
			Menores menorF=new Menores();
			Adultos adultoF=new Adultos();
			Gestantes gestantef=new Gestantes();
			Pacientes pacientef=new Pacientes();
			Paciente_Detalles paciente_detallesf=new Paciente_Detalles();
			
			 String defaultFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS";
			 String[] acceptableFormats = new String[]{"yyyyMM"};
			 DateConverter dateConverter = new DateConverter(defaultFormat, acceptableFormats );
			
			XStream xstream = new XStream(new DomDriver("UTF_8", new NoNameCoder()));
			xstream.autodetectAnnotations(true);
			xstream.registerConverter(dateConverter);
			
			int contador=0;
			int total=grupoAExportarListMenores.size();
			String operationStr="create";
			while (iter.hasNext()){
				registroGrupo=iter.next();
				contador++;
				if(contador==total){
					operationStr="end";
				}else{
					operationStr="";
				}
				
				if(registroGrupo.getGre_Tabla().equals("MENORES")){
					menorF=repoMenores.getMenor(registroGrupo.getGre_Registro());
					pacientef=repoPacientes.findById(menorF.getPac_Mnor_Id());
					paciente_detallesf=repoPaciente_Detalle.findById(menorF.getPac_Mnor_Id());
					if((menorF!=null)&&(pacientef!=null)&&(paciente_detallesf!=null)){
						xmlReturn =xmlInicial+"<table>Menores</table><final>"+operationStr+"</final><operation>CREATE</operation>";
						xmlReturn += xstream.toXML(menorF);
						xmlReturn += xstream.toXML(pacientef);
						xmlReturn += xstream.toXML(paciente_detallesf);
						xmlReturn +="</sisvan>";
					}
				}
				
				if(registroGrupo.getGre_Tabla().equals("ADULTOS")){
					adultoF=repoAdultos.getAdulto(registroGrupo.getGre_Registro());
					pacientef=repoPacientes.findById(adultoF.getPac_Adu_Id());
					paciente_detallesf=repoPaciente_Detalle.findById(adultoF.getPac_Adu_Id());
					if((adultoF!=null)&&(pacientef!=null)&&(paciente_detallesf!=null)){
						xmlReturn =xmlInicial+"<table>Adultos</table><final>"+operationStr+"</final><operation>CREATE</operation>";
						xmlReturn += xstream.toXML(adultoF);
						xmlReturn += xstream.toXML(pacientef);
						xmlReturn += xstream.toXML(paciente_detallesf);
						xmlReturn +="</sisvan>";
					}
				}
				
				if(registroGrupo.getGre_Tabla().equals("GESTANTES")){
					gestantef=repoGestantes.getGestante(registroGrupo.getGre_Registro());
					pacientef=repoPacientes.findById(gestantef.getPac_Ges_Id());
					paciente_detallesf=repoPaciente_Detalle.findById(gestantef.getPac_Ges_Id());
					if((gestantef!=null)&&(pacientef!=null)&&(paciente_detallesf!=null)){
						xmlReturn =xmlInicial+"<table>Gestantes</table><final>"+operationStr+"</final><operation>CREATE</operation>";
						xmlReturn += xstream.toXML(gestantef);
						xmlReturn += xstream.toXML(pacientef);
						xmlReturn += xstream.toXML(paciente_detallesf);
						xmlReturn +="</sisvan>";
					}
				}
				System.out.println(xmlReturn);
				xmlReturn=Crypto.encrypt(xmlReturn);
				bw.write(xmlReturn);
				bw.write("\n");
			}
			bw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void cargaComponentes(){
		idGrupoT="0";
		repoGrpo_Exportacion=new Grupos_ExportacionRepository(session);
		repoPacientes=new PacientesRepository(session);
		repoPaciente_Detalle=new Paciente_DetallesRepository(session);
		repoMenores=new MenoresRepository(session);
		repoAdultos=new AdultosRepository(session);
		repoGestantes=new GestantesRepository(session);
		
		tableGrupos.getColumns().addAll(tablaColumn, registroColumn, fechaColumn, tipoColumn, idColumn, idGrupoColumn);
		tablaColumn.setCellValueFactory(new PropertyValueFactory<>("gre_Tabla"));
		registroColumn.setCellValueFactory(new PropertyValueFactory<>("gre_Registro"));
		fechaColumn.setCellValueFactory(new PropertyValueFactory<>("gre_Fecha_Registro"));
		tipoColumn.setCellValueFactory(new PropertyValueFactory<>("gre_Descripcion"));
		idColumn.setCellValueFactory(new PropertyValueFactory<>("gre_Id"));
		idGrupoColumn.setCellValueFactory(new PropertyValueFactory<>("gre_Grupo_Id"));
	}
	
	public void iniciaGrupoExportacion(){
		try{
			String IdGrupo=generaIDGrupo();
			idGrupoT=IdGrupo;
			session.clear();
			//Recopila informacion no sincorizada de Menores
			List listadoMenoresNoSync=repoMenores.selectNoSyncronizate();
			Iterator iterMenores = listadoMenoresNoSync.iterator();
			Menores menor=new Menores();
			while (iterMenores.hasNext()){
				Grupos_Exportacion grupoExpor=new Grupos_Exportacion();
				menor=(Menores) iterMenores.next();
				grupoExpor.setGre_Grupo_Id(IdGrupo);
				grupoExpor.setGre_Tabla("MENORES");
				grupoExpor.setGre_Registro(menor.getMnor_Id().toString());
				grupoExpor.setGre_Fecha_Registro(menor.getMnor_Fecha());
				grupoExpor.setGre_Estado("T");
				grupoExpor.setGre_Fecha(new Date());
				grupoExpor.setGre_Descripcion("Registro");
				repoGrpo_Exportacion.saveGrupo(grupoExpor);
			}
			
			//Recopila informacion no sincorizada de Gestantes
			List listadoGestantesNoSync=repoGestantes.selectNoSyncronizate();
			Iterator iterGestantes = listadoGestantesNoSync.iterator();
			Gestantes gestante=new Gestantes();
			while (iterGestantes.hasNext()){
				Grupos_Exportacion grupoExpor=new Grupos_Exportacion();
				gestante=(Gestantes) iterGestantes.next();
				grupoExpor.setGre_Grupo_Id(IdGrupo);
				grupoExpor.setGre_Tabla("GESTANTES");
				grupoExpor.setGre_Registro(gestante.getGes_Id().toString());
				grupoExpor.setGre_Fecha_Registro(gestante.getGes_Fecha());
				grupoExpor.setGre_Estado("T");
				grupoExpor.setGre_Fecha(new Date());
				grupoExpor.setGre_Descripcion("Registro");
				repoGrpo_Exportacion.saveGrupo(grupoExpor);
			}
			
			//Recopila informacion no sincorizada de Adultos
			List listadoAdultosNoSync=repoAdultos.selectNoSyncronizate();
			Iterator iterAdultos = listadoAdultosNoSync.iterator();
			Adultos adulto=new Adultos();
			while (iterAdultos.hasNext()){
				Grupos_Exportacion grupoExpor=new Grupos_Exportacion();
				adulto=(Adultos) iterAdultos.next();
				grupoExpor.setGre_Grupo_Id(IdGrupo);
				grupoExpor.setGre_Tabla("ADULTOS");
				grupoExpor.setGre_Registro(adulto.getAdu_Id().toString());
				grupoExpor.setGre_Fecha_Registro(adulto.getAdu_Fecha());
				grupoExpor.setGre_Estado("T");
				grupoExpor.setGre_Fecha(new Date());
				grupoExpor.setGre_Descripcion("Registro");
				repoGrpo_Exportacion.saveGrupo(grupoExpor);
			}
			
			cargaTablaGrupoExportacion(IdGrupo);
		}catch(Exception e){
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Exportar");
        	alert.setHeaderText("Exportar Informacion");
        	alert.setContentText("Error al Generar Archivo\n"+e.toString());
        	alert.showAndWait();
		}
	}
	
	private void cargaTablaGrupoExportacion(String IdGrupo) {
		tableGrupos.getItems().clear();
		List grupoExportacionList=repoGrpo_Exportacion.findByIdGrupo(IdGrupo);
		Iterator iter = grupoExportacionList.iterator();
		Grupos_Exportacion datoGrupo=new Grupos_Exportacion();
		while (iter.hasNext()){
			datoGrupo=(Grupos_Exportacion) iter.next();
			tableGrupos.getItems().add(datoGrupo);
		}
		
	}
	
	//Envia Registros al Cliente WS
		@SuppressWarnings("finally")
		public boolean enviarRegistros(String IdGrupoCargue){
			boolean okEnvio=false;
			String xmlReturn="";
			String upgd="";
			String usuario="";
			if(mainApp.getInstitucionApp()==null){
				upgd="000";
			}else{
				upgd=mainApp.getInstitucionApp().getIps_Id();
			}
			
			if(mainApp.getUsuarioApp()==null){
				usuario="Admin";
			}else{
				usuario=mainApp.getUsuarioApp().getUsu_Id();
			}
			
			String xmlInicial="<sisvan>";
			xmlInicial += "<usuario_id>"+usuario+"</usuario_id>";
			xmlInicial += "<upgd>"+upgd+"</upgd>";
			xmlInicial += "<grupo>"+IdGrupoCargue+"</grupo>";
			try{
				// 1. obtenemos la informacion del grupo a exportar
				List grupoAExportarListMenores=repoGrpo_Exportacion.findExportGrupo(IdGrupoCargue);
				Iterator<Grupos_Exportacion> iter = grupoAExportarListMenores.iterator();
				Grupos_Exportacion registroGrupo=new Grupos_Exportacion();
				
				Menores menorF=new Menores();
				Adultos adultoF=new Adultos();
				Gestantes gestantef=new Gestantes();
				Pacientes pacientef=new Pacientes();
				Paciente_Detalles paciente_detallesf=new Paciente_Detalles();
				
				 String defaultFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS";
				 String[] acceptableFormats = new String[]{"yyyyMM"};
				 DateConverter dateConverter = new DateConverter(defaultFormat, acceptableFormats );
				
				XStream xstream = new XStream(new DomDriver("UTF_8", new NoNameCoder()));
				xstream.autodetectAnnotations(true);
				xstream.registerConverter(dateConverter);
				
				int contador=0;
				int total=grupoAExportarListMenores.size();
				String operationStr="end";
				if(total>0){
					while (iter.hasNext()){
						registroGrupo=iter.next();
						contador++;
						if(contador==total){
							operationStr="end";
						}else{
							operationStr="";
						}
						
						if(registroGrupo.getGre_Tabla().equals("MENORES")){
							menorF=repoMenores.getMenor(registroGrupo.getGre_Registro());
							pacientef=repoPacientes.findById(menorF.getPac_Mnor_Id());
							paciente_detallesf=repoPaciente_Detalle.findById(menorF.getPac_Mnor_Id());
							if((menorF!=null)&&(pacientef!=null)&&(paciente_detallesf!=null)){
								xmlReturn =xmlInicial+"<table>Menores</table><final>"+operationStr+"</final><operation>CREATE</operation>";
								xmlReturn += xstream.toXML(menorF);
								xmlReturn += xstream.toXML(pacientef);
								xmlReturn += xstream.toXML(paciente_detallesf);
								xmlReturn +="</sisvan>";
							}
						}
						
						if(registroGrupo.getGre_Tabla().equals("ADULTOS")){
							adultoF=repoAdultos.getAdulto(registroGrupo.getGre_Registro());
							pacientef=repoPacientes.findById(adultoF.getPac_Adu_Id());
							paciente_detallesf=repoPaciente_Detalle.findById(adultoF.getPac_Adu_Id());
							if((adultoF!=null)&&(pacientef!=null)&&(paciente_detallesf!=null)){
								xmlReturn =xmlInicial+"<table>Adultos</table><final>"+operationStr+"</final><operation>CREATE</operation>";
								xmlReturn += xstream.toXML(adultoF);
								xmlReturn += xstream.toXML(pacientef);
								xmlReturn += xstream.toXML(paciente_detallesf);
								xmlReturn +="</sisvan>";
							}
						}
						
						if(registroGrupo.getGre_Tabla().equals("GESTANTES")){
							gestantef=repoGestantes.getGestante(registroGrupo.getGre_Registro());
							pacientef=repoPacientes.findById(gestantef.getPac_Ges_Id());
							paciente_detallesf=repoPaciente_Detalle.findById(gestantef.getPac_Ges_Id());
							if((gestantef!=null)&&(pacientef!=null)&&(paciente_detallesf!=null)){
								xmlReturn =xmlInicial+"<table>Gestantes</table><final>"+operationStr+"</final><operation>CREATE</operation>";
								xmlReturn += xstream.toXML(gestantef);
								xmlReturn += xstream.toXML(pacientef);
								xmlReturn += xstream.toXML(paciente_detallesf);
								xmlReturn +="</sisvan>";
							}
						}
						System.out.println(xmlReturn);
						xmlReturn=Base64.getEncoder().encodeToString(xmlReturn.getBytes("UTF-8"));
						System.out.println(xmlReturn);
						if(!enviarRegistro(usuario, upgd, IdGrupoCargue, xmlReturn)){
							okEnvio=false;
							break;
						}else{
							okEnvio=true;
						}
					}
				}else{
					okEnvio=true;
				}
			}catch(Exception e){
				this.cancelOper();
				e.printStackTrace();
				labelProgress.setText("Error en Sincronizacion");
				Alert alert = new Alert(AlertType.ERROR);
	        	alert.setTitle("Sincronizacion");
	        	alert.setHeaderText("Error de sincronizacion WEB");
	        	alert.setContentText(e.toString());
	        	alert.showAndWait();
			}finally{
				return okEnvio;
			}
		}
			
		public String generaIDGrupo(){
			Random rn = new Random();
			Calendar calendario = new GregorianCalendar();
			int year, dia, hora, minutos, segundos;
			
			int numer=rn.nextInt(1000 - 1 + 1) + 1;
			year=calendario.getMaximum(Calendar.WEEK_OF_YEAR);
			dia=calendario.getMaximum(Calendar.DAY_OF_YEAR);
			hora =calendario.get(Calendar.HOUR_OF_DAY);
			minutos = calendario.get(Calendar.MINUTE);
			segundos = calendario.get(Calendar.SECOND);
			
			String idGenerated=String.valueOf(year)+String.valueOf(dia)+String.valueOf(hora)+String.valueOf(minutos)+String.valueOf(segundos)+String.valueOf(numer);
			if(idGenerated.length()>=21){
				idGenerated=idGenerated.substring(0,20);
			}
			return idGenerated;
		}
		
		@SuppressWarnings("finally")
		private boolean enviarRegistro(String codUsr, String CodUpgd, String codGrp, String Base64Carga){
			boolean registroEnviado=false;
			try{
				String[] resultadoOperacion=new String[2];
				
				ConsumoCargaSisvan consumo=new ConsumoCargaSisvan();
				resultadoOperacion=consumo.cargaSisvanCliente(codUsr, CodUpgd, codGrp, Base64Carga);
				
				if(resultadoOperacion[0].equalsIgnoreCase("OK")){
					registroEnviado=true;
					if(actualizarCargue(codGrp)){
						elementosCargados++;
					}
				}else if(resultadoOperacion[0].equalsIgnoreCase("FAILWEB")){
					labelProgress.setText("Error en Sincronizacion");
					Alert alert = new Alert(AlertType.ERROR);
		        	alert.setTitle("Sincronizacion");
		        	alert.setHeaderText("Error de conexion");
		        	alert.setContentText("No hay comunicacion con el Servidor");
		        	alert.showAndWait();
				}else{
					registroEnviado=true;
					buf.append("\n"+resultadoOperacion[1]);
				}
				
			}catch(Exception es){
				es.printStackTrace();
				labelProgress.setText("Error en Sincronizacion");
				Alert alert = new Alert(AlertType.ERROR);
	        	alert.setTitle("Sincronizacion");
	        	alert.setHeaderText("Error de sincronizacion WEB");
	        	alert.setContentText(es.toString());
	        	alert.showAndWait();
			}finally{
				return registroEnviado;
			}
	}
		
	private void cuentaNoSincronizados() {
		long numMenoresNoSync=0;
		long numAdultosNoSync=0;
		long numGestantesNoSync=0;
		long totalNS=0;
		try{
			numMenoresNoSync=repoMenores.countNoSync();
			numAdultosNoSync=repoAdultos.countNoSync();
			numGestantesNoSync=repoGestantes.countNoSync();
			
			totalNS=numMenoresNoSync+numAdultosNoSync+numGestantesNoSync;
			totalNSField.setText(String.valueOf(totalNS));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void setMainApp(Main mainApp, Session session) {
		this.mainApp = mainApp;
		this.session=session;
		cargaComponentes();
		cuentaNoSincronizados();
    }
}
