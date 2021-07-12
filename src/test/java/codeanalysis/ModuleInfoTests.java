package codeanalysis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModuleInfoTests {
    @Test
    void sameModuleTests(){
        ModuleInfo m1 = new ModuleInfo("aaa");
        ModuleInfo m2 = new ModuleInfo("aaa");

        assertEquals(m1, m2);
    }


}
