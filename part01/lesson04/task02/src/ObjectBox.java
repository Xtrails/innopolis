import java.util.ArrayList;
import java.util.List;

/**
 * ����� ObjectBox, ������� ������ ��������� Object.
 * � ������ ������ ���� ����� addObject, ����������� ������ � ���������.
 * � ������ ������ ���� ����� deleteObject, ����������� ������� ������� � ��������� � ��� ������� ��������� ���.
 * ������ ���� ����� dump, ��������� ���������� ��������� � ������.
 *
 * @version   1.0 23.04.2019
 * @author    Pavel Anisimov
 */
public class ObjectBox<T> {

    /** ��������� */
    private List<T> list = new ArrayList<>();

    /**
     * �����������
     * @param list - ����
     */
    public ObjectBox(List<T> list) {
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
}
