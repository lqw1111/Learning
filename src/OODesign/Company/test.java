package OODesign.Company;

public class test {
    public static void main(String[] args) {
        Developer developer = new CompanyDeveloper();
        System.out.println(developer.getDescription());

        developer = new Java(developer);
        System.out.println(developer.getDescription());

        developer = new Python(developer);
        System.out.println(developer.getDescription());

        developer = new CPlusPlus(developer);
        System.out.println(developer.getDescription());

        developer = new CSharp(developer);
        System.out.println(developer.getDescription());

        developer = new Web(developer);
        System.out.println(developer.getDescription());
    }
}
