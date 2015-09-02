package br.com.sistema.external;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import com.sistema.banca.Banca;

public class Declaracao {

	public static void declaracao(Banca apresentacao) throws Exception {
		
		Document doc = new Document(PageSize.A4, 72, 72, 200, 72);

		// Criando o arquivo de saída.
		OutputStream os = new FileOutputStream("out.pdf");

		// Associando o doc ao arquivo de saída.
		PdfWriter.getInstance(doc, os);

		// Abrindo o documento para a edição
		doc.open();
		
		Paragraph p1 = new Paragraph();

		Font font1 = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
		
		p1.add(new Phrase("DECLARAÇÃO", font1));

		p1.setAlignment(Element.ALIGN_CENTER);
		p1.setSpacingAfter(30);
		doc.add(p1);
		
		Paragraph p2 = new Paragraph();

		Font font2 = new Font(Font.FontFamily.TIMES_ROMAN, 12);
		
		SimpleDateFormat day = new SimpleDateFormat("dd");
		String dia = day.format(apresentacao.getDataApresentacao());
		
		SimpleDateFormat mounth = new SimpleDateFormat("MM");
		String mes = mounth.format(apresentacao.getDataApresentacao());
		
		SimpleDateFormat year = new SimpleDateFormat("yyyy");
		String ano = year.format(apresentacao.getDataApresentacao());
		
		p2.add(new Phrase("DECLARAMOS para os devidos fins, que o trabalho de conclusão do curso de graduação em "
				+ apresentacao.getProposta().getAluno().getCurso() + 
				", apresentado pelo (a) acadêmico (a) "
				+ apresentacao.getProposta().getAluno().getNome() +
				", intitulado "
				+ apresentacao.getProposta().getTema() +
				", teve sua defesa no dia "
				+ dia +" do " + mes + " de " + ano + ", "
				+ "e contou com a seguinte banca examinadora:", font2));

		p2.setAlignment(Element.ALIGN_JUSTIFIED);
		p2.setSpacingAfter(40);
		
		doc.add(p2);
		
		Font font3 = new Font(Font.FontFamily.TIMES_ROMAN, 12);
		
		Paragraph p3 = new Paragraph();

		p3.add(new Phrase("Orientador: Prof°."+ apresentacao.getProposta().getOrientador().getNome() + ", " +
				apresentacao.getProposta().getOrientador().getTitulo(), font3));

		p3.setAlignment(Element.ALIGN_LEFT);
		
		doc.add(p3);
		
		Paragraph p4 = new Paragraph();

		p4.add(new Phrase("Avaliador 1: Profª."+ apresentacao.getPrimeiroConvidado(), font3));

		p4.setAlignment(Element.ALIGN_LEFT);
		
		doc.add(p4);
		
		Paragraph p5 = new Paragraph();

		p5.add(new Phrase("Avaliador 2: Profª."+ apresentacao.getSegundoConvidado(), font3));

		p5.setAlignment(Element.ALIGN_LEFT);
		
		doc.add(p5);
		
		Paragraph p6 = new Paragraph();

		p6.add(new Phrase("Manaus, __ de ___________ de 20__.", font3));

		p6.setAlignment(Element.ALIGN_RIGHT);
		p6.setSpacingBefore(100);
		
		doc.add(p6);
		
		Paragraph p7 = new Paragraph();

		p7.add(new Phrase("................................................................................", font3));

		p7.setAlignment(Element.ALIGN_RIGHT);
		p7.setSpacingBefore(50);
		
		doc.add(p7);
		
		Font font4 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
		
		Paragraph p8 = new Paragraph();

		p8.add(new Phrase("Marcela Sávia Picanço Pessoa", font4));

		p8.setAlignment(Element.ALIGN_RIGHT);
		p8.setSpacingBefore(5);
		
		doc.add(p8);
		
		Paragraph p9 = new Paragraph();

		p9.add(new Phrase("Coordenadora do Curso de " + apresentacao.getProposta().getAluno().getCurso(), font3));

		p9.setAlignment(Element.ALIGN_RIGHT);
		
		doc.add(p9);
		
		doc.close();
	}
	
}
