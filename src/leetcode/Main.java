import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {
    /**
     * Iterate through each line of input.
     */
//    public static void main(String[] args) throws IOException {
//        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
//        BufferedReader in = new BufferedReader(reader);
//        String line;
//        List<String> param = new ArrayList<String>();
//        while ((line = in.readLine()) != null) {
//            System.out.println(line);
//            param.add(line);
//        }
//        System.out.println(helper(param));
//    }
//
//    private static String helper(List<String> param){
//        Integer n = Integer.valueOf(param.get(0));
//        List<List<Integer>> prereq = new ArrayList<>();
//        for(int i = 1 ; i < param.size() ; i ++){
//            String pam = param.get(i);
//            List<Integer> temp = new ArrayList<Integer>();
//            for(String course : pam.split(" ")){
//                temp.add(Integer.valueOf(course));
//            }
//            prereq.add(temp);
//        }
//        int[] res = findOrder(n, prereq);
//        StringBuilder sb = new StringBuilder();
//        for (Integer r : res) {
//            sb.append(r + " ");
//        }
//        return sb.toString();
//    }
//
//    public static int[] findOrder(int numCourses, List<List<Integer>> prerequisites) {
//        List<List<Integer>> graph = new ArrayList<>();
//
//        for(int i = 0 ; i < numCourses ; i ++){
//            graph.add(new ArrayList<Integer>());
//        }
//
//        for(List<Integer> list : prerequisites){
////            int first = pre[1];
//            int then = list.get(list.size() - 1);
//            for (int i = 0 ; i < list.size() - 1 ; i ++) {
//                graph.get(then).add(list.get(i));
//            }
//        }
//
//        List<Integer> list = new ArrayList<Integer>();
//        int[] visited = new int[numCourses];
//
//        for(int i = 0 ; i < numCourses ; i ++){
//            if(dfs(i, graph, visited, list))
//                return new int[0];
//        }
//        int[] res = new int[list.size()];
//        for(int i = 0 ; i < list.size() ; i++){
//            res[i] = list.get(i);
//        }
//        return res;
//    }
//
//    private static boolean dfs(int index,  List<List<Integer>> graph, int[] visited, List<Integer> list){
//        if(visited[index] == 1)
//            return true;
//        if(visited[index] == 2)
//            return false;
//        visited[index] = 1;
//        for(int i = 0 ; i < graph.get(index).size() ; i ++){
//            if(dfs(graph.get(index).get(i), graph, visited, list))
//                return true;
//        }
//        list.add(index);
//        visited[index] = 2;
//        return false;
//    }

    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(reader);
        String line;
        List<Integer> parm = new ArrayList<Integer>();
        while ((line = in.readLine()) != null) {
            System.out.println(line);
            parm.add(Integer.valueOf(line));
        }
        System.out.println(helper(parm));
    }
    private static String helper(List<Integer> parm){
        Integer res = 0;
        List<Integer> temp = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        dfs(parm, 0, temp, visited, res);
        return String.valueOf(res);
    }

    private static void dfs(List<Integer> parm, Integer index , List<Integer> temp, Set<Integer> visited, Integer res){
        if(visited.contains(parm.get(index)) || visited.size() == parm.size())
            return;
        temp.add(parm.get(index));

        if (temp.size() == parm.size()){
            res ++;
            return;
        }

        for (int i = 0 ; i < parm.size() ; i ++){
            if (isPrime(temp.get(temp.size() - 1) + parm.get(i))){
                dfs(parm, i, temp, visited, res);
            }
        }

        temp.remove(temp.size() - 1);
    }

    public static boolean isPrime(int n)
    {
        if(n < 2) return false;
        for(int i = 2; i < n; ++i)
            if(n%i == 0) return false;
        return true;
    }


}