package br.com.caelum.agiletickets.models;

import java.math.BigDecimal;


public enum TipoDeEspetaculo {
	
	
	
	CINEMA{
		public BigDecimal calculaPreco(Sessao sessao){
			return CalculaPrecoPorcentagem(CINCO_PORCENTO, DEZ_PORCENTO,sessao);
		}
	}
	, SHOW{
		public BigDecimal calculaPreco(Sessao sessao){
			return CalculaPrecoPorcentagem(CINCO_PORCENTO, DEZ_PORCENTO,sessao);
		}
	}, 
	TEATRO, 
	BALLET{
		public BigDecimal calculaPreco(Sessao sessao){
			BigDecimal valorPrecoPorcentagem = CalculaPrecoPorcentagem(CINQUENTA_PORCENTO, VINTE_PORCENTO,sessao);
			return calculaAjustePelaDuracao(sessao, valorPrecoPorcentagem,DEZ_PORCENTO);
		}
	}, 
	ORQUESTRA{
		
		public BigDecimal calculaPreco(Sessao sessao){
			BigDecimal valorPrecoPorcentagem = CalculaPrecoPorcentagem(CINQUENTA_PORCENTO, VINTE_PORCENTO,sessao);
			
			return calculaAjustePelaDuracao(sessao, valorPrecoPorcentagem,DEZ_PORCENTO);
		}
		
	};
	

	

	private static final double VINTE_PORCENTO = 0.20;
	private static final double DEZ_PORCENTO = 0.10;
	private static final double CINCO_PORCENTO = 0.05;
	private static final double CINQUENTA_PORCENTO = 0.50;
	private static final double UMA_HORA_EM_MINUTOS = 60;
	
	public BigDecimal calculaPreco(Sessao sessao){
		return sessao.getPreco();
	}
	
	private static double calculaPorcentagemQuantidadeIngressosRestantes(
			Sessao sessao) {
		return (sessao.getTotalIngressos() - sessao.getIngressosReservados()) / sessao.getTotalIngressos().doubleValue();
	}
	
	private static BigDecimal CalculaPrecoPorcentagem(double PorcentagemIngresso,double porcentagemReajuste, Sessao sessao) {
		BigDecimal preco;
		if(calculaPorcentagemQuantidadeIngressosRestantes(sessao) <= PorcentagemIngresso) { 
			preco = sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(porcentagemReajuste)));
		} else {
			preco = sessao.getPreco();
		}

		return preco;
	}

	private static BigDecimal calculaAjustePelaDuracao(Sessao sessao,BigDecimal preco, double porcentagemAjuste) {
		if(sessao.getDuracaoEmMinutos() > UMA_HORA_EM_MINUTOS){
			preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(porcentagemAjuste)));
		}
		return preco;
	}
}
