package com.nelisriebezos.broozercruiserbot.domaintest;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@SelectPackages("com.nelisriebezos.broozercruiserbot.domaintest.classtest")
@Suite
@SuiteDisplayName("Domain tests")
public class DomainSuite {
}
