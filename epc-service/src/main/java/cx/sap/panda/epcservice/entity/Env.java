package cx.sap.panda.epcservice.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Env {

    @Id
    private Integer id;

    private String envCode;

    private String kymalm;
}
