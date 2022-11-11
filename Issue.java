package Pattern.HomeTask;

/**
 * - Порядковый номер клиента
 * - Дата и время звонка в ISO формате
 * - Фамилия и Имя клиента
 * - Телефон клиента
 * - Текст жалобы
 */

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Issue {
    private int id;

    private final String time;

    private final String fullName;

    private final String phoneNumber;

    private final String text;


    public Issue(int id, String time, String fullName, String phoneNumber, String text) {
        this.id = id;
        this.time = time;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.text = text;
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
