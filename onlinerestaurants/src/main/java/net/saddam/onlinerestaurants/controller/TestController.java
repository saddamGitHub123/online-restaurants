package net.saddam.onlinerestaurants.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import net.saddam.restaurantsbackend.dao.TestDAO;
import net.saddam.restaurantsbackend.dto.Test;
import net.saddam.restaurantsbackend.dto.User;
import net.saddam.restaurantsbackend.model.ImageRequest;
import net.saddam.restaurantsbackend.model.Response;

/**
 * 
 * @author saddam
 *
 */
//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@Controller
@EnableWebMvc
@RequestMapping("/test")
public class TestController {
	
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	
	@Autowired
	public TestDAO testDAO;
	
	 @Autowired
	    private JavaMailSender mailSender;
	 
	 
	 
	 
 //Sending OTP using email validation
	 
	 @RequestMapping(value = "/send/email/otp", method = RequestMethod.POST)
		public @ResponseBody User mailSenderUsedSMTP(HttpServletRequest request,@RequestBody User user) throws UnsupportedEncodingException {
		 
		 logger.info("email verification Entered mailSenderUsedSMTP() in TestController  - Post email,userid and shopid");
		
		 //Set your account user name and password
		 final String username = "sksaddamhosan2015@gmail.com";
		 final String password = "9800536118";
         
		 
		 //set 
		    Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");

			Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username,password);
					}
				});
			try {
				
				//create OTP for texting mesg
				 String sendingOTP = String.valueOf(sendOTP(6));
			        System.out.println(sendingOTP);
			        
			        //automatic genarate OTP set as password
			        user.setPassword(sendingOTP);
			        
			        //save otp as a password into database method
			        testDAO.saveOTPasPaswd(user);
			        
			        
			      //Set all input 
			    	String  recipientAddress = user.getEmail();
					// String subject = "One Time Password";
					 String otp = sendingOTP;

				Message message = new MimeMessage(session);
				///message.setFrom(new InternetAddress("no-reply@touchcor.com"));
				InternetAddress me = new InternetAddress("from-email@gmail.com");
		        me.setPersonal("no-reply");
		        message.setFrom(me);
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(recipientAddress));
				message.setSubject("One Time Password");
				message.setText("Your OTP is :" + otp +
						"\n\n\n\n Disclaimer:- This is a system generated email. Please do not reply to this email." +
						"\n\n\n\r\n" + 
						"*** This message is intended only for the person or entity, addressed for security purpose."
						+ "\nIf you have received this message in error, delete this message from your system *** ");

				Transport.send(message);

				System.out.println("Done");

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}

		   return user;
		 
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 //Sending OTP using email validation
	 
	 @RequestMapping(value = "/send/email", method = RequestMethod.POST)
		public @ResponseBody User mailSender(HttpServletRequest request, @RequestBody User user) {

		    /*Properties props = new Properties();
		   
		    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");*/
		   
		 //create OTP for texting mesg
		 String sendingOTP =String.valueOf(sendOTP(4));
	        System.out.println(sendingOTP);
	        
	        //automatic genarate OTP set as password
	        user.setPassword(sendingOTP);
	        
	        //save otp as a password into database method
	        testDAO.saveOTPasPaswd(user);
	        
	        
		    //Set all input 
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

                   
             return user;
		 
	 }


	 
	 
	 
	 
	 
	 
	//Sending email using smtp
	 
 //Sending OTP using email validation
	 
	 @RequestMapping(value = "/send/email/smtp", method = RequestMethod.GET)
		public String  mailSenderSMTP(HttpServletRequest request) {
		 
		 final String username = "sksddmhosan@gmail.com";
			final String password = "9800536118Sa";

			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");

			Session session = Session.getInstance(props,
			  new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			  });
			
			try {

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("no-replay@gmail.com"));
				message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("sksaddamhosan2015@gmail.com"));
				message.setSubject("Testing Subject");
				message.setText("Dear Mail Crawler,"
					+ "\n\n No spam to my email, please!");

				Transport.send(message);

				System.out.println("Done");

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}

		   return "result";
		 
	 }
	 
	 

	
	/*@RequestMapping(value = "/forgotten/password/phoneNumber/{phnNumber}", method = RequestMethod.GET)
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
		       // Message message = messageFactory.create(params);
		       // System.out.println(message.getSid());
		        
		       // return 
		    
		}   catch (TwilioRestException e) {
	        System.out.println(e.getErrorMessage());
	    }

	}*/
	
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
	
	
	
	
	 // post data from one controller to another controller
	 
	 @RequestMapping(value = "/send/datato/ctrl", method = RequestMethod.POST)
		public @ResponseBody User dataToAnotherCtrl(HttpServletRequest request,@RequestBody User user){
		 
		 logger.info("send data to one controller to another dataToAnotherCtrl() in TestController");
		 
		 
		 //Setting another controller url
		 String url = "http://192.168.2.94:8080/onlinerestaurants/test/send/email/otp";
		 
		//set your headers
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        
	      //set your entity to send
	        HttpEntity entity = new HttpEntity(user, headers);

	        RestTemplate restTemplate = new RestTemplate();
	        
	     // send it!
	        ResponseEntity<User> response = restTemplate.exchange(url, HttpMethod.POST, entity,User.class);
	        
	        System.out.println(response);
	        
	        logger.info("Returning from dataToAnotherCtrl()");
	         

		 
		   return user;
		 
	 }
	 
	
	
	
	
	
	
	// Fetching data from multiple table using hibernate
	@RequestMapping(value = "/fetching/data/multiple/table", method = RequestMethod.GET)
	public String  fetchingData(HttpServletRequest request) {
		
		  //String shopid = "";
		 // String userid = "";
		  
		  
		
		
		return "result";
	}
	
	
	//for save image to database using blob and converting into java post and get
	
	
	@RequestMapping(value = "/image/save/blob", method = RequestMethod.POST)
	public @ResponseBody Response  imageSave(HttpServletRequest request, @RequestBody Test test ) {
		
		  //String shopid = "";
		 // String userid = "";
		System.out.println("Entering imageSave method");
		Response response = new Response();
		
		ImageRequest imageRequest = new ImageRequest();
		if(testDAO.saveImageByByte(test)) {
			
			
			response.setStatus_code("200");
			response.setStatus_message("Succssufully upload");
			return response;
			
		}
		else {
			response.setStatus_code("400");
			response.setStatus_message("Succssufully upload");
			return  response; 
		}
		  
		  //System.out.println("image to blob file is done");
		  
	
         
		
		  //System.out.println("the value is :" +testValue);
		
		
	}
	
	
	
	@RequestMapping(value = "/image/get", method = RequestMethod.POST)
	public @ResponseBody Response  imageGet(HttpServletRequest request, @RequestBody Test test ) {
		
		  //String shopid = "";
		 // String userid = "";
		System.out.println("Entering imageSave method");
		String id = test.getId();
		Response response = new Response();
		
		//ImageRequest imageRequest = new ImageRequest();
		List<Test> tesValue = testDAO.getImageByByte(id) ;
			
			
			response.setStatus_code("200");
			response.setStatus_message("Succssufully upload");
			//return response;
			
	
		
			response.setStatus_code("400");
			response.setStatus_message("Succssufully upload");
			return  response; 
	
		  
		  //System.out.println("image to blob file is done");
		  
	
         
		
		  //System.out.println("the value is :" +testValue);
		
		
	}
	
	
	
	
}	
	
	
