import org.testng.annotations.Test;

import java.util.UnknownFormatConversionException;


public class NegativeStringOperationTest {

    StringOperation stringOperation = new StringOperation();

    @Test(expectedExceptions = StringIndexOutOfBoundsException.class)
    public void testGetIndexChar() {
        String str = "fffkkkeeelll";
        int indexNum = 15;
        stringOperation.getIndexChar(indexNum, str);
    }

    @Test(expectedExceptions = UnknownFormatConversionException.class)
    public void testGetStringFormat() {
        String strFormat = "aaa%^bbb";
        String str = "aaaabbb";
        stringOperation.getStringFormat(strFormat, str);
    }
}