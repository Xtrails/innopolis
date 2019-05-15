package part01.lesson05.task01;


import part01.lesson05.task01.domain.Pet;
import part01.lesson05.task01.exception.PetDuplicateException;

import java.util.*;

/**
 * Класс картотека домашних животных
 *
 * @version   1.0 26.04.2019
 * @author    Pavel Anisimov
 */
public class PetCatalog {

    /** Сет где храним уникальные значения объектов Pet */
    private Set<Pet> catalog = new HashSet<>();

    /** Map для быстрого поиска */
    private Map<String,Set<Pet>> findMap = new HashMap<>();

    /** Пустой конструктор */
    public PetCatalog() {
    }

    /**
     * Получить каталог
     * @return - каталог животных
     */
    public Set<Pet> getCatalog() {
        return catalog;
    }

    /**
     * Метод добавления животного в общий список (добавление дубликатов приводит к исключительной ситуации)
     * @param pet - добавляемое животное
     */
    public void addPet(Pet pet){
        if(catalog.contains(pet)){
            try {
                throw new PetDuplicateException("Каталог уже содержит такого питомца");
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
     * Поиск животного по его кличке
     * @param name - кличка животного
     * @return
     */
    public Set<Pet> findPetByName(String name){
        if(!name.isEmpty()){
            System.out.println("Ищем животное с кличкой: " + name);
            if(findMap.get(name)!=null){
                return findMap.get(name);
            }
        }
        return null;
    }

    /**
     * Изменение данных животного по его идентификатору
     * @param pet - объект Pet на который обновятся данные в каталоге
     */
    public void editPetById(Pet pet) {
        if(pet!=null){
            System.out.println("Изменяем данные животного с ID: " + pet.getId());
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
     * Вывод на экран списка животных в отсортированном порядке.
     * Поля для сортировки –  хозяин, кличка животного, вес.
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
