package com.kayako.api.customfield;

import static java.lang.Boolean.TRUE;
import static org.easymock.EasyMock.mock;
import static org.easymock.EasyMock.expect;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.powermock.api.easymock.PowerMock.replay;
import static org.powermock.api.easymock.PowerMock.verify;
import static com.kayako.api.customfield.CustomFieldLinkedSelect.PARENT_CHILD_SEPARATOR;

import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.rules.ErrorCollector;

import com.kayako.api.rest.RawArrayElement;
import com.kayako.api.exception.KayakoException;

import java.util.HashMap;

@RunWith(PowerMockRunner.class)
@PrepareForTest(CustomFieldDefinition.class)
public class CustomFieldLinkedSelectTest {

    private static final int INT_ONE = 1;
    private static final int INT_TWO = 2;
    private static final int INT_JOINT_ID = 0;
    private static final int INT_PARENT_ID= 1;
    private static final int INT_CHILD_ID = 2;
    private static final String STR_CHILD_VALUE = "childValue";
    private static final String STR_PARENT_VALUE = "parentValue";
    private static final String STR_JOINT_VALUE = STR_PARENT_VALUE + PARENT_CHILD_SEPARATOR + STR_CHILD_VALUE;

    private CustomFieldOption jointOption;
    private CustomFieldOption childOption;
    private CustomFieldOption parentOption;
    private RawArrayElement rawArrayElement;
    private CustomFieldGroup customFieldGroupMocked;
    private CustomFieldLinkedSelect customFieldLinkedSelect;
    private CustomFieldDefinition customFieldDefinitionMocked;

    @Before
    public void setUp() {
        customFieldGroupMocked = mock(CustomFieldGroup.class);
        customFieldLinkedSelect = new CustomFieldLinkedSelect(customFieldGroupMocked);

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

        customFieldDefinitionMocked = mock(CustomFieldDefinition.class);
        customFieldLinkedSelect.setDefinition(customFieldDefinitionMocked);
    }

    @Rule
    public final ErrorCollector collector = new ErrorCollector();

    @Test
    public void givenElementWhenPopulateThenCFLinkedSelect() throws KayakoException {
        expect(customFieldDefinitionMocked.getOptionById(INT_JOINT_ID)).andReturn(jointOption);
        expect(customFieldDefinitionMocked.getOptionById(INT_PARENT_ID)).andReturn(parentOption);
        expect(customFieldDefinitionMocked.getOptionByValue(STR_CHILD_VALUE)).andReturn(childOption);
        expect(customFieldDefinitionMocked.getOptionByValue(STR_JOINT_VALUE)).andReturn(jointOption);
        rawArrayElement = new RawArrayElement(CustomField.getObjectXmlName(), STR_JOINT_VALUE);
        replay(customFieldDefinitionMocked);
        customFieldLinkedSelect.populate(rawArrayElement);
        verify(customFieldDefinitionMocked);
        collector.checkThat(customFieldLinkedSelect.getSelectedOption(), sameInstance(childOption));
    }

    @Test
    public void givenNullWhenSetSelectedOptionThenSetRawValueAsNull() throws KayakoException {
        customFieldLinkedSelect.setSelectedOption(null);

        collector.checkThat(customFieldLinkedSelect.getRawValue(), is(nullValue()));
    }

    @Test
    public void givenCFOptionWhenSetSelectedOptionThenCFLinkedSelect() throws KayakoException {
        expect(customFieldDefinitionMocked.getOptionById(INT_PARENT_ID)).andReturn(parentOption);

        replay(customFieldDefinitionMocked);
        customFieldLinkedSelect.setSelectedOption(childOption);
        verify(customFieldDefinitionMocked);

        collector.checkThat(customFieldLinkedSelect.getRawValue(), equalTo(STR_JOINT_VALUE));
    }

    @Test
    public void givenZeroAsParentOptionIdWhenBuildHashMapThenEmptyHashMap() throws KayakoException {
        expect(customFieldDefinitionMocked.getOptionById(INT_JOINT_ID)).andReturn(jointOption);
        givenOptionWhenBuildHashMapThenHashMap(parentOption, INT_ONE);
    }

    @Test
    public void givenChildOptionSelectedWhenBuildHashMapThenEmptyHashMap() throws KayakoException {
        expect(customFieldDefinitionMocked.getOptionById(INT_PARENT_ID)).andReturn(parentOption);
        givenOptionWhenBuildHashMapThenHashMap(childOption, INT_TWO);
    }

    private void givenOptionWhenBuildHashMapThenHashMap(CustomFieldOption option, int valueShouldEqual) throws KayakoException {
        replay(customFieldDefinitionMocked);
        customFieldLinkedSelect.setSelectedOption(option);
        verify(customFieldDefinitionMocked);
        HashMap<String, String> builtHashMap = customFieldLinkedSelect.buildHashMap(TRUE);

        collector.checkThat(builtHashMap.size(), equalTo(valueShouldEqual));
    }
}
