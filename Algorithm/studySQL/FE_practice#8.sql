/*
KL_SCHEDULE 테이블은 K-리그에서 예정 혹은 진행된 모든 경기에 대한 정보를 담은 테이블입니다.
이 테이블의 구조는 아래와 같으며, STADIUM_ID, SCHE_DATE, GUBUN, HOMETEAM_ID, AWAYTEAM_ID, HOME_SCORE, AWAY_SCORE는 각각 경기가 진행된 경기장의 아이디, 경기 날짜, 경기 진행 여부, 홈팀 아이디, 원정팀 아이디, 홈팀 점수, 원정팀 점수를 나타냅니다.

NAME	TYPE	NULLABLE
STADIUM_ID	VARCHAR(N)	FALSE
SCHE_DATE	DATE	FALSE
GUBUN	VARCHAR(N)	FALSE
HOMETEAM_ID	VARCHAR(N)	FALSE
AWAYTEAM_ID	VARCHAR(N)	FALSE
HOME_SCORE	INTEGER	TRUE
AWAY_SCORE	INTEGER	TRUE

2012년 4월의 경기 횟수를 1일부터 30일까지 날짜별로 검색하는 SQL문을 작성하세요.
단, 경기가 없는 날에는 경기 횟수를 0으로 출력합니다.
출력 컬럼은 날짜, 경기횟수 순으로 하며, 이때 결과는 날짜의 오름차순으로 정렬합니다.
*/

SELECT DATE_FORMAT(DateTable.TheDate, '%Y-%m-%d') AS TheDate, IFNULL(COUNT(SCH.SCHE_DATE), 0) AS 경기횟수
FROM (
  SELECT DATE(ADDDATE('2012-04-01', @num:=@num+1)) AS TheDate
  FROM (
    SELECT @num:= -1
  ) num, KL_SCHEDULE
  WHERE @num < DATEDIFF('2012-04-30', '2012-04-01')
) DateTable
LEFT JOIN KL_SCHEDULE SCH ON DateTable.TheDate = DATE(SCH.SCHE_DATE)
GROUP BY DateTable.TheDate
ORDER BY DateTable.TheDate ASC;
