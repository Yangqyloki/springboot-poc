package cx.sap.panda.epcservice.rabbitmq;

import com.alibaba.druid.util.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import cx.sap.panda.epcservice.entity.HeadersData;
import cx.sap.panda.epcservice.multitenant.TenantContext;
import cx.sap.panda.epcservice.repository.EnvRepository;
import cx.sap.panda.epcservice.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Component
public class KymaConsumer {

    @Autowired
    private EnvRepository envRepository;

    @Autowired
    private TenantRepository tenantRepository;

    private CountDownLatch latch = new CountDownLatch(1);

    private ObjectMapper mapper = new ObjectMapper();

    public void receiveMessage(final byte[] message) {
        try {
            latch.await(10000, TimeUnit.MILLISECONDS);
            System.out.println("Received (String) " + new String(message));
            final HeadersData headersData = mapper.readValue(new String(message), HeadersData.class);
            TenantContext.setCurrentTenant(headersData.getTenantId().toString());

            System.out.println(mapper.writeValueAsString(headersData.getTenantId()));

            if (StringUtils.isEmpty(headersData.getEnvironmentId())) {
                System.out.println("Call kyma with lm: " + tenantRepository.findTenantByTenantCode(headersData.getTenantId()).get().getKymalm());
            }
            else{
                System.out.println("Call kyma with lm: " + envRepository.findEnvByEnvCode(headersData.getEnvironmentId()).get().getKymalm());
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
