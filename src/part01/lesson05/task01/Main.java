package part01.lesson05.task01;

import part01.lesson05.task01.domain.Person;
import part01.lesson05.task01.domain.Pet;
import part01.lesson05.task01.domain.Sex;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * Урок 5.
 *
 * Разработать программу – картотеку домашних животных. У каждого животного есть уникальный идентификационный номер,
 * кличка, хозяин (объект класс Person с полями – имя, возраст, пол), вес.
 *
 *  Реализовать:
 *  - метод добавления животного в общий список (учесть, что добавление дубликатов должно приводить к исключительной ситуации)
 *  - поиск животного по его кличке (поиск должен быть эффективным)
 *  - изменение данных животного по его идентификатору
 *  - вывод на экран списка животных в отсортированном порядке. Поля для сортировки –  хозяин, кличка животного, вес.
 *
 * @version   1.0 26.04.2019
 * @author    Pavel Anisimov
 */

public class Main {

    public static void main(String[] args) {
        PetCatalog catalog = new PetCatalog();

        catalog.addPet(new Pet("Zuma", new Person(14, Sex.MAN, "Dima"), 3));
        catalog.addPet(new Pet("Kuzya", new Person(14, Sex.MAN, "Dima"), 3));
        catalog.addPet(new Pet("Zuma", new Person(14, Sex.MAN, "Dima"), 2));
        catalog.addPet(new Pet("Zuma", new Person(14, Sex.MAN, "Dima"), 11));
        catalog.addPet(new Pet("Petya", new Person(32, Sex.WOMAN, "Nastya"), 1));
        catalog.addPet(new Pet("Sharik", new Person(23, Sex.WOMAN, "Marina"), 2));
        catalog.addPet(new Pet("Shmonya", new Person(11, Sex.WOMAN, "Katya"), 5));
        catalog.addPet(new Pet("Artem", new Person(50, Sex.MAN, "Petya"), 4));

        catalog.print();

        Set<Pet> findPets = catalog.findPetByName("Zuma");
        if (findPets != null) {
            findPets.stream().forEach(p ->
                    System.out.println(p)
            );
            System.out.println();
        }


        Optional<UUID> optUuid = catalog.getCatalog().stream().map(Pet::getId).findFirst();
        UUID uuid = optUuid.isPresent() ? optUuid.get() : null;
        if (uuid != null) {
            Pet updatePet = new Pet(uuid, "Koshka", new Person(14, Sex.MAN, "Dima"), 3);
            catalog.editPetById(updatePet);
        }

        catalog.print();
    }
}
