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
                    try {
                        if (IssuesTxtUtil.read(pathReader).remove(0) == null) {
                            break;
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("В очередь добавилась проблема");
                    try {
                            queue.offer(IssuesTxtUtil.read(pathReader).get(0));
                        } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    try {
                        queue.wait(4500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }

            }
        }
    }
