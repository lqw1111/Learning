package OODesign.Company;

public class Java extends SkillDecorator {

    public Java(Developer developer){
        this.developer = developer;
    }

    @Override
    public String getDescription() {
        return this.developer.getDescription() + " Java";
    }
}
