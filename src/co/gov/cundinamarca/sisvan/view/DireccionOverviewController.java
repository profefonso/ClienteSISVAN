package co.gov.cundinamarca.sisvan.view;

import java.sql.Connection;

import org.hibernate.Session;

import co.gov.cundinamarca.sisvan.Main;
import co.gov.cundinamarca.sisvan.model.parameters.SelectSiNo;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DireccionOverviewController {
	
	private Main mainApp;
	private Session session;
	private String dirFull;
	
	@FXML
	private ComboBox<String> comboUnoBox;
	@FXML
    private TextField textUnoField;
	@FXML
	private ComboBox<String> comboDosBox;
	@FXML
	private ComboBox<String> comboTresBox;
	@FXML
	private ComboBox<String> comboCuatroBox;
	@FXML
	private ComboBox<String> comboCincoBox;
    @FXML
    private TextField textDosField;
    @FXML
	private ComboBox<String> comboSeisBox;
    @FXML
    private TextField textTresField;
    @FXML
	private ComboBox<String> comboSieteBox;
    @FXML
	private ComboBox<String> comboOchoBox;
    @FXML
    private TextField textCuatroField;
    
    ObservableList<String> dirUno=FXCollections.observableArrayList();
    ObservableList<String> letras=FXCollections.observableArrayList();
    ObservableList<String> bis=FXCollections.observableArrayList();
    ObservableList<String> ubicacion=FXCollections.observableArrayList();
    ObservableList<String> complemento=FXCollections.observableArrayList();
    
    private Stage dialogStage;
    private boolean okLogin = false;
	
    @FXML
    private void initialize() {
    }
    
    @FXML
    private void handleAceptar() {
    	try{
    		addForm(comboUnoBox.getValue());
    		addForm(textUnoField.getText());
    		addForm(comboDosBox.getValue());
    		addForm(comboTresBox.getValue());
    		addForm(comboCuatroBox.getValue());
    		addForm(comboCincoBox.getValue());
    		addForm("#");
    		addForm(textDosField.getText());
    		addForm(comboSeisBox.getValue());
    		addForm("-");
    		addForm(textTresField.getText());
    		addForm(comboSieteBox.getValue());
    		addForm(comboOchoBox.getValue());
    		addForm(textCuatroField.getText());
    	}catch(Exception e){
    		e.printStackTrace();
    		this.dirFull="null";
    	}
    	this.dialogStage.close();
    }
    
    @FXML
    private void handleCancelar() {
    	this.dirFull="null";
    	this.dialogStage.close();
    }
    
    public void addForm(String text){
    	try{
    		if(text.equals(null)||text.equals("")){
        		
        	}else{
        		if(this.dirFull.equals("null")){
        			if(text.equals("#")){
        				this.dirFull="null";
        			}else if(text.equals("-")){
        				this.dirFull="null";
        			}else{
        				this.dirFull=text;
        			}
        			
        		}else{
        			this.dirFull=this.dirFull+" "+text;
        		}
        	}
    	}
    	catch(NullPointerException e){
    		
    	}
    }
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public String getDirFull() {
        return dirFull;
    }
    
    public void cargaComponentes(){
    	this.dirFull="null";
    	dirUno.add("");
    	dirUno.add("Avenida calle");
    	dirUno.add("Avenida carrera");
    	dirUno.add("Calle");
    	dirUno.add("Carrera");
    	dirUno.add("Diagonal");
    	dirUno.add("Transversal");
    	letras.add("");
    	letras.add("A");
    	letras.add("B");
    	letras.add("C");
    	letras.add("D");
    	letras.add("E");
    	letras.add("F");
    	letras.add("G");
    	letras.add("H");
    	letras.add("I");
    	letras.add("J");
    	letras.add("K");
    	letras.add("L");
    	letras.add("M");
    	letras.add("N");
    	letras.add("O");
    	letras.add("P");
    	letras.add("Q");
    	letras.add("R");
    	letras.add("S");
    	letras.add("T");
    	letras.add("U");
    	letras.add("V");
    	letras.add("W");
    	letras.add("X");
    	letras.add("Y");
    	letras.add("Z");
    	bis.add("");
    	bis.add("Bis");
    	ubicacion.add("");
    	ubicacion.add("Norte");
    	ubicacion.add("Sur");
    	ubicacion.add("Este");
    	ubicacion.add("Oeste");
    	complemento.add("");
    	complemento.add("Agrupación");
    	complemento.add("Apartamento");
    	complemento.add("Bloque");
    	complemento.add("Bodega");
    	complemento.add("Camino");
    	complemento.add("Carretera");
    	complemento.add("Casa");
    	complemento.add("Conjunto");
    	complemento.add("Consultorio");
    	complemento.add("Célula");
    	complemento.add("Depósito");
    	complemento.add("Edificio");
    	complemento.add("Entrada");
    	complemento.add("Esquina");
    	complemento.add("Etapa");
    	complemento.add("Garaje");
    	complemento.add("Interior");
    	complemento.add("Kilómetro");
    	complemento.add("Local");
    	complemento.add("Lote");
    	complemento.add("Manzana");
    	complemento.add("Mezanine");
    	complemento.add("Módulo");
    	complemento.add("Oficina");
    	complemento.add("Parcela");
    	complemento.add("Paseo");
    	complemento.add("Penthouse");
    	complemento.add("Piso");
    	complemento.add("Predio");
    	complemento.add("Puente");
    	complemento.add("Sector");
    	complemento.add("Semisótano");
    	complemento.add("Solar");
    	complemento.add("Supermanzana");
    	complemento.add("Torre");
    	complemento.add("Unidad");
    	complemento.add("Unidad residencial");
    	complemento.add("Urbanización y Zona");
    	complemento.add("Salóncomunal");
    	complemento.add("Vereda");
    	
    	comboUnoBox.setItems(dirUno);
    	comboDosBox.setItems(letras);
    	comboTresBox.setItems(bis);
    	comboCuatroBox.setItems(letras);
    	comboCincoBox.setItems(ubicacion);
    	comboSeisBox.setItems(letras);
    	comboSieteBox.setItems(ubicacion);
    	comboOchoBox.setItems(complemento);
    	addTextLimiter(textUnoField,3);
    	addTextLimiter(textDosField,3);
    	addTextLimiter(textTresField,3);
    	addTextLimiter(textCuatroField,50);
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
       
    public void setMainApp(Main mainApp, Session session) {
		this.mainApp = mainApp;
		this.session=session;
		cargaComponentes();
    }
}
