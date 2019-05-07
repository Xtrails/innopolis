import domain.Pet;
import exception.PetDuplicateException;

import java.util.*;

/**
 * ����� ��������� �������� ��������
 *
 * @version   1.0 26.04.2019
 * @author    Pavel Anisimov
 */
public class PetCatalog {

    /** ��� ��� ������ ���������� �������� �������� Pet */
    private Set<Pet> catalog = new HashSet<>();

    /** Map ��� �������� ������ */
    private Map<String,Set<Pet>> findMap = new HashMap<>();

    /** ������ ����������� */
    public PetCatalog() {
    }

    /**
     * �������� �������
     * @return - ������� ��������
     */
    public Set<Pet> getCatalog() {
        return catalog;
    }

    /**
     * ����� ���������� ��������� � ����� ������ (���������� ���������� �������� � �������������� ��������)
     * @param pet - ����������� ��������
     */
    public void addPet(Pet pet){
        if(catalog.contains(pet)){
            try {
                throw new PetDuplicateException("������� ��� �������� ������ �������");
            } catch (PetDuplicateException e) {
                System.out.println(e.getMessage());
            }
        } else {
            catalog.add(pet);
            if(findMap.containsKey(pet.getName())){
                findMap.get(pet.getName()).add(pet);
            } else {
                Set<Pet> nameSet = new HashSet<>();
                nameSet.add(pet);
                findMap.put(pet.getName(),nameSet);
            }
        }
    }

    /**
     * ����� ��������� �� ��� ������
     * @param name - ������ ���������
     * @return
     */
    public Set<Pet> findPetByName(String name){
        if(!name.isEmpty()){
            System.out.println("���� �������� � �������: " + name);
            if(findMap.get(name)!=null){
                return findMap.get(name);
            }
        }
        return null;
    }

    /**
     * ��������� ������ ��������� �� ��� ��������������
     * @param pet - ������ Pet �� ������� ��������� ������ � ��������
     */
    public void editPetById(Pet pet) {
        if(pet!=null){
            System.out.println("�������� ������ ��������� � ID: " + pet.getId());
            for (Pet petIter : catalog) {
                if(petIter.getId().equals(pet.getId()))
                    petIter.setName(pet.getName());
                petIter.setOwner(pet.getOwner());
                petIter.setWeight(pet.getWeight());
            }
            System.out.println();
        }
    }

    /**
     * ����� �� ����� ������ �������� � ��������������� �������.
     * ���� ��� ���������� �  ������, ������ ���������, ���.
     */
    public void print(){
        List<Pet> sortCatalog = new ArrayList<>(catalog);
        Collections.sort(sortCatalog);
        sortCatalog.stream().forEach(pet ->
            System.out.println(pet.toString())
        );
        System.out.println();
    }
}
