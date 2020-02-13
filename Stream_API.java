package Others;

import java.util.ArrayList;
import java.util.List;

public class Stream_API {

    /**
     *  Добавил метод main с автогенерацией данных и выводом их в консоль.
     *  Добавил вывод результата в консоль.
     *  Исправил названия классов MembersGroup на Group, Finder на Service и метода findOldMembers на findByAge.
     *  Изменил возвращаемое значение findByAge с Set'а на List, т.к. имена могут дублироваться, а Set хранит
     *      только уникальные значения, что в случае повторяющихся имён приведёт к потере части данных
     *      и некорректному результату.
     *  Изменил название списка в findByAge с groupsNames на membersNames.
     *  Изменил логику поиска в findByAge с exclusive на inclusive в соответствии с общепринятыми нормами поиска.
     *
     *  Исправил комментарии в классе Service:
     *      1:
     *              "@return список имен групп из списка групп старше возраста targetAge"
     *          как намеренно запутывающий и противоречащий тому, что выводит в качестве результата дефолтная версия
     *          метода findOldMembers, на:
     *              "@return список имён членов групп, чей возраст старше targetAge".
     *      2:
     *              "Поиск групп людей старше определенного возраста."
     *          на:
     *              "Поиск людей старше определенного возраста."
     *      3:
     *              "@param groups группы"
     *          на:
     *              "@param groups список групп"
     *      4:
     *              "@param targetAge возраст для поиска"
     *          на:
     *              "@param targetAge минимальный возраст"
     */

    public class Group {
        private final String groupName;
        private final List<Member> members;
        public Group(String groupName, List<Member> members) {
            this.groupName = groupName;
            this.members = members;
        }
        public String getGroupName() {
            return groupName;
        }
        public List<Member> getMembers() {
            return members;
        }
    }

    public class Member {
        private final String name;
        private final Integer age;
        public Member(String name, Integer age) {
            this.name = name;
            this.age = age;
        }
        public String getName() {
            return name;
        }
        public Integer getAge() {
            return age;
        }
    }

    public static void main(String[] args) {
        Stream_API stream_api = new Stream_API();
        List<Group> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            List<Member> members = new ArrayList<>();
            for (int j = 0; j < 3; j++)
                members.add(stream_api.new Member(String.valueOf(i) + String.valueOf(j), (int) (Math.random() * 30)));
            list.add(stream_api.new Group(String.valueOf(i), members));
        }
        for (Group grp : list) {
            System.out.println("Group groupName: " + grp.getGroupName());
            for (Member mbr : grp.getMembers())
                System.out.println("Name: " + mbr.getName() + ", age: " + mbr.getAge());
            System.out.println("-------------------");
        }

        print(stream_api.new Service().findByAge(list, 15));
    }

    private static void print(List<String> set) {
        System.out.println("Result:");
        for (String str : set)
            System.out.println(str);
    }

    public class Service {

        /**
         * Поиск людей старше определенного возраста.
         *
         * @param groups список групп
         * @param targetAge минимальный возраст
         * @return список имён членов групп, чей возраст старше targetAge
         */

        public List<String> findByAge(List<Group> groups, int targetAge) {
            final List<String> membersNames = new ArrayList<>();
            groups.forEach(group -> {
                group.getMembers().stream().filter(member -> member.getAge() >= targetAge)
                        .forEach(member -> {
                            membersNames.add(member.getName());
                });
            });
            return membersNames;
        }
    }
}