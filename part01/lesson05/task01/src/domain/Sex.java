package domain;

/**
 * ����� Sex �� ���������� ����������� ������ MAN, WOMAN
 *
 * @version   1.0 26.04.2019
 * @author    Pavel Anisimov
 */
public enum Sex {
    MAN ("MAN"),
    WOMAN ("WOMAN");

    /** ��������� */
    private String title;

    /**
     * �����������
     * @param title - ���������
     */
    Sex(String title) {
        this.title = title;
    }

    /**
     * �������� ���������
     * @return - ���������
     */
    public String getTitle() {
        return title;
    }

    /**
     * ������� ��������� � ���� ������
     * @return - ���������
     */
    @Override
    public String toString() {
        return "domain.Sex{" +
                "title='" + title + '\'' +
                '}';
    }
}