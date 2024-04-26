
package acme.features.administrator.spam;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.spam.Spam;

@Repository
public interface AdministratorSpamRepository extends AbstractRepository {

	@Query("select s from Spam s")
	Collection<Spam> findAllSpams();

	@Query("select s from Spam s where s.id = :id")
	Spam findOneSpamById(int id);

	@Query("select s from Spam s where s.spanishWord = :word or s.englishWord = :word")
	Spam findOneSpamByWord(String word);

	@Query("select s.spanishWord, s.englishWord from Spam s")
	Collection<String> findAllWords();
}
