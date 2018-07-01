/*
 * Copyright 2002-2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.suren.autotest.web.framework.log;

/**
 * 日志相关的常量
 * @author suren
 * @since 2016年8月22日 上午8:39:31
 */
public interface LoggerConstants
{
	String IMG_LOG_CONF_FILE_NAME = "image4Search.properties";
	String IMG_LOG_DIR = "image4SearchDir";
	
	/** 当前测试流程的唯一标识 */
	String PROGRESS_IDENTIFY = "progress_identify";
	/** 区分同一流程多次测试的唯一标识 */
	String APP_IDENTIFY = "application_identify";
	
	/** 当前测试流程中的gif图片路径 */
	String GIF_CURRENT_PATH = "gif_file_current_path";
}
