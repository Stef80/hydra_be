--INSERT INTO users (email, name, surname, password, workplace, expertise_area, actived,tenant_id) values
--('stefano.longobucco@agmsolutions.net', 'Stefano', 'Longobucco', '$2y$12$a7IeahjiRt6WKtpVWG46eehCIFtIxc5WCbn6xc7S4TtE9UtBn8dnq', 'Milano', 'jave be','true','agm'),
--('stefano.longobucco@gmail.com', 'Stefano', 'Longobucco', '$2y$12$a7IeahjiRt6WKtpVWG46eehCIFtIxc5WCbn6xc7S4TtE9UtBn8dnq', 'Milano', 'jave be','true','nextre'),
--('giulio.verne@agmsolutions.net', 'Giulio', 'Verne', '$2y$12$a7IeahjiRt6WKtpVWG46eehCIFtIxc5WCbn6xc7S4TtE9UtBn8dnq', 'Milano', 'jave be','true','agm');


--INSERT INTO projects (name, description, start_date, end_date, total_days, tenant_id) values
--('HYDRA_BE','gestione smartworking back-end','18-01-2021', '30-04-2021', 90, 'agm'),
--('WEB SCRAPER','acquisizione informazioni da siti esterni','18-01-2021', '30-04-2021', 90, 'nextre');


INSERT INTO roles (user_fk,role) values
  (1 , 'ADMIN'),
  (2,'WORKER'),
  (3,'ADMIN');

INSERT INTO tasks(task_name, project_fk,date_of_registration, status, tenant_id) values
('analisi funzionale', 1, '18-01-2021','OPEN','agm'),
('analisi Base di dati', 1, '22-01-2021','OPEN','agm'),
('analisi End Point', 1, '22-01-2021','OPEN','agm'),
('analisi funzionale', 2, '18-01-2021','OPEN','nextre'),
('analisi Base di dati', 2, '22-01-2021','OPEN','nextre'),
('analisi End Point', 2, '22-01-2021','OPEN','nextre');


--INSERT INTO updates()