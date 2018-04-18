package com.kayako.api.customfield;

import static java.lang.Boolean.TRUE;
import static java.lang.Boolean.FALSE;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.mock;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.powermock.api.easymock.PowerMock.replay;
import static org.powermock.api.easymock.PowerMock.verify;
import static org.powermock.api.easymock.PowerMock.mockStatic;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.rules.ErrorCollector;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import net.iharder.Base64;
import java.io.IOException;
import com.kayako.api.util.Helper;
import com.kayako.api.rest.RawArrayElement;
import com.kayako.api.exception.KayakoException;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Helper.class)
public class CustomFieldFileTest {

    private static final String CONTENTS = "CONTENTS";
    private static final String CONTENTS_2 = "CONTENTS_2";
    private static final String FILE_NAME = "filename";
    private static final String FILE_PATH = "file_path";
    private CustomFieldFile customFieldFile;
    private CustomFieldGroup customFieldGroup;
    private Map<String, String> attributes;

    @Rule
    public final ErrorCollector collector = new ErrorCollector();

    @Before
    public void setUp(){
        customFieldGroup = mock(CustomFieldGroup.class);
        customFieldFile = new CustomFieldFile(customFieldGroup);

        attributes = new HashMap<>();
    }

    @Test
    public void shouldSetContents(){
        customFieldFile.setContents(CONTENTS.getBytes());
        collector.checkThat(customFieldFile.getContents(), equalTo(CONTENTS.getBytes()));
    }

    @Test
    public void givenNameWhenSetFileName(){
        customFieldFile.setFileName(FILE_NAME);
        collector.checkThat(customFieldFile.getFileName(), equalTo(FILE_NAME));
    }

    @Test
    public void givenTrueWhenSetChanged(){
        customFieldFile.setChanged(TRUE);
        collector.checkThat(customFieldFile.getChanged(), equalTo(TRUE));
    }

    @Test
    public void givenFalseWhenSetChanged(){
        customFieldFile.setChanged(FALSE);
        collector.checkThat(customFieldFile.getChanged(), equalTo(FALSE));
    }

    @Test
    public void givenElementWhenPopulateThenCustomFieldFile() throws KayakoException {
        RawArrayElement rawArrayElement = new RawArrayElement(CustomField.getObjectXmlName(), attributes, Base64.encodeBytes(CONTENTS.getBytes()));
        collector.checkThat(customFieldFile.populate(rawArrayElement), sameInstance(customFieldFile));
    }

    @Test
    public void shouldSetContentFromFile() throws IOException {
        File file = new File(FILE_PATH);
        customFieldFile.setFileName(FILE_NAME); //

        mockStatic(Helper.class);
        expect(Helper.readBytesFromFile(file)).andReturn(CONTENTS_2.getBytes());
        replay(Helper.class);
        customFieldFile.setContentFromFile(file);
        verify(Helper.class);

        collector.checkThat(customFieldFile.getContents(), equalTo(CONTENTS_2.getBytes()));
    }

    @Test
    public void shouldBuildHashMap(){
        HashMap<String, String> builtHashMap = customFieldFile.buildHashMap(TRUE);
        collector.checkThat(builtHashMap, is(notNullValue()));
        collector.checkThat(builtHashMap, instanceOf(HashMap.class));
    }

    @Test
    public void shouldBuildFilesHashMap(){
        customFieldFile.setFileName(FILE_NAME);
        customFieldFile.setContents(CONTENTS.getBytes());
        HashMap<String, HashMap<String, String>> builtFileHashMap = customFieldFile.buildFilesHashMap();
        collector.checkThat(builtFileHashMap, is(notNullValue()));
        collector.checkThat(builtFileHashMap, instanceOf(HashMap.class));
    }
}