import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

public class PositiveStringOperationTest {

    StringOperation stringOperation = new StringOperation();

    @DataProvider
    public Object[][] getDataIndexChar() {
        return new Object[][]{{"aaa bbb ccc ddd iii", 10, 'c'},
                {"fffkkkeeelll", 4, 'k'}};
    }

    @Test(dataProvider = "getDataIndexChar")
    public void testGetIndexChar(String str, int indexNum, char expectedResult) {
        Assert.assertEquals(stringOperation.getIndexChar(indexNum, str), expectedResult);
    }

    @DataProvider
    public Object[][] getDataUperCaseStr() {
        return new Object[][]{{"aaa bbb ccc ddd iii", "AAA BBB CCC DDD III"},
                {"fffkkkeeelll", "FFFKKKEEELLL"}};
    }

    @Test(dataProvider = "getDataUperCaseStr")
    public void testGetUperCaseStr(String str, String expectedResult) {
        Assert.assertEquals(stringOperation.getUperCaseStr(str), expectedResult);
    }

    @DataProvider
    public Object[][] getDataStringTrim() {
        return new Object[][]{{"  aaaiii  ", "aaaiii"},
                {"    fffkkkeeelll", "fffkkkeeelll"},
                {"sssdddee   ", "sssdddee"},
                {"nnnnmmmm", "nnnnmmmm"}};
    }

    @Test(dataProvider = "getDataStringTrim")
    public void testGetStringTrim(String str, String expectedResult) {
        Assert.assertEquals(stringOperation.getStringTrim(str), expectedResult);
    }

    @DataProvider
    public Object[][] getDataStringSplit() {
        return new Object[][]{{"aaa bbb ccc ddd iii", " ", "[aaa, bbb, ccc, ddd, iii]"},
                {"fff-kkk-eee-lll", "-", "[fff, kkk, eee, lll]"}};
    }

    @Test(dataProvider = "getDataStringSplit")
    public void testGetStringSplit(String str, String regEx, String expectedResult) {
        Assert.assertEquals(Arrays.toString(stringOperation.getStringSplit(str, regEx)), expectedResult);
    }

    @DataProvider
    public Object[][] getDataStringFormat() {
        return new Object[][]{{"Текст до вставки строки ' %s ' Текст после вставки строки", "aaabbbcccdddiii", "Текст до вставки строки ' aaabbbcccdddiii ' Текст после вставки строки"},
                {"Текст до вставки строки ' %s ' Текст после вставки строки", "fff-kkk-eee-lll", "Текст до вставки строки ' fff-kkk-eee-lll ' Текст после вставки строки"}};
    }

    @Test(dataProvider = "getDataStringFormat")
    public void testGetStringFormat(String formatStr, String str, String expectedResult) {
        Assert.assertEquals(stringOperation.getStringFormat(formatStr, str), expectedResult);
    }
}