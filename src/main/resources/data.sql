INSERT INTO category(id, name)
VALUES
(1,'Domowe'),
(2,'Samochód');

INSERT INTO task(name, created_at, category_id)
VALUES
    ('Zrobić zakupy', '2021-02-19 15:00', 1),
    ('Wynieść śmieci', '2021-02-19 15:01', 1),
    ('Umyć naczynia', '2021-02-19 15:01', 1),
    ('Wynieść śmieci', '2021-02-19 15:01', 1),
    ('Umyć samochód', '2021-02-19 15:01', 2),
    ('Zmyć podłogę', '2021-02-19 15:05', 1),
    ('Przegląd', '2021-02-19 15:05', 2);