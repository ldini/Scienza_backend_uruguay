create table tipo_documentacion
(
    id           int auto_increment
        primary key,
    fecha_delete datetime    null,
    fecha_insert datetime    not null,
    fecha_update datetime    null,
    version      int         not null,
    codigo       varchar(6)  not null,
    descripcion  varchar(50) not null
)
    charset = utf8;

INSERT INTO tipo_documentacion (id, fecha_delete, fecha_insert, fecha_update, version, codigo, descripcion)
VALUES (1, null, now(), null, 0, 'REC', 'Receta');
INSERT INTO tipo_documentacion (id, fecha_delete, fecha_insert, fecha_update, version, codigo, descripcion)
VALUES (2, null, now(), null, 0, 'FORM', 'Formulario');
INSERT INTO tipo_documentacion (id, fecha_delete, fecha_insert, fecha_update, version, codigo, descripcion)
VALUES (3, null, now(), null, 0, 'OTR', 'Otros');


DROP TABLE `documentacion`;

CREATE TABLE `documentacion`
(
    `id`                    int(11)  NOT NULL AUTO_INCREMENT,
    `fecha_delete`          datetime DEFAULT NULL,
    `fecha_insert`          datetime NOT NULL,
    `fecha_update`          datetime DEFAULT NULL,
    `version`               int(11)  NOT NULL,
    `afiliado_id`           int(11)  NOT NULL,
    `tipo_documentacion_id` int(11)  NOT NULL,
    PRIMARY KEY (`id`),
    KEY `FKr85n94oi585ta4krkihba908h` (`afiliado_id`),
    KEY `FKedut1yufdy0kgcq6tqy5gvqhp` (`tipo_documentacion_id`),
    CONSTRAINT `FKedut1yufdy0kgcq6tqy5gvqhp` FOREIGN KEY (`tipo_documentacion_id`) REFERENCES `tipo_documentacion` (`id`),
    CONSTRAINT `FKr85n94oi585ta4krkihba908h` FOREIGN KEY (`afiliado_id`) REFERENCES `afiliado` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 57
  DEFAULT CHARSET = utf8;

CREATE TABLE `tramite`
(
    `id`           int(11)      NOT NULL AUTO_INCREMENT,
    `fecha_delete` datetime DEFAULT NULL,
    `fecha_insert` datetime     NOT NULL,
    `fecha_update` datetime DEFAULT NULL,
    `version`      int(11)      NOT NULL,
    `descripcion`  varchar(500) NOT NULL,
    `afiliado_id`  int(11)      NOT NULL,
    PRIMARY KEY (`id`),
    KEY `FK2fp8av8mlghk5i1slw77xyx4x` (`afiliado_id`),
    CONSTRAINT `FK2fp8av8mlghk5i1slw77xyx4x` FOREIGN KEY (`afiliado_id`) REFERENCES `afiliado` (`id`)
)
    CHARSET = utf8;

CREATE TABLE `tramite_archivo`
(
    `id`           int(11)      NOT NULL AUTO_INCREMENT,
    `fecha_delete` datetime DEFAULT NULL,
    `fecha_insert` datetime     NOT NULL,
    `fecha_update` datetime DEFAULT NULL,
    `version`      int(11)      NOT NULL,
    `path`         varchar(100) NOT NULL,
    `tramite_id`   int(11)      NOT NULL,
    PRIMARY KEY (`id`),
    KEY `FKacyt3gybc1p1igdcaf4smfgic` (`tramite_id`),
    CONSTRAINT `FKacyt3gybc1p1igdcaf4smfgic` FOREIGN KEY (`tramite_id`) REFERENCES `tramite` (`id`)
)
    CHARSET = utf8;

CREATE TABLE `documentacion_archivo`
(
    `id`               int(11)      NOT NULL AUTO_INCREMENT,
    `fecha_delete`     datetime DEFAULT NULL,
    `fecha_insert`     datetime     NOT NULL,
    `fecha_update`     datetime DEFAULT NULL,
    `version`          int(11)      NOT NULL,
    `path`             varchar(100) NOT NULL,
    `documentacion_id` int(11)      NOT NULL,
    PRIMARY KEY (`id`),
    KEY `FK415c7uqgjt7w6wkl75sp3v6lt` (`documentacion_id`),
    CONSTRAINT `FK415c7uqgjt7w6wkl75sp3v6lt` FOREIGN KEY (`documentacion_id`) REFERENCES `documentacion` (`id`)
)
    CHARSET = utf8
