package com.kayako.api.util.library;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.powermock.api.easymock.PowerMock.replay;
import static org.powermock.api.easymock.PowerMock.verify;
import static org.powermock.api.easymock.PowerMock.expectPrivate;
import static org.powermock.api.easymock.PowerMock.createPartialMock;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import com.kayako.api.configuration.Configuration;
import com.kayako.api.enums.HttpResponseTypeEnum;
import com.kayako.api.rest.RawArrayElement;
import java.util.ArrayList;
import java.util.HashMap;

public class RESTClientTest {
    private final static String BASE_URL = "BASE_URL";
    private final static String API_KEY = "API_KEY";
    private final static String SECRET_KEY = "SECRET_KEY";
    private final static String CONTROLLER = "CONTROLLER";

    private final static String REST_GET = "GET";
    private final static String REST_PUT = "PUT";
    private final static String REST_POST = "POST";
    private final static String REST_DELETE = "DELETE";

    //protected method name which is needed to mock
    private final static String PROCESS_REQUEST = "processRequest";
    //protected method call parameters
    private final static ArrayList<String> PARAMETERS = new ArrayList<>();
    private final static HashMap<String, String> DATA = new HashMap<>();
    private final static HashMap<String, HashMap<String, String>> FILES = new HashMap<>();

    //to verify result values returned from rest requests
    private final RawArrayElement rawArrayElement = new RawArrayElement();

    @Rule
    public final ErrorCollector collector = new ErrorCollector();

    private RESTClient restClient;
    private RESTClient restClientMocked;
    private Configuration configuration;

    @Before
    public void setUp() {
        restClient = new RESTClient();
        configuration = Configuration.init(BASE_URL, API_KEY, SECRET_KEY, Boolean.FALSE);
        restClientMocked = createPartialMock(RESTClient.class, PROCESS_REQUEST);
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
    public void shouldGet() throws Exception {
        expectPrivate(restClientMocked,
                PROCESS_REQUEST, CONTROLLER, REST_GET, PARAMETERS, DATA, FILES).andReturn(rawArrayElement);

        replay(restClientMocked);
        collector.checkThat(restClientMocked.get(CONTROLLER), sameInstance(rawArrayElement));
        verify(restClientMocked);
    }

    @Test
    public void shouldPost() throws Exception {
        expectPrivate(restClientMocked,
                PROCESS_REQUEST, CONTROLLER, REST_POST, PARAMETERS, DATA, FILES).andReturn(rawArrayElement);
        replay(restClientMocked);
        collector.checkThat(restClientMocked.post(CONTROLLER), sameInstance(rawArrayElement));
        verify(restClientMocked);
    }

    @Test
    public void shouldPut() throws Exception {
        expectPrivate(restClientMocked,
                PROCESS_REQUEST, CONTROLLER, REST_PUT, PARAMETERS, DATA, FILES).andReturn(rawArrayElement);
        replay(restClientMocked);
        collector.checkThat(restClientMocked.put(CONTROLLER), sameInstance(rawArrayElement));
        verify(restClientMocked);
    }

    @Test
    public void shouldDelete() throws Exception {
        expectPrivate(restClientMocked,
                PROCESS_REQUEST, CONTROLLER, REST_DELETE, PARAMETERS, DATA, FILES).andReturn(rawArrayElement);
        replay(restClientMocked);
        collector.checkThat(restClientMocked.delete(CONTROLLER), sameInstance(rawArrayElement));
        verify(restClientMocked);
    }
}
