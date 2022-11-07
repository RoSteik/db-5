USE `rostyks`;

DELIMITER //
DROP TRIGGER IF EXISTS AddUserCheckTypeOfOrder //
CREATE TRIGGER AddUserCheckTypeOfOrder
    BEFORE INSERT
    ON `rostyks`.`user` FOR EACH ROW
BEGIN
    IF(NOT EXISTS(
            SELECT id FROM `rostyks`.`type_of_order`
            WHERE id = NEW.type_of_order_id
        ))
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Foreign key error: No type_of_order with such id';
    END IF;
END //

DROP TRIGGER IF EXISTS UpdateUserCheckTypeOfOrder //
    CREATE TRIGGER UpdateUserCheckTypeOfOrder
    BEFORE UPDATE
    ON `rostyks`.`user` FOR EACH ROW
BEGIN
    IF(NOT EXISTS(
            SELECT id FROM `rostyks`.`type_of_order`
            WHERE id = NEW.type_of_order_id
        ))
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Foreign key error: No  type_of_order with such id';
    END IF;
END //

DROP TRIGGER IF EXISTS UpdateTypeOfOrderCheckId //
CREATE TRIGGER UpdateTypeOfOrderCheckId
    BEFORE UPDATE
    ON `rostyks`.`type_of_order` FOR EACH ROW
BEGIN
    IF(EXISTS(
            SELECT type_of_order_id FROM `rostyks`.`user`
            WHERE type_of_order_id = OLD.id
        ))
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Foreign key error: Can`t update row with record present in related table';
    END IF;
END //


DROP TRIGGER IF EXISTS DeleteTypeOfOrderCheckId //
CREATE TRIGGER DeleteTypeOfOrderCheckId
    BEFORE DELETE
    ON `rostyks`.`type_of_order` FOR EACH ROW
BEGIN
    IF(EXISTS(
            SELECT  type_of_order_id FROM `rostyks`.`user`
            WHERE type_of_order_id = OLD.id
        ))
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Foreign key error: Can`t delete row with record present in related table';
    END IF;
END //



/* 3 пункт 6 lab-db */
DROP TRIGGER IF EXISTS CheckRatingCardinality//
CREATE TRIGGER CheckRatingCardinality
    BEFORE INSERT
    ON `rostyks`.`user` FOR EACH ROW
BEGIN
    IF (NEW.rating < 0)
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Value error: rating can`t be less than 0';
    END IF;
END //

DROP TRIGGER IF EXISTS CheckUserName //
CREATE TRIGGER CheckUserName
    BEFORE INSERT
    ON `rostyks`.`user` FOR EACH ROW
BEGIN
    IF (NEW.name NOT RLIKE '^[a-zA-Z0-9]')
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Value error: invalid name format';
    END IF;
END //

DROP TRIGGER IF EXISTS ForbidDeleteTrip //
CREATE TRIGGER ForbidDeleteTrip
    BEFORE DELETE
    ON `rostyks`.`trip` FOR EACH ROW
BEGIN
    SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Forbidden method: you can`t delete data from table `trip`';
END //

DELIMITER ;


