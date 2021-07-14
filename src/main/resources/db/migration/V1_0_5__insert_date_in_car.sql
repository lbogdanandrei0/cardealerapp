delete from dealer;
delete from car;

insert into dealer (name, address) values
                   ('d1', 'adr1'),
                   ('d2', 'adr2');

insert into car (location, brand, type, engine_capacity, engine_power, price, date_of_fabrication, description) values
                (1, 'Tesla', 'Sedan', 2000, 500, 2000.00, '2012-05-10', 'First Tesla Description'),
                (2, 'Tesla', 'Sedan', 1500, 450, 1800.50, '2016-12-05', 'Second Tesla Description'),
                (1, 'Audi', 'Sedan', 4200, 520, 50000, '2008-03-25', 'Audi A8 Description'),
                (1, 'Honda', 'Sedan', 1989, 170, 15000, '2013-04-10', 'Honda Civic Description'),
                (2, 'Volkswagen', 'Sedan', 2048, 140, 13000, '2016-09-01', 'VW Passat Description');