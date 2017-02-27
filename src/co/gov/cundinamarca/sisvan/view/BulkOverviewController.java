package co.gov.cundinamarca.sisvan.view;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.formula.functions.Even;
import org.hibernate.Session;

import co.gov.cundinamarca.sisvan.Main;
import co.gov.cundinamarca.sisvan.model.parameters.Usuarios;
import co.gov.cundinamarca.sisvan.utilSiiweb.ExcelRead;
import co.gov.cundinamarca.sisvan.utilSiiweb.TextAreaAppender;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class BulkOverviewController {
	
	private Main mainApp;
	private Session session;
	private File file;
	private StringBuilder buf;
	private Stage dialogStage;
	ExcelRead leerExcel;
	
	@FXML
	private Button btnAceptar;

	@FXML
	private Label lblResult;

	@FXML
	private Button btnCancelar;

	@FXML
	private ProgressBar pbProgress;
	
	@FXML
	private TextArea loggingView;
	
	Task copyWorker;

	@FXML
	private void initialize() {
		lblResult.setText("Seleccione archivo...");
		TextAreaAppender.setTextArea(loggingView);
		leerExcel=new ExcelRead();
		pbProgress.setVisible(false);
	}
	
	@FXML
	private void handleOpen() {
		FileChooser fileChooser = new FileChooser();

		// Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Arcvhivos XLSX(*.xlsx)", "*.xlsx");
		FileChooser.ExtensionFilter extFilter2 = new FileChooser.ExtensionFilter("Archivos XLS(*.xls)", "*.xls");
		fileChooser.getExtensionFilters().add(extFilter);
		fileChooser.getExtensionFilters().add(extFilter2);

		// Show save file dialog
		file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

		if (file != null && file.exists()) {
			buf = new StringBuilder();
			try {
				setupLogginView();
				iniciaLectura();
			} catch (Exception e) {
				buf.append(e.getMessage());
			}
		}
	}

	void iniciaLectura() {
		pbProgress.setVisible(true);
		lblResult.setText("Procesando archivo...");
		loggingView.appendText("Inicia lectura de archivo...");
		copyWorker = createWorker();
		
		pbProgress.progressProperty().unbind();
		pbProgress.setProgress(ProgressBar.INDETERMINATE_PROGRESS);
		btnAceptar.setDisable(true);
        
		copyWorker = createWorker();
		copyWorker.messageProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println(newValue);
                if(newValue.equals("9")){
                	copyWorker.cancel(true);
                	muestraResultados();
            		dialogStage.close();
                }
            }
        });
        
        new Thread(copyWorker).start();
        
	}

	@FXML
	void handleCancel() {
		this.buf.append("Se ha cancelado la operación!");
		if(copyWorker!=null){
			copyWorker.cancel(true);
			lblResult.setText("Operacion Cancelada");
	        System.out.println("cancelled.");
		}
		muestraResultados();
		dialogStage.close();
	}
	
	public Task createWorker() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(1000);
                    if(i==1){
                    	System.out.println("Entra Variables");
                    	leerExcel.cargarVariables(session, loggingView);
                    }else if(i==3){
                    	try {
                    		System.out.println("Entra Menores");
                			leerExcel.cargarMenores(file, buf, session, mainApp.getUsuarioApp(), loggingView);
                		} catch (InvalidFormatException | IOException e) {
                			// TODO Auto-generated catch block
                			e.printStackTrace();
                		}
                    }
                    else if(i==5){
                    	try {
                    		System.out.println("Entra Gestantes");
                			leerExcel.cargarGestates(file, buf, session, mainApp.getUsuarioApp(), loggingView);
                		} catch (InvalidFormatException | IOException e) {
                			// TODO Auto-generated catch block
                			e.printStackTrace();
                		}
                    }
                    else if(i==7){
                    	try {
                    		System.out.println("Entra Adultos");
                			leerExcel.cargarAdultos(file, buf, session, mainApp.getUsuarioApp(), loggingView);
                		} catch (InvalidFormatException | IOException e) {
                			// TODO Auto-generated catch block
                			e.printStackTrace();
                		}
                    }
                    else if(i==8){
                    	loggingView.appendText("\nFinalizando Proceso");
                		
                    }
                    
                    else if(i==9){
                    	loggingView.appendText("\nCarga Finalizada");
                		
                    }
                    updateMessage(String.valueOf(i));
                    updateProgress(i + 1, 10);
                }
                return true;
            }
        };
    }
	
	private void muestraResultados(){
		Alert alert = new Alert("".equals(buf.toString()) ? AlertType.INFORMATION : AlertType.WARNING);
		alert.setTitle("Cargue Archivo");
		alert.setHeaderText("Cargue de Información por Archivo");
		String mensaje = "".equals(buf.toString()) ? "Se ha cargado satisfactoriamente el archivo"
				: String.format("%1$s%2$s", System.getProperty("line.separator"), buf.toString());
		
		// Create expandable Exception.
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		String exceptionText = sw.toString();

		Label label = new Label("Errores en Archivo:");

		TextArea textArea = new TextArea(mensaje);
		textArea.setEditable(false);
		textArea.setWrapText(true);

		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);

		GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(label, 0, 0);
		expContent.add(textArea, 0, 1);

		// Set expandable Exception into the dialog pane.
		alert.getDialogPane().setExpandableContent(expContent);
		alert.showAndWait();
	}
	
	private void setupLogginView() {
        loggingView.setWrapText(true);
        loggingView.appendText("Archivo: ");
        loggingView.appendText(file.getPath());
        loggingView.appendText("\n");
        loggingView.setEditable(false);
    }
	
	public void setMainApp(Main mainApp, Session session) {
		this.mainApp = mainApp;
		this.session=session;
    }
	
	public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

}
