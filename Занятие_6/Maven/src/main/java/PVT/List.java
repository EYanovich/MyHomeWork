package PVT;

import java.util.ArrayList;
import java.util.Collections;

public class List {
    public ArrayList<Integer> getFilledList(int listSize) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < listSize; i++) {
            list.add( (int) (Math.random() * 100) );
        }
        return list;
    }

    public int getMaxValueList(ArrayList<Integer> list) {
        return Collections.max( list );
    }

    public int getMinValueList(ArrayList<Integer> list) {
        return Collections.min( list );
    }

    public ArrayList<Integer> getSortList(ArrayList<Integer> list) {
        Collections.sort( list );
        return list;
    }

    public ArrayList<Integer> getListReplaceValue(ArrayList<Integer> list, int value, int newValue) {
        Collections.replaceAll( list, value, newValue );
        return list;
    }

    public boolean getListIsEmpty(ArrayList<Integer> list) {
        return list.isEmpty();
    }

    public ArrayList<Integer> getReverseList(ArrayList<Integer> list) {
        Collections.reverse( list );
        return list;
    }

    public ArrayList<Integer> getListChangeValueByIndex(ArrayList<Integer> list, int i, int j) {
        Collections.swap( list, i, j );
        return list;
    }

    public ArrayList<String> getListRemove(ArrayList<String> listFirst, ArrayList<String> listSecond) {
        listFirst.removeAll( listSecond );
        return listFirst;
    }

    public ArrayList<String> getListRetein(ArrayList<String> listFirst, ArrayList<String> listSecond) {
        listFirst.retainAll( listSecond );
        return listFirst;
    }

}
