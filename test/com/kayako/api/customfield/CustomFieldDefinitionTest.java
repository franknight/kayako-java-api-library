package com.kayako.api.customfield;

import org.junit.Rule;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import com.kayako.api.util.Helper;
import org.junit.rules.ErrorCollector;
import com.kayako.api.rest.RawArrayElement;
import com.kayako.api.exception.KayakoException;
import org.powermock.modules.junit4.PowerMockRunner;
import com.kayako.api.enums.CustomFieldDefinitionTypeEnum;
import org.powermock.core.classloader.annotations.PrepareForTest;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import static java.lang.Boolean.TRUE;
import static java.lang.Boolean.FALSE;
import static org.easymock.EasyMock.expect;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.powermock.api.easymock.PowerMock.replay;
import static org.powermock.api.easymock.PowerMock.verify;
import static org.powermock.api.easymock.PowerMock.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest(CustomFieldOption.class)
public class CustomFieldDefinitionTest {

    private static final int INT_ONE = 1;
    private static final int INT_ZERO = 0;
    private static final String STR_INT_ONE = "1";
    private static final String STR_INT_ZERO = "0";
    private static final String STR_VALUE = "STR_VALUE";
    private static final String ELEMENT_NAME = "elementName";
    private static final String CUSTOM_FIELD_ID = "customfieldid";
    private static final String CUSTOM_FIELD_GROUP_ID = "customfieldgroupid";
    private static final String FIELD_TYPE = "fieldtype";
    private static final String FIELD_NAME = "fieldname";
    private static final String TITLE = "title";
    private static final String DEFAULT_VALUE = "defaultvalue";
    private static final String IS_REQUIRED = "isrequired";
    private static final String USER_EDITABLE = "usereditable";
    private static final String STAFF_EDITABLE = "staffeditable";
    private static final String REGEXP_VALIDATE = "regexpvalidate";
    private static final String DISPLAY_ORDER = "displayorder";
    private static final String STR_CONTROLLER = "strController";
    private static final String ENCRYPTED = "encryptindb";
    private static final String IS_SELECTED = "isselected";
    private static final String DESCRIPTION = "description";
    private static final int INT_ID_NOT_EXIST_IN_OPTIONS = 0;
    private static final String STR_VALUE_NOT_EXIST_IN_OPTIONS = "strValueNotInOptions";

    private CustomFieldDefinition customFieldDefinition;
    private RawArrayElement rawArrayElement;
    private CustomFieldOption customFieldOption;
    private ArrayList<CustomFieldOption> options;
    private ArrayList<RawArrayElement> componentList;
    private RawArrayElement component1;
    private Map<String, String> attributes;

    @Rule
    public final ErrorCollector collector = new ErrorCollector();

    @Before
    public void setUp() throws KayakoException {

        attributes = new HashMap<>();
        attributes.put(CUSTOM_FIELD_ID, STR_INT_ONE);
        attributes.put(CUSTOM_FIELD_GROUP_ID, STR_INT_ONE);
        attributes.put(FIELD_TYPE, CustomFieldDefinitionTypeEnum.CHECKBOX.getString());
        attributes.put(FIELD_NAME, STR_VALUE);
        attributes.put(TITLE, STR_VALUE);
        attributes.put(DEFAULT_VALUE, STR_VALUE);
        attributes.put(IS_REQUIRED, STR_INT_ONE);
        attributes.put(USER_EDITABLE, STR_INT_ONE);
        attributes.put(STAFF_EDITABLE, STR_INT_ONE);
        attributes.put(REGEXP_VALIDATE, STR_VALUE);
        attributes.put(DISPLAY_ORDER, STR_INT_ONE);
        attributes.put(ENCRYPTED, STR_INT_ONE);
        attributes.put(DESCRIPTION, STR_VALUE);

        component1 = new RawArrayElement(CustomFieldOption.getObjectXmlName(), new HashMap<>());
        component1.setAttribute(IS_SELECTED, STR_INT_ZERO);
        componentList = new ArrayList<>();
        componentList.add(component1);

        rawArrayElement = new RawArrayElement(CustomFieldDefinition.getObjectXmlName(), attributes, null);
        rawArrayElement.setComponents(componentList);

        customFieldDefinition = new CustomFieldDefinition(rawArrayElement);

        options = new ArrayList<>();
        customFieldOption = new CustomFieldOption();
        customFieldOption.setId(INT_ONE);
        customFieldOption.setValue(STR_VALUE);
        options.add(customFieldOption);
        customFieldDefinition.setOptions(options);
    }

    @Test(expected = KayakoException.class)
    public void givenIdWhenGetThenKayakoException() throws KayakoException {
        customFieldDefinition.get(INT_ONE);
    }

    @Test(expected = KayakoException.class)
    public void whenRefreshThenKayakoException() throws KayakoException {
        customFieldDefinition.refresh();
    }

    @Test
    public void givenIdWhenGetOptionByIdThenCustomFieldOption() throws KayakoException {
        collector.checkThat(customFieldDefinition.getOptionById(INT_ONE), sameInstance(customFieldOption));
    }

    @Test
    public void givenIdNotExistInOptionsWhenGetOptionByIdThenNull() throws KayakoException {
        collector.checkThat(customFieldDefinition.getOptionById(INT_ID_NOT_EXIST_IN_OPTIONS), is(nullValue()));
    }

    @Test
    public void givenValueWhenGetOptionByValueThenCustomFieldOption() throws KayakoException {
        collector.checkThat(customFieldDefinition.getOptionByValue(STR_VALUE), sameInstance(customFieldOption));
    }

    @Test
    public void givenValueNotExistInOptionsWhenGetOptionByValueThenNull() throws KayakoException {
        collector.checkThat(customFieldDefinition.getOptionByValue(STR_VALUE_NOT_EXIST_IN_OPTIONS), is(nullValue()));
    }

    @Test
    public void shouldSetReadOnly() {
        customFieldDefinition.setReadOnly(TRUE);
        collector.checkThat(customFieldDefinition.getReadOnly(), equalTo(TRUE));
    }

    @Test
    public void shouldSetController() {

        CustomFieldDefinition.setController(STR_CONTROLLER);
        collector.checkThat(CustomFieldDefinition.getController(), equalTo(STR_CONTROLLER));
    }

    @Test
    public void shouldSetDefinitions() throws KayakoException {
        ArrayList<CustomFieldDefinition> definitionList = new ArrayList<>();
        definitionList.add(new CustomFieldDefinition(rawArrayElement));
        CustomFieldDefinition.setDefinitions(definitionList);
        collector.checkThat(CustomFieldDefinition.getDefinitions(), is(notNullValue()));
        collector.checkThat(CustomFieldDefinition.getDefinitions().size(), equalTo(INT_ONE));
    }

    @Test
    public void shouldGetAttributes() {
        collector.checkThat(customFieldDefinition.getId(), equalTo(Helper.parseInt(attributes.get(CUSTOM_FIELD_ID))));
        collector.checkThat(customFieldDefinition.getGroupId(), equalTo(Helper.parseInt(attributes.get(CUSTOM_FIELD_GROUP_ID))));
        collector.checkThat(customFieldDefinition.getType(), equalTo(CustomFieldDefinitionTypeEnum.getEnum(attributes.get(FIELD_TYPE))));
        collector.checkThat(customFieldDefinition.getName(), equalTo(attributes.get(FIELD_NAME)));
        collector.checkThat(customFieldDefinition.getTitle(), equalTo(attributes.get(TITLE)));
        collector.checkThat(customFieldDefinition.getDefaultValue(), equalTo(attributes.get(DEFAULT_VALUE)));
        collector.checkThat(customFieldDefinition.getRequired(), equalTo(TRUE));
        collector.checkThat(customFieldDefinition.getUserEditable(), equalTo(TRUE));
        collector.checkThat(customFieldDefinition.getStaffEditable(), equalTo(TRUE));
        collector.checkThat(customFieldDefinition.getRegexpValidate(), equalTo(attributes.get(REGEXP_VALIDATE)));
        collector.checkThat(customFieldDefinition.getDisplayOrder(), equalTo(Helper.parseInt(attributes.get(DISPLAY_ORDER))));
        collector.checkThat(customFieldDefinition.getEncrypted(), equalTo(TRUE));
        collector.checkThat(customFieldDefinition.getDescription(), equalTo(attributes.get(DESCRIPTION)));
    }

    @Test
    public void shouldAddCustomFieldOption() throws KayakoException {
        CustomFieldOption customFieldOpt = new CustomFieldOption();
        customFieldDefinition.addOption(customFieldOpt);
        final int IDX_OF_LAST = customFieldDefinition.getOptions().size()-INT_ONE;
        collector.checkThat(customFieldDefinition.getOptions().get(IDX_OF_LAST), sameInstance(customFieldOpt));
    }

    @Test
    public void givenFalseWhenGetDefaultOptionsThenCustomFieldOptionList() throws KayakoException {
        collector.checkThat(customFieldDefinition.getDefaultOptions(FALSE), sameInstance(customFieldDefinition.getOptions()));
    }

    @Test
    public void givenTrueWhenGetDefaultOptionsThenCustomFieldOptionList() throws KayakoException {
        final int sizeOfOptions = customFieldDefinition.getOptions().size();

        mockStatic(CustomFieldOption.class);
        expect(CustomFieldOption.getAll(customFieldDefinition.getId())).andReturn(rawArrayElement);

        replay(CustomFieldOption.class);
        collector.checkThat(customFieldDefinition.getDefaultOptions(TRUE).size() > sizeOfOptions, equalTo(TRUE));
        verify(CustomFieldOption.class);
    }

    @Test
    public void givenDateFieldWhenGetDefaultOptionThenEmptyOptions() throws KayakoException {
        customFieldDefinition.setOptions(options);
        customFieldDefinition.setType(CustomFieldDefinitionTypeEnum.DATE);

        collector.checkThat(customFieldDefinition.getDefaultOptions(TRUE).size(), equalTo(INT_ZERO));
    }

    @Test
    public void givenTrueWhenGetOptionsThenCustomFieldOptionList() throws KayakoException {
        final int sizeOfOptions = customFieldDefinition.getOptions().size();

        mockStatic(CustomFieldOption.class);
        expect(CustomFieldOption.getAll(customFieldDefinition.getId())).andReturn(rawArrayElement);

        replay(CustomFieldOption.class);
        collector.checkThat(customFieldDefinition.getOptions(TRUE).size() > sizeOfOptions, equalTo(TRUE));
        verify(CustomFieldOption.class);
    }

    @Test
    public void givenDateFieldWhenGetOptionThenEmptyOptions() throws KayakoException {
        customFieldDefinition.setOptions(options);
        customFieldDefinition.setType(CustomFieldDefinitionTypeEnum.DATE);

        collector.checkThat(customFieldDefinition.getOptions(TRUE).size(), equalTo(INT_ZERO));
    }
}
