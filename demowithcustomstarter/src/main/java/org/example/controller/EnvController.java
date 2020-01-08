package org.example.controller;

import cx.sap.panda.service.StarterService;
import org.example.entity.Env;
import org.example.repository.EnvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Optional;

@RestController
@RequestMapping(value = "/env")
public class EnvController {

    @Resource
    private EnvRepository envRepository;

    @GetMapping(value = "/{envCode}")
    public @ResponseBody
    Optional<Env> getTenant(
            @PathVariable String envCode) {

        //String[] splitArray = starterService.split(",");
        //System.out.println(splitArray);

        return envRepository.findEnvByEnvcode(envCode);
    }

}
