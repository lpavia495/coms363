SELECT
    c.number AS course_number,
    c.name AS course_name,
    AVG(r.grade) AS average_grade
FROM
    courses c
INNER JOIN
    register r ON c.number = r.course_number
WHERE
    r.regtime < NOW() -- Assuming NOW() returns the current timestamp
GROUP BY
    c.number, c.name
ORDER BY
    c.number;
