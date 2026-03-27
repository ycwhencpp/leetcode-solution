/**
You are given a list of edges [[a,b], [b,c], [d,f], [f,g]]. Here a,b means a is manager of b. You have to implement 3 functions:
a. Return total number of employees under a manager (both direct and indirect)
b. Change the manager of an employee. If that employee has some reportees then those will also be affected.
c. Add new employees. Eg: [c,d] needs to be added.
Input format was not hardcoded. You have to write the main function on how input will be given to these apis.
The key was to have optimized solution such that (a) part should have TC - O(1)
**/

class EMDesign{
    HashMap<String, HashSet<String>> managerToEmployee = new HashMap<>();
    HashMap<String, Integer> managerToEmployeeCount = new HashMap<>();
    HashMap<String, String> employeeToManager = new HashMap<>();
    public EMDesign(String[][] edges){
        for(String[] row : edges){
            String manager = row[0];
            String employee = row[1];
            if(!managerToEmployee.containsKey(manager)){
                managerToEmployee.put(manager, new HashSet<>());
            }
            managerToEmployee.get(manager).add(employee);
            employeeToManager.put(employee, manager);
        }
        HashSet<String> visited = new HashSet<>();
        for(String person : managerToEmployee.keySet()){ //for disconnected group
            buildGraph(person, visited);
        }
    } 

    public int getEmployeeCount(String manager){  // 0(1)
        return map.getOrDefault(manager, 0);
    }

    public void changeManager(String employee, String newManager){ // 0(H)
        
        if(isCyclicRelation(employee, newManager)) return;
        int currEmployeeSubtreeCount = managerToEmployeeCount.get(employee)+1;
        String manager = employeeToManager.get(employee);
        // compute new subTree Count
        while(manager != null){
            managerToEmployeeCount.put(manager, managerToEmployeeCount.get(manager)-currEmployeeSubtreeCount);
            manager = employeeToManager.get(manager);
        }

        //detach
        String oldManager = employeeToManager.get(employee);
        managerToEmployee.remove(employee);

        //attach
        employeToManager.put(employee, newManager);
        if(!managerToEmployee.containsKey(newManager)){
            managerToEmployee.put(new HashSet<>());
        }
        managerToEmployee.get(newManager).add(employee);

        propogateCount(newManager, currEmployeeSubtreeCount);
    }

    public void propogateCount(String manager, int count){ // 0(H)
        while(manager!=null){
            managerToEmployeeCount.put(manager, managerToEmployeeCount.getOrDefault(manager, 0)+count); 
            manager = employeeToManager.get(manager);
        }
    }

    public void buildGraph(String person, HashSet<String> visited){ // 0(V+E)=> size of hashmap
        if(visited.contains(person)) return;
        visited.add(person);
        int count =0;

        for(String children : managerToEmployee.get(person)){
            count+= 1+buildGraph(children, visited);
        }
        managerToEmployeeCount.put(person, count);
    }

    // graph will get build whenever we change manager until then only increment count;
    public void addEmployee(String employee, String manager){ // 0(H) 
        if(!managerToEmployee.containsKey(manager)){
            managerToEmployee.put(new HashSet<>());
        }
        managerToEmployee.get(manager).add(employee);
        employeeToManager.put(employee, manager);

        propogateCount(manager, 1);
       

    }

    public boolean isCyclicRelation(String employee, String newManager){
        if(employee.equals(newManager)) return true;
        
        for(String node : managerToEmployee.get(employee)){
            if(isCyclicRelation(node, newManager))return true;
        }
        return false;
    }   
}