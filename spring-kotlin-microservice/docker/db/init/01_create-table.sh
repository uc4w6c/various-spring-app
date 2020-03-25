#!/bin/bash
psql -U root -d testdb << "EOSQL"
create table cats (name VARCHAR(10) Primary key, age INTEGER);
EOSQL
