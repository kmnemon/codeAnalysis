package codeanalysis;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static codeanalysis.ProjectDependency.findAllimportPaicTypes;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProjectDependencyTests {
    @Test
    void findAllimportPaicTypesTests(){
        List<String> con1 = Arrays.asList("asdf","import com.paic.aaa");
        List<String> con2 = Arrays.asList("asdf","import com.paic.bbb");
        List<String> con3 = Arrays.asList("asdf","import com.xxx.aaa");
        List<String> con4 = Arrays.asList("asdf"," com.paic.ccc");
        List<String> con5 = Arrays.asList("asdf","import com.paixxc.aaa");
        List<String> con6 = Arrays.asList("asdf","import  com.paic.ddd");

        TypeInfo t1 = new TypeInfo("x1", "packA", con1);
        TypeInfo t2 = new TypeInfo("x2", "packB", con2);
        TypeInfo t3 = new TypeInfo("x3", "packC", con3);
        TypeInfo t4 = new TypeInfo("x4", "packD", con4);
        TypeInfo t5 = new TypeInfo("x5", "packE", con5);
        TypeInfo t6 = new TypeInfo("x6", "packF", con6);

        List<TypeInfo> ti1 = Arrays.asList(t1,t2);
        List<TypeInfo> ti2 = Arrays.asList(t3,t4);
        List<TypeInfo> ti3 = Arrays.asList(t5,t6);

        Map<ModuleInfo, List<TypeInfo>> modulesInProject = new HashMap<>();
        modulesInProject.put(new ModuleInfo("x1"), ti1);
        modulesInProject.put(new ModuleInfo("x2"), ti2);
        modulesInProject.put(new ModuleInfo("x3"), ti3);


        assertEquals(3, findAllimportPaicTypes(modulesInProject).size());
        assertEquals("com.paic.aaa", findAllimportPaicTypes(modulesInProject).get(0));

    }
}
