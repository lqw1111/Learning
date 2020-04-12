package OODesign.Company;

public class CPlusPlus extends SkillDecorator {

    public CPlusPlus(Developer developer){
        this.developer = developer;
    }

    @Override
    public String getDescription() {
        return this.developer.getDescription() + " C++";
    }
}
