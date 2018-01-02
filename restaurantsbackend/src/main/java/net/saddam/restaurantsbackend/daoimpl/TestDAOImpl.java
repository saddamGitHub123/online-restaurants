package net.saddam.restaurantsbackend.daoimpl;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.saddam.restaurantsbackend.dao.TestDAO;
import net.saddam.restaurantsbackend.dto.Test;
import net.saddam.restaurantsbackend.dto.User;


/**
 * 
 * @author saddam
 *
 */
@Repository("testDAO")
@Transactional
public class TestDAOImpl implements TestDAO {
	
	private static final Logger log = LoggerFactory.getLogger(TestDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User saveOTPasPaswd(User user) {	
		String Password = user.getPassword();
		String Shop_ID = user.getShop_ID();
		String User_Id = user.getUser_Id();
		
		// update particular column using shopid and userid
		String selectProductsByShopId = "UPDATE User SET Password = :Password WHERE Shop_ID = :Shop_ID AND User_Id = :User_Id";
		
		//set the data base value
		int updatedEntities = sessionFactory.getCurrentSession()
				 .createQuery( selectProductsByShopId )
		        .setParameter( "Password", Password )
		        .setParameter( "Shop_ID", Shop_ID )
		        .setParameter( "User_Id", User_Id )
		        .executeUpdate();
		
		if(updatedEntities == 1)
		  return user;
		else 
			return user;
		
	}

	//save image to database as a blob value
	@Override
	public Test saveImage(Test test) {
		
		  String url = test.getUrl();
		 // byte[] image = test.getImage();
		System.out.println(url);
		//save image into database
    	File file = new File(url);
        byte[] bFile = new byte[(int) file.length()];
        
        //System.out.println(bFile);
        
       /* try {
	     FileInputStream fileInputStream = new FileInputStream(file);
	     //convert file into array of bytes
	     fileInputStream.read(bFile);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }*/
        if(bFile != null && bFile.length != 0 ) {
       Test test1 = new Test();
        test1.setImage(bFile);
        test1.setUrl(url);
        sessionFactory.getCurrentSession().persist(test1);
        
        }

        Integer test_ID = 14;
        
        String selectProductsByShopId = "FROM Test WHERE test_ID = :test_ID";
        List<Test> list= sessionFactory
				.getCurrentSession()
				.createQuery(selectProductsByShopId, Test.class)
					.setParameter("test_ID", test_ID)
				//	.setParameter("Availability", true)
						.getResultList();
        
       // for (int i=list.size()-1  ;i>0;i++) {
        //int count = list.size();
        byte[] bAvatar = list.get(0).getImage();
       // Blob blob = list.get(0).getImage();
        
        //Blob blob = rs.getBlob(cloumnName[i]);
       // byte[] bdata = bAvatar.getBytes(1, (int) blob.length());
        
      //  String s = new String(bAvatar);
       // byte[] bdata = new byte[(int) bAvatar.length];
       // String s = new String(bdata);
        String base64Encoded = DatatypeConverter.printBase64Binary(bAvatar);
        
        byte[] byteArray = DatatypeConverter.parseBase64Binary(base64Encoded);
        System.out.println("image to byte"+bFile);
        System.out.println("fro database blob to byte"+bAvatar);
        System.out.println("byte to base64"+base64Encoded);
        System.out.println("base64 to byte"+byteArray);
        
        
          Test t1 = list.get(0);

       /* try{
            FileOutputStream fos = new FileOutputStream("C:\\Users\\Inxts-Windows\\Pictures\\image\\tiger1.jpg");
            fos.write(bAvatar);
            fos.close();
        }catch(Exception e){
            e.printStackTrace();
        }*/

		
		return t1;
	}

}
