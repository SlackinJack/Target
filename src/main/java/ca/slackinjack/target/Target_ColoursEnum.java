package ca.slackinjack.target;

public enum Target_ColoursEnum {
    BLACK("black", "0", 0),
    DARK_BLUE("darkblue", "1", 170),
    DARK_GREEN("darkgreen", "2", 43520),
    DARK_AQUA("darkaqua", "3", 43690),
    DARK_RED("darkred", "4", 11141120),
    DARK_PURPLE("darkpurple", "5", 11141290),
    GOLD("gold", "6", 16755200),
    GRAY("gray", "7", 11184810),
    DARK_GRAY("darkgray", "8", 5592405),
    BLUE("blue", "9", 5592575),
    GREEN("green", "a", 5635925),
    AQUA("aqua", "b", 5636095),
    RED("red", "c", 16733525),
    LIGHT_PURPLE("lightpurple", "d", 16733695),
    YELLOW("yellow", "e", 16777045),
    WHITE("white", "f", 16777215),
    OBFUSCATED("obfuscated", "k", 0),
    BOLD("bold", "l", 0),
    STRIKE_THROUGH("strikethrough", "m", 0),
    UNDERLINE("underline", "n", 0),
    ITALIC("italic", "o", 0),
    RESET("reset", "r", 0);

    private final String colourName;
    private final String colourFormat;
    private final int colourRGB;

    private Target_ColoursEnum(String colourNameIn, String colourFormatIn, int colourRGBIn) {
        this.colourName = colourNameIn;
        this.colourFormat = colourFormatIn;
        this.colourRGB = colourRGBIn;
    }

    public String getColourName() {
        return this.colourName;
    }

    public String getColourFormat() {
        return "\u00A7" + this.colourFormat;
    }
    
    public int getColourRGB() {
        return this.colourRGB;
    }
}
