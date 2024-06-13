CREATE TYPE varstatus AS ENUM (
    'occupé',
    'libre'
);

-- Variable domain for email column with integrate check
CREATE DOMAIN varemail AS TEXT CHECK (VALUE ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$');
-- Variable type for gender column with preselected value
CREATE TYPE vargender AS ENUM ('Male','Female'); 