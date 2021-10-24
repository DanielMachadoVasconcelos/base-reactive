package br.com.ead.base;

import lombok.extern.slf4j.Slf4j;
import static org.junit.platform.engine.discovery.ClassNameFilter.includeClassNamePatterns;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;

import org.junit.platform.engine.FilterResult;
import org.junit.platform.engine.TestDescriptor;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryListener;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.LauncherSession;
import org.junit.platform.launcher.LauncherSessionListener;
import org.junit.platform.launcher.PostDiscoveryFilter;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestPlan;
import org.junit.platform.launcher.core.LauncherConfig;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import org.junit.platform.reporting.legacy.xml.LegacyXmlReportGeneratingListener;

@Slf4j
public class Application {

    public static void main(String[] args) {

        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(selectPackage("com.example.mytests"))
                .filters(includeClassNamePatterns(".*Tests"))
                .build();

        try (LauncherSession session = LauncherFactory.openSession()) {
            TestPlan testPlan = session.getLauncher().discover(request);
        }
    }
}
