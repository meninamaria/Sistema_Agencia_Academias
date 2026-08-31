DROP DATABASE IF EXISTS agencia_academias;

CREATE DATABASE IF NOT EXISTS agencia_academias
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE agencia_academias;

-- Tabela: academia
CREATE TABLE academia (
    id_acd               INT AUTO_INCREMENT PRIMARY KEY,
    nome                 VARCHAR(100)   NOT NULL,
    endereco             VARCHAR(150)   NOT NULL,
    contato              VARCHAR(20)    NOT NULL,
    mensalidade          DECIMAL(10,2)  NOT NULL,
    atividades_ofertadas VARCHAR(255)   NOT NULL,
    num_personais        INT            NOT NULL DEFAULT 0,
    criado_em            TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT chk_academia_mensalidade CHECK (mensalidade >= 0),
    CONSTRAINT chk_academia_personais   CHECK (num_personais >= 0)
) ENGINE=InnoDB;

-- Tabela: cliente
CREATE TABLE cliente (
    id_cli       INT AUTO_INCREMENT PRIMARY KEY,
    nome         VARCHAR(100)  NOT NULL,
    cpf          CHAR(14)      NOT NULL UNIQUE,   -- formato: 000.000.000-00
    idade        INT           NOT NULL,
    login        VARCHAR(50)   NOT NULL UNIQUE,
    senha        VARCHAR(255)  NOT NULL,
    rest_medica  VARCHAR(255)  NULL,
    criado_em    TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT chk_cliente_idade CHECK (idade > 0 AND idade < 130)
) ENGINE=InnoDB;

ALTER TABLE cliente AUTO_INCREMENT = 1;

-- Tabela: matricula
CREATE TABLE matricula (
    id_matricula      INT AUTO_INCREMENT PRIMARY KEY,
    id_cli            INT       NOT NULL,
    id_acd            INT       NOT NULL,
    data_matricula    DATETIME  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_cancelamento DATETIME  NULL,
    status            ENUM('ATIVA','CANCELADA') NOT NULL DEFAULT 'ATIVA',

    cliente_se_ativa  INT GENERATED ALWAYS AS (
                          CASE WHEN status = 'ATIVA' THEN id_cli ELSE NULL END
                      ) STORED,

    CONSTRAINT fk_matricula_cliente
        FOREIGN KEY (id_cli) REFERENCES cliente(id_cli)
        ON DELETE RESTRICT,
    CONSTRAINT fk_matricula_academia
        FOREIGN KEY (id_acd) REFERENCES academia(id_acd)
        ON DELETE RESTRICT,

    UNIQUE KEY uk_matricula_ativa_por_cliente (cliente_se_ativa)
) ENGINE=InnoDB;

CREATE INDEX idx_matricula_academia ON matricula(id_acd);



-- Tabela: avaliacao
CREATE TABLE avaliacao (
    id_avaliacao   INT AUTO_INCREMENT PRIMARY KEY,
    id_cli         INT           NOT NULL,
    id_acd         INT           NOT NULL,
    nota           DECIMAL(2,1)  NOT NULL,
    data_avaliacao DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_avaliacao_cliente
        FOREIGN KEY (id_cli) REFERENCES cliente(id_cli)
        ON DELETE CASCADE,
    CONSTRAINT fk_avaliacao_academia
        FOREIGN KEY (id_acd) REFERENCES academia(id_acd)
        ON DELETE CASCADE,

    CONSTRAINT chk_avaliacao_nota CHECK (nota BETWEEN 0.0 AND 5.0)

) ENGINE=InnoDB;

CREATE INDEX idx_avaliacao_academia ON avaliacao(id_acd);

CREATE OR REPLACE VIEW vw_academia_avaliacao AS
SELECT
    a.id_acd,
    a.nome,
    COUNT(av.id_avaliacao)                    AS total_avaliacoes,
    ROUND(COALESCE(AVG(av.nota), 0), 1)       AS media_avaliacao
FROM academia a
LEFT JOIN avaliacao av ON av.id_acd = a.id_acd
GROUP BY a.id_acd, a.nome;

CREATE OR REPLACE VIEW vw_cliente_academia_atual AS
SELECT
    c.id_cli,
    c.nome        AS nome_cliente,
    m.id_acd,
    ac.nome       AS nome_academia,
    m.data_matricula
FROM cliente c
JOIN matricula m ON m.id_cli = c.id_cli AND m.status = 'ATIVA'
JOIN academia ac ON ac.id_acd = m.id_acd;

CREATE TABLE administrador (
    id_admin  INT AUTO_INCREMENT PRIMARY KEY,
    login     VARCHAR(50)  NOT NULL UNIQUE,
    senha     VARCHAR(255) NOT NULL,
    criado_em TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;
