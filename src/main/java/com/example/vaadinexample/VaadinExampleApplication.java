package com.example.vaadinexample;

import com.example.vaadinexample.model.Contact;
import com.example.vaadinexample.model.Country;
import com.example.vaadinexample.model.User;
import com.example.vaadinexample.repository.ContactRepository;
import com.example.vaadinexample.repository.CountryRepository;
import com.example.vaadinexample.repository.UserRepository;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.auth.AuthMethod;
import com.nexmo.client.auth.TokenAuthMethod;
import com.nexmo.client.sms.SmsSubmissionResult;
import com.nexmo.client.sms.messages.TextMessage;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

@SpringBootApplication
@EnableWebSecurity
@EnableScheduling
//@Log4j
@Slf4j
public class VaadinExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(VaadinExampleApplication.class, args);
	}

	@Autowired
	private ContactRepository contactRepository;

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;



	@Bean
	public CommandLineRunner loadData() {
		return (args -> {
			Country country = countryRepository.save(new Country(null,"Singapore", "+65", "IN"));

			contactRepository.save(new Contact(null, "Ganeshrajhan", "Rajarethinam", "krganeshrajhan@gmail.com", 98254760, country, LocalDate.now()));
			User user = new User();
			user.setUsername("ganesh");
			String encodedPassword = passwordEncoder.encode("1234");
			user.setPassword(encodedPassword);
			userRepository.save(user);

		});
	}

	public void sendMessage(Contact contact)  {

		AuthMethod auth = new TokenAuthMethod("f0293c3f", "365c9d51a56a8209");
		NexmoClient client = new NexmoClient(auth);
		SmsSubmissionResult[] responses = new SmsSubmissionResult[0];
		String number = contact.getDialCountry().getDialCode() + contact.getPhoneNumber();
		try {
			responses = client.getSmsClient().submitMessage(new TextMessage(
                    "+6583327612",
//                    "+6598254760",
					number,
                    "Many more happy returns"));
		} catch (IOException e) {
			log.error(e.getMessage());
		} catch (NexmoClientException e) {
			log.error(e.getMessage());
		}
		for (SmsSubmissionResult response : responses) {
			log.info(response.toString());
		}
	}

	@Scheduled(cron = "0 00 9 * * ?")
	public void perform() throws Exception
	{
		log.info("Scheduler");
		LocalDate date = LocalDate.now();
		List<Contact> c1 = contactRepository.findByDob( date.getMonth().getValue(), date.getDayOfMonth());
		c1.forEach(contact -> sendMessage(contact));

	}
}
