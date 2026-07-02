CREATE TABLE missing_persons (

    id BIGSERIAL PRIMARY KEY,

    case_number VARCHAR(100) NOT NULL UNIQUE,

    full_name VARCHAR(255) NOT NULL,

    age INTEGER,

    gender VARCHAR(20),

    blood_group VARCHAR(10),

    height DOUBLE PRECISION,

    weight DOUBLE PRECISION,

    identification_marks VARCHAR(500),

    last_seen_date DATE,

    last_seen_location VARCHAR(500),

    city VARCHAR(100),

    state VARCHAR(100),

    country VARCHAR(100),

    description TEXT,

    photo_url VARCHAR(500),

    status VARCHAR(30),

    reported_by BIGINT,

    created_at TIMESTAMP,

    updated_at TIMESTAMP,

    CONSTRAINT fk_missing_person_user
        FOREIGN KEY (reported_by)
        REFERENCES users(id)
);