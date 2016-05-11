package br.com.caelum.agiletickets.models;

import java.math.BigDecimal;


public enum TipoDeEspetaculo {
	
	CINEMA{
		public BigDecimal calculaPreco(Sessao sessao){
			return UtilitariosCalculo.CalculaPrecoPorcentagem(CINCO_PORCENTO, DEZ_PORCENTO,sessao);
		}
	}
	, SHOW{
		public BigDecimal calculaPreco(Sessao sessao){
			return UtilitariosCalculo.CalculaPrecoPorcentagem(CINCO_PORCENTO, DEZ_PORCENTO,sessao);
		}
	}, 
	TEATRO, 
	BALLET{
		public BigDecimal calculaPreco(Sessao sessao){
			BigDecimal valorPrecoPorcentagem = UtilitariosCalculo.CalculaPrecoPorcentagem(CINQUENTA_PORCENTO, VINTE_PORCENTO,sessao);
			return UtilitariosCalculo.calculaAjustePelaDuracao(sessao, valorPrecoPorcentagem,DEZ_PORCENTO, UMA_HORA_EM_MINUTOS);
		}
	}, 
	ORQUESTRA{
		
		public BigDecimal calculaPreco(Sessao sessao){
			BigDecimal valorPrecoPorcentagem = UtilitariosCalculo.CalculaPrecoPorcentagem(CINQUENTA_PORCENTO, VINTE_PORCENTO,sessao);
			
			return UtilitariosCalculo.calculaAjustePelaDuracao(sessao, valorPrecoPorcentagem,DEZ_PORCENTO, UMA_HORA_EM_MINUTOS);
		}
		
	};

	private static final double VINTE_PORCENTO = 0.20;
	private static final double DEZ_PORCENTO = 0.10;
	private static final double CINCO_PORCENTO = 0.05;
	private static final double CINQUENTA_PORCENTO = 0.50;
	private static final Integer UMA_HORA_EM_MINUTOS = 60;
	
	public BigDecimal calculaPreco(Sessao sessao){
		return sessao.getPreco();
	}
	
}
