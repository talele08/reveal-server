
INSERT INTO lookup_intervention_type(identifier, name, code, entity_status, created_by,
                                     created_datetime, modified_by, modified_datetime)
VALUES (uuid_generate_v4(), 'SURVEY', 'SURVEY',
        'ACTIVE', '71fca736-c156-40bc-9de5-3ae04981fbc9', current_timestamp,
        '71fca736-c156-40bc-9de5-3ae04981fbc9', current_timestamp);