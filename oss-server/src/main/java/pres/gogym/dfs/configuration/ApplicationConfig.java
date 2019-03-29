package pres.gogym.dfs.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import pres.gogym.dfs.interceptor.AccessTokenInterceptor;
import pres.gogym.dfs.interceptor.LoginPermissionInterceptor;
import pres.gogym.dfs.plugin.fastdfs.factory.FastDFSTemplateFactory;
import pres.gogym.plugin.threadpool.ThreadPool;

@Configuration
public class ApplicationConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		// 排除不需要拦截的请求地址
		String[] paths = { "/ossDeveloper/developerLogin",
				"/ossDeveloper/developerRegister", "/file/**",
				"/accessToken/**", "/ossMaterialInfo/uploadFile",
				"/ossMaterialInfo/getFile/**","/ossMaterialInfo/downFile/**"  };

		// 注册拦截器
		registry.addInterceptor(loginPermissionInterceptor())
				.excludePathPatterns(paths);
		registry.addInterceptor(accessTokenInterceptor());

		super.addInterceptors(registry);

	}

	/**
	 * Description: 登录验证拦截器，如果有注入其他bean,需要这里显示声明，否则@Autowired注入无效
	 * 
	 * @return
	 * @see
	 */
	@Bean
	public LoginPermissionInterceptor loginPermissionInterceptor() {
		return new LoginPermissionInterceptor();
	}

	@Bean
	public AccessTokenInterceptor accessTokenInterceptor() {
		return new AccessTokenInterceptor();
	}

	/**
	 * 
	 * Description: 配置fastDfs连接工厂
	 * 
	 * @return
	 * @see
	 */
	@Bean
	public FastDFSTemplateFactory initFastDfs() {

		final String FASTDFS_CONFIG = "conf/fdfs-client.conf";
		String path = getClass().getClassLoader().getResource(FASTDFS_CONFIG)
				.getPath();

		FastDFSTemplateFactory fastDFSTemplateFactory = new FastDFSTemplateFactory();
		try {
			fastDFSTemplateFactory.init(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fastDFSTemplateFactory;
	}

	@Bean
	public ThreadPool initThreadPool() {
		// 初始化一个线程池
		return new ThreadPool(ThreadPool.FixedThread, 10);
	}

}
