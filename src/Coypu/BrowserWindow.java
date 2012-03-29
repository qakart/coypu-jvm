package Coypu;

import Coypu.Actions.AcceptModalDialog;
import Coypu.Actions.CancelModalDialog;
import Coypu.Finders.ElementFinder;
import Coypu.Queries.HasDialogQuery;
import Coypu.Queries.HasNoDialogQuery;
import Coypu.Robustness.RobustWrapper;
import Coypu.Robustness.Waiter;

/// <summary>
/// A browser window belonging to a particular browser session
/// </summary>
public class BrowserWindow extends DriverScope {

    public BrowserWindow(Configuration configuration, ElementFinder elementFinder, Driver driver, RobustWrapper robustWrapper, Waiter waiter, UrlBuilder urlBuilder) {
        super(configuration, elementFinder, driver, robustWrapper, waiter, urlBuilder);
    }

    /// <summary>
    /// Check that a dialog with the specified text appears within the <see cref="Configuration.Timeout"/>
    /// </summary>
    /// <param name="withText">Dialog text</param>
    /// <returns>Whether an element appears</returns>
    public boolean HasDialog(String withText) {
        return HasDialog(withText, configuration);
    }

    public boolean HasDialog(String withText, Options options) {
        return Query(new HasDialogQuery(driver, withText, this, SetOptions(options)));
    }

    /// <summary>
    /// Check that a dialog with the specified is not present. Returns as soon as the dialog is not present, or when the <see cref="Configuration.Timeout"/> is reached.
    /// </summary>
    /// <param name="withText">Dialog text</param>
    /// <returns>Whether an element does not appears</returns>
    public boolean HasNoDialog(String withText) {
        return HasNoDialog(withText,configuration);
    }

    public boolean HasNoDialog(String withText, Options options) {
        return Query(new HasNoDialogQuery(driver, withText, this, SetOptions(options)));
    }

    /// <summary>
    /// Accept the first modal dialog to appear within the <see cref="Configuration.Timeout"/>
    /// </summary>
    /// <exception cref="T:Coypu.MissingHtmlException">Thrown if the dialog cannot be found</exception>
    public void AcceptModalDialog() {
        AcceptModalDialog(configuration);
    }

    public void AcceptModalDialog(Options options) {
        RetryUntilTimeout(new AcceptModalDialog(this, driver, SetOptions(options)));
    }

    /// <summary>
    /// Cancel the first modal dialog to appear within the <see cref="Configuration.Timeout"/>
    /// </summary>
    /// <exception cref="T:Coypu.MissingHtmlException">Thrown if the dialog cannot be found</exception>
    public void CancelModalDialog() {
        CancelModalDialog(configuration);
    }
    public void CancelModalDialog(Options options) {
        RetryUntilTimeout(new CancelModalDialog(this, driver, SetOptions(options)));
    }

    /// <summary>
    /// Visit a url in the browser
    /// </summary>
    /// <param name="virtualPath">Virtual paths will use the Configuration.AppHost,Port,SSL settings. Otherwise supply a fully qualified URL.</param>
    public void Visit(String virtualPath) {
        driver.Visit(urlBuilder.GetFullyQualifiedUrl(virtualPath, configuration));
    }

    /// <summary>
    /// Fill in a previously found text field
    /// </summary>
    /// <param name="element">The text field</param>
    /// <returns>With</returns>
    public FillInWith FillIn(ElementScope element) {
        return FillIn(element,configuration);
    }

    public FillInWith FillIn(ElementScope element, Options options) {
        return new FillInWith(element, driver, robustWrapper, this, SetOptions(options));
    }
}
