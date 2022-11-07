USE `rostyks`;

/*2a, 2c, 2d 6 lab db*/

DROP PROCEDURE IF EXISTS CarTestInserts;
DELIMITER //
CREATE PROCEDURE CarTestInserts(
    IN new_car_brand VARCHAR(50),
    IN new_car_clas VARCHAR(50))
BEGIN
    DECLARE max_id INT;
    DECLARE idx INT;
    SELECT MAX(id) + 1 INTO max_id FROM `car`;
    IF max_id IS NULL THEN
        SET max_id = 1;
    END IF;
    SET idx = 1;
    label1:
    WHILE idx < 11
        DO
            INSERT INTO `car` (brand, clas)
            VALUES (CONCAT(new_car_brand, max_id), CONCAT(new_car_clas, max_id));
            SET max_id = max_id + 1;
            SET idx = idx + 1;
            ITERATE label1;
        END WHILE;
END //
DELIMITER ;

DROP PROCEDURE IF EXISTS UserParamInsert;
DELIMITER //
CREATE PROCEDURE UserParamInsert(
    IN new_name VARCHAR(50),
    IN new_rating INT)
BEGIN
    INSERT INTO `user` (name, rating) VALUES (new_name, new_rating);
    SELECT id, name, rating FROM `user` WHERE name = new_name;
END //
DELIMITER ;

DROP PROCEDURE IF EXISTS CalcAverageRating;
DELIMITER //
CREATE PROCEDURE CalcAverageRating()
BEGIN
    DECLARE label VARCHAR(20);
    SELECT GetAverageRating() AS average_rating;
END //



/*2b*/
DROP PROCEDURE IF EXISTS AddDriverHasCarRelationship //
CREATE PROCEDURE AddDriverHasCarRelationship(
    IN dr_name VARCHAR(50),
    IN cr_brand VARCHAR(50))
BEGIN
    DECLARE dr_id, cr_id INT;
    SELECT id INTO dr_id FROM `driver` WHERE name = dr_name;
    SELECT id INTO cr_id FROM `car` WHERE brand = cr_brand;
    IF (dr_id IS NULL)
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Null value: no such driver found';
    END IF;
    IF (cr_id IS NULL)
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Null value: no such car found';
    END IF;
    INSERT INTO `driver_has_car` (driver_id, car_id) VALUES (dr_id, cr_id);
END //


DROP PROCEDURE IF EXISTS CreateTablesWithCursor //
CREATE PROCEDURE CreateTablesWithCursor()
BEGIN
    DECLARE done BOOL DEFAULT false;
    DECLARE car_brand VARCHAR(50);
    DECLARE my_cursor CURSOR
        FOR SELECT brand FROM `car`;

    DECLARE CONTINUE HANDLER
        FOR NOT FOUND SET done = true;

    OPEN my_cursor;
    my_loop: LOOP
        FETCH my_cursor INTO car_brand;
        IF (done = true) THEN LEAVE my_loop;
        END IF;
        SET @temp_query = CONCAT('CREATE TABLE IF NOT EXISTS ', car_brand, DATE_FORMAT(NOW(), '_%Y_%m_%d_%H_%i_%s'), ' (', car_brand, '1 INT, ', car_brand, '2 BOOL);');
        PREPARE my_query FROM @temp_query;
        EXECUTE my_query;
        DEALLOCATE PREPARE my_query;
    END LOOP;
    CLOSE my_cursor;
END //

DELIMITER ;

