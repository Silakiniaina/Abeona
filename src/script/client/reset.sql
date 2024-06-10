\c postgres

DROP DATABASE abeona; 
CREATE DATABASE abeona with owner sanda;

\c abeona;

\i extensions.sql 
\i sequences.sql 
\i functions.sql 
\i tables.sql 
\i datas.sql 
