package cx.sap.panda.epcservice.repository;

import cx.sap.panda.epcservice.entity.Tenant;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TenantRepository extends CrudRepository<Tenant, Integer> {

    Optional<Tenant> findTenantByTenantCode(String tenantId);
}

