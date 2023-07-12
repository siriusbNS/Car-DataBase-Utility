create schema if not exists mydatabase;
create table mydatabase.CarBrand (
    brandId SERIAL,
    brandName varchar(30) not null,
    foundingDate DATE,
    PRIMARY KEY (brandId)
);
create table mydatabase.CarModel (
    modelId SERIAL PRIMARY KEY,
    modelName varchar(30) not null ,
    modelLength int not null,
    modelWidth int not null,
    bodyType varchar(30) not null,
    brandId_ INTEGER,
    FOREIGN KEY (brandId_) references mydatabase.CarBrand(brandId)
    ON DELETE SET NULL
);