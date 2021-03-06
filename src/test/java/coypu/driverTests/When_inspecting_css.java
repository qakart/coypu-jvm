package coypu.driverTests;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class When_inspecting_css extends DriverSpecs
{
    @Test
    public void does_not_find_missing_examples()

    {
        String shouldNotFind = "#inspectingContent p.css-missing-test";
        assertThat("Expected not to find something at: " + shouldNotFind, driver().hasCss(shouldNotFind, root()), is(false));
    }




    @Test
    public void finds_present_examples()

    {
        String shouldFind = "#inspectingContent p.css-test span";
        assertThat("Expected to find something at: " + shouldFind,driver().hasCss(shouldFind, root()), is(true));

        shouldFind = "ul#cssTest li:nth-child(3)";
        assertThat("Expected to find something at: " + shouldFind, driver().hasCss(shouldFind, root()), is(true));
    }


    @Test
    public void only_finds_visible_elements()
    {
        String shouldNotFind = "#inspectingContent p.css-test img.invisible";
        assertThat("Expected not to find something at: " + shouldNotFind, driver().hasCss(shouldNotFind, root()), is(false));
    }
}
