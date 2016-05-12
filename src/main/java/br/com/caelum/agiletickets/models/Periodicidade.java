package br.com.caelum.agiletickets.models;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.Weeks;

public enum Periodicidade {
	
	DIARIA{
		public Integer calculaQuantidadeSessoes(LocalDate inicio, LocalDate fim){
			return Days.daysBetween(inicio, fim).getDays()+1;	
		}
		public LocalDate somaData(LocalDate data,int Quantidade){
			return data.plusDays(Quantidade);
		}
		
	}, SEMANAL{
		public Integer calculaQuantidadeSessoes(LocalDate inicio, LocalDate fim){
			return Weeks.weeksBetween(inicio, fim).getWeeks()+1;
		}
		public LocalDate somaData(LocalDate data,int Quantidade){
			return data.plusWeeks(Quantidade);
		}
		
	};
	
	
	public Integer calculaQuantidadeSessoes(LocalDate inicio, LocalDate fim){
		return null;
	}
	public LocalDate somaData(LocalDate data,int Quantidade){
		return null;
	}
	
}
