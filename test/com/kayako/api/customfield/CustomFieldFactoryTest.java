package com.kayako.api.customfield;

import static com.kayako.api.customfield.CustomFieldLinkedSelect.PARENT_CHILD_SEPARATOR;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.mock;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.powermock.api.easymock.PowerMock.createMockAndExpectNew;
import static org.powermock.api.easymock.PowerMock.replayAll;
import static org.powermock.api.easymock.PowerMock.verifyAll;

import org.junit.Before;
import org.junit.Ignore;
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

	private static final int INT_JOINT_ID = 0;
	private static final int INT_PARENT_ID = 1;
	private static final int INT_CHILD_ID = 2;
	private static final String STR_CHILD_VALUE = "childValue";
	private static final String STR_PARENT_VALUE = "parentValue";
	private static final String STR_JOINT_VALUE = STR_PARENT_VALUE + PARENT_CHILD_SEPARATOR + STR_CHILD_VALUE;

	private CustomFieldOption jointOption;
	private CustomFieldOption childOption;
	private CustomFieldOption parentOption;

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
	@Ignore
	public void shouldCreateCustomFieldFile() throws Exception {
		// Arrange
		rawArrayElement.setAttribute(TYPE, CustomFieldDefinitionTypeEnum.FILE.getString());
		rawArrayElement.setAttribute(FILE_NAME, STR_VALUE);

		// Act
		CustomField customField = CustomFieldFactory.createCustomField(customFieldGroupMocked, rawArrayElement);

		// Assert
		collector.checkThat(customField, instanceOf(CustomFieldFile.class));
		collector.checkThat(((CustomFieldFile) customField).getFileName(), equalTo(STR_VALUE));
	}

	private CustomFieldLinkedSelect createCustomFieldForTest() throws Exception {
		CustomFieldLinkedSelect customField = new CustomFieldLinkedSelect(customFieldGroupMocked);

		jointOption = new CustomFieldOption();
		childOption = new CustomFieldOption();
		parentOption = new CustomFieldOption();

		jointOption.setId(INT_JOINT_ID);
		jointOption.setValue(STR_JOINT_VALUE);
		childOption.setId(INT_CHILD_ID);
		childOption.setValue(STR_CHILD_VALUE);
		childOption.setParentOptionId(INT_PARENT_ID);
		parentOption.setId(INT_PARENT_ID);
		parentOption.setValue(STR_PARENT_VALUE);

		CustomFieldDefinition definition = mock(CustomFieldDefinition.class);
		customField.setDefinition(definition);

		expect(definition.getOptionById(INT_JOINT_ID)).andReturn(jointOption);
		expect(definition.getOptionById(INT_CHILD_ID)).andReturn(parentOption);
		expect(definition.getOptionById(INT_PARENT_ID)).andReturn(parentOption);
		expect(definition.getOptionByValue(STR_JOINT_VALUE)).andReturn(jointOption);
		expect(definition.getOptionByValue(STR_CHILD_VALUE)).andReturn(childOption);
		expect(definition.getOptionByValue(STR_PARENT_VALUE)).andReturn(parentOption);

		return customField;
	}

	@Test
	@PrepareForTest(CustomFieldFactory.class)
	public void shouldCreateCustomFieldLinkedSelect() throws Exception {
		// Arrange
		rawArrayElement = new RawArrayElement(CustomField.getObjectXmlName(), STR_JOINT_VALUE);
		rawArrayElement.setAttribute(TYPE, CustomFieldDefinitionTypeEnum.LINKED_SELECT.getString());

		CustomFieldLinkedSelect customFieldLinkedSelect = createCustomFieldForTest();
		CustomFieldLinkedSelect customFieldLinkedSelectMocked = createMockAndExpectNew(CustomFieldLinkedSelect.class,
				customFieldGroupMocked);
		
		expect(customFieldLinkedSelectMocked.populate(rawArrayElement)).andReturn(customFieldLinkedSelect);

		// Act
		replayAll();
		CustomField customField = CustomFieldFactory.createCustomField(customFieldGroupMocked, rawArrayElement);
		verifyAll();

		// Assert
		collector.checkThat(customField, instanceOf(CustomFieldLinkedSelect.class));
	}

	@Test
	@PrepareForTest(CustomFieldFactory.class)
	public void shouldCreateCustomFieldMultiSelect() throws Exception {
		// Arrange
		rawArrayElement.setAttribute(TYPE, CustomFieldDefinitionTypeEnum.MULTI_SELECT.getString());

		CustomFieldMultiSelect mockedCustomFieldMultiSelect = createMockAndExpectNew(CustomFieldMultiSelect.class,
				customFieldGroupMocked);

		expect(mockedCustomFieldMultiSelect.populate(rawArrayElement)).andReturn(mockedCustomFieldMultiSelect);

		// Act
		replayAll();
		CustomField customField = CustomFieldFactory.createCustomField(customFieldGroupMocked, rawArrayElement);
		verifyAll();

		// Assert
		collector.checkThat(customField, instanceOf(CustomFieldMultiSelect.class));
	}

	@Test
	@PrepareForTest(CustomFieldFactory.class)
	public void shouldCreateCustomFieldSelect() throws Exception {
		// Arrange
		rawArrayElement.setAttribute(TYPE, CustomFieldDefinitionTypeEnum.SELECT.getString());
		CustomFieldSelect mockedCustomFieldSelect = createMockAndExpectNew(CustomFieldSelect.class,
				customFieldGroupMocked);
		expect(mockedCustomFieldSelect.populate(rawArrayElement)).andReturn(mockedCustomFieldSelect);

		// Act
		replayAll();
		CustomField customField = CustomFieldFactory.createCustomField(customFieldGroupMocked, rawArrayElement);
		verifyAll();

		// Assert
		collector.checkThat(customField, instanceOf(CustomFieldSelect.class));
	}

	@Test
	public void shouldCreateCustomFieldDate() throws Exception {
		// Arrange
		rawArrayElement.setAttribute(TYPE, CustomFieldDefinitionTypeEnum.DATE.getString());
		rawArrayElement.setElementName(CustomFieldDate.getObjectXmlName());

		// Act
		CustomField customField = CustomFieldFactory.createCustomField(customFieldGroupMocked, rawArrayElement);

		// Assert
		collector.checkThat(customField, instanceOf(CustomFieldDate.class));
	}

	@Test
	public void shouldCreateCustomField() throws Exception {
		// Arrange
		rawArrayElement.setAttribute(TYPE, CustomFieldDefinitionTypeEnum.TEXT.getString());
		rawArrayElement.setAttribute(TITLE_KEY, STR_VALUE4TITLE);
		rawArrayElement.setAttribute(NAME_KEY, STR_VALUE4NAME);
		rawArrayElement.setElementName(CustomField.getObjectXmlName());

		// Act
		CustomField customField = CustomFieldFactory.createCustomField(customFieldGroupMocked, rawArrayElement);

		// Assert
		collector.checkThat(customField.getName(), equalTo(STR_VALUE4NAME));
		collector.checkThat(customField.getTitle(), equalTo(STR_VALUE4TITLE));
	}

	@Test
	public void givenNullElementWhenCreateCustomFieldThenKayakoException() throws Exception {
		thrown.expect(KayakoException.class);
		thrown.expectMessage(containsString(INVALID_XML_ELEMENT_MSG));

		CustomFieldFactory.createCustomField(customFieldGroupMocked, null);
	}

	@Test
	public void givenElementWithInvalidElementNameWhenCreateCustomFieldThenKayakoException() throws Exception {
		thrown.expect(KayakoException.class);
		thrown.expectMessage(containsString(INVALID_XML_ELEMENT_MSG));

		rawArrayElement.setElementName(INVALID_ELEMENT_NAME);
		CustomFieldFactory.createCustomField(customFieldGroupMocked, rawArrayElement);
	}
}
