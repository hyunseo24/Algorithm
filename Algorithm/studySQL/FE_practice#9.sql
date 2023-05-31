/*
각 팀에서 키가 제일 작은 선수를 검색하는 SQL문을 작성하세요.
키가 제일 작은 선수가 여럿 있을 때는 모두 출력합니다.
출력 컬럼은 TEAM_NAME, PLAYER_NAME, POSITION, HEIGHT 순으로 합니다.
이때 결과는 HEIGHT, TEAM_NAME, PLAYER_NAME의 오름차순으로 정렬합니다.
*/

SELECT KT.TEAM_NAME, KP.PLAYER_NAME, KP.POSITION, KP.HEIGHT
FROM KL_PLAYER KP
JOIN KL_TEAM KT ON KP.TEAM_ID = KT.TEAM_ID
WHERE (KT.TEAM_ID, KP.HEIGHT) IN (
        SELECT TEAM_ID, MIN(HEIGHT)
        FROM KL_PLAYER
        GROUP BY TEAM_ID
      )
ORDER BY KP.HEIGHT ASC, KT.TEAM_NAME ASC, KP.PLAYER_NAME ASC;
