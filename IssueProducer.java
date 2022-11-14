package Pattern.HomeTask;

import Thread.HomeTask.Crystal;
import Thread.HomeTask.CrystalColorEnum;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * 2) IssueProducer impl Runnable {
 *  Queue<Issue> queue;
 *
 *  раз в N минут используя IssuesCsvUtil.read считывает пробелмы из файла и помещает их в очередь
 * }
 */
    public class IssueProducer implements Runnable {

    Path pathReader = Path.of("resources", "log-file-Complaint.csv");
    private Queue<Issue> queue;
    private List<Issue> list;

    public IssueProducer(Queue<Issue> queue, List<Issue> list) {
        this.queue = queue;
        this.list = list;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                if (IssuesTxtUtil.read(pathReader).isEmpty()) {
                    break;
                }
                System.out.println("В очередь добавилась проблема");
                queue.offer(IssuesTxtUtil.read(pathReader).get(0));
                try {
                    queue.wait(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("В очередь добавилась еще одна жалоба");
                IssuesTxtUtil.writeNewIssue(pathReader);
                System.out.println("Ожидаем считывание жалобы");
                queue.offer(IssuesTxtUtil.read(pathReader).get(0));
                System.out.println("Жалоба считалась и передана диспетчерам");
                try {
                    queue.wait(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("В очередь добавилась еще одна жалоба");
            }

        }
    }
}
