CREATE TABLE IF NOT EXISTS student (
	id INT(10) PRIMARY KEY,
	name VARCHAR(20),
	login_id VARCHAR(20),
	facilitator_id INT(10),
    classroom_id INT(10),
	FOREIGN KEY (facilitator_id) REFERENCES facilitator(id),
    FOREIGN KEY (classroom_id) REFERENCES classroom(id)
);

INSERT INTO student (id, name, login_id, facilitator_id, classroom_id) VALUES
    (1, '佐藤', 'foo123', 1, 1),
    (2, '鈴木', 'bar456', 2, 2),
    (3, '田中', 'baz789', 1, 1),
    (4, '加藤', 'hoge0000', 1, 1),
    (5, '太田', 'fuga1234', 2, 2),
    (6, '佐々木', 'piyo5678', 1, 1),
    (7, '小田原', 'fizz9999', 1, 1),
    (8, 'Smith', 'buzz777', 2, 2),
    (9, 'Johnson', 'fizzbuzz#123', 1, 1),
    (10, 'Williams', 'xxx:42', 1, 1)
ON DUPLICATE KEY UPDATE id=id;