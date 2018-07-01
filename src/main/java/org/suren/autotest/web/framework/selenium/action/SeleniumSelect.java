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

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.suren.autotest.web.framework.selenium.strategy.SearchStrategyUtils;

import com.surenpi.autotest.webui.action.RandomSelectAble;
import com.surenpi.autotest.webui.action.SelectAble;
import com.surenpi.autotest.webui.ui.Element;

/**
 * 下拉列表实现
 * @author <a href="http://surenpi.com">suren</a>
 */
@Component
public class SeleniumSelect implements SelectAble, RandomSelectAble
{

	@Autowired
	private SearchStrategyUtils		searchStrategyUtils;

	@Override
	public boolean selectByText(Element element, String text)
	{
		Select select = createSelect(element);
		if(select != null)
		{
			select.selectByVisibleText(text);
			return true;
		}

		return false;
	}

	@Override
	public boolean selectByValue(Element element, String value)
	{
		Select select = createSelect(element);
		if(select != null)
		{
			select.selectByValue(value);
			return true;
		}

		return false;
	}

	@Override
	public boolean selectByIndex(Element element, int index)
	{
		Select select = createSelect(element);
		if(select != null)
		{
			select.selectByIndex(index);
			return true;
		}

		return false;
	}

	@Override
	public boolean selectAll(Element element)
	{
		if(!isMultiple(element)) {
			return false;
		}
		
		List<Element> optionList = getOptions(element);
		if(optionList == null) {
			return false;
		}
		
		for(int i = 0; i < optionList.size(); i++) {
			selectByIndex(element, i);
		}
		
		return true;
	}

	@Override
	public boolean deselectByText(Element element, String text)
	{
		Select select = createSelect(element);
		if(select != null)
		{
			select.deselectByVisibleText(text);
			return true;
		}

		return false;
	}

	@Override
	public boolean deselectByValue(Element element, String value)
	{
		Select select = createSelect(element);
		if(select != null)
		{
			select.deselectByValue(value);
			return true;
		}

		return false;
	}

	@Override
	public boolean deselectByIndex(Element element, int index)
	{
		Select select = createSelect(element);
		if(select != null)
		{
			select.deselectByIndex(index);
			return true;
		}

		return false;
	}

	@Override
	public boolean deselectAll(Element element)
	{
		Select select = createSelect(element);
		if(select != null)
		{
			select.deselectAll();
			return true;
		}

		return false;
	}

	@Override
	public boolean isMultiple(Element element)
	{
		Select select = createSelect(element);
		if(select != null)
		{
			return select.isMultiple();
		}

		return false;
	}

	@Override
	public WebElement randomSelect(Element ele)
	{
		Select select = createSelect(ele);
		if(select != null)
		{
			List<WebElement> options = select.getOptions();
			if(CollectionUtils.isNotEmpty(options))
			{
				int count = options.size();
				int index = RandomUtils.nextInt(count);
				index = (index == 0 ? 1 : index); //通常第一个选项都是无效的选项

				select.selectByIndex(index);
				
				return options.get(index);
			}
		}
		
		return null;
	}

	@Override
	public List<Element> getOptions(Element element)
	{
		return null;
	}

	@Override
	public List<Element> getSelectedOptions(Element element)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEnabled(Element element)
	{
		return searchStrategyUtils.findStrategy(WebElement.class, element).search(element).isEnabled();
	}

	@Override
	public boolean isHidden(Element element)
	{
		return !searchStrategyUtils.findStrategy(WebElement.class, element).search(element).isDisplayed();
	}
	
	/**
	 * 转化为Selenium支持的下拉列表对象
	 * @param element
	 * @return
	 */
	private Select createSelect(Element element)
	{
		WebElement webEle = searchStrategyUtils.findStrategy(WebElement.class, element).search(element);
		if (webEle != null)
		{
			return new Select(webEle);
		}

		return null;
	}

}
