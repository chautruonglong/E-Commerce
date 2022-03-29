create extension if not exists "uuid-ossp";

create table customers (
   id uuid default uuid_generate_v4() not null,
    createdAt timestamp default now(),
    updatedAt timestamp default now(),
    address text,
    email text,
    name text,
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
    category text,
    description text,
    discount double precision,
    name text,
    otherImages text[],
    price double precision,
    thumbnailImage text,
    primary key (id)
);

alter table customers
   add constraint UK_rfbvkrffamfql7cjmen8v976v unique (email);
