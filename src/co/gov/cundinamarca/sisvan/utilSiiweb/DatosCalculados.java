package co.gov.cundinamarca.sisvan.utilSiiweb;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class DatosCalculados {
	
	public DatosCalculados() {
	}
	
	public String calculaEdadExacta(Date fechaNacimimiento){
		LocalDate fechaNac = fechaNacimimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate ahora = LocalDate.now();

		Period periodo = Period.between(fechaNac, ahora);
		String edadReturn=periodo.getYears()+" Años, "+periodo.getMonths()+" Meses y "+periodo.getDays()+" Días";
		return edadReturn;
	}
	
	public String calulaEdadAproximada(Date fechaNacimimiento){
		String edadReturn="";
		LocalDate fechaNac = fechaNacimimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate ahora = LocalDate.now();

		Period periodo = Period.between(fechaNac, ahora);
		int meses=0;
		meses=periodo.getYears()*12;
		meses=meses+periodo.getMonths();
		if(periodo.getDays()>=21){
			meses=meses+1;
			edadReturn=String.valueOf(meses)+" Meses";
		}else if(periodo.getDays()>=11){
			edadReturn=String.valueOf(meses)+",5 Meses";
		}else{
			edadReturn=String.valueOf(meses)+" Meses";
		}
		return edadReturn;
	}
	
	public String calulaEdadAproximadaOnlyNumber(Date fechaNacimimiento){
		String edadReturn="";
		LocalDate fechaNac = fechaNacimimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate ahora = LocalDate.now();

		Period periodo = Period.between(fechaNac, ahora);
		int meses=0;
		meses=periodo.getYears()*12;
		meses=meses+periodo.getMonths();
		if(periodo.getDays()>=21){
			meses=meses+1;
			edadReturn=String.valueOf(meses);
		}else if(periodo.getDays()>=11){
			edadReturn=String.valueOf(meses)+".5";
		}else{
			edadReturn=String.valueOf(meses);
		}
		return edadReturn;
	}
	
	public int calculaEdad(Date fechaNacimimiento){
		LocalDate fechaNac = fechaNacimimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate ahora = LocalDate.now();

		Period periodo = Period.between(fechaNac, ahora);
		int edadReturn=periodo.getYears();
		return edadReturn;
	}
	
	public long calculaDias(Date fecInicial, Date fecFinal){
		long diferenciaEn_ms = (fecFinal.getTime()-fecInicial.getTime());
		long dias = diferenciaEn_ms / (1000 * 60 * 60 * 24);
		return dias;
	}
	
	public String edadAproximadaGrafica(Date fechaNacimimiento, Date fechaConsulta){
		String edadReturn="";
		LocalDate fechaNac = fechaNacimimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate consulta = fechaConsulta.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		Period periodo = Period.between(fechaNac, consulta);
		int meses=0;
		meses=periodo.getYears()*12;
		meses=meses+periodo.getMonths();
		if(periodo.getDays()>=21){
			meses=meses+1;
			edadReturn=String.valueOf(meses);
		}else if(periodo.getDays()>=11){
			edadReturn=String.valueOf(meses)+".5";
		}else{
			edadReturn=String.valueOf(meses);
		}
		return edadReturn;
	}
	
	public String calculaIMC(Double peso, Double talla){
		String IMC="0";
		if((peso>0)&&(talla>0)){
			Double tallaPaciente=(talla/100);
			Double imc=(peso/(tallaPaciente*tallaPaciente));
			IMC=imc.toString();
		}
		
		return IMC;
	}
}

