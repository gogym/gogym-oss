
lockForAppId
===
select #use("cols")# from oss_app_info  where app_id=#appId# for update


sample
===
* 注释

	select #use("cols")# from oss_app_info  where  #use("condition")#

cols
===
	id,dev_id,name,code,app_id,app_secret,intro,use_space,use_space_str,func_write,func_read,create_time

updateSample
===
	
	id=#id#,dev_id=#devId#,name=#name#,code=#code#,app_id=#appId#,app_secret=#appSecret#,intro=#intro#,use_space=#useSpace#,use_space_str=#useSpaceStr#,func_write=#funcWrite#,func_read=#funcRead#,create_time=#createTime#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(devId)){
	 and dev_id=#devId#
	@}
	@if(!isEmpty(name)){
	 and name=#name#
	@}
	@if(!isEmpty(code)){
	 and code=#code#
	@}
	@if(!isEmpty(appId)){
	 and app_id=#appId#
	@}
	@if(!isEmpty(appSecret)){
	 and app_secret=#appSecret#
	@}
	@if(!isEmpty(intro)){
	 and intro=#intro#
	@}
	@if(!isEmpty(useSpace)){
	 and use_space=#useSpace#
	@}
	@if(!isEmpty(useSpaceStr)){
	 and use_space_str=#useSpaceStr#
	@}
	@if(!isEmpty(createTime)){
	 and create_time=#createTime#
	@}
	
	