package com.kayako.api.customfield;

import static java.lang.Boolean.TRUE;
import static org.easymock.EasyMock.mock;
import static org.easymock.EasyMock.expect;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.CoreMatchers.containsString;
import static org.powermock.api.easymock.PowerMock.replay;
import static org.powermock.api.easymock.PowerMock.verify;
import static org.powermock.api.easymock.PowerMock.mockStatic;

import org.junit.Rule;
import org.junit.Test;
import org.junit.Before;
import org.junit.rules.ErrorCollector;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import org.junit.runner.RunWith;
import org.junit.rules.ExpectedException;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;

import com.kayako.api.rest.KEntity;
import com.kayako.api.rest.RawArrayElement;
import com.kayako.api.exception.KayakoException;
import com.kayako.api.enums.CustomFieldDefinitionTypeEnum;

@RunWith(PowerMockRunner.class)
@PrepareForTest(KEntity.class)
public class CustomFieldTest {

    private static final int INT_ID = 1;
    private static final int INT_TYPE = 10;
    private static final String FIELD_TYPE = "fieldtype";
    private static final String STR_VALUE = "1";
    private static final String NAME_KEY = "name";
    private static final String FIELD_NAME = "fieldname";
    private static final String NAME_VALUE = "NAME_VALUE";
    private static final String TITLE_KEY = "title";
    private static final String TITLE_VALUE = "TITLE";
    private static final String CONTENT = "CONTENT";
    private static final String CONTROLLER = "CONTROLLER";
    private static final String OBJECT_XML_NAME = "objectXmlName";
    private static final String OBJECT_TO_STRING = "CustomField- ID: " + INT_ID;
    private static final CustomFieldOption CUSTOM_FIELD_OPTION = new CustomFieldOption();
    private static final String METHOD_NOT_AVAILABLE_MSG = "method is not available";

    private CustomField customField;
    private CustomFieldGroup customFieldGroupMock;
    private CustomFieldDefinition customFieldDefinitionMock;
    private RawArrayElement rawArrayElement;
    private Map<String, String> attributes;

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

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

    @Test
    public void givenIdWhenGetThenKayakoException() throws KayakoException {
        thrown.expect(KayakoException.class);
        thrown.expectMessage(containsString(METHOD_NOT_AVAILABLE_MSG));
        customField.get(INT_ID);
    }

    @Test
    public void givenControllerWhenCreateThenKayakoException() throws KayakoException {
        thrown.expect(KayakoException.class);
        thrown.expectMessage(containsString(METHOD_NOT_AVAILABLE_MSG));
        customField.create(CONTROLLER);
    }

    @Test
    public void givenControllerWhenDeleteThenKayakoException() throws KayakoException {
        thrown.expect(KayakoException.class);
        thrown.expectMessage(containsString(METHOD_NOT_AVAILABLE_MSG));
        customField.delete(CONTROLLER);
    }

    @Test
    public void givenControllerWhenRefreshThenKayakoException() throws KayakoException {
        thrown.expect(KayakoException.class);
        thrown.expectMessage(containsString(METHOD_NOT_AVAILABLE_MSG));
        customField.refresh(CONTROLLER);
    }

    @Test
    public void givenControllerWhenUpdateThenKayakoException() throws KayakoException {
        thrown.expect(KayakoException.class);
        thrown.expectMessage(containsString(METHOD_NOT_AVAILABLE_MSG));
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
    public void givenTrueWhenGetDefinitionThenCustomFieldDefinition() throws KayakoException {
        mockStatic(KEntity.class);
        RawArrayElement rawArrayElement1 = new RawArrayElement();
        ArrayList<RawArrayElement> rawArrayElementList = new ArrayList<>();
        rawArrayElement  = new RawArrayElement();
        rawArrayElement.setElementName(CustomFieldDefinition.getObjectXmlName());
        rawArrayElement.setAttribute(FIELD_NAME, FIELD_NAME);
        rawArrayElement.setAttribute(FIELD_TYPE, CustomFieldDefinitionTypeEnum.CHECKBOX.getString());

        rawArrayElementList.add(rawArrayElement);

        rawArrayElement1.setComponents(rawArrayElementList);
        customField.setName(FIELD_NAME);
        expect(KEntity.getAll(CustomFieldDefinition.getController())).andReturn(rawArrayElement1);
        replay(KEntity.class);
        customField.getDefinition(TRUE);
        verify(KEntity.class);
        collector.checkThat(customField.getDefinition(), is(notNullValue()));
        collector.checkThat(customField.getDefinition(), not(equalTo(customFieldDefinitionMock)));
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
    public void givenDefinitionWhenSetCustomFieldGroupThenCustomField() {
        collector.checkThat(customField.setCustomFieldGroup(customFieldGroupMock), sameInstance(customField));
        collector.checkThat(customField.getCustomFieldGroup(), equalTo(customFieldGroupMock));
    }

    @Test
    public void shouldSetObjectXmlName() {
        CustomField.setObjectXmlName(OBJECT_XML_NAME);
        collector.checkThat(CustomField.getObjectXmlName(), equalTo(OBJECT_XML_NAME));
    }

    @Test
    public void shouldSetType() {
        customField.setType(INT_TYPE);
        collector.checkThat(customField.getType(), equalTo(INT_TYPE));
    }

    @Test
    public void shouldToString() {
        customField.setId(INT_ID);

        collector.checkThat(customField.toString(), equalTo(OBJECT_TO_STRING));
    }
}
