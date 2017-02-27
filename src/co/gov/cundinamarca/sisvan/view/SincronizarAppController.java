package co.gov.cundinamarca.sisvan.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.DomDriver;

import co.com.siiweb.clientes.ConsumoActualizarVersion;
import co.com.siiweb.clientes.ConsumoCargaSisvan;
import co.com.siiweb.sisvancrypto.Crypto;
import co.gov.cundinamarca.sisvan.Main;
import co.gov.cundinamarca.sisvan.model.parameters.Grupos_Exportacion;
import co.gov.cundinamarca.sisvan.model.parameters.Paciente_Detalles;
import co.gov.cundinamarca.sisvan.model.parameters.Pacientes;
import co.gov.cundinamarca.sisvan.model.parameters.Sincronizacion;
import co.gov.cundinamarca.sisvan.model.parameters.Versiones;
import co.gov.cundinamarca.sisvan.model.transactions.Adultos;
import co.gov.cundinamarca.sisvan.model.transactions.Gestantes;
import co.gov.cundinamarca.sisvan.model.transactions.Menores;
import co.gov.cundinamarca.sisvan.repository.AdultosRepository;
import co.gov.cundinamarca.sisvan.repository.GestantesRepository;
import co.gov.cundinamarca.sisvan.repository.Grupos_ExportacionRepository;
import co.gov.cundinamarca.sisvan.repository.MenoresRepository;
import co.gov.cundinamarca.sisvan.repository.Paciente_DetallesRepository;
import co.gov.cundinamarca.sisvan.repository.PacientesRepository;
import co.gov.cundinamarca.sisvan.repository.VersionesRepository;
import co.gov.cundinamarca.tiposproyecto.sec_salud.sisvan.actualizarversion_ps.ItemTipo;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SincronizarAppController {
	private Main mainApp;
	private Session session;
	private Stage dialogStage;
	
	boolean okActualizacionParametros=false;
	
	private Sincronizacion sincronizacion;
	private Versiones versiones;
	private VersionesRepository repoVersiones;
	
	@SuppressWarnings("rawtypes")
	Task copyWorker;
	
	@FXML
	ProgressBar progressBar;
	@FXML
	Label labelProgress;
	
	@FXML
	Button butonSincronizarWeb;
	@FXML
	Button butonSincronizarArchivo;
	
	 @FXML
	 private void initialize() {
		 labelProgress.setText("Listo para Sincronización");
	 }
	 
	 public void setDialogStage(Stage dialogStage) {
		 this.dialogStage = dialogStage;
	 }

	@FXML
	private void handleCancelar() {
		if(copyWorker!=null){
			copyWorker.cancel(true);
	        progressBar.progressProperty().unbind();
	        progressBar.setProgress(0);
	        System.out.println("cancelled.");
		}
		dialogStage.close();
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
	
	@FXML
	private void handleIniciarSincronizacionWEB() {
		butonSincronizarWeb.setDisable(true);
		butonSincronizarArchivo.setDisable(true);
		copyWorker = createWorker();
		okActualizacionParametros=false;
        progressBar.progressProperty().unbind();
        progressBar.setProgress(ProgressBar.INDETERMINATE_PROGRESS);
        progressBar.progressProperty().bind(copyWorker.progressProperty());
        
        copyWorker.messageProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println(newValue);
                switch (newValue) {
				case "0":
					labelProgress.setText("Sincronizando.");
					break;
				case "1":
					labelProgress.setText("Sincronizando..");
					break;
				case "2":
					labelProgress.setText("Estableciendo comunicación...");
					break;
				case "3":
					labelProgress.setText("Recibiendo actualización de parametros...");
					break;
				case "4":
					break;
				case "5":
					//Inicia envio de datos cargados al servidor
					okActualizacionParametros=iniciaActualizacionParametros();
					break;
				case "6":
					labelProgress.setText("Finalizando actualización de parametros...");
					break;
				case "7":
					
					break;
				case "8":
					labelProgress.setText("Sincronizacion Finalizada");
					break;
				case "9":
					finalizaSincronizacion();
					butonSincronizarWeb.setDisable(false);
					butonSincronizarArchivo.setDisable(false);
					break;
				default:
					break;
				}
            }
        });
        
        new Thread(copyWorker).start();
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
	
	@FXML
	private void handleOpenArchivo() {
		 FileChooser fileChooser = new FileChooser();
		 // Set extension filter
	     FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("SVN files (*.svn)", "*.svn");
	     fileChooser.getExtensionFilters().add(extFilter);
	     // Show save file dialog
	     File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
	     if (file != null) {
	    	 progressBar.progressProperty().unbind();
	         progressBar.setProgress(ProgressBar.INDETERMINATE_PROGRESS);
	    	 labelProgress.setText("Leyendo Archivo...");
	    	 if(leeArchivo(file)){
	    		 Alert alert = new Alert(AlertType.CONFIRMATION);
		    	 alert.setTitle("Sincronizacion Manual");
		         alert.setHeaderText("Lectura de Parametros");
		         alert.setContentText("Archivo Cargado Exitosamente!");
		         alert.showAndWait();
	    	 }else{
	    		 Alert alert = new Alert(AlertType.ERROR);
		    	 alert.setTitle("Sincronizacion Manual");
		         alert.setHeaderText("Lectura de Parametros");
		         alert.setContentText("Error Cargando Archivo!");
		         alert.showAndWait();
	    	 }
	    	 labelProgress.setText("Carga finalizada...");
		     progressBar.setProgress(100);
	     }
	}
	
	@SuppressWarnings("finally")
	private boolean iniciaActualizacionParametros() {
		// metodo para recibir los parametros actualizados
		boolean okParametros=false;
		try{
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
			String[] resultadoOperacion=new String[2];
			
			versiones=repoVersiones.lastVersion();
			System.out.println("Version Actual: "+versiones.getVer_Codigo());
			
			ConsumoActualizarVersion consumo=new ConsumoActualizarVersion();
			resultadoOperacion=consumo.actualizarVersionSISVAN(usuario, upgd, versiones.getVer_Codigo());
			
			if(resultadoOperacion[0].equalsIgnoreCase("OK")){
				sincronizacion=new Sincronizacion();
				sincronizacion.setMessage(resultadoOperacion[1]);
				List<String> sentenciasArray = new ArrayList<String>();
				for (ItemTipo item : consumo.getArrayVersion().getItem()) {
					System.out.println(item.getVersionVers());
					System.out.println(item.getSentenciasSent());
					sentenciasArray.add(item.getVersionVers()+"~"+item.getSentenciasSent());
					sincronizacion.setVersion(item.getVersionVers().toString());
				}
				sincronizacion.setSentenciasSQL(sentenciasArray);
				if(exuteSentencias()){
					okParametros=true;
				}else{
					this.cancelOper();
					labelProgress.setText("Error en Sincronizacion");
					Alert alert = new Alert(AlertType.ERROR);
		        	alert.setTitle("Sincronizacion");
		        	alert.setHeaderText("Error de parametros");
		        	alert.setContentText("No se pudieron Actualizar Parametros");
		        	alert.showAndWait();
				}
			}else if(resultadoOperacion[0].equalsIgnoreCase("FAILWEB")){
				this.cancelOper();
				labelProgress.setText("Error en Sincronizacion");
				Alert alert = new Alert(AlertType.ERROR);
	        	alert.setTitle("Sincronizacion");
	        	alert.setHeaderText("Error de conexion");
	        	alert.setContentText("No hay comunicacion con el Servidor");
	        	alert.showAndWait();
			}else{
				this.cancelOper();
				labelProgress.setText("Sincronizacion de parametros");
				Alert alert = new Alert(AlertType.WARNING);
	        	alert.setTitle("Sincronizacion");
	        	alert.setHeaderText("Sincronizacion WEB");
	        	alert.setContentText(resultadoOperacion[1]);
	        	alert.showAndWait();
			}
			
		}catch(Exception es){
			this.cancelOper();
			es.printStackTrace();
			labelProgress.setText("Error en Sincronizacion");
			Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Sincronizacion");
        	alert.setHeaderText("Error de sincronizacion WEB");
        	alert.setContentText(es.toString());
        	alert.showAndWait();
		}finally{
			return okParametros;
		}
	}
	
	private boolean leeArchivo(File file) {
		String cadena;
		boolean okCarga=false;
	    try {
	    	BufferedReader b = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			while((cadena = b.readLine())!=null) {
				System.out.println(cadena);
				cadena=Crypto.decrypt(cadena);
				final Gson gson = new Gson();
				sincronizacion = gson.fromJson(cadena, Sincronizacion.class);
				if(sincronizacion.getMessage().equalsIgnoreCase("Ok")){
					versiones=repoVersiones.lastVersion();
					System.out.println("Version Actual: "+versiones.getVer_Codigo());
					if(Long.valueOf(versiones.getVer_Codigo())<Long.valueOf(sincronizacion.getVersion().toString())){
						if(exuteSentencias()){
							okCarga=true;
						}
					}else{
						Alert alert = new Alert(AlertType.ERROR);
				    	alert.setTitle("Sincronizacion Manual");	
				        alert.setHeaderText("Lectura de Parametros");
				        alert.setContentText("Error: Version de Archivo no Actualizada");
				        alert.showAndWait();
					}
				}else{
					Alert alert = new Alert(AlertType.ERROR);
			    	 alert.setTitle("Sincronizacion Manual");
			         alert.setHeaderText("Lectura de Parametros");
			         alert.setContentText("Error: "+sincronizacion.getMessage());
			         alert.showAndWait();
				}
			}
			b.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    catch(Exception ef){
	    	ef.printStackTrace();
	    }
		return okCarga;
	}
	
	@SuppressWarnings("finally")
	public boolean exuteSentencias(){
		boolean okEjectSQL=false;
        try{
        	session.beginTransaction();
            session.doWork(new Work(){
              @Override
              public void execute(Connection connection) throws SQLException {
            	  connection.setAutoCommit(false);
  	        	  Statement statement = connection.createStatement();
  	        	  String[] sentenciaComoArray=new String[2];
  	        	  for (String iterable_element : sincronizacion.getSentenciasSQL()) {
  	        		System.out.println(iterable_element);
  	        		sentenciaComoArray=null;
  	        		sentenciaComoArray = iterable_element.split("~");
  	        		try{
  	        			if(sentenciaComoArray[1]!=null){
  	        				if(sentenciaComoArray[0]!=null){
  	  	        				if(Long.valueOf(sentenciaComoArray[0])>Long.valueOf(versiones.getVer_Codigo().toString())){
  	  	        					System.out.println("Sentencia: "+sentenciaComoArray[1]);
  	  	        					statement.addBatch(sentenciaComoArray[1]);
  	  	        				}
  	  	        			}
  	        			}
  	        		}catch(ArrayIndexOutOfBoundsException e){
  	        			e.printStackTrace();
  	        		}
  	        		catch(NullPointerException ne){
  	        			ne.printStackTrace();
  	        		}catch(Exception e){
  	        			e.printStackTrace();
  	        		}
  	        	  }
  	        	  statement.addBatch("INSERT INTO VERSIONES(VER_CODIGO, VER_ESTADO) VALUES('"+sincronizacion.getVersion()+"', 'A')");
  	        	  statement.executeBatch();
  	        	  connection.commit();
  	        	  statement.close();
                }
            });
            session.getTransaction().commit();
            session.flush();
            session.clear();
            okEjectSQL=true;
        }catch(HibernateException e){
            e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
	    	alert.setTitle("Sincronizacion Manual");
	        alert.setHeaderText("Insercion de Parametros");
	        alert.setContentText("Error: "+e.toString());
	        alert.showAndWait();
        }catch(Exception e){
            e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
	    	alert.setTitle("Sincronizacion Manual");
	        alert.setHeaderText("insercion de Parametros");
	        alert.setContentText("Error: "+e.toString());
	        alert.showAndWait();
        }finally{
        	return okEjectSQL;
        }
 
    } 
	
	private void finalizaSincronizacion() {
		try
        {
			Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setTitle("Sincronizacion");
        	alert.setHeaderText("Finalizada");
        	alert.setContentText("Sincronización Finalizada Exitosamente");

        	alert.showAndWait();
            
        }catch(Exception e){
        	e.printStackTrace();
        }
	}
	
	private void cargaComponentes(){
		sincronizacion=new Sincronizacion();
		versiones=new Versiones();
		repoVersiones=new VersionesRepository(session);
	}
		
	public void setMainApp(Main mainApp, Session session, boolean sinLogin) {
		this.mainApp = mainApp;
		this.session=session;
		cargaComponentes();
    }
}
