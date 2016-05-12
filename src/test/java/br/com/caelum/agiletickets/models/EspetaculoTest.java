package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import junit.framework.Assert;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

public class EspetaculoTest {

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}

	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	
	/**
	 * cenários possíveis para criar sessoes:  
	 * mesmo dia inicio e fim periodicidade diaria
	 * mesmo dia inicio e fim periodicidade semanal
	 * dias diferentes inicio e fim diaria
	 * dias diferentes inicio e fim semanal
	 * data inicio > data fim 
	 * 
	 * não deveria criar sessao em horários data/hora que possua outra sessao
	 */
	@Test
	public void deveriaCriarApenasUmaSessaoQuandoDatasForemIguais(){
		Espetaculo espetaculo = new Espetaculo();
		LocalDate hoje = new LocalDate();
		LocalTime agora = new LocalTime();
		List<Sessao> sessoes = espetaculo.criaSessoes(hoje,hoje, agora, Periodicidade.DIARIA);
		Assert.assertEquals(1, sessoes.size());
		Assert.assertEquals(espetaculo, sessoes.get(0).getEspetaculo());
		Assert.assertEquals(hoje.toDateTime(agora), sessoes.get(0).getInicio());
	}
	@Test
	public void deveriaCriar6SessoesQuandoDiferencaDaDataFimEDatasInicioForIgual5EaForPeriocidadeDiaria(){
		Espetaculo espetaculo = new Espetaculo();
		LocalDate inicio = new LocalDate();
		LocalDate fim = inicio.plusDays(5);
		int numeroSessoes = 6;
		LocalTime agora = new LocalTime();
		
		
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio,fim, agora, Periodicidade.DIARIA);
		Assert.assertEquals(numeroSessoes, sessoes.size());
		for(Sessao s : sessoes){
			Assert.assertEquals(espetaculo, s.getEspetaculo());
		}	
		//Assert.assertEquals(hoje.toDateTime(agora), sessoes.get(0).getInicio());
	}
	
	@Test
	public void deveriaCriar3SessoesQuandoDiferencaDaDataFimEDatasInicioForIgual14EmenorQue21EaForPeriocidadeSemanal(){
		Espetaculo espetaculo = new Espetaculo();
		LocalDate inicio = new LocalDate();
		LocalDate fim = inicio.plusWeeks(3);
				
		int numeroSessoes = 3;
		LocalTime agora = new LocalTime();
		
		
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio,fim, agora, Periodicidade.SEMANAL);
		Assert.assertEquals(numeroSessoes, sessoes.size());
		
		for(Sessao s : sessoes){
			Assert.assertEquals(espetaculo, s.getEspetaculo());
		}	
		//Assert.assertEquals(hoje.toDateTime(agora), sessoes.get(0).getInicio());
	}
}
