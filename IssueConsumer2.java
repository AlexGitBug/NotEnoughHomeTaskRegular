package Pattern.HomeTask;

import java.util.List;
import java.util.Queue;

public class IssueConsumer2 extends IssueConsumer{

    private String name;

    public IssueConsumer2(Queue<Issue> queue, List<Issue> list, String name) {
        super(queue, list, name);
        this.name = name;
    }
}
