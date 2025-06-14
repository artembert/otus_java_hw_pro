-- Fix sequences after manual inserts with explicit IDs
-- This ensures the sequences start from the correct value for auto-generated IDs

SELECT setval('client_id_seq', (SELECT MAX(id) FROM client));
SELECT setval('address_id_seq', (SELECT MAX(id) FROM address));
SELECT setval('phone_id_seq', (SELECT MAX(id) FROM phone)); 