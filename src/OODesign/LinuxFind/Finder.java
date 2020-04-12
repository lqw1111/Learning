package OODesign.LinuxFind;

import java.util.ArrayList;
import java.util.List;

public class Finder {

    private List<Filter> filterChain;

    public Finder(){
        filterChain = new ArrayList<>();
    }

    public void addFilter(Filter filter){
        filterChain.add(filter);
    }

    public List<File> find(File root){
        List<File> res = new ArrayList<>();
        findFile(root, res);
        return res;
    }

    private void findFile(File file, List<File> res){
        if(file == null)
            return;
        if(satisify(file)){
            res.add(file);
        }
        for (File f : file.getFiles()) {
            findFile(f, res);
        }
    }

    private boolean satisify(File file){
        for (Filter filter : filterChain){
//            if (!filter.execute(file)) return false;
        }
        return true;
    }
}
