package mentcare.repositories;


import mentcare.models.Patient;
import mentcare.models.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long> {

    Visit findById(long id);
}


