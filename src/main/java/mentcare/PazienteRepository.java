package mentcare;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface PazienteRepository extends CrudRepository<Paziente, Long> {

    List<Paziente> findByLastName(String lastName);

    Paziente findById(long id);
}