create table  compania_celular
(
	id int auto_increment
		primary key,
	fecha_delete datetime null,
	fecha_insert datetime not null,
	fecha_update datetime null,
	version int not null,
	codigo varchar(5) not null,
	descripcion varchar(50) not null
)
charset=latin1;

create table  email
(
	id int auto_increment
		primary key,
	fecha_delete datetime null,
	fecha_insert datetime not null,
	fecha_update datetime null,
	version int not null,
	tipo_email varchar(10) not null,
	subject varchar(100) not null,
	signature varchar(100) null,
	recipient_to varchar(500) not null,
	recipient_cc varchar(500) null,
	recipient_cco varchar(500) null
)
charset=latin1;

create table  permiso
(
	id int auto_increment
		primary key,
	fecha_delete datetime null,
	fecha_insert datetime not null,
	fecha_update datetime null,
	version int not null,
	nombre varchar(255) not null,
	descripcion varchar(255) not null
)
charset=latin1;

create table  pregunta_frecuente
(
	id int auto_increment
		primary key,
	fecha_delete datetime null,
	fecha_insert datetime not null,
	fecha_update datetime null,
	version int not null,
	categoria varchar(200) not null,
	pregunta varchar(200) not null,
	respuesta varchar(500) not null
)
charset=latin1;

create table  rol
(
	id int auto_increment
		primary key,
	fecha_delete datetime null,
	fecha_insert datetime not null,
	fecha_update datetime null,
	version int not null,
	nombre varchar(255) not null,
	descripcion varchar(255) not null
)
charset=latin1;

create table  rol_permiso
(
	rol_id int not null,
	permiso_id int not null,
	primary key (rol_id, permiso_id),
	constraint FK6o522368i97la9m9cqn0gul2e
		foreign key (rol_id) references rol (id),
	constraint FK_qeugj0uoem6m1tafqrqr7ibs0
		foreign key (rol_id) references rol (id),
	constraint FKfyao8wd0o5tsyem1w55s3141k
		foreign key (permiso_id) references permiso (id),
	constraint FK_eata06mq5cod7s3fm1e02wty6
		foreign key (permiso_id) references permiso (id)
)
charset=latin1;

create table  seguro_medico
(
	id int auto_increment
		primary key,
	fecha_delete datetime null,
	fecha_insert datetime not null,
	fecha_update datetime null,
	version int not null,
	sap_id bigint not null,
	sap_descripcion varchar(100) not null,
	descripcion varchar(100) not null
)
charset=latin1;

create table  seguro_medico_permiso
(
	seguro_medico_id int not null,
	permiso_id int not null,
	primary key (seguro_medico_id, permiso_id),
	constraint FK_qr357yyn1nj7honwigqxcmmmn
		foreign key (seguro_medico_id) references seguro_medico (id),
	constraint FKc4dno5m5vebahkjkd2ss4nqhe
		foreign key (permiso_id) references permiso (id),
	constraint FK_cbsjgeg4fxwfhr0plqyhnrn6t
		foreign key (permiso_id) references permiso (id),
	constraint FKxnyajfghf40ex3r1qe9fo21t
		foreign key (seguro_medico_id) references seguro_medico (id)
)
charset=latin1;

create table  tipo_consulta
(
	id int auto_increment
		primary key,
	fecha_delete datetime null,
	fecha_insert datetime not null,
	fecha_update datetime null,
	version int not null,
	codigo varchar(6) not null,
	descripcion varchar(50) not null
)
charset=latin1;

create table  tipo_categoria
(
	id int auto_increment
		primary key,
	fecha_delete datetime null,
	fecha_insert datetime not null,
	fecha_update datetime null,
	version int not null,
	tipo_consulta_id int not null,
	codigo varchar(6) not null,
	descripcion varchar(50) not null,
	constraint FK6sy08bl4b2tvt9sp1gs745bxi
		foreign key (tipo_consulta_id) references tipo_consulta (id),
	constraint FK_dcxa5kjt0vgubksi3qdps9uu5
		foreign key (tipo_consulta_id) references tipo_consulta (id)
)
charset=latin1;

create table  usuario
(
	id int auto_increment
		primary key,
	fecha_delete datetime null,
	fecha_insert datetime not null,
	fecha_update datetime null,
	version int not null,
	username varchar(255) not null,
	password varchar(255) not null,
	enabled bit not null,
	account_non_expired bit not null,
	credentials_non_expired bit not null,
	account_non_locked bit not null,
	rol_id int not null,
	constraint FKshkwj12wg6vkm6iuwhvcfpct8
		foreign key (rol_id) references rol (id),
	constraint FK_9hfl8tdutsxq1r5er252rnssq
		foreign key (rol_id) references rol (id)
)
charset=latin1;

create table  administrador
(
	id int auto_increment
		primary key,
	fecha_delete datetime null,
	fecha_insert datetime not null,
	fecha_update datetime null,
	version int not null,
	nombre varchar(50) not null,
	apellido varchar(50) not null,
	telefono int not null,
	email varchar(50) not null,
	operativo bit default b'1' not null,
	usuario_id int not null,
	constraint FK6wreymkne84tlme1l8mf6smi5
		foreign key (usuario_id) references usuario (id),
	constraint FK_41f19w50uvnqar18j6gr9jepp
		foreign key (usuario_id) references usuario (id)
)
charset=latin1;

create table  afiliado
(
	id int auto_increment
		primary key,
	fecha_delete datetime null,
	fecha_insert datetime not null,
	fecha_update datetime null,
	version int not null,
	sap_id bigint not null,
	nombre varchar(50) not null,
	apellido varchar(50) not null,
	apodo varchar(10) null,
	seguro_medico_id int not null,
	numero_afiliado varchar(50) not null,
	numero_documento int not null,
	sexo varchar(1) not null,
	fecha_nacimiento date not null,
	provincia varchar(100) null,
	localidad varchar(100) null,
	calle varchar(100) null,
	calle_numero int null,
	piso varchar(10) null,
	departamento varchar(10) null,
	codigo_postal varchar(10) null,
	latitud varchar(10) null,
	longitud varchar(10) null,
	telefono_particular varchar(20) null,
	telefono_laboral varchar(20) null,
	telefono_celular varchar(20) null,
	compania_celular_id int null,
	email varchar(50) null,
	verificar_perfil bit not null,
	login_android bit not null,
	login_ios bit not null,
	login_web bit not null,
	usuario_id int null,
	constraint afiliado_unique
		unique (numero_documento, sap_id),
	constraint FKb5xkj9fowcnotwd5cpac31m3t
		foreign key (seguro_medico_id) references seguro_medico (id),
	constraint FK_dx94i1s0cp9fuu6vocj4i3kkq
		foreign key (seguro_medico_id) references seguro_medico (id),
	constraint FKm7ni5bfatifdmk5xpq0fvlvcu
		foreign key (compania_celular_id) references compania_celular (id),
	constraint FK_8xis41r46v1031s1ng29yx45w
		foreign key (compania_celular_id) references compania_celular (id),
	constraint FKpxehvaw206wwr1968akli6mi8
		foreign key (usuario_id) references usuario (id),
	constraint FK_6gnquunlhy8ae2m5tbbg9277c
		foreign key (usuario_id) references usuario (id)
)
charset=latin1;

create table  afiliado_vinculado
(
	afiliado_id int not null,
	afiliado_vinculado_id int not null,
	constraint FK81kk1uvjf7oykqn1hal968njh
		foreign key (afiliado_id) references afiliado (id),
	constraint FK_ma5erddmcjpiymo8kjv6ldap8
		foreign key (afiliado_id) references afiliado (id),
	constraint FKed8b4x68oj66l6h9nrhmm9221
		foreign key (afiliado_vinculado_id) references afiliado (id),
	constraint FK_msoffb0i80q679hxj0ge3fttv
		foreign key (afiliado_vinculado_id) references afiliado (id)
)
charset=latin1;

create table  bandeja_alta_pendiente
(
	id int auto_increment
		primary key,
	fecha_delete datetime null,
	fecha_insert datetime not null,
	fecha_update datetime null,
	version int not null,
	nombre varchar(50) not null,
	apellido varchar(50) not null,
	numero_documento int not null,
	sap_id bigint not null,
	password varchar(255) not null,
	telefono varchar(50) not null,
	email varchar(100) default '' not null,
	tipo_dispositivo varchar(3) not null,
	estado varchar(1) not null,
	fecha_error datetime not null,
	fecha_atencion datetime null,
	administrador_id int null,
	constraint FKl2s7wvoe6xh26n6hu5lka2ip5
		foreign key (administrador_id) references administrador (id),
	constraint FK_e90yks4dnfkf4yeaq0eeedwgl
		foreign key (administrador_id) references administrador (id)
)
charset=latin1;

create table  bandeja_perfil_pendiente
(
	id int auto_increment
		primary key,
	fecha_delete datetime null,
	fecha_insert datetime not null,
	fecha_update datetime null,
	version int not null,
	nombre varchar(50) not null,
	apellido varchar(50) not null,
	apodo varchar(10) null,
	sexo varchar(1) not null,
	fecha_nacimiento date not null,
	provincia varchar(100) null,
	localidad varchar(100) null,
	calle varchar(100) null,
	calle_numero int null,
	piso varchar(10) null,
	departamento varchar(10) null,
	codigo_postal varchar(10) null,
	latitud varchar(10) null,
	longitud varchar(10) null,
	telefono_particular varchar(20) null,
	telefono_laboral varchar(20) null,
	telefono_celular varchar(20) null,
	compania_celular_id int null,
	email varchar(50) null,
	tipo_dispositivo varchar(3) not null,
	estado varchar(1) not null,
	fecha_edicion datetime not null,
	fecha_atencion datetime null,
	administrador_id int null,
	afiliado_id int null,
	constraint FK7mcnnh8dqv5w976hhj7wqlbxn
		foreign key (compania_celular_id) references compania_celular (id),
	constraint FK844v5e7bwclltntjxg9g5j99l
		foreign key (administrador_id) references administrador (id),
	constraint FK_l4uyukqviydvrat7eo1djneji
		foreign key (administrador_id) references administrador (id),
	constraint FK_ni3vgj86779e08v2bagndyjd6
		foreign key (compania_celular_id) references compania_celular (id),
	constraint FKe12inuvds14hnq0sb93lt4vom
		foreign key (afiliado_id) references afiliado (id),
	constraint FK_4wglpsg8aoc9l3pw3539s5gxm
		foreign key (afiliado_id) references afiliado (id)
)
charset=latin1;

create table  consulta
(
	id int auto_increment
		primary key,
	fecha_delete datetime null,
	fecha_insert datetime not null,
	fecha_update datetime null,
	version int not null,
	afiliado_id int not null,
	tipo_categoria_id int not null,
	ticket int default 0 not null,
	canal varchar(10) default '' not null,
	administrador_id int null,
	constraint FKdy9u78iadlra7wmr8c15sy9s8
		foreign key (administrador_id) references administrador (id),
	constraint FKefu3sgd404wf785d3r47i462l
		foreign key (tipo_categoria_id) references tipo_categoria (id),
	constraint FK_jrl6hngbhp1tfh74ojjxtj6gs
		foreign key (tipo_categoria_id) references tipo_categoria (id),
	constraint FKoyhhh0esm6o7cl5l3nvb5acix
		foreign key (afiliado_id) references afiliado (id),
	constraint FK_rfmeju0o0i2cpunilfc3ottql
		foreign key (afiliado_id) references afiliado (id)
)
charset=latin1;

create table  bandeja_consulta_pendiente
(
	id int auto_increment
		primary key,
	fecha_delete datetime null,
	fecha_insert datetime not null,
	fecha_update datetime null,
	version int not null,
	consulta_id int not null,
	comentario varchar(500) null,
	tipo_dispositivo varchar(3) not null,
	estado varchar(1) not null,
	fecha_consulta datetime not null,
	fecha_atencion datetime null,
	administrador_id int null,
	constraint FK5fu53gfx58t0iw6jtdsuagrpx
		foreign key (administrador_id) references administrador (id),
	constraint FK_burintybrq5uf7gp3moakq505
		foreign key (administrador_id) references administrador (id),
	constraint FKamnrsu61r4y9ga2spq6p2ohhf
		foreign key (consulta_id) references consulta (id),
	constraint FK_7doiot3ty5qwwcf0foghii347
		foreign key (consulta_id) references consulta (id)
)
charset=latin1;

create table  consulta_mensaje
(
	id int auto_increment
		primary key,
	fecha_delete datetime null,
	fecha_insert datetime not null,
	fecha_update datetime null,
	version int not null,
	consulta_id int not null,
	afiliado_id int null,
	administrador_id int null,
	mensaje varchar(500) not null,
	leido bit not null,
	fecha_leido datetime null,
	constraint FK14k6g6jnb4hr55f0uxwhh67ab
		foreign key (afiliado_id) references afiliado (id),
	constraint FKexncunmgvjpv6rs9c355ufyfb
		foreign key (administrador_id) references administrador (id),
	constraint FKmnqv90wlstr6l69qmgolrmp69
		foreign key (consulta_id) references consulta (id)
);

create table  mensaje
(
	id int auto_increment
		primary key,
	fecha_delete datetime null,
	fecha_insert datetime not null,
	fecha_update datetime null,
	version int not null,
	administrador_id int null,
	titulo varchar(100) not null,
	mensaje_push varchar(500) not null,
	mensaje_abreviado varchar(500) not null,
	mensaje longtext not null,
	constraint FKs03kho77r38ws64lki9p6rexd
		foreign key (administrador_id) references administrador (id),
	constraint FK_frenvt41l8pyy935lyqn3mv7k
		foreign key (administrador_id) references administrador (id)
)
charset=latin1;

create table  notificacion
(
	id int auto_increment
		primary key,
	fecha_delete datetime null,
	fecha_insert datetime not null,
	fecha_update datetime null,
	version int not null,
	afiliado_id int not null,
	tipo_notificacion varchar(20) not null,
	mensaje_id int not null,
	notificado bit not null,
	fecha_notificado datetime null,
	leido bit not null,
	fecha_leido datetime null,
	entity_id int null,
	constraint FKe87st2y5ke3cs0qwgmedfwg58
		foreign key (mensaje_id) references mensaje (id),
	constraint FK_id6fpettwfctyw39nf33g6xhn
		foreign key (mensaje_id) references mensaje (id),
	constraint FKiq10nwwtr7n568419l0tusfnc
		foreign key (afiliado_id) references afiliado (id),
	constraint FK_7d8yox7wol0drk1ucnqpmiksi
		foreign key (afiliado_id) references afiliado (id)
)
charset=latin1;

create table  observacion_alta_pendiente
(
	id int auto_increment
		primary key,
	fecha_delete datetime null,
	fecha_insert datetime not null,
	fecha_update datetime null,
	version int not null,
	texto varchar(500) not null,
	administrador_id int not null,
	alta_pendiente_id int not null,
	constraint FK4yutlm70nswth8tj384blqvk0
		foreign key (administrador_id) references administrador (id),
	constraint FKfix61p1dg47eoq68n4dnfjl2w
		foreign key (alta_pendiente_id) references bandeja_alta_pendiente (id)
);

create table  pedido
(
	id int auto_increment
		primary key,
	fecha_delete datetime null,
	fecha_insert datetime not null,
	fecha_update datetime null,
	version int not null,
	afiliado_id int not null,
	numero_pedido int not null,
	fecha_pedido date not null,
	canal varchar(100) null,
	sector varchar(100) null,
	clasificacion varchar(50) not null,
	estado varchar(50) not null,
	domicilio varchar(100) null,
	farmacia varchar(100) null,
	importe_cob_parcial decimal(15,2) null,
	medio_de_pago varchar(5) null,
	constraint numero_pedido_unique
		unique (numero_pedido),
	constraint FKemnv39y3qkpp44kp7d4euwqof
		foreign key (afiliado_id) references afiliado (id),
	constraint FK_3he3ltme3jhlthd2ho4gsfbnb
		foreign key (afiliado_id) references afiliado (id)
)
charset=latin1;

create table  entrega
(
	id int auto_increment
		primary key,
	fecha_delete datetime null,
	fecha_insert datetime not null,
	fecha_update datetime null,
	version int not null,
	pedido_id int not null,
	numero_entrega int not null,
	numero_remito varchar(20) not null,
	operador_logistico varchar(100) null,
	estado varchar(50) not null,
	texto_fecha_entrega varchar(100) default '' not null,
	texto_turno varchar(100) default '' not null,
	importe_abonado decimal(15,2) null,
	medio_de_pago varchar(10) null,
	letra_destinatario varchar(10) null,
	constraint FKhga3p49gwpf1r33jbdc5s7a7b
		foreign key (pedido_id) references pedido (id),
	constraint FK_o93fc95o9jkkl4keg6ek5wkwe
		foreign key (pedido_id) references pedido (id)
)
charset=latin1;

create table  entrega_material
(
	id int auto_increment
		primary key,
	fecha_delete datetime null,
	fecha_insert datetime not null,
	fecha_update datetime null,
	version int not null,
	entrega_id int not null,
	sap_id bigint not null,
	nombre varchar(100) not null,
	cantidad int not null,
	monodroga varchar(100) null,
	laboratorio varchar(100) not null,
	lote varchar(100) not null,
	vencimiento date not null,
	constraint FKkn9yawi280rvp6u4qboi8s2pf
		foreign key (entrega_id) references entrega (id),
	constraint FK_crvaxsa4bjd727htxkxe5iq24
		foreign key (entrega_id) references entrega (id)
)
charset=latin1;

create table  pedido_material
(
	id int auto_increment
		primary key,
	fecha_delete datetime null,
	fecha_insert datetime not null,
	fecha_update datetime null,
	version int not null,
	pedido_id int not null,
	sap_id bigint not null,
	nombre varchar(100) not null,
	cantidad int not null,
	monodroga varchar(100) null,
	laboratorio varchar(100) not null,
	constraint FKessmr5q1fwaidltql04qfl8ex
		foreign key (pedido_id) references pedido (id),
	constraint FK_1dc681i6cd83u4crcvsclxwar
		foreign key (pedido_id) references pedido (id)
)
charset=latin1;

create table  personal_medico
(
	id int auto_increment
		primary key,
	fecha_delete datetime null,
	fecha_insert datetime not null,
	fecha_update datetime null,
	version int not null,
	apellido varchar(50) not null,
	email varchar(50) not null,
	nombre varchar(50) not null,
	seguro_medico_id int not null,
	usuario_id int not null,
	constraint FKe5xpucxjsuqq6mje0xdxgf9t6
		foreign key (usuario_id) references usuario (id),
	constraint FKsbr8kchkrt7vea2c71crq613i
		foreign key (seguro_medico_id) references seguro_medico (id)
);

create table  sesion
(
	id int auto_increment
		primary key,
	fecha_delete datetime null,
	fecha_insert datetime not null,
	fecha_update datetime null,
	version int not null,
	token varchar(50) not null,
	player_id varchar(50) null,
	tipo_dispositivo varchar(3) not null,
	rol varchar(50) not null,
	afiliado_id int null,
	administrador_id int null,
	personal_medico_id int null,
	constraint FK6wqh03511qy3e5out38k0ba4n
		foreign key (afiliado_id) references afiliado (id),
	constraint FK8vfyswm891h5auyjtjk3o8t2i
		foreign key (personal_medico_id) references personal_medico (id),
	constraint FK_c3gtmkcnt1d43f23lpi0o5mm2
		foreign key (afiliado_id) references afiliado (id),
	constraint FKqdld073r54ngjid5mtgj0dh14
		foreign key (administrador_id) references administrador (id),
	constraint FK_m8fdkju90lghl7cj4328wariw
		foreign key (administrador_id) references administrador (id)
)
charset=latin1;

create table  consulta_sesion
(
	id int auto_increment
		primary key,
	fecha_delete datetime null,
	fecha_insert datetime not null,
	fecha_update datetime null,
	version int not null,
	consulta_id int not null,
	sesion_id int not null,
	estado varchar(10) not null,
	constraint FK2yeicurvnknt1eykmabmbibvd
		foreign key (consulta_id) references consulta (id),
	constraint FKela2nc1dimlq9mmm1n4dwyy9
		foreign key (sesion_id) references sesion (id)
)
charset=utf8;

create table  template
(
	id int auto_increment
		primary key,
	fecha_delete datetime null,
	fecha_insert datetime not null,
	fecha_update datetime null,
	version int not null,
	administrador_id int not null,
	titulo varchar(100) not null,
	descripcion varchar(200) not null,
	contenido longtext not null,
	constraint FKpjkwiad4g1850rpygki0alfa4
		foreign key (administrador_id) references administrador (id),
	constraint FK_3l69romh80fx9q7d7k96mcgtt
		foreign key (administrador_id) references administrador (id)
)
charset=latin1;

create table  transaccion
(
	id int auto_increment
		primary key,
	fecha_delete datetime null,
	fecha_insert datetime not null,
	fecha_update datetime null,
	version int not null,
	sesion_id int not null,
	controller varchar(100) null,
	action varchar(100) null,
	endpoint varchar(255) null,
	request varchar(1024) null,
	response varchar(2048) null,
	constraint transaccion_ibfk_1
		foreign key (sesion_id) references sesion (id),
	constraint FKc314aausbw7xrmegtnlswvl4l
		foreign key (sesion_id) references sesion (id)
)
charset=utf8mb4;

create table  traza
(
	id int auto_increment
		primary key,
	fecha_delete datetime null,
	fecha_insert datetime not null,
	fecha_update datetime null,
	version int not null,
	codigo varchar(100) not null,
	entrega_material_id int not null,
	constraint FK6s9tmvst33ki1d85w0fueow44
		foreign key (entrega_material_id) references entrega_material (id),
	constraint FK_57al3iohpr3rt50tjvuow603j
		foreign key (entrega_material_id) references entrega_material (id)
)
charset=latin1;

-- create or replace definer = root@`%` procedure CA_UNIV_AFI()
-- -- missing source code
-- ;

-- create or replace definer = root@`%` function extract_numbers(in_string varchar(50)) returns bigint
-- -- missing source code
-- ;

-- create procedure get_acciones_afiliados(IN fecha_inicio datetime, IN fecha_fin datetime)
-- BEGIN
--     SELECT t.fecha_insert, t.action, s.tipo_dispositivo, a.sap_id, sg.sap_descripcion
--     FROM (transaccion AS t
--              INNER JOIN sesion AS s
--                         ON t.sesion_id = s.id
--              INNER JOIN afiliado AS a
--                         ON s.afiliado_id = a.id
--              INNER JOIN seguro_medico as sg
--                         ON a.seguro_medico_id = sg.id)
--     WHERE (t.fecha_insert BETWEEN fecha_inicio AND  fecha_fin)
--       AND t.action NOT IN ('updatePlayerId', 'getHome', 'getCellCompanyList', 'getQueryTypeList', 'getFarmacySearch', 'getDeliveryLocation')
--     ORDER BY t.fecha_insert
-- END;

-- create or replace definer = root@`%` procedure get_universo_afiliados()
-- -- missing source code
-- ;

