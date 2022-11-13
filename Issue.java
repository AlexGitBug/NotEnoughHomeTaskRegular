package Pattern.HomeTask;

/**
 * - Порядковый номер клиента
 * - Дата и время звонка в ISO формате
 * - Фамилия и Имя клиента
 * - Телефон клиента
 * - Текст жалобы
 */

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Issue {
    private int id;

    private  String time;

    private String fullName;

    private String phoneNumber;

    private String text;

    Path pathWriter = Path.of("resources", "log-file-Solutions.csv");
    Path pathReader = Path.of("resources", "log-file-Complaint.csv");

    public Issue(int id, String time, String fullName, String phoneNumber, String text) {
        this.id = id;
        this.time = time;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.text = text;
    }


    public Issue() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getText() {
        return text;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", time='" + time + '\'' +
                ", fullName='" + fullName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
