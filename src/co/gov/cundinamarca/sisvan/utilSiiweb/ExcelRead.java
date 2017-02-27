package co.gov.cundinamarca.sisvan.utilSiiweb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.function.BiConsumer;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Session;

import co.gov.cundinamarca.sisvan.model.parameters.Actividades;
import co.gov.cundinamarca.sisvan.model.parameters.Barrio_Veredas;
import co.gov.cundinamarca.sisvan.model.parameters.Departamentos;
import co.gov.cundinamarca.sisvan.model.parameters.Desarrollos;
import co.gov.cundinamarca.sisvan.model.parameters.Educacion_Menores;
import co.gov.cundinamarca.sisvan.model.parameters.Entidades;
import co.gov.cundinamarca.sisvan.model.parameters.Especialidades;
import co.gov.cundinamarca.sisvan.model.parameters.Instituciones;
import co.gov.cundinamarca.sisvan.model.parameters.Lactancias;
import co.gov.cundinamarca.sisvan.model.parameters.Lugar_Residencias;
import co.gov.cundinamarca.sisvan.model.parameters.Micronutrientes;
import co.gov.cundinamarca.sisvan.model.parameters.Municipios;
import co.gov.cundinamarca.sisvan.model.parameters.Paciente_Detalles;
import co.gov.cundinamarca.sisvan.model.parameters.Pacientes;
import co.gov.cundinamarca.sisvan.model.parameters.Programas;
import co.gov.cundinamarca.sisvan.model.parameters.Regimenes;
import co.gov.cundinamarca.sisvan.model.parameters.Riesgos;
import co.gov.cundinamarca.sisvan.model.parameters.Tipo_Consultas;
import co.gov.cundinamarca.sisvan.model.parameters.Tipo_Identificaciones;
import co.gov.cundinamarca.sisvan.model.parameters.Tipo_Poblaciones;
import co.gov.cundinamarca.sisvan.model.parameters.Usuarios;
import co.gov.cundinamarca.sisvan.model.transactions.Adultos;
import co.gov.cundinamarca.sisvan.model.transactions.Gestantes;
import co.gov.cundinamarca.sisvan.model.transactions.Menores;
import co.gov.cundinamarca.sisvan.repository.ActividadesRepository;
import co.gov.cundinamarca.sisvan.repository.AdultosRepository;
import co.gov.cundinamarca.sisvan.repository.Barrio_VeredasRepository;
import co.gov.cundinamarca.sisvan.repository.CargaRepository;
import co.gov.cundinamarca.sisvan.repository.DepartamentosRepository;
import co.gov.cundinamarca.sisvan.repository.DesarrollosRepository;
import co.gov.cundinamarca.sisvan.repository.Educacion_MenoresRepository;
import co.gov.cundinamarca.sisvan.repository.EntidadesRepository;
import co.gov.cundinamarca.sisvan.repository.EspecialidadesRepository;
import co.gov.cundinamarca.sisvan.repository.GestantesRepository;
import co.gov.cundinamarca.sisvan.repository.InstitucionesRepository;
import co.gov.cundinamarca.sisvan.repository.LactanciasRepository;
import co.gov.cundinamarca.sisvan.repository.Lugar_ResidenciasRepository;
import co.gov.cundinamarca.sisvan.repository.MenoresRepository;
import co.gov.cundinamarca.sisvan.repository.MicronutrientesRepository;
import co.gov.cundinamarca.sisvan.repository.MunicipiosRepository;
import co.gov.cundinamarca.sisvan.repository.ProgramasRepository;
import co.gov.cundinamarca.sisvan.repository.RegimenesRepository;
import co.gov.cundinamarca.sisvan.repository.RiesgosRepository;
import co.gov.cundinamarca.sisvan.repository.Tipo_ConsultasRepository;
import co.gov.cundinamarca.sisvan.repository.Tipo_IdentificacionesRepository;
import co.gov.cundinamarca.sisvan.repository.Tipo_PoblacionesRepository;
import co.gov.cundinamarca.sisvan.view.CalculateZscore;
import javafx.scene.control.TextArea;

public class ExcelRead {
	private List<Instituciones> listIstituciones;
	private List<Tipo_Identificaciones> listTiposIdentificaciones;
	private List<Departamentos> listDepartamentos;
	private List<Municipios> listMunicipios;
	private List<Barrio_Veredas> listBarrio_Veredas;
	private List<Lugar_Residencias> listLugar_Residencias;
	private List<Tipo_Consultas> listTipo_Consultas;
	private List<Lactancias> listLactancias;
	private List<Tipo_Poblaciones> listTipo_Poblaciones;
	private List<Programas> listProgramas;
	private List<Micronutrientes> listMicronutrientes;
	private List<Regimenes> listRegimenes;
	private List<Entidades> listEntidades;
	private List<Desarrollos> listDesarrollos;
	private List<Actividades> listActividades;
	private List<Especialidades> listEspecialidades;
	private List<Educacion_Menores> listEducacion_Menores;
	private List<Riesgos> listRiesgos;
	private MenoresRepository repoMenores;
	private AdultosRepository repoAdultos;
	private GestantesRepository repoGestantes;
	private CalculateZscore calculaZcore;

	private void readSheet(File file, int indexSheet, BiConsumer<Row, StringBuilder> consumer,
			StringBuilder message, TextArea loggingView) throws IOException, InvalidFormatException, NullPointerException {
		try (Workbook workBook = file.getAbsolutePath().contains(".xlsx") ? new XSSFWorkbook(file)
				: new HSSFWorkbook(new FileInputStream(file))) {
			Sheet sheet = workBook.getSheetAt(indexSheet);
			for (Row row : sheet) {
				String documento="";
				try{
					documento=loadCell(row.getCell(5));
					if(!documento.equals("")){
						try{
							consumer.accept(row, message);
						}catch(NullPointerException n){
							break;
						}catch(ArrayIndexOutOfBoundsException r){
							break;
						}
					}
				}
				catch(NullPointerException ne){
					break;
				}
				catch(ArrayIndexOutOfBoundsException r){
					break;
				}
				catch(Exception d){
				}
			}
			workBook.close();
		}
	}

	private <T> void save(Usuarios usuario, Pacientes paciente, Paciente_Detalles detalles, T entidad,
			Session session) {
		CargaRepository.<T>save(session, paciente, detalles, entidad);
	}

	private String loadCell(Cell cell) {
		String aux = null;
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC:
			aux = String.format(Locale.US, "%1$f", cell.getNumericCellValue()).replaceAll("\\.0+$", "");
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			aux = String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_ERROR:
			aux = null;
			break;
		case Cell.CELL_TYPE_FORMULA:
			aux = null;
			break;
		case Cell.CELL_TYPE_STRING:
			aux = cell.getStringCellValue();
			break;
		}
		return aux == null || "".equals(aux) ? null : aux;
	}

	private BiConsumer<Row, StringBuilder> kidConsumer(Session session, Usuarios usuario) {
		final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		return (Row row, StringBuilder buf) -> {
			if (row.getRowNum() > 0) {
				try {
					Pacientes paciente = new Pacientes();
					Paciente_Detalles detalles = new Paciente_Detalles();
					Menores menor = new Menores();
					
					Date mnor_now = Calendar.getInstance().getTime();
					String mnor_idIps=findIstituciones(loadCell(row.getCell(1)));
					Date mnor_fechaReporte=format.parse(loadCell(row.getCell(3)));
					String mnor_idTipoIdentificacion=findTiposIdentificacion(loadCell(row.getCell(4)));
					String mnor_identificacion=loadCell(row.getCell(5));
					String mnor_primerNombre=loadCell(row.getCell(6));
					String mnor_segundoNombre="";
					String mnor_segundoApellido="";
					
					try{
						mnor_segundoNombre=loadCell(row.getCell(7));
					}catch(NullPointerException e){
						
					}
					
					try{
						mnor_segundoApellido=loadCell(row.getCell(9));
					}catch(NullPointerException e){
						
					}
					
					String mnor_primerApellido=loadCell(row.getCell(8));
					String mnor_idDeptoResidencia=findDepto(loadCell(row.getCell(10)));
					String mnor_idMunicipio=findMunicipios(loadCell(row.getCell(11)));
					String mnor_direccionResidencia=loadCell(row.getCell(12));
					String mnor_idBarrio_Veredas=findBarrio_Veredas(loadCell(row.getCell(13)));
					String mnor_idLugar_Residencias=findLugar_Residencias(loadCell(row.getCell(14)));
					String mnor_telefono=loadCell(row.getCell(15));
					Date mnor_fechaNacimiento=format.parse(loadCell(row.getCell(16)));
					String mnor_idSexo=findSexo(loadCell(row.getCell(17)));
					String mnor_idControlRn=findSN(loadCell(row.getCell(18)));
					String mnor_idTipo_Consultas=findTipo_Consultas(loadCell(row.getCell(19)));
					String mnor_peso=loadCell(row.getCell(20));
					String mnor_talla=loadCell(row.getCell(21));
					String mnor_pCefalico=loadCell(row.getCell(22));
					String mnor_circunBrazo=loadCell(row.getCell(23));
					String mnor_idLactancias=findLactancias(loadCell(row.getCell(24)));
					String mnor_tiempoLactancias=loadCell(row.getCell(25));
					String mnor_idTipo_Poblaciones=findTipo_Poblaciones(loadCell(row.getCell(26)));
					String mnor_programaDtalNutricional=findSN(loadCell(row.getCell(27)));
					String mnor_idProgramas=findProgramas(loadCell(row.getCell(28)));
					String mnor_idMicronutrientes=findMicronutrientes(loadCell(row.getCell(29)));
					String mnor_idRegimenes=findRegimenes(loadCell(row.getCell(30)));
					String mnor_idEntidades=findEntidades(loadCell(row.getCell(31)));
					String mnor_idDesarrollos=findDesarrollos(loadCell(row.getCell(32)));
					String mnor_idEra=findSN(loadCell(row.getCell(33)));
					String mnor_idEda=findSN(loadCell(row.getCell(34)));
					String mnor_idDesparacitacion=findSN(loadCell(row.getCell(35)));
					String mnor_idActividades=findActividades(loadCell(row.getCell(36)));
					String mnor_idEspecialidades=findEspecialidades(loadCell(row.getCell(37)));
					String mnor_idEducacion_Menores=findEducacion_Menores(loadCell(row.getCell(38)));
										
					if((!mnor_identificacion.equals(null))&&(!mnor_identificacion.equals(""))){
						paciente.setTid_Pac_Id(mnor_idTipoIdentificacion);
						paciente.setPac_Id(mnor_identificacion);
						paciente.setUsu_Pac_Id(usuario.getUsu_Id());
						paciente.setPac_Primerapellido(mnor_primerApellido);
						paciente.setPac_Segundoapellido(mnor_segundoApellido);
						paciente.setPac_Primernombre(mnor_primerNombre);
						paciente.setPac_Segundonombre(mnor_segundoNombre);
						paciente.setPac_Sexo(mnor_idSexo);
						paciente.setPac_Fechanacimiento(mnor_fechaNacimiento);
						paciente.setPac_Estado("A");
						paciente.setPac_Fecha(mnor_now);
						
						detalles.setTid_Pac_Pacd_Id(mnor_idTipoIdentificacion);
						detalles.setPac_Pacd_Id(mnor_identificacion);
						detalles.setPacd_Id((long) 1);
						detalles.setUsu_Pacd_Id(usuario.getUsu_Id());
						detalles.setDep_Pacd_Id(mnor_idDeptoResidencia);
						detalles.setMun_Pacd_Id(mnor_idMunicipio);
						detalles.setBve_Pacd_Id(mnor_idBarrio_Veredas);
						detalles.setLre_Pacd_Id(mnor_idLugar_Residencias);
						detalles.setEps_Pacd_Id(mnor_idEntidades);
						detalles.setTpo_Pacd_Id(mnor_idTipo_Poblaciones);
						detalles.setReg_Pacd_Id(mnor_idRegimenes);
						detalles.setPacd_Direccion(mnor_direccionResidencia);
						detalles.setPacd_Telefono(mnor_telefono);
						detalles.setPacd_Fecha(mnor_now);
						
						menor.setIps_Mnor_Id(mnor_idIps);
						menor.setTid_Pac_Mnor_Id(mnor_idTipoIdentificacion);
						menor.setPac_Mnor_Id(mnor_identificacion);
						menor.setPacd_Mnor_Id((long) 1);
						menor.setLac_Mnor_Id(mnor_idLactancias);
						menor.setPrg_Mnor_Id(mnor_idProgramas);
						menor.setMnu_Mnor_Id(mnor_idMicronutrientes);
						menor.setAct_Mnor_Id(mnor_idActividades);
						menor.setTco_Mnor_Id(mnor_idTipo_Consultas);
						menor.setDes_Mnor_Id(mnor_idDesarrollos);
						menor.setEsp_Mnor_Id(mnor_idEspecialidades);
						menor.setEme_Mnor_Id(mnor_idEducacion_Menores);
						menor.setUsu_Mnor_Id(usuario.getUsu_Id());
						menor.setMnor_Fecha(mnor_now);
						menor.setMnor_Control(mnor_idControlRn);
						menor.setMnor_Peso(Double.valueOf(mnor_peso));
						menor.setMnor_Talla(Double.valueOf(mnor_talla));
						menor.setMnor_Perimetro_Cefalico(Double.valueOf(mnor_pCefalico));
						menor.setMnor_Brazo(Double.valueOf(mnor_circunBrazo));
						menor.setMnor_Tiempo_Lactancia(Long.valueOf(mnor_tiempoLactancias));
						menor.setMnor_Paquete_Nutricional(mnor_programaDtalNutricional);
						menor.setMnor_Era(mnor_idEra);
						menor.setMnor_Eda(mnor_idEda);
						menor.setMnor_Desparasitacion(mnor_idDesparacitacion);
						menor.setMnor_Zstalla_Edad((double) 0);
						menor.setMnor_Zspeso_Edad((double) 0);
						menor.setMnor_Zsimc_Edad((double) 0);
						menor.setMnor_Zspeso_Talla((double) 0);
						menor.setMnor_Zspcefalico_Edad((double) 0);
						menor.setMnor_Estado("R");
						menor.setMnor_Fecha_Reporte(mnor_fechaReporte);
						menor.setMnor_sync("NS");
						
						if(repoMenores.buscarMenoresNoRepeat(mnor_identificacion, mnor_fechaReporte)){
							save(usuario, paciente, detalles, menor, session);
							calculaZcore.calcularMenor(menor, paciente, session);
						}
					}
					
				}
				catch(NullPointerException e){
					addError(buf, "Menores", row, e);
				}
				catch (Exception ex) {
					addError(buf, "Menores", row, ex);
				}
			}
		};
	}

	private BiConsumer<Row, StringBuilder> pregnantConsumer(Session session, Usuarios usuario) {
		final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		return (Row row, StringBuilder buf) -> {
			if (row.getRowNum() > 0) {
				try {
					Pacientes paciente = new Pacientes();
					Paciente_Detalles detalles = new Paciente_Detalles();
					Gestantes gestante = new Gestantes();
					
					Date now = Calendar.getInstance().getTime();
					String idIps=findIstituciones(loadCell(row.getCell(1)));
					Date fechaReporte=format.parse(loadCell(row.getCell(3)));
					String idTipoIdentificacion=findTiposIdentificacion(loadCell(row.getCell(4)));
					String identificacion=loadCell(row.getCell(5));
					String primerNombre=loadCell(row.getCell(6));
					String segundoNombre="";
					String segundoApellido="";
					
					try{
						segundoNombre=loadCell(row.getCell(7));
					}catch(NullPointerException e){
						
					}
					
					try{
						segundoApellido=loadCell(row.getCell(9));
					}catch(NullPointerException e){
						
					}
					
					String primerApellido=loadCell(row.getCell(8));
					String idDeptoResidencia=findDepto(loadCell(row.getCell(10)));
					String idMunicipio=findMunicipios(loadCell(row.getCell(11)));
					String direccionResidencia=loadCell(row.getCell(12));
					String idBarrio_Veredas=findBarrio_Veredas(loadCell(row.getCell(13)));
					String idLugar_Residencias=findLugar_Residencias(loadCell(row.getCell(14)));
					String telefono=loadCell(row.getCell(15));
					Date fechaNacimiento=format.parse(loadCell(row.getCell(16)));
					String idTipo_Consultas=findTipo_Consultas(loadCell(row.getCell(17)));
					String pesoPregestacional=loadCell(row.getCell(18));
					String pesoActual=loadCell(row.getCell(19));
					String talla=loadCell(row.getCell(20));
					String semanasGestacion=loadCell(row.getCell(21));
					String idTipo_Poblaciones=findTipo_Poblaciones(loadCell(row.getCell(22)));
					String programaDtalNutricional=findSN(loadCell(row.getCell(23)));
					String idProgramas=findProgramas(loadCell(row.getCell(24)));
					String idRegimenes=findRegimenes(loadCell(row.getCell(25)));
					String idEntidades=findEntidades(loadCell(row.getCell(26)));
					String idEspecialidades=findEspecialidades(loadCell(row.getCell(27)));
					String idMicronutrientes=findMicronutrientes(loadCell(row.getCell(28)));
					String educacionNuticional=findSN(loadCell(row.getCell(29)));
					String idActividades=findActividades(loadCell(row.getCell(30)));
					String idRiesgos=findRiesgos(loadCell(row.getCell(31)));
					
					if((!identificacion.equals(null))&&(!identificacion.equals(""))){
						paciente.setPac_Id(identificacion);
						paciente.setTid_Pac_Id(idTipoIdentificacion);
						paciente.setUsu_Pac_Id(usuario.getUsu_Id());
						paciente.setPac_Primerapellido(primerApellido);
						paciente.setPac_Segundoapellido(segundoApellido);
						paciente.setPac_Primernombre(primerNombre);
						paciente.setPac_Segundonombre(segundoNombre);
						paciente.setPac_Sexo("1");
						paciente.setPac_Fechanacimiento(fechaNacimiento);
						paciente.setPac_Estado("A");
						paciente.setPac_Fecha(now);
						
						detalles.setTid_Pac_Pacd_Id(idTipoIdentificacion);
						detalles.setPac_Pacd_Id(identificacion);
						detalles.setPacd_Id((long) 1);
						detalles.setUsu_Pacd_Id(usuario.getUsu_Id());
						detalles.setDep_Pacd_Id(idDeptoResidencia);
						detalles.setMun_Pacd_Id(idMunicipio);
						detalles.setBve_Pacd_Id(idBarrio_Veredas);
						detalles.setLre_Pacd_Id(idLugar_Residencias);
						detalles.setEps_Pacd_Id(idEntidades);
						detalles.setTpo_Pacd_Id(idTipo_Poblaciones);
						detalles.setReg_Pacd_Id(idRegimenes);
						detalles.setPacd_Direccion(direccionResidencia);
						detalles.setPacd_Telefono(telefono);
						detalles.setPacd_Fecha(now);
						
						gestante.setIps_Ges_Id(idIps);
						gestante.setGes_Fecha_Reporte(fechaReporte);
						gestante.setTid_Pac_Ges_Id(idTipoIdentificacion);
						gestante.setPac_Ges_Id(identificacion);
						gestante.setCtr_Ges_Id(idTipo_Consultas);
						gestante.setGes_Peso_Pregestacion(Double.valueOf(pesoPregestacional));
						gestante.setGes_Peso_Actual(Double.valueOf(pesoActual));
						gestante.setGes_Talla(Double.valueOf(talla));
						gestante.setGes_Edadgestacional(Long.valueOf(semanasGestacion));
						gestante.setGes_Paquete_Nutricional(programaDtalNutricional);
						gestante.setPrg_Ges_Id(idProgramas);
						gestante.setGes_Remision(idEspecialidades);
						gestante.setMnu_Ges_Id(idMicronutrientes);
						gestante.setGes_Educacion_Nutricional(educacionNuticional);
						gestante.setAct_Ges_Id(idActividades);
						gestante.setRie_Ges_Id(idRiesgos);
						gestante.setUsu_Ges_Id(usuario.getUsu_Id());
						gestante.setGes_Fecha(now);
						gestante.setGes_sync("NS");
						gestante.setGes_Estado("R");
						gestante.setPacd_Ges_Id((long) 1);
						
						if(repoGestantes.buscarGestantesNoRepeat(identificacion, fechaReporte)){
							save(usuario, paciente, detalles, gestante, session);
							calculaZcore.calculaGestante(gestante, paciente, session);
						}else{
						}
					}
					
				}
				catch(NullPointerException e){
					addError(buf, "Gestantes", row, e);
				}
				catch (Exception ex) {
					addError(buf, "Gestantes", row, ex);
				}
			}
		};
	}

	private BiConsumer<Row, StringBuilder> AdultConsumer(Session session, Usuarios usuario) {
		final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		return (Row row, StringBuilder buf) -> {
			if (row.getRowNum() > 0) {
				try {
					Pacientes paciente = new Pacientes();
					Paciente_Detalles detalles = new Paciente_Detalles();
					Adultos adulto = new Adultos();
					
					Date adul_now = Calendar.getInstance().getTime();
					String adul_idIps=findIstituciones(loadCell(row.getCell(1)));
					Date adul_fechaReporte=format.parse(loadCell(row.getCell(3)));
					String adul_idTipoIdentificacion=findTiposIdentificacion(loadCell(row.getCell(4)));
					String adul_identificacion=loadCell(row.getCell(5));
					String adul_primerNombre=loadCell(row.getCell(6));
					String adul_segundoNombre="";
					String adul_segundoApellido="";
					
					try{
						adul_segundoNombre=loadCell(row.getCell(7));
					}catch(NullPointerException e){
					}
					
					try{
						adul_segundoApellido=loadCell(row.getCell(9));
					}catch(NullPointerException e){
						
					}
					
					String adul_primerApellido=loadCell(row.getCell(8));
					String adul_idDeptoResidencia=findDepto(loadCell(row.getCell(10)));
					String adul_idMunicipio=findMunicipios(loadCell(row.getCell(11)));
					String adul_direccionResidencia=loadCell(row.getCell(12));
					String adul_idBarrio_Veredas=findBarrio_Veredas(loadCell(row.getCell(13)));
					String adul_idLugar_Residencias=findLugar_Residencias(loadCell(row.getCell(14)));
					String adul_telefono=loadCell(row.getCell(15));
					Date adul_fechaNacimiento=format.parse(loadCell(row.getCell(16)));
					String adul_idSexo=findSexo(loadCell(row.getCell(17)));
					String adul_mujerLactante=findSN(loadCell(row.getCell(18)));
					String adul_peso=loadCell(row.getCell(19));
					String adul_talla=loadCell(row.getCell(20));
					String adul_circunCintura=loadCell(row.getCell(21));
					String adul_circunMuneca=loadCell(row.getCell(22));
					String adul_idTipo_Poblaciones=findTipo_Poblaciones(loadCell(row.getCell(23)));
					String adul_programaDtalNutricional=findSN(loadCell(row.getCell(24)));
					String adul_idProgramas=findProgramas(loadCell(row.getCell(25)));
					String adul_idRegimenes=findRegimenes(loadCell(row.getCell(26)));
					String adul_idEntidades=findEntidades(loadCell(row.getCell(27)));
					String adul_idEspecialidades=findEspecialidades(loadCell(row.getCell(28)));
					String adul_idMicronutrientes=findMicronutrientes(loadCell(row.getCell(29)));
					String adul_educacionNuticional=findSN(loadCell(row.getCell(30)));
					String adul_idActividades=findActividades(loadCell(row.getCell(31)));
					
					if((!adul_identificacion.equals(null))&&(!adul_identificacion.equals(""))){
						paciente.setTid_Pac_Id(adul_idTipoIdentificacion);
						paciente.setPac_Id(adul_identificacion);
						paciente.setUsu_Pac_Id(usuario.getUsu_Id());
						paciente.setPac_Primerapellido(adul_primerApellido);
						paciente.setPac_Segundoapellido(adul_segundoApellido);
						paciente.setPac_Primernombre(adul_primerNombre);
						paciente.setPac_Segundonombre(adul_segundoNombre);
						paciente.setPac_Sexo(adul_idSexo);
						paciente.setPac_Fechanacimiento(adul_fechaNacimiento);
						paciente.setPac_Estado("A");
						paciente.setPac_Fecha(adul_now);
						
						detalles.setTid_Pac_Pacd_Id(adul_idTipoIdentificacion);
						detalles.setPac_Pacd_Id(adul_identificacion);
						detalles.setPacd_Id((long) 1);
						detalles.setUsu_Pacd_Id(usuario.getUsu_Id());
						detalles.setDep_Pacd_Id(adul_idDeptoResidencia);
						detalles.setMun_Pacd_Id(adul_idMunicipio);
						detalles.setBve_Pacd_Id(adul_idBarrio_Veredas);
						detalles.setLre_Pacd_Id(adul_idLugar_Residencias);
						detalles.setEps_Pacd_Id(adul_idEntidades);
						detalles.setTpo_Pacd_Id(adul_idTipo_Poblaciones);
						detalles.setReg_Pacd_Id(adul_idRegimenes);
						detalles.setPacd_Direccion(adul_direccionResidencia);
						detalles.setPacd_Telefono(adul_telefono);
						detalles.setPacd_Fecha(adul_now);
						
						adulto.setIps_Adu_Id(adul_idIps);
						adulto.setAdu_Fecha_Reporte(adul_fechaReporte);
						adulto.setTid_Pac_Adu_Id(adul_idTipoIdentificacion);
						adulto.setPac_Adu_Id(adul_identificacion);
						adulto.setAdu_Mujer_Lactante(adul_mujerLactante);
						adulto.setAdu_Peso(Double.valueOf(adul_peso));
						adulto.setAdu_Talla(Double.valueOf(adul_talla));
						adulto.setAdu_Cintura(Double.valueOf(adul_circunCintura));
						adulto.setAdu_Muneca(Double.valueOf(adul_circunMuneca));
						adulto.setAdu_Paquete_Nutricional(adul_programaDtalNutricional);
						adulto.setPrg_Adu_Id(adul_idProgramas);
						adulto.setAdu_Remision(adul_idEspecialidades);
						adulto.setMnu_Adu_Id(adul_idMicronutrientes);
						adulto.setAdu_Educacion_Nutricional(adul_educacionNuticional);
						adulto.setAct_Adu_Id(adul_idActividades);
						adulto.setUsu_Adu_Id(usuario.getUsu_Id());
						adulto.setAdu_Fecha(Calendar.getInstance().getTime());
						adulto.setAdu_sync("NS");
						adulto.setAdu_Estado("R");
						adulto.setPacd_Adu_Id((long) 1);
						
						if(repoAdultos.buscarAdultosNoRepeat(adul_identificacion, adul_fechaReporte)){
							save(usuario, paciente, detalles, adulto, session);
						}else{
						}
					}	
				}
				catch(NullPointerException e){
					addError(buf, "Adultos", row, e);
				}
				catch (Exception ex) {
					addError(buf, "Adultos", row, ex);
				}
			}
		};
	}

	private void addError(StringBuilder buf, String sheet, Row row, Exception ex) {
		String error ="";
		Exception en = new NullPointerException();
		if (ex.toString().equals(en.toString())){
			error="Algunos Campos Obligatrios estan Vacios";
		}else{
			error = ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage();
		}
		buf.append(String.format("%1$s%2$s-Fila: %3$d. %4$s", System.getProperty("line.separator"), sheet,
				row.getRowNum() + 1, error));

	}
	
	public void cargarVariables(Session session, TextArea loggingView){
		loggingView.appendText("\nCargando Variables en Memoria");
		loadVariablesMemoria(session);
	}
	
	public void cargarMenores(File file, StringBuilder message, Session session, Usuarios usuario, TextArea loggingView)
			throws FileNotFoundException, IOException, InvalidFormatException {
		loggingView.appendText("\nCargando Menores...");
		readSheet(file, 0, kidConsumer(session, usuario), message, loggingView);
	}
	
	public void cargarGestates(File file, StringBuilder message, Session session, Usuarios usuario, TextArea loggingView)
			throws FileNotFoundException, IOException, InvalidFormatException {
		loggingView.appendText("\nCargando Gestantes...");
		readSheet(file, 1, pregnantConsumer(session, usuario), message, loggingView);
	}
	
	public void cargarAdultos(File file, StringBuilder message, Session session, Usuarios usuario, TextArea loggingView)
			throws FileNotFoundException, IOException, InvalidFormatException {
		loggingView.appendText("\nCargando Adultos...");
		readSheet(file, 2, AdultConsumer(session, usuario), message, loggingView);
	}
	
	private void loadVariablesMemoria(Session session){
		calculaZcore=new CalculateZscore();
		repoMenores=new MenoresRepository(session);
		repoAdultos=new AdultosRepository(session);
		repoGestantes=new GestantesRepository(session);
		InstitucionesRepository repoInstituciones=new InstitucionesRepository(session);
		Tipo_IdentificacionesRepository repoTipo_identificacione=new Tipo_IdentificacionesRepository(session);
		DepartamentosRepository repoDepartamentos=new DepartamentosRepository(session);
		MunicipiosRepository repoMunicipios=new MunicipiosRepository(session);
		Barrio_VeredasRepository repoBarrio_Veredas=new Barrio_VeredasRepository(session);
		Lugar_ResidenciasRepository repoLugar_Residencias=new Lugar_ResidenciasRepository(session);
		Tipo_ConsultasRepository repoTipo_Consultas=new Tipo_ConsultasRepository(session);
		LactanciasRepository repoLactancias=new LactanciasRepository(session);
		Tipo_PoblacionesRepository repoTipo_Poblaciones=new Tipo_PoblacionesRepository(session);
		ProgramasRepository repoProgramas=new ProgramasRepository(session);
		MicronutrientesRepository repoMicronutrientes=new MicronutrientesRepository(session);
		RegimenesRepository repoRegimenes=new RegimenesRepository(session);
		EntidadesRepository repoEntidades=new EntidadesRepository(session);
		DesarrollosRepository repoDesarrollos=new DesarrollosRepository(session);
		ActividadesRepository repoActividades=new ActividadesRepository(session);
		EspecialidadesRepository repoEspecialidades=new EspecialidadesRepository(session);
		Educacion_MenoresRepository repoEducacion_Menores=new Educacion_MenoresRepository(session);
		RiesgosRepository repoRiesgos=new RiesgosRepository(session);
		listRiesgos=repoRiesgos.selectAll();
		listEducacion_Menores=repoEducacion_Menores.selectAll();
		listEspecialidades=repoEspecialidades.selectAll();
		listActividades=repoActividades.selectAll();
		listDesarrollos=repoDesarrollos.selectAll();
		listEntidades=repoEntidades.selectAll();
		listRegimenes=repoRegimenes.selectAll();
		listMicronutrientes=repoMicronutrientes.selectAll();
		listProgramas=repoProgramas.selectAll();
		listTipo_Poblaciones=repoTipo_Poblaciones.selectAll();
		listLactancias=repoLactancias.selectAll();
		listTipo_Consultas=repoTipo_Consultas.selectAll();
		listLugar_Residencias=repoLugar_Residencias.selectAll();
		listBarrio_Veredas=repoBarrio_Veredas.selectAll();
		listMunicipios=repoMunicipios.selectAll();
		listIstituciones=repoInstituciones.selectAll();
		listTiposIdentificaciones=repoTipo_identificacione.selectAll();
		listDepartamentos=repoDepartamentos.selectAll();
	}
	
	private String findIstituciones(String name){
		String Idreturn="000";
		Instituciones istitucion=new Instituciones();
		try{
			
			Iterator<Instituciones> iter = listIstituciones.iterator();
			while (iter.hasNext()){
				istitucion=(Instituciones) iter.next();
				if(istitucion.getIps_Nombre().equalsIgnoreCase(name)){
					Idreturn=istitucion.getIps_Id();
					break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return Idreturn;
	}
	
	private String findTiposIdentificacion(String name) {
		String Idreturn="0";
		Tipo_Identificaciones tipoIdentificacion=new Tipo_Identificaciones();
		try{
			
			Iterator<Tipo_Identificaciones> iter = listTiposIdentificaciones.iterator();
			while (iter.hasNext()){
				tipoIdentificacion=(Tipo_Identificaciones) iter.next();
				if(tipoIdentificacion.getTid_Nombre().equalsIgnoreCase(name)){
					Idreturn=tipoIdentificacion.getTid_Id();
					break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return Idreturn;
	}
	
	private String findDepto(String name) {
		String Idreturn="25";
		Departamentos departamento=new Departamentos();
		try{
			
			Iterator<Departamentos> iter = listDepartamentos.iterator();
			while (iter.hasNext()){
				departamento=(Departamentos) iter.next();
				if(departamento.getDep_Nombre().equalsIgnoreCase(name)){
					Idreturn=departamento.getDep_Id();
					break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return Idreturn;
	}
	
	private String findMunicipios(String name) {
		String Idreturn="000";
		Municipios municipio=new Municipios();
		try{
			
			Iterator<Municipios> iter = listMunicipios.iterator();
			while (iter.hasNext()){
				municipio=(Municipios) iter.next();
				if(municipio.getMun_Nombre().equalsIgnoreCase(name)){
					Idreturn=municipio.getMun_Id();
					break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return Idreturn;
	}
	
	private String findBarrio_Veredas(String name) {
		String Idreturn="000";
		Barrio_Veredas barrio_Veredas=new Barrio_Veredas();
		try{
			
			Iterator<Barrio_Veredas> iter = listBarrio_Veredas.iterator();
			while (iter.hasNext()){
				barrio_Veredas=(Barrio_Veredas) iter.next();
				if(barrio_Veredas.getBve_Nombre().equalsIgnoreCase(name)){
					Idreturn=barrio_Veredas.getBve_Id();
					break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return Idreturn;
	}
	
	private String findLugar_Residencias(String name) {
		String Idreturn="0";
		Lugar_Residencias lugar_Residencias=new Lugar_Residencias();
		try{
			
			Iterator<Lugar_Residencias> iter = listLugar_Residencias.iterator();
			while (iter.hasNext()){
				lugar_Residencias=(Lugar_Residencias) iter.next();
				if(lugar_Residencias.getLre_Nombre().equalsIgnoreCase(name)){
					Idreturn=lugar_Residencias.getLre_Id();
					break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return Idreturn;
	}
	
	private String findSexo(String name){
		
		String idSexo="2";
		switch (name){
		case "MASCULINO":
			idSexo="0";
			break;
		case "FEMENINO":
			idSexo="1";
			break;
		case "INDETERMINADO":
			idSexo="2";
			break;
		default:
			idSexo="2";
			break;
		}
		return idSexo;
	}
	
	private String findSN(String name){
		
		String idSN="1";
		switch (name){
		case "SI":
			idSN="1";
			break;
		case "NO":
			idSN="2";
			break;
		default:
			idSN="1";
			break;
		}
		return idSN;
	}
	
	private String findTipo_Consultas(String name) {
		String Idreturn="4";
		Tipo_Consultas tipo_Consultas=new Tipo_Consultas();
		try{
			
			Iterator<Tipo_Consultas> iter = listTipo_Consultas.iterator();
			while (iter.hasNext()){
				tipo_Consultas=(Tipo_Consultas) iter.next();
				if(tipo_Consultas.getTco_Nombre().equalsIgnoreCase(name)){
					Idreturn=tipo_Consultas.getTco_Id();
					break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return Idreturn;
	}
	
	private String findLactancias(String name) {
		String Idreturn="4";
		Lactancias lactancias=new Lactancias();
		try{
			
			Iterator<Lactancias> iter = listLactancias.iterator();
			while (iter.hasNext()){
				lactancias=(Lactancias) iter.next();
				if(lactancias.getLac_Nombre().equalsIgnoreCase(name)){
					Idreturn=lactancias.getLac_Id();
					break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return Idreturn;
	}
	
	private String findTipo_Poblaciones(String name) {
		String Idreturn="0";
		Tipo_Poblaciones tipo_Poblaciones=new Tipo_Poblaciones();
		try{
			
			Iterator<Tipo_Poblaciones> iter = listTipo_Poblaciones.iterator();
			while (iter.hasNext()){
				tipo_Poblaciones=(Tipo_Poblaciones) iter.next();
				if(tipo_Poblaciones.getTpo_Nombre().equalsIgnoreCase(name)){
					Idreturn=tipo_Poblaciones.getTpo_Id();
					break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return Idreturn;
	}
	
	private String findProgramas(String name) {
		String Idreturn="9";
		Programas programas=new Programas();
		try{
			
			Iterator<Programas> iter = listProgramas.iterator();
			while (iter.hasNext()){
				programas=(Programas) iter.next();
				if(programas.getPrg_Nombre().equalsIgnoreCase(name)){
					Idreturn=programas.getPrg_Id();
					break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return Idreturn;
	}
	
	private String findMicronutrientes(String name) {
		String Idreturn="6";
		Micronutrientes micronutrientes=new Micronutrientes();
		try{
			
			Iterator<Micronutrientes> iter = listMicronutrientes.iterator();
			while (iter.hasNext()){
				micronutrientes=(Micronutrientes) iter.next();
				if(micronutrientes.getMnu_Nombre().equalsIgnoreCase(name)){
					Idreturn=micronutrientes.getMnu_Id();
					break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return Idreturn;
	}
	
	private String findRegimenes(String name) {
		String Idreturn="5";
		Regimenes regimenes=new Regimenes();
		try{
			
			Iterator<Regimenes> iter = listRegimenes.iterator();
			while (iter.hasNext()){
				regimenes=(Regimenes) iter.next();
				if(regimenes.getReg_Nombre().equalsIgnoreCase(name)){
					Idreturn=regimenes.getReg_Id();
					break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return Idreturn;
	}
	
	private String findEntidades(String name) {
		String Idreturn="000";
		Entidades entidades=new Entidades();
		try{
			
			Iterator<Entidades> iter = listEntidades.iterator();
			while (iter.hasNext()){
				entidades=(Entidades) iter.next();
				if(entidades.getEps_Nombre().equalsIgnoreCase(name)){
					Idreturn=entidades.getEps_Id();
					break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return Idreturn;
	}
	
	private String findDesarrollos(String name) {
		String Idreturn="5";
		Desarrollos desarrollos=new Desarrollos();
		try{
			
			Iterator<Desarrollos> iter = listDesarrollos.iterator();
			while (iter.hasNext()){
				desarrollos=(Desarrollos) iter.next();
				if(desarrollos.getDes_Nombre().equalsIgnoreCase(name)){
					Idreturn=desarrollos.getDes_Id();
					break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return Idreturn;
	}
	
	private String findActividades(String name) {
		String Idreturn="1";
		Actividades actividades=new Actividades();
		try{
			
			Iterator<Actividades> iter = listActividades.iterator();
			while (iter.hasNext()){
				actividades=(Actividades) iter.next();
				if(actividades.getAct_Nombre().equalsIgnoreCase(name)){
					Idreturn=actividades.getAct_Id();
					break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return Idreturn;
	}
	
	private String findEspecialidades(String name) {
		String Idreturn="10";
		Especialidades especialidades=new Especialidades();
		try{
			
			Iterator<Especialidades> iter = listEspecialidades.iterator();
			while (iter.hasNext()){
				especialidades=(Especialidades) iter.next();
				if(especialidades.getEsp_Nombre().equalsIgnoreCase(name)){
					Idreturn=especialidades.getEsp_Id();
					break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return Idreturn;
	}
	
	private String findEducacion_Menores(String name) {
		String Idreturn="10";
		Educacion_Menores educacion_Menores=new Educacion_Menores();
		try{
			
			Iterator<Educacion_Menores> iter = listEducacion_Menores.iterator();
			while (iter.hasNext()){
				educacion_Menores=(Educacion_Menores) iter.next();
				if(educacion_Menores.getEme_Nombre().equalsIgnoreCase(name)){
					Idreturn=educacion_Menores.getEme_Id();
					break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return Idreturn;
	}
	
	private String findRiesgos(String name) {
		String Idreturn="11";
		Riesgos riesgos=new Riesgos();
		try{
			
			Iterator<Riesgos> iter = listRiesgos.iterator();
			while (iter.hasNext()){
				riesgos=(Riesgos) iter.next();
				if(riesgos.getRie_Nombre().equalsIgnoreCase(name)){
					Idreturn=riesgos.getRie_Id();
					break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return Idreturn;
	}
}
