package co.gov.cundinamarca.sisvan;

import java.io.IOException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import co.gov.cundinamarca.sisvan.model.parameters.Instituciones;
import co.gov.cundinamarca.sisvan.model.parameters.Paciente_Detalles;
import co.gov.cundinamarca.sisvan.model.parameters.Pacientes;
import co.gov.cundinamarca.sisvan.model.parameters.Usuarios;
import co.gov.cundinamarca.sisvan.model.transactions.Adultos;
import co.gov.cundinamarca.sisvan.model.transactions.Gestantes;
import co.gov.cundinamarca.sisvan.model.transactions.Menores;
import co.gov.cundinamarca.sisvan.view.AdultosOverviewController;
import co.gov.cundinamarca.sisvan.view.BulkOverviewController;
import co.gov.cundinamarca.sisvan.view.CambiarPasswordOverviewController;
import co.gov.cundinamarca.sisvan.view.DashBoardOverviewController;
import co.gov.cundinamarca.sisvan.view.DireccionOverviewController;
import co.gov.cundinamarca.sisvan.view.ExportarTransaccionesOverviewController;
import co.gov.cundinamarca.sisvan.view.GestantesOverviewController;
import co.gov.cundinamarca.sisvan.view.GraphicsOverviewController;
import co.gov.cundinamarca.sisvan.view.HistoricoGraficasGestantesController;
import co.gov.cundinamarca.sisvan.view.HistoricoGraficasMenoresController;
import co.gov.cundinamarca.sisvan.view.IpsFindListOverviewController;
import co.gov.cundinamarca.sisvan.view.LoginOverviewController;
import co.gov.cundinamarca.sisvan.view.MenoresOverviewController;
import co.gov.cundinamarca.sisvan.view.PacientesFindListOverviewController;
import co.gov.cundinamarca.sisvan.view.PacientesOverviewController;
import co.gov.cundinamarca.sisvan.view.RootLayoutController;
import co.gov.cundinamarca.sisvan.view.SincronizarAppController;
import co.gov.cundinamarca.sisvan.view.UsuariosOverviewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {
	
	private Usuarios usuarioApp;
	private Instituciones institucionApp;
	private Pacientes paciente;
	private Paciente_Detalles paciente_detalle;
	private Date fechaConsulta;
	
	private Stage primaryStage;
    private BorderPane rootLayout; 
    Session session;
    
    public Main(){
    	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	    session = sessionFactory.openSession();
	    paciente=new Pacientes();
	    paciente_detalle=new Paciente_Detalles();
    }
    

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("MANGO");
        this.primaryStage.getIcons().add(new Image("file:resources/images/lok.png"));

        initRootLayout();

        if(showLoginOverview()){
        	dashBoardOverview();
        }else{
        	System.exit(0);
        }
	}
	
	public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            
            // Give the controller access to the main app.
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);
            
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void showUsuariosOverview() {
		 try {
			 FXMLLoader loader = new FXMLLoader();
	         loader.setLocation(Main.class.getResource("view/UsuariosOverview.fxml"));
	         AnchorPane usuariosOverview = (AnchorPane) loader.load();

	         // Set person overview into the center of root layout.
	         rootLayout.setCenter(usuariosOverview);

	         // Give the controller access to the main app.
	         UsuariosOverviewController controller = loader.getController();
	         controller.setMainApp(this, session);
	         
	     } catch (IOException e) {
	    	 e.printStackTrace();
	    }
	}
	
	public void dashBoardOverview() {
		 try {
			 FXMLLoader loader = new FXMLLoader();
	         loader.setLocation(Main.class.getResource("view/DashBoardOverview.fxml"));
	         AnchorPane dashboardOverview = (AnchorPane) loader.load();

	         // Set person overview into the center of root layout.
	         rootLayout.setCenter(dashboardOverview);
	         
	         DashBoardOverviewController controller = loader.getController();
	         controller.setMainApp(this, session);
	         
	     } catch (IOException e) {
	    	 e.printStackTrace();
	    }
	}
	
	public void menoresOverview(Menores menor) {
		 try {
			 FXMLLoader loader = new FXMLLoader();
	         loader.setLocation(Main.class.getResource("view/MenoresOverview.fxml"));
	         AnchorPane menoresOverview = (AnchorPane) loader.load();

	         // Set person overview into the center of root layout.
	         rootLayout.setCenter(menoresOverview);
	          MenoresOverviewController controller = loader.getController();
	          controller.setMainApp(this, session, paciente, paciente_detalle, menor, fechaConsulta);
	     } catch (IOException e) {
	    	 e.printStackTrace();
	    }
	}
	
	public void adultosOverview(Adultos adulto) {
		 try {
			 FXMLLoader loader = new FXMLLoader();
	         loader.setLocation(Main.class.getResource("view/AdultosOverview.fxml"));
	         AnchorPane adultosOverview = (AnchorPane) loader.load();

	         // Set person overview into the center of root layout.
	         rootLayout.setCenter(adultosOverview);
	          AdultosOverviewController controller = loader.getController();
	          controller.setMainApp(this, session, paciente, paciente_detalle, adulto, fechaConsulta);
	     } catch (IOException e) {
	    	 e.printStackTrace();
	    }
	}
	
	public void gestantesOverview(Gestantes gestante) {
		 try {
			 FXMLLoader loader = new FXMLLoader();
	         loader.setLocation(Main.class.getResource("view/GestantesOverview.fxml"));
	         AnchorPane gestantesOverview = (AnchorPane) loader.load();

	         // Set person overview into the center of root layout.
	         rootLayout.setCenter(gestantesOverview);
	          GestantesOverviewController controller = loader.getController();
	          controller.setMainApp(this, session, paciente, paciente_detalle, gestante, fechaConsulta);
	     } catch (IOException e) {
	    	 e.printStackTrace();
	    }
	}
	
	public void pacientesOverview(String formularioSig) {
		 try {
			 FXMLLoader loader = new FXMLLoader();
	         loader.setLocation(Main.class.getResource("view/PacientesOverview.fxml"));
	         AnchorPane pacientesOverview = (AnchorPane) loader.load();

	         // Set person overview into the center of root layout.
	         rootLayout.setCenter(pacientesOverview);
	         PacientesOverviewController controller = loader.getController();
	         controller.setMainApp(this, session, formularioSig);
	     } catch (IOException e) {
	    	 e.printStackTrace();
	    }
	}
	
	public void exportarTransaccionesOverview() {
		 try {
			 FXMLLoader loader = new FXMLLoader();
	         loader.setLocation(Main.class.getResource("view/ExportarTransaccionesOverview.fxml"));
	         AnchorPane exportarTransaccionessOverview = (AnchorPane) loader.load();

	         // Set person overview into the center of root layout.
	         rootLayout.setCenter(exportarTransaccionessOverview);
	         ExportarTransaccionesOverviewController controller = loader.getController();
	         controller.setMainApp(this, session);
	     } catch (IOException e) {
	    	 e.printStackTrace();
	    }
	}
	 
	public boolean showLoginOverview() {
		try {
			FXMLLoader loader = new FXMLLoader();
		    loader.setLocation(Main.class.getResource("view/LoginOverview.fxml"));
		    AnchorPane page = (AnchorPane) loader.load();

		    Stage dialogStage = new Stage();
		    dialogStage.setTitle("MANGO - INGRESO");
		    dialogStage.getIcons().add(new Image("file:resources/images/lok.png"));
		    dialogStage.initModality(Modality.WINDOW_MODAL);
		    dialogStage.initOwner(primaryStage);
		    Scene scene = new Scene(page);
		    dialogStage.setScene(scene);
		    dialogStage.setResizable(false);

		    LoginOverviewController controller = loader.getController();
		    controller.setMainApp(this, session);
		    controller.setDialogStage(dialogStage);
		    dialogStage.showAndWait();

		    return controller.isOkLogin();
		} catch (IOException e) {
			e.printStackTrace();
		    return false;
		}	
	}
	
	public void showBulkOverview() {
		try {
			FXMLLoader loader = new FXMLLoader();
		    loader.setLocation(Main.class.getResource("view/BulkOverview.fxml"));
		    AnchorPane page = (AnchorPane) loader.load();

		    Stage dialogStage = new Stage();
		    dialogStage.setTitle("MANGO - Carga de Registros");
		    dialogStage.getIcons().add(new Image("file:resources/images/lok.png"));
		    dialogStage.initModality(Modality.WINDOW_MODAL);
		    dialogStage.initOwner(primaryStage);
		    Scene scene = new Scene(page);
		    dialogStage.setScene(scene);
		    dialogStage.setResizable(false);

		    BulkOverviewController controller = loader.getController();
		    controller.setMainApp(this, session);
		    controller.setDialogStage(dialogStage);
		    dialogStage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public boolean showSincronizarAppOverview(boolean sinLogin) {
		try {
			FXMLLoader loader = new FXMLLoader();
		    loader.setLocation(Main.class.getResource("view/SincronizarAppOverview.fxml"));
		    AnchorPane page = (AnchorPane) loader.load();

		    Stage dialogStage = new Stage();
		    dialogStage.setTitle("Sincronizar");
		    dialogStage.getIcons().add(new Image("file:resources/images/lok.png"));
		    dialogStage.initModality(Modality.WINDOW_MODAL);
		    dialogStage.initOwner(primaryStage);
		    Scene scene = new Scene(page);
		    dialogStage.setScene(scene);
		    dialogStage.setResizable(false);

		    SincronizarAppController controller = loader.getController();
		    controller.setMainApp(this, session, sinLogin);
		    controller.setDialogStage(dialogStage);
		    dialogStage.showAndWait();

		    return true;
		} catch (IOException e) {
			e.printStackTrace();
		    return false;
		}	
	}
	
	public boolean showCambiarClaveOverview() {
		try {
			FXMLLoader loader = new FXMLLoader();
		    loader.setLocation(Main.class.getResource("view/CambiarPasswordOverview.fxml"));
		    AnchorPane page = (AnchorPane) loader.load();

		    Stage dialogStage = new Stage();
		    dialogStage.setTitle("Cambiar clave");
		    dialogStage.getIcons().add(new Image("file:resources/images/lok.png"));
		    dialogStage.initModality(Modality.WINDOW_MODAL);
		    dialogStage.initOwner(primaryStage);
		    Scene scene = new Scene(page);
		    dialogStage.setScene(scene);
		    dialogStage.setResizable(false);

		    CambiarPasswordOverviewController controller = loader.getController();
		    controller.setMainApp(this, session);
		    controller.setDialogStage(dialogStage);
		    dialogStage.showAndWait();

		    return true;
		} catch (IOException e) {
			e.printStackTrace();
		    return false;
		}	
	}
	
	public Instituciones showIpsFindListOverview() {
		try {
			FXMLLoader loader = new FXMLLoader();
		    loader.setLocation(Main.class.getResource("view/IpsFindListOverview.fxml"));
		    AnchorPane page = (AnchorPane) loader.load();

		    Stage dialogStage = new Stage();
		    dialogStage.setTitle("Instituciones");
		    dialogStage.getIcons().add(new Image("file:resources/images/lok.png"));
		    dialogStage.initModality(Modality.WINDOW_MODAL);
		    dialogStage.initOwner(primaryStage);
		    Scene scene = new Scene(page);
		    dialogStage.setScene(scene);
		    dialogStage.setResizable(false);

		    IpsFindListOverviewController controller = loader.getController();
		    controller.setMainApp(this, session);
		    controller.setDialogStage(dialogStage);
		    dialogStage.showAndWait();
		    Instituciones institucionSelect=controller.getIpsSelect();
		    
		    return institucionSelect;
		} catch (IOException e) {
			e.printStackTrace();
		    return null;
		}	
	}
	
	public Pacientes showPacientesFindListOverview() {
		try {
			FXMLLoader loader = new FXMLLoader();
		    loader.setLocation(Main.class.getResource("view/PacientesFindListOverview.fxml"));
		    AnchorPane page = (AnchorPane) loader.load();

		    Stage dialogStage = new Stage();
		    dialogStage.setTitle("Pacientes");
		    dialogStage.getIcons().add(new Image("file:resources/images/lok.png"));
		    dialogStage.initModality(Modality.WINDOW_MODAL);
		    dialogStage.initOwner(primaryStage);
		    Scene scene = new Scene(page);
		    dialogStage.setScene(scene);
		    dialogStage.setResizable(false);

		    PacientesFindListOverviewController controller = loader.getController();
		    controller.setMainApp(this, session);
		    controller.setDialogStage(dialogStage);
		    dialogStage.showAndWait();
		    Pacientes pacienteSelect=controller.getPacienteSelect();
		    
		    return pacienteSelect;
		} catch (IOException e) {
			e.printStackTrace();
		    return null;
		}	
	}
	
	public void showGraphicsOverview(String graficaDraw) {
		try {
			FXMLLoader loader = new FXMLLoader();
		    loader.setLocation(Main.class.getResource("view/GraphicsOverview.fxml"));
		    AnchorPane page = (AnchorPane) loader.load();

		    Stage dialogStage = new Stage();
		    dialogStage.setTitle("Grafica");
		    dialogStage.getIcons().add(new Image("file:resources/images/lok.png"));
		    dialogStage.initModality(Modality.WINDOW_MODAL);
		    dialogStage.initOwner(primaryStage);
		    Scene scene = new Scene(page);
		    dialogStage.setScene(scene);
		    dialogStage.setResizable(false);

		    GraphicsOverviewController controller = loader.getController();
		    controller.setMainApp(this, session, graficaDraw);
		    controller.setDialogStage(dialogStage);
		    dialogStage.showAndWait();
		    
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public void graficasHistoricasOverview(Pacientes paciente) {
		 try {
			 FXMLLoader loader = new FXMLLoader();
	         loader.setLocation(Main.class.getResource("view/GraficasHistoricasMenoresOverview.fxml"));
	         AnchorPane graficasHistoricasOverview = (AnchorPane) loader.load();

	         Stage dialogStage = new Stage();
			 dialogStage.setTitle("Graficas Historicas Menores");
			 dialogStage.getIcons().add(new Image("file:resources/images/lok.png"));
			 dialogStage.initModality(Modality.WINDOW_MODAL);
			 dialogStage.initOwner(primaryStage);
			 Scene scene = new Scene(graficasHistoricasOverview);
			 dialogStage.setScene(scene);
			 dialogStage.setResizable(false);
			 
			 HistoricoGraficasMenoresController controller = loader.getController();
			 controller.setMainApp(this, session, paciente);
			 controller.setDialogStage(dialogStage);
			 dialogStage.showAndWait();
	         
	     } catch (IOException e) {
	    	 e.printStackTrace();
	    }
	}
	

	public void graficasHistoricasGestantesOverview(Pacientes paciente) {
		try {
			 FXMLLoader loader = new FXMLLoader();
	         loader.setLocation(Main.class.getResource("view/GraficasHistoricasGestantesOverview.fxml"));
	         AnchorPane graficasHistoricasOverview = (AnchorPane) loader.load();

	         Stage dialogStage = new Stage();
			 dialogStage.setTitle("Graficas Historicas Gestantes");
			 dialogStage.getIcons().add(new Image("file:resources/images/lok.png"));
			 dialogStage.initModality(Modality.WINDOW_MODAL);
			 dialogStage.initOwner(primaryStage);
			 Scene scene = new Scene(graficasHistoricasOverview);
			 dialogStage.setScene(scene);
			 dialogStage.setResizable(false);
			 
			 HistoricoGraficasGestantesController controller = loader.getController();
			 controller.setMainApp(this, session, paciente);
			 controller.setDialogStage(dialogStage);
			 dialogStage.showAndWait();
	         
	     } catch (IOException e) {
	    	 e.printStackTrace();
	    }
	}
	
	public String showAddressOverview() {
		try {
			FXMLLoader loader = new FXMLLoader();
		    loader.setLocation(Main.class.getResource("view/DireccionOverview.fxml"));
		    AnchorPane page = (AnchorPane) loader.load();

		    Stage dialogStage = new Stage();
		    dialogStage.setTitle("Dirección");
		    dialogStage.getIcons().add(new Image("file:resources/images/lok.png"));
		    dialogStage.initModality(Modality.WINDOW_MODAL);
		    dialogStage.initOwner(primaryStage);
		    Scene scene = new Scene(page);
		    dialogStage.setScene(scene);
		    dialogStage.setResizable(false);

		    DireccionOverviewController controller = loader.getController();
		    controller.setMainApp(this, session);
		    controller.setDialogStage(dialogStage);
		    dialogStage.showAndWait();

		    return controller.getDirFull();
		} catch (IOException e) {
			e.printStackTrace();
		    return "null";
		}	
	}
	
	public Usuarios getUsuarioApp() {
		return usuarioApp;
	}


	public void setUsuarioApp(Usuarios usuarioApp) {
		this.usuarioApp = usuarioApp;
	}


	public Instituciones getInstitucionApp() {
		return institucionApp;
	}


	public void setInstitucionApp(Instituciones institucionApp) {
		this.institucionApp = institucionApp;
	}
	
	public Pacientes getPaciente() {
		return paciente;
	}


	public void setPaciente(Pacientes paciente) {
		this.paciente = paciente;
	}


	public Paciente_Detalles getPaciente_detalle() {
		return paciente_detalle;
	}


	public void setPaciente_detalle(Paciente_Detalles paciente_detalle) {
		this.paciente_detalle = paciente_detalle;
	}


	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}


	public void setFechaConsulta(Date fechaConsulta) {
		this.fechaConsulta=fechaConsulta;
	}	
}
