package Pattern.HomeTask;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public class Yrunner {

    public static void main(String[] args) throws IOException {


        Queue<Issue> issue = new LinkedBlockingDeque<>();

        List<Issue> list = new ArrayList<>();

        Path pathReader = Path.of("resources", "log-file-Complaint.csv");
        Path pathWriter = Path.of("resources", "log-file-Solutions.csv");


        Thread producer = new Thread(new IssueProducer(issue, list));
        Thread consumer = new Thread(new IssueConsumer(issue, list));

        producer.start();
        consumer.start();
    }
}