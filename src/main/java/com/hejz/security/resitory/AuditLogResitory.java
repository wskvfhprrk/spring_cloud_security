package com.hejz.security.resitory;

import com.hejz.security.entity.AuditLog;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface AuditLogResitory extends PagingAndSortingRepository<AuditLog, Long> {
}
