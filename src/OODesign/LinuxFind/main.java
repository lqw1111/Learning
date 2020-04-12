package OODesign.LinuxFind;

public class main {
    public static void main(String[] args) {
        Finder finder = new Finder();
//        finder.addFilter(new XMLFilter());
//        finder.addFilter(new SizeFilter());
        finder.find(new File());
    }
}
