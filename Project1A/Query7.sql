SELECT S.name, S.snum FROM students AS S
INNER JOIN major AS M ON S.snum = M.snum
INNER JOIN register AS R ON S.snum = R.snum
INNER JOIN courses AS C ON R.course_number = C.number
WHERE M.level IN ('MS', 'PhD') AND C.name = 'database' ORDER BY S.snum;
