package cx.sap.panda.epcservice.repository;

import cx.sap.panda.epcservice.entity.Env;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EnvRepository extends CrudRepository<Env, Integer> {

    Optional<Env> findEnvByEnvCode(String envCode);

    void deleteEnvByEnvCode(String envCode);
}
