CREATE TABLE IF NOT EXISTS cliente
(
    id_cliente BIGSERIAL PRIMARY KEY,
    birth_day DATE,
    cpf VARCHAR(255) UNIQUE NOT NULL,
    endereco_cliente VARCHAR(255),
    idade_cliente INTEGER,
    nome_cliente VARCHAR(255) NOT NULL,
    numero_cliente VARCHAR(255) UNIQUE NOT NULL,
    senha_cliente VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS cliente_mesa
(
    id_cliente_mesa BIGSERIAL PRIMARY KEY,
    id_cliente BIGINT NOT NULL REFERENCES cliente(id_cliente),
    id_mesa BIGINT NOT NULL REFERENCES mesa(id_mesa)
);

CREATE TABLE IF NOT EXISTS itens_pedidos
(
    id_itens_pedido BIGSERIAL PRIMARY KEY,
    id_pedido BIGINT NOT NULL REFERENCES pedido(id_pedido),
    id_produto BIGINT NOT NULL REFERENCES produtos(id_produto),
    quantidade_produto INTEGER NOT NULL,
    total_item_pedido NUMERIC NOT NULL
);

CREATE TABLE IF NOT EXISTS mesa
(
    id_mesa BIGSERIAL PRIMARY KEY,
    mesa_em_uso BOOLEAN NOT NULL,
    valor_total_mesa NUMERIC NOT NULL
);

CREATE TABLE IF NOT EXISTS pedido
(
    id_pedido BIGSERIAL PRIMARY KEY,
    data_pedido DATE NOT NULL,
    hora_pedido TIMESTAMP NOT NULL,
    hora_pedido_finalizado TIMESTAMP NOT NULL,
    id_cliente_pedido BIGINT NOT NULL REFERENCES cliente(id_cliente),
    id_mesa_pedido BIGINT NOT NULL REFERENCES mesa(id_mesa),
    pedido_pago BOOLEAN NOT NULL,
    pedido_pronto BOOLEAN NOT NULL,
    total_pedido NUMERIC NOT NULL
);

CREATE TABLE IF NOT EXISTS produtos
(
    id_produto BIGSERIAL PRIMARY KEY,
    classe_produto VARCHAR(255) NOT NULL,
    descricao_produto VARCHAR(255) NOT NULL,
    nome_produto VARCHAR(255) NOT NULL UNIQUE,
    preco_produto NUMERIC NOT NULL,
    promo_produto INTEGER NOT NULL
);