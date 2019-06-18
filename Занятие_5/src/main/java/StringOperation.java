public class StringOperation {

    public char getIndexChar(int index, String str) {
        return str.charAt(index);
    }

    public String getUperCaseStr(String str) {
        return str.toUpperCase();
    }

    public String getStringTrim(String str) {
        return str.trim();
    }

    public String[] getStringSplit(String str, String regEx) {
        return str.split(regEx);
    }

    public String getStringFormat(String formatStr, String str) {
        return String.format(formatStr, str);
    }

}
