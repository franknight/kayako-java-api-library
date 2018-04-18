package com.kayako.api.customfield;

import static java.lang.Boolean.TRUE;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.mock;
import static org.easymock.EasyMock.verify;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.powermock.api.easymock.PowerMock.replay;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Before;
import org.junit.rules.ErrorCollector;
import java.util.Map;
import java.util.HashMap;
import com.kayako.api.rest.RawArrayElement;
import com.kayako.api.exception.KayakoException;

public class CustomFieldTest {
    private CustomField customField;
    private CustomFieldGroup customFieldGroupMock;
    private CustomFieldDefinition customFieldDefinitionMock;
    private RawArrayElement rawArrayElement;

    private static final int INT_ID = 1;
    private static final String STR_VALUE = "1";
    private static final String NAME_KEY = "name";
    private static final String NAME_VALUE = "NAME_VALUE";
    private static final String TITLE_KEY = "title";
    private static final String TITLE_VALUE = "TITLE";
    private static final String CONTENT = "CONTENT";
    private static final String CONTROLLER = "CONTROLLER";
    private static final CustomFieldOption CUSTOM_FIELD_OPTION = new CustomFieldOption();
    private Map<String, String> attributes;

    @Rule
    public final ErrorCollector collector = new ErrorCollector();

    @Before
    public void setUp() throws KayakoException {
        customFieldGroupMock = mock(CustomFieldGroup.class);

        customFieldDefinitionMock = mock(CustomFieldDefinition.class);
        expect(customFieldDefinitionMock.getOptionById(INT_ID)).andReturn(CUSTOM_FIELD_OPTION);
        customField = new CustomField(customFieldGroupMock);
        customField.setDefinition(customFieldDefinitionMock);

        attributes = new HashMap<>();
        attributes.put(TITLE_KEY, TITLE_VALUE);
        attributes.put(NAME_KEY, NAME_VALUE);
    }

    @Test(expected = KayakoException.class)
    public void givenIdWhenGetThenKayakoException() throws KayakoException {
        customField.get(INT_ID);
    }

    @Test(expected = KayakoException.class)
    public void givenControllerWhenCreateThenKayakoException() throws KayakoException {
        customField.create(CONTROLLER);
    }

    @Test(expected = KayakoException.class)
    public void givenControllerWhenDeleteThenKayakoException() throws KayakoException {
        customField.delete(CONTROLLER);
    }

    @Test(expected = KayakoException.class)
    public void givenControllerWhenRefreshThenKayakoException() throws KayakoException {
        customField.refresh(CONTROLLER);
    }

    @Test(expected = KayakoException.class)
    public void givenControllerWhenUpdateThenKayakoException() throws KayakoException {
        customField.update(CONTROLLER);
    }

    @Test
    public void givenValueWhenGetOptionThenCustomFieldOption() throws KayakoException {
        replay(customFieldDefinitionMock);
        collector.checkThat(customField.getOption(STR_VALUE), sameInstance(CUSTOM_FIELD_OPTION));
        verify(customFieldDefinitionMock);
    }

    @Test
    public void givenIdWhenGetOptionThenCustomFieldOption() throws KayakoException {
        replay(customFieldDefinitionMock);
        collector.checkThat(customField.getOption(INT_ID), sameInstance(CUSTOM_FIELD_OPTION));
        verify(customFieldDefinitionMock);
    }

    @Test
    public void givenRawArrayElementWhenPopulateThenCustomField() throws KayakoException {
        rawArrayElement = new RawArrayElement(CustomField.getObjectXmlName(), attributes, CONTENT);
        customField.populate(rawArrayElement);
        collector.checkThat(customField.getTitle(), equalTo(TITLE_VALUE));
        collector.checkThat(customField.getName(), equalTo(NAME_VALUE));
        collector.checkThat(customField.getRawValue(), equalTo(CONTENT));
    }

    @Test(expected = KayakoException.class)
    public void givenInitialRawArrayElementWhenPopulateThenKayakoException() throws KayakoException {
        rawArrayElement = new RawArrayElement();
        customField.populate(rawArrayElement);
    }

    @Test
    public void whenBuildHashMapThenHashMap() {
        Map<String, String> builtHashMap = customField.buildHashMap();
        collector.checkThat(builtHashMap.get(customField.getName()), equalTo(customField.getRawValue()));
    }

    @Test
    public void whenBuildFilesHashMapThenHashMap() {
        Map<String, HashMap<String, String>> builtFilesHashMap = customField.buildFilesHashMap();
        collector.checkThat(builtFilesHashMap.isEmpty(), equalTo(TRUE));
    }

    @Test
    public void givenIdWhenSetIdThenCustomField(){
        collector.checkThat(customField.setId(INT_ID), sameInstance(customField));
        collector.checkThat(customField.getId(), equalTo(INT_ID));
    }

    @Test
    public void givenDefinitionWhenSetDefinitionThenCustomField() throws KayakoException {
        collector.checkThat(customField.setDefinition(customFieldDefinitionMock), sameInstance(customField));
        collector.checkThat(customField.getDefinition(), equalTo(customFieldDefinitionMock));
    }

    @Test
    public void givenCustomFieldGroupWhenSetCustomFieldGroupThenCustomField() {
        collector.checkThat(customField.setCustomFieldGroup(customFieldGroupMock), sameInstance(customField));
        collector.checkThat(customField.getCustomFieldGroup(), equalTo(customFieldGroupMock));
    }

    @Test
    public void givenDefinitionWhenSetDefinitionThenCustomFiel() {
        collector.checkThat(customField.setCustomFieldGroup(customFieldGroupMock), sameInstance(customField));
        collector.checkThat(customField.getCustomFieldGroup(), equalTo(customFieldGroupMock));
    }
}
