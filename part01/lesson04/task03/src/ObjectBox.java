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
public class ObjectBox<T> {

    /** ��������� */
    private Set<T> set = new HashSet<>();

    /** ������ ����������� */
    public ObjectBox() {
    }

    /**
     * �����������
     * @param arr - ������
     */
    public ObjectBox(T[] arr) {
        set = new HashSet<>(Arrays.asList(arr));
    }

    /**
     * �������� ���
     * @return - ���
     */
    public Set<T> getSet() {
        return set;
    }

    /**
     * �������� ���
     * @param set - ���
     */
    public void setSet(Set<T> set) {
        this.set = set;
    }

    /**
     * �������� ������ � ���������
     * @param obj - ����������� ������
     */
    public void addObject(T obj){
        set.add(obj);
    }

    /**
     * ��������� ������� ������� � ��������� � ��� ������� ������� ���
     * @param obj - ��������� ������
     */
    public void deleteObject(T obj){
        if(set.contains(obj)){
            set.remove(obj);
        }
    }

    /**
     * ������� ���������� ��������� � ������
     */
    public void dump(){
        for (T t : set) {
            System.out.print(t.toString() + " ");
        }
        System.out.println();
    }
}
