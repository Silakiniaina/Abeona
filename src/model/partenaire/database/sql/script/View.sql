CREATE OR REPLACE VIEW v_update_log AS
    SELECT
        actual::DATE AS date_log,
        actual::TIME AS time_log
    FROM
        update_log;

CREATE OR REPLACE VIEW v_date_time_current_update_log AS
    SELECT
        date_log,
        time_log AS time_begin_log,
        time_log + '01:00:00' AS time_end_log
    FROM
        v_update_log
    ORDER BY
        date_log DESC,
        time_log DESC
    LIMIT 1;