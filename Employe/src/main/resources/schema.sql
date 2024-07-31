create table Employeee(
id bigint not null, 	
name varchar (23),
age int ,

primary key(id)
);

create table Addresss(
	id bigint not null,
	employee_id bigint,
	city varchar(30),
	state varchar(30),
	primary key(id),
	foreign key(employee_id) REFERENCES Employeee(id)
);