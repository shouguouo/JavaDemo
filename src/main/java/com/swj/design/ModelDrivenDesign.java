package com.swj.design;

import java.util.HashSet;
import java.util.Set;

/**
 * 功能说明:
 *
 * @author wink~
 */
public class ModelDrivenDesign {

}

abstract class AbstractNet {
    private Set rules;

    void assignRule(LayoutRule rule) {
        rules.add(rule);
    }

    Set assignedRules() {
        return rules;
    }
}

class Net extends AbstractNet {
    private Bus bus;

    Set assignedRules() {
        Set result = new HashSet();
        result.addAll(super.assignedRules());
        return result;
    }
}
// 电路连接
class LayoutRule {
    private String netName;

    private String componentPin;

    public String getNetName() {
        return netName;
    }

    public void setNetName(String netName) {
        this.netName = netName;
    }

    public String getComponentPin() {
        return componentPin;
    }

    public void setComponentPin(String componentPin) {
        this.componentPin = componentPin;
    }
}
// 总线规则
class Bus {
    private String busName;

    private String ruleType;

    private String parameters;

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }
}