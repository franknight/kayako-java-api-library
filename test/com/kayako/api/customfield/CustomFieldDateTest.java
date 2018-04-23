package com.kayako.api.customfield;

import static org.easymock.EasyMock.mock;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.sameInstance;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Before;
import org.junit.rules.ErrorCollector;

import java.util.Map;
import java.util.HashMap;
import java.sql.Timestamp;
import java.text.ParseException;
import com.kayako.api.util.Helper;
import com.kayako.api.rest.RawArrayElement;
import com.kayako.api.exception.KayakoException;
import com.kayako.api.configuration.Configuration;

public class CustomFieldDateTest {
    private static final String API_KEY = "API_KEY";
    private static final String SECRET_KEY = "SECRET_KEY";
    private static final String API_URL = "API_URL";
    private static final String CONTROLLER = "CONTROLLER";
    private static final String TITLE_KEY = "title";
    private static final String NAME_KEY = "name";
    private static final String TITLE_VALUE = "TITLE";
    private static final String NAME_VALUE = "NAME_VALUE";
    private static final String IMPROPER_DATE_STR = "111,222";
    private static final int INT_ID = 1;
    private static final String ELEMENT_NAME = "ELEMENT_NAME"; // apart from value of objectXmlName in Custom Field

    private String dateString;
    private static Timestamp timestamp;
    private Map<String, String> attributes;
    private CustomFieldDate customFieldDate;
    private RawArrayElement rawArrayElement;
    private CustomFieldGroup customFieldGroupMock;

    @Rule
    public final ErrorCollector collector = new ErrorCollector();

    @Before
    public void setUp() throws ParseException {
        customFieldGroupMock = mock(CustomFieldGroup.class);
        customFieldDate = new CustomFieldDate(customFieldGroupMock);
        Configuration.init(API_URL, API_KEY, SECRET_KEY);

        attributes = new HashMap<>();
        attributes.put(TITLE_KEY, TITLE_VALUE);
        attributes.put(NAME_KEY, NAME_VALUE);

        dateString = Helper.getDateString(System.currentTimeMillis());
        timestamp = new Timestamp(Helper.getTimeStampFromDateString(dateString));
    }

    @Test(expected = KayakoException.class)
    public void givenIdWhenGetThenKayakoException() throws KayakoException {
        customFieldDate.get(INT_ID);
    }

    @Test(expected = KayakoException.class)
    public void givenControllerWhenRefreshThenKayakoException() throws KayakoException {
        customFieldDate.refresh(CONTROLLER);
    }

    @Test
    public void givenTimestampWhenSetTimestampThenCustomFieldDate(){
        collector.checkThat(customFieldDate.setTimestamp(timestamp), sameInstance(customFieldDate));
        collector.checkThat(customFieldDate.getTimestamp(), equalTo(timestamp));
    }

    @Test
    public void givenDateStringWhenSetValueThenCustomFieldDate() throws KayakoException {
        collector.checkThat(customFieldDate.setValue(dateString), sameInstance(customFieldDate));
        collector.checkThat(customFieldDate.getValue(), equalTo(dateString));
    }

    @Test(expected = KayakoException.class)
    public void givenImproperDateStrWhenSetValueThenParseException() throws KayakoException {
        customFieldDate.setValue(IMPROPER_DATE_STR);
    }

    @Test
    public void givenDateStrWhenSetDateThenCustomFieldDate() throws ParseException {
        collector.checkThat(customFieldDate.setDate(dateString), sameInstance(customFieldDate));
        collector.checkThat(customFieldDate.getDate(), equalTo(dateString));
    }

    @Test (expected = KayakoException.class)
    public void givenRawArrayElementWhenPopulateThenKayakoExpection() throws KayakoException {
        rawArrayElement = new RawArrayElement(ELEMENT_NAME);
        customFieldDate.populate(rawArrayElement);
    }

    @Test
    public void givenRawArrayElementWhenPopulateThenCustomFieldDate() throws KayakoException {
        rawArrayElement = new RawArrayElement(CustomField.getObjectXmlName(), attributes, dateString);
        customFieldDate.populate(rawArrayElement);
        collector.checkThat(customFieldDate.getTitle(), equalTo(TITLE_VALUE));
        collector.checkThat(customFieldDate.getName(), equalTo(NAME_VALUE));
        collector.checkThat(customFieldDate.getTimestamp(), equalTo(timestamp));
    }

    @Test
    public void givenRawArrayElementWithImproperDateStrWhenPopulateThenCustomFieldDate() throws KayakoException {
        rawArrayElement = new RawArrayElement(CustomField.getObjectXmlName(), attributes, IMPROPER_DATE_STR);
        collector.checkThat(customFieldDate.populate(rawArrayElement), sameInstance(customFieldDate));
    }
}
