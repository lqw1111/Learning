package OODesign.Company;

public class CSharp extends SkillDecorator {
    public CSharp(Developer developer){
        this.developer = developer;
    }

    @Override
    public String getDescription() {
        return this.developer.getDescription() + " C#";
    }
}
