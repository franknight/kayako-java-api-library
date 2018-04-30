package com.kayako.api.util.library;

import static com.kayako.tests.Test.API_KEY;
import static com.kayako.tests.Test.API_URL;
import static com.kayako.tests.Test.SECRET_KEY;
import static java.net.HttpURLConnection.HTTP_OK;
import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static org.easymock.EasyMock.mock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.anyString;
import static org.easymock.EasyMock.anyObject;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.powermock.api.easymock.PowerMock.mockStatic;
import static org.powermock.api.easymock.PowerMock.expectLastCall;
import static org.powermock.api.easymock.PowerMock.replay;
import static org.powermock.api.easymock.PowerMock.verify;
import static org.powermock.api.easymock.PowerMock.expectNew;
import static org.powermock.api.easymock.PowerMock.createMockAndExpectNew;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.rules.ErrorCollector;
import com.kayako.api.rest.RawArrayElement;
import com.kayako.api.enums.HttpResponseTypeEnum;
import com.kayako.api.configuration.Configuration;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.xml.sax.InputSource;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import com.kayako.api.util.Helper;
import com.kayako.api.rest.XMLHandler;

@RunWith(PowerMockRunner.class)
@PrepareForTest({RESTClient.class, Helper.class, SAXParserFactory.class})
public class RESTClientTest {
    private final static int BUFFER_SIZE_1K = 1024;
    private final static String CONTROLLER = "CONTROLLER";
    private final static String RESPONSE_MESSAGE = "responseMessage";
    private static final String CONTENT_ENCODING = "content-encoding";

    private final static String SALT_STR = "salt";
    private final static String API_KEY_STR = "apikey";
    private final static String SIGNATURE_STR = "signature";
    private final static String PARAMETER_1 = "parameter1";
    private final static String PARAMETER_2 = "parameter2";
    private final static String DATA_NAME = "dataName";
    private final static String FILE_NAME = "fileName";
    private final static String FIELD_NAME = "fieldName";
    private final static String DATA_VALUE = "dataValue";
    private final static String FILE_CONTENTS = "fileContents";
    private final static String CONTENT_VIA_SLURP = "contentViaSlurp";

    private static Map<String, String> data;
    private static Map<String, String> file;
    private static List<String> parameters;
    private static Map<String, HashMap<String,String>> files;

    //to verify result values returned from rest requests
    private final RawArrayElement rawArrayElement = new RawArrayElement();

    @Rule
    public final ErrorCollector collector = new ErrorCollector();

    private URL mockedUrl;
    private RESTClient restClient;
    private SAXParser mockedSAXParser;
    private Configuration configuration;
    private XMLHandler mockedXMLHandler;
    private InputStream mockedInputStream;
    private InputSource mockedInputSource;
    private OutputStream mockedOutputStream;
    private HttpURLConnection mockedConnection;
    private SAXParserFactory mockedSAXParserFactory;
    private InputStreamReader mockedInputStreamReader;
    private OutputStreamWriter mockedOutputStreamWriter;
    private ByteArrayInputStream mockedByteArrayInputStream;

    @BeforeClass
    public static void before(){
        data = new HashMap<>();
        data.put(DATA_NAME, DATA_VALUE);

        file = new HashMap<>();
        files = new HashMap<>();
        file.put(FILE_NAME, FILE_CONTENTS);
        files.put(FIELD_NAME, (HashMap) file);

        parameters = new ArrayList<>();
        parameters.add(PARAMETER_1);
        parameters.add(PARAMETER_2);
    }

    @Before
    public void setUp() throws Exception {
        restClient = new RESTClient();
        configuration = Configuration.init(API_URL, API_KEY, SECRET_KEY);
        Configuration.getConfiguration().setDebug(Boolean.TRUE);
        restClient.setConfig(Configuration.getConfiguration());

        mockedSAXParser = mock(SAXParser.class);
        mockedXMLHandler = mock(XMLHandler.class);
        mockedInputStream = mock(InputStream.class);
        mockedInputSource = mock(InputSource.class);
        mockedOutputStream = mock(OutputStream.class);
        mockedConnection = mock(HttpURLConnection.class);
        mockedSAXParserFactory = mock(SAXParserFactory.class);
        mockedUrl = createMockAndExpectNew(URL.class, anyString());
        expect(mockedUrl.openConnection()).andReturn(mockedConnection);
    }

    @Test
    public void shouldInitialize() {
        restClient.initialize(configuration);
        collector.checkThat(restClient.getConfig(), sameInstance(configuration));
    }

    @Test
    public void shouldSetConfig() {
        restClient.setConfig(configuration);
        collector.checkThat(restClient.getConfig(), sameInstance(configuration));
    }

    @Test
    public void shouldSetResponseTypePlain() {
        restClient.setResponseType(HttpResponseTypeEnum.PLAIN);
        collector.checkThat(restClient.getResponseType(), equalTo(HttpResponseTypeEnum.PLAIN));
    }

    @Test
    public void shouldSetResponseTypeJson() {
        restClient.setResponseType(HttpResponseTypeEnum.JSON);
        collector.checkThat(restClient.getResponseType(), equalTo(HttpResponseTypeEnum.JSON));
    }

    @Test
    public void shouldSetResponseTypeXml() {
        restClient.setResponseType(HttpResponseTypeEnum.XML);
        collector.checkThat(restClient.getResponseType(), equalTo(HttpResponseTypeEnum.XML));
    }

    @Test
    public void shouldSetResponseTypeUnknown() {
        restClient.setResponseType(HttpResponseTypeEnum.UNKNOWN);
        collector.checkThat(restClient.getResponseType(), equalTo(HttpResponseTypeEnum.UNKNOWN));
    }

    @Test
    public void shouldSetResponseTypeMixed() {
        restClient.setResponseType(HttpResponseTypeEnum.XML);
        restClient.setResponseType(HttpResponseTypeEnum.UNKNOWN);
        collector.checkThat(restClient.getResponseType(), equalTo(HttpResponseTypeEnum.UNKNOWN));
    }

    @Test
    public void receivedHttpBadRequestWhenGetThenNull() throws Exception {
        expect(mockedConnection.getResponseCode()).andReturn(HTTP_BAD_REQUEST).andReturn(HTTP_BAD_REQUEST);
        expect(mockedConnection.getResponseMessage()).andReturn(RESPONSE_MESSAGE);

        mockedConnection.setDoOutput(Boolean.FALSE);
        expectLastCall();

        mockedConnection.setRequestMethod(RESTClient.METHOD_GET);
        expectLastCall();

        replay(URL.class, mockedUrl, mockedConnection);
        collector.checkThat(restClient.get(CONTROLLER), is(nullValue()));
        verify(URL.class, mockedUrl, mockedConnection);
    }

    @Test
    public void receivedHttpOkWhenGetThenElement() throws Exception {
        arrange4GetWhenReceivedHttpOk();

        collector.checkThat(restClient.get(CONTROLLER), sameInstance(rawArrayElement));
    }

    @Test
    public void givenContentTypePLAINReceivedHttpOkWhenGetThenElement() throws Exception {
        restClient.setResponseType(HttpResponseTypeEnum.PLAIN);
        arrange4GetWhenReceivedHttpOk();

        collector.checkThat(restClient.get(CONTROLLER).getContent(), equalTo(CONTENT_VIA_SLURP));
    }

    @Test
    public void givenContentTypeJSONReceivedHttpOkWhenGetThenElement() throws Exception {
        restClient.setResponseType(HttpResponseTypeEnum.JSON);
        arrange4GetWhenReceivedHttpOk();

        collector.checkThat(restClient.get(CONTROLLER).getContent(), equalTo(CONTENT_VIA_SLURP));
    }

    @Test
    public void receivedHttpBadRequestWhenPostThenNull() throws Exception {
        expect(mockedConnection.getResponseCode()).andReturn(HTTP_BAD_REQUEST).andReturn(HTTP_BAD_REQUEST);
        expect(mockedConnection.getResponseMessage()).andReturn(RESPONSE_MESSAGE);

        mockedConnection.setDoOutput(Boolean.TRUE);
        expectLastCall();

        mockedConnection.setRequestMethod(RESTClient.METHOD_POST);
        expectLastCall();

        mockedConnection.setRequestProperty(anyString(), anyString());
        expectLastCall();

        expect(mockedConnection.getOutputStream()).andReturn(mockedOutputStream);
        mockedOutputStreamWriter = createMockAndExpectNew(OutputStreamWriter.class, mockedOutputStream);
        mockedOutputStreamWriter.write(anyString());
        expectLastCall();
        mockedOutputStreamWriter.close();
        expectLastCall();

        replay(URL.class, OutputStreamWriter.class, mockedUrl, mockedConnection, mockedOutputStreamWriter);

        collector.checkThat(restClient.post(CONTROLLER), is(nullValue()));
    }

    @Test
    public void givenFilesReceivedHttpBadReqWhenPutThenNull() throws Exception {
        arrange4PutWhenReceivedHttpBadReq();

        collector.checkThat(
                restClient.put(CONTROLLER, (ArrayList) parameters, (HashMap) data, (HashMap)files),
                is(nullValue()));
    }

    @Test
    public void givenNoFileReceivedHttpBadReqWhenPutThenNull() throws Exception {
        arrange4PutWhenReceivedHttpBadReq();

        collector.checkThat(restClient.put(CONTROLLER), is(nullValue()));
    }

    @Test
    public void receivedHttpBadReqWhenDeleteThenNull() throws Exception {
        expect(mockedConnection.getResponseCode()).andReturn(HTTP_BAD_REQUEST).andReturn(HTTP_BAD_REQUEST);
        expect(mockedConnection.getResponseMessage()).andReturn(RESPONSE_MESSAGE);

        mockedConnection.setDoOutput(Boolean.FALSE);
        expectLastCall();

        mockedConnection.setRequestMethod(RESTClient.METHOD_DELETE);
        expectLastCall();

        replay(URL.class, mockedUrl, mockedConnection);
        collector.checkThat(restClient.delete(CONTROLLER), is(nullValue()));
    }

    @Test
    public void givenFilesReceivedHttpOkWhenPostThenElement() throws Exception {
        // Arrange
        expect(mockedConnection.getResponseCode()).andReturn(HTTP_OK);
        mockedConnection.setDoOutput(Boolean.TRUE);
        expectLastCall();

        mockedConnection.setRequestMethod(RESTClient.METHOD_POST);
        expectLastCall();

        mockedConnection.setRequestProperty(anyString(), anyString());
        expectLastCall();

        expect(mockedConnection.getOutputStream()).andReturn(mockedOutputStream);
        mockedOutputStreamWriter = createMockAndExpectNew(OutputStreamWriter.class, mockedOutputStream);
        mockedOutputStreamWriter.write(anyString());
        expectLastCall();
        mockedOutputStreamWriter.close();
        expectLastCall();

        arrange4ProcessRequestWhenReceivedHttpOk();
        replay(OutputStreamWriter.class, mockedOutputStreamWriter, mockedOutputStream);

        // Act
        RawArrayElement element = restClient.post(CONTROLLER, (ArrayList)parameters, (HashMap) data, (HashMap)files);

        // Assert
        collector.checkThat(element, is(notNullValue()));
        collector.checkThat(element, sameInstance(rawArrayElement));
    }

    @Test
    public void givenPostMethodWhenGetRequestDataTestThenString() throws Exception {
        // Arrange
        data.remove(API_KEY);
        data.remove(SALT_STR);
        data.remove(SIGNATURE_STR);

        // Act
        String urlString =  restClient.getRequestDataTest(
                CONTROLLER, RESTClient.METHOD_POST, (ArrayList)parameters, (HashMap) data);

        // Assert
        collector.checkThat(data.containsKey(API_KEY_STR), is(Boolean.TRUE));
        collector.checkThat(data.containsKey(SALT_STR), is(Boolean.TRUE));
        collector.checkThat(data.containsKey(SIGNATURE_STR), is(Boolean.TRUE));
        collector.checkThat(urlString, containsString(CONTROLLER));
        collector.checkThat(urlString, containsString(PARAMETER_1));
        collector.checkThat(urlString, containsString(PARAMETER_2));
    }

    @Test
    public void givenGetMethodWhenGetRequestDataTestThenString() throws Exception {
        // Act
        String urlString =  restClient.getRequestDataTest(
                CONTROLLER, RESTClient.METHOD_GET, (ArrayList)parameters, (HashMap) data);

        // Assert
        collector.checkThat(urlString, containsString(CONTROLLER));
        collector.checkThat(urlString, containsString(PARAMETER_1));
        collector.checkThat(urlString, containsString(PARAMETER_2));
        collector.checkThat(urlString, containsString(SALT_STR));
        collector.checkThat(urlString, containsString(API_KEY_STR));
        collector.checkThat(urlString, containsString(SIGNATURE_STR));
    }

    private void arrange4ProcessRequestWhenReceivedHttpOk() throws Exception {
        expect(mockedConnection.getContent()).andReturn(mockedInputStream);
        expect(mockedConnection.getContentEncoding()).andReturn(CONTENT_ENCODING);

        mockedInputStreamReader = createMockAndExpectNew(InputStreamReader.class, mockedInputStream, "UTF-8");

        expectNew(InputSource.class, mockedInputStreamReader).andReturn(mockedInputSource);
        mockedInputSource.setEncoding(anyString());
        expectLastCall();

        expectNew(XMLHandler.class).andReturn(mockedXMLHandler);

        mockStatic(SAXParserFactory.class);
        expect(SAXParserFactory.newInstance()).andReturn(mockedSAXParserFactory);

        mockStatic(Helper.class);
        expect(Helper.slurp(mockedInputStream, BUFFER_SIZE_1K)).andReturn(CONTENT_VIA_SLURP);

        expect(mockedSAXParserFactory.newSAXParser()).andReturn(mockedSAXParser);

        mockedByteArrayInputStream = createMockAndExpectNew(ByteArrayInputStream.class, (byte[])anyObject());
        expectNew(InputSource.class, mockedByteArrayInputStream).andReturn(mockedInputSource);

        mockedSAXParser.parse(mockedInputSource, mockedXMLHandler);
        expectLastCall();

        mockedInputStream.close();
        expectLastCall();

        mockedConnection.disconnect();
        expectLastCall();

        expect(mockedXMLHandler.getRawArrayElement()).andReturn(rawArrayElement);

        replay(URL.class, InputStream.class, InputSource.class, XMLHandler.class, SAXParserFactory.class,
                SAXParser.class, Helper.class, InputStreamReader.class, ByteArrayInputStream.class,
                mockedUrl, mockedConnection, mockedInputSource, mockedXMLHandler,
                mockedSAXParser, mockedInputStreamReader, mockedSAXParserFactory);
    }

    private void arrange4PutWhenReceivedHttpBadReq() throws Exception {
        expect(mockedConnection.getResponseCode()).andReturn(HTTP_BAD_REQUEST).andReturn(HTTP_BAD_REQUEST);
        expect(mockedConnection.getResponseMessage()).andReturn(RESPONSE_MESSAGE);

        mockedConnection.setDoOutput(Boolean.TRUE);
        expectLastCall();

        mockedConnection.setRequestMethod(RESTClient.METHOD_PUT);
        expectLastCall();

        mockedConnection.setRequestProperty(anyString(), anyString());
        expectLastCall();

        expect(mockedConnection.getOutputStream()).andReturn(mockedOutputStream);
        mockedOutputStreamWriter = createMockAndExpectNew(OutputStreamWriter.class, mockedOutputStream);
        mockedOutputStreamWriter.write(anyString());
        expectLastCall();
        mockedOutputStreamWriter.close();
        expectLastCall();

        replay(URL.class, OutputStreamWriter.class, mockedUrl, mockedConnection, mockedOutputStreamWriter);
    }

    private void arrange4GetWhenReceivedHttpOk() throws Exception {
        mockedConnection.setDoOutput(Boolean.FALSE);
        expectLastCall();

        mockedConnection.setRequestMethod(RESTClient.METHOD_GET);
        expectLastCall();

        expect(mockedConnection.getResponseCode()).andReturn(HTTP_OK);

        arrange4ProcessRequestWhenReceivedHttpOk();
    }
}
