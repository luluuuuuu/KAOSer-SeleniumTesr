/*
 *
 *  * Copyright 2002-2007 the original author or authors.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package org.suren.autotest.web.framework.settings;

/**
 * @author suren
 */
public class SessionStorageConfig
{
    /** 跳过登录 */
    private boolean skipLogin;
    /** 自动加载 */
    private boolean autoLoad;
    /** 账户名 */
    private String account;

    public boolean isSkipLogin()
    {
        return skipLogin;
    }

    public void setSkipLogin(boolean skipLogin)
    {
        this.skipLogin = skipLogin;
    }

    public boolean isAutoLoad()
    {
        return autoLoad;
    }

    public void setAutoLoad(boolean autoLoad)
    {
        this.autoLoad = autoLoad;
    }

    public String getAccount()
    {
        return account;
    }

    public void setAccount(String account)
    {
        this.account = account;
    }
}
