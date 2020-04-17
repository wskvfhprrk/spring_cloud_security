package org.example.log;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * @author wskvf
 */
public interface AuditRepository extends JpaSpecificationExecutor<AuditLog>, CrudRepository<AuditLog, Long> {
}
