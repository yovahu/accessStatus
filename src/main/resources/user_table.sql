create table users (
    Id INT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    Login VARCHAR(20) NOT NULL,
    Password VARCHAR(11) NOT NULL
)