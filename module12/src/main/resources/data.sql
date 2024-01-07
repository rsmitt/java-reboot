
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_USER');
INSERT INTO auth (username, password) VALUES ('admin', '$2a$10$rnxHUAo/gTfmUm2Rmf7lfeyIwMKcVOiU9cdnGWIrFDXivSGOsCvde');
INSERT INTO auth_roles (auth_id, role_id) SELECT (SELECT id FROM auth WHERE username = 'admin'), (SELECT id FROM roles WHERE name = 'ROLE_ADMIN');
INSERT INTO auth (username, password) VALUES ('user', '$2a$10$rnxHUAo/gTfmUm2Rmf7lfeyIwMKcVOiU9cdnGWIrFDXivSGOsCvde');
INSERT INTO auth_roles (auth_id, role_id) SELECT (SELECT id FROM auth WHERE username = 'user'), (SELECT id FROM roles WHERE name = 'ROLE_USER');