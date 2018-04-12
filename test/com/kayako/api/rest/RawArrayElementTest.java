package com.kayako.api.rest;

import com.kayako.api.exception.KayakoException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class RawArrayElementTest {

    private RawArrayElement rawArrayElement;
    private static final String TEST_AUTO_ELEMENT_NAME = "Auto Element";
    private static final String TEST_ELEMENT_NAME = "Element";
    private static final String TEST_CONTENT_NAME = "Content";
    private static final String TEST_EMPTY_CONTENT_NAME = "Empty content";
    private static final String TEST_KEY_NAME = "key";
    private static final String TEST_VALUE_NAME = "value";

    @Rule
    public final ErrorCollector collector = new ErrorCollector();

    @Test
    public void shouldCreatedWithDefaultConstructor() {
        // Arrange
        rawArrayElement = new RawArrayElement();

        // Assert
        assertEquals(TEST_AUTO_ELEMENT_NAME, rawArrayElement.getElementName());
    }

    @Test
    public void shouldCreatedConstructorWithElementNameAndAttributes() {
        // Arrange
        Map<String, String> attributes = new HashMap<String, String>();
        attributes.put(TEST_KEY_NAME, TEST_VALUE_NAME);

        rawArrayElement = new RawArrayElement(TEST_ELEMENT_NAME, attributes);

        // Assert
        collector.checkThat(rawArrayElement.getElementName(), equalTo(TEST_ELEMENT_NAME));
        collector.checkThat(rawArrayElement.getAttributes().size(), is(1));
    }

    @Test
    public void shouldCreatedConstructorWithElementNameAndContent() {
        // Arrange
        rawArrayElement = new RawArrayElement(TEST_ELEMENT_NAME, TEST_CONTENT_NAME);

        // Assert
        collector.checkThat(rawArrayElement.getElementName(), equalTo(TEST_ELEMENT_NAME));
        collector.checkThat(rawArrayElement.getContent(), equalTo(TEST_CONTENT_NAME));
    }

    @Test
    public void shouldCreatedConstructorWithElementNameAndAttributesAndcontent() throws IllegalAccessException {
        // Arrange
        Map<String, String> attributes = new HashMap<String, String>();
        attributes.put(TEST_KEY_NAME, TEST_VALUE_NAME);

        rawArrayElement = new RawArrayElement(TEST_ELEMENT_NAME, attributes, "cont");

        // Act
        rawArrayElement.put(TEST_CONTENT_NAME);

        // Assert
        collector.checkThat(rawArrayElement.getElementName(), equalTo(TEST_ELEMENT_NAME));
        collector.checkThat(rawArrayElement.get(), equalTo(TEST_CONTENT_NAME));
        collector.checkThat(rawArrayElement.getAttributes().size(), is(1));
    }

    @Test(expected = IllegalAccessException.class)
    public void shouldSetComponents() throws Exception {
        rawArrayElement = new RawArrayElement("elementname", TEST_CONTENT_NAME);

        rawArrayElement.setElementName(TEST_ELEMENT_NAME);
        rawArrayElement.setContent(null);

        ArrayList<RawArrayElement> components = new ArrayList<RawArrayElement>();
        components.add(rawArrayElement);

        collector.checkThat(rawArrayElement.getFirstComponent(), equalTo(null));

        rawArrayElement.setComponents(components);

        collector.checkThat(rawArrayElement.getComponents().size(), is(1));
        collector.checkThat(rawArrayElement.getComponents(TEST_ELEMENT_NAME).size(), is(1));
        collector.checkThat(rawArrayElement.getFirstComponent().getContent(), equalTo(""));

        rawArrayElement.setComposite(true);
        rawArrayElement.setContent(TEST_EMPTY_CONTENT_NAME);

        RawArrayElement newRawArrayElement = new RawArrayElement();
        newRawArrayElement.put(rawArrayElement);

        collector.checkThat(rawArrayElement.get(), equalTo(""));
    }

    @Test
    public void shouldSetAttribute() {
        // Arrange
        rawArrayElement = new RawArrayElement(TEST_ELEMENT_NAME, TEST_CONTENT_NAME);

        // Act
        rawArrayElement.setAttribute("keyA", "valueA");

        // Assert
        assertEquals("valueA", rawArrayElement.get("keyA"));
    }

    @Test
    public void shouldFilterByComponentName() throws KayakoException {
        // Arrange
        rawArrayElement = new RawArrayElement(TEST_ELEMENT_NAME, TEST_CONTENT_NAME);

        // Assert
        assertEquals(TEST_ELEMENT_NAME, rawArrayElement.filterByComponentName(TEST_CONTENT_NAME).getElementName());
    }

    @Test
    public void shouldFilterByComponentValue() throws KayakoException {
        // Arrange
        rawArrayElement = new RawArrayElement(TEST_ELEMENT_NAME, TEST_CONTENT_NAME);
        ArrayList<RawArrayElement> components = new ArrayList<RawArrayElement>();

        // Act
        components.add(rawArrayElement);
        rawArrayElement.setComponents(components);

        // Assert
        assertEquals(rawArrayElement, rawArrayElement.filterByComponentValue(TEST_ELEMENT_NAME, TEST_CONTENT_NAME));
    }

    @Test(expected = KayakoException.class)
    public void shouldFilterByComponentValueThenGotException() throws KayakoException {
        // Arrange
        rawArrayElement = new RawArrayElement(TEST_ELEMENT_NAME, TEST_CONTENT_NAME);
        ArrayList<RawArrayElement> components = new ArrayList<RawArrayElement>();

        // Act
        components.add(rawArrayElement);
        rawArrayElement.setComponents(components);

        // Assert
        assertThat(rawArrayElement.filterByComponentValue(TEST_AUTO_ELEMENT_NAME, TEST_CONTENT_NAME), is(rawArrayElement));
    }

    @Test
    public void shouldFilterWhenNegativeIsTrue() throws KayakoException {
        // Arrange
        rawArrayElement = new RawArrayElement(TEST_ELEMENT_NAME, TEST_CONTENT_NAME);
        ArrayList<RawArrayElement> components = new ArrayList<RawArrayElement>();

        // Act
        components.add(rawArrayElement);
        rawArrayElement.setComponents(components);

        // Assert
        assertEquals(rawArrayElement, rawArrayElement.filterByComponentValue(TEST_ELEMENT_NAME, TEST_EMPTY_CONTENT_NAME, true));
    }

    @Test
    public void shouldFilterByComponentAttribute() throws KayakoException {
        // Arrange
        Map<String, String> attributes = new HashMap<String, String>();
        attributes.put(TEST_KEY_NAME, TEST_VALUE_NAME);

        rawArrayElement = new RawArrayElement(TEST_ELEMENT_NAME, attributes);
        ArrayList<RawArrayElement> components = new ArrayList<RawArrayElement>();

        // Act
        components.add(rawArrayElement);
        rawArrayElement.setComponents(components);

        // Assert
        assertEquals(rawArrayElement, rawArrayElement.filterByComponentAttribute(TEST_KEY_NAME, TEST_VALUE_NAME));
    }

    @Test
    public void shouldCheckToStringWhenCompositeFalse() {
        // Arrange
        Map<String, String> attributes = new HashMap<String, String>();
        attributes.put(TEST_KEY_NAME, TEST_VALUE_NAME);

        rawArrayElement = new RawArrayElement(TEST_ELEMENT_NAME, attributes);

        // Assert
        assertEquals("<Element key = 'value' >\n"
                + "null\n"
                + "</Element>\n", rawArrayElement.toString());

    }

    @Test
    public void shouldCheckToStringWhenCompositeTrue() {
        // Arrange
        ArrayList<RawArrayElement> components = new ArrayList<RawArrayElement>();
        Map<String, String> attributes = new HashMap<String, String>();
        attributes.put(TEST_KEY_NAME, TEST_VALUE_NAME);

        rawArrayElement = new RawArrayElement(TEST_ELEMENT_NAME, attributes);

        // Act
        components.add(new RawArrayElement(TEST_ELEMENT_NAME, TEST_CONTENT_NAME));
        rawArrayElement.setComponents(components);
        rawArrayElement.setComposite(true);

        // Assert
        assertEquals("<Element key = 'value' >\n"
                + "<Element>\n"
                + "Content\n"
                + "</Element>\n"
                + "</Element>\n", rawArrayElement.toString());
    }

}