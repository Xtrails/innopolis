package part01.lesson04.task03;

import java.util.*;

/**
 * ����� MathBox, ����������� ��������� ����������:
 *
 * ����������� �� ���� �������� ������ Number. �������� �� ����� �����������.
 * �������� ������� ������ ������� �������������� � ���������� ��������� (������� ��������������).
 * ���������� ����� summator, ������������ ����� ���� ��������� ���������.
 * ���������� ����� splitter, ����������� ����������� ������� ���� ���������� � ������� ��������� �� ��������,
 * ���������� ���������� ������. ���������� � ������� ������ ��������� ���������� ������������ �������.
 * ���������� ��������� �������������� ������ toString, hashCode, equals, ����� ����� ���� ������������
 * MathBox ��� ������ ������ �� ����� � �������� �������� ����� ������ � ���������� (��������, hashMap).
 * ���������� ��������� �����������!
 * ������� �����, ������� �������� �� ���� Integer � ���� ����� �������� ���� � ���������, ������� ���.
 *
 * @version   1.0 23.04.2019
 * @author    Pavel Anisimov
 */
public class MathBox<T extends Number> extends ObjectBox<T> {

    public MathBox(Object o) {
        if(o instanceof Object){
            try {
                throw new MathBoxException("������ ���� Object �� ����� ���� ������� � ����������� MathBox");
            } catch (MathBoxException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * �����������
     * @param arr - ������
     */
    public MathBox(T[] arr) {
        super(arr);
    }

    /**
     * ����� summator, ������������ ����� ���� ��������� ���������
     * @param set - ��� ��������
     * @return - ����� �������� ���������
     */
    public static Double summator(Set<? extends Number> set) {
        return set
                .stream()
                .map(Number::doubleValue)
                .reduce((s1, s2) -> s1 + s2)
                .orElse(0D);
    }

    /**
     * ����� �������� �� ���� Integer � ���� ����� �������� ���� � ���������, ������� ���
     * @param val - ��������, ������� ���������� ������� �� �����
     */
    public void deleteIntVal(Integer val){

        if(this.getSet().contains(val)){
            this.getSet().remove(val);
        }
    }

    /**
     * ����� splitter, ����������� ����������� ������� ���� ���������� � ������� ��������� �� ��������,
     * ���������� ���������� ������. ���������� � ������� ������ ��������� ���������� ������������ �������.
     * @param val - ��������
     */
    public void splitter(T val) {
        if (val != null) {
            Set<T> splitSet = new HashSet<>();
            for (T t : this.getSet()) {
                Double newVal = t.doubleValue() / val.doubleValue();
                splitSet.add((T) newVal);
            }
            this.getSet().clear();
            this.getSet().addAll(splitSet);
        }
    }

    @Override
    public String toString() {
        String result = "";
        for (T t : this.getSet()) {
            result+=t.toString() + " ";
        }
        return "MathBox{" +
                " list=" + result +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MathBox<?> mathBox = (MathBox<?>) o;
        return Objects.equals(this.getSet(), mathBox.getSet());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getSet());
    }
}
