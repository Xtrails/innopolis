import jdk.jshell.execution.Util;

import java.io.File;
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
        FileUtill.createDictonariesFile();

//        byte[] buffer ="".getBytes();
//        FileUtill.writeFile(buffer,"test.txt");

//        ���� �������� ���������� �����
//        for (int i = 0; i < 30; i++) {
//            System.out.println(Utill.createWord(1,15));
//        }

//        ���� ����������� � ��������� ������
//        String sentence = "";
//        int n = 100;
//        String[] words = FileUtill.getWords(n);
//        for (int i = 2; i < n; i++) {
//            String word = Utill.getRandomWordFromArr(words);
//            sentence = Utill.createSentence(i,word);
//            System.out.print(sentence.length() == i ? "true" : "false");
//            System.out.println(" " + sentence + " size=" + sentence.length());
//        }


    }
}
