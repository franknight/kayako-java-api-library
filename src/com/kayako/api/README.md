# Notes
The documentation in the Wiki is incomplete.

In order to create a ticket, some parameters are required:

```
import com.kayako.api.configuration.Configuration;
import com.kayako.api.enums.CreationTypeEnum;
import com.kayako.api.exception.KayakoException;
import com.kayako.api.ticket.Ticket;
import com.kayako.api.user.User;
import com.kayako.api.department.Department;

public class Main {
    private static final String API_URL = "Use your own URL";

    private static final String API_KEY = "Use your own key";

    private static final String SECRET_KEY = "Use your own secret";

    public static void main(String[] args) throws KayakoException {
        // Initialize
        Configuration config = Configuration.init(API_URL, API_KEY, SECRET_KEY, true);

        // Set configuration
        Configuration.setConfiguration(config);

        // Provide a department
        Department dept = Department.get(3);
        
        // Provide a user
        User user = User.get(6);
        
        Ticket ticket = new Ticket(dept, user, "Content", "Subject");
        ticket.setCreationType(CreationTypeEnum.DEFAULT);
        ticket.setTicketPriorityId(1);
        ticket.setTemplateGroup(1);
        ticket.setTicketTypeId(1);
        ticket.setTicketStatusId(1);
        ticket.setOwnerStaffId(1);
        ticket.create();

        System.out.println(ticket);

    }
}
```
