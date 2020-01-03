package cx.sap.panda.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.Objects;

/**
 * When 2 services config the same path and in `replace.route.filter.map`, the targetService will replace the
 * sourceService when calling sourceService API
 * */
@Component
public class ReplaceRouteFilter extends ZuulFilter
{
	@Autowired
	ZuulProperties properties;


	@Value("#{${replace.route.filter.map}}")
	Map<String, String> serviceReplacementMap;

	@Override
	public String filterType()
	{
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder()
	{
		return 10;
	}

	@Override
	public boolean shouldFilter()
	{
		String sourceService = (String) RequestContext.getCurrentContext().get("serviceId");
		if(!CollectionUtils.isEmpty(serviceReplacementMap)&& serviceReplacementMap.keySet().contains(sourceService)&& Objects.nonNull(serviceReplacementMap.get(sourceService))){
			String targetService = serviceReplacementMap.get(sourceService);
			if(properties.getRoutes().get(sourceService).getPath().equals(properties.getRoutes().get(targetService).getPath())){
				return true;
			}
		}
		return false;
	}

	@Override
	public Object run() throws ZuulException
	{
		String source = (String) RequestContext.getCurrentContext().get("serviceId");
		RequestContext.getCurrentContext().set("serviceId", serviceReplacementMap.get(source));
		return null;
	}
}
