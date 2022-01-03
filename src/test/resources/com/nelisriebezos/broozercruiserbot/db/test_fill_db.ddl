insert into car (id, kmcounter)
values (1,
        150);

insert into person(id, name)
values (1,
        'niels');

insert into tanksession(id, timestamp, carid)
values (1,
        timestamp('2020-12-02 13:12:12'),
        1);

insert into trip(id, distance, timestamp, tanksessionid)
values (1,
        100,
        timestamp('2020-12-02 13:12:12'),
        1);

insert into trip_person(tripid, personid)
values (1,
        1);

insert into correction(id, timestamp, distance, personid, tanksessionid)
values (1,
        timestamp('2020-12-02 13:12:12'),
        50,
        1,
        1);

insert into cruiser_sequences(sequence_name, next_val)
values ('car_seq',
        2);

insert into cruiser_sequences(sequence_name, next_val)
values ('correction_seq',
        2);

insert into cruiser_sequences(sequence_name, next_val)
values ('person_seq',
        2);

insert into cruiser_sequences(sequence_name, next_val)
values ('tanksession_seq',
        2);

insert into cruiser_sequences(sequence_name, next_val)
values ('trip_seq',
        2);