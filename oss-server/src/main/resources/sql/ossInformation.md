sample
===
* 注释

	select #use("cols")# from oss_information  where  #use("condition")#

cols
===
	id,root,invoking_root,nginx_log_path,create_time,modified_time

updateSample
===
	
	id=#id#,root=#root#,invoking_root=#invokingRoot#,nginx_log_path=#nginxLogPath#,create_time=#createTime#,modified_time=#modifiedTime#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(root)){
	 and root=#root#
	@}
	@if(!isEmpty(invokingRoot)){
	 and invoking_root=#invokingRoot#
	@}
	@if(!isEmpty(nginxLogPath)){
	 and nginx_log_path=#nginxLogPath#
	@}
	@if(!isEmpty(createTime)){
	 and create_time=#createTime#
	@}
	@if(!isEmpty(modifiedTime)){
	 and modified_time=#modifiedTime#
	@}
	
	