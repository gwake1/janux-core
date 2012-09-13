SET @OLD_STORAGE_ENGINE=@@STORAGE_ENGINE;
SET STORAGE_ENGINE=InnoDb;

-- SOURCE ../../../payment/sql/mysql/payment/create.sql;
-- SOURCE ../../../../build/shared/sql/mysql/payment/create.sql;
SOURCE payment/create.sql;
SOURCE processor/create.sql;

SET STORAGE_ENGINE=@OLD_STORAGE_ENGINE;
