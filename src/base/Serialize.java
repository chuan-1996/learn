package base;

import java.io.*;

/**
 * 将类写到磁盘上
 *
 */
class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userId;
    private String userName;

    public User() {

    }
    public User(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
    public String getId() {
    	return this.userId;
    }
    public String getName() {
    	return this.userName;
    }
}

public class Serialize{
    //example
	public static void main(String[] args) {
		 User user = new User("1001", "Joe");
		 Serialize.writeObj("D:/user.txt",user);
		 User c = new User();
		 c = Serialize.readObj("D:/user.txt",c);
		 System.out.print(c.getId());
	}

	public static <T> void writeObj(String path,T a) {

	        try {
	            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path));
	            objectOutputStream.writeObject(a);
	            objectOutputStream.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	   }
	}

    public static <T> T readObj(String path,T a) {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path));
            try {
                Object object = objectInputStream.readObject();
                return (T)object;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally{
            	objectInputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
