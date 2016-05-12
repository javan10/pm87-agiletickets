package br.com.caelum.agiletickets.models;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.Weeks;

public enum Periodicidade {
	
	DIARIA{
		public Integer calculaQuantidadeSessoes(LocalDate inicio, LocalDate fim){
			return Days.daysBetween(inicio, fim).getDays()+1;	
		}
		
	}, SEMANAL{
		public Integer calculaQuantidadeSessoes(LocalDate inicio, LocalDate fim){
			return Weeks.weeksBetween(inicio, fim).getWeeks()+1;
		}
	};
	
	
	public Integer calculaQuantidadeSessoes(LocalDate inicio, LocalDate fim){
		return null;
	}
	
}
