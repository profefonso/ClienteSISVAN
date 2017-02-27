package co.gov.cundinamarca.sisvan.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.hibernate.Session;

import co.com.siiweb.sisvancrypto.Crypto;
import co.gov.cundinamarca.sisvan.Main;
import co.gov.cundinamarca.sisvan.model.parameters.Grupos_Exportacion;
import co.gov.cundinamarca.sisvan.model.parameters.Institucion_Usuarios;
import co.gov.cundinamarca.sisvan.model.parameters.Instituciones;
import co.gov.cundinamarca.sisvan.model.parameters.Paciente_Detalles;
import co.gov.cundinamarca.sisvan.model.parameters.Pacientes;
import co.gov.cundinamarca.sisvan.model.transactions.Adultos;
import co.gov.cundinamarca.sisvan.model.transactions.Gestantes;
import co.gov.cundinamarca.sisvan.model.transactions.Menores;
import co.gov.cundinamarca.sisvan.repository.AdultosRepository;
import co.gov.cundinamarca.sisvan.repository.GestantesRepository;
import co.gov.cundinamarca.sisvan.repository.Grupos_ExportacionRepository;
import co.gov.cundinamarca.sisvan.repository.Institucion_UsuariosRepository;
import co.gov.cundinamarca.sisvan.repository.InstitucionesRepository;
import co.gov.cundinamarca.sisvan.repository.MenoresRepository;
import co.gov.cundinamarca.sisvan.repository.Paciente_DetallesRepository;
import co.gov.cundinamarca.sisvan.repository.PacientesRepository;
import co.gov.cundinamarca.sisvan.utilSiiweb.ExcelRead;
import co.gov.cundinamarca.sisvan.utilSiiweb.Utilities;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class DashBoardOverviewController {

	private Main mainApp;
	Session session;

	InstitucionesRepository repoInstituciones;
	Institucion_UsuariosRepository repoInstitucion_usuario;
	Grupos_ExportacionRepository repoGrpo_Exportacion;
	PacientesRepository repoPacientes;
	Paciente_DetallesRepository repoPaciente_Detalle;
	MenoresRepository repoMenores;
	AdultosRepository repoAdultos;
	GestantesRepository repoGestantes;

	ObservableList<Instituciones> institucionesList = FXCollections.observableArrayList();

	@FXML
	private ComboBox<Instituciones> institucionesBox;

	public DashBoardOverviewController() {
	}

	@FXML
	private void initialize() {

	}

	@FXML
	private void handleIraMenores() {
		mainApp.pacientesOverview("Menores");
	}

	@FXML
	private void handleIraAdultos() {
		mainApp.pacientesOverview("Adultos");
	}

	@FXML
	private void handleIraGestantes() {
		mainApp.pacientesOverview("Gestantes");
	}

	@FXML
	private void handleIraExporttar() {
		mainApp.exportarTransaccionesOverview();
	}

	@FXML
	private void handleIraSincronizar() {
		mainApp.showSincronizarAppOverview(false);
	}
	
	@FXML
	private void handleIraBulk() {
		mainApp.showBulkOverview();
	}

	private void cargaComonentes() {
		// Inicializa el Combo Instituciones
		repoInstituciones = new InstitucionesRepository(session);
		repoInstitucion_usuario = new Institucion_UsuariosRepository(session);
		List upgdsList = null;
		if(mainApp.getUsuarioApp().getPer_Usu_Id().equalsIgnoreCase("Alcaldia")){
			upgdsList = repoInstituciones.listByMunicipio(mainApp.getUsuarioApp().getMun_Usu_Id());
			Instituciones instiL=new Instituciones();
			Iterator iter = upgdsList.iterator();
			while (iter.hasNext()) {
				instiL=(Instituciones) iter.next();
				institucionesList.add(instiL);
			}
		}else{
			Institucion_Usuarios institucion_usuario = new Institucion_Usuarios();
			upgdsList = repoInstitucion_usuario.getListIpsByUsuario(mainApp.getUsuarioApp().getUsu_Id());
			Iterator iter = upgdsList.iterator();
			while (iter.hasNext()) {
				institucion_usuario = (Institucion_Usuarios) iter.next();
				institucionesList.add(repoInstituciones.findById(institucion_usuario.getIps_Ipsu_Id()));
			}
		}
		
		institucionesBox.setItems(institucionesList);
		institucionesBox.setValue(mainApp.getInstitucionApp());

		institucionesBox.valueProperty().addListener(new ChangeListener<Instituciones>() {
			@Override
			public void changed(ObservableValue<? extends Instituciones> observable, Instituciones oldValue,
					Instituciones newValue) {
				mainApp.setInstitucionApp(newValue);
			}
		});
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(Main mainApp, Session session) {
		this.mainApp = mainApp;
		this.session = session;
		cargaComonentes();
	}

}
