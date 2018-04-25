package com.kayako.api.rest;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.AttributesImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class XMLHandlerTest {
    private static final String TEST_URI = "testuri";
    private static final String TEST_LOCAL_NAME = "testlocalname";
    private static final String TEST_QNAME = "testqname";
    private static final char[] TEST_CHAR_ARRAY = new char[]{'t', 'e', 's', 't', 'u', 'r', 'i', 's'};
    private static final int TEST_START = 0;
    private static final int TEST_END = 7;
    private static Attributes attributes;
    private XMLHandler xmlHandler = new XMLHandler();

    @Before
    public void setUp() {
        attributes = new AttributesImpl();
    }

    @Test
    public void givenStringWhenCallStartElementThenTurnCurrentElementToTrue()
            throws Exception {
        //Arrange
        xmlHandler.startElement(TEST_URI, TEST_LOCAL_NAME, TEST_QNAME, attributes);

        //Act
        boolean returnedCurrentElement = xmlHandler.currentElement;

        //Assert
        assertTrue(returnedCurrentElement);
    }

    @Test
    public void givenObjectStackWhenEndElementQueryThenPopElement()
            throws Exception {
        //Arrange
        xmlHandler.startElement(TEST_URI, TEST_LOCAL_NAME, TEST_QNAME, attributes);
        xmlHandler.endElement(TEST_URI, TEST_LOCAL_NAME, TEST_QNAME);

        //Act
        boolean returnedCurrentElement = xmlHandler.currentElement;

        //Assert
        assertFalse(returnedCurrentElement);
    }

    @Test
    public void givenCharArrayWhenHandleCharactersThenPutSubCharacterArray()
            throws Exception {
        //Arrange
        xmlHandler.startElement(TEST_URI, TEST_LOCAL_NAME, TEST_QNAME, attributes);
        xmlHandler.characters(TEST_CHAR_ARRAY, TEST_START, TEST_END);

        //Act
        String returnedCurrentValue = xmlHandler.currentValue;

        //Assert
        assertEquals(TEST_URI, returnedCurrentValue);
    }
}
