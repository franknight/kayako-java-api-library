package com.kayako.api.customfield;

import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.Rule;
import org.junit.Test;
import org.junit.Before;
import org.junit.rules.ErrorCollector;

public class CustomFieldValueTest {

    private final static String RAW_VALUE = "rawValue";
    private final static String FILE_NAME = "fileName";
    private static final String COLON_SPLIT = " : ";

    private CustomFieldValue customFieldValue;
    private CustomFieldValue customFieldValue4File;

    @Rule
    public final ErrorCollector collector = new ErrorCollector();

    @Before
    public void setUp(){
        customFieldValue = new CustomFieldValue(RAW_VALUE);
        customFieldValue4File = new CustomFieldValue(RAW_VALUE, FILE_NAME);
    }

    @Test
    public void shouldToStringNotFile(){
        collector.checkThat(customFieldValue.toString(), equalTo(RAW_VALUE));
    }

    @Test
    public void shouldToStringFile(){
        String expectedResult = FILE_NAME + COLON_SPLIT + RAW_VALUE;
        collector.checkThat(customFieldValue4File.toString(), equalTo(expectedResult));
    }
}
