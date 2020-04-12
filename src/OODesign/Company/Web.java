package OODesign.Company;

public class Web extends SkillDecorator {
    public Web(Developer developer){
        this.developer = developer;
    }

    @Override
    public String getDescription() {
        return this.developer.getDescription() + " Web";
    }
}
