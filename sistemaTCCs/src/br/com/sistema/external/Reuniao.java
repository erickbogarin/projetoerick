package br.com.sistema.external;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sistema.reuniao.ReuniaoBean;

public class Reuniao {

	public static void reuniao(ReuniaoBean reuniao) throws Exception {

		Document doc = new Document(PageSize.A4, 72, 72, 72, 72);

		// Criando o arquivo de saída.
		OutputStream os = new FileOutputStream("out.pdf");

		// Associando o doc ao arquivo de saída.
		PdfWriter.getInstance(doc, os);

		// Abrindo o documento para a edição
		doc.open();

		Image img = Image.getInstance("C:/Users/Erick/Desktop/logo.png");
		img.scaleAbsolute(450f, 80f);
		img.setAlignment(Element.ALIGN_CENTER);
		doc.add(img);

		Font font1 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

		Paragraph p1 = new Paragraph();

		p1.add(new Phrase("Nome do aluno: ", font1));
		p1.add(new Phrase(reuniao.getPropostaSelecionada().getAluno().getNome()));

		p1.setAlignment(Element.ALIGN_JUSTIFIED);
		p1.setSpacingBefore(25);

		doc.add(p1);

		Paragraph p2 = new Paragraph();

		p2.add(new Phrase("Título do Trabalho: ", font1));
		p2.add(new Phrase(reuniao.getPropostaSelecionada().getTema()));

		p2.setAlignment(Element.ALIGN_JUSTIFIED);
		doc.add(p2);

		Paragraph p3 = new Paragraph();

		p3.add(new Phrase("Nome do Orientador: ", font1));
		p3.add(new Phrase(reuniao.getPropostaSelecionada().getOrientador()
				.getNome()));

		p3.setAlignment(Element.ALIGN_JUSTIFIED);
		doc.add(p3);

		Paragraph p4 = new Paragraph();

		SimpleDateFormat date2 = new SimpleDateFormat("yyyy");

		p4.add(new Phrase("Mês de Acompanhamento: ", font1));
		p4.add(new Phrase(
				reuniao.getQuadroSelecionado().getPeriodo()
						+ " / "
						+ date2.format(reuniao.getQuadroSelecionado()
								.getDataCadastro())));

		p4.setAlignment(Element.ALIGN_JUSTIFIED);
		doc.add(p4);

		Paragraph p5 = new Paragraph();

		p5.add(new Phrase("Avaliação dos resultados alcançados no período: ",
				font1));
		p5.add(new Phrase(reuniao.getQuadroSelecionado()
				.getAvaliacaoResultados()));

		p5.setAlignment(Element.ALIGN_JUSTIFIED);
		doc.add(p5);

		Paragraph p6 = new Paragraph("Acompanhamento das reuniões semanais: ",
				FontFactory.getFont(FontFactory.TIMES_ROMAN, 14));

		p6.setAlignment(Element.ALIGN_JUSTIFIED);

		p6.setSpacingBefore(15);

		doc.add(p6);

		PdfPTable table = new PdfPTable(new float[] { 3, 6, 6, 3, 3 });

		table.setWidthPercentage(100f);
		table.getDefaultCell().setUseAscender(true);
		table.getDefaultCell().setUseDescender(true);

		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");

		PdfPCell dataHeader = new PdfPCell(new Phrase("Data",
				FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD)));
		dataHeader.setHorizontalAlignment(Element.ALIGN_CENTER);

		table.addCell(dataHeader);

		PdfPCell assuntoHeader = new PdfPCell(new Phrase("Assunto Discutido",
				FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD)));
		assuntoHeader.setHorizontalAlignment(Element.ALIGN_CENTER);

		table.addCell(assuntoHeader);

		PdfPCell resultadosHeader = new PdfPCell(new Phrase(
				"Resultados Obtidos", FontFactory.getFont(
						FontFactory.TIMES_ROMAN, 12, Font.BOLD)));
		resultadosHeader.setHorizontalAlignment(Element.ALIGN_CENTER);

		table.addCell(resultadosHeader);

		PdfPCell orientadorHeader = new PdfPCell(new Phrase(
				"Rubrica do orientador", FontFactory.getFont(
						FontFactory.TIMES_ROMAN, 12, Font.BOLD)));
		orientadorHeader.setHorizontalAlignment(Element.ALIGN_CENTER);

		table.addCell(orientadorHeader);

		PdfPCell alunoHeader = new PdfPCell(new Phrase("Rubrica do aluno",
				FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD)));
		alunoHeader.setHorizontalAlignment(Element.ALIGN_CENTER);

		table.addCell(alunoHeader);

		for (com.sistema.reuniao.Reuniao r : reuniao.getPeriodos()) {

			PdfPCell data = new PdfPCell(new Phrase(date.format(r
					.getDataReuniao())));
			data.setHorizontalAlignment(Element.ALIGN_CENTER);

			PdfPCell assunto = new PdfPCell(new Phrase(r.getAssuntoDiscutido()));
			assunto.setHorizontalAlignment(Element.ALIGN_CENTER);

			PdfPCell resultados = new PdfPCell(new Phrase(
					r.getResultadosObtidos()));
			resultados.setHorizontalAlignment(Element.ALIGN_CENTER);

			table.addCell(data);
			table.addCell(assunto);
			table.addCell(resultados);
			table.addCell("     ");
			table.addCell("     ");
		}

		table.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.setSpacingBefore(15);

		doc.add(table);

		Paragraph p7 = new Paragraph(
				"Assinatura do Orientador:_________________________________________",
				FontFactory.getFont(FontFactory.TIMES_ROMAN, 11));

		p7.setAlignment(Element.ALIGN_JUSTIFIED);

		p7.setSpacingBefore(15);

		doc.add(p7);

		doc.close();
	}

}
