package ${package}.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class ApplicationController
{
	@RequestMapping( "/test" )
	String test()
	{
		return "This is a test";
	}

	public static void main( String[] args ) throws Exception
	{
		SpringApplication.run( ApplicationController.class, args );
	}
}