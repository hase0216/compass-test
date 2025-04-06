CREATE TABLE IF NOT EXISTS classroom (
	id INT(10) PRIMARY KEY,
	name VARCHAR(20),
	facilitator_id INT(10),
	FOREIGN KEY (facilitator_id) REFERENCES facilitator(id)
);

INSERT INTO classroom (id, name, facilitator_id) VALUES
    (1, 'クラスA', 1),
    (2, 'クラスB', 2),
    (3, 'クラスC', 1)
ON DUPLICATE KEY UPDATE id=id;