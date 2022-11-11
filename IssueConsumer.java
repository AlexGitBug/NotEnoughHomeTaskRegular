package Pattern.HomeTask;

import Thread.HomeTask.Crystal;
import Thread.HomeTask.CrystalColorEnum;

import java.io.IOException;
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
    Path pathWriter = Path.of("resources", "log-file-Solutions.csv");
    Path pathReader = Path.of("resources", "log-file-Complaint.csv");
    public IssueConsumer(Queue<Issue> queue, List<Issue> list) {
        this.queue = queue;
        this.list = list;
    }

    @Override
    public void run() {
            synchronized (queue) {
                while (true) {
                if (!queue.isEmpty()) {
                    try {
                        if (IssuesTxtUtil.read(pathReader).remove(0) == null) {
                            break;
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Диспетчер забирает жалобу для решения");
                    queue.remove(list);
                    System.out.println("Диспетчер обрабатывает жалобу");
                    try {
                        queue.wait(3500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Диспетчер обработал жалобу и делает запись в Журнале");
                    try {
                        IssuesTxtUtil.write(pathReader, list);
                        System.out.println("Удалиение жалобы из списка");
                        IssuesTxtUtil.removeLineFromFile(list, pathReader);
                        System.out.println(list);
                    } catch (IOException e) {
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
}