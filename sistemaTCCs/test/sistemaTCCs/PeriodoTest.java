package sistemaTCCs;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.sistema.periodo.Periodo;
import com.sistema.usuario.aluno.Aluno;

public class PeriodoTest {
	
	public static void main(String[] args) {
		
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("teste");
		
		EntityManager manager = entityManagerFactory.createEntityManager();
		
		Query query = manager.createQuery("select p from Periodo p join fetch p.alunos where p.id = :pId");
        query.setParameter("pId", 1);
		
		Periodo periodo = (Periodo) query.getSingleResult();
		
		for(Aluno a : periodo.getAlunos()) {
			System.out.println("Nome: " + a.getNome());
		}
	}
	
}
