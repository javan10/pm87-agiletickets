package br.com.caelum.agiletickets.models;

import java.math.BigDecimal;

public class UtilitariosCalculo {
	
	public static double calculaPorcentagemQuantidadeIngressosRestantes(
			Sessao sessao) {
		return (sessao.getTotalIngressos() - sessao.getIngressosReservados()) / sessao.getTotalIngressos().doubleValue();
	}
	
	public static BigDecimal CalculaPrecoPorcentagem(double PorcentagemIngresso,double porcentagemReajuste, Sessao sessao) {
		BigDecimal preco;
		if(calculaPorcentagemQuantidadeIngressosRestantes(sessao) <= PorcentagemIngresso) { 
			preco = sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(porcentagemReajuste)));
		} else {
			preco = sessao.getPreco();
		}

		return preco;
	}

	public static BigDecimal calculaAjustePelaDuracao(Sessao sessao,BigDecimal preco, double porcentagemAjuste, Integer duracaoMinutos) {
		if(sessao.getDuracaoEmMinutos() > duracaoMinutos){
			preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(porcentagemAjuste)));
		}
		return preco;
	}
}
