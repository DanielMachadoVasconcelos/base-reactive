package br.com.ead.base;

import org.junit.platform.engine.discovery.ClassNameFilter;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestPlan;
import org.junit.platform.launcher.core.LauncherConfig;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

import java.io.PrintWriter;

import static org.junit.platform.engine.discovery.ClassNameFilter.STANDARD_INCLUDE_PATTERN;

public class Application {

    public static void main(String[] args) {

        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(DiscoverySelectors.selectPackage("br.com.ead.base"))
                .filters(ClassNameFilter.includeClassNamePatterns(STANDARD_INCLUDE_PATTERN))
                .build();

        LauncherConfig launcherConfig = LauncherConfig.builder()
                .enableTestEngineAutoRegistration(false)
                .enablePostDiscoveryFilterAutoRegistration(false)
                .enableTestExecutionListenerAutoRegistration(false)
                .build();

        SummaryGeneratingListener listener = new SummaryGeneratingListener();
        Launcher launcher = LauncherFactory.create(launcherConfig);
        TestPlan plan = launcher.discover(request);
        launcher.execute(plan, listener);

        TestExecutionSummary summary = listener.getSummary();
        summary.printTo(new PrintWriter(System.out));
    }
}
