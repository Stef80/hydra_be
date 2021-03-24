
CREATE TABLE License(
     tenant_id BIGSERIAL PRIMARY KEY,
     email VARCHAR NOT NULL,
     business_name  VARCHAR NOT NULL,
     start_date DATE NOT NULL,
     end_date DATE NOT NULL,
     cost REAL,
     paid REAL 
);

CREATE TABLE Users(
  id BIGSERIAL PRIMARY KEY,
  email    VARCHAR(255) NOT NULL UNIQUE,
  name      VARCHAR(255),
  surname   VARCHAR(255) ,
  password  VARCHAR(255) NOT NULL,
  workplace VARCHAR(255) NOT NULL,
  expertise_area VARCHAR(255),
  actived VARCHAR(19) NOT NULL,
 tenant_id BIGINT,
 FOREIGN KEY(tenant_id) REFERENCES License ON DELETE NO ACTION
);

CREATE TABLE Roles(
  id BIGSERIAL PRIMARY KEY,
  user_fk BIGINT NOT NULL,
  role VARCHAR(18) NOT NULL,
 tenant_id BIGINT,
  FOREIGN KEY(user_fk) REFERENCES Users ON DELETE NO ACTION,
  FOREIGN KEY(tenant_id) REFERENCES License ON DELETE NO ACTION,
  UNIQUE(role,user_fk)
);


CREATE TABLE Projects(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    total_days INT NOT NULL,
	tenant_id BIGINT,
    FOREIGN KEY(tenant_id) REFERENCES License ON DELETE NO ACTION
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
    revision INTEGER, 
	tenant_id BIGINT,
    FOREIGN KEY(tenant_id) REFERENCES License ON DELETE NO ACTION,
    FOREIGN KEY(project_fk) REFERENCES Projects  ON DELETE NO ACTION
);

CREATE TABLE Assigned(
    id BIGSERIAL PRIMARY KEY,
    user_fk BIGINT NOT NULL,
    task_fk BIGINT NOT NULL,
	tenant_id BIGINT,
    FOREIGN KEY(user_fk) REFERENCES Users ON DELETE NO ACTION,
    FOREIGN KEY(task_fk) REFERENCES Tasks  ON DELETE NO ACTION,
    FOREIGN KEY(tenant_id) REFERENCES License ON DELETE NO ACTION,
    UNIQUE(user_fk,task_fk)
);


CREATE TABLE Bookables(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    tenant_id BIGINT,
    FOREIGN KEY(tenant_id) REFERENCES License ON DELETE NO ACTION
);

CREATE TABLE Books(
      id BIGSERIAL PRIMARY KEY,
      user_fk BIGINT NOT NULL,
      bookable_fk BIGINT NOT NULL,
      start_date TIMESTAMP NOT NULL,
      end_date TIMESTAMP NOT NULL,
      tenant_id BIGINT,
      FOREIGN KEY(user_fk) REFERENCES Users ON DELETE NO ACTION,
      FOREIGN KEY(bookable_fk) REFERENCES Bookables ON DELETE NO ACTION,
      FOREIGN KEY(tenant_id) REFERENCES License ON DELETE NO ACTION,
      UNIQUE(bookable_fk, start_date, end_date),
      CONSTRAINT CheckEndLaterThanStart CHECK (end_date >= start_date)
);





