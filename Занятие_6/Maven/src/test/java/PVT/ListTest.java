package PVT;

import PVT.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

class ListTest {
    List list = new List();

    @Test
    void getFilledListTest() {
        int sizeList = 15;
        ArrayList<Integer> arrList = list.getFilledList( sizeList );
        Assertions.assertNotNull( arrList );
        Assertions.assertEquals( arrList.size(), sizeList );
    }

    @Test
    void getMaxValueListTest() {
        ArrayList<Integer> arlist = new ArrayList( Arrays.asList( 1, 2, 3, 5, 8, 13, 21 ) );
        int expected = 21;
        Assertions.assertEquals( expected, list.getMaxValueList( arlist ) );
    }

    @Test
    void getMinValueListTest() {
        ArrayList<Integer> arlist = new ArrayList( Arrays.asList( 1, 2, 3, 5, 8, 13, 21 ) );
        int expected = 1;
        Assertions.assertEquals( expected, list.getMinValueList( arlist ) );
    }

    @Test
    void getSortListTest() {
        ArrayList<Integer> arlist = new ArrayList( Arrays.asList( 23, 54, 12, 87, 96, 33, 21 ) );
        ArrayList<Integer> expected = new ArrayList( Arrays.asList( 12, 21, 23, 33, 54, 87, 96 ) );
        Assertions.assertEquals( expected, list.getSortList( arlist ) );
    }

    @Test
    void getListReplaceValueTest() {
        ArrayList<Integer> arlist = new ArrayList( Arrays.asList( 23, 54, 12, 87, 54, 33, 54 ) );
        ArrayList<Integer> expected = new ArrayList( Arrays.asList( 23, 5, 12, 87, 5, 33, 5 ) );
        int value = 54;
        int replaceValue = 5;
        Assertions.assertEquals( expected, list.getListReplaceValue( arlist, value, replaceValue ) );
    }

    @Test
    void getListIsEmptyTest() {
        ArrayList<Integer> arlist = new ArrayList<Integer>();
        Assertions.assertTrue( list.getListIsEmpty( arlist ) );
    }

    @Test
    void getReverseListTest() {
        ArrayList<Integer> arlist = new ArrayList( Arrays.asList( 23, 54, 12, 87, 96, 33, 21 ) );
        ArrayList<Integer> expected = new ArrayList( Arrays.asList( 21, 33, 96, 87, 12, 54, 23 ) );
        Assertions.assertEquals( expected, list.getReverseList( arlist ) );
    }

    @Test
    void getListChangeValueByIndexTest() {
        ArrayList<Integer> arlist = new ArrayList( Arrays.asList( 23, 54, 12, 87, 96, 33, 21 ) );
        ArrayList<Integer> expected = new ArrayList( Arrays.asList( 23, 33, 12, 87, 96, 54, 21 ) );
        int index = 1;
        int changeIndex = 5;
        Assertions.assertEquals( expected, list.getListChangeValueByIndex( arlist, index, changeIndex ) );
    }

    @Test
    void getListRemoveTest() {
        ArrayList<String> arlistFirst = new ArrayList( Arrays.asList( "White", "Black", "Red" ) );
        ArrayList<String> arlistSecond = new ArrayList( Arrays.asList( "Green", "Red", "White" ) );
        ArrayList<String> expected = new ArrayList( Arrays.asList( "Black" ) );
        Assertions.assertEquals( expected, list.getListRemove( arlistFirst, arlistSecond ) );
    }

    @Test
    void getListClearTestc() {
        ArrayList<String> arlistFirst = new ArrayList( Arrays.asList( "White", "Black", "Red" ) );
        ArrayList<String> arlistSecond = new ArrayList( Arrays.asList( "Green", "Red", "White" ) );
        ArrayList<String> expected = new ArrayList( Arrays.asList( "White", "Red" ) );
        Assertions.assertEquals( expected, list.getListRetein( arlistFirst, arlistSecond ) );
    }


}