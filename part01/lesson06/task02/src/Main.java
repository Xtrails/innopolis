import java.util.List;

/**
 * ���� 6. ������� 2.
 *
 * ������� ��������� ��������� ������, ���������� �� ��������� ��������:
 *
 *  - ����������� ������� �� 1<=n1<=15 ����. � ����������� ����� ������������ ���� ����� ���������� �������.
 *  - ����� ������� �� 1<=n2<=15 ��������� ����
 *  - ����� ��������� ����� ��������
 *  - ����������� ���������� � ��������� �����
 *  - ����������� ������������� (.|!|?)+" "
 *  - ����� ������� �� �������. � ����� ������ 1<=n3<=20 �����������. � ����� ������ ����� ������ ������ � ������� �������.
 *  - ���� ������ ���� 1<=n4<=1000. ���� ����������� probability ��������� ������ �� ���� ����� ������� � ��������� ����������� (1/probability).
 *
 * ���������� �������� ����� getFiles(String path, int n, int size, String[] words, int probability),
 * ������� ������� n ������ �������� size � �������� path. words - ������ ����, probability - �����������.
 *
 * @version   1.0 29.04.2019
 * @author    Pavel Anisimov
 */
public class Main {
    public static void main(String[] args) {
//        for (int i = 0; i < 30; i++) {
//            System.out.println(Utill.createRndWord(1,15));
//        }
        for (int i = 0; i < 10; i++) {
            System.out.print(Utill.createRndParagraph(1, 15));
        }
    }
}
