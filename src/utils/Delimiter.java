package utils;

public enum Delimiter {
    Song("@10@"),
    Musician("@20@"),
    Album("@30@"),
    Category("@40@");

    private String delim = "";

    Delimiter(String s) {
        delim = s;
    }

    public String getDelim() {
        return delim;
    }
}
