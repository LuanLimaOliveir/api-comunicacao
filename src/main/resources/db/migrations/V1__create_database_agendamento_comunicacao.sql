CREATE TABLE tb_tipo_comunicacao (
	id_tipo_comunicacao SERIAL,
	ds_tipo_comunicacao VARCHAR(10) NOT NULL,
	PRIMARY KEY(id_tipo_comunicacao)
);

INSERT INTO tb_tipo_comunicacao (ds_tipo_comunicacao) VALUES ('email');
INSERT INTO tb_tipo_comunicacao (ds_tipo_comunicacao) VALUES ('push');
INSERT INTO tb_tipo_comunicacao (ds_tipo_comunicacao) VALUES ('sms');
INSERT INTO tb_tipo_comunicacao (ds_tipo_comunicacao) VALUES ('whatsapp');

CREATE TABLE tb_status_envio (
	id_status_envio SERIAL,
	ds_status_envio VARCHAR(25) NOT NULL,
	cd_status INTEGER NOT NULL,
	PRIMARY KEY(id_status_envio)
);

INSERT INTO tb_status_envio (ds_status_envio, cd_status) VALUES ('success', 200);
INSERT INTO tb_status_envio (ds_status_envio, cd_status) VALUES ('error', 500);
INSERT INTO tb_status_envio (ds_status_envio, cd_status) VALUES ('timeout', 408);

CREATE TABLE tb_agendamento (
	id_agendamento SERIAL,
	dt_agendamento TIMESTAMP NOT NULL,
	destinatario VARCHAR(100) NOT NULL,
	mensagem TEXT NOT NULL,
	dt_cadastro TIMESTAMP NOT NULL DEFAULT current_timestamp,
	cd_tipo_comunicacao INTEGER NOT NULL,
	PRIMARY KEY(id_agendamento),
	CONSTRAINT fk_tipo_comunicacao FOREIGN KEY(cd_tipo_comunicacao) REFERENCES tb_tipo_comunicacao (id_tipo_comunicacao)
);

CREATE TABLE tb_log_envio_mensagem (
	cd_agendamento INTEGER NOT NULL,
	dt_procesaamento TIMESTAMP NOT NULL,
	cd_status_envio INTEGER NOT NULL,
	CONSTRAINT fk_agendamento FOREIGN KEY(cd_agendamento) REFERENCES tb_agendamento (id_agendamento),
	CONSTRAINT fk_status_envio FOREIGN KEY(cd_status_envio) REFERENCES tb_status_envio (id_status_envio)
);