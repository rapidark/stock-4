package com.gesangwu.spider.biz.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CliqueDeptExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int offset = -1;

    protected int rows = -1;

    public CliqueDeptExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setOffset(int offset) {
        this.offset=offset;
    }

    public int getOffset() {
        return offset;
    }

    public void setRows(int rows) {
        this.rows=rows;
    }

    public int getRows() {
        return rows;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCliqueIdIsNull() {
            addCriterion("clique_id is null");
            return (Criteria) this;
        }

        public Criteria andCliqueIdIsNotNull() {
            addCriterion("clique_id is not null");
            return (Criteria) this;
        }

        public Criteria andCliqueIdEqualTo(Long value) {
            addCriterion("clique_id =", value, "cliqueId");
            return (Criteria) this;
        }

        public Criteria andCliqueIdNotEqualTo(Long value) {
            addCriterion("clique_id <>", value, "cliqueId");
            return (Criteria) this;
        }

        public Criteria andCliqueIdGreaterThan(Long value) {
            addCriterion("clique_id >", value, "cliqueId");
            return (Criteria) this;
        }

        public Criteria andCliqueIdGreaterThanOrEqualTo(Long value) {
            addCriterion("clique_id >=", value, "cliqueId");
            return (Criteria) this;
        }

        public Criteria andCliqueIdLessThan(Long value) {
            addCriterion("clique_id <", value, "cliqueId");
            return (Criteria) this;
        }

        public Criteria andCliqueIdLessThanOrEqualTo(Long value) {
            addCriterion("clique_id <=", value, "cliqueId");
            return (Criteria) this;
        }

        public Criteria andCliqueIdIn(List<Long> values) {
            addCriterion("clique_id in", values, "cliqueId");
            return (Criteria) this;
        }

        public Criteria andCliqueIdNotIn(List<Long> values) {
            addCriterion("clique_id not in", values, "cliqueId");
            return (Criteria) this;
        }

        public Criteria andCliqueIdBetween(Long value1, Long value2) {
            addCriterion("clique_id between", value1, value2, "cliqueId");
            return (Criteria) this;
        }

        public Criteria andCliqueIdNotBetween(Long value1, Long value2) {
            addCriterion("clique_id not between", value1, value2, "cliqueId");
            return (Criteria) this;
        }

        public Criteria andSecDeptCodeIsNull() {
            addCriterion("sec_dept_code is null");
            return (Criteria) this;
        }

        public Criteria andSecDeptCodeIsNotNull() {
            addCriterion("sec_dept_code is not null");
            return (Criteria) this;
        }

        public Criteria andSecDeptCodeEqualTo(String value) {
            addCriterion("sec_dept_code =", value, "secDeptCode");
            return (Criteria) this;
        }

        public Criteria andSecDeptCodeNotEqualTo(String value) {
            addCriterion("sec_dept_code <>", value, "secDeptCode");
            return (Criteria) this;
        }

        public Criteria andSecDeptCodeGreaterThan(String value) {
            addCriterion("sec_dept_code >", value, "secDeptCode");
            return (Criteria) this;
        }

        public Criteria andSecDeptCodeGreaterThanOrEqualTo(String value) {
            addCriterion("sec_dept_code >=", value, "secDeptCode");
            return (Criteria) this;
        }

        public Criteria andSecDeptCodeLessThan(String value) {
            addCriterion("sec_dept_code <", value, "secDeptCode");
            return (Criteria) this;
        }

        public Criteria andSecDeptCodeLessThanOrEqualTo(String value) {
            addCriterion("sec_dept_code <=", value, "secDeptCode");
            return (Criteria) this;
        }

        public Criteria andSecDeptCodeLike(String value) {
            addCriterion("sec_dept_code like", value, "secDeptCode");
            return (Criteria) this;
        }

        public Criteria andSecDeptCodeNotLike(String value) {
            addCriterion("sec_dept_code not like", value, "secDeptCode");
            return (Criteria) this;
        }

        public Criteria andSecDeptCodeIn(List<String> values) {
            addCriterion("sec_dept_code in", values, "secDeptCode");
            return (Criteria) this;
        }

        public Criteria andSecDeptCodeNotIn(List<String> values) {
            addCriterion("sec_dept_code not in", values, "secDeptCode");
            return (Criteria) this;
        }

        public Criteria andSecDeptCodeBetween(String value1, String value2) {
            addCriterion("sec_dept_code between", value1, value2, "secDeptCode");
            return (Criteria) this;
        }

        public Criteria andSecDeptCodeNotBetween(String value1, String value2) {
            addCriterion("sec_dept_code not between", value1, value2, "secDeptCode");
            return (Criteria) this;
        }

        public Criteria andSecDeptNameIsNull() {
            addCriterion("sec_dept_name is null");
            return (Criteria) this;
        }

        public Criteria andSecDeptNameIsNotNull() {
            addCriterion("sec_dept_name is not null");
            return (Criteria) this;
        }

        public Criteria andSecDeptNameEqualTo(String value) {
            addCriterion("sec_dept_name =", value, "secDeptName");
            return (Criteria) this;
        }

        public Criteria andSecDeptNameNotEqualTo(String value) {
            addCriterion("sec_dept_name <>", value, "secDeptName");
            return (Criteria) this;
        }

        public Criteria andSecDeptNameGreaterThan(String value) {
            addCriterion("sec_dept_name >", value, "secDeptName");
            return (Criteria) this;
        }

        public Criteria andSecDeptNameGreaterThanOrEqualTo(String value) {
            addCriterion("sec_dept_name >=", value, "secDeptName");
            return (Criteria) this;
        }

        public Criteria andSecDeptNameLessThan(String value) {
            addCriterion("sec_dept_name <", value, "secDeptName");
            return (Criteria) this;
        }

        public Criteria andSecDeptNameLessThanOrEqualTo(String value) {
            addCriterion("sec_dept_name <=", value, "secDeptName");
            return (Criteria) this;
        }

        public Criteria andSecDeptNameLike(String value) {
            addCriterion("sec_dept_name like", value, "secDeptName");
            return (Criteria) this;
        }

        public Criteria andSecDeptNameNotLike(String value) {
            addCriterion("sec_dept_name not like", value, "secDeptName");
            return (Criteria) this;
        }

        public Criteria andSecDeptNameIn(List<String> values) {
            addCriterion("sec_dept_name in", values, "secDeptName");
            return (Criteria) this;
        }

        public Criteria andSecDeptNameNotIn(List<String> values) {
            addCriterion("sec_dept_name not in", values, "secDeptName");
            return (Criteria) this;
        }

        public Criteria andSecDeptNameBetween(String value1, String value2) {
            addCriterion("sec_dept_name between", value1, value2, "secDeptName");
            return (Criteria) this;
        }

        public Criteria andSecDeptNameNotBetween(String value1, String value2) {
            addCriterion("sec_dept_name not between", value1, value2, "secDeptName");
            return (Criteria) this;
        }

        public Criteria andDeptTypeIsNull() {
            addCriterion("dept_type is null");
            return (Criteria) this;
        }

        public Criteria andDeptTypeIsNotNull() {
            addCriterion("dept_type is not null");
            return (Criteria) this;
        }

        public Criteria andDeptTypeEqualTo(Integer value) {
            addCriterion("dept_type =", value, "deptType");
            return (Criteria) this;
        }

        public Criteria andDeptTypeNotEqualTo(Integer value) {
            addCriterion("dept_type <>", value, "deptType");
            return (Criteria) this;
        }

        public Criteria andDeptTypeGreaterThan(Integer value) {
            addCriterion("dept_type >", value, "deptType");
            return (Criteria) this;
        }

        public Criteria andDeptTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("dept_type >=", value, "deptType");
            return (Criteria) this;
        }

        public Criteria andDeptTypeLessThan(Integer value) {
            addCriterion("dept_type <", value, "deptType");
            return (Criteria) this;
        }

        public Criteria andDeptTypeLessThanOrEqualTo(Integer value) {
            addCriterion("dept_type <=", value, "deptType");
            return (Criteria) this;
        }

        public Criteria andDeptTypeIn(List<Integer> values) {
            addCriterion("dept_type in", values, "deptType");
            return (Criteria) this;
        }

        public Criteria andDeptTypeNotIn(List<Integer> values) {
            addCriterion("dept_type not in", values, "deptType");
            return (Criteria) this;
        }

        public Criteria andDeptTypeBetween(Integer value1, Integer value2) {
            addCriterion("dept_type between", value1, value2, "deptType");
            return (Criteria) this;
        }

        public Criteria andDeptTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("dept_type not between", value1, value2, "deptType");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNull() {
            addCriterion("gmt_create is null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNotNull() {
            addCriterion("gmt_create is not null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateEqualTo(Date value) {
            addCriterion("gmt_create =", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotEqualTo(Date value) {
            addCriterion("gmt_create <>", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThan(Date value) {
            addCriterion("gmt_create >", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_create >=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThan(Date value) {
            addCriterion("gmt_create <", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThanOrEqualTo(Date value) {
            addCriterion("gmt_create <=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIn(List<Date> values) {
            addCriterion("gmt_create in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotIn(List<Date> values) {
            addCriterion("gmt_create not in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateBetween(Date value1, Date value2) {
            addCriterion("gmt_create between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotBetween(Date value1, Date value2) {
            addCriterion("gmt_create not between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}