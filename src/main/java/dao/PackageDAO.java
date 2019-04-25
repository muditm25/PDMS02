package dao;

import java.util.List;

import org.hibernate.Session;

import bean.Package;
import bean.Student;
import iiitb.com.PDMS.CommonSessionFactory;
import iiitb.com.PDMS.SendMail;

public class PackageDAO {
	
public static List<Package> getAllPackages(int id){
		
		Session ses = CommonSessionFactory.sf.openSession();
		
		List<Package> pkglist = ses.createNativeQuery("select * from package where stud_id="+id,Package.class).list();
		
		ses.close();
		return pkglist;
		
	}

public static String addPackage(Package pkg, int stud_id) {
	Session ses = CommonSessionFactory.sf.openSession();
	
	try {
		ses.beginTransaction();
		
		Student s1 = (Student)ses.get(Student.class, stud_id);
		List<Package> pkgs = s1.getPackages();
	
		Integer id = (Integer) ses.save(pkg);
		
		pkgs.add(pkg);
		s1.setPackages(pkgs);
		ses.save(s1);
		if(id>0)
			return "Added a new package!!";
	}
	catch(Exception e) {
		return "Could not add the package! Please try again!!!";
	}
	finally {
		ses.getTransaction().commit();
		ses.close();
	}
	return "You are here!!!"; 
}

public static String setReceived(String recname, String collect_time, String pkg_id, int stud_id) {
	
	Session ses = CommonSessionFactory.sf.openSession();

	try {
		ses.beginTransaction();
		
		Student s1 = (Student)ses.get(Student.class, stud_id);
		List<Package> pkgs = s1.getPackages();
	
		for(Package p : pkgs) {
			if(p.getPkg_id().equals(pkg_id)) {
				p.setPkg_collect_time(collect_time);
				p.setReciever_name(recname);
				p.setCollect_status(true);
				Integer id = (Integer) ses.save(p);
				if(id>0)
					return "Package received!!";
			}
		}
	}
	catch(Exception e) {
		return "Try again!!";
	}
	finally {
		ses.getTransaction().commit();
		ses.close();
	}
	return "failure";
}

public static String sendMailOnAdd(int id, String pkgid) {
	
	Session ses = CommonSessionFactory.sf.openSession();
	try {
		ses.beginTransaction();
		
		Student s1 = (Student)ses.get(Student.class,id);
		List<Package> pkgs = s1.getPackages();
		
		for(Package p : pkgs) {
			if(p.getPkg_id().equals(pkgid)) {
				String email = s1.getEmailid();			
				String resp = SendMail.sendOne(email,p);
				return resp;
			}
		}
	}
	finally {
		ses.getTransaction().commit();
		ses.close();
	}
	return "Mail not sent!!";
}

public static String deletePkg(int id,String pkgid) {

	Session ses = CommonSessionFactory.sf.openSession();
	try {
		ses.beginTransaction();
		
		Student s1 = (Student)ses.get(Student.class,id);
		List<Package> pkgs = s1.getPackages();
		
		for(Package p : pkgs) {
			if(p.getPkg_id().equals(pkgid)) {
				ses.delete(p);
				return "Successfully deleted!!!";
			}
		}
	}
	finally {
		ses.getTransaction().commit();
		ses.close();
	}
	return "Could not delete!!";
}


}
