CREATE SCHEMA `tenant1` ;

CREATE TABLE `tenant1`.`tenant` (
  `id` INT NOT NULL,
  `tenantCode` VARCHAR(45) NULL,
  `kymalm` VARCHAR(100) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);
);

insert into tenant1.env values (1, 'env1', 'cart-service-checkout', 'Kyma: check for cart checkout for tenant2.env1.');
insert into tenant1.env values (2, 'env2', 'cart-service-place-order', 'Kyma: check for cart place order for tenant2.env1.');
