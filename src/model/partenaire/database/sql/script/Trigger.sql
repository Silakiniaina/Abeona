CREATE OR REPLACE FUNCTION update_status_function()
RETURNS TRIGGER AS $$
DECLARE
    rec RECORD;
    current_date DATE := CURRENT_DATE;
    update_date DATE;
    begin_update_time TIME;
    end_update_time TIME;
BEGIN 

    SELECT 
        date_log,
        time_begin_log,
        time_end_log
    INTO 
        update_date,
        begin_update_time,
        end_update_time
    FROM 
        v_date_time_current_update_log;

    FOR rec IN
        SELECT
            id_chambre
        FROM
            reservation_chambres
        WHERE 
            date_debut = update_date
    LOOP

        UPDATE chambres SET varstatus = 'occupé' WHERE id_chambre = rec.id_chambre AND checking_time BETWEEN begin_update_time AND end_update_time;

    END LOOP;

    FOR rec IN
        SELECT
            id_chambre
        FROM
            reservation_chambres
        WHERE
            date_fin = update_date
    LOOP

        UPDATE chambres SET varstatus = 'libre' WHERE id_chambre = rec.id_chambre AND checking_time BETWEEN begin_update_time AND end_update_time;

    END LOOP;

END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER update_status_trigger
AFTER INSERT ON update_log
FOR EACH ROW
EXECUTE FUNCTION update_status_function();
