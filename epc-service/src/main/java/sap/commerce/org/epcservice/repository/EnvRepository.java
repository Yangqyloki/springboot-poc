package sap.commerce.org.epcservice.repository;

import org.springframework.data.repository.CrudRepository;
import sap.commerce.org.epcservice.entity.Env;

import java.util.Optional;

public interface EnvRepository extends CrudRepository<Env, Integer> {

    Optional<Env> findEnvByEnvCode(String envCode);

    void deleteEnvByEnvCode(String envCode);
}
