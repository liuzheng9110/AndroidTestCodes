package main;
import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;


public class DbDaoDaogenerator {
	public static void main(String[] args) throws Exception {
		Schema schema = new Schema(1, "bean_dao");
		
		addUser(schema);
		
		new DaoGenerator().generateAll(schema, "src/bean_dao");
	}

	private static void addUser(Schema schema) {
		Entity userInfo = schema.addEntity("UserInfo");
		userInfo.addIdProperty();
		userInfo.addStringProperty("userName");
		userInfo.addStringProperty("userSex");
		userInfo.addStringProperty("userAge");
	}
}
