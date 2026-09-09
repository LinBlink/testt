use test;
select course, max(score) as maxs, avg(score) as avgs from score GROUP BY course ORDER BY avgs ;
