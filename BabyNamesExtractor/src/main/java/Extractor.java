import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Extractor {
    static Pattern tableRowPattern = Pattern.compile("<tr .*?><td>(\\d*?)<\\/td><td>([\\w\\s]*)<\\/td><td>([\\w\\s]*)<\\/td>");
    static Pattern h3YearPattern = Pattern.compile("<h3 .*?>[\\w\\s]*?(\\d*?)<\\/h3>");

    public static String extractYear(String[] lines) {
        String year = null;
        for (String line : lines) {
            Matcher h3Match = h3YearPattern.matcher(line);
            if (h3Match.matches() && h3Match.groupCount() == 1) {
                year = h3Match.group(1);
                break;
            }
        }
        return year;
    }

    public static ArrayList<BabyName> extractNames(String[] lines) {
        ArrayList<BabyName> babyNameList = new ArrayList<BabyName>();
        for (String line : lines) {
            // get the matchs
            Matcher rowMatch = tableRowPattern.matcher(line);
            if (rowMatch.matches() && rowMatch.groupCount() == 3) {
                // 1 - rank
                // 2 - MaleName
                // 3 = femaleName
                BabyName maleName = new BabyName(rowMatch.group(1), rowMatch.group(2), BabyName.Gender.Male);
                BabyName femaleName = new BabyName(rowMatch.group(1), rowMatch.group(3), BabyName.Gender.Female);
                babyNameList.add(maleName);
                babyNameList.add(femaleName);
            }
        }
        return babyNameList;
    }

}
