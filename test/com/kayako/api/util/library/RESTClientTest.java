package com.kayako.api.util.library;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import com.kayako.api.configuration.Configuration;
import com.kayako.api.enums.HttpResponseTypeEnum;
import com.kayako.api.rest.RawArrayElement;

public class RESTClientTest {

    private final static String BASE_URL = "BASE_URL";
    private final static String API_KEY = "API_KEY";
    private final static String SECRET_KEY = "SECRET_KEY";
    private final static String CONTROLLER = "CONTROLLER";
    
	@Rule
	public final ErrorCollector collector = new ErrorCollector();

	private RESTClient restClient;
	private Configuration configuration;

	@Before
	public void setUp(){
		restClient = new RESTClient();
		configuration = Configuration.init(BASE_URL, API_KEY, SECRET_KEY, Boolean.FALSE);
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
	public void shouldSetResponseType() {
		restClient.setResponseType(HttpResponseTypeEnum.PLAIN);
		collector.checkThat(restClient.getResponseType(), equalTo(HttpResponseTypeEnum.PLAIN));

		restClient.setResponseType(HttpResponseTypeEnum.JSON);
		collector.checkThat(restClient.getResponseType(), equalTo(HttpResponseTypeEnum.JSON));

		restClient.setResponseType(HttpResponseTypeEnum.XML);
		collector.checkThat(restClient.getResponseType(), equalTo(HttpResponseTypeEnum.XML));

		restClient.setResponseType(HttpResponseTypeEnum.UNKNOWN);
		collector.checkThat(restClient.getResponseType(), equalTo(HttpResponseTypeEnum.UNKNOWN));
	}

    @Test
    public void shouldGet() {
	    RESTClient mockedRestClient = mock(RESTClient.class);
        final RawArrayElement rawArrayElement = new RawArrayElement();
        when(mockedRestClient.get(CONTROLLER)).thenReturn(rawArrayElement);

        collector.checkThat(mockedRestClient.get(CONTROLLER), sameInstance(rawArrayElement));
    }

    @Test
    public void shouldPost() {
        RESTClient mockedRestClient = mock(RESTClient.class);
        final RawArrayElement rawArrayElement = new RawArrayElement();
        when(mockedRestClient.post(CONTROLLER)).thenReturn(rawArrayElement);

        collector.checkThat(mockedRestClient.post(CONTROLLER), sameInstance(rawArrayElement));
    }

    @Test
    public void shouldPut() {
        RESTClient mockedRestClient = mock(RESTClient.class);
        final RawArrayElement rawArrayElement = new RawArrayElement();
        when(mockedRestClient.put(CONTROLLER)).thenReturn(rawArrayElement);

        collector.checkThat(mockedRestClient.put(CONTROLLER), sameInstance(rawArrayElement));
    }

    @Test
    public void shouldDelete() {
        RESTClient mockedRestClient = mock(RESTClient.class);
        final RawArrayElement rawArrayElement = new RawArrayElement();
        when(mockedRestClient.put(CONTROLLER)).thenReturn(rawArrayElement);

        collector.checkThat(mockedRestClient.put(CONTROLLER), sameInstance(rawArrayElement));
    }
}