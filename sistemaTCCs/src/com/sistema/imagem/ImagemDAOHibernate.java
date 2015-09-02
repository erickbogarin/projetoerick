package com.sistema.imagem;

import org.hibernate.Session;
import org.hibernate.criterion.Projections;

public class ImagemDAOHibernate implements ImagemDAO {

	private Session sessao;

	public Session getSessao() {
		return sessao;
	}

	public void setSessao(Session sessao) {
		this.sessao = sessao;
	}

	@Override
	public byte[] acompanhamentoLogo() {
		return (byte[]) sessao
				.createCriteria(Imagem.class, "i")
				.setProjection(
						Projections.projectionList().add(
								Projections.property("i.acompanhamentoLogo")
										.as("acompanhamentoLogo")))
				.uniqueResult();
	}

	@Override
	public byte[] cadastroLogo() {
		return (byte[]) sessao
				.createCriteria(Imagem.class, "i")
				.setProjection(
						Projections.projectionList().add(
								Projections.property("i.cadastroLogo").as(
										"cadastroLogo"))).uniqueResult();
	}

	@Override
	public byte[] cadastroRodape() {
		return (byte[]) sessao
				.createCriteria(Imagem.class, "i")
				.setProjection(
						Projections.projectionList().add(
								Projections.property("i.cadastroRodape").as(
										"cadastroRodape"))).uniqueResult();
	}

	@Override
	public byte[] ataAssinaturas() {
		return (byte[]) sessao
				.createCriteria(Imagem.class, "i")
				.setProjection(
						Projections.projectionList().add(
								Projections.property("i.ataAssinaturas").as(
										"ataAssinaturas"))).uniqueResult();
	}

	@Override
	public byte[] ataLogo() {
		return (byte[]) sessao
				.createCriteria(Imagem.class, "i")
				.setProjection(
						Projections.projectionList()
								.add(Projections.property("i.ataLogo").as(
										"ataLogo"))).uniqueResult();
	}

	@Override
	public byte[] ataNotas() {
		return (byte[]) sessao
				.createCriteria(Imagem.class, "i")
				.setProjection(
						Projections.projectionList().add(
								Projections.property("i.ataNotas").as(
										"ataNotas"))).uniqueResult();
	}

	@Override
	public byte[] ataRodape() {
		return (byte[]) sessao
				.createCriteria(Imagem.class, "i")
				.setProjection(
						Projections.projectionList().add(
								Projections.property("i.ataRodape").as(
										"ataRodape"))).uniqueResult();
	}

	@Override
	public byte[] avaliacaoEscrito() {
		return (byte[]) sessao
				.createCriteria(Imagem.class, "i")
				.setProjection(
						Projections.projectionList().add(
								Projections.property("i.avaliacaoEscrito").as(
										"avaliacaoEscrito"))).uniqueResult();
	}

	@Override
	public byte[] avaliacaoDefesa() {
		return (byte[]) sessao
				.createCriteria(Imagem.class, "i")
				.setProjection(
						Projections.projectionList().add(
								Projections.property("i.avaliacaoDefesa").as(
										"avaliacaoDefesa"))).uniqueResult();
	}

	@Override
	public byte[] avaliacaoLogo() {
		return (byte[]) sessao
				.createCriteria(Imagem.class, "i")
				.setProjection(
						Projections.projectionList().add(
								Projections.property("i.avaliacaoLogo").as(
										"avaliacaoLogo"))).uniqueResult();
	}

	@Override
	public byte[] avaliacaoAssinatura() {
		return (byte[]) sessao
				.createCriteria(Imagem.class, "i")
				.setProjection(
						Projections.projectionList().add(
								Projections.property("i.avaliacaoAssinatura").as(
										"avaliacaoAssinatura"))).uniqueResult();
	}

}
