package br.com.sistema.external;

import java.io.FileOutputStream;
import java.io.OutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfWriter;
import com.sistema.imagem.ImagemBean;
import com.sistema.proposta.Proposta;

public class Ata {

	static ImagemBean imagem;
	
	public static void ata(Proposta proposta) throws Exception {
		
		Document doc = new Document(PageSize.A4, 72, 72, 72, 72);
		
		// Criando o arquivo de saída.
		OutputStream os = new FileOutputStream("out.pdf");
		
		// Associando o doc ao arquivo de saída.
		PdfWriter.getInstance(doc, os);

		// Abrindo o documento para a edição
		doc.open();
		
		Image img = Image.getInstance("C:/Users/Erick/Desktop/logo_ata.png");
		img.scaleAbsolute(600f, 120f);
		img.setAlignment(Element.ALIGN_CENTER);
		doc.add(img);
		
		Font font1 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
		Font font2 = new Font(Font.FontFamily.TIMES_ROMAN, 12);
		Font font3 = new Font(Font.FontFamily.TIMES_ROMAN, 11);
		
		Paragraph p1 = new Paragraph();

		p1.add(new Phrase("Nome do Aluno(a): ", font1));
		p1.add(new Phrase(proposta.getAluno().getNome(), font2));

		p1.setAlignment(Element.ALIGN_JUSTIFIED);
		p1.setSpacingAfter(3);
		doc.add(p1);
		
		Paragraph p2 = new Paragraph();

		p2.add(new Phrase("Título do Trabalho: ", font1));
		p2.add(new Phrase(proposta.getTema(), font2));

		p2.setAlignment(Element.ALIGN_JUSTIFIED);

		doc.add(p2);
		
		Paragraph p3 = new Paragraph();

		p3.add(new Phrase("Professor Orientador: ", font1));
		p3.add(new Phrase(proposta.getOrientador().getNome(), font2));

		p3.setAlignment(Element.ALIGN_JUSTIFIED);
		p3.setSpacingAfter(20);
		
		doc.add(p3);
		
		Image notas = Image.getInstance("C:/Users/Erick/Desktop/notas.png");
		notas.scaleAbsolute(550f, 160f);
		notas.setAlignment(Element.ALIGN_CENTER);
		
		doc.add(notas);
		
		Image assinaturas = Image.getInstance("C:/Users/Erick/Desktop/ata_assinaturas.png");
		assinaturas.scaleAbsolute(555f, 102.7f);
		assinaturas.setAlignment(Element.ALIGN_CENTER);
		
		doc.add(assinaturas);
		
		Paragraph p4 = new Paragraph();

		p4.add(new Phrase("Manaus, ______ de ___________ de _______. ", font3));

		p4.setAlignment(Element.ALIGN_RIGHT);
		p4.setSpacingBefore(80);
		
		doc.add(p4);
		
		Font font4 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD, new GrayColor(0.50f));
		
		Paragraph p5 = new Paragraph();
		
		p5.add(new Phrase("APROVADO PELA RESOLUÇÃO CONSUP N° 02 DE 03/04/2012", font4));

		p5.setAlignment(Element.ALIGN_RIGHT);
		
		p5.setSpacingBefore(80);
		
		doc.add(p5);
		
		doc.close();
		
	}
	
}
