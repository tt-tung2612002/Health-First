drop database if exists health_first;

create database if not exists health_first;

use health_first;

create table user_type ( id integer, name varchar(255) );

alter table user_type add primary key(id);

create table user (
    id integer,
    user_type_id integer,
    display_name varchar(255),
    username varchar(255),
    password varchar(255)
);

alter table user add constraint pk_user primary key(id);

alter table
    user
add
    constraint fk_user_type foreign key(user_type_id) references user_type(id);

alter table user add constraint uni_username unique(username);

create table role ( id integer, name varchar(255) );

alter table role add constraint pk_role primary key(id);

alter table role add constraint uni_role unique(name);

create table user_role (user_id integer, role_id integer);

alter table
    user_role
add
    constraint pk_user_role primary key(user_id, role_id);

alter table
    user_role
add
    constraint fk_user_role_user foreign key(user_id) references user(id);

alter table
    user_role
add
    constraint fk_user_role_role foreign key(role_id) references role(id);

create table province ( id integer, name varchar(255) );

create table district (
    id integer,
    province_id integer,
    name varchar(255)
);

create table ward (
    id integer,
    name varchar(255),
    district_id integer
);

alter table province add constraint pk_province primary key(id);

alter table district add constraint pk_district primary key(id);

alter table ward add constraint pk_ward primary key(id);

alter table
    district
add
    constraint fk_district_province foreign key(province_id) references province(id);

alter table
    ward
add
    constraint fk_ward_district foreign key(district_id) references district(id);

create table address (
    id integer,
    province_id integer,
    district_id integer,
    ward_id integer
);

alter table address add constraint pk_address primary key(id);

alter table
    address
add
    constraint fk_address_province foreign key(province_id) references province(id);

alter table
    address
add
    constraint fk_address_district foreign key(district_id) references district(id);

alter table
    address
add
    constraint fk_address_ward foreign key(ward_id) references ward(id);

create table user_region_management (user_id integer, address_id integer);

alter table
    user_region_management
add
    constraint pk_user_region_management primary key(user_id, address_id);

alter table
    user_region_management
add
    constraint fk_user_region_management_user foreign key(user_id) references user(id);

alter table
    user_region_management
add
    constraint fk_user_region_management_address foreign key(address_id) references address(id);

create table business_type (id integer, name varchar(255));

alter table
    business_type
add
    constraint pk_business_type primary key(id);

create table facility_state (id integer, name varchar(255));

alter table
    facility_state
add
    constraint pk_facility_state primary key(id);

create table facility (
    id integer,
    facility_code varchar(255),
    name varchar(255),
    address_id integer,
    business_type_id integer,
    state_id integer
);

alter table facility add constraint pk_facility primary key(id);

alter table
    facility
add
    constraint fk_facility_address foreign key(address_id) references address(id);

alter table
    facility
add
    constraint fk_facility_business_type foreign key(business_type_id) references business_type(id);

alter table
    facility
add
    constraint fk_facility_state foreign key(state_id) references facility_state(id);

create table certificate_state (id integer, name varchar(255));

alter table
    certificate_state
add
    constraint pk_certificate_state primary key(id);

create table certificate (
    id integer,
    facility_id integer,
    certificate_number varchar(255),
    published_date date,
    expired_date date,
    state_id integer
);

alter table
    certificate
add
    constraint pk_certificate primary key(id);

alter table
    certificate
add
    constraint fk_certificate_facility foreign key(facility_id) references facility(id);

alter table
    certificate
add
    constraint fk_certificate_state foreign key(state_id) references certificate_state(id);

create table plan (
    id integer,
    name varchar(255),
    description varchar(255),
    created_date date,
    created_user_id integer
);

alter table plan add constraint pk_plan primary key(id);

alter table
    plan
add
    constraint fk_plan_created_user foreign key(created_user_id) references user(id);

create table activity_state (id integer, name varchar(255));

alter table
    activity_state
add
    constraint pk_activity_state primary key(id);

create table activity_result (id integer, name varchar(255));

alter table
    activity_result
add
    constraint pk_activity_result primary key(id);

create table activity (
    id integer,
    facility_id integer,
    plan_id integer,
    state_id integer,
    result_id integer,
    name varchar(255),
    created_date date,
    created_user_id integer,
    started_date date,
    ended_date date,
    conclusion varchar(255)
);

alter table activity add constraint pk_activity primary key(id);

alter table
    activity
add
    constraint fk_activity_facility foreign key(facility_id) references facility(id);

alter table
    activity
add
    constraint fk_activity_plan foreign key(plan_id) references plan(id);

alter table
    activity
add
    constraint fk_activity_state foreign key(state_id) references activity_state(id);

alter table
    activity
add
    constraint fk_activity_result foreign key(result_id) references activity_result(id);

create table food (
    id integer,
    name varchar(255),
    description varchar(255)
);

alter table food add constraint pk_food primary key(id);

create table sample_result (id integer, name varchar(255));

alter table
    sample_result
add
    constraint pk_sample_result primary key(id);

create table sample_state (id integer, name varchar(255));

alter table
    sample_state
add
    constraint pk_sample_state primary key(id);

create table inspection_unit (id integer, name varchar(255));

alter table
    inspection_unit
add
    constraint pk_inspection_unit primary key(id);

create table sample (
    id integer,
    sample_code varchar(255),
    food_id integer,
    activity_id integer,
    status_id integer,
    result_id integer,
    inspection_unit_id integer,
    created_date date,
    resulted_date date
);

alter table sample add constraint pk_sample primary key(id);

alter table
    sample
add
    constraint fk_sample_food foreign key(food_id) references food(id);

alter table
    sample
add
    constraint fk_sample_activity foreign key(activity_id) references activity(id);

alter table
    sample
add
    constraint fk_sample_status foreign key(status_id) references sample_state(id);

alter table
    sample
add
    constraint fk_sample_result foreign key(result_id) references sample_result(id);

alter table
    sample
add
    constraint fk_sample_inspection_unit foreign key(inspection_unit_id) references user(id);