SELECT COUNT(*) AS num_female_software_eng_students
FROM students
WHERE Gender = 'F'
AND (
    snum IN (
        SELECT snum
        FROM major
        WHERE name = 'Software Engineering'
    )
    OR
    snum IN (
        SELECT snum
        FROM minor
        WHERE name = 'Software Engineering'
    )
);