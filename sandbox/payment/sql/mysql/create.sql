SET @OLD_STORAGE_ENGINE=@@STORAGE_ENGINE;
SET storage_engine=InnoDb;

SOURCE payment/create.sql

SET storage_engine=@OLD_STORAGE_ENGINE;
