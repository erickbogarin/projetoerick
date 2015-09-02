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

public class Avaliacao {
	
	static ImagemBean imagem;
	
	public static void avaliacao(Proposta proposta) throws Exception{
		
		Document doc = new Document(PageSize.A4, 72, 72, 0, 72);

		// Criando o arquivo de saída.
		OutputStream os = new FileOutputStream("out.pdf");

		// Associando o doc ao arquivo de saída.
		PdfWriter.getInstance(doc, os);

		// Abrindo o documento para a edição
		doc.open();

		Image img = Image.getInstance("C:/Users/Erick/Desktop/logo_avaliacao.png");
		img.scaleAbsolute(593f, 145f);
		img.setAlignment(Element.ALIGN_CENTER);
		doc.add(img);
		
		Font font1 = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD);
		Font font2 = new Font(Font.FontFamily.TIMES_ROMAN, 11);
		
		Paragraph p = new Paragraph();

		p.add(new Phrase("Dados de Identificação", font1));

		p.setAlignment(Element.ALIGN_JUSTIFIED);
		p.setSpacingBefore(10f);
		doc.add(p);
		
		Paragraph p1 = new Paragraph();

		p1.add(new Phrase("Nome do Aluno(a): ", font1));
		p1.add(new Phrase(proposta.getAluno().getNome(), font2));

		p1.setAlignment(Element.ALIGN_JUSTIFIED);
		p1.setSpacingBefore(10f);
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
		
		Image ava1 = Image.getInstance("C:/Users/Erick/Desktop/av1.png");
		ava1.scaleAbsolute(625f, 165f);
		ava1.setAlignment(Element.ALIGN_CENTER);
		
		doc.add(ava1);
		
		Image ava2 = Image.getInstance("C:/Users/Erick/Desktop/av2.png");
		ava2.scaleAbsolute(634f, 108f);
		ava2.setAlignment(Element.ALIGN_CENTER);
		
		doc.add(ava2);
		
		Paragraph p4 = new Paragraph();

		p4.add(new Phrase("Data Avaliação: _____/_______________/________", font2));
		
		p4.setAlignment(Element.ALIGN_LEFT);
		p4.setSpacingBefore(30);
		p4.setSpacingAfter(20);
		
		doc.add(p4);
		
		Image sign = Image.getInstance("C:/Users/Erick/Desktop/av_assinatura.png");
		sign.scaleAbsolute(634f, 90f);
		sign.setAlignment(Element.ALIGN_CENTER);
		
		doc.add(sign);
		
		Font font4 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD, new GrayColor(0.50f));
		
		Paragraph p5 = new Paragraph();
		
		p5.add(new Phrase("APROVADO PELA RESOLUÇÃO CONSUP N° 02 DE 03/04/2012", font4));

		p5.setAlignment(Element.ALIGN_RIGHT);
		
		p5.setSpacingBefore(20);
		
		doc.add(p5);
		
		doc.close();
	}
	
}
