package com.zkt.find.common.util;


public class Condition {

	public Condition(){

	}
	public Condition(String col,String cond,String keyword){
		this.col = col;
		this.cond = cond;
		this.keyword = keyword;
		this.colType = "varchar2";
	}
	public Condition(String col,String cond,String keyword,String colType){
		this.col = col;
		this.cond = cond;
		this.keyword = keyword;
		this.colType = colType;
	}

	//字段名
	private String col;
	//条件 like = < >等等
	private String cond;
	//关键字
	private String keyword;
	//字段类型
	private String colType;

	private String sql;

	public String getCol() {
		return col;
	}

	public void setCol(String col) {
		this.col = col;
	}

	public String getCond() {
		return cond;
	}

	public void setCond(String cond) {
		this.cond = cond;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getColType() {
		return colType;
	}


	public void setColType(String colType) {
		this.colType = colType;
	}

	//
	public String toSqlCondition()
	{
		if(this.sql==null){
			if(colType.equalsIgnoreCase("varchar2"))
			{
				if(cond.equals("like"))
					this.sql = col +" " + cond + " '%" + keyword + "%'";
				else
					this.sql = col +" " + cond + " '" + keyword + "'";
			}
			if(colType.equalsIgnoreCase("date"))
				this.sql = col +" " + cond + " to_date('" + keyword + "','yyyy-mm-dd')";
		}
		return this.sql;
	}

	//
	public String mergeConditionOr(Condition c)
	{
		this.sql = this.toSqlCondition()+" or "+c.toSqlCondition();
		return this.sql;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

}
