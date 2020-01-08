package cx.sap.panda.multitenant;

import cx.sap.panda.data.TenantContext;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver {
    @Override
    public String resolveCurrentTenantIdentifier() {
        String tenantId = TenantContext.getCurrentTenant();
        if (tenantId != null) {
            return tenantId;
        }
        return "tenant2";
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
