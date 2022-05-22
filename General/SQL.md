## Case statement

The CASE statement goes through conditions and <u>returns a value when the first condition is met</u>. So, once a condition is true, it will stop reading and return the result. If no conditions are true, it returns the value in the ELSE clause.

```sql
CASE
    WHEN condition1 THEN result1
    WHEN condition2 THEN result2
    WHEN conditionN THEN resultN
    ELSE result
END;
SELECT OrderID, Quantity,
	CASE WHEN Quantity > 30 THEN 'The quantity is greater than 30'
	WHEN Quantity = 30 THEN 'The quantity is 30'
	ELSE 'The quantity is under 30'
END AS QuantityText
FROM OrderDetails;
```





SQL Online : https://www.w3schools.com/sql/trysql.asp?filename=trysql_op_in

### Example

On an online recruiting platform, each recruiting company can make a request for the candidates to complete a personalized skill assessment. The assessment can contain tasks in three categories: SQL, Algo and BugFixing. Following the assessment, the company receives a report containing, for each candidate, their declared years of experience (an integer between 0 and 100) and their score in each category. The score is the number of points from 0 to 100, or NULL, which means there was no task in this category. 

You are given a table, assessments, with the following structure: 

```sql
create table assessments ( 
    id integer not null, 
    experience integer not null, 
    sql integer, 
    algo integer, 
    bug_fixing integer, 
    unique(id) 
);
```

Your task is to write an SQL query that, for each different length of experience, counts the number of candidates with precisely that amount of experience and how many of them got a perfect score in each category in which they were requested to solve tasks (so a NULL score is here treated as a perfect score). 

Your query should return a table containing the following columns: exp (each candidate's years of experience), max (number of assessments achieving the maximum score), count (total number of assessments). Rows should be ordered by decreasing exp. 

```sql
SELECT * FROM [assessments]
```

| id   | experience | sql    | algo   | bug_fixing |
| :--- | :--------- | :----- | :----- | :--------- |
| 1    | 3          | 100    | *null* | 50         |
| 2    | 5          | *null* | 100    | 100        |
| 3    | 1          | 100    | 100    | 100        |
| 4    | 5          | 100    | 50     | *null*     |
| 5    | 5          | 100    | 100    | 100        |

```sql
select experience as exp, SUM(
    CASE WHEN (sql == 100 or sql is null) and (algo == 100 or algo is null) and (bug_fixing == 100 or bug_fixing is null) 
    THEN 1 ELSE 0 END ) as max, 
count(*) as count from assessments group by 1 order by 1 DESC;
```

| exp  | max  | count |
| :--- | :--- | :---- |
| 5    | 2    | 3     |
| 3    | 0    | 1     |
| 1    | 1    | 1     |

**Example2**: students table (name, class, maths mark, total mark) How many students passed maths and total for each class.

