package com.hl.core.lib.bean;

import java.io.Serializable;

/**
 *
 */
public class TypeItem implements Serializable{

	private String ID = "";
	private String Value = "";
	private String remark = "";

	private String TypeCode="";
	private String TypeName="";
	private String Code="";

	public int id;

	public TypeItem() {
		id = -1;
		ID = "";
		Value = "";
	}

	public TypeItem(String _ID, String _Value) {
		ID = _ID;
		Value = _Value;
	}
	public TypeItem(int _id, String _Value) {
		id = _id;
		ID = String.valueOf(id);
		Value = _Value;
	}
	public TypeItem(String _ID, String _Value, String remark) {
		this(_ID , _Value);
		this.remark = remark ;
	}
	public TypeItem(String _ID, String _TypeCode, String _Code, String _Value) {
		ID = _ID;
		TypeCode = _TypeCode;
		Code = _Code;
		Value = _Value;
	}
	public TypeItem(String _ID, String _TypeCode, String _TypeName, String _Code, String _Value) {
		ID = _ID;
		TypeCode = _TypeCode;
		TypeName = _TypeName;
		Code = _Code;
		Value = _Value;
	}

	@Override
	public String toString() { //ΪʲôҪ��дtoString()�أ���Ϊ����������ʾ��ݵ�ʱ��������������Ķ������ַ������£�ֱ�Ӿ�ʹ�ö���.toString()
		return Value;
	}

	public String getID() {
		return ID;
	}

	public String getValue() {
		return Value;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public void setValue(String value) {
		Value = value;
	}

	public String getTypeCode() {
		return TypeCode;
	}

	public void setTypeCode(String typeCode) {
		TypeCode = typeCode;
	}

	public String getTypeName() {
		return TypeName;
	}

	public void setTypeName(String typeName) {
		TypeName = typeName;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}
}
