package org.jenkinsci.plugins.testExample;

import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.Action;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prit8976 on 8/27/15.
 */
public class TestExampleProjectAction implements Action {

    private AbstractProject<?, ?> project;

    @Override
    public String getIconFileName() {
        return "/plugin/testExample/img/project_icon.png";
    }

    @Override
    public String getDisplayName() {
        return "Test Example Project Action";
    }

    @Override
    public String getUrlName() {
        return "testExamplePA";
    }

    public AbstractProject<?, ?> getProject() {
        return this.project;
    }

    public String getProjectName() {
        return this.project.getName();
    }

    public List<String> getProjectMessages() {
        List<String> projectMessages = new ArrayList<String>();
        List<? extends AbstractBuild<?, ?>> builds = project.getBuilds();
        String projectMessage="";
        final Class<TestExampleBuildAction> buildClass = TestExampleBuildAction.class;

        for (AbstractBuild<?, ?> currentBuild : builds) {
            projectMessage = "Build #"+currentBuild.getAction(buildClass).getBuildNumber()
                    +": "+currentBuild.getAction(buildClass).getMessage();
            projectMessages.add(projectMessage);
        }
        return projectMessages;
    }

    TestExampleProjectAction(final AbstractProject<?, ?> project) {
        this.project = project;
    }
}
