package com.cpt.payments.controller;

  
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cpt.payments.pojo.AddRequest;
import com.cpt.payments.pojo.AddResponse;
import com.cpt.payments.service.TestService;

 

@RestController
@RequestMapping("/controller")
public class TestController {
	 
    private static final Logger logger = LogManager.getLogger(TestController.class.getName());

	
	@GetMapping("/hello")
	public String sayHello() {
		return "HELLO GUYZ.."; 
	}
	 
	
	@GetMapping("/add")
	public int add(@RequestParam(value = "num1") int val1, @RequestParam(value = "num2") int val2) {
		
		 	logger.debug("This is a debug message.");
	        logger.info("This is an info message.");
	        logger.warn("This is a warning message.");
	        logger.error("This is an error message.");
	        logger.trace("This is a trace message.");
		
		System.out.println("val1 : "+val1 + " val2 : "+val2);
		int res = val1 + val2;
		
		System.out.println("res:"+res);
		return res;
	}
	
	
	@PostMapping("/processJSON")
	public AddResponse processJSON(@RequestBody AddRequest request) {
		System.out.println("Calling processJson : "+ request);
		int res = request.getNum1() + request.getNum2();
		
		AddResponse response = new AddResponse();
		response.setResValue(res);
		
		System.out.println("response : "+response);
		return response;
	}
	
	
	@PostMapping("/validateAndProcess")
	public AddResponse validateAndProcess(
			@RequestHeader ("signature") String clientSignature,
			@RequestBody AddRequest request) {
		
		System.out.println("Calling processJson : "+ request + "|Signature : "+ clientSignature);

		TestService service = new TestService();
		service.validateAndProcess(request,clientSignature);
		 
		int res = service.validateAndProcess(request,clientSignature);
		
		AddResponse response = new AddResponse();
		response.setResValue(res);
		
		System.out.println("response : "+response);
		return response;
	}
}
