package com.kayako.api.util;

import static org.easymock.EasyMock.mock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.anyInt;
import static org.easymock.EasyMock.anyObject;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.powermock.api.easymock.PowerMock.replay;
import static org.powermock.api.easymock.PowerMock.verify;
import static org.powermock.api.easymock.PowerMock.createMockAndExpectNew;
import static com.kayako.tests.Test.API_URL;
import static com.kayako.tests.Test.API_KEY;
import static com.kayako.tests.Test.SECRET_KEY;

import org.junit.rules.ExpectedException;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.rules.ErrorCollector;
import java.io.File;
import java.io.Reader;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.text.Format;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.kayako.api.configuration.Configuration;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Helper.class)
public class HelperTest {

    private final static int INT_ZERO = 0;
    private final static long LONG_ZERO = 0L;
    private final static int VALID_INT = 123;
    private final static int INT_MINUS_ONE = -1;
    private final static String STR_VALID_INT = "123";
    private final static String FILE_NAME = "fileName";
    private static final String CHARSET_UTF_8 = "UTF-8";
    private final static String STR_INVALID_INT = "#123";
    private static final String STR_INVALID_DATE = "30/02/2018";
    private final static long MAX_VALID_LONG = 9223372036854775807L;
    private final static String STR_VALID_LONG = "9223372036854775807";
    private final static String STR_INVALID_LONG = "9223372036854775807L";
    private final static long currentTimestamp = System.currentTimeMillis();
    private static final String FILE_TOO_LONG_MSG = "it is too long";
    private static final String NOT_COMPLETELY_READ_FILE_MSG = "Could not completely read file " + FILE_NAME;

    private Date currentDate;
    private Format formatter;
    private String currentDateStr;

    private File mockedFile;
    private Reader mockedReader;
    private InputStream mockedInputStream;

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Rule
    public final ErrorCollector collector = new ErrorCollector();
    @Before
    public void setUp() throws Exception {
        Configuration.init(API_URL, API_KEY, SECRET_KEY);

        currentDate = new Date(currentTimestamp);
        formatter = new SimpleDateFormat(Configuration.getConfiguration().getDateFormat());
        currentDateStr = formatter.format(currentDate);

        mockedFile = mock(File.class);

        mockedInputStream = createMockAndExpectNew(FileInputStream.class, mockedFile);
        mockedInputStream.close();
        expectLastCall();

        mockedReader = createMockAndExpectNew(InputStreamReader.class, mockedInputStream, CHARSET_UTF_8);
        mockedReader.close();
        expectLastCall();
    }

    @Test
    public void givenValidStringWhenParseIntThenInteger(){
        collector.checkThat(Helper.parseInt(STR_VALID_INT), equalTo(VALID_INT));
    }

    @Test
    public void givenInvalidStringWhenParseIntThenZero(){
        collector.checkThat(Helper.parseInt(STR_INVALID_INT), equalTo(INT_ZERO));
    }

    @Test
    public void givenValidStringWhenParseLongThenLongInteger(){
        collector.checkThat(Helper.parseLong(STR_VALID_LONG), equalTo(MAX_VALID_LONG));
    }

    @Test
    public void givenInvalidStringWhenParseLongThenZero(){
        collector.checkThat(Helper.parseLong(STR_INVALID_LONG), equalTo(LONG_ZERO));
    }

    @Test
    public void givenTimestampWhenGetDateStringThenString(){
        collector.checkThat(Helper.getDateString(currentTimestamp), equalTo(formatter.format(currentDate)));
    }

    @Test
    public void givenStringWhenGetTimeStampFromDateStringThenTimestamp() throws ParseException {
        collector.checkThat(Helper.getTimeStampFromDateString(currentDateStr),
                equalTo(((DateFormat)formatter).parse(currentDateStr).getTime()));
    }

    @Test(expected = ParseException.class)
    public void givenInvalidStringWhenGetTimeStampFromDateStringThenParseException() throws ParseException {
        Helper.getTimeStampFromDateString(STR_INVALID_DATE);
    }

    @Test
    public void givenHugeFileWhenReadBytesFromFileThenIOException() throws IOException {
        expect(mockedFile.getName()).andReturn(FILE_NAME);
        expect(mockedFile.length()).andReturn(MAX_VALID_LONG);

        thrown.expect(IOException.class);
        thrown.expectMessage(containsString(FILE_TOO_LONG_MSG));

        replay(mockedFile, FileInputStream.class);
        Helper.readBytesFromFile(mockedFile);
        verify(mockedFile, FileInputStream.class);
    }

    @Test
    public void givenFileWhenReadBytesFromFileThenBytes() throws Exception {
        expect(mockedFile.length()).andReturn((long)VALID_INT);
        expect(mockedInputStream.read(anyObject(), anyInt(), anyInt())).andReturn(VALID_INT);
        replay(FileInputStream.class, mockedInputStream, mockedFile);
        collector.checkThat(Helper.readBytesFromFile(mockedFile).length, equalTo(VALID_INT));
        verify(FileInputStream.class, mockedInputStream, mockedFile);
    }

    @Test
    public void givenFileWhenReadBytesFromFileThenIOException() throws Exception {
        expect(mockedFile.getName()).andReturn(FILE_NAME);
        expect(mockedFile.length()).andReturn((long)VALID_INT);
        expect(mockedInputStream.read(anyObject(), anyInt(), anyInt())).andReturn(INT_MINUS_ONE);

        thrown.expect(IOException.class);
        thrown.expectMessage(equalTo(NOT_COMPLETELY_READ_FILE_MSG));

        replay(FileInputStream.class, mockedInputStream, mockedFile);
        Helper.readBytesFromFile(mockedFile);
        verify(FileInputStream.class, mockedInputStream, mockedFile);
    }

    @Test
    public void givenInputStreamWhenSlurpThenUnsupportedEncodingException() throws IOException {
        expect(mockedReader.read(anyObject(), anyInt(), anyInt())).andThrow(new UnsupportedEncodingException());
        replay(InputStreamReader.class, mockedReader);
        String slurpResult = Helper.slurp(mockedInputStream, VALID_INT);
        verify(InputStreamReader.class, mockedReader);
        collector.checkThat(slurpResult.length(), equalTo(INT_ZERO));
    }

    @Test
    public void givenInputStreamWhenSlurpThenIOException() throws IOException {
        expect(mockedReader.read(anyObject(), anyInt(), anyInt())).andThrow(new IOException());
        replay(InputStreamReader.class, mockedReader);
        String slurpResult = Helper.slurp(mockedInputStream, VALID_INT);
        verify(InputStreamReader.class, mockedReader);
        collector.checkThat(slurpResult.length(), equalTo(INT_ZERO));
    }

    @Test
    public void givenInputStreamWhenSlurpThenString() throws IOException {
        expect(mockedReader.read(anyObject(), anyInt(), anyInt())).andReturn(VALID_INT).andReturn(INT_MINUS_ONE);
        replay(InputStreamReader.class, mockedReader);
        String slurpResult = Helper.slurp(mockedInputStream, VALID_INT);
        verify(InputStreamReader.class, mockedReader);
        collector.checkThat(slurpResult.length(), equalTo(VALID_INT));
    }
}
