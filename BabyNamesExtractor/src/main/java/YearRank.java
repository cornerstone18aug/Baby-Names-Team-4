import java.security.cert.Extension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class YearRank {
    private String year;
    private HashMap<String, BabyName> babyNameHashMap;
    private String path;

    private YearRank() {
        this.babyNameHashMap = new HashMap<String, BabyName>();
    }

    private YearRank(String year) {
        this();
        this.year = year;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void add(BabyName babyName) {
        if(babyNameHashMap.get(babyName.getName()) == null || Integer.parseInt( babyNameHashMap.get(babyName.getName()).getRank()) < Integer.parseInt(babyName.getRank())) {
            babyNameHashMap.put(babyName.getName(), babyName);
        }
    }

    public BabyName get(String babyName) {
        return babyNameHashMap.get(babyName);
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n" + this.getYear() + " ------------");
        for (Map.Entry<String, BabyName> entry : babyNameHashMap.entrySet()) {
            BabyName babyName = entry.getValue();
            sb.append("\n" + babyName.getName() + " - " + babyName.getRank());
        }
        return sb.toString();
    }
    public YearRank loadFromFile(String path) {
        this.path = path;
        // load the file
        String[] lines = FileHelper.readLinesFromFile(path);
        // load the year
        this.year = Extractor.extractYear(lines);
        // extract the names from the file
        ArrayList<BabyName> babyNames = Extractor.extractNames(lines);
        for (BabyName babyName:babyNames) {
            this.add(babyName);
        }
        return this;
    }
    public YearRank saveSummary() {
        if(this.getYear() != null && this.babyNameHashMap.size() > 0) {
            FileHelper.writeToFile(this.path + ".summary", this.print());
        }
        return this;
    }

    // static methods
    public static void generateSummary(String path) {
        String[] filesPathList = FileHelper.getFilesList(path);
        for (String filePath :filesPathList) {
            if(filePath.contains(".html")) {
                new YearRank().loadFromFile(filePath).saveSummary();
            }
        }
    }

}
