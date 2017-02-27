package co.gov.cundinamarca.sisvan.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.hibernate.Session;

import co.gov.cundinamarca.sisvan.model.parameters.Pacientes;
import co.gov.cundinamarca.sisvan.model.transactions.Gestantes;
import co.gov.cundinamarca.sisvan.model.transactions.Menores;
import co.gov.cundinamarca.sisvan.repository.GestantesRepository;
import co.gov.cundinamarca.sisvan.repository.MenoresRepository;
import co.gov.cundinamarca.sisvan.utilSiiweb.DatosCalculados;
import javafx.fxml.FXML;

public class CalculateZscore {
	
	private Pacientes paciente;
	private Menores menor;
	private MenoresRepository repomenores;
	private Gestantes gestante;
	private GestantesRepository repoGestantes;
	DatosCalculados datosCaculados;

	private String NASHORN_ENGINE_NAME = "nashorn";
    private ScriptEngine engine;
    private Invocable invoker;
	
	private static final File SCRIPT = new File("src/co/com/siiweb/sisvan/view/htmlView/js/antroprometric.js");

	
	public CalculateZscore(){
		try{
			FileReader fr = new FileReader(SCRIPT);
			ScriptEngineManager manager = new ScriptEngineManager();
	        engine = manager.getEngineByName(NASHORN_ENGINE_NAME);
	        invoker = (Invocable)engine;
	        engine.eval(fr);
		}catch(Exception f){
			f.printStackTrace();
		}
	}
    
	public boolean calculaGestante(Gestantes gestante, Pacientes paciente, Session session){
		try{
			this.gestante=gestante;
			this.paciente=paciente;
			repoGestantes=new GestantesRepository(session);
			datosCaculados=new DatosCalculados();
			if(calculaDatosGestante()){
				repoGestantes.saveGestante(gestante);
				return true;
			}else{
				return false;
			}
    	}catch(Exception r){
    		r.printStackTrace();
    		return false;
    	}
	}
	
    @SuppressWarnings("finally")
	private boolean calculaDatosGestante() {
    	boolean okCalcula=false;
		try{
			String limiteVal=limitesGestantes();
			if(limiteVal.equals("")){
				String tallaGestante=String.valueOf(gestante.getGes_Talla());
				tallaGestante=tallaGestante.replace(",", ".");
				String pesoGestante=String.valueOf(gestante.getGes_Peso_Actual());
				pesoGestante=pesoGestante.replace(",", ".");
				String semanasGestacion=String.valueOf(gestante.getGes_Edadgestacional());
				semanasGestacion=semanasGestacion.replace(",", ".");
				
				Object iMCCalculate=invoker.invokeFunction("calculateAgeImc",pesoGestante,tallaGestante);
				System.out.println("iMCCalculate: "+iMCCalculate.toString());
				
				Object estadoNutrional=invoker.invokeFunction("pregnantNutritionalState",iMCCalculate.toString(),semanasGestacion);
				System.out.println("Estado Nutricional: "+estadoNutrional.toString());
								
				if((estadoNutrional.toString()!=null)&&(!estadoNutrional.toString().equals(""))&&(!estadoNutrional.toString().equals("-"))){
					try{
						gestante.setGes_Estado_Nutricional(estadoNutrional.toString());
					}catch(Exception g){
						gestante.setGes_Estado_Nutricional("");
					}
				}else{
					gestante.setGes_Estado_Nutricional("");
				}
				
				okCalcula=true;
			}
		}catch(Exception er){
			er.printStackTrace();
			okCalcula=false;
		}
		finally{
			return okCalcula;
		}
	}
    
    @SuppressWarnings("finally")
	private String limitesGestantes(){
		String mensajeError="";
		try{
			String tallaGestante=String.valueOf(gestante.getGes_Talla());
			tallaGestante=tallaGestante.replace(",", ".");
			String pesoGestante=String.valueOf(gestante.getGes_Peso_Actual());
			pesoGestante=pesoGestante.replace(",", ".");
			String semanasGestacion=String.valueOf(gestante.getGes_Edadgestacional());
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
			
		}catch(Exception e){
			e.printStackTrace();
			mensajeError=e.toString();
		}finally{
			return mensajeError;
		}
	}

	public boolean calcularMenor(Menores menor, Pacientes paciente, Session session){
    	try{
			this.menor=menor;
			this.paciente=paciente;
			repomenores=new MenoresRepository(session);
			datosCaculados=new DatosCalculados();
			if(calculaDatos()){
				repomenores.saveMenor(menor);
				return true;
			}else{
				return false;
			}
    	}catch(Exception r){
    		r.printStackTrace();
    		return false;
    	}
    }
	
	@SuppressWarnings("finally")
	private boolean calculaDatos(){
		boolean okCalcula=false;
		try{
			String limiteVal=limitesMenores();
			if(limiteVal.equals("")){
				String tallaMenor=String.valueOf(menor.getMnor_Talla());
				tallaMenor=tallaMenor.replace(",", ".");
				String pesoMenor=String.valueOf(menor.getMnor_Peso());
				pesoMenor=pesoMenor.replace(",", ".");
				String perimetroCefalico=String.valueOf(menor.getMnor_Perimetro_Cefalico());
				perimetroCefalico=perimetroCefalico.replace(",", ".");
				String circunferenciaBrazo=String.valueOf(menor.getMnor_Brazo());
				circunferenciaBrazo=circunferenciaBrazo.replace(",", ".");
				
				String edadAproximadaCalculadaOnly=datosCaculados.calulaEdadAproximadaOnlyNumber(paciente.getPac_Fechanacimiento());
				String diasDiferencia=String.valueOf(datosCaculados.calculaDias(paciente.getPac_Fechanacimiento(), menor.getMnor_Fecha_Reporte()));
				
				Object zScoreSizeAgeCalculate=invoker.invokeFunction("calculateSizeAge",tallaMenor,diasDiferencia,paciente.getPac_Sexo());
				System.out.println("DatozScoreSizeAgeCalculate: "+zScoreSizeAgeCalculate.toString());
				
				Object zScoreAgeIMCCalculate=invoker.invokeFunction("calculateZScoreAgeImc",pesoMenor,tallaMenor,diasDiferencia,paciente.getPac_Sexo(),edadAproximadaCalculadaOnly);
				System.out.println("Dato zScoreAgeIMCCalculate: "+zScoreAgeIMCCalculate.toString());
				
				Object zScoreWeightAge=invoker.invokeFunction("calculateWeightAge",pesoMenor,diasDiferencia,paciente.getPac_Sexo());
				System.out.println("Dato zScoreWeightAge: "+zScoreWeightAge.toString());
				
				Object zScoreHeadAge=invoker.invokeFunction("calculateHeadAge",perimetroCefalico,diasDiferencia,paciente.getPac_Sexo());
				System.out.println("Dato zScoreHeadAge: "+zScoreHeadAge.toString());
				
				Object zScoreWeightSize=invoker.invokeFunction("calculateWeightSize",pesoMenor,tallaMenor,edadAproximadaCalculadaOnly,paciente.getPac_Sexo());
				System.out.println("Dato zScoreWeightSize: "+zScoreWeightSize.toString());
				
				if((zScoreSizeAgeCalculate.toString()!=null)&&(!zScoreSizeAgeCalculate.toString().equals(""))&&(!zScoreSizeAgeCalculate.toString().equals("No calculada"))){
					try{
						String[] valComp=zScoreSizeAgeCalculate.toString().split(" - ");
						System.out.println("val:"+valComp[0]);
						menor.setMnor_Zstalla_Edad(Double.valueOf(valComp[0]));
					}catch(Exception g){
						menor.setMnor_Zstalla_Edad((double) 0);
					}
				}else{
					menor.setMnor_Zstalla_Edad((double) 0);
				}
				
				if((zScoreWeightAge.toString()!=null)&&(!zScoreWeightAge.toString().equals(""))&&(!zScoreWeightAge.toString().equals("No calculada"))){
					try{
						String[] valComp=zScoreWeightAge.toString().split(" - ");
						System.out.println("val:"+valComp[0]);
						menor.setMnor_Zspeso_Edad(Double.valueOf(valComp[0]));
					}catch(Exception g){
						menor.setMnor_Zspeso_Edad((double) 0);
					}
				}else{
					menor.setMnor_Zspeso_Edad((double) 0);
				}
				
				if((zScoreAgeIMCCalculate.toString()!=null)&&(!zScoreAgeIMCCalculate.toString().equals(""))&&(!zScoreAgeIMCCalculate.toString().equals("No calculada"))){
					try{
						String[] valComp=zScoreAgeIMCCalculate.toString().split(" - ");
						System.out.println("val:"+valComp[0]);
						menor.setMnor_Zsimc_Edad(Double.valueOf(valComp[0]));
					}catch(Exception g){
						menor.setMnor_Zsimc_Edad((double) 0);
					}
				}else{
					menor.setMnor_Zsimc_Edad((double) 0);
				}
				
				if((zScoreWeightSize.toString()!=null)&&(!zScoreWeightSize.toString().equals(""))&&(!zScoreWeightSize.toString().equals("No calculada"))){
					try{
						String[] valComp=zScoreWeightSize.toString().split(" - ");
						System.out.println("val:"+valComp[0]);
						menor.setMnor_Zspeso_Talla(Double.valueOf(valComp[0]));
					}catch(Exception g){
						menor.setMnor_Zspeso_Talla((double) 0);
					}
				}else{
					menor.setMnor_Zspeso_Talla((double) 0);
				}
				
				if((zScoreHeadAge.toString()!=null)&&(!zScoreHeadAge.toString().equals(""))&&(!zScoreHeadAge.toString().equals("No calculada"))){
					try{
						String[] valComp=zScoreHeadAge.toString().split(" - ");
						System.out.println("val:"+valComp[0]);
						menor.setMnor_Zspcefalico_Edad(Double.valueOf(valComp[0]));
					}catch(Exception g){
						menor.setMnor_Zspcefalico_Edad((double) 0);
					}
				}else{
					menor.setMnor_Zspcefalico_Edad((double) 0);
				}
				
				
				okCalcula=true;
			}
		}catch(Exception er){
			er.printStackTrace();
			okCalcula=false;
		}
		finally{
			return okCalcula;
		}
	}
	
	@SuppressWarnings("finally")
	private String limitesMenores(){
		String mensajeError="";
		try{
			String tallaMenor=String.valueOf(menor.getMnor_Talla());
			tallaMenor=tallaMenor.replace(",", ".");
			String pesoMenor=String.valueOf(menor.getMnor_Peso());
			pesoMenor=pesoMenor.replace(",", ".");
			String perimetroCefalico=String.valueOf(menor.getMnor_Perimetro_Cefalico());
			perimetroCefalico=perimetroCefalico.replace(",", ".");
			String circunferenciaBrazo=String.valueOf(menor.getMnor_Brazo());
			circunferenciaBrazo=circunferenciaBrazo.replace(",", ".");
			
			if(tallaMenor.equals(""))
				tallaMenor="0";
			if(pesoMenor.equals(""))
				pesoMenor="0";
			if(perimetroCefalico.equals(""))
				perimetroCefalico="0";
			if(circunferenciaBrazo.equals(""))
				circunferenciaBrazo="0";
			
			if((Double.parseDouble(pesoMenor)<0.9)||(Double.parseDouble(pesoMenor)>58)){
				mensajeError="El peso del menor esta fuera de los límites establecidos";
			}
			
			if((Double.parseDouble(tallaMenor)<38)||(Double.parseDouble(tallaMenor)>150)){
				mensajeError="La talla del menor esta fuera de los límites establecidos";
			}
			
		}catch(Exception e){
			e.printStackTrace();
			mensajeError=e.toString();
		}finally{
			return mensajeError;
		}
	}

}
