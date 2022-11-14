package Pattern.HomeTask;

import java.nio.file.Path;
import java.util.List;
import java.util.Queue;

/**
 * 3) IssueConsumer impl Runnable {
 * Queue<Issue> queue
 *
 * это диспетчер, он как только освободится берёт из очереди последнюю проблему и обрабатывает
 *
 * }
 */

public class IssueConsumer implements Runnable {
    private Queue<Issue> queue;
    private List<Issue> list;

    private String name;
    Path pathWriter = Path.of("resources", "log-file-Solutions.csv");
    Path pathReader = Path.of("resources", "log-file-Complaint.csv");

    public IssueConsumer(Queue<Issue> queue, List<Issue> list, String name) {
        this.queue = queue;
        this.list = list;
        this.name = name;
    }

    @Override
    public void run() {
            synchronized (queue) {
                while (true) {
                if (!queue.isEmpty() ) {
                    if (IssuesTxtUtil.read(pathReader).isEmpty()) {
                            break;
                        }
                    System.out.println(getName() + " забирает жалобу для решения");
                    queue.remove(list);
                    System.out.println("Диспетчер обрабатывает жалобу");
                    System.out.println("Диспетчер обработал жалобу и делает запись в новом Журнале");
                    IssuesTxtUtil.writeInSolutionFile(pathReader, pathWriter);
                    System.out.println("Удаление жалобы из списка жалоб");
                    IssuesTxtUtil.removeLineFromFile(list, pathReader);
                    try {
                        queue.wait(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else if (queue.isEmpty()){
                    System.out.println("Список проблем пуст.");
                    break;
                }
            }

        }

    }

    public Queue<Issue> getQueue() {
        return queue;
    }

    public void setQueue(Queue<Issue> queue) {
        this.queue = queue;
    }

    public List<Issue> getList() {
        return list;
    }

    public void setList(List<Issue> list) {
        this.list = list;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}