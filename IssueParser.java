package Pattern.HomeTask;

/**
 * 4) IssueParser {
 *
 * // собирает из строки объект Issue
 * Issue parse(String line)
 *
 * }
 */

    public class IssueParser {

    public static Issue parse(String line) {
        String[] string = line.split(", ");
        Issue issue = new Issue(Integer.parseInt(string[0]), string[1], string[2], string[3], string[4]);
        return issue;
    }
}
