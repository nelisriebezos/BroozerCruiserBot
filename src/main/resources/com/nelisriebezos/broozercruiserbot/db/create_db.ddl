create table cruiser_version
(
    name    varchar(200),
    version int not null,
    primary key (name)
);
create table cruiser_sequences
(
    sequence_name varchar(50) not null,
    next_val      bigint      not null,
    primary key (sequence_name)
);

create table car
(
    id        bigint not null,
    kmcounter int    not null,
    primary key (id)
);

create table person
(
    id   bigint       not null,
    name varchar(255) not null,
    primary key (id)
);

create table tanksession
(
    id        bigint    not null,
    timestamp timestamp not null,
    carid     bigint    not null,
    primary key (id)
);

create table trip
(
    id            bigint    not null,
    mileageInKm      int       not null,
    timestamp     timestamp not null,
    tanksessionid bigint    not null,
    primary key (id)
);

create table trip_person
(
    tripid   bigint not null,
    personid bigint not null,
    foreign key (tripid) references trip (id),
    foreign key (personid) references person (id)
);

alter table tanksession
    add foreign key (carid) references car (id);

alter table trip
    add foreign key (tanksessionid) references tanksession (id);

-- Mark the version of this particular schema
insert into cruiser_version(name, version)
values ('cruiser', 4);



