CREATE TABLE Users(
user_id BIGSERIAL PRIMARY KEY,
email    VARCHAR(255) NOT NULL,
name      VARCHAR(255) NOT NULL,
surname   VARCHAR(255) NOT NULL,
password  VARCHAR(255) NOT NULL,
workplace VARCHAR(255) NOT NULL,
expetise_area VARCHAR(255) NOT NULL);

CREATE TABLE Roles(
role_id BIGSERIAL PRIMARY KEY,
 user_id BIGINT NOT NULL,
role VARCHAR(255) NOT NULL,
FOREIGN KEY(user_id) REFERENCES Users ON DELETE CASCADE );


CREATE TABLE Projects(
    project_id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    total_days INT NOT NULL
);

CREATE TABLE Tasks(
    task_id BIGSERIAL PRIMARY KEY,
    task_name VARCHAR NOT NULL,
    project_id BIGINT NOT NULL,
    date_of_registation DATE NOT NULL,
    state VARCHAR(20) NOT NULL,
    total_worked DECIMAL(2,2),
    FOREIGN KEY(project_id) REFERENCES Projects  ON DELETE CASCADE
);

CREATE TABLE Assigned(
Assigned_id BIGSERIAL PRIMARY KEY,
user_id BIGINT NOT NULL,
task_id BIGINT NOT NULL,
FOREIGN KEY(user_id) REFERENCES Users ON DELETE CASCADE,
FOREIGN KEY(task_id) REFERENCES Tasks  ON DELETE CASCADE
);

CREATE TABLE Updates(
     update_id  BIGSERIAL PRIMARY KEY,
     assigned_id BIGINT NOT NULL,
     date_of_publish DATE NOT NULL,
     hours_of_working DECIMAL(3,2) NOT NULL,
     FOREIGN KEY(assigned_id)REFERENCES Assigned ON DELETE CASCADE
  
);