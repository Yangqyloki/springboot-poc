package sap.commerce.org.epcservice.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HeadersData {

    private String user;
    private String role;
    private String tenantId;
    private String environmentId;

    private HeadersData(final Builder builder) {
        user = builder.user;
        role = builder.role;
        tenantId = builder.tenantId;
        environmentId = builder.environmentId;
    }

    public String getUser() {
        return user;
    }

    public String getRole() {
        return role;
    }

    public String getTenantId() {
        return tenantId;
    }

    public String getEnvironmentId() {
        return environmentId;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "HeadersData{" +
                "user='" + user + '\'' +
                ", role='" + role + '\'' +
                ", tenantId='" + tenantId + '\'' +
                ", environmentId='" + environmentId + '\'' +
                '}';
    }

    public static final class Builder {
        private String user;
        private String role;
        private String tenantId;
        private String environmentId;

        private Builder() {
        }

        public Builder withUser(final String user) {
            this.user = user;
            return this;
        }

        public Builder withRole(final String role) {
            this.role = role;
            return this;
        }

        public Builder withTenantId(final String tenantId) {
            this.tenantId = tenantId;
            return this;
        }

        public Builder withEnvironmentId(final String environmentId) {
            this.environmentId = environmentId;
            return this;
        }

        public HeadersData build() {
            return new HeadersData(this);
        }
    }
}

