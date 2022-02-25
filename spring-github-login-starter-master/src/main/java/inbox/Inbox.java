package inbox;


import java.nio.file.Path;
import java.util.Arrays;

import javax.annotation.PostConstruct;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.uuid.Uuids;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import inbox.emaillist.EmailListItem;
import inbox.emaillist.EmailListItemKey;
import inbox.emaillist.EmailListItemRepository;
import inbox.folder.Folder;
import inbox.folder.FolderRepository;

@SpringBootApplication
@RestController
public class Inbox {

	@Autowired
	 EmailListItemRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(Inbox.class, args);

	
	}

	@RequestMapping("/user")
	public String user(@AuthenticationPrincipal OAuth2User principal) {
		System.out.println(principal);
		return principal.getAttribute("name");
	}
	
	@Bean
	public CqlSessionBuilderCustomizer sessionBuilderCustomizer(DataStaxAstraProperties astraProperties) {
		Path bundle = astraProperties.getSecureConnectBundle().toPath();
		return builder -> builder.withCloudSecureConnectBundle(bundle);
	}

	@PostConstruct
	public void init(){
		
		for(int i =0; i<10; i++){
			EmailListItemKey key = new EmailListItemKey();
			key.setId("Agam Sidhu");
			key.setLabel("Inbox");
			key.setTimeUUID(Uuids.timeBased());

			EmailListItem item = new EmailListItem();
			item.setKey(key);
			item.setTo(Arrays.asList("Agam Sidhu"));
			item.setSubject("Subject " + i);
			item.setUnread(true);

			repo.save(item);
		}

	}
	

}
