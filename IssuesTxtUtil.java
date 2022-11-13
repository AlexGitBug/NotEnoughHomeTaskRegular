package Pattern.HomeTask;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IssuesTxtUtil {

    public static List<Issue> read(Path path) throws IOException {
        List<Issue> list = new ArrayList<>();
        try (BufferedReader bufferedReader = Files.newBufferedReader(path)) {
            String readLine = bufferedReader.readLine();
            while ((readLine = bufferedReader.readLine()) != null) {
                Issue parse = IssueParser.parse(readLine);
                list.add(parse);
            }
            return list;
        }
    }

    public static void write(Path path) throws IOException {
        Path pathWriter = Path.of("resources", "log-file-Solutions.csv");
        //                1             2                3          4         5                    6                     7       8      9      10    11     12     13    14      15    16       17
        String regex = "(\\d)(,\\s\\d{4}-\\d{2}-\\d{2})(\\w)(\\d{2}:\\d{2})(:\\d{2})(,\\s[А-Яа-я]{6}\\s[А-Яа-я]{4},\\s)(\\+?)(?:(375)?)(\\s?)(\\d{2})(\\s?)(\\d{3})(\\s?)(\\d{2})(\\s?)(\\d{2})(,\\s[А-Яа-я\\s?\\-\\-?]+)";
        try (Stream<String> lines = Files.lines(path);
             BufferedWriter bufferedWriter = Files.newBufferedWriter(pathWriter, StandardOpenOption.APPEND)) {
            lines.map(StringBuilder::new)
                    .map(stringBuilder -> stringBuilder.toString())
                    .forEach(line -> {
                        try {
                            bufferedWriter.write(line.replaceAll(regex, "$1$2 $4, +375 ($10) $12-$14-$16"));
                            bufferedWriter.newLine();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    });
        }
    }

    public static void removeLineFromFile(List<Issue> list, Path path) throws IOException {
        String currentLineRegex = "(\\d,)\\s(\\d{4}-\\d{2}-\\d{2})(\\w)(\\d{2}:\\d{2})(:\\d{2}),\\s[А-Яа-я]{6}\\s[А-Яа-я]{4},\\s\\+?(375)?\\s?\\d{2}\\s?\\d{3}\\s?\\d{2}\\s?\\d{2},\\s[А-Яа-я\\s?\\-\\-?]+";
        List<String> out = Files.lines(path)
                .filter(line -> list.contains(currentLineRegex))
                .collect(Collectors.toList());

        Files.write(path, out, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public static void writeNewIssue(Path path) throws IOException {

        Path pathWriter = Path.of("resources", "log-file-Solutions.csv");
        String newIssue = "4, 2021-09-13T10:15:30, Иванов Женя, +375 29 111 77 77, Течет вода";

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            bufferedWriter.newLine();
            bufferedWriter.write(newIssue);
        }
    }
}
