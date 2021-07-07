package codeanalysis;

import java.util.List;

class ProjectDependencyInfo {
    private String projectName;

    private static final List<String> internalClasses;
    private static final List<String> thirdPartyClasses;

    static {
        internalClasses = null;
        thirdPartyClasses = null;
    }




    private ProjectDependencyInfo(){}


}
