package kostomarov.courseproject.email;

import lombok.Data;

@Data
public class EmailDetail {
    private String toEmail;
    private String subject;
    private String text;
    private String typeOfFile;
}

