INSERT INTO users (email, name, surname, password, workplace, expertise_area, actived,tenant_id) values
('stefano.longobucco@agmsolutions.net', 'Stefano', 'Longobucco', '$2y$12$a7IeahjiRt6WKtpVWG46eehCIFtIxc5WCbn6xc7S4TtE9UtBn8dnq', 'Milano', 'jave be','true','agm'),
('stefano.longobucco@gmail.com', 'Stefano', 'Longobucco', '$2y$12$a7IeahjiRt6WKtpVWG46eehCIFtIxc5WCbn6xc7S4TtE9UtBn8dnq', 'Milano', 'jave be','true','nextre'),
('giulio.verne@agmsolutions.net', 'Giulio', 'Verne', '$2y$12$a7IeahjiRt6WKtpVWG46eehCIFtIxc5WCbn6xc7S4TtE9UtBn8dnq', 'Milano', 'jave be','true','agm');


INSERT INTO projects (name, description, start_date, end_date, total_days, tenant_id) values
('HYDRA_BE','gestione smartworking back-end','18-01-2021', '30-04-2021', 90, 'agm'),
('WEB SCRAPER','acquisizione informazioni da siti esterni','18-01-2021', '30-04-2021', 90, 'nextre');


INSERT INTO roles (user_fk,role,tenant_id) values
  (1 ,'ADMIN','agm'),
  (2,'ADMIN''nexte'),
  (3,'WORKER','agm');

INSERT INTO tasks(task_name, project_fk,date_of_registration, status,revision, tenant_id) values
('analisi funzionale', 1, '18-01-2021','OPEN',0,'agm'),
('analisi Base di dati', 1, '22-01-2021','OPEN',0,'agm'),
('analisi End Point', 1, '22-01-2021','OPEN',0,'agm'),
('analisi funzionale', 2, '18-01-2021','OPEN',0,'nextre'),
('analisi Base di dati', 2, '22-01-2021','OPEN',0,'nextre'),
('analisi End Point', 2, '22-01-2021','OPEN',0,'nextre');


--INSERT INTO bookables(name, desription, tenant_id)