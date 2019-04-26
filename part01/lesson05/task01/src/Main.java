import domain.Person;
import domain.Pet;
import domain.Sex;

import java.util.Set;
import java.util.UUID;

/**
 * ”рок 5.
 *
 * –азработать программу Ц картотеку домашних животных. ” каждого животного есть уникальный идентификационный номер,
 * кличка, хоз€ин (объект класс Person с пол€ми Ц им€, возраст, пол), вес.
 *
 *  –еализовать:
 *  - метод добавлени€ животного в общий список (учесть, что добавление дубликатов должно приводить к исключительной ситуации)
 *  - поиск животного по его кличке (поиск должен быть эффективным)
 *  - изменение данных животного по его идентификатору
 *  - вывод на экран списка животных в отсортированном пор€дке. ѕол€ дл€ сортировки Ц  хоз€ин, кличка животного, вес.
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

        UUID uuid = catalog.getCatalog().stream().map(Pet::getId).findFirst().get();
        Pet updatePet = new Pet(uuid, "Koshka", new Person(14, Sex.MAN, "Dima"), 3);
        catalog.editPetById(updatePet);

        catalog.print();
    }
}
