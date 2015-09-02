package br.com.sistema.external;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sistema.proposta.Proposta;

public class Cadastro {

	public static void geradorPDF(Proposta proposta) throws Exception {

		Document doc = new Document(PageSize.A4, 72, 72, 72, 72);

		// Criando o arquivo de saída.
		OutputStream os = new FileOutputStream("out.pdf");

		// Associando o doc ao arquivo de saída.
		PdfWriter.getInstance(doc, os);

		// Abrindo o documento para a edição
		doc.open();

		Image img = Image.getInstance("C:/Users/Erick/Desktop/TCC.png");
		img.scaleAbsolute(450f, 80f);
		img.setAlignment(Element.ALIGN_CENTER);
		doc.add(img);

		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
		String cadastro = date.format(new Date());

		Paragraph data = new Paragraph("Data: " + cadastro,
				FontFactory.getFont(FontFactory.TIMES_ROMAN, 14));
		data.setAlignment(Element.ALIGN_RIGHT);
		data.setSpacingBefore(25);
		doc.add(data);

		Font f = new Font(FontFamily.TIMES_ROMAN, 18, Font.BOLD);

		Paragraph p1 = new Paragraph("Aluno:", f);
		p1.setAlignment(Element.ALIGN_LEFT);
		p1.setSpacingBefore(25);

		doc.add(p1);

		PdfPTable table = new PdfPTable(2);

		table.setHorizontalAlignment(Element.ALIGN_CENTER);

		PdfPCell cell = new PdfPCell(new Phrase(proposta.getAluno().getNome(),
				FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		PdfPCell cell2 = new PdfPCell(new Phrase("Matrícula: "
				+ proposta.getAluno().getMatricula().toString(),
				FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD)));
		cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell2);

		PdfPCell cell3 = new PdfPCell(new Phrase("E-mail: "
				+ proposta.getAluno().getEmail(), FontFactory.getFont(
				FontFactory.TIMES_ROMAN, 12, Font.BOLD)));
		cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell3);

		PdfPCell cell4 = new PdfPCell(new Phrase("Telefone: "
				+ proposta.getAluno().getCelular().toString(),
				FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD)));
		cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell4);

		table.setSpacingBefore(5);
		table.setWidthPercentage(105.0F);

		doc.add(table);

		Paragraph p2 = new Paragraph("Orientador:", f);
		p2.setAlignment(Element.ALIGN_LEFT);

		doc.add(p2);

		PdfPTable table2 = new PdfPTable(2);

		table2.setHorizontalAlignment(Element.ALIGN_CENTER);

		PdfPCell cell5 = new PdfPCell(new Phrase(proposta.getOrientador()
				.getNome(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 12,
				Font.BOLD)));
		cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell5);

		PdfPCell cell6 = new PdfPCell(new Phrase("Título: "
				+ proposta.getOrientador().getTitulo(), FontFactory.getFont(
				FontFactory.TIMES_ROMAN, 12, Font.BOLD)));
		cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell6);

		PdfPCell cell7 = new PdfPCell(new Phrase("E-mail: "
				+ proposta.getOrientador().getEmail(), FontFactory.getFont(
				FontFactory.TIMES_ROMAN, 12, Font.BOLD)));
		cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell7);

		PdfPCell cell8 = new PdfPCell(new Phrase("Telefone: "
				+ proposta.getOrientador().getTelefone(), FontFactory.getFont(
				FontFactory.TIMES_ROMAN, 12, Font.BOLD)));
		cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell8);

		table2.setSpacingBefore(5);
		table2.setWidthPercentage(105.0F);

		doc.add(table2);

		Paragraph p3 = new Paragraph("Co-Orientador:", f);
		p2.setAlignment(Element.ALIGN_LEFT);

		doc.add(p3);

		if (proposta.getCoOrientador() != null) {

			PdfPTable table3 = new PdfPTable(2);
			
			table3.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			PdfPCell nome = new PdfPCell(new Phrase(proposta.getCoOrientador()
					.getNome(), FontFactory.getFont(FontFactory.TIMES_ROMAN,
					12, Font.BOLD)));
			nome.setHorizontalAlignment(Element.ALIGN_CENTER);
			table3.addCell(nome);

			PdfPCell titulo = new PdfPCell(
					new Phrase("Título: "
							+ proposta.getCoOrientador().getTitulo(),
							FontFactory.getFont(FontFactory.TIMES_ROMAN, 12,
									Font.BOLD)));
			titulo.setHorizontalAlignment(Element.ALIGN_CENTER);
			table3.addCell(titulo);

			PdfPCell email = new PdfPCell(
					new Phrase("E-mail: "
							+ proposta.getCoOrientador().getEmail(),
							FontFactory.getFont(FontFactory.TIMES_ROMAN, 12,
									Font.BOLD)));
			email.setHorizontalAlignment(Element.ALIGN_CENTER);
			table3.addCell(email);

			PdfPCell telefone = new PdfPCell(
					new Phrase("Telefone: "
							+ proposta.getCoOrientador().getTelefone(),
							FontFactory.getFont(FontFactory.TIMES_ROMAN, 12,
									Font.BOLD)));
			telefone.setHorizontalAlignment(Element.ALIGN_CENTER);
			table3.addCell(telefone);

			table3.setHorizontalAlignment(Element.ALIGN_CENTER);

			table3.setSpacingBefore(5);
			table3.setWidthPercentage(105.0F);

			doc.add(table3);

		} else {
			Paragraph nulo = new Paragraph("Nenhum ", FontFactory.getFont(
					FontFactory.TIMES_ROMAN, 14));

			data.setAlignment(Element.ALIGN_LEFT);
			data.setSpacingBefore(10);

			doc.add(nulo);
		}

		Paragraph p4 = new Paragraph("Área de Concentração: ", f);
		p4.setAlignment(Element.ALIGN_LEFT);

		doc.add(p4);

		PdfPTable table4 = new PdfPTable(1);

		table4.setHorizontalAlignment(Element.ALIGN_CENTER);

		PdfPCell cell9 = new PdfPCell(new Phrase(
				proposta.getAreaConcentracao(), FontFactory.getFont(
						FontFactory.TIMES_ROMAN, 12, Font.BOLD)));
		cell9.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell9.setPadding(5);
		table4.addCell(cell9);

		table4.setSpacingBefore(15);
		table4.setWidthPercentage(105.0F);

		doc.add(table4);

		Paragraph p5 = new Paragraph("Tema:", f);
		p5.setAlignment(Element.ALIGN_LEFT);

		doc.add(p5);

		PdfPTable table5 = new PdfPTable(1);

		table5.setHorizontalAlignment(Element.ALIGN_CENTER);

		PdfPCell cell10 = new PdfPCell(new Phrase(proposta.getTema(),
				FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD)));
		cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell10.setPadding(5);
		table5.addCell(cell10);

		table5.setSpacingBefore(15);
		table5.setWidthPercentage(105.0F);

		doc.add(table5);

		Paragraph p6 = new Paragraph("Descrição:", f);
		p6.setAlignment(Element.ALIGN_LEFT);

		doc.add(p6);

		PdfPTable table6 = new PdfPTable(1);

		table6.setHorizontalAlignment(Element.ALIGN_CENTER);

		PdfPCell cell11 = new PdfPCell(new Phrase(proposta.getDescricao(),
				FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD)));
		cell11.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell11.setPadding(5);

		table6.addCell(cell11);

		table6.setSpacingBefore(15);
		table6.setWidthPercentage(105.0F);

		doc.add(table6);

		Paragraph p7 = new Paragraph("Resultado Esperados do TCC:", f);
		p7.setAlignment(Element.ALIGN_LEFT);

		doc.add(p7);

		PdfPTable table7 = new PdfPTable(1);

		table7.setHorizontalAlignment(Element.ALIGN_CENTER);

		PdfPCell cell12 = new PdfPCell(new Phrase(
				proposta.getResultadosEsperados(), FontFactory.getFont(
						FontFactory.TIMES_ROMAN, 12, Font.BOLD)));
		cell12.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell12.setPadding(5);

		table7.addCell(cell12);

		table7.setSpacingBefore(15);
		table7.setWidthPercentage(105.0F);

		doc.add(table7);

		Image img2 = Image.getInstance("C:/Users/Erick/Desktop/rodape.png");
		img2.scaleAbsolute(550f, 200f);
		img2.setAlignment(Element.ALIGN_CENTER);
		doc.add(img2);

		doc.close();
	}
}
