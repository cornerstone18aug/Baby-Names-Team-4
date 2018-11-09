public class BabyName {
    public enum Gender { Male, Female }
    private String rank;
    private String name;
    private Gender gender;

    public BabyName() {
    }

    public BabyName(String rank, String name, Gender gender) {
        this();
        this.setRank(rank);
        this.setName(name);
        this.setGender(gender);
    }


    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
