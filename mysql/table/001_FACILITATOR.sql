CREATE TABLE IF NOT EXISTS facilitator (
	id INT(10) PRIMARY KEY
);

INSERT INTO facilitator (id) VALUES
    (1),
    (2)
ON DUPLICATE KEY UPDATE id=id;