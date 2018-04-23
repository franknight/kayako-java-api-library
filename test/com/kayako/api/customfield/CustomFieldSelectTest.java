package com.kayako.api.customfield;

import static java.lang.Boolean.TRUE;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.mock;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.powermock.api.easymock.PowerMock.replay;
import static org.powermock.api.easymock.PowerMock.verify;

import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.Before;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;

import java.util.HashMap;
import com.kayako.api.rest.RawArrayElement;
import com.kayako.api.exception.KayakoException;

@RunWith(PowerMockRunner.class)
@PrepareForTest(CustomFieldDefinition.class)
public class CustomFieldSelectTest {

    private final static int INT_ID = 1;
    private final static String STR_INT_ID = "1";
    private final static String VALUE_CONTENT = "value";
    private final static String FIELD_NAME = "fieldName";
    private final static String ELEMENT_NAME = "elementName";

    private RawArrayElement rawArrayElement;
    private CustomFieldGroup customFieldGroup;
    private CustomFieldOption customFieldOption;
    private CustomFieldSelect customFieldSelect;
    private CustomFieldDefinition mockedDefinition4GetOptionById;
    private CustomFieldDefinition mockedDefinition4GetOptionByValue;

    @Rule
    public final ErrorCollector collector = new ErrorCollector();

    @Before
    public void setUp() throws KayakoException {
        customFieldGroup = mock(CustomFieldGroup.class);
        customFieldSelect = new CustomFieldSelect(customFieldGroup);
        rawArrayElement = new RawArrayElement(ELEMENT_NAME, STR_INT_ID);

        customFieldOption = new CustomFieldOption();
        customFieldOption.setId(INT_ID);
        customFieldOption.setValue(VALUE_CONTENT);

        mockedDefinition4GetOptionById = mock(CustomFieldDefinition.class);
        mockedDefinition4GetOptionByValue = mock(CustomFieldDefinition.class);
        expect(mockedDefinition4GetOptionById.getOptionById(INT_ID)).andReturn(customFieldOption);
        expect(mockedDefinition4GetOptionByValue.getOptionByValue(VALUE_CONTENT)).andReturn(customFieldOption);
    }

    @Test
    public void givenOptionWhenSelectedOption() throws KayakoException {
        CustomFieldOption customFieldOption = new CustomFieldOption();
        customFieldSelect.setSelectedOption(customFieldOption);

        collector.checkThat(customFieldSelect.getSelectedOption(), sameInstance(customFieldOption));
    }

    @Test
    public void givenIdWhenSetValueThenOptionSelected() throws KayakoException {
        givenStringWhenSetValueThenOptionSelected(mockedDefinition4GetOptionById, STR_INT_ID);
    }

    @Test
    public void givenValueWhenSetValueThenOptionSelected() throws KayakoException {
        givenStringWhenSetValueThenOptionSelected(mockedDefinition4GetOptionByValue, VALUE_CONTENT);
    }

    @Test
    public void shouldBuildHashMap() throws KayakoException {
        customFieldSelect.setName(FIELD_NAME);
        customFieldSelect.setSelectedOption(customFieldOption);
        HashMap<String, String> builtHashMap = customFieldSelect.buildHashMap(TRUE);
        collector.checkThat(builtHashMap, is(notNullValue()));
        collector.checkThat(builtHashMap.get(FIELD_NAME), equalTo(STR_INT_ID));
    }

    @Test
    public void shouldBuildHashMapSelectedOptionNull() throws KayakoException {
        customFieldSelect.setSelectedOption(null);
        HashMap<String, String> builtHashMap = customFieldSelect.buildHashMap(TRUE);
        collector.checkThat(builtHashMap, is(notNullValue()));
    }

    @Test
    public void givenElementWithIntContentWhenPopulateThenCustomFieldSelect() throws KayakoException {
        givenElementWhenPopulateThenCustomFieldSelect(mockedDefinition4GetOptionById, STR_INT_ID);
    }

    @Test
    public void givenElementWithValueContentWhenPopulateThenCustomFieldSelect() throws KayakoException {
        givenElementWhenPopulateThenCustomFieldSelect(mockedDefinition4GetOptionByValue, VALUE_CONTENT);
    }

    private void givenElementWhenPopulateThenCustomFieldSelect(CustomFieldDefinition mockedDefinition, String element) throws KayakoException {
        customFieldSelect.setDefinition(mockedDefinition);
        rawArrayElement = new RawArrayElement(CustomField.getObjectXmlName(), element);
        replay(mockedDefinition);
        customFieldSelect.populate(rawArrayElement);
        verify(mockedDefinition);
        collector.checkThat(customFieldSelect.getSelectedOption(), sameInstance(customFieldOption));
    }

    private void givenStringWhenSetValueThenOptionSelected(CustomFieldDefinition mockedDefinition, String value) throws KayakoException {
        customFieldSelect.setDefinition(mockedDefinition);
        replay(mockedDefinition);
        customFieldSelect.setValue(value);
        verify(mockedDefinition);
        collector.checkThat(customFieldSelect.getSelectedOption(), sameInstance(customFieldOption));
    }
}
