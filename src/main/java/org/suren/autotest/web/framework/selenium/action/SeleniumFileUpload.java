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

package org.suren.autotest.web.framework.selenium.action;

import java.io.File;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.suren.autotest.web.framework.autoit3.AutoItCmd;
import org.suren.autotest.web.framework.selenium.SeleniumEngine;
import org.suren.autotest.web.framework.selenium.strategy.SearchStrategyUtils;

import com.surenpi.autotest.webui.action.ClickAble;
import com.surenpi.autotest.webui.action.FileUploadAble;
import com.surenpi.autotest.webui.action.RandomFileUploadAble;
import com.surenpi.autotest.webui.core.RandomFile;
import com.surenpi.autotest.webui.ui.Element;

/**
 * 利用Selenium实现文件上传
 * @author <a href="http://surenpi.com">suren</a>
 */
@Component
public class SeleniumFileUpload implements FileUploadAble, RandomFileUploadAble
{
	private static final Logger logger = LoggerFactory.getLogger(SeleniumFileUpload.class);

	@Autowired
	private SeleniumEngine			engine;
	@Autowired
	private SearchStrategyUtils		searchStrategyUtils;
	@Autowired
	private ClickAble clickAble;
	@Autowired
	private RandomFile randomFile;
	
	@Override
	public boolean isEnabled(Element element)
	{
		return clickAble.isEnabled(element);
	}

	@Override
	public boolean isHidden(Element element)
	{
		return clickAble.isHidden(element);
	}

	@Override
	public boolean upload(Element element, URL url)
	{
		logger.error("Not support upload from a url.");
		return false;
	}
	
	@Override
	public boolean upload(Element element, final File file)
	{
		WebElement webEle = findElement(element);
		if(webEle != null)
		{
			logger.info("File upload, element is already found.");
			ExecutorService executor = Executors.newSingleThreadExecutor();
			
			final AutoItCmd autoItCmd = new AutoItCmd();
			try
			{
				Future<?> future = executor.submit(new Runnable()
				{
					
					@Override
					public void run()
					{
						try
						{
							autoItCmd.execFileChoose(file);
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
				});
				
				synchronized (autoItCmd)
				{
					autoItCmd.wait();
				}
					
				click(element);
				
				if(!future.isDone())
				{
					future.get(30, TimeUnit.SECONDS);
				}
				
				return true;
			}
			catch (InterruptedException | ExecutionException | TimeoutException e)
			{
				logger.error("File uplod error.", e);
				e.printStackTrace();
			}
			finally
			{
				autoItCmd.close();
				executor.shutdown();
			}
		}
		else
		{
			logger.error("Can not found element, when prepare to upload file.");
		}
		
		return false;
	}

	@Override
	public boolean upload(Element element)
	{
		File tmpFile = randomFile.createFile();
		
		return upload(element, tmpFile);
	}
	
	/**
	 * 超找元素
	 * @param element
	 * @return
	 */
	private WebElement findElement(Element element)
	{
		return searchStrategyUtils.findStrategy(WebElement.class, element).search(element);
	}

	@Override
	public boolean click(Element element)
	{
		clickAble.click(element);
		return true;
	}
}
