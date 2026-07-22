package abstractfactory;

public class FactoryProducer {

    public static AbstractFactory getFactory(String majorName) {

        if (majorName.equalsIgnoreCase("Information Technology")) {
            return new ITFactory();
        }

        if (majorName.equalsIgnoreCase("Business")) {
            return new BusinessFactory();
        }

        if (majorName.equalsIgnoreCase("English")) {
            return new EnglishFactory();
        }

        return null;
    }
}