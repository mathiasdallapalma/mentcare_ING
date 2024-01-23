package mentcare;

import mentcare.models.Evaluation;
import mentcare.models.Patient;
import mentcare.models.Prescription;
import mentcare.models.Visit;
import mentcare.repositories.EvaluationRepository;
import mentcare.repositories.PatientRepository;
import mentcare.repositories.PrescriptionRepository;
import mentcare.repositories.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MentCLR implements CommandLineRunner {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Autowired
    private VisitRepository visitRepository;



    @Override
    public void run(String... args) throws Exception {
        Patient p1 = new Patient("John", "Doe", 70, 170,  "2000-04-20",
                "maschio", "1234567890", "30elode@aley.org", "Via vittoria 2",
                "lattice","BBB");
        patientRepository.save(p1);

        Evaluation e1 = new Evaluation("01/01/2024", 30, "note", "urgenza", p1.getId());
        Evaluation e2 = new Evaluation("02/01/2024", 10, "note", "prenotata", p1.getId());
        Evaluation e3 = new Evaluation("03/01/2024", 45, "note", "prenotata", p1.getId());

        Prescription pr1 = new Prescription("Tachipirina", 10, "note", p1.getId());
        Prescription pr2 = new Prescription("Tachipirina", 20, "note", p1.getId());
        Prescription pr3 = new Prescription("Luxopedrina", 30, "note", p1.getId());

        evaluationRepository.save(e1);
        evaluationRepository.save(e2);
        evaluationRepository.save(e3);
        prescriptionRepository.save(pr1);
        prescriptionRepository.save(pr2);
        prescriptionRepository.save(pr3);

        p1 = new Patient("Jane", "Doe", 60, 160, "2000-04-20",
                "femmina", "1234567890", "30elode@aley.org", "Via vittoria 2",
                "api","CCC");
        patientRepository.save(p1);
        e1 = new Evaluation("05/01/2024", 20, "note", "urgenza", p1.getId());
        e2 = new Evaluation("12/01/2024", 20, "note", "urgenza", p1.getId());
        e3 = new Evaluation("13/01/2024", 50, "note", "prenotata", p1.getId());

        pr1 = new Prescription("Acitofolina", 25, "note", p1.getId());
        pr2 = new Prescription("Epifrilina", 20, "note", p1.getId());
        pr3 = new Prescription("Luxopedrina", 40, "note", p1.getId());

        evaluationRepository.save(e1);
        evaluationRepository.save(e2);
        evaluationRepository.save(e3);
        prescriptionRepository.save(pr1);
        prescriptionRepository.save(pr2);
        prescriptionRepository.save(pr3);

        Visit v1 = new Visit("2/2/24", "10:30", "E-PT", "John Doe");
        Visit v2 = new Visit("2/2/24", "12:30", "E-PT", "Mario Rossi");
        Visit v3 = new Visit("4/3/24", "17:30", "G-P1", "Luigi verdi");
        visitRepository.save(v1);
        visitRepository.save(v2);
        visitRepository.save(v3);

    }
}
