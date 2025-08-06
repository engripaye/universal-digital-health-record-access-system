package dev.engripaye.universal_digital_health_record_access_system.repository;

import dev.engripaye.universal_digital_health_record_access_system.model.HealthRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface HealthRepository extends MongoRepository<HealthRecord, String> {

    List<HealthRecord> findByPatientId(String patientId);
}