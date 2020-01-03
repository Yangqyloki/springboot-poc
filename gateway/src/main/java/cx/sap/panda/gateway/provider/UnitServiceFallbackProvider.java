package cx.sap.panda.gateway.provider;

import com.google.gson.Gson;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class UnitServiceFallbackProvider implements FallbackProvider

{
	@Override
	public String getRoute()
	{
		return "unit-service";
	}

	@Override
	public ClientHttpResponse fallbackResponse(final String route, final Throwable cause)
	{
		ClientHttpResponse response = new ClientHttpResponse()
		{
			@Override
			public InputStream getBody() throws IOException
			{
				Map<String,String> result = new LinkedHashMap<>();
				result.put("timestamp",new Date().toString());
				result.put("status","400");
				result.put("message","Zuul fallback from cx.sap.panda.gateway.provider.UnitServiceFallbackProvider");
				result.put("reason","Unit service down");
				return  new ByteArrayInputStream(new Gson().toJson(result).getBytes());

			}

			@Override
			public HttpHeaders getHeaders()
			{
				HttpHeaders headers = new HttpHeaders();
				headers.set("Content-Type", "application/json");
				return headers;
			}

			@Override
			public HttpStatus getStatusCode() throws IOException
			{
				return HttpStatus.OK;
			}

			@Override
			public int getRawStatusCode() throws IOException
			{
				return HttpStatus.OK.value();
			}

			@Override
			public String getStatusText() throws IOException
			{
				return HttpStatus.OK.getReasonPhrase();
			}

			@Override
			public void close()
			{

			}
		};

		return response;
	}
}
