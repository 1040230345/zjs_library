package dao;

/**
 * 适配器
 */
public interface IDaoAdapter {


	/**
	 * 执行查询，返回一个实例
	 * @param sql
	 * @param requiredType
	 * @param args
	 * @return
	 */
	public <K> K queryForObject(String sql, Class<K> requiredType, Object... args);


}
