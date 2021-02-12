CREATE TABLE Users(
id BIGSERIAL PRIMARY KEY,
email    VARCHAR(255) NOT NULL UNIQUE,
name      VARCHAR(255) NOT NULL,
surname   VARCHAR(255) NOT NULL,
password  VARCHAR(255) NOT NULL,
workplace VARCHAR(255) NOT NULL,
expertise_area VARCHAR(255) NOT NULL,
actived BOOLEAN NOT NULL);

CREATE TABLE Roles(
  id BIGSERIAL PRIMARY KEY,
 user_id BIGINT NOT NULL,
role VARCHAR(18) NOT NULL,
FOREIGN KEY(user_id) REFERENCES Users ON DELETE CASCADE,
UNIQUE(role,user_id)
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
    project_id BIGINT NOT NULL,
    date_of_registation DATE NOT NULL,
    status VARCHAR(20) NOT NULL,
    total_worked DECIMAL(2,2),
    FOREIGN KEY(project_id) REFERENCES Projects  ON DELETE CASCADE
);

CREATE TABLE Assigned(
id BIGSERIAL PRIMARY KEY,
user_id BIGINT NOT NULL,
task_id BIGINT NOT NULL,
FOREIGN KEY(user_id) REFERENCES Users ON DELETE CASCADE,
FOREIGN KEY(task_id) REFERENCES Tasks  ON DELETE CASCADE,
UNIQUE(user_id,task_id)
);

CREATE TABLE Updates(
     id  BIGSERIAL PRIMARY KEY ,
     assigned_id BIGINT NOT NULL,
     date_of_publish TIMESTAMP NOT NULL,
     hours_of_working DECIMAL(2,2) NOT NULL,
     FOREIGN KEY(assigned_id)REFERENCES Assigned ON DELETE CASCADE
);

