package com.hl.core.lib.network.config;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;


/**
 * @Describe:缓存管理
 * @Package: com.hl.core.lib.network.config
 * @Author: liyu
 * @Date: 2018/1/17 0017 上午 10:28
 * @Copyright: hl
 */

public class CookieManager implements CookieJar {
    private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        cookieStore.put(url.host(), cookies);
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> cookies = cookieStore.get(url.host());
        return cookies != null ? cookies : new ArrayList<Cookie>();
    }

    public void clearCookie() {
        cookieStore.clear();
    }
}
