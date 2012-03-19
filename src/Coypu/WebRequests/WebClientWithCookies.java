﻿package Coypu.WebRequests;
import Coypu.WebRequests.RestrictedResourceDownloader;

public class WebClientWithCookies extends WebClient implements RestrictedResourceDownloader
{
    private IEnumerable<Cookie> requestCookies;
    private final WebRequestCookieInjector webRequestCookieInjector;

    public WebClientWithCookies()
    {
        webRequestCookieInjector = new WebRequestCookieInjector();
    }

    public void SetCookies(Enumerable<Cookie> cookies)
    {
        requestCookies = cookies;
    }

    protected WebRequest GetWebRequest(Uri address)
    {
        return webRequestCookieInjector.InjectCookies(base.GetWebRequest(address), requestCookies);
    }
}
