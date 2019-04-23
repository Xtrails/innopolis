import java.util.*;

/**
 * ����� ObjectBox, ������� ������ ��������� Object.
 * � ������ ������ ���� ����� addObject, ����������� ������ � ���������.
 * � ������ ������ ���� ����� deleteObject, ����������� ������� ������� � ��������� � ��� ������� ��������� ���.
 * ������ ���� ����� dump, ��������� ���������� ��������� � ������.
 *
 * @version   1.0 23.04.2019
 * @author    Pavel Anisimov
 */
public class ObjectBox<T extends Number> extends Number {

    /** ��������� */
    private List<T> list = new ArrayList<>();

    /** ������ ����������� */
    public ObjectBox() {
    }

    /**
     * �����������
     * @param arr - ������
     */
    public ObjectBox(T[] arr) {
        Set<T> set = new HashSet<>(Arrays.asList(arr));
        list.addAll(set);
    }

    /**
     * �������� ����
     * @return - ����
     */
    public List<T> getList() {
        return list;
    }

    /**
     * �������� ����
     * @param list - ����
     */
    public void setList(List<T> list) {
        this.list = list;
    }

    /**
     * �������� ������ � ���������
     * @param obj - ����������� ������
     */
    public void addObject(T obj){
        list.add(obj);
    }

    /**
     * ��������� ������� ������� � ��������� � ��� ������� ������� ���
     * @param obj - ��������� ������
     */
    public void deleteObject(T obj){
        if(list.contains(obj)){
            list.remove(obj);
        }
    }

    /**
     * ������� ���������� ��������� � ������
     */
    public void dump(){
        for (T t : list) {
            System.out.print(t.toString() + " ");
        }
        System.out.println();
    }

    @Override
    public int intValue() {
        return 0;
    }

    @Override
    public long longValue() {
        return 0;
    }

    @Override
    public float floatValue() {
        return 0;
    }

    @Override
    public double doubleValue() {
        return 0;
    }
}
