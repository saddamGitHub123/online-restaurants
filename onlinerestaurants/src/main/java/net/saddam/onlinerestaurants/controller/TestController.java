package net.saddam.onlinerestaurants.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;

import net.saddam.restaurantsbackend.dao.TestDAO;
import net.saddam.restaurantsbackend.dto.User;

/**
 * 
 * @author saddam
 *
 */

@Controller
@EnableWebMvc
@RequestMapping("/test")
public class TestController {
	
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	
	@Autowired
	public TestDAO testDAO;
	
	 @Autowired
	    private JavaMailSender mailSender;
	 
	 
	 //Sending OTP using email vadi
	 
	 @RequestMapping(value = "/send/email", method = RequestMethod.POST)
		public String mailSender(HttpServletRequest request, @RequestBody User user) {

		    /*Properties props = new Properties();
		   
		    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");*/
		   
		 //create OTP for texting mesg
		 String sendingOTP =String.valueOf(sendOTP(4));
	        System.out.println(sendingOTP);
		    
	        
		    //Set all input 
	        
	        /*if(recipientAddress.equals("sksaddamhosan2015@gmail.com")) {
	        	System.out.println("both are equal");
	        }*/
	        	
		    	//String  recipientAddress = "sksaddamhosan2015@gmail.com";
		    	String  recipientAddress = user.getEmail();
				 String subject = "One Time Password";
				 String message = sendingOTP;
				 
				 
				// prints debug info
		           System.out.println("To: " + recipientAddress+" Subject: " + subject +" Message: " + message);
		    	
				// creates a simple e-mail object
             SimpleMailMessage email = new SimpleMailMessage();
                email.setTo(recipientAddress);
                   email.setSubject(subject);
                   email.setText(message);
                   
                   
                   
                // sends the e-mail
                   mailSender.send(email);
                   
                   String result = "OTP Send";
                   
             return result;

		     
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		// String recipient1 ="sksaddamhosan2015@gmail.com";
		// String  subjectOTP = "OTP";
		// String messageValue = "3425";
		 
		// takes input from e-mail form
		 /*String  recipientAddress = request.getParameter("sksaddamhosan2015@gmail.com");
		 String subject = request.getParameter("OTP");
		 String message = request.getParameter("3456");*/
		/* Properties props = new Properties();
		 props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		 
		 String  recipientAddress = "sksaddamhosan2015@gmail.com";
		 String subject = "OTP";
		 String message = "3456";
		 
		// prints debug info
           System.out.println("To: " + recipientAddress);
           System.out.println("Subject: " + subject);
           System.out.println("Message: " + message);
           
           
        // creates a simple e-mail object
               SimpleMailMessage email = new SimpleMailMessage();
                email.setTo(recipientAddress);
                email.setSubject(subject);
                email.setText(message);
                
                
             // sends the e-mail
             mailSender.send(email);
             
             // forwards to the view named "Result"
              return "Result";*/
		 
		/*// takes input from e-mail form
		 
          String recipientAddress = request.getParameter(recipient);
	         String subject = request.getParameter("subject");
	         String message = request.getParameter("message");
	          
	         // prints debug info
	         System.out.println("To: " + recipientAddress);
	         System.out.println("Subject: " + subject);
	         System.out.println("Message: " + message);
	          
	         // creates a simple e-mail object
	         SimpleMailMessage email = new SimpleMailMessage();
	         email.setTo(recipientAddress);
	         email.setSubject(subject);
	         email.setText(message);
	          
	         // sends the e-mail
	         mailSender.send(email);
	          
	         // forwards to the view named "Result"
	         return "Result";*/
		 
		 
	 }

	 
	
	
	@RequestMapping(value = "/forgotten/password/phoneNumber/{phnNumber}", method = RequestMethod.GET)
	public @ResponseBody void forgottenPassword(HttpServletRequest request,@PathVariable("phnNumber")String phnNumber) {
		
		

		try {
			logger.info("Entered forgottenPassword()  - Send password in your mobile");
			
			// Find your Account Sid and Token at twilio.com/user/account
		   // public static final String ACCOUNT_SID = "ACb984ebe5fa98b08b29f21139b7edd152";
		   // public static final String AUTH_TOKEN = "50420a58d72b94576f8a9d854d07ff55";
		    //public static final String TWILIO_NUMBER = "+19295002280";
			String phn = "+91"+phnNumber;
		    System.out.println(phn);
		    String ACCOUNT_SID = "ACb984ebe5fa98b08b29f21139b7edd152";
		    String AUTH_TOKEN = "50420a58d72b94576f8a9d854d07ff55";
		    String TWILIO_NUMBER = "+19295002280";
			
		        TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
		 
		        // Build a filter for the MessageList
		        List<NameValuePair> params = new ArrayList<NameValuePair>();
		        String s =String.valueOf(sendOTP(4));
		        System.out.println(s);
		        params.add(new BasicNameValuePair("Body",s ));
		        params.add(new BasicNameValuePair("To",phn)); //Add real number here
		        params.add(new BasicNameValuePair("From", TWILIO_NUMBER));

		        MessageFactory messageFactory = client.getAccount().getMessageFactory();
		        Message message = messageFactory.create(params);
		        System.out.println(message.getSid());
		        
		       // return 
		    
		}   catch (TwilioRestException e) {
	        System.out.println(e.getErrorMessage());
	    }

	}
	
	//Generate automatic  OTP
	static char[] sendOTP(int length){
		
		String  number= "0123456789";
		Random r = new Random();
		char[] otp = new char[length];

		 for(int i=0 ; i<length ;i++){

		    otp[i] = number.charAt(r.nextInt((number.length())));
		 } 
		 return otp;
		 }
	
}	
	
	
