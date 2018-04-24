package com.kayako.api.customfield;


import static org.easymock.EasyMock.mock;
import static org.easymock.EasyMock.expect;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.powermock.api.easymock.PowerMock.replayAll;
import static org.powermock.api.easymock.PowerMock.verifyAll;
import static org.powermock.api.easymock.PowerMock.createMockAndExpectNew;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.rules.ErrorCollector;
import com.kayako.api.rest.RawArrayElement;
import com.kayako.api.exception.KayakoException;
import com.kayako.api.configuration.Configuration;
import com.kayako.api.enums.CustomFieldDefinitionTypeEnum;

@RunWith(PowerMockRunner.class)
@PrepareForTest(CustomFieldFactory.class)
public class CustomFieldFactoryTest {

    private static final String TYPE = "type";
    private static final String API_KEY = "apiKey";
    private static final String BASE_URL = "baseUrl";
    private static final String SECRET_KEY = "secretKey";
    private static final String NAME_KEY = "name";
    private static final String TITLE_KEY = "title";
    private static final String FILE_NAME = "filename";
    private static final String STR_VALUE = "strValue";
    private static final String STR_VALUE4NAME = "strValue4Name";
    private static final String STR_VALUE4TITLE= "strValue4Title";
    private static final String INVALID_ELEMENT_NAME = "invalidElementName";

    private RawArrayElement rawArrayElement;
    private CustomFieldGroup mockedCustomFieldGroup;

    @Rule
    public final ErrorCollector collector = new ErrorCollector();

    @Before
    public void setUp(){
        mockedCustomFieldGroup = mock(CustomFieldGroup.class);

        rawArrayElement = new RawArrayElement(CustomField.getObjectXmlName());
        rawArrayElement.setAttribute(TYPE, CustomFieldDefinitionTypeEnum.TEXT.toString());

        Configuration.init(BASE_URL, API_KEY, SECRET_KEY);
    }

    @Test
    public void shouldCreateCustomFieldFile() throws KayakoException {
        rawArrayElement.setAttribute(TYPE, CustomFieldDefinitionTypeEnum.FILE.getString());
        rawArrayElement.setAttribute(FILE_NAME, STR_VALUE);
        CustomField customField = CustomFieldFactory.createCustomField(mockedCustomFieldGroup, rawArrayElement);

        collector.checkThat(customField, instanceOf(CustomFieldFile.class));
        collector.checkThat(((CustomFieldFile)customField).getFileName(), equalTo(STR_VALUE));
    }

    @Test
    public void shouldCreateCustomFieldLinkedSelect() throws Exception {
        rawArrayElement.setAttribute(TYPE, CustomFieldDefinitionTypeEnum.LINKED_SELECT.getString());

        CustomFieldLinkedSelect mockedCustomFieldLinkedSelect = createMockAndExpectNew(CustomFieldLinkedSelect.class, mockedCustomFieldGroup);
        expect(mockedCustomFieldLinkedSelect.populate(rawArrayElement)).andReturn(mockedCustomFieldLinkedSelect);

        replayAll();
        CustomField customField = CustomFieldFactory.createCustomField(mockedCustomFieldGroup, rawArrayElement);
        verifyAll();
        collector.checkThat(customField, instanceOf(CustomFieldLinkedSelect.class));
        collector.checkThat(customField, is(notNullValue()));
    }

    @Test
    public void shouldCreateCustomFieldMultiSelect() throws Exception {
        rawArrayElement.setAttribute(TYPE, CustomFieldDefinitionTypeEnum.MULTI_SELECT.getString());

        CustomFieldMultiSelect mockedCustomFieldMultiSelect = createMockAndExpectNew(CustomFieldMultiSelect.class, mockedCustomFieldGroup);
        expect(mockedCustomFieldMultiSelect.populate(rawArrayElement)).andReturn(mockedCustomFieldMultiSelect);

        replayAll();
        CustomField customField = CustomFieldFactory.createCustomField(mockedCustomFieldGroup, rawArrayElement);
        verifyAll();
        collector.checkThat(customField, instanceOf(CustomFieldMultiSelect.class));
        collector.checkThat(customField, is(notNullValue()));
    }

    @Test
    public void shouldCreateCustomFieldSelect() throws Exception {
        rawArrayElement.setAttribute(TYPE, CustomFieldDefinitionTypeEnum.SELECT.getString());

        CustomFieldSelect mockedCustomFieldSelect = createMockAndExpectNew(CustomFieldSelect.class, mockedCustomFieldGroup);
        expect(mockedCustomFieldSelect.populate(rawArrayElement)).andReturn(mockedCustomFieldSelect);

        replayAll();
        CustomField customField = CustomFieldFactory.createCustomField(mockedCustomFieldGroup, rawArrayElement);
        verifyAll();
        collector.checkThat(customField, instanceOf(CustomFieldSelect.class));
        collector.checkThat(customField, is(notNullValue()));
    }

    @Test
    public void shouldCreateCustomFieldDate() throws KayakoException {
        rawArrayElement.setAttribute(TYPE, CustomFieldDefinitionTypeEnum.DATE.getString());
        rawArrayElement.setElementName(CustomFieldDate.getObjectXmlName());
        CustomField customField = CustomFieldFactory.createCustomField(mockedCustomFieldGroup, rawArrayElement);

        collector.checkThat(customField, instanceOf(CustomFieldDate.class));
        collector.checkThat(customField, is(notNullValue()));
    }

    @Test
    public void shouldCreateCustomField() throws KayakoException {
        rawArrayElement.setAttribute(TYPE, CustomFieldDefinitionTypeEnum.TEXT.getString());
        rawArrayElement.setAttribute(TITLE_KEY, STR_VALUE4TITLE);
        rawArrayElement.setAttribute(NAME_KEY, STR_VALUE4NAME);
        rawArrayElement.setElementName(CustomField.getObjectXmlName());
        CustomField customField = CustomFieldFactory.createCustomField(mockedCustomFieldGroup, rawArrayElement);

        collector.checkThat(customField, is(notNullValue()));
        collector.checkThat(customField.getName(), equalTo(STR_VALUE4NAME));
        collector.checkThat(customField.getTitle(), equalTo(STR_VALUE4TITLE));
    }

    @Test (expected = KayakoException.class)
    public void givenNullElementWhenCreateCustomFieldThenKayakoException() throws KayakoException {
        CustomFieldFactory.createCustomField(mockedCustomFieldGroup, null);
    }

    @Test (expected = KayakoException.class)
    public void givenElementWithInvalidElementNameWhenCreateCustomFieldThenKayakoException() throws KayakoException {
        rawArrayElement.setElementName(INVALID_ELEMENT_NAME);
        CustomFieldFactory.createCustomField(mockedCustomFieldGroup, rawArrayElement);
    }
}
