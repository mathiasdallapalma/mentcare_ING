package mentcare.repositories;

import mentcare.models.Evaluation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EvaluationRepository extends CrudRepository<Evaluation, Long> {

        List<Evaluation> findByPatient_id(Long patient_id);
        Evaluation findById(long id);
}

