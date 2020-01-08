package org.example.repository;

import org.example.entity.Env;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EnvRepository extends CrudRepository<Env, Integer> {

    Optional<Env> findEnvByEnvcode(String envCode);
}
