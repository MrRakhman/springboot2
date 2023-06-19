package kz.bitlab.techorda.sprintboot.repository;

import kz.bitlab.techorda.sprintboot.model.ApplicationRequest;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<ApplicationRequest, Long> {
}
