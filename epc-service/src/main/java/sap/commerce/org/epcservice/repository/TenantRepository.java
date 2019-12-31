package sap.commerce.org.epcservice.repository;

import org.springframework.data.repository.CrudRepository;
import sap.commerce.org.epcservice.entity.Tenant;

import java.util.Optional;

public interface TenantRepository extends CrudRepository<Tenant, Integer> {

    Optional<Tenant> findTenantByTenantCode(String tenantId);
}

