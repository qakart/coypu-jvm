﻿//package Coypu.Tests.When_interacting_with_the_browser;
//
//import Coypu.BrowserSession;
//import Coypu.Configuration;
//import Coypu.ElementScope;
//import Coypu.Tests.TestDoubles.FakeDriver;
//import org.junit.Before;
//
//public abstract class BrowserInteractionTests
//{
//    protected FakeDriver driver;
//    protected FakeWaiter fakeWaiter;
//    protected BrowserSession browserSession;
//    protected SpyRobustWrapper spyRobustWrapper;
//    protected StubUrlBuilder stubUrlBuilder;
//    protected Configuration configuration;
//    protected ElementScope elementScope;
//    protected Object queryResult;
//
//    @Before
//    public void SetUp()
//    {
//        driver = new FakeDriver();
//        spyRobustWrapper = new SpyRobustWrapper();
//        fakeWaiter = new FakeWaiter();
//        stubUrlBuilder = new StubUrlBuilder();
//        configuration = new Configuration();
//        browserSession = TestSessionBuilder.Build(configuration, driver, spyRobustWrapper, fakeWaiter, new SpyRestrictedResourceDownloader(),
//                                                  stubUrlBuilder);
//
//        elementScope = browserSession.FindXPath(".");
//    }
//
//    protected Object RunQueryAndCheckTiming()
//    {
//        return RunQueryAndCheckTiming<Object>();
//    }
//
//    protected Object RunQueryAndCheckTiming(TimeSpan timeout)
//    {
//        return RunQueryAndCheckTiming<Object>(timeout);
//    }
//
//    protected T RunQueryAndCheckTiming<T>()
//    {
//        return RunQueryAndCheckTiming<T>(configuration.Timeout);
//    }
//
//    protected T RunQueryAndCheckTiming<T>(TimeSpan timeout)
//    {
//        var query = spyRobustWrapper.QueriesRan<T>().Single();
//        RunQueryAndCheckTiming(query, timeout);
//
//        return query.Result;
//    }
//
//    protected T RunQueryAndCheckTiming<T>(TimeSpan timeout, int index)
//    {
//        var query = spyRobustWrapper.QueriesRan<T>().ElementAt(index);
//        RunQueryAndCheckTiming(query, timeout);
//
//        return query.Result;
//    }
//
//    protected T RunQueryAndCheckTiming<T>(Query<T> query)
//    {
//        return RunQueryAndCheckTiming(query, configuration.Timeout);
//    }
//
//    protected T RunQueryAndCheckTiming<T>(Query<T> query, TimeSpan timeout)
//    {
//        query.Run();
//
//        queryResult = query.Result;
//
//        assertThat(query.Timeout, Is.EqualTo(timeout));
//        assertThat(query.RetryInterval, Is.EqualTo(configuration.RetryInterval));
//
//        return query.Result;
//    }
//}
//
//public class StubDriverFactory : DriverFactory
//{
//    private readonly Driver driver;
//
//    public StubDriverFactory(Driver driver)
//    {
//        this.driver = driver;
//    }
//
//    public Driver NewWebDriver(Type driverType, Drivers.Browser browser)
//    {
//        return driver;
//    }
//}
//
//public class StubUrlBuilder : UrlBuilder
//{
//    private readonly Dictionary<string, string> urls = new Dictionary<string, string>();
//
//    public string GetFullyQualifiedUrl(string virtualPath, Configuration configuration)
//    {
//        return urls[virtualPath];
//    }
//
//    public void SetStubUrl(string virtualPath, string url)
//    {
//        urls[virtualPath] = url;
//    }
//}
//
//public class FakeWaiter : Waiter
//{
//    private Action<TimeSpan> doOnWait = ms => { };
//
//    #region Waiter Members
//
//    public void Wait(TimeSpan duration)
//    {
//        doOnWait(duration);
//    }
//
//    #endregion
//
//    public void DoOnWait(Action<TimeSpan> action)
//    {
//        doOnWait = action;
//    }
//}
