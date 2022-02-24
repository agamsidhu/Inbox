package inbox.folder;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;

@Table(value = "folders_by_user")
public class Folder {

    @PrimaryKeyColumn(name = "user_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String userId;

    @PrimaryKeyColumn(name = "label", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    private String label;

    @CassandraType(type = Name.TEXT)
    private String color;

    public Folder(String userName, String label, String color) {
        this.userId = userName;
        this.label = label;
        this.color = color;
    }
    public Folder(){}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    } 

    
}
