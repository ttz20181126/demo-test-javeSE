package cn.getech.test.dto;

/**
 * @author
 * 条码是否有反展 0-无 1-有
 */

public enum ReverseEnum {


	/**
	 * 无
	 */
	NO_REVERSE(0),

	/**
	 * 有
	 */
	IS_REVERSE(1);

	private Integer code;

	ReverseEnum(Integer code) {
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}
}
