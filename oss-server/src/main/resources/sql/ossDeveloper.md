sample
===
* 注释
	select #use("cols")# from oss_developer  where  #use("condition")#


findByUsername
===
	select #use("cols")# from oss_developer where username=#username#


countData
===
SELECT 
  (SELECT 
    COUNT(*) 
  FROM
    `oss_app_info`) app_count,
  (SELECT 
    COUNT(*) 
  FROM
    `oss_material_info`) meta_count,
    use_space_str len,func_write,func_read
FROM
  oss_app_info WHERE dev_id=#devId#;



-- 分割线，下面是一些公共使用的部分-------------------------------------------------------


cols
===
	id,username,password,nickname,email,tel,intro,status,create_time

updateSample
===
	id=#id#,username=#username#,password=#password#,nickname=#nickname#,email=#email#,tel=#tel#,intro=#intro#,status=#status#,create_time=#createTime#

condition
===
	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(username)){
	 and username=#username#
	@}
	@if(!isEmpty(password)){
	 and password=#password#
	@}
	@if(!isEmpty(nickname)){
	 and nickname=#nickname#
	@}
	@if(!isEmpty(email)){
	 and email=#email#
	@}
	@if(!isEmpty(tel)){
	 and tel=#tel#
	@}
	@if(!isEmpty(intro)){
	 and intro=#intro#
	@}
	@if(!isEmpty(status)){
	 and status=#status#
	@}
	@if(!isEmpty(createTime)){
	 and create_time=#createTime#
	@}
	
	