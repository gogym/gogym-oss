sample
===
* 注释

	select #use("cols")# from oss_sys_user  where  #use("condition")#

cols
===
	id,username,password,intro,create_time

updateSample
===
	
	id=#id#,username=#username#,password=#password#,intro=#intro#,create_time=#createTime#

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
	@if(!isEmpty(intro)){
	 and intro=#intro#
	@}
	@if(!isEmpty(createTime)){
	 and create_time=#createTime#
	@}
	
	