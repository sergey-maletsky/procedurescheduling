CREATE SEQUENCE IF NOT EXISTS room_id_seq MINVALUE 1;
CREATE SEQUENCE IF NOT EXISTS doctor_id_seq MINVALUE 1;
CREATE SEQUENCE IF NOT EXISTS study_id_seq MINVALUE 1;
CREATE SEQUENCE IF NOT EXISTS patient_id_seq MINVALUE 1;

CREATE TABLE IF NOT EXISTS room
(
  id BIGINT PRIMARY KEY NOT NULL,
  name VARCHAR NOT NULL
);

CREATE UNIQUE INDEX IF NOT EXISTS room_name_idx
  ON room (name);

CREATE TABLE IF NOT EXISTS doctor
(
  id BIGINT PRIMARY KEY NOT NULL,
  name VARCHAR NOT NULL
);

CREATE UNIQUE INDEX IF NOT EXISTS doctor_name_idx
  ON doctor (name);

CREATE TABLE IF NOT EXISTS study
(
  id BIGINT PRIMARY KEY NOT NULL,
  description VARCHAR NOT NULL,
  status VARCHAR NOT NULL,
  start_time TIMESTAMP NOT NULL,
  end_time TIMESTAMP,
  doctor_id BIGINT NOT NULL,
  patient_id BIGINT NOT NULL,
  room_id BIGINT NOT NULL,
  CONSTRAINT study_doctor FOREIGN KEY (doctor_id) REFERENCES doctor (id),
  CONSTRAINT study_patient FOREIGN KEY (patient_id) REFERENCES patient (id),
  CONSTRAINT study_room FOREIGN KEY (room_id) REFERENCES room (id)
);

CREATE TABLE IF NOT EXISTS patient
(
  id BIGINT PRIMARY KEY NOT NULL,
  name VARCHAR NOT NULL,
  sex VARCHAR,
  date_of_birth TIMESTAMP
);

CREATE UNIQUE INDEX IF NOT EXISTS patient_name_idx
  ON patient (name);

INSERT INTO room (id, name) VALUES (COALESCE((select id from room order by id desc limit 1), 0) + 1, 'ROOM 1') ON CONFLICT (name) DO NOTHING;
INSERT INTO room (id, name) VALUES (COALESCE((select id from room order by id desc limit 1), 0) + 1, 'ROOM 2') ON CONFLICT (name) DO NOTHING;
INSERT INTO room (id, name) VALUES (COALESCE((select id from room order by id desc limit 1), 0) + 1, 'ROOM 3') ON CONFLICT (name) DO NOTHING;

INSERT INTO doctor (id, name) VALUES (COALESCE((select id from doctor order by id desc limit 1), 0) + 1, 'Dr. Evil') ON CONFLICT (name) DO NOTHING;
INSERT INTO doctor (id, name) VALUES (COALESCE((select id from doctor order by id desc limit 1), 0) + 1, 'Dr. Who') ON CONFLICT (name) DO NOTHING;
INSERT INTO doctor (id, name) VALUES (COALESCE((select id from doctor order by id desc limit 1), 0) + 1, 'Dr. Robin Williams') ON CONFLICT (name) DO NOTHING;