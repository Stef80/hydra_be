CREATE TABLE Users(
  id BIGSERIAL PRIMARY KEY,
  email    VARCHAR(255) NOT NULL UNIQUE,
  name      VARCHAR(255) NOT NULL,
  surname   VARCHAR(255) NOT NULL,
  password  VARCHAR(255) NOT NULL,
  workplace VARCHAR(255) NOT NULL,
  expertise_area VARCHAR(255) NOT NULL,
  actived BOOLEAN NOT NULL 
);

CREATE TABLE Roles(
  id BIGSERIAL PRIMARY KEY,
  user_fk BIGINT NOT NULL,
  role VARCHAR(18) NOT NULL,
  FOREIGN KEY(user_fk) REFERENCES Users ON DELETE NO ACTION,
  UNIQUE(role,user_fk)
);


CREATE TABLE Projects(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    total_days INT NOT NULL
);

CREATE TABLE Tasks(
    id BIGSERIAL PRIMARY KEY,
    task_name VARCHAR NOT NULL,
    project_fk BIGINT NOT NULL,
    date_of_registration DATE NOT NULL,
    status VARCHAR(20) NOT NULL,
    total_worked REAL,
    date_of_publish TIMESTAMP ,
    hours_of_working REAL,
    revision SERIAL, 
    FOREIGN KEY(project_fk) REFERENCES Projects  ON DELETE NO ACTION
);

CREATE TABLE Assigned(
    id BIGSERIAL PRIMARY KEY,
    user_fk BIGINT NOT NULL,
    task_fk BIGINT NOT NULL,
    FOREIGN KEY(user_fk) REFERENCES Users ON DELETE NO ACTION,
    FOREIGN KEY(task_fk) REFERENCES Tasks  ON DELETE NO ACTION,
    UNIQUE(user_fk,task_fk)
);


