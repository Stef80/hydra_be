INSERT INTO Licence (email, business_name, start_date, end_date,cost, paid) values
('stefano.longobucco@agmsolutions.net','AGM','2021-01-01','2026-12-31',2000.00, 2000.00),
('stefano.longobucco@nextre.it','nextre','2021-01-01','2026-12-31',2000.00, 2000.00);


INSERT INTO users (email, name, surname, password, workplace, expertise_area, actived,tenant_id) values
('stefano.longobucco@agmsolutions.net', 'Stefano', 'Longobucco', '$2y$12$a7IeahjiRt6WKtpVWG46eehCIFtIxc5WCbn6xc7S4TtE9UtBn8dnq', 'Milano', 'jave be','ACTIVE',1),
('stefano.longobucco@gmail.com', 'Stefano', 'Longobucco', '$2y$12$a7IeahjiRt6WKtpVWG46eehCIFtIxc5WCbn6xc7S4TtE9UtBn8dnq', 'Milano', 'jave be','ACTIVE',2),
('giulio.verne@agmsolutions.net', 'Giulio', 'Verne', '$2y$12$a7IeahjiRt6WKtpVWG46eehCIFtIxc5WCbn6xc7S4TtE9UtBn8dnq', 'Milano', 'jave be','ACTIVE',1);


INSERT INTO projects (name, description, start_date, end_date, total_days, tenant_id) values
('HYDRA_BE','gestione smartworking back-end','18-01-2021', '30-04-2021', 90, 1),
('WEB SCRAPER','acquisizione informazioni da siti esterni','18-01-2021', '30-04-2021', 90, 2);


INSERT INTO roles (user_fk,role,tenant_id) values
  (1 ,'ADMIN',1),
  (2,'ADMIN',2),
  (3,'WORKER',1);

INSERT INTO tasks(task_name, project_fk,date_of_registration, status,revision, tenant_id) values
('analisi funzionale', 1, '18-01-2021','OPEN',0,1),
('analisi Base di dati', 1, '22-01-2021','OPEN',0,1),
('analisi End Point', 1, '22-01-2021','OPEN',0,1),
('analisi funzionale', 2, '18-01-2021','OPEN',0,2),
('analisi Base di dati', 2, '22-01-2021','OPEN',0,2),
('analisi End Point', 2, '22-01-2021','OPEN',0,2);


--INSERT INTO bookables(name, desription, tenant_id)