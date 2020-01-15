package cx.sap.panda.epcservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cx.sap.panda.epcservice.entity.Tenant;
import cx.sap.panda.epcservice.repository.TenantRepository;

import java.util.Optional;

@RestController
@RequestMapping(value = "/tenant")
public class TenantController {

    @Autowired
    private TenantRepository tenantRepository;

    @PostMapping
    public @ResponseBody
    String addNewTenant(
            @RequestParam Integer id,
            @RequestParam String tenantCode,
            @RequestParam String kymaLm) {

        Tenant tenant = new Tenant();
        tenant.setId(id);
        tenant.setTenantCode(tenantCode);
        tenant.setKymalm(kymaLm);

        tenantRepository.save(tenant);
        return "saved";
    }

    @GetMapping(value = "/{tenantCode}")
    public @ResponseBody
    Optional<Tenant> getTenant(
            @PathVariable String tenantCode) {
        return tenantRepository.findTenantByTenantCode(tenantCode);
    }

    @PutMapping(value = "/{tenantCode}")
    public @ResponseBody
    Tenant updateTenant(
            @PathVariable String tenantCode,
            @RequestParam String kymalm) {
        Optional<Tenant> optionalTenant = tenantRepository.findTenantByTenantCode(tenantCode);
        if (optionalTenant.isPresent()) {
            Tenant tenant = optionalTenant.get();
            tenant.setKymalm(kymalm);
            return tenantRepository.save(tenant);
        }
        return null;
    }

    @DeleteMapping(value = "/{tenantCode}")
    public @ResponseBody
    void deleteTenant(
            @RequestParam String tenantId,
            @PathVariable Integer tenantCode) {
        tenantRepository.deleteById(tenantCode);
    }
}
