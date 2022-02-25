package inbox.emaillist;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailListItemRepository extends CassandraRepository<EmailListItem, EmailListItemKey>{

   // List<EmailListItem> findAllByuserId(EmailListItemKey id);

   List<EmailListItem> findAllByKeyIdAndKey_Label (String id, String label);
}
