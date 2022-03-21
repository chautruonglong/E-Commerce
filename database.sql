create extension if not exists "uuid-ossp";

create table customers (
   id uuid default uuid_generate_v4() not null,
    createdAt timestamp default now(),
    updatedAt timestamp default now(),
    address text,
    code text,
    email text,
    password text,
    phone text,
    primary key (id)
);

create table orders (
   id uuid default uuid_generate_v4() not null,
    createdAt timestamp default now(),
    updatedAt timestamp default now(),
    customerId uuid,
    productId uuid,
    primary key (id)
);

create table products (
   id uuid default uuid_generate_v4() not null,
    createdAt timestamp default now(),
    updatedAt timestamp default now(),
    code text,
    name text,
    price double,
    primary key (id)
);

create table products_images (
   products_id uuid default uuid_generate_v4() not null,
    images text[]
);

alter table products_images
   add constraint FKbp8jstfr20o4dtau7wgu5khju
   foreign key (products_id)
   references products;
