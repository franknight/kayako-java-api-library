package com.kayako.api.customfield;

import static java.lang.Boolean.TRUE;
import static org.easymock.EasyMock.mock;
import static org.easymock.EasyMock.expect;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.powermock.api.easymock.PowerMock.replay;
import static org.powermock.api.easymock.PowerMock.verify;

import org.junit.Rule;
import org.junit.Test;
import org.junit.Before;
import org.junit.rules.ErrorCollector;

import java.util.ArrayList;
import java.util.HashMap;

import com.kayako.api.rest.RawArrayElement;
import com.kayako.api.exception.KayakoException;

public class CustomFieldMultiSelectTest {

    private static final int IDX_ZERO = 0;
    private static final int INT_ID_1 = 1;
    private static final int INT_ID_2 = 2;
    private static final Object INT_ONE = 1;
    private static final String OPTION1_VALUE = "option1Value";
    private static final String OPTION2_VALUE = "option2Value";
    private static final String OPTION_VALUE4POPULATE_1 = "optionValue_4Populate1";
    private static final String OPTION_VALUE4POPULATE_2 = "optionValue_4Populate2";
    private static final String COMBINED_VALUE_4POPULATE = OPTION_VALUE4POPULATE_1 +
            CustomFieldMultiSelect.VALUE_SEPARATOR + OPTION_VALUE4POPULATE_2;
    private static final String RAW_VALUE2VALIDATE_SET_OPTION =
            OPTION1_VALUE + CustomFieldMultiSelect.VALUE_SEPARATOR +
                    OPTION2_VALUE+ CustomFieldMultiSelect.VALUE_SEPARATOR;

    private CustomFieldOption option1;
    private CustomFieldOption option2;
    private CustomFieldOption option3;
    private CustomFieldOption option4populate1;
    private CustomFieldOption option4populate2;
    private ArrayList<CustomFieldOption> optionList;

    private RawArrayElement rawArrayElement;
    private CustomFieldGroup mockedCustomFieldGroup;
    private CustomFieldMultiSelect customFieldMultiSelect;
    private CustomFieldDefinition mockedCustomFieldDefinition;

    @Rule
    public final ErrorCollector collector = new ErrorCollector();

    @Before
    public void setUp(){
        mockedCustomFieldGroup = mock(CustomFieldGroup.class);
        mockedCustomFieldDefinition = mock(CustomFieldDefinition.class);

        customFieldMultiSelect = new CustomFieldMultiSelect(mockedCustomFieldGroup);
        customFieldMultiSelect.setDefinition(mockedCustomFieldDefinition);

        option1 = new CustomFieldOption();
        option2 = new CustomFieldOption();
        option3 = new CustomFieldOption();
        option4populate1 = new CustomFieldOption();
        option4populate2 = new CustomFieldOption();

        option1.setId(INT_ID_1);
        option1.setId(INT_ID_2);
        option1.setValue(OPTION1_VALUE);
        option2.setValue(OPTION2_VALUE);

        optionList = new ArrayList<>();
        optionList.add(option1);
        optionList.add(option2);

        rawArrayElement = new RawArrayElement(CustomField.getObjectXmlName());
        rawArrayElement.setContent(COMBINED_VALUE_4POPULATE);
    }

    @Test
    public void givenOptionListWhenSetOptionsThenCustomFieldMultiSelect(){
        customFieldMultiSelect.setOptions(optionList);
        collector.checkThat(customFieldMultiSelect.getOptions(), sameInstance(optionList));
        collector.checkThat(customFieldMultiSelect.getRawValue(), equalTo(RAW_VALUE2VALIDATE_SET_OPTION));
    }

    @Test
    public void givenOptionWhenSetOptionsThenCustomFieldMultiSelect(){
        customFieldMultiSelect.setOptions(option3);
        collector.checkThat(customFieldMultiSelect.getOptions().size(), equalTo(INT_ONE));
        collector.checkThat(customFieldMultiSelect.getOptions().get(IDX_ZERO), sameInstance(option3));
    }

    @Test
    public void givenNewOptionWhenAddToOptions(){
        customFieldMultiSelect.addToOptions(option3);
        collector.checkThat(customFieldMultiSelect.getOptions().contains(option3), equalTo(TRUE));
    }

    @Test
    public void givenRawArrayElementWhenPopulateThenNewOptions() throws KayakoException {
        expect(mockedCustomFieldDefinition.getOptionByValue(OPTION_VALUE4POPULATE_1)).andReturn(option4populate1);
        expect(mockedCustomFieldDefinition.getOptionByValue(OPTION_VALUE4POPULATE_2)).andReturn(option4populate2);

        replay(mockedCustomFieldDefinition);
        customFieldMultiSelect.populate(rawArrayElement);
        verify(mockedCustomFieldDefinition);

        collector.checkThat(customFieldMultiSelect.getOptions().contains(option4populate1), equalTo(TRUE));
        collector.checkThat(customFieldMultiSelect.getOptions().contains(option4populate2), equalTo(TRUE));
    }

    @Test
    public void shouldBuildHashMap(){
        customFieldMultiSelect.setOptions(optionList);
        HashMap<String, String> builtHashMap = customFieldMultiSelect.buildHashMap(TRUE);
        collector.checkThat(builtHashMap.size(), equalTo(customFieldMultiSelect.getOptions().size()));
        collector.checkThat(builtHashMap.get(customFieldMultiSelect.getName() + "[0]"),
                equalTo(Integer.toString(customFieldMultiSelect.getOptions().get(0).getId())));
    }

    @Test
    public void shouldGetValues(){
        customFieldMultiSelect.setOptions(optionList);
        HashMap<String, String> values = customFieldMultiSelect.getValues();
        collector.checkThat(values.size(), equalTo(customFieldMultiSelect.getOptions().size()));
        collector.checkThat(values.get(Integer.toString(optionList.get(IDX_ZERO).getId())),
                equalTo(optionList.get(IDX_ZERO).getValue()));
    }
}
