statisticsApi
===
SELECT 
  a.click_date,
  b.hours,
  IFNULL(b.read, 0) AS 'read',
  IFNULL(b.write, 0) AS 'write' 
FROM
  (SELECT 
    '00:00' AS click_date 
  UNION
  ALL 
  SELECT 
    '01:00' AS click_date 
  UNION
  ALL 
  SELECT 
    '02:00' AS click_date 
  UNION
  ALL 
  SELECT 
    '03:00' AS click_date 
  UNION
  ALL 
  SELECT 
    '04:00' AS click_date 
  UNION
  ALL 
  SELECT 
    '05:00' AS click_date 
  UNION
  ALL 
  SELECT 
    '06:00' AS click_date 
  UNION
  ALL 
  SELECT 
    '07:00' AS click_date 
  UNION
  ALL 
  SELECT 
    '08:00' AS click_date 
  UNION
  ALL 
  SELECT 
    '09:00' AS click_date 
  UNION
  ALL 
  SELECT 
    '10:00' AS click_date 
  UNION
  ALL 
  SELECT 
    '11:00' AS click_date 
  UNION
  ALL 
  SELECT 
    '12:00' AS click_date 
  UNION
  ALL 
  SELECT 
    '13:00' AS click_date 
  UNION
  ALL 
  SELECT 
    '14:00' AS click_date 
  UNION
  ALL 
  SELECT 
    '15:00' AS click_date 
  UNION
  ALL 
  SELECT 
    '16:00' AS click_date 
  UNION
  ALL 
  SELECT 
    '17:00' AS click_date 
  UNION
  ALL 
  SELECT 
    '18:00' AS click_date 
  UNION
  ALL 
  SELECT 
    '19:00' AS click_date 
  UNION
  ALL 
  SELECT 
    '20:00' AS click_date 
  UNION
  ALL 
  SELECT 
    '21:00' AS click_date 
  UNION
  ALL 
  SELECT 
    '22:00' AS click_date 
  UNION
  ALL 
  SELECT 
    '23:00' AS click_date) a 
  LEFT JOIN 
    (SELECT 
      DATE_FORMAT(create_time, '%H:00') AS hours,
      COUNT(call_type = 1 
        OR NULL) AS 'read',
      COUNT(call_type = 2 
        OR NULL) AS 'write' 
    FROM
      `oss_api_call_log` 
    WHERE DATE_FORMAT(create_time, '%Y-%m-%d') = DATE_FORMAT('2019-03-27', '%Y-%m-%d') AND app_id IN ( #join(ids)#)
    GROUP BY hours) b 
    ON a.click_date = b.hours 
ORDER BY click_date ASC ;






sample
===
* 注释

	select #use("cols")# from oss_api_call_log  where  #use("condition")#

cols
===
	id,app_id,from_ip,store_path,call_type,create_time

updateSample
===
	
	id=#id#,app_id=#appId#,from_ip=#fromIp#,store_path=#storePath#,call_type=#callType#,create_time=#createTime#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(appId)){
	 and app_id=#appId#
	@}
	@if(!isEmpty(fromIp)){
	 and from_ip=#fromIp#
	@}
	@if(!isEmpty(storePath)){
	 and store_path=#storePath#
	@}
	@if(!isEmpty(callType)){
	 and call_type=#callType#
	@}
	@if(!isEmpty(createTime)){
	 and create_time=#createTime#
	@}
	
	