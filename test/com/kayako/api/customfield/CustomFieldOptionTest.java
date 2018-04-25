package com.kayako.api.customfield;

import static java.lang.Boolean.TRUE;
import static java.lang.Boolean.FALSE;
import static org.easymock.EasyMock.expect;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.powermock.api.easymock.PowerMock.mockStatic;
import static org.powermock.api.easymock.PowerMock.replay;
import static org.powermock.api.easymock.PowerMock.verify;

import org.junit.rules.ExpectedException;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.rules.ErrorCollector;

import java.util.ArrayList;
import com.kayako.api.util.Helper;
import com.kayako.api.rest.KEntity;
import com.kayako.api.rest.RawArrayElement;
import com.kayako.api.exception.KayakoException;

@RunWith(PowerMockRunner.class)
@PrepareForTest(KEntity.class)
public class CustomFieldOptionTest {
    private static final int INT_PARENT_OPTION_ID = 1;
    private static final String STR_VALUE = "strValue";
    private static final int INT_ID = 123;
    private static final int INT_DISPLAY_ORDER = 321;
    private static final int INT_FIELD_ID = 111;
    private static final String STR_INT_ZERO = "0";
    private static final String STR_INT_VALUE = "2";
    private static final String IS_SELECTED = "isselected";
    private static final String OPTION_VALUE = "optionvalue";
    private static final String DISPLAY_ORDER = "displayorder";
    private static final String CUSTOM_FIELD_ID = "customfieldid";
    private static final String CUSTOM_CONTROLLER = "customController";
    private static final String CUSTOM_OBJECT_XML_NAME = "customObjectXmlName";
    private static final String CUSTOM_FIELD_OPTION_ID = "customfieldoptionid";
    private static final String PARENT_CUSTOM_FIELD_OPTION_ID = "parentcustomfieldoptionid";
    private static final String METHOD_NOT_AVAILABLE_MSG = "method is not available";

    private CustomFieldOption customFieldOption;
    private RawArrayElement rawArrayElement4GetAll;
    private RawArrayElement rawArrayElement4Populate;
    private ArrayList<String> parameters;

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Rule
    public final ErrorCollector collector = new ErrorCollector();

    @Before
    public void setUp(){
        customFieldOption = new CustomFieldOption();
        rawArrayElement4GetAll = new RawArrayElement();
        rawArrayElement4Populate = new RawArrayElement();

        parameters = new ArrayList<>();
        parameters.add(Integer.toString(INT_FIELD_ID));
    }

    @Test
    public void shouldSetParentOptionId(){
        customFieldOption.setParentOptionId(INT_PARENT_OPTION_ID);
        collector.checkThat(customFieldOption.getParentOptionId(), equalTo(INT_PARENT_OPTION_ID));
    }

    @Test
    public void shouldSetValue(){
        customFieldOption.setValue(STR_VALUE);
        collector.checkThat(customFieldOption.getValue(), equalTo(STR_VALUE));
    }

    @Test
    public void shouldSetId(){
        customFieldOption.setId(INT_ID);
        collector.checkThat(customFieldOption.getId(), equalTo(INT_ID));
    }

    @Test
    public void shouldSetDisplayOrder(){
        customFieldOption.setDisplayOrder(INT_DISPLAY_ORDER);
        collector.checkThat(customFieldOption.getDisplayOrder(), equalTo(INT_DISPLAY_ORDER));
    }

    @Test
    public void shouldSetFieldId(){
        customFieldOption.setFieldId(INT_FIELD_ID);
        collector.checkThat(customFieldOption.getFieldId(), equalTo(INT_FIELD_ID));
    }

    @Test
    public void shouldSetSelectedTrue(){
        customFieldOption.setSelected(TRUE);
        collector.checkThat(customFieldOption.getSelected(), equalTo(TRUE));
    }

    @Test
    public void shouldSetSelectedFalse(){
        customFieldOption.setSelected(FALSE);
        collector.checkThat(customFieldOption.getSelected(), equalTo(FALSE));
    }

    @Test
    public void shouldSetReadOnlyTrue(){
        customFieldOption.setReadOnly(TRUE);
        collector.checkThat(customFieldOption.getReadOnly(), equalTo(TRUE));
    }

    @Test
    public void shouldSetReadOnlyFalse(){
        customFieldOption.setReadOnly(FALSE);
        collector.checkThat(customFieldOption.getReadOnly(), equalTo(FALSE));
    }

    @Test
    public void shouldGet() throws KayakoException {
        thrown.expect(KayakoException.class);
        thrown.expectMessage(containsString(METHOD_NOT_AVAILABLE_MSG));
        CustomFieldOption.get(INT_ID);
    }

    @Test
    public void shouldRefresh() throws KayakoException {
        thrown.expect(KayakoException.class);
        thrown.expectMessage(containsString(METHOD_NOT_AVAILABLE_MSG));
        customFieldOption.refresh();
    }

    @Test
    public void givenElementWhenPopulateThenCustomFieldOption() throws KayakoException {
        rawArrayElement4Populate.setElementName(CustomFieldOption.getObjectXmlName());
        rawArrayElement4Populate.setAttribute(CUSTOM_FIELD_ID, STR_INT_VALUE);
        rawArrayElement4Populate.setAttribute(CUSTOM_FIELD_OPTION_ID, STR_INT_ZERO);
        rawArrayElement4Populate.setAttribute(OPTION_VALUE, STR_VALUE);
        rawArrayElement4Populate.setAttribute(IS_SELECTED, STR_INT_ZERO);
        rawArrayElement4Populate.setAttribute(DISPLAY_ORDER, STR_INT_VALUE);
        rawArrayElement4Populate.setAttribute(PARENT_CUSTOM_FIELD_OPTION_ID, STR_INT_ZERO);
        customFieldOption.populate(rawArrayElement4Populate);

        collector.checkThat(customFieldOption.getId(), equalTo(Helper.parseInt(STR_INT_ZERO)));
        collector.checkThat(customFieldOption.getValue(), equalTo(STR_VALUE));
        collector.checkThat(customFieldOption.getFieldId(), equalTo(Helper.parseInt(STR_INT_VALUE)));
        collector.checkThat(customFieldOption.getSelected(), equalTo(FALSE));
        collector.checkThat(customFieldOption.getDisplayOrder(), equalTo(Helper.parseInt(STR_INT_VALUE)));
        collector.checkThat(customFieldOption.getParentOptionId(), equalTo(Helper.parseInt(STR_INT_ZERO)));
    }

    @Test (expected = KayakoException.class)
    public void givenElementWhenPopulateThenThrowKayakoException() throws KayakoException {
        customFieldOption.populate(rawArrayElement4Populate);
    }

    @Test
    public void shouldSetObjectXmlName(){
        CustomFieldOption.setObjectXmlName(CUSTOM_OBJECT_XML_NAME);
        collector.checkThat(CustomFieldOption.getObjectXmlName(), equalTo(CUSTOM_OBJECT_XML_NAME));
    }

    @Test
    public void shouldSetController(){
        CustomFieldOption.setController(CUSTOM_CONTROLLER);
        collector.checkThat(CustomFieldOption.getController(), equalTo(CUSTOM_CONTROLLER));
    }

    @Test
    public void givenFieldIdWhenGetAllThenRawArrayElement() throws KayakoException {
        mockStatic(KEntity.class);
        expect(KEntity.getAll(CustomFieldOption.getController(), parameters)).andReturn(rawArrayElement4GetAll);
        replay(KEntity.class);
        collector.checkThat(CustomFieldOption.getAll(INT_FIELD_ID), sameInstance(rawArrayElement4GetAll));
        verify(KEntity.class);
    }
}
