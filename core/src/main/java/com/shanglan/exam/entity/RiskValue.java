package com.shanglan.exam.entity;


import com.shanglan.exam.base.BaseEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * 警报项
 */
//@Entity
//@Table(name = "cnoa_risk_value")
public class RiskValue extends BaseEntity {


    private static final long serialVersionUID = 1211747394022548863L;

    private String riskAddr;//风险地点
    private String riskDesc;//风险描述
    private String riskType;//风险类型
    private String riskValue;//风险值
    private String riskLevel;//风险等级
    private String precaution;//管控措施
    private String riskAnalysis;//风险分析
    private String riskDept;//责任部门
    private String responsible;//责任人
    private LocalDateTime handleTime;//治理时限
    private String handleMoney;//治理资金
    private String handleResult;//管控措施执行情况
    private LocalDateTime checkTime;//检查时间
    private LocalDateTime publishTime;//发布时间
    private LocalDateTime updateTime;//发布时间
    private boolean deleted;//逻辑删除，默认false

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private User publishUser;//发布人
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private User handleUser;//处理人
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private User deleteUser;//删除


}
