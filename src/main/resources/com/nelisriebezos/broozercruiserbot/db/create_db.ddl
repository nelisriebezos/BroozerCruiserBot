create table cruiser_version (name varchar(200), version int not null, primary key (name));
create table cruiser_sequences (sequence_name varchar(50) not null, next_val bigint not null, primary key (sequence_name));



-- Mark the version of this particular schema
insert into cruiser_version(name, version) values('cruiser', 1);
