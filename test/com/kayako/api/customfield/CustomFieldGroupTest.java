package com.kayako.api.customfield;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.easymock.EasyMock.createMockBuilder;
import static java.lang.Boolean.TRUE;

import org.junit.Rule;
import org.junit.Test;
import org.junit.Before;
import org.junit.rules.ErrorCollector;

import java.util.HashMap;
import java.util.ArrayList;

import com.kayako.api.rest.RawArrayElement;
import com.kayako.api.exception.KayakoException;
import com.kayako.api.enums.CustomFieldGroupTypeEnum;
import com.kayako.api.enums.CustomFieldDefinitionTypeEnum;

public class CustomFieldGroupTest {

    private static final int INT_ID = 1;
    private static final int INT_ONE = 1;
    private static final int INT_ZERO = 0;
    private static final String ID_KEY = "id";
    private static final String TYPE = "type";
    private static final String ID_VALUE = "2";
    private static final String TITLE_KEY = "title";
    private static final String FILE_NAME = "filename";
    private static final String STR_VALUE = "strValue";
    private static final String STR_TITLE = "strTitle";
    private static final String ELEMENT_NAME = "field";
    private static final String TITLE_VALUE = "titleValue";
    private static final String STR_CONTROLLER = "strController";
    private static final String CUSTOM_CONTROLLER = "controllerCtz";
    private static final String OBJECT_XML_NAME = "group";
    private static final String CUSTOM_OBJECT_XML_NAME = "groupCtz";

    private RawArrayElement componentElement;
    private ArrayList<CustomField> fieldList;
    private RawArrayElement rawArrayElement;
    private CustomFieldGroup customFieldGroup;
    private ArrayList<RawArrayElement> componentList;

    @Rule
    public final ErrorCollector collector = new ErrorCollector();

    @Before
    public void setUp(){
        customFieldGroup = createMockBuilder(CustomFieldGroup.class).createMock();
        fieldList = new ArrayList<>();
        customFieldGroup.setFields(fieldList);

        componentElement = new RawArrayElement();
        componentElement.setElementName(ELEMENT_NAME);
        componentElement.setAttribute(FILE_NAME, STR_VALUE);
        componentElement.setAttribute(TYPE, CustomFieldDefinitionTypeEnum.FILE.getString());

        componentList = new ArrayList<>();
        componentList.add(componentElement);

        rawArrayElement = new RawArrayElement();
        rawArrayElement.setAttribute(ID_KEY, ID_VALUE);
        rawArrayElement.setAttribute(TITLE_KEY, TITLE_VALUE);
        rawArrayElement.setComponents(componentList);
    }

    @Test(expected = KayakoException.class)
    public void givenIdWhenGetThenKayakoException() throws KayakoException {
        customFieldGroup.get(INT_ID);
    }

    @Test(expected = KayakoException.class)
    public void givenIdWhenRefreshThenKayakoException() throws KayakoException {
        customFieldGroup.refresh(STR_CONTROLLER);
    }

    @Test
    public void shouldSetType(){
        customFieldGroup.setType(CustomFieldGroupTypeEnum.TICKET);
        collector.checkThat(customFieldGroup.getType(), equalTo(CustomFieldGroupTypeEnum.TICKET));
    }

    @Test
    public void shouldSetFields(){
        collector.checkThat(customFieldGroup.setFields(fieldList), sameInstance(customFieldGroup));
        collector.checkThat(customFieldGroup.getFields(), sameInstance(fieldList));
    }

    @Test
    public void shouldSetId(){
        collector.checkThat(customFieldGroup.setId(INT_ID), sameInstance(customFieldGroup));
        collector.checkThat(customFieldGroup.getId(), equalTo(INT_ID));
    }

    @Test
    public void shouldSetTitle(){
        collector.checkThat(customFieldGroup.setTitle(STR_TITLE), sameInstance(customFieldGroup));
        collector.checkThat(customFieldGroup.getTitle(), equalTo(STR_TITLE));
    }

    @Test
    public void shouldSetReadOnlyTrue(){
        collector.checkThat(customFieldGroup.setReadOnly(TRUE), sameInstance(customFieldGroup));
        collector.checkThat(customFieldGroup.getReadOnly(), equalTo(TRUE));
    }

    @Test (expected = KayakoException.class)
    public void givenElementWithInvalidElementNameWhenPopulateThenKayakoException() throws KayakoException {
        CustomFieldGroup.setObjectXmlName(OBJECT_XML_NAME);
        rawArrayElement.setElementName(CUSTOM_OBJECT_XML_NAME);
        customFieldGroup.populate(rawArrayElement);
    }

    @Test
    public void givenElementWhenPopulateThenCustomFieldGroup() throws KayakoException {
        rawArrayElement.setElementName(CustomFieldGroup.getObjectXmlName());
        customFieldGroup.getFields().clear();
        customFieldGroup.setFields(fieldList);
        customFieldGroup.populate(rawArrayElement);
        collector.checkThat(customFieldGroup.getFields().size(), equalTo(INT_ONE));
    }

    @Test
    public void shouldBuildHashMap() throws KayakoException {
        customFieldGroup.getFields().add(CustomFieldFactory.createCustomField(customFieldGroup, componentElement));
        HashMap<String, String> builtHashMap = customFieldGroup.buildHashMap();
        collector.checkThat(builtHashMap.size(), equalTo( INT_ZERO));
    }

    @Test
    public void shouldBuildFilesHashMap() throws KayakoException {
        customFieldGroup.getFields().clear();
        customFieldGroup.getFields().add(CustomFieldFactory.createCustomField(customFieldGroup, componentElement));
        HashMap<String, HashMap<String, String>> builtFilesHashMap = customFieldGroup.buildFilesHashMap();
        collector.checkThat(builtFilesHashMap.size(), equalTo( INT_ONE));
    }

    @Test
    public void shouldSetObjectXmlName(){
        CustomFieldGroup.setObjectXmlName(CUSTOM_OBJECT_XML_NAME);
        collector.checkThat(CustomFieldGroup.getObjectXmlName(), equalTo(CUSTOM_OBJECT_XML_NAME));
    }

    @Test
    public void shouldSetController(){
        CustomFieldGroup.setController(CUSTOM_CONTROLLER);
        collector.checkThat(CustomFieldGroup.getController(), equalTo(CUSTOM_CONTROLLER));
    }
}
