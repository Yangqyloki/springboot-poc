package sap.commerce.org.epcservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.web.bind.annotation.*;
import sap.commerce.org.epcservice.entity.Env;
import sap.commerce.org.epcservice.entity.HeadersData;
import sap.commerce.org.epcservice.entity.Tenant;
import sap.commerce.org.epcservice.repository.EnvRepository;

import javax.sql.DataSource;
import java.util.Optional;

@RestController
@RequestMapping(value = "/env")
public class EnvController  {

    @Autowired
    private EnvRepository envRepository;

    @PostMapping
    public @ResponseBody
    String addNewTenant(
            @RequestParam String tenantId,
            @RequestParam Integer id,
            @RequestParam String envid,
            @RequestParam String kymaLm) {

        Env env = new Env();
        env.setId(id);
        env.setEnvCode(envid);
        env.setKymalm(kymaLm);

        envRepository.save(env);
        return "saved";
    }

    @GetMapping(value = "/{envCode}")
    public @ResponseBody
    Optional<Env> getTenant(
            @RequestParam String tenantId,
            @PathVariable String envCode) {
        return envRepository.findEnvByEnvCode(envCode);
    }

    @PutMapping(value = "/{envCode}")
    public @ResponseBody
    Env updateTenant(
            @RequestParam String tenantId,
            @PathVariable String envCode,
            @RequestParam String kymalm) {
        Optional<Env> optionalEnv = envRepository.findEnvByEnvCode(envCode);
        if (optionalEnv.isPresent()) {
            Env env = optionalEnv.get();
            env.setKymalm(kymalm);
            return envRepository.save(env);
        }
        return null;
    }

    @DeleteMapping(value = "/{envCode}")
    public @ResponseBody
    void deleteTenant(
            @RequestParam String tenantId,
            @PathVariable String envCode) {
        envRepository.deleteEnvByEnvCode(envCode);
    }
}
