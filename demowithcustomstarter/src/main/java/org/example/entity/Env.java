package org.example.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
public class Env {

    @Id
    private Integer id;

    private String envcode;

    private String kymalm;
}
