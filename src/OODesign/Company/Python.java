package OODesign.Company;

public class Python extends SkillDecorator {

    public Python(Developer developer){
        this.developer = developer;
    }

    @Override
    public String getDescription() {
        return this.developer.getDescription() + " Python";
    }
}
