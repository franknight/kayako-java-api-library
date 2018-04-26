package com.kayako.api.customfield;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.mock;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.powermock.api.easymock.PowerMock.createMockAndExpectNew;
import static org.powermock.api.easymock.PowerMock.replayAll;
import static org.powermock.api.easymock.PowerMock.verifyAll;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.kayako.api.configuration.Configuration;
import com.kayako.api.enums.CustomFieldDefinitionTypeEnum;
import com.kayako.api.exception.KayakoException;
import com.kayako.api.rest.RawArrayElement;

@RunWith(PowerMockRunner.class)
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
	private static final String STR_VALUE4TITLE = "strValue4Title";
	private static final String INVALID_ELEMENT_NAME = "invalidElementName";
	private static final String INVALID_XML_ELEMENT_MSG = "Invalid XML Element";

	private RawArrayElement rawArrayElement;
	private CustomFieldGroup customFieldGroupMocked;

	@Rule
	public final ExpectedException thrown = ExpectedException.none();

	@Rule
	public final ErrorCollector collector = new ErrorCollector();

	@Before
	public void setUp() {
		customFieldGroupMocked = mock(CustomFieldGroup.class);

		rawArrayElement = new RawArrayElement(CustomField.getObjectXmlName());
		rawArrayElement.setAttribute(TYPE, CustomFieldDefinitionTypeEnum.TEXT.toString());

		Configuration.init(BASE_URL, API_KEY, SECRET_KEY);
	}

	@Test
	public void shouldCreateCustomFieldFile() throws KayakoException {
		// Arrange
		rawArrayElement.setAttribute(TYPE, CustomFieldDefinitionTypeEnum.FILE.getString());
		rawArrayElement.setAttribute(FILE_NAME, STR_VALUE);

		// Act
		CustomField customField = CustomFieldFactory.createCustomField(customFieldGroupMocked, rawArrayElement);

		// Assert
		collector.checkThat(customField, instanceOf(CustomFieldFile.class));
		collector.checkThat(((CustomFieldFile) customField).getFileName(), equalTo(STR_VALUE));
	}

	@Test
	@PrepareForTest(CustomFieldFactory.class)
	public void shouldCreateCustomFieldLinkedSelect() throws Exception {
		// Arrange
		rawArrayElement.setAttribute(TYPE, CustomFieldDefinitionTypeEnum.LINKED_SELECT.getString());
		CustomFieldLinkedSelect mockedCustomFieldLinkedSelect = mock(CustomFieldLinkedSelect.class);
		expect(mockedCustomFieldLinkedSelect.populate(rawArrayElement)).andReturn(mockedCustomFieldLinkedSelect);
		
		// Act
		replayAll();
		CustomField customField = CustomFieldFactory.createCustomField(customFieldGroupMocked, rawArrayElement);
		verifyAll();
		
		// Assert
		collector.checkThat(customField, instanceOf(CustomFieldLinkedSelect.class));
		collector.checkThat(customField, is(notNullValue()));
	}

	@Test
	@PrepareForTest(CustomFieldFactory.class)
	public void shouldCreateCustomFieldMultiSelect() throws Exception {
		// Arrange
		rawArrayElement.setAttribute(TYPE, CustomFieldDefinitionTypeEnum.MULTI_SELECT.getString());
		CustomFieldMultiSelect mockedCustomFieldMultiSelect = createMockAndExpectNew(CustomFieldMultiSelect.class, customFieldGroupMocked);
		expect(mockedCustomFieldMultiSelect.populate(rawArrayElement)).andReturn(mockedCustomFieldMultiSelect);

		// Act
		replayAll();
		CustomField customField = CustomFieldFactory.createCustomField(customFieldGroupMocked, rawArrayElement);
		verifyAll();

		// Assert
		collector.checkThat(customField, instanceOf(CustomFieldMultiSelect.class));
		collector.checkThat(customField, is(notNullValue()));
	}

	@Test
	@PrepareForTest(CustomFieldFactory.class)
	public void shouldCreateCustomFieldSelect() throws Exception {
		// Arrange
		rawArrayElement.setAttribute(TYPE, CustomFieldDefinitionTypeEnum.SELECT.getString());
		CustomFieldSelect mockedCustomFieldSelect = createMockAndExpectNew(CustomFieldSelect.class, customFieldGroupMocked);
		expect(mockedCustomFieldSelect.populate(rawArrayElement)).andReturn(mockedCustomFieldSelect);

		// Act
		replayAll();
		CustomField customField = CustomFieldFactory.createCustomField(customFieldGroupMocked, rawArrayElement);
		verifyAll();

		// Assert
		collector.checkThat(customField, instanceOf(CustomFieldSelect.class));
		collector.checkThat(customField, is(notNullValue()));
	}

	@Test
	public void shouldCreateCustomFieldDate() throws KayakoException {
		// Arrange
		rawArrayElement.setAttribute(TYPE, CustomFieldDefinitionTypeEnum.DATE.getString());
		rawArrayElement.setElementName(CustomFieldDate.getObjectXmlName());

		// Act
		CustomField customField = CustomFieldFactory.createCustomField(customFieldGroupMocked, rawArrayElement);

		// Assert
		collector.checkThat(customField, instanceOf(CustomFieldDate.class));
		collector.checkThat(customField, is(notNullValue()));
	}

	@Test
	public void shouldCreateCustomField() throws KayakoException {
		// Arrange
		rawArrayElement.setAttribute(TYPE, CustomFieldDefinitionTypeEnum.TEXT.getString());
		rawArrayElement.setAttribute(TITLE_KEY, STR_VALUE4TITLE);
		rawArrayElement.setAttribute(NAME_KEY, STR_VALUE4NAME);
		rawArrayElement.setElementName(CustomField.getObjectXmlName());

		// Act
		CustomField customField = CustomFieldFactory.createCustomField(customFieldGroupMocked, rawArrayElement);

		// Assert
		collector.checkThat(customField, is(notNullValue()));
		collector.checkThat(customField.getName(), equalTo(STR_VALUE4NAME));
		collector.checkThat(customField.getTitle(), equalTo(STR_VALUE4TITLE));
	}

	@Test
	public void givenNullElementWhenCreateCustomFieldThenKayakoException() throws KayakoException {
		thrown.expect(KayakoException.class);
		thrown.expectMessage(containsString(INVALID_XML_ELEMENT_MSG));

		CustomFieldFactory.createCustomField(customFieldGroupMocked, null);
	}

	@Test
	public void givenElementWithInvalidElementNameWhenCreateCustomFieldThenKayakoException() throws KayakoException {
		thrown.expect(KayakoException.class);
		thrown.expectMessage(containsString(INVALID_XML_ELEMENT_MSG));

		rawArrayElement.setElementName(INVALID_ELEMENT_NAME);
		CustomFieldFactory.createCustomField(customFieldGroupMocked, rawArrayElement);
	}
}
