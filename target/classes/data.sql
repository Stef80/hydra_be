--INSERT INTO users (email, name, surname, password, workplace, expertise_area, actived) values
--('stefano.longobucco@agmsolutions.net', 'Stefano', 'Longobucco', '$2y$12$a7IeahjiRt6WKtpVWG46eehCIFtIxc5WCbn6xc7S4TtE9UtBn8dnq', 'Milano', 'jave be','true');


--INSERT INTO projects (name, description, start_date, end_date, total_days) values
--('HYDRA_BE','gestione smartworking back-end','18-01-2021', '30-04-2021', 90);


--INSERT INTO roles (user_fk,role) values
--(1 , 0 )

INSERT INTO tasks(task_name, project_fk,date_of_registration, status) values
('analisi funzionale', 1, '18-01-2021','0'),
('analisi Base di dati', 1, '22-01-2021','0'),
('analisi End Point', 1, '22-01-2021','0');


--INSERT INTO updates()