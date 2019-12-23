INSERT INTO customer (id, name, email) VALUES
(1, 'Mariazinha', 'mariazinha@email.com'),
(2, 'Joãozinho', 'joaozinho@email.com');

INSERT INTO perfil (id, perfil) VALUES
(1, 'ADMIN'),
(2, 'CLIENTE');

INSERT INTO acesso(id, username, password, perfil_id) VALUES
(1, 'mariazinha', '123456', 2),
(2, 'joaozinho', '123456', 2),
(3, 'admin', 'admin', 1);