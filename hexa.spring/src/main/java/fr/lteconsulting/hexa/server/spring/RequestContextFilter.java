package fr.lteconsulting.hexa.server.spring;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestContextFilter implements Filter
{
	@Override
	public void init( FilterConfig arg0 ) throws ServletException
	{
	}

	@Override
	public void destroy()
	{
	}

	@Override
	public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain ) throws IOException, ServletException
	{
		HexaSpring.hexa().onBeginServletRequestProcessing( (HttpServletRequest) request, (HttpServletResponse) response );

		chain.doFilter( request, response );

		HexaSpring.hexa().onEndServletRequestProcessing();
	}
}
