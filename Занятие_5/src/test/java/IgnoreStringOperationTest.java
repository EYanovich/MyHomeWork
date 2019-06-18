import org.testng.annotations.Test;

import java.util.UnknownFormatConversionException;


public class IgnoreStringOperationTest {

    StringOperation stringOperation = new StringOperation();

    @Test(enabled = false, expectedExceptions = StringIndexOutOfBoundsException.class)
    public void testGetIndexChar() {
        String str = "fffkkkeeelll";
        int indexNum = 15;
        stringOperation.getIndexChar(indexNum, str);
    }

    @Test(enabled = false, expectedExceptions = UnknownFormatConversionException.class)
    public void testGetStringFormat() {
        String strFormat = "aaa%^bbb";
        String str = "aaaabbb";
        stringOperation.getStringFormat(strFormat, str);
    }
}