package com.kun;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.Executors;

import org.apache.ibatis.io.Resources;

public class Context {
	private Context() {
	}

	public static final String USER_INFO = "SK_LoginControl_login_userInfo";

	// 系统分隔符
	public static final String SYSTEM_SEPARATOR = System.getProperty("file.separator");

	private static long PROPERTIES_FILE_LAST_MODIFY_TIME = 0;// config.properties最后修改时间
	private static final String PROPERTIES_FILE = "config.properties";
	private static final long CHECK_PROPERTIES_FILE_INTERVAL = 10000l;// 轮询检查

	public static String TMP_PARAM = "default_value";// 这个可以删掉，只是提供一个样例

	static {
		init(true);
	}

	/**
	 * 初始化
	 */
	public static final void init(boolean startBgThread) {
		// 初始化顺序不能随便动
		Properties properties = initProperties();
		// 获取参数，设置参数
		String tmp = properties.getProperty("param_name", "default_value");
		TMP_PARAM = tmp;

		// 启动后台线程
		if (startBgThread) {
			initBackgroudThread();
		}
	}
	/**
	 * load config.properties文件
	 */
	private static final Properties initProperties() {
		Properties properties = new Properties();
		InputStream is = null;
		try {
			PROPERTIES_FILE_LAST_MODIFY_TIME = Resources.getResourceAsFile(PROPERTIES_FILE).lastModified();
			is = Resources.getResourceAsStream(PROPERTIES_FILE);
			properties.load(is);
			return properties;
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * 初始化缓存
	 */
	private static final void initBackgroudThread() {
		// 启动加载用户信息线程
		Executors.newSingleThreadExecutor().execute(new BackgroudProcessThread());
		// 启动加载映射文件线程
		// Executors.newSingleThreadExecutor().execute(new
		// TagFileProcessThread());
		// 启动消费者进程
		// Executors.newSingleThreadExecutor().execute(new
		// BackgroudProcessThread());
	}

	private static class BackgroudProcessThread implements Runnable {

		@Override
		public void run() {
			while (true) {
				try {
					File file = Resources.getResourceAsFile(PROPERTIES_FILE);
					if (file.lastModified() > PROPERTIES_FILE_LAST_MODIFY_TIME) {
						init(false);
						PROPERTIES_FILE_LAST_MODIFY_TIME = file.lastModified();
					}
					Thread.sleep(CHECK_PROPERTIES_FILE_INTERVAL);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}