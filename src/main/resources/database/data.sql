INSERT INTO order_statuses(code, name) VALUES ('ACCEPTED', 'принят'), ('BEING_PROCESSED', 'обрабатывается'), ('COMPLETED', 'выполнен');
INSERT INTO brands(name) VALUES ('BMW'), ('Mercedes-Benz'), ('Toyota');
INSERT INTO cars(model, brand_id) VALUES ('x7', 1), ('i8', 1), ('f10', 1), ('gla', 2), ('cls', 2), ('amg', 2), ('camry', 3), ('avalon', 3), ('supra', 3);
INSERT INTO options(name) VALUES ('спортивный аэродинамический обвес'), ('отделка салона кожей'), ('подогрев сидений'), ('обогрев лобового стекла'), ('зимняя резина');